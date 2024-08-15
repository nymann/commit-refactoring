package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CommitMessage;

public class InlineConstructorCommitMessage implements CommitMessage {
    private final String className;

    public InlineConstructorCommitMessage(String className) {
        this.className = className;
    }

    @Override
    public String getMessage() {
        return "Inline constructor in '" + className + "'";
    }
}
