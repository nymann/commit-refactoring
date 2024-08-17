package com.github.nymann.commitrefactoring.intellij.listeners;


import com.github.nymann.commitrefactoring.intellij.IntelliJRefactoringService;
import com.intellij.notification.Notification;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class CommitListener implements Notifications {

    private final IntelliJRefactoringService intelliJRefactoringService;

    public CommitListener(Project project) {
        intelliJRefactoringService = project.getService(IntelliJRefactoringService.class);
    }

    @Override
    public void notify(@NotNull Notification notification) {
        if (notification.getContent().contains("committed:")) {
            intelliJRefactoringService.clearRefactorings();
        }
    }
}
