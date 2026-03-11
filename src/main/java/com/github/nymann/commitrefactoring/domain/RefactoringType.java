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

import java.util.function.BiFunction;

public enum RefactoringType {
    CHANGE_SIGNATURE((before, after) -> ChangeSignatureCommitMessageFactory.create(before)),
    EXTRACT((before, after) -> ExtractCommitMessageFactory.create(after)),
    INLINE((before, after) -> InlineCommitMessageFactory.create(before)),
    INTRODUCE_PARAMETER((before, after) -> new IntroduceParameterCommitMessage()),
    MOVE(MoveCommitMessageFactory::create),
    RENAME(RenameCommitMessageFactory::create),
    SAFE_DELETE((before, after) -> SafeDeleteCommitMessageFactory.create(before)),
    PUSH_MEMBERS_DOWN(PushMembersDownFactory::create),
    MAKE_STATIC((before, after) -> MakeStaticFactory.create(before)),
    EXTRACT_DELEGATE(ExtractDelegateCommitMessageFactory::create),
    ENCAPSULATE_FIELDS(EncapsulateFieldsFactory::create),
    CONVERT_INSTANCE_METHOD(ConvertToInstanceMethodFactory::create),
    MOVE_MEMBERS((before, after) -> new MoveMembersCommitMessage()),
    PULL_MEMBERS_UP((before, after) -> PullMembersUpFactory.create(after)),
    NO_REFACTORING((before, after) -> () -> ""),
    UNKNOWN(null);

    private final BiFunction<CodeElement, CodeElement, CommitMessage> commitMessageFactory;

    RefactoringType(BiFunction<CodeElement, CodeElement, CommitMessage> commitMessageFactory) {
        this.commitMessageFactory = commitMessageFactory;
    }

    public CommitMessage createCommitMessage(CodeElement before, CodeElement after) {
        if (this == UNKNOWN) {
            throw new UnsupportedOperationException("Cannot create commit message for UNKNOWN refactoring type");
        }
        return commitMessageFactory.apply(before, after);
    }
}
