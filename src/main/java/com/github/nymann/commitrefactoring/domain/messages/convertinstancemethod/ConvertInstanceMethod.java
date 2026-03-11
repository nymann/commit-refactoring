package com.github.nymann.commitrefactoring.domain.messages.convertinstancemethod;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class ConvertInstanceMethod implements CommitMessage {
    private final String methodName;

    public ConvertInstanceMethod(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String getMessage() {
        return "Convert '" + methodName + "' to instance method";
    }
}
