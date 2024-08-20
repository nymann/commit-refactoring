package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CommitMessage;

public class InlineFieldCommitMessage implements CommitMessage {
    private final String fieldName;

    public InlineFieldCommitMessage(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String getMessage() {
        return "Inline field '" + fieldName + "'";
    }
}
