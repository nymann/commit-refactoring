package com.github.nymann.commitrefactoring.messages.extract;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CommitMessage;
import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.messages.DefaultCommitMessage;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLocalVariable;
import com.intellij.psi.PsiMethod;

public class ExtractCommitMessageFactory {
    public static CommitMessage create(Refactoring refactoring) {
        PsiElement after = refactoring.getFirstAfter();
        if (after instanceof PsiMethod psiMethod) {
            return new ExtractMethodCommitMessage(psiMethod.getName());
        }
        if (after instanceof PsiLocalVariable psiLocalVariable) {
            return new ExtractVariableCommitMessage(psiLocalVariable.getName());
        }
        return new DefaultCommitMessage(refactoring);
    }

    public static CommitMessage create(CodeElement codeElement) {
        return switch (codeElement.getType()) {
            case LOCAL_VARIABLE -> new ExtractVariableCommitMessage(codeElement.getName());
            case METHOD -> new ExtractMethodCommitMessage(codeElement.getName());
            default -> new DefaultExtractCommitMessage(codeElement.getType());
        };
    }
}
