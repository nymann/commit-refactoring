package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.messages.DefaultCommitMessage;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;

public class InlineCommitMessageFactory {
    public static CommitMessage create(Refactoring refactoring) {
        PsiElement before = refactoring.getFirstBefore();
        if (before instanceof PsiMethod psiMethod) {
            return new InlineMethodCommitMessage(psiMethod);
        }
        return new DefaultCommitMessage(refactoring);
    }

}
