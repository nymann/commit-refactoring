package com.github.nymann.commitrefactoring.application;

import com.github.nymann.commitrefactoring.domain.Refactoring;
import com.github.nymann.commitrefactoring.domain.RefactoringHistory;

public final class RefactoringService {
    private final RefactoringHistory history = new RefactoringHistory();
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
        history.add(refactoring);
    }

    public void clearRefactorings() {
        history.clear();
    }

    public void undoLastRefactoring() {
        history.undo();
    }

    public void redoLastRefactoring() {
        history.redo();
    }

    public String getCommitMessage() {
        if (this.textToAppendToCommit.isEmpty()) {
            return getRefactoringCommitMessage();
        }
        return getRefactoringCommitMessage() + this.textToAppendToCommit;
    }

    private String getRefactoringCommitMessage() {
        return history.composeMessage(
                refactoringMessageTemplate::processTemplate,
                () -> defaultMessageTemplate.processTemplate(Refactoring.none()));
    }

    public void setTextToAppendToCommit(String textToAppendToCommit) {
        this.textToAppendToCommit = textToAppendToCommit;
    }
}
