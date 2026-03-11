package com.github.nymann.commitrefactoring.domain.messages.changesignature;

import com.github.nymann.commitrefactoring.domain.CodeElement;
import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.github.nymann.commitrefactoring.domain.CommitMessage;

public class ChangeSignatureCommitMessageFactory {
    public static CommitMessage create(CodeElement before) {
        if (CodeElementType.METHOD.equals(before.type())) {
            return new ChangeMethodSignatureCommitMessage(before.name());
        }
        if (CodeElementType.CLASS.equals(before.type())) {
            return new ChangeClassSignatureCommitMessage(before.name());
        }
        return new DefaultChangeSignatureCommitMessage();
    }
}
