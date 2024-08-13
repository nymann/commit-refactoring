package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.messages.DefaultCommitMessage;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;

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
        return new DefaultCommitMessage(refactoring);
    }
}
