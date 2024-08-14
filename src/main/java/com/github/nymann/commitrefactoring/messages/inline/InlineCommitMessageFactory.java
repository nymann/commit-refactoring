package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.messages.DefaultCommitMessage;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLocalVariable;
import com.intellij.psi.PsiMethod;

public class InlineCommitMessageFactory {
    public static CommitMessage create(Refactoring refactoring) {
        PsiElement before = refactoring.getFirstBefore();
        if (before instanceof PsiMethod psiMethod) {
            return new InlineMethodCommitMessage(psiMethod);
        }
        if (before instanceof PsiLocalVariable psiLocalVariable) {
            return new InlineLocalVariableCommitMessage(psiLocalVariable);
        }
        return new DefaultCommitMessage(refactoring);
    }

}
