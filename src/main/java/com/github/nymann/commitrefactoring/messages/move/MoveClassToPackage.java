package com.github.nymann.commitrefactoring.messages.move;

import com.github.nymann.commitrefactoring.CommitMessage;

public class MoveClassToPackage implements CommitMessage {
    private final String className;
    private final String packageName;

    public MoveClassToPackage(String className, String packageName) {
        this.className = className;
        this.packageName = packageName;
    }

    @Override
    public String getMessage() {
        return "Move class '" + className + "' to '" + packageName + "'";
    }
}
