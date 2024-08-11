package com.github.nymann.commitrefactoring

import com.intellij.refactoring.listeners.RefactoringEventData
import java.util.*

object RefactoringStore {

    private val refactorings = mutableListOf<Refactoring>()

    fun addRefactoring(refactoringId: String, afterData: RefactoringEventData?) {
        refactorings.add(Refactoring(refactoringId, constructCommitMessage(refactoringId, afterData)))
    }

    fun getRefactorings(): List<Refactoring> {
        return refactorings
    }

    fun getLatest(): Refactoring? {
        return refactorings.lastOrNull()
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
}

data class Refactoring(val id: String, val description: String)
