package com.github.nymann.commitrefactoring.adapter.in.intellij;

import com.github.nymann.commitrefactoring.domain.RefactoringType;
import com.intellij.openapi.extensions.ExtensionPointName;
import org.jetbrains.annotations.NotNull;

public class RefactoringTypeFactory {
    private static final ExtensionPointName<RefactoringTypeProvider> EP_NAME =
            new ExtensionPointName<>("com.github.nymann.commitrefactoring.refactoringTypeProvider");

    public static @NotNull RefactoringType fromIntellij(@NotNull String refactoringId) {
        for (RefactoringTypeProvider provider : EP_NAME.getExtensions()) {
            RefactoringType type = provider.resolve(refactoringId);
            if (type != null) {
                return type;
            }
        }
        return RefactoringType.UNKNOWN;
    }
}
