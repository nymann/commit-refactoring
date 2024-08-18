package com.github.nymann.commitrefactoring;

public class DefaultMakeStaticCommitMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Make static";
    }
}
