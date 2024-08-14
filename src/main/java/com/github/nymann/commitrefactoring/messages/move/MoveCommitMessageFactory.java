package com.github.nymann.commitrefactoring.messages.move;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;

public class MoveCommitMessageFactory {
    public static CommitMessage create(CodeElement from, CodeElement to) {
        if (CodeElementType.CLASS.equals(from.getType()) && CodeElementType.PACKAGE.equals(to.getType())) {
            return new MoveClassToPackage(from.getName(), to.getName());
        }
        return new DefaultMoveCommitMessage(from.getType(), to.getType());
    }

}
