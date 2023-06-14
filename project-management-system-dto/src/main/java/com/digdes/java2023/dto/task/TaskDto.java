package com.digdes.java2023.dto.task;

import com.digdes.java2023.model.task.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Проект. Данные на выход по операциям поиска")
public class TaskDto {

    @Schema(description = "Наименование задачи")
    private String name;

    @Schema(description = "Описание задачи")
    private String description;

    @Schema(description = "Исполнитель задачи")
    private String employee;

    @Schema(description = "Оценка трудозатрат в часах")
    private int estimate;

    @Schema(description = "Крайний срок")
    private String deadline;

    @Schema(description = "Статус задачи")
    private TaskStatus taskStatus;

    @Schema(description = "Автор задачи")
    private String author;

    @Schema(description = "Дата создания")
    private String createDate;

    @Schema(description = "Дата изменения")
    private String changeDate;
}
