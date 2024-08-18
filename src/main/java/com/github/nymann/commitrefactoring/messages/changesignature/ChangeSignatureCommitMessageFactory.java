package com.github.nymann.commitrefactoring.messages.changesignature;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import org.jetbrains.annotations.NotNull;

public class ChangeSignatureCommitMessageFactory {
    public static @NotNull CommitMessage create(Refactoring refactoring) {
        CodeElement before = refactoring.before();
        if (CodeElementType.METHOD.equals(before.type())) {
            return new ChangeMethodSignatureCommitMessage(before.name());
        }
        if(CodeElementType.CLASS.equals(before.type())) {
            return new ChangeClassSignatureCommitMessage(before.name());
        }
        return new DefaultChangeSignatureCommitMessage();
    }
}
