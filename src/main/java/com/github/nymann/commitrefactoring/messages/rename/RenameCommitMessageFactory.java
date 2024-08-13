package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.messages.DefaultCommitMessage;
import com.intellij.psi.*;

public class RenameCommitMessageFactory {
    public static CommitMessage create(Refactoring refactoring) {
        PsiElement before = refactoring.getFirstBefore();
        PsiElement after = refactoring.getFirstAfter();
        if (before instanceof PsiMethod psiMethod) {
            return new RenameMethodCommitMessage(psiMethod, (PsiMethod) after);
        }
        if (before instanceof PsiClass psiClass) {
            return new RenameClassCommitMessage(psiClass, (PsiClass) after);
        }
        if (before instanceof PsiField psiField) {
            return new RenameFieldCommitMessage(psiField, (PsiField) after);
        }
        if (before instanceof PsiVariable psiVariable) {
            return new RenameVariableCommitMessage(psiVariable, (PsiVariable) after);
        }
        if (before instanceof PsiParameter psiParameter) {
            return new RenameParameterCommitMessage(psiParameter, (PsiParameter) after);
        }
        return new DefaultCommitMessage(refactoring);
    }
}
