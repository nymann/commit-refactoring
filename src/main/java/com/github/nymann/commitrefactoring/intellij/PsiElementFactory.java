package com.github.nymann.commitrefactoring.intellij;

import com.intellij.psi.PsiElement;
import com.intellij.refactoring.listeners.RefactoringEventData;

public class PsiElementFactory {
    public static PsiElement create(RefactoringEventData refactoringEventData) {
        if(refactoringEventData == null) {
            return null;
        }
        PsiElement psiElement = refactoringEventData
                .get()
                .get(RefactoringEventData.PSI_ELEMENT_KEY);
        if (psiElement != null) {
            return psiElement;
        }
        PsiElement[] psiElements = refactoringEventData
                .get()
                .get(RefactoringEventData.PSI_ELEMENT_ARRAY_KEY);
        if (psiElements != null) {
            for (PsiElement element : psiElements) {
                return element;
            }
        }
        return null;
    }
}