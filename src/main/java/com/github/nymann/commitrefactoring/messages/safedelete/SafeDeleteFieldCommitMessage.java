package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiField;

public class SafeDeleteFieldCommitMessage implements CommitMessage {
    private final PsiField deletedField;

    public SafeDeleteFieldCommitMessage(PsiField deletedField) {
        this.deletedField = deletedField;
    }

    @Override
    public String getMessage() {
        return "Remove unused field '" + deletedField.getName() + "'";
    }
}
