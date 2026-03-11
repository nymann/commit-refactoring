package com.github.nymann.commitrefactoring.adapter.out.intellij.providers;

import com.github.nymann.commitrefactoring.domain.Refactoring;
import com.github.nymann.commitrefactoring.application.port.TemplateVariableProvider;
import com.intellij.dvcs.repo.Repository;
import com.intellij.openapi.project.Project;
import git4idea.repo.GitRepositoryManager;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class IntelliJBranchProvider implements TemplateVariableProvider {
    private final @NotNull GitRepositoryManager repositoryManager;

    public IntelliJBranchProvider(Project project) {
        this.repositoryManager = GitRepositoryManager.getInstance(project);
    }

    @Override
    public String getVariableName() {
        return "branch";
    }

    @Override
    public String resolve(Refactoring refactoring) {
		return repositoryManager.getRepositories()
				.stream()
				.map(Repository::getCurrentBranchName)
				.filter(Objects::nonNull)
				.findFirst()
				.orElse("unknown-branch");
	}
}
