package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.CommitMessage;

public class IntroduceParameterCommitMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Introduce parameter";
    }
}
