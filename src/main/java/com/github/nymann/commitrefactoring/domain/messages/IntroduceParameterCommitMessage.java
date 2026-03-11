package com.github.nymann.commitrefactoring.domain.messages;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class IntroduceParameterCommitMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Introduce parameter";
    }
}
