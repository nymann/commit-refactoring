package com.github.nymann.commitrefactoring.messages.changesignature;

import com.github.nymann.commitrefactoring.CommitMessage;

public class ChangeClassSignatureCommitMessage implements CommitMessage {
    private final String className;

    public ChangeClassSignatureCommitMessage(String className) {
        this.className = className;
    }

    @Override
    public String getMessage() {
        return "Change class signature of '" + className + "'";
    }
}
