package com.github.nymann.commitrefactoring.intellij;

import com.github.nymann.commitrefactoring.CodeElement;
import com.github.nymann.commitrefactoring.CodeElementType;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CodeElementFactory {

    public static CodeElement create(PsiElement element) {
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
        return new CodeElement(element
                .getClass()
                .getName(), CodeElementType.UNKNOWN);
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
