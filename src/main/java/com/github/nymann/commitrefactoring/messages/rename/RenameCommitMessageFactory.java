package com.github.nymann.commitrefactoring.messages.rename;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.messages.DefaultCommitMessage;
import com.intellij.psi.*;

public class RenameCommitMessageFactory {
    public static CommitMessage create(Refactoring refactoring) {
        PsiElement before = refactoring.getFirstBefore();
        PsiElement after = refactoring.getFirstAfter();
        if (before instanceof PsiMethod psiMethod) {
            return new RenameMethodCommitMessage(psiMethod.getName(), ((PsiMethod) after).getName());
        }
        if (before instanceof PsiClass psiClass) {
            return new RenameClassCommitMessage(psiClass.getName(), ((PsiClass) after).getName());
        }
        if (before instanceof PsiField psiField) {
            return new RenameFieldCommitMessage(psiField.getName(), ((PsiField) after).getName());
        }
        if (before instanceof PsiLocalVariable psiVariable) {
            return new RenameVariableCommitMessage(psiVariable.getName(), ((PsiLocalVariable) after).getName());
        }
        if (before instanceof PsiParameter psiParameter) {
            return new RenameParameterCommitMessage(psiParameter.getName(), ((PsiParameter) after).getName());
        }
        return new DefaultCommitMessage(refactoring);
    }

    public static CommitMessage create(CodeElement before, CodeElement after) {
        return switch (before.getType()) {
            case CLASS -> new RenameClassCommitMessage(before.getName(), after.getName());
            case FIELD -> new RenameFieldCommitMessage(before.getName(), after.getName());
            case METHOD -> new RenameMethodCommitMessage(before.getName(), after.getName());
            case PARAMETER -> new RenameParameterCommitMessage(before.getName(), after.getName());
            case LOCAL_VARIABLE -> new RenameVariableCommitMessage(before.getName(), after.getName());
            default -> new DefaultRenameCommitMessage(before.getType());
        };
    }
}
