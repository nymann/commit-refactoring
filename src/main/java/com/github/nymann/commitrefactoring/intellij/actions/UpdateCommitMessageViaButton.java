package com.github.nymann.commitrefactoring.intellij.actions;

import com.github.nymann.commitrefactoring.intellij.IntelliJRefactoringService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.CommitMessageI;
import com.intellij.openapi.vcs.VcsDataKeys;
import com.intellij.openapi.vcs.ui.Refreshable;

public class UpdateCommitMessageViaButton extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();
        if (project == null) {
            return;
        }
        String message = project.getService(IntelliJRefactoringService.class).getCommitMessage();
        getCommitPanel(event).setCommitMessage(message);
    }

    private CommitMessageI getCommitPanel(AnActionEvent event) {
        Refreshable data = Refreshable.PANEL_KEY.getData(event.getDataContext());

        if (data instanceof CommitMessageI) {
            return (CommitMessageI) data;
        }

        return VcsDataKeys.COMMIT_MESSAGE_CONTROL.getData(event.getDataContext());
    }
}
