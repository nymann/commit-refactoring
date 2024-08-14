package com.github.nymann.commitrefactoring.messages.extract;

import com.github.nymann.commitrefactoring.CommitMessage;

public class ExtractMethodCommitMessage implements CommitMessage {

    private final String methodName;

    public ExtractMethodCommitMessage(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String getMessage() {
        return "Extract method '" + methodName + "'";
    }
}
