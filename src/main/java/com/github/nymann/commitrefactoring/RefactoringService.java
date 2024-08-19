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
    private String defaultCommitMessage;

    public RefactoringService(TemplateProcessor templateProcessor, String defaultCommitMessage) {
        this.templateProcessor = templateProcessor;
        this.defaultCommitMessage = defaultCommitMessage;
    }

    public RefactoringService() {
        templateProcessor = new TemplateProcessor();
        defaultCommitMessage = "UNSAFE";
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
        String message = refactorings
                .stream()
                .map(templateProcessor::processTemplate)
                .collect(Collectors.joining("\n"));

        if (message.isEmpty()) {
            return defaultCommitMessage;
        }
        return message;
    }

    public void setDefaultCommitMessage(String defaultCommitMessage) {
        this.defaultCommitMessage = defaultCommitMessage;
    }
}
