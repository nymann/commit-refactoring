package com.github.nymann.commitrefactoring;

import com.github.nymann.commitrefactoring.messages.IntroduceParameterCommitMessage;
import com.github.nymann.commitrefactoring.messages.changesignature.ChangeSignatureCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.convertinstancemethod.ConvertToInstanceMethodFactory;
import com.github.nymann.commitrefactoring.messages.encapsulatefield.EncapsulateFieldsFactory;
import com.github.nymann.commitrefactoring.messages.extract.ExtractCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.extractdelegate.ExtractDelegateCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.inline.InlineCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.makestatic.MakeStaticFactory;
import com.github.nymann.commitrefactoring.messages.move.MoveCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.move.MoveMembersCommitMessage;
import com.github.nymann.commitrefactoring.messages.pullmembersup.PullMembersUpFactory;
import com.github.nymann.commitrefactoring.messages.pushmembersdown.PushMembersDownFactory;
import com.github.nymann.commitrefactoring.messages.rename.RenameCommitMessageFactory;
import com.github.nymann.commitrefactoring.messages.safedelete.SafeDeleteCommitMessageFactory;
import org.jetbrains.annotations.NotNull;

public class CommitMessageFactory {

    public static @NotNull CommitMessage create(Refactoring refactoring) {
        return switch (refactoring.refactoringType()) {
            case CHANGE_SIGNATURE -> ChangeSignatureCommitMessageFactory.create(refactoring);
            case EXTRACT -> ExtractCommitMessageFactory.create(refactoring.after());
            case INLINE -> InlineCommitMessageFactory.create(refactoring.before());
            case MOVE -> MoveCommitMessageFactory.create(refactoring.before(), refactoring.after());
            case RENAME -> RenameCommitMessageFactory.create(refactoring.before(), refactoring.after());
            case SAFE_DELETE -> SafeDeleteCommitMessageFactory.create(refactoring.before());
            case INTRODUCE_PARAMETER -> new IntroduceParameterCommitMessage();
            case PUSH_MEMBERS_DOWN -> PushMembersDownFactory.create(refactoring.before(), refactoring.after());
            case MAKE_STATIC -> MakeStaticFactory.create(refactoring.before());
            case EXTRACT_DELEGATE ->
                    ExtractDelegateCommitMessageFactory.create(refactoring.before(), refactoring.after());
            case ENCAPSULATE_FIELDS -> EncapsulateFieldsFactory.create(refactoring.before(), refactoring.after());
            case CONVERT_INSTANCE_METHOD ->
                    ConvertToInstanceMethodFactory.create(refactoring.before(), refactoring.after());
            case PULL_MEMBERS_UP -> PullMembersUpFactory.create(refactoring.after());
            case MOVE_MEMBERS -> new MoveMembersCommitMessage();
            case NO_REFACTORING -> () -> "";
            case UNKNOWN -> throw new RuntimeException(refactoring.toString());
        };
    }
}
