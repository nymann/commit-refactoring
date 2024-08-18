package com.github.nymann.commitrefactoring.intellij;

import com.github.nymann.commitrefactoring.*;
import com.github.nymann.commitrefactoring.intellij.providers.IntelliJBranchProvider;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.CheckinProjectPanel;

import java.util.List;

@Service(Service.Level.PROJECT)
public final class IntelliJRefactoringService implements TemplateChangeListener {

    private final RefactoringService refactoringService;
    private final TemplateProcessor templateProcessor;
    private CheckinProjectPanel panel;

    public IntelliJRefactoringService(Project project) {
        String template = CommitRefactoringSettings
                .getInstance()
                .getTemplate();
        List<TemplateVariableProvider> providers = List.of(new RefactoringProvider(), new IntelliJBranchProvider(project));
        templateProcessor = new TemplateProcessor(template, providers);
        this.refactoringService = new RefactoringService(templateProcessor);


        project
                .getMessageBus()
                .connect()
                .subscribe(
                        CommitRefactoringSettings.TEMPLATE_CHANGED_TOPIC,
                        this
                );
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
    public void onTemplateChanged(String newTemplate) {
        this.templateProcessor.setTemplate(newTemplate);
        this.setCommitMessageOnPanel();
    }
}
