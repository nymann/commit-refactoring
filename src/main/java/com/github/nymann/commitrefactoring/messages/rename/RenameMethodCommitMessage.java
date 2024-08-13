package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiMethod;

public class RenameMethodCommitMessage implements CommitMessage {

    private final PsiMethod before;
    private final PsiMethod after;

    public RenameMethodCommitMessage(PsiMethod before, PsiMethod after) {
        this.before = before;
        this.after = after;
    }

    @Override
    public String getMessage() {
        return "Rename method '" + this.before.getName() + "' to '" + this.after.getName() + "'";
    }
}
