package com.github.nymann.commitrefactoring.exceptions;

import com.intellij.psi.PsiElement;

public class UnexpectedBeforeData extends UnexpectedPsiElementType {
    public UnexpectedBeforeData(PsiElement data, Class<? extends PsiElement> expected) {
        super(data, expected, "before");
    }
}
