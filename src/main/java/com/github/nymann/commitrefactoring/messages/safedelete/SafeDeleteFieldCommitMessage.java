package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CommitMessage;

public class SafeDeleteFieldCommitMessage implements CommitMessage {
    private final String fieldName;

    public SafeDeleteFieldCommitMessage(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String getMessage() {
        return "Remove unused field '" + fieldName + "'";
    }
}
