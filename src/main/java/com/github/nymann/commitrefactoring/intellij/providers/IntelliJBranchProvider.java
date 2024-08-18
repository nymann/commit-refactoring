package com.github.nymann.commitrefactoring.intellij.providers;

import com.github.nymann.commitrefactoring.Refactoring;
import com.github.nymann.commitrefactoring.TemplateVariableProvider;
import com.intellij.openapi.project.Project;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryManager;
import org.jetbrains.annotations.NotNull;

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
        for (GitRepository repository : repositoryManager.getRepositories()) {
            return repository.getCurrentBranchName();
        }
        return "unknown-branch";
    }
}
