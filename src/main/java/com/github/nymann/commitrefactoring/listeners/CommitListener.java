package com.github.nymann.commitrefactoring.listeners;


import com.github.nymann.commitrefactoring.SingletonRefactoringStore;
import com.intellij.notification.Notification;
import com.intellij.notification.Notifications;
import org.jetbrains.annotations.NotNull;

public class CommitListener implements Notifications {
    @Override
    public void notify(@NotNull Notification notification) {
        if (notification.getContent().contains("committed: Refactoring")) {
            SingletonRefactoringStore.getInstance().clear();
        }
    }
}
