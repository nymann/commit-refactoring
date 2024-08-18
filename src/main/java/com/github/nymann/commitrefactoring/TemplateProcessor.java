package com.github.nymann.commitrefactoring;

import java.util.List;

public class TemplateProcessor {
    private final List<TemplateVariableProvider> providers;
    private String template;

    public TemplateProcessor() {
        this("${refactoring}", List.of(new RefactoringProvider()));
    }

    public TemplateProcessor(String template, List<TemplateVariableProvider> providers) {
        this.template = template;
        this.providers = providers;
    }

    public String processTemplate(Refactoring refactoring) {
        String result = template;
        for (TemplateVariableProvider provider : providers) {
            String variable = "${" + provider.getVariableName() + "}";
            result = result.replace(variable, provider.resolve(refactoring));
        }
        return result;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
