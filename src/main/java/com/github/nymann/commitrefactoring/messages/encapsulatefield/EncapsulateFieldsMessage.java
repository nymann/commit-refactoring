package com.github.nymann.commitrefactoring.messages.encapsulatefield;

import com.github.nymann.commitrefactoring.CommitMessage;

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
