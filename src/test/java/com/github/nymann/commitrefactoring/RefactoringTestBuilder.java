package com.github.nymann.commitrefactoring;

public class RefactoringTestBuilder {
    private RefactoringType refactoringType = RefactoringType.UNKNOWN;
    private String beforeName = null;
    private String afterName = null;
    private CodeElementType beforeType = CodeElementType.UNKNOWN;
    private CodeElementType afterType = CodeElementType.UNKNOWN;

    public static RefactoringTestBuilder builder() {
        return new RefactoringTestBuilder();
    }

    public RefactoringTestBuilder refactoringType(RefactoringType refactoringType) {
        this.refactoringType = refactoringType;
        return this;
    }

    public RefactoringTestBuilder beforeName(String beforeName) {
        this.beforeName = beforeName;
        return this;
    }

    public RefactoringTestBuilder afterName(String afterName) {
        this.afterName = afterName;
        return this;
    }

    public RefactoringTestBuilder beforeType(CodeElementType beforeType) {
        this.beforeType = beforeType;
        return this;
    }

    public RefactoringTestBuilder afterType(CodeElementType afterType) {
        this.afterType = afterType;
        return this;
    }

    public RefactoringTestBuilder before(CodeElement before) {
        this.beforeType = before.type();
        this.beforeName = before.name();
        return this;
    }

    public RefactoringTestBuilder after(CodeElement after) {
        this.afterType = after.type();
        this.afterName = after.name();
        return this;
    }

    public Refactoring build() {
        return new Refactoring(
                refactoringType,
                new CodeElement(beforeName, beforeType),
                new CodeElement(afterName, afterType)
        );
    }
}
