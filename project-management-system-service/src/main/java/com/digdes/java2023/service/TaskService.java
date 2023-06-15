package com.digdes.java2023.service;

import com.digdes.java2023.amqp.MessageConsumer;
import com.digdes.java2023.amqp.MessageProducer;
import com.digdes.java2023.dto.task.EditTaskDto;
import com.digdes.java2023.dto.task.FindTaskDto;
import com.digdes.java2023.dto.task.TaskDto;
import com.digdes.java2023.mail.TestMailSender;
import com.digdes.java2023.mapping.TaskMapper;
import com.digdes.java2023.model.employee.Employee;
import com.digdes.java2023.model.task.Task;
import com.digdes.java2023.model.task.TaskStatus;
import com.digdes.java2023.repository.EmployeeRepository;
import com.digdes.java2023.repository.TaskRepository;
import com.digdes.java2023.repository.filter.TaskFilter;
import com.digdes.java2023.repository.filter.TaskSpecification;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            task.setAuthor(employeeRepository.findByUsername(authentication.getName()).orElseThrow());
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

    public List<TaskDto> find(FindTaskDto dto) {
//        DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        LocalDate deadlineMin = LocalDate.parse(dto.getDeadlineMin().format(formatterDate));
//        LocalDate deadlineMax = LocalDate.parse(dto.getDeadlineMax().format(formatterDate));
//        LocalDateTime createDateMin = LocalDateTime.parse(dto.getCreateDateMin(), formatterDateTime);
//        LocalDateTime createDateMax = LocalDateTime.parse(dto.getCreateDateMax(), formatterDateTime);

        List<TaskDto> result = new ArrayList<>();

        TaskFilter taskFilter = new TaskFilter();
        taskFilter.setTaskStatus(dto.getTaskStatus());

        if (!ObjectUtils.isEmpty(dto.getEmployee())) {
            Optional<Employee> employee = employeeRepository.findByAccount(dto.getEmployee());
            if (employee.isEmpty()) return result;
            else taskFilter.setEmployee(employee.orElseThrow());
        }

        if (!ObjectUtils.isEmpty(dto.getAuthor())) {
            Optional<Employee> author = employeeRepository.findByAccount(dto.getAuthor());
            if (author.isEmpty()) return result;
            else taskFilter.setAuthor(author.orElseThrow());
        }

        taskFilter.setDeadlineMin(dto.getDeadlineMin());
        taskFilter.setDeadlineMax(dto.getDeadlineMax());
        taskFilter.setCreateDateMin(dto.getCreateDateMin());
        taskFilter.setCreateDateMax(dto.getCreateDateMax());

        List<Task> tasks = taskRepository.findAll(TaskSpecification.getSpec(taskFilter));


        for (Task item:tasks) {
            result.add(TaskMapper.mapFromEntity(item));
        }
        return result;
    }

}
