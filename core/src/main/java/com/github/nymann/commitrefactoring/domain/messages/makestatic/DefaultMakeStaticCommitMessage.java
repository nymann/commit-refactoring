package com.github.nymann.commitrefactoring.domain.messages.makestatic;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class DefaultMakeStaticCommitMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Make static";
    }
}
