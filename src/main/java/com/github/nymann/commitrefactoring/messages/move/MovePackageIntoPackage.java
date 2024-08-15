package com.github.nymann.commitrefactoring.messages.move;

import com.github.nymann.commitrefactoring.CommitMessage;

public class MovePackageIntoPackage implements CommitMessage {
    private final String movePackage;
    private final String destinationPackage;

    public MovePackageIntoPackage(String movePackage, String destinationPackage) {
        this.movePackage = movePackage;
        this.destinationPackage = destinationPackage;
    }

    @Override
    public String getMessage() {
        return "Move package '" + movePackage + "' into '" + destinationPackage + "'";
    }
}
