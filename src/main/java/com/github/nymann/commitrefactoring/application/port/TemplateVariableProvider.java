package com.github.nymann.commitrefactoring.application.port;

import com.github.nymann.commitrefactoring.domain.Refactoring;

public interface TemplateVariableProvider {
    String getVariableName();

    String resolve(Refactoring refactoring);
}
