package com.github.nymann.commitrefactoring;

import com.intellij.psi.PsiElement;

public class UnexpectedPsiElementType extends RuntimeException {
    public UnexpectedPsiElementType(PsiElement data, Class<? extends PsiElement> expected, String name) {
        super(name + " was not an instance of " + expected.getName() + " it was: " + data.getClass().getName());
    }
}
