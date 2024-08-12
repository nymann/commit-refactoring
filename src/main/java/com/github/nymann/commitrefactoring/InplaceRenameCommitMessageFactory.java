package com.github.nymann.commitrefactoring;

import com.github.nymann.commitrefactoring.messages.DefaultCommitMessage;
import com.github.nymann.commitrefactoring.messages.RenameClassCommitMessage;
import com.github.nymann.commitrefactoring.messages.RenameFieldCommitMessage;
import com.github.nymann.commitrefactoring.messages.RenameMethodCommitMessage;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiMethod;

public class InplaceRenameCommitMessageFactory {
    public static CommitMessage create(Refactoring refactoring) {
        PsiElement before = refactoring.getBefore();
        if (before instanceof PsiMethod) {
            return new RenameMethodCommitMessage(refactoring);
        }
        if (before instanceof PsiClass) {
            return new RenameClassCommitMessage(refactoring);
        }
        if (before instanceof PsiField) {
            return new RenameFieldCommitMessage(refactoring);
        }
        return new DefaultCommitMessage(refactoring);
    }
}
