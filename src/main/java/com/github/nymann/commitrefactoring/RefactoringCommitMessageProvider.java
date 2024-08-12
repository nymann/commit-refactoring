package com.github.nymann.commitrefactoring;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.changes.LocalChangeList;
import com.intellij.openapi.vcs.changes.ui.CommitMessageProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RefactoringCommitMessageProvider implements CommitMessageProvider {
    @Override
    public @Nullable String getCommitMessage(@NotNull LocalChangeList localChangeList, @NotNull Project project) {
        List<Refactoring> refactorings = SingletonRefactoringStore.getInstance().getRefactorings();
        if (refactorings.isEmpty()) {
            return "Test";
        }
        if (refactorings.size() == 1) {
            return refactorings.get(0).description();
        }
        StringBuilder result = new StringBuilder();
        for (Refactoring refactoring : refactorings) {
            result.append(refactoring.description()).append("\n");
        }

        return result.toString();
    }
}
