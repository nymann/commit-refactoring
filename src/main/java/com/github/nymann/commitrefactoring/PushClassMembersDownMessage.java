package com.github.nymann.commitrefactoring;

public class PushClassMembersDownMessage implements CommitMessage {
    private final String fromClass;
    private final String toClass;

    public PushClassMembersDownMessage(String fromClass, String toClass) {
        this.fromClass = fromClass;
        this.toClass = toClass;
    }

    @Override
    public String getMessage() {
        return "Push members down from '" + fromClass + "' to '" + toClass + "'";
    }
}
