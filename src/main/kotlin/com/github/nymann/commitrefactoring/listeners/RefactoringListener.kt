package com.github.nymann.commitrefactoring.listeners

import com.github.nymann.commitrefactoring.RefactoringStore
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.refactoring.listeners.RefactoringEventData
import com.intellij.refactoring.listeners.RefactoringEventListener

class RefactoringListener(private val project: Project) : RefactoringEventListener {
    private val logger = Logger.getInstance(RefactoringListener::class.java)

    override fun refactoringStarted(refactoringId: String, beforeData: RefactoringEventData?) {
        logger.warn("Refactoring started: $refactoringId")
    }

    override fun conflictsDetected(refactoringId: String, conflictsData: RefactoringEventData) {
        logger.warn("Conflicts detected in refactoring: $refactoringId")
    }

    override fun undoRefactoring(refactoringId: String) {
        logger.warn("Undo refactoring: $refactoringId")
    }

    override fun refactoringDone(refactoringId: String, afterData: RefactoringEventData?) {
        RefactoringStore.addRefactoring(refactoringId, afterData)
        if (false) {
            // TODO: feature toggle
            triggerCheckInProjectActionInAhorribleWay(project)
        }
    }

    private fun triggerCheckInProjectActionInAhorribleWay(project: Project) {
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
