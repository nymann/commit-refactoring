package com.github.nymann.commitrefactoring.domain.messages.encapsulatefield;

import com.github.nymann.commitrefactoring.domain.CodeElement;
import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class EncapsulateFieldsFactory {
    public static CommitMessage create(CodeElement before, CodeElement after) {
        if (CodeElementType.FIELD.equals(before.type()) && CodeElementType.METHOD.equals(after.type())) {
            return new EncapsulateFieldsMessage(before.name());
        }

        return new DefaultEncapsulateFieldsMessage();
    }
}
