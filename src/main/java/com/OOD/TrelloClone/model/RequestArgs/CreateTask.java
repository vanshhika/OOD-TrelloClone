package com.OOD.TrelloClone.model.RequestArgs;

import com.OOD.TrelloClone.model.TaskState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTask {
    @NotNull
    String taskName;
    long assignedTo;
    String description;
    List<String> comments;
}
