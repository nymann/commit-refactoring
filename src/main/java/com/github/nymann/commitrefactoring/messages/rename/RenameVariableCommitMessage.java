package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiVariable;

public class RenameVariableCommitMessage implements CommitMessage {

    private final PsiVariable before;
    private final PsiVariable after;

    public RenameVariableCommitMessage(PsiVariable before, PsiVariable after) {
        this.before = before;
        this.after = after;
    }

    @Override
    public String getMessage() {
        return "Rename variable '" + before.getName() + "' to '" + after.getName() + "'";
    }
}
