package com.github.nymann.commitrefactoring.application;

import com.github.nymann.commitrefactoring.domain.CodeElement;
import com.github.nymann.commitrefactoring.domain.Refactoring;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.nymann.commitrefactoring.domain.CodeElementType.UNKNOWN;
import static com.github.nymann.commitrefactoring.domain.RefactoringType.NO_REFACTORING;

public final class RefactoringService {
    private final List<Refactoring> refactorings = new ArrayList<>();
    private final Deque<Refactoring> undoStack = new ArrayDeque<>();
    private final Deque<Refactoring> redoStack = new ArrayDeque<>();
    private final TemplateProcessor refactoringMessageTemplate;
    private final TemplateProcessor defaultMessageTemplate;
    private String textToAppendToCommit;

    public RefactoringService(TemplateProcessor refactoringMessageTemplate, TemplateProcessor defaultMessageTemplate, String textToAppendToCommit) {
        this.refactoringMessageTemplate = refactoringMessageTemplate;
        this.defaultMessageTemplate = defaultMessageTemplate;
        this.textToAppendToCommit = textToAppendToCommit;
    }

    public RefactoringService(TemplateProcessor refactoringMessageTemplate, TemplateProcessor defaultMessageTemplate) {
        this.refactoringMessageTemplate = refactoringMessageTemplate;
        this.defaultMessageTemplate = defaultMessageTemplate;
        this.textToAppendToCommit = "";
    }

    public RefactoringService() {
        refactoringMessageTemplate = new TemplateProcessor();
        defaultMessageTemplate = new TemplateProcessor();
        textToAppendToCommit = "";
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
        if (this.textToAppendToCommit.isEmpty()) {
            return getRefactoringCommitMessage();
        }
        return getRefactoringCommitMessage() + this.textToAppendToCommit;
    }

    private String getRefactoringCommitMessage() {
        String message = refactorings
                .stream()
                .map(refactoringMessageTemplate::processTemplate)
                .collect(Collectors.joining("\n"));

        if (message.isEmpty()) {
            return defaultMessageTemplate.processTemplate(new Refactoring(
                    NO_REFACTORING,
                    new CodeElement("N/A", UNKNOWN),
                    new CodeElement("N/A", UNKNOWN)));
        }
        return message;
    }

    public void setTextToAppendToCommit(String textToAppendToCommit) {
        this.textToAppendToCommit = textToAppendToCommit;
    }
}
