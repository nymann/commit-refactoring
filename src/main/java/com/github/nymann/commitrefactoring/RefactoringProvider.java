package com.github.nymann.commitrefactoring;

public class RefactoringProvider implements TemplateVariableProvider {
    @Override
    public String getVariableName() {
        return "refactoring";
    }

    @Override
    public String resolve(Refactoring refactoring) {
        return CommitMessageFactory
                .create(refactoring)
                .getMessage();
    }
}
