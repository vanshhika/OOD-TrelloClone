package com.OOD.TrelloClone.model.RequestArgs;

import com.OOD.TrelloClone.model.TaskState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyTask {
    @NotNull
    long taskId;
    String taskName;
    long assignedTo;
    String description;
    List<String> comments;
    String stringstate;
}
