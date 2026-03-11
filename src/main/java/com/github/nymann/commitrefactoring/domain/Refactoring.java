package com.github.nymann.commitrefactoring.domain;

import static com.github.nymann.commitrefactoring.domain.CodeElementType.UNKNOWN;
import static com.github.nymann.commitrefactoring.domain.RefactoringType.NO_REFACTORING;

public record Refactoring(RefactoringType refactoringType, CodeElement before, CodeElement after) {

    public static Refactoring none() {
        return new Refactoring(NO_REFACTORING, new CodeElement("N/A", UNKNOWN), new CodeElement("N/A", UNKNOWN));
    }

    public boolean isSupported() {
        return refactoringType != RefactoringType.UNKNOWN;
    }

    public CommitMessage toCommitMessage() {
        return refactoringType.createCommitMessage(before, after);
    }

    @Override
    public String toString() {
        return "Refactoring{" + "refactoringType='" + refactoringType.name() + "', before=" + before.toString() + ", after=" + after.toString() + "}";
    }
}
