package com.github.nymann.commitrefactoring.intellij;

import com.github.nymann.commitrefactoring.RefactoringType;

public class RefactoringTypeFactory {
    public static RefactoringType fromIntellij(String refactoringId) {
        return switch (refactoringId) {
            case "refactoring.inline.method", "refactoring.inline.local.variable", "refactoring.inline.class",
                 "refactoring.inline.parameter" -> RefactoringType.INLINE;
            case "refactoring.extract.method", "refactoring.extractVariable" -> RefactoringType.EXTRACT;
            case "refactoring.inplace.rename", "refactoring.rename" -> RefactoringType.RENAME;
            case "refactoring.safeDelete" -> RefactoringType.SAFE_DELETE;
            case "refactoring.changeSignature", "refactoring.changeClassSignature" -> RefactoringType.CHANGE_SIGNATURE;
            case "refactoring.move" -> RefactoringType.MOVE;
            case "refactoring.introduceParameter" -> RefactoringType.INTRODUCE_PARAMETER;
            case "refactoring.push.down" -> RefactoringType.PUSH_MEMBERS_DOWN;
            default -> RefactoringType.UNKNOWN;
        };
    }
}
