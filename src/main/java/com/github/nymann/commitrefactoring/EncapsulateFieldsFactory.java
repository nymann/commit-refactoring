package com.github.nymann.commitrefactoring;

import org.jetbrains.annotations.NotNull;

public class EncapsulateFieldsFactory {
    public static @NotNull CommitMessage create(CodeElement before, CodeElement after) {
        if (CodeElementType.FIELD.equals(before.type()) && CodeElementType.METHOD.equals(after.type())) {
            return new EncapsulateFieldsMessage(before.name());
        }

        return new DefaultEncapsulateFieldsMessage();
    }
}
