package com.github.nymann.commitrefactoring.intellij.actions;

import com.github.nymann.commitrefactoring.intellij.IntelliJRefactoringService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

public class UpdateCommitMessageViaButton extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();
        if (project == null) {
            return;
        }
        IntelliJRefactoringService service = project.getService(IntelliJRefactoringService.class);
        service.setButtonBaseCommitMessage();
    }
}
