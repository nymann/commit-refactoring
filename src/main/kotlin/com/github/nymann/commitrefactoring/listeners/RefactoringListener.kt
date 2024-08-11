package com.github.nymann.commitrefactoring.listeners

import com.github.nymann.commitrefactoring.RefactoringStore
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.project.Project
import com.intellij.refactoring.listeners.RefactoringEventData
import com.intellij.refactoring.listeners.RefactoringEventListener

class RefactoringListener(private val project: Project) : RefactoringEventListener {
    override fun refactoringDone(refactoringId: String, afterData: RefactoringEventData?) {
        RefactoringStore.addRefactoring(refactoringId, afterData)
        if (false) {
            // TODO: feature toggle
            triggerCheckInProjectActionInAHorribleWay(project)
        }
    }

    private fun triggerCheckInProjectActionInAHorribleWay(project: Project) {
        val actionManager = ActionManager.getInstance()
        val action = actionManager.getAction("CheckinProject") ?: return

        val dataContext = DataContext {
            when (it) {
                CommonDataKeys.PROJECT.name -> project
                else -> null
            }
        }
        val actionEvent = AnActionEvent.createFromDataContext(
            ActionPlaces.UNKNOWN,
            Presentation(),
            dataContext
        )
        action.actionPerformed(actionEvent)
    }

}
