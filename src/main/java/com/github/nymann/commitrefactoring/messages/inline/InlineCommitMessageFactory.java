package com.github.nymann.commitrefactoring.messages.inline;

import com.github.nymann.commitrefactoring.CodeElement;
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
            return new InlineMethodCommitMessage(psiMethod.getName());
        }
        if (before instanceof PsiLocalVariable psiLocalVariable) {
            return new InlineVariableCommitMessage(psiLocalVariable.getName());
        }
        return new DefaultCommitMessage(refactoring);
    }

    public static CommitMessage create(CodeElement before) {
        return switch (before.getType()) {
            case METHOD -> new InlineMethodCommitMessage(before.getName());
            case LOCAL_VARIABLE -> new InlineVariableCommitMessage(before.getName());
            default -> throw new RuntimeException("TODO");
        };
    }

}
