package com.github.nymann.commitrefactoring;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public final class RefactoringService {
    private final List<Refactoring> refactorings = new ArrayList<>();
    private final Deque<Refactoring> undoStack = new ArrayDeque<>();
    private final Deque<Refactoring> redoStack = new ArrayDeque<>();
    private final TemplateProcessor templateProcessor;

    public RefactoringService(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
    }

    public RefactoringService() {
        templateProcessor = new TemplateProcessor();
    }

    public void addRefactoring(Refactoring refactoring) {
        this.refactorings.add(refactoring);
        undoStack.push(refactoring);
        redoStack.clear();
    }

    public void clearRefactorings() {
        refactorings.clear();
    }

    public void undoLastRefactoring() {
        if (undoStack.isEmpty()) {
            return;
        }
        if (refactorings.isEmpty()) {
            return;
        }
        Refactoring lastRefactoring = undoStack.pop();
        refactorings.remove(lastRefactoring);
        redoStack.push(lastRefactoring);
    }

    public void redoLastRefactoring() {
        if (!redoStack.isEmpty()) {
            Refactoring refactoring = redoStack.pop();
            refactorings.add(refactoring);
            undoStack.push(refactoring);
        }
    }

    public String getCommitMessage() {
        return refactorings
                .stream()
                .map(templateProcessor::processTemplate)
                .collect(Collectors.joining("\n"));
    }
}
