package com.github.nymann.commitrefactoring.adapter.in.intellij;

import com.github.nymann.commitrefactoring.domain.CodeElement;
import com.github.nymann.commitrefactoring.domain.CodeElementType;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.refactoring.listeners.RefactoringEventData;
import org.jetbrains.annotations.Nullable;

public class CodeElementFactory {
    private static final Logger logger = Logger.getInstance(CodeElementFactory.class);
    private static final ExtensionPointName<CodeElementProvider> EP_NAME =
            new ExtensionPointName<>("com.github.nymann.commitrefactoring.codeElementProvider");

    public static CodeElement create(@Nullable RefactoringEventData eventData) {
        if (eventData == null) {
            return new CodeElement("UNKNOWN", CodeElementType.UNKNOWN);
        }
        logger.info("Number of extensions: " + EP_NAME.getExtensions().length);
        for (CodeElementProvider provider : EP_NAME.getExtensions()) {
            return provider.create(eventData);
        }
        throw new RuntimeException("No providers available");
    }
}
