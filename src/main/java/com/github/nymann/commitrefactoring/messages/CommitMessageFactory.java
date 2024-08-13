package com.github.nymann.commitrefactoring.messages;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.messages.extract.ExtractCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.inline.InlineCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.rename.RenameCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.safedelete.SafeDeleteCommitMessageFactory;

public class CommitMessageFactory {
    private String yo;
    private void test() {
        yo = ":)";
        System.out.println(yo);
    }
    public static CommitMessage create(Refactoring refactoring) {
        return switch (refactoring.getRefactoringId()) {
            case "refactoring.inline.method", "refactoring.inline.local.variable" ->
                    InlineCommitMessageFactory.create(refactoring);
            case "refactoring.extract.method", "refactoring.extractVariable" ->
                    ExtractCommitMessageFactory.create(refactoring);
            case "refactoring.inplace.rename", "refactoring.rename" -> RenameCommitMessageFactory.create(refactoring);
            case "refactoring.safeDelete" -> SafeDeleteCommitMessageFactory.create(refactoring);
            case "refactoring.changeSignature" -> new ChangeSignatureCommitMessage();
            default -> new DefaultCommitMessage(refactoring);
        };
    }
}
