package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.intellij.openapi.diagnostic.Logger;

public class DefaultCommitMessage implements CommitMessage {
    private static final Logger log = Logger.getInstance(DefaultCommitMessage.class);
    private final Refactoring refactoring;

    public DefaultCommitMessage(Refactoring refactoring) {
        this.refactoring = refactoring;
    }

    @Override
    public String getMessage() {
        log.warn(refactoring.toString());
        return refactoring.refactoringType().toString();
    }

}
