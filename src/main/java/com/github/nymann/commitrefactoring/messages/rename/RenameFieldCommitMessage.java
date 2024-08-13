package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiField;

public class RenameFieldCommitMessage implements CommitMessage {
    private final PsiField before;
    private final PsiField after;

    public RenameFieldCommitMessage(PsiField before, PsiField after) {
        this.before = before;
        this.after = after;
    }

    @Override
    public String getMessage() {
        return "Rename field '" + before.getName() + "' to '" + after.getName() + "'";
    }
}
