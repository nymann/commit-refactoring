package com.github.nymann.commitrefactoring.listeners

import com.github.nymann.commitrefactoring.RefactoringStore
import com.intellij.notification.Notification
import com.intellij.notification.Notifications

class CommitListener : Notifications {
    override fun notify(notification: Notification) {
        if (notification.content.contains("committed: Refactoring")) {
            RefactoringStore.clear()
        }
    }
}