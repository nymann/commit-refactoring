package com.github.nymann.commitrefactoring.messages.safedelete;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.messages.DefaultCommitMessage;
import com.github.nymann.commitrefactoring.messages.inline.DefaultInlineCommitMessage;
import com.intellij.psi.*;

public class SafeDeleteCommitMessageFactory {
    public static CommitMessage create(Refactoring refactoring) {
        PsiElement deletedElement = refactoring.getFirstBefore();
        if (deletedElement instanceof PsiField psiField) {
            return new SafeDeleteFieldCommitMessage(psiField.getName());
        }
        if (deletedElement instanceof PsiClass psiClass) {
            return new SafeDeleteClassCommitMessage(psiClass.getName());
        }
        if (deletedElement instanceof PsiMethod psiMethod) {
            return new SafeDeleteMethodCommitMessage(psiMethod.getName());
        }
        if (deletedElement instanceof PsiParameter psiParameter) {
            return new SafeDeleteParameterCommitMessage(psiParameter.getName());
        }
        return new DefaultCommitMessage(refactoring);
    }

    public static CommitMessage create(CodeElement deletedElement) {
        return switch(deletedElement.getType()) {
            case CLASS -> new SafeDeleteClassCommitMessage(deletedElement.getName());
            case FIELD -> new SafeDeleteFieldCommitMessage(deletedElement.getName());
            case METHOD -> new SafeDeleteMethodCommitMessage(deletedElement.getName());
            case PARAMETER -> new SafeDeleteParameterCommitMessage(deletedElement.getName());
            default -> new DefaultSafeDeleteCommitMessage(deletedElement.getType());
        };
    }
}
