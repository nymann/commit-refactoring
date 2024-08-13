package com.github.nymann.commitrefactoring.messages.extract;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.messages.DefaultCommitMessage;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiVariable;

public class ExtractCommitMessageFactory {
    public static CommitMessage create(Refactoring refactoring) {
        PsiElement after = refactoring.getFirstAfter();
        if (after instanceof PsiMethod psiMethod) {
            return new ExtractMethodCommitMessage(psiMethod);
        }
        if (after instanceof PsiVariable psiVariable) {
            return new ExtractVariableCommitMessage(psiVariable);
        }
        return new DefaultCommitMessage(refactoring);
    }
}
