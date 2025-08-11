package neo.project.task.statement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Семейное положение клиента")
public enum MaritalStatus {

    @Schema(description = "Холост/Не замужем")
    SINGLE,

    @Schema(description = "Женат/Замужем")
    MARRIED,

    @Schema(description = "Разведен(а)")
    DIVORCED,

    @Schema(description = "Вдовец/Вдова")
    WIDOWED // поменять в бд
}