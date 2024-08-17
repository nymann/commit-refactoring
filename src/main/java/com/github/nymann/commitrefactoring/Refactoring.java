package com.github.nymann.commitrefactoring;

public record Refactoring(RefactoringType refactoringType, CodeElement before, CodeElement after) {
    @Override
    public String toString() {
        return "Refactoring{" + "refactoringType='" + refactoringType.name() + "', before=" + before.toString() + ", after=" + after.toString() + "}";
    }
}
