package com.github.nymann.commitrefactoring.listeners;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementFactory;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.RefactoringStore;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.refactoring.listeners.RefactoringEventData;
import com.intellij.refactoring.listeners.RefactoringEventListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RefactoringListener implements RefactoringEventListener {
    private static final Logger logger = Logger.getInstance(RefactoringListener.class);
    private final RefactoringStore refactorings;
    private CodeElement before = null;

    public RefactoringListener(Project project) {
        this.refactorings = RefactoringStore.getInstance(project);
    }

    @Override
    public void refactoringStarted(@NotNull String refactoringId, @Nullable RefactoringEventData beforeData) {
        before = CodeElementFactory.createFromPsiElement(beforeData);
    }

    @Override
    public void refactoringDone(@NotNull String refactoringId, @Nullable RefactoringEventData refactoringEventData) {
        CodeElement after = CodeElementFactory.createFromPsiElement(refactoringEventData);
        Refactoring refactoring = new Refactoring(refactoringId, before, after);
        refactorings.addRefactoring(refactoring);
    }

    @Override
    public void conflictsDetected(@NotNull String refactoringId, @NotNull RefactoringEventData conflictsData) {
        CodeElement element = CodeElementFactory.createFromPsiElement(conflictsData);
        logger.warn("Conflict detected: '" + refactoringId + "', data: " + element);
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
