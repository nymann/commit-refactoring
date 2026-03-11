package com.github.nymann.commitrefactoring.application;

import com.github.nymann.commitrefactoring.application.port.TemplateVariableProvider;
import com.github.nymann.commitrefactoring.domain.Refactoring;

public class RefactoringProvider implements TemplateVariableProvider {
    @Override
    public String getVariableName() {
        return "refactoring";
    }

    @Override
    public String resolve(Refactoring refactoring) {
        return refactoring
                .toCommitMessage()
                .getMessage();
    }
}
