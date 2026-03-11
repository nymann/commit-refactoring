package com.github.nymann.commitrefactoring.domain;

import com.github.nymann.commitrefactoring.domain.messages.IntroduceParameterCommitMessage;
import com.github.nymann.commitrefactoring.domain.messages.changesignature.ChangeSignatureCommitMessageFactory;
import com.github.nymann.commitrefactoring.domain.messages.convertinstancemethod.ConvertToInstanceMethodFactory;
import com.github.nymann.commitrefactoring.domain.messages.encapsulatefield.EncapsulateFieldsFactory;
import com.github.nymann.commitrefactoring.domain.messages.extract.ExtractCommitMessageFactory;
import com.github.nymann.commitrefactoring.domain.messages.extractdelegate.ExtractDelegateCommitMessageFactory;
import com.github.nymann.commitrefactoring.domain.messages.inline.InlineCommitMessageFactory;
import com.github.nymann.commitrefactoring.domain.messages.makestatic.MakeStaticFactory;
import com.github.nymann.commitrefactoring.domain.messages.move.MoveCommitMessageFactory;
import com.github.nymann.commitrefactoring.domain.messages.move.MoveMembersCommitMessage;
import com.github.nymann.commitrefactoring.domain.messages.pullmembersup.PullMembersUpFactory;
import com.github.nymann.commitrefactoring.domain.messages.pushmembersdown.PushMembersDownFactory;
import com.github.nymann.commitrefactoring.domain.messages.rename.RenameCommitMessageFactory;
import com.github.nymann.commitrefactoring.domain.messages.safedelete.SafeDeleteCommitMessageFactory;

public class CommitMessageFactory {

    public static CommitMessage create(Refactoring refactoring) {
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
