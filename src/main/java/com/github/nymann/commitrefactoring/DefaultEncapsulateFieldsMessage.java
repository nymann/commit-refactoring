package com.github.nymann.commitrefactoring;

public class DefaultEncapsulateFieldsMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Encapsulate fields";
    }
}
