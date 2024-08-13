package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiLocalVariable;

public class RenameVariableCommitMessage implements CommitMessage {

    private final PsiLocalVariable before;
    private final PsiLocalVariable after;

    public RenameVariableCommitMessage(PsiLocalVariable before, PsiLocalVariable after) {
        this.before = before;
        this.after = after;
    }

    @Override
    public String getMessage() {
        return "Rename variable '" + before.getName() + "' to '" + after.getName() + "'";
    }
}
