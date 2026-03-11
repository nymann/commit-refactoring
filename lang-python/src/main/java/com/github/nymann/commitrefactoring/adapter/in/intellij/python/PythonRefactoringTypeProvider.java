package com.github.nymann.commitrefactoring.adapter.in.intellij.python;

import com.github.nymann.commitrefactoring.adapter.in.intellij.RefactoringTypeProvider;
import com.github.nymann.commitrefactoring.domain.RefactoringType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PythonRefactoringTypeProvider implements RefactoringTypeProvider {
    @Override
    public @Nullable RefactoringType resolve(@NotNull String refactoringId) {
        return switch (refactoringId) {
            case "refactoring.python.extract.variable", "refactoring.python.introduce.variable" -> RefactoringType.EXTRACT;
            case "refactoring.python.extract.method" -> RefactoringType.EXTRACT;
            case "refactoring.python.inline.local", "refactoring.python.inline.local.variable" -> RefactoringType.INLINE;
            case "refactoring.python.rename" -> RefactoringType.RENAME;
            case "refactoring.python.move" -> RefactoringType.MOVE;
            case "refactoring.python.safeDelete" -> RefactoringType.SAFE_DELETE;
            case "refactoring.python.changeSignature" -> RefactoringType.CHANGE_SIGNATURE;
            case "refactoring.python.pull.up" -> RefactoringType.PULL_MEMBERS_UP;
            case "refactoring.python.push.down" -> RefactoringType.PUSH_MEMBERS_DOWN;
            case "refactoring.python.introduceParameter" -> RefactoringType.INTRODUCE_PARAMETER;
            default -> null;
        };
    }
}
