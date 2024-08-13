package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiParameter;

public class RenameParameterCommitMessage implements CommitMessage {

    private final PsiParameter before;
    private final PsiParameter after;

    public RenameParameterCommitMessage(PsiParameter before, PsiParameter after) {
        this.before = before;
        this.after = after;
    }

    @Override
    public String getMessage() {
        return "Rename parameter '" + before.getName() + "' to '" + after.getName() + "'";
    }
}
