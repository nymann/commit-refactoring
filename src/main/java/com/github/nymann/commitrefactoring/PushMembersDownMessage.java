package com.github.nymann.commitrefactoring;

public class PushMembersDownMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Push members down";
    }
}
