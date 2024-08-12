package com.github.nymann.commitrefactoring.listeners;

import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.SingletonRefactoringStore;
import com.intellij.openapi.project.Project;
import com.intellij.refactoring.listeners.RefactoringEventData;
import com.intellij.refactoring.listeners.RefactoringEventListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RefactoringListener implements RefactoringEventListener {
    private final SingletonRefactoringStore refactorings;
    private Refactoring currentRefactoring;

    public RefactoringListener(Project project) {
        this.refactorings = SingletonRefactoringStore.getInstance(project);
    }

    @Override
    public void refactoringStarted(@NotNull String refactoringId, @Nullable RefactoringEventData beforeData) {
        if (beforeData != null) {
            currentRefactoring = new Refactoring(refactoringId, beforeData);
        } else {
            currentRefactoring = new Refactoring(refactoringId);
        }
    }

    @Override
    public void refactoringDone(@NotNull String refactoringId, @Nullable RefactoringEventData refactoringEventData) {
        if (currentRefactoring == null) {
            throw new RuntimeException(refactoringId + " has not been started");
        }
        if (refactoringEventData != null) {
            currentRefactoring.setAfter(refactoringEventData);
        }
        refactorings.addRefactoring(currentRefactoring);
    }

    @Override
    public void conflictsDetected(@NotNull String refactoringId, @NotNull RefactoringEventData conflictsData) {
        // TODO: Unsafe?
        RefactoringEventListener.super.conflictsDetected(refactoringId, conflictsData);
    }

    @Override
    public void undoRefactoring(@NotNull String refactoringId) {
        refactorings.undoLastRefactoring();
    }

    @Override
    public void redoRefactoring(@NotNull String refactoringId) {
        refactorings.redoLastRefactoring();
    }
}
