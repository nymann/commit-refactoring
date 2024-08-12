package com.github.nymann.commitrefactoring.listeners

import com.github.nymann.commitrefactoring.RefactoringStore
import com.intellij.refactoring.listeners.RefactoringEventData
import com.intellij.refactoring.listeners.RefactoringEventListener

class RefactoringListener : RefactoringEventListener {
    override fun refactoringDone(refactoringId: String, afterData: RefactoringEventData?) {
        RefactoringStore.addRefactoring(refactoringId, afterData)
    }

    override fun undoRefactoring(refactoringId: String) {
        RefactoringStore.undoLastRefactoring()
    }

    override fun redoRefactoring(refactoringId: String) {
        RefactoringStore.redoLastRefactoring()
    }

    override fun refactoringStarted(refactoringId: String, beforeData: RefactoringEventData?) {
        // TODO: We can get the before state eg. we would be able to do 'Rename variable X to Y'
        super.refactoringStarted(refactoringId, beforeData)
    }
}
