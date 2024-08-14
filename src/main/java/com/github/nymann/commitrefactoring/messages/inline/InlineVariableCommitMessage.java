package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiLocalVariable;

public class InlineVariableCommitMessage implements CommitMessage {
    private final PsiLocalVariable variable;

    public InlineVariableCommitMessage(PsiLocalVariable before) {
        this.variable = before;
    }

    @Override
    public String getMessage() {
        return "Inline variable '" + variable.getName() + "'";
    }
}
