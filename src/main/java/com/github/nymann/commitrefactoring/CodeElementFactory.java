package com.github.nymann.commitrefactoring;

import com.intellij.psi.*;
import com.intellij.refactoring.listeners.RefactoringEventData;

import static com.intellij.refactoring.listeners.RefactoringEventData.PSI_ELEMENT_ARRAY_KEY;
import static com.intellij.refactoring.listeners.RefactoringEventData.PSI_ELEMENT_KEY;

public class CodeElementFactory {
    private static PsiElement createPsiElementFromRefactoringEventData(RefactoringEventData before) {
        PsiElement psiElement = before.get().get(PSI_ELEMENT_KEY);
        if (psiElement != null) {
            return psiElement.copy();
        }
        PsiElement[] psiElements = before.get().get(PSI_ELEMENT_ARRAY_KEY);
        if (psiElements != null) {
            for (PsiElement element : psiElements) {
                return element.copy();
            }
        }
        return null;
    }

    public static CodeElement createFromPsiElement(RefactoringEventData before) {
        if (before == null) {
            return new CodeElement("UNKNOWN", CodeElementType.UNKNOWN);
        }
        PsiElement element = createPsiElementFromRefactoringEventData(before);
        if (element == null) {
            return new CodeElement("UNKNOWN", CodeElementType.UNKNOWN);
        }
        if (element instanceof PsiClass psiClass) {
            return new CodeElement(psiClass.getName(), CodeElementType.CLASS);
        } else if (element instanceof PsiField psiField) {
            return new CodeElement(psiField.getName(), CodeElementType.FIELD);
        } else if (element instanceof PsiLocalVariable psiLocalVariable) {
            return new CodeElement(psiLocalVariable.getName(), CodeElementType.LOCAL_VARIABLE);
        } else if (element instanceof PsiMethod psiMethod) {
            return new CodeElement(psiMethod.getName(), CodeElementType.METHOD);
        } else if (element instanceof PsiPackage psiPackage) {
            return new CodeElement(psiPackage.getQualifiedName(), CodeElementType.PACKAGE);
        } else if (element instanceof PsiParameter psiParameter) {
            return new CodeElement(psiParameter.getName(), CodeElementType.PARAMETER);
        } else if (element instanceof PsiCodeBlock psiCodeBlock) {
            return new CodeElement(psiCodeBlock.getText(), CodeElementType.CODE_BLOCK);
        }
        return new CodeElement(element.getClass().getName(), CodeElementType.UNKNOWN);
    }
}
