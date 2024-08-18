package com.github.nymann.commitrefactoring;

public interface TemplateVariableProvider {
    String getVariableName();

    String resolve(Refactoring refactoring);
}
