package com.github.nymann.commitrefactoring;

import com.github.nymann.commitrefactoring.messages.DefaultCommitMessage;
import com.github.nymann.commitrefactoring.messages.ExtractMethodCommitMessage;
import com.github.nymann.commitrefactoring.messages.InlineMethodCommitMessage;
import com.github.nymann.commitrefactoring.messages.RenameVariableCommitMessage;

public class CommitMessageFactory {
    public static CommitMessage create(Refactoring refactoring) {
        return switch (refactoring.getRefactoringId()) {
            case "refactoring.inline.method" -> new InlineMethodCommitMessage(refactoring);
            case "refactoring.extract.method" -> new ExtractMethodCommitMessage(refactoring);
            case "refactoring.inplace.rename" -> InplaceRenameCommitMessageFactory.create(refactoring);
            case "refactoring.rename" -> new RenameVariableCommitMessage(refactoring);
            default -> new DefaultCommitMessage(refactoring);
        };
    }
}
