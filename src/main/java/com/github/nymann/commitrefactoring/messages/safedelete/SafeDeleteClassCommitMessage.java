package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiClass;

public class SafeDeleteClassCommitMessage implements CommitMessage {
    private final PsiClass deletedClass;

    public SafeDeleteClassCommitMessage(PsiClass deletedField) {
        this.deletedClass = deletedField;
    }

    @Override
    public String getMessage() {
        return "Remove unused class '" + deletedClass.getName() + "'";
    }
}
