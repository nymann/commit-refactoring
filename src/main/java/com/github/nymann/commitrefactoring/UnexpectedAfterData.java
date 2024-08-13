package com.github.nymann.commitrefactoring;

import com.intellij.psi.PsiElement;

public class UnexpectedAfterData extends UnexpectedPsiElementType {
    public UnexpectedAfterData(PsiElement data, Class<? extends PsiElement> expected) {
        super(data, expected, "after");
    }
}
