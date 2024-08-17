package com.github.nymann.commitrefactoring.intellij.extensions;

import com.github.nymann.commitrefactoring.intellij.IntelliJRefactoringService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.LocalChangeList;
import com.intellij.openapi.vcs.changes.ui.CommitMessageProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RefactoringCommitMessageProvider implements CommitMessageProvider {
    @Override
    public @Nullable String getCommitMessage(@NotNull LocalChangeList localChangeList, @NotNull Project project) {
        return project.getService(IntelliJRefactoringService.class).getCommitMessage();
    }
}
