package com.github.nymann.commitrefactoring.intellij;

import com.github.nymann.commitrefactoring.RefactoringType;
import org.jetbrains.annotations.NotNull;

public class RefactoringTypeFactory {
    public static @NotNull RefactoringType fromIntellij(@NotNull String refactoringId) {
        String id = refactoringId.replaceFirst("refactoring.", "");
        return switch (id) {
            case "inline.method", "inline.local.variable", "inline.class",
                 "inline.parameter" -> RefactoringType.INLINE;
            case "extract.method", "extractVariable" -> RefactoringType.EXTRACT;
            case "inplace.rename", "rename" -> RefactoringType.RENAME;
            case "safeDelete" -> RefactoringType.SAFE_DELETE;
            case "changeSignature", "changeClassSignature" -> RefactoringType.CHANGE_SIGNATURE;
            case "move" -> RefactoringType.MOVE;
            case "introduceParameter" -> RefactoringType.INTRODUCE_PARAMETER;
            case "push.down" -> RefactoringType.PUSH_MEMBERS_DOWN;
            case "makeStatic" -> RefactoringType.MAKE_STATIC;
            case "extract.delegate" -> RefactoringType.EXTRACT_DELEGATE;
            default -> RefactoringType.UNKNOWN;
        };
    }
}
