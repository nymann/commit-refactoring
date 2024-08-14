package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.messages.DefaultCommitMessage;
import com.intellij.psi.*;

public class SafeDeleteCommitMessageFactory {
    public static CommitMessage create(Refactoring refactoring) {
        PsiElement deletedElement = refactoring.getFirstBefore();
        if (deletedElement instanceof PsiField psiField) {
            return new SafeDeleteFieldCommitMessage(psiField);
        }
        if (deletedElement instanceof PsiClass psiClass) {
            return new SafeDeleteClassCommitMessage(psiClass);
        }
        if (deletedElement instanceof PsiMethod psiMethod) {
            return new SafeDeleteMethodCommitMessage(psiMethod);
        }
        if (deletedElement instanceof PsiParameter psiParameter) {
            return new SafeDeleteParameterCommitMessage(psiParameter);
        }
        return new DefaultCommitMessage(refactoring);
    }
}
