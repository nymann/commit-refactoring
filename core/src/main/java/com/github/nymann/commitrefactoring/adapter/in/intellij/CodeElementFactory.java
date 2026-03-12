package com.github.nymann.commitrefactoring.adapter.in.intellij;

import com.github.nymann.commitrefactoring.domain.CodeElement;
import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.psi.PsiElement;
import com.intellij.refactoring.listeners.RefactoringEventData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CodeElementFactory {
    private static final Logger logger = Logger.getInstance(CodeElementFactory.class);
    private static final ExtensionPointName<CodeElementProvider> EP_NAME =
            new ExtensionPointName<>("com.github.nymann.commitrefactoring.codeElementProvider");

    public static CodeElement create(@Nullable RefactoringEventData eventData) {
        if (eventData == null) {
            return new CodeElement("UNKNOWN", CodeElementType.UNKNOWN);
        }
        CodeElement lastResult = null;
        for (CodeElementProvider provider : EP_NAME.getExtensions()) {
            CodeElement result = provider.create(eventData);
            if (result.type() != CodeElementType.UNKNOWN) {
                return result;
            }
            lastResult = result;
        }
        if (lastResult != null) {
            return lastResult;
        }
        logger.warn("No CodeElementProviders registered");
        return new CodeElement("UNKNOWN", CodeElementType.UNKNOWN);
    }

    public static CodeElement createFromPsiElement(@NotNull PsiElement element) {
        RefactoringEventData eventData = new RefactoringEventData();
        eventData.addElement(element);
        return create(eventData);
    }
}
