package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CommitMessage;

public class InlineClassCommitMessage implements CommitMessage {
    private final String className;

    public InlineClassCommitMessage(String className) {
        this.className = className;
    }

    @Override
    public String getMessage() {
        return "Inline class '" + className + "'";
    }
}
