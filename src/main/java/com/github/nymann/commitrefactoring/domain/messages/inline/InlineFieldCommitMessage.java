package com.github.nymann.commitrefactoring.domain.messages.inline;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

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
