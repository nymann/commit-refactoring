package com.github.nymann.commitrefactoring

import com.intellij.openapi.project.Project
import com.intellij.openapi.vcs.changes.LocalChangeList
import com.intellij.openapi.vcs.changes.ui.CommitMessageProvider

class RefactoringCommitMessageProvider : CommitMessageProvider {

    override fun getCommitMessage(localChangeList: LocalChangeList, project: Project): String {

        return (RefactoringStore.getLatest() ?: return "").description
    }
}
