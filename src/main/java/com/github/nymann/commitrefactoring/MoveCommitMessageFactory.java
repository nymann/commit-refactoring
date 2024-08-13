package com.github.nymann.commitrefactoring;

import com.github.nymann.commitrefactoring.messages.DefaultCommitMessage;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiPackage;

public class MoveCommitMessageFactory {
    public static CommitMessage create(Refactoring refactoring) {
        PsiElement before = refactoring.getFirstBefore();
        PsiElement after = refactoring.getFirstAfter();
        if (before instanceof PsiClass psiClass && after instanceof PsiPackage psiPackage) {
            return new MoveClassToPackage(psiClass, psiPackage);
        }
        return new DefaultCommitMessage(refactoring);
    }
}
