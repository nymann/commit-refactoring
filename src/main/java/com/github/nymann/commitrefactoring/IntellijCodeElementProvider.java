package com.github.nymann.commitrefactoring;

import com.github.nymann.commitrefactoring.intellij.PsiElementFactory;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.*;
import com.intellij.refactoring.listeners.RefactoringEventData;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static java.util.Optional.ofNullable;

public class IntellijCodeElementProvider implements CodeElementProvider {
    private static final Logger logger = Logger.getInstance(IntellijCodeElementProvider.class);

    public IntellijCodeElementProvider() {

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

    @Override
    public CodeElement create(RefactoringEventData eventData) {
        PsiElement element = PsiElementFactory.create(eventData);
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
        String unsupportedClassName = ofNullable(element)
                .map(Object::getClass)
                .map(Class::getName)
                .orElse("null");
        logger.warn(unsupportedClassName + " is unsupported");
        return new CodeElement(unsupportedClassName, CodeElementType.UNKNOWN);
    }
}
