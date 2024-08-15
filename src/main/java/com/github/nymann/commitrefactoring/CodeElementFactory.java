package com.github.nymann.commitrefactoring;

import com.intellij.psi.*;
import com.intellij.refactoring.listeners.RefactoringEventData;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.intellij.refactoring.listeners.RefactoringEventData.PSI_ELEMENT_ARRAY_KEY;
import static com.intellij.refactoring.listeners.RefactoringEventData.PSI_ELEMENT_KEY;

public class CodeElementFactory {
    private static PsiElement createPsiElementFromRefactoringEventData(RefactoringEventData before) {
        PsiElement psiElement = before.get().get(PSI_ELEMENT_KEY);
        if (psiElement != null) {
            return psiElement;
        }
        PsiElement[] psiElements = before.get().get(PSI_ELEMENT_ARRAY_KEY);
        if (psiElements != null) {
            for (PsiElement element : psiElements) {
                return element;
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
        }
        if (element instanceof PsiField psiField) {
            return new CodeElement(psiField.getName(), CodeElementType.FIELD);
        }
        if (element instanceof PsiLocalVariable psiLocalVariable) {
            return new CodeElement(psiLocalVariable.getName(), CodeElementType.LOCAL_VARIABLE);
        }
        if (element instanceof PsiMethod psiMethod) {
            return psiMethodFactoryMethod(psiMethod);
        }
        if (element instanceof PsiPackage psiPackage) {
            return new CodeElement(psiPackage.getQualifiedName(), CodeElementType.PACKAGE);
        }
        if (element instanceof PsiParameter psiParameter) {
            return new CodeElement(psiParameter.getName(), CodeElementType.PARAMETER);
        }
        if (element instanceof PsiCodeBlock psiCodeBlock) {
            return psiCodeBlockFactoryMethod(psiCodeBlock);
        }
        return new CodeElement(element.getClass().getName(), CodeElementType.UNKNOWN);
    }

    private static @NotNull CodeElement psiMethodFactoryMethod(PsiMethod psiMethod) {
        if (isConstructor(psiMethod)) {
            return new CodeElement(psiMethod.getName(), CodeElementType.CONSTRUCTOR);
        }
        return new CodeElement(psiMethod.getName(), CodeElementType.METHOD);
    }

    private static @NotNull CodeElement psiCodeBlockFactoryMethod(PsiCodeBlock psiCodeBlock) {
        for (@NotNull PsiElement child : psiCodeBlock.getChildren()) {
            if (child instanceof PsiDeclarationStatement declarationStatement) {
                PsiElement[] declaredElements = declarationStatement.getDeclaredElements();
                if (declaredElements.length == 1) {
                    PsiElement declaredElement = declaredElements[0];
                    if (declaredElement instanceof PsiLocalVariable localVariable) {
                        return new CodeElement(localVariable.getName(), CodeElementType.LOCAL_VARIABLE);
                    }
                }
            }
        }
        return new CodeElement(psiCodeBlock.getText(), CodeElementType.CODE_BLOCK);
    }

    private static boolean isConstructor(PsiMethod psiMethod) {
        PsiClass containingClass = psiMethod.getContainingClass();
        if (containingClass == null) {
            return false;
        }
        return Objects.equals(containingClass.getName(), psiMethod.getName());
    }
}
