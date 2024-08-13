package com.github.nymann.commitrefactoring.extensions;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.RefactoringStore;
import com.github.nymann.commitrefactoring.CommitMessageFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.LocalChangeList;
import com.intellij.openapi.vcs.changes.ui.CommitMessageProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RefactoringCommitMessageProvider implements CommitMessageProvider {
    @Override
    public @Nullable String getCommitMessage(@NotNull LocalChangeList localChangeList, @NotNull Project project) {
        List<Refactoring> refactorings = RefactoringStore.getInstance(project).getRefactorings();
        if (refactorings.isEmpty()) {
            return "UNSAFE";
        }
        if (refactorings.size() == 1) {
            Refactoring refactoring = refactorings.get(0);
            return CommitMessageFactory.create(refactoring).getMessage();
        }
        StringBuilder result = new StringBuilder();
        for (Refactoring refactoring : refactorings) {
            CommitMessage commitMessage = CommitMessageFactory.create(refactoring);
            result.append(commitMessage.getMessage()).append("\n");
        }
        return result.toString();
    }
}