package com.github.nymann.commitrefactoring;

public record Refactoring(String refactoringId, CodeElement before, CodeElement after) {
}
