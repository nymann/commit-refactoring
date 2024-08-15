package com.github.nymann.commitrefactoring.messages.move;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.github.nymann.commitrefactoring.CommitMessage;

public class MoveCommitMessageFactory {
    public static CommitMessage create(CodeElement from, CodeElement to) {
        if (CodeElementType.CLASS.equals(from.type()) && CodeElementType.PACKAGE.equals(to.type())) {
            return new MoveClassToPackage(from.name(), to.name());
        }
        return new DefaultMoveCommitMessage(from.type(), to.type());
    }

}
