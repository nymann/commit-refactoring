package com.github.nymann.commitrefactoring.messages.makestatic;

import com.github.nymann.commitrefactoring.CommitMessage;

public class MakeMethodStaticCommitMessage implements CommitMessage {
    private final String methodName;

    public MakeMethodStaticCommitMessage(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String getMessage() {
        return "Make method '" + methodName + "' static";
    }
}
