package com.github.nymann.commitrefactoring.intellij;

import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.RefactoringService;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.diagnostic.Logger;

@Service(Service.Level.PROJECT)
public final class IntelliJRefactoringService {
    private static final Logger log = Logger.getInstance(IntelliJRefactoringService.class);

    private final RefactoringService refactoringService;

    public IntelliJRefactoringService() {
        this.refactoringService = new RefactoringService();
    }

    public void addRefactoring(Refactoring refactoring) {
        this.refactoringService.addRefactoring(refactoring);
    }

    public void clearRefactorings() {
        this.refactoringService.clearRefactorings();
    }

    public void undoLastRefactoring() {
        this.refactoringService.undoLastRefactoring();
    }

    public void redoLastRefactoring() {
        this.refactoringService.redoLastRefactoring();
    }

    public String getCommitMessage() {
        return this.refactoringService.getCommitMessage();
    }
}
