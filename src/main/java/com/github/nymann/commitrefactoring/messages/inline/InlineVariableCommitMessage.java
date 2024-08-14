package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.mock.MockPsiManager;
import com.intellij.psi.PsiLocalVariable;

public class InlineVariableCommitMessage implements CommitMessage {
    private final CodeElement variable;

    public InlineVariableCommitMessage(CodeElement before) {
        this.variable = before;
    }

    @Override
    public String getMessage() {
        return "Inline variable '" + variable.getName() + "'";
    }
}
