package com.github.nymann.commitrefactoring.domain.messages.encapsulatefield;

import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class DefaultEncapsulateFieldsMessage implements CommitMessage {
    @Override
    public String getMessage() {
        return "Encapsulate fields";
    }
}
