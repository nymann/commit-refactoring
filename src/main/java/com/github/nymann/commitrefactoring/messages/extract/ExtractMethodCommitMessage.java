package com.github.nymann.commitrefactoring.messages.extract;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.intellij.psi.PsiMethod;

public class ExtractMethodCommitMessage implements CommitMessage {

    private final PsiMethod after;

    public ExtractMethodCommitMessage(PsiMethod after) {
        this.after = after;
    }

    @Override
    public String getMessage() {
        return "Extract method to '" + after.getName() + "'";
    }
}
