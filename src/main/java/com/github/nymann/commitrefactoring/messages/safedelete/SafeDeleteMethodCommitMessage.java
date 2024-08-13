package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiMethod;

public class SafeDeleteMethodCommitMessage implements CommitMessage {
    private final PsiMethod deletedMethod;

    public SafeDeleteMethodCommitMessage(PsiMethod deletedField) {
        this.deletedMethod = deletedField;
    }

    @Override
    public String getMessage() {
        return "Remove unused method '" + deletedMethod.getName() + "'";
    }
}
