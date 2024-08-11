package com.github.nymann.commitrefactoring

import com.intellij.refactoring.listeners.RefactoringEventData
import java.util.*

object RefactoringStore {

    private val refactorings = mutableListOf<Refactoring>()
    private val undoStack = ArrayDeque<Refactoring>()
    private val redoStack = ArrayDeque<Refactoring>()

    fun addRefactoring(refactoringId: String, afterData: RefactoringEventData?) {
        val refactoring = Refactoring(refactoringId, constructCommitMessage(refactoringId, afterData))
        refactorings.add(refactoring)
        undoStack.push(refactoring)
        redoStack.clear()
    }

    fun getRefactorings(): List<Refactoring> {
        return refactorings
    }

    private fun constructCommitMessage(refactoringId: String, afterData: RefactoringEventData?): String {
        val result = "Refactoring: ${refactoringId.toName()}"
        val psiElement = afterData?.getUserData(RefactoringEventData.PSI_ELEMENT_KEY)
        val methodName = psiElement?.toString()?.split(":")?.lastOrNull()?.trim()
        if (methodName != null) {
            return "$result $methodName"
        }
        return result
    }

    private fun String.toName(): String {
        return removePrefix("refactoring.").split('.').joinToString(" ") { it ->
            it.replaceFirstChar {
                it.titlecase(Locale.getDefault())
            }
        }
    }

    fun clear() {
        this.refactorings.clear()
    }

    fun undoLastRefactoring() {
        if (undoStack.isNotEmpty()) {
            val lastRefactoring = undoStack.pop()
            refactorings.remove(lastRefactoring)
            redoStack.push(lastRefactoring)
        }
    }

    fun redoLastRefactoring() {
        if (redoStack.isNotEmpty()) {
            val lastUndoneRefactoring = redoStack.pop()
            refactorings.add(lastUndoneRefactoring)
            undoStack.push(lastUndoneRefactoring)
        }
    }
}

data class Refactoring(val id: String, val description: String)
