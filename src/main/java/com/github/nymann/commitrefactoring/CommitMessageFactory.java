package com.github.nymann.commitrefactoring;

import com.github.nymann.commitrefactoring.messages.DefaultChangeSignatureCommitMessage;
import com.github.nymann.commitrefactoring.messages.DefaultCommitMessage;
import com.github.nymann.commitrefactoring.messages.extract.ExtractCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.inline.InlineCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.move.MoveCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.rename.RenameCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.safedelete.SafeDeleteCommitMessageFactory;

public class CommitMessageFactory {

    public static CommitMessage create(Refactoring refactoring) {
        return switch (refactoring.refactoringId()) {
            case "refactoring.inline.method", "refactoring.inline.local.variable", "refactoring.inline.class",
                 "refactoring.inline.parameter" -> InlineCommitMessageFactory.create(refactoring.before());
            case "refactoring.extract.method", "refactoring.extractVariable" ->
                    ExtractCommitMessageFactory.create(refactoring.after());
            case "refactoring.inplace.rename", "refactoring.rename" ->
                    RenameCommitMessageFactory.create(refactoring.before(), refactoring.after());
            case "refactoring.safeDelete" -> SafeDeleteCommitMessageFactory.create(refactoring.before());
            case "refactoring.changeSignature" -> new DefaultChangeSignatureCommitMessage();
            case "refactoring.move" -> MoveCommitMessageFactory.create(refactoring.before(), refactoring.after());
            default -> new DefaultCommitMessage(refactoring);
        };
    }
}
