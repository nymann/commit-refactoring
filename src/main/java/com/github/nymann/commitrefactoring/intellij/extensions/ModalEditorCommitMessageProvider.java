package com.github.nymann.commitrefactoring.intellij.extensions;

import com.github.nymann.commitrefactoring.intellij.IntelliJRefactoringService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.LocalChangeList;
import com.intellij.openapi.vcs.changes.ui.CommitMessageProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ModalEditorCommitMessageProvider implements CommitMessageProvider {
    @Override
    public @Nullable String getCommitMessage(@NotNull LocalChangeList localChangeList, @NotNull Project project) {
        IntelliJRefactoringService service = project.getService(IntelliJRefactoringService.class);
        return service.getModalAutomaticCommitMessage();
    }
}
