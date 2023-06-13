package com.digdes.java2023.mapping;

import com.digdes.java2023.dto.task.EditTaskDto;
import com.digdes.java2023.dto.task.TaskDto;
import com.digdes.java2023.model.task.Task;

import java.time.LocalDate;

public class TaskMapper {
    public static Task create(EditTaskDto editTaskDto) {
        Task task = new Task();
        task.setName(editTaskDto.getName());
        task.setDescription(editTaskDto.getDescription());
        task.setEstimate(editTaskDto.getEstimate());
        task.setDeadline(LocalDate.parse(editTaskDto.getDeadline()));
        return task;
    }

    public static TaskDto mapFromEntity(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setEmployee(task.getEmployee().getAccount());
        taskDto.setEstimate(task.getEstimate());
        taskDto.setDeadline(task.getDeadline().toString());
        taskDto.setTaskStatus(task.getTaskStatus());
        taskDto.setAuthor(task.getAuthor().getAccount());
        taskDto.setCreateDate(task.getCreateDate().toString());
        taskDto.setChangeDate(task.getChangeDate().toString());

        return taskDto;
    }

    public static Task edit(EditTaskDto taskDto, Task taskOld) {
        if (taskDto.getName() != null) taskOld.setName(taskDto.getName());
        if (taskDto.getDescription() != null) taskOld.setDescription(taskDto.getDescription());
        if (taskDto.getEstimate() != 0) taskOld.setEstimate(taskDto.getEstimate());
        if (taskDto.getDeadline() != null) taskOld.setDeadline(LocalDate.parse(taskDto.getDeadline()));
        return taskOld;
    }
}
