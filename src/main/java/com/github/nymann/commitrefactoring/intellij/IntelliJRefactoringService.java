package com.github.nymann.commitrefactoring.intellij;

import com.github.nymann.commitrefactoring.*;
import com.github.nymann.commitrefactoring.intellij.providers.IntelliJBranchProvider;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.CheckinProjectPanel;

import java.util.List;

@Service(Service.Level.PROJECT)
public final class IntelliJRefactoringService implements SettingsChangeListener {

    private final RefactoringService refactoringService;
    private final TemplateProcessor refactoringMessageTemplate;
    private CheckinProjectPanel panel;

    public IntelliJRefactoringService(Project project) {
        CommitRefactoringSettings settings = CommitRefactoringSettings.getInstance();
        String template = settings.getTemplate();
        List<TemplateVariableProvider> providers = List.of(new RefactoringProvider(), new IntelliJBranchProvider(project));
        refactoringMessageTemplate = new TemplateProcessor(template, providers);
        this.refactoringService = new RefactoringService(refactoringMessageTemplate, settings.getDefaultCommitMessage());

        project
                .getMessageBus()
                .connect()
                .subscribe(CommitRefactoringSettings.SETTINGS_CHANGED_TOPIC, this);
    }

    public void addRefactoring(Refactoring refactoring) {
        this.refactoringService.addRefactoring(refactoring);
    }

    public void clearRefactorings() {
        this.refactoringService.clearRefactorings();
    }

    public void undoLastRefactoring() {
        this.refactoringService.undoLastRefactoring();
    }

    public void redoLastRefactoring() {
        this.refactoringService.redoLastRefactoring();
    }

    public String getCommitMessage() {
        return this.refactoringService.getCommitMessage();
    }

    public void setCommitMessageOnPanel() {
        if (usingNonModalCommitEditor()) {
            this.panel.setCommitMessage(getCommitMessage());
        }
    }

    private boolean usingNonModalCommitEditor() {
        return this.panel != null;
    }

    public void setPanel(CheckinProjectPanel panel) {
        this.panel = panel;
    }

    @Override
    public void onSettingsChanged() {
        CommitRefactoringSettings settings = CommitRefactoringSettings.getInstance();
        this.refactoringMessageTemplate.setTemplate(settings.getTemplate());
        this.refactoringService.setDefaultCommitMessage(settings.getDefaultCommitMessage());
        this.setCommitMessageOnPanel();

    }
}
