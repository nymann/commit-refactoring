package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiClass;

public class RenameClassCommitMessage implements CommitMessage {

    private final PsiClass before;
    private final PsiClass after;

    public RenameClassCommitMessage(PsiClass before, PsiClass after) {
        this.before = before;
        this.after = after;
    }

    @Override
    public String getMessage() {
        return "Rename class '" + before.getName() + "' to '" + after.getName() + "'";
    }
}
