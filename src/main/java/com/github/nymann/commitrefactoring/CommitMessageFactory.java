package com.github.nymann.commitrefactoring;

import com.github.nymann.commitrefactoring.messages.ChangeSignatureCommitMessage;
import com.github.nymann.commitrefactoring.messages.DefaultCommitMessage;
import com.github.nymann.commitrefactoring.messages.extract.ExtractCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.inline.InlineCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.rename.RenameCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.safedelete.SafeDeleteCommitMessageFactory;

public class CommitMessageFactory {
    public static CommitMessage create(Refactoring refactoring) {
        return switch (refactoring.getRefactoringId()) {
            case "refactoring.inline.method", "refactoring.inline.local.variable" ->
                    InlineCommitMessageFactory.create(refactoring);
            case "refactoring.extract.method", "refactoring.extractVariable" ->
                    ExtractCommitMessageFactory.create(refactoring);
            case "refactoring.inplace.rename", "refactoring.rename" -> RenameCommitMessageFactory.create(refactoring);
            case "refactoring.safeDelete" -> SafeDeleteCommitMessageFactory.create(refactoring);
            case "refactoring.changeSignature" -> new ChangeSignatureCommitMessage();
            case "refactoring.move" -> MoveCommitMessageFactory.create(refactoring);
            default -> new DefaultCommitMessage(refactoring);
        };
    }
}
