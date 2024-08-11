package com.github.nymann.commitrefactoring

import com.intellij.openapi.project.Project
import com.intellij.openapi.vcs.changes.LocalChangeList
import com.intellij.openapi.vcs.changes.ui.CommitMessageProvider

class RefactoringCommitMessageProvider : CommitMessageProvider {

    override fun getCommitMessage(localChangeList: LocalChangeList, project: Project): String {
        val refactorings = RefactoringStore.getLatest() ?: return ""

        return refactorings.description
    }
}
