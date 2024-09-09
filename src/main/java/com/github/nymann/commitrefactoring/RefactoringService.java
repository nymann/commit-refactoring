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
    private final TemplateProcessor refactoringMessageTemplate;
    private String defaultCommitMessage;

    public RefactoringService(TemplateProcessor refactoringMessageTemplate, String defaultCommitMessage) {
        this.refactoringMessageTemplate = refactoringMessageTemplate;
        this.defaultCommitMessage = defaultCommitMessage;
    }

    public RefactoringService() {
        refactoringMessageTemplate = new TemplateProcessor();
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
                .map(refactoringMessageTemplate::processTemplate)
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
