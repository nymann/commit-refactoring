package com.github.nymann.commitrefactoring;

import com.github.nymann.commitrefactoring.messages.DefaultChangeSignatureCommitMessage;
import org.jetbrains.annotations.NotNull;

public class ChangeSignatureCommitMessageFactory {
    public static @NotNull CommitMessage create(Refactoring refactoring) {
        CodeElement before = refactoring.before();
        if (CodeElementType.METHOD.equals(before.type())) {
            return new ChangeMethodSignatureCommitMessage(before.name());
        }
        return new DefaultChangeSignatureCommitMessage();
    }
}
