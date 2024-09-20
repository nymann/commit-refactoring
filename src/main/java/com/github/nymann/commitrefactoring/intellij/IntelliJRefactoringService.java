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
    private final TemplateProcessor defaultMessageTemplate;
    private boolean commitMessageViaButtonOnly;
    private CheckinProjectPanel panel;

    public IntelliJRefactoringService(Project project) {
        CommitRefactoringSettings settings = CommitRefactoringSettings.getInstance();
        List<TemplateVariableProvider> providers = List.of(new RefactoringProvider(), new IntelliJBranchProvider(project));
        refactoringMessageTemplate = new TemplateProcessor(settings.getTemplate(), providers);
        defaultMessageTemplate = new TemplateProcessor(settings.getDefaultCommitMessage(), providers);
        this.refactoringService = new RefactoringService(refactoringMessageTemplate, defaultMessageTemplate);
        commitMessageViaButtonOnly = settings.getCommitMessageViaButtonOnly();

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

    public void setButtonBaseCommitMessage() {
        this.panel.setCommitMessage(getCommitMessage());
    }

    public void setCommitMessageOnPanel() {
        if (getCommitMessageViaButtonOnly()) {
            return;
        }

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

    public String getModalAutomaticCommitMessage() {
        if (getCommitMessageViaButtonOnly()) {
            return null;
        }
        return this.getCommitMessage();
    }

    @Override
    public void onSettingsChanged() {
        CommitRefactoringSettings settings = CommitRefactoringSettings.getInstance();
        this.refactoringMessageTemplate.setTemplate(settings.getTemplate());
        this.defaultMessageTemplate.setTemplate(settings.getDefaultCommitMessage());
        this.setCommitMessageViaButtonOnly(settings.getCommitMessageViaButtonOnly());
        this.setCommitMessageOnPanel();
    }

    public boolean getCommitMessageViaButtonOnly() {
        return this.commitMessageViaButtonOnly;
    }

    public void setCommitMessageViaButtonOnly(boolean enabled) {
        commitMessageViaButtonOnly = enabled;
    }
}
