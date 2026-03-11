package com.github.nymann.commitrefactoring.adapter.out.intellij.providers;

import com.github.nymann.commitrefactoring.domain.Refactoring;
import com.intellij.openapi.project.Project;

import static java.util.stream.Collectors.joining;

public class IntelliJBranchNumberProvider extends IntelliJBranchProvider {

    public IntelliJBranchNumberProvider(Project project) {
        super(project);
    }

    @Override
    public String getVariableName() {
        return "branchNr";
    }

    @Override
    public String resolve(Refactoring refactoring) {
        return filterNumbers(super.resolve(refactoring));
    }

    static String filterNumbers(String toFilter) {
        return toFilter.chars()
                .dropWhile(c -> !Character.isDigit(c))
                .takeWhile(Character::isDigit)
                .mapToObj(c -> (char) c)
                .map(String::valueOf)
                .collect(joining());
    }
}
