package com.github.nymann.commitrefactoring;

import com.github.nymann.commitrefactoring.messages.DefaultChangeSignatureCommitMessage;
import com.github.nymann.commitrefactoring.messages.IntroduceParameterCommitMessage;
import com.github.nymann.commitrefactoring.messages.extract.ExtractCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.inline.InlineCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.move.MoveCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.rename.RenameCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.safedelete.SafeDeleteCommitMessageFactory;
import org.jetbrains.annotations.NotNull;

public class CommitMessageFactory {

    public static @NotNull CommitMessage create(Refactoring refactoring) {
        return switch (refactoring.refactoringType()) {
            case CHANGE_SIGNATURE -> new DefaultChangeSignatureCommitMessage();
            case EXTRACT -> ExtractCommitMessageFactory.create(refactoring.after());
            case INLINE -> InlineCommitMessageFactory.create(refactoring.before());
            case MOVE -> MoveCommitMessageFactory.create(refactoring.before(), refactoring.after());
            case RENAME -> RenameCommitMessageFactory.create(refactoring.before(), refactoring.after());
            case SAFE_DELETE -> SafeDeleteCommitMessageFactory.create(refactoring.before());
            case INTRODUCE_PARAMETER -> new IntroduceParameterCommitMessage();
            case UNKNOWN -> throw new RuntimeException(refactoring.toString());
        };
    }
}
