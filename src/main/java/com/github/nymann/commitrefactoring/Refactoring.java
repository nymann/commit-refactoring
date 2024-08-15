package com.github.nymann.commitrefactoring;

public record Refactoring(String refactoringId, CodeElement before, CodeElement after) {
    @Override
    public String toString() {
        return "Refactoring{" + "refactoringId='" + refactoringId + "', before=" + before.toString() + ", after=" + after.toString() + "}";
    }
}
