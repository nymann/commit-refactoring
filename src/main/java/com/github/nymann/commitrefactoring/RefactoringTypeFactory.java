package com.github.nymann.commitrefactoring;

public class RefactoringTypeFactory {
    public static RefactoringType fromIntellij(String refactoringId) {
        return switch (refactoringId) {
            case "refactoring.inline.method", "refactoring.inline.local.variable", "refactoring.inline.class",
                 "refactoring.inline.parameter" -> RefactoringType.INLINE;
            case "refactoring.extract.method", "refactoring.extractVariable" ->
                    RefactoringType.EXTRACT;
            case "refactoring.inplace.rename", "refactoring.rename" ->
                    RefactoringType.RENAME;
            case "refactoring.safeDelete" -> RefactoringType.SAFE_DELETE;
            case "refactoring.changeSignature" -> RefactoringType.CHANGE_SIGNATURE;
            case "refactoring.move" -> RefactoringType.MOVE;
            default -> RefactoringType.None;
        };
    }
}
