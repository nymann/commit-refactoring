package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;

public class DefaultCommitMessage implements CommitMessage {
    private final Refactoring refactoring;

    public DefaultCommitMessage(Refactoring refactoring) {
        this.refactoring = refactoring;
    }

    @Override
    public String getMessage() {
        return "Refactoring: " + refactoring.getRefactoringId().replace("refactoring.", "").replace(".", " ");
    }
}
