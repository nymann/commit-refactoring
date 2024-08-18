package com.github.nymann.commitrefactoring.messages.makestatic;

import com.github.nymann.commitrefactoring.CommitMessage;

public class DefaultMakeStaticCommitMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Make static";
    }
}
