package com.github.nymann.commitrefactoring.messages.makestatic;

import com.github.nymann.commitrefactoring.CommitMessage;

public class MakeClassStaticCommitMessage implements CommitMessage {
    private final String className;

    public MakeClassStaticCommitMessage(String className) {
        this.className = className;
    }

    @Override
    public String getMessage() {
        return "Make class '" + className + "' static";
    }
}
