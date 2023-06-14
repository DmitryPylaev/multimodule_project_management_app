package com.digdes.java2023.service;

import com.digdes.java2023.amqp.MessageConsumer;
import com.digdes.java2023.amqp.MessageProducer;
import com.digdes.java2023.dto.task.EditTaskDto;
import com.digdes.java2023.dto.task.TaskDto;
import com.digdes.java2023.mail.TestMailSender;
import com.digdes.java2023.mapping.TaskMapper;
import com.digdes.java2023.model.employee.Employee;
import com.digdes.java2023.model.task.Task;
import com.digdes.java2023.model.task.TaskStatus;
import com.digdes.java2023.repository.EmployeeRepository;
import com.digdes.java2023.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskService {
    private EmployeeRepository employeeRepository;
    private TaskRepository taskRepository;
    private MessageProducer messageProducer;

    public TaskDto create(EditTaskDto taskDto) {
        Task task = TaskMapper.create(taskDto);
        Employee employee = employeeRepository.findByAccount(taskDto.getEmployee()).orElseThrow();
        task.setEmployee(employee);
        task.setTaskStatus(TaskStatus.NEW);
        String author = SecurityContextHolder.getContext().getAuthentication().getName();
        if (author != null) {
            task.setAuthor(employeeRepository.findByUsername(author).orElseThrow());
        } else task.setAuthor(employee);
        task.setCreateDate(LocalDateTime.now());
        task.setChangeDate(LocalDateTime.now());
        taskRepository.save(task);
        if (employee.getEmail()!=null) {
            TestMailSender.address = employee.getEmail();
            MessageConsumer.messageText = "Hello, you assigned to task named: " + task.getName();
            messageProducer.sendMessage();
        }
        return TaskMapper.mapFromEntity(task);
    }

    public TaskDto edit(EditTaskDto editTaskDto) {
        Optional<Task> taskFromBase = taskRepository.findByName(editTaskDto.getName());
        if (taskFromBase.isPresent()) {
            Task taskToSave = TaskMapper.edit(editTaskDto, taskFromBase.get());
            if (editTaskDto.getEmployee() != null) {
                Employee employee = employeeRepository.findByAccount(editTaskDto.getEmployee()).orElseThrow();
                taskToSave.setEmployee(employee);
            }
            taskToSave.setChangeDate(LocalDateTime.now());
            taskRepository.save(taskToSave);
            return TaskMapper.mapFromEntity(taskToSave);
        }
        return new TaskDto();
    }

    public TaskDto shiftStatus(String projectName) {
        Optional<Task> taskFromBase = taskRepository.findByName(projectName);
        if (taskFromBase.isPresent()) {
            Task task = taskFromBase.get();
            switch (task.getTaskStatus()) {
                case NEW -> {
                    task.setTaskStatus(TaskStatus.IN_PROGRESS);
                    task.setChangeDate(LocalDateTime.now());
                }
                case IN_PROGRESS -> {
                    task.setTaskStatus(TaskStatus.DONE);
                    task.setChangeDate(LocalDateTime.now());
                }
                case DONE -> task.setTaskStatus(TaskStatus.CLOSED);
            }
            taskRepository.save(task);
            return TaskMapper.mapFromEntity(task);
        }
        return new TaskDto();
    }

    // TODO: 14.06.2023 Сделать логику поиска через фильтры specification 
//    public List<EmployeeDto> find(String input) {
//        List<Employee> employeeList = employeeRepository.findByEmployeeStatusAndLastNameContainingOrNameContainingOrPatronymicContainingOrAccountContainingOrEmailContaining(
//                EmployeeStatus.ACTIVE,
//                input,
//                input,
//                input,
//                input,
//                input);
//        List<EmployeeDto> result = new ArrayList<>();
//        for (Employee employee : employeeList) {
//            result.add(EmployeeMapper.mapFromEntity(employee));
//        }
//        return result;
//    }

}
