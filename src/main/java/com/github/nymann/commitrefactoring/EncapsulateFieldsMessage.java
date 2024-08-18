package com.github.nymann.commitrefactoring;

public class EncapsulateFieldsMessage implements CommitMessage {
    private final String fieldName;

    public EncapsulateFieldsMessage(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String getMessage() {
        return "Encapsulate field '" + fieldName + "'";
    }
}
