package com.github.nymann.commitrefactoring.listeners

import com.github.nymann.commitrefactoring.RefactoringStore
import com.intellij.openapi.project.Project
import com.intellij.refactoring.listeners.RefactoringEventData
import com.intellij.refactoring.listeners.RefactoringEventListener

class RefactoringListener(private val project: Project) : RefactoringEventListener {
    override fun refactoringDone(refactoringId: String, afterData: RefactoringEventData?) {
        RefactoringStore.addRefactoring(refactoringId, afterData)
    }

    override fun undoRefactoring(refactoringId: String) {
        RefactoringStore.undoLastRefactoring()
    }

    override fun redoRefactoring(refactoringId: String) {
        RefactoringStore.redoLastRefactoring()
    }
}
