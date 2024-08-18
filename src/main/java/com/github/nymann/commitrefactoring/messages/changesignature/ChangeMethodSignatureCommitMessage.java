package com.github.nymann.commitrefactoring.messages.changesignature;

import com.github.nymann.commitrefactoring.CommitMessage;

public class ChangeMethodSignatureCommitMessage implements CommitMessage {
    private final String methodName;

    public ChangeMethodSignatureCommitMessage(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String getMessage() {
        return "Change method signature of '" + methodName + "'";
    }
}
