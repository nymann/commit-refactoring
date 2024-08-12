package com.github.nymann.commitrefactoring.listeners;

import com.github.nymann.commitrefactoring.SingletonRefactoringStore;
import com.intellij.refactoring.listeners.RefactoringEventData;
import com.intellij.refactoring.listeners.RefactoringEventListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RefactoringListener implements RefactoringEventListener {
    private final SingletonRefactoringStore refactorings;

    public RefactoringListener() {
        this.refactorings = SingletonRefactoringStore.getInstance();
    }

    @Override
    public void refactoringStarted(@NotNull String refactoringId, @Nullable RefactoringEventData beforeData) {
        // TODO: We can get information about the refactoring here such as what the method was renamed from
        RefactoringEventListener.super.refactoringStarted(refactoringId, beforeData);
    }

    @Override
    public void refactoringDone(@NotNull String s, @Nullable RefactoringEventData refactoringEventData) {
        refactorings.addRefactoring(s, refactoringEventData);
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
