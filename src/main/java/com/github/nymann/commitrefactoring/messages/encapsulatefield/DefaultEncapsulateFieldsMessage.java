package com.github.nymann.commitrefactoring.messages.encapsulatefield;

import com.github.nymann.commitrefactoring.CommitMessage;

public class DefaultEncapsulateFieldsMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Encapsulate fields";
    }
}
