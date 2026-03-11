package com.github.nymann.commitrefactoring.adapter.in.intellij.java;

import com.github.nymann.commitrefactoring.adapter.in.intellij.RefactoringTypeProvider;
import com.github.nymann.commitrefactoring.domain.RefactoringType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class JavaRefactoringTypeProvider implements RefactoringTypeProvider {
    @Override
    public @Nullable RefactoringType resolve(@NotNull String refactoringId) {
        return switch (refactoringId) {
            case "refactoring.inline.method", "refactoring.inline.local.variable",
                 "refactoring.inline.class", "refactoring.inline.parameter",
                 "refactoring.inline.field" -> RefactoringType.INLINE;
            case "refactoring.extract.method", "refactoring.extractVariable" -> RefactoringType.EXTRACT;
            case "refactoring.inplace.rename", "refactoring.rename" -> RefactoringType.RENAME;
            case "refactoring.safeDelete" -> RefactoringType.SAFE_DELETE;
            case "refactoring.changeSignature",
                 "refactoring.changeClassSignature" -> RefactoringType.CHANGE_SIGNATURE;
            case "refactoring.move" -> RefactoringType.MOVE;
            case "refactoring.introduceParameter" -> RefactoringType.INTRODUCE_PARAMETER;
            case "refactoring.push.down" -> RefactoringType.PUSH_MEMBERS_DOWN;
            case "refactoring.makeStatic" -> RefactoringType.MAKE_STATIC;
            case "refactoring.extract.delegate" -> RefactoringType.EXTRACT_DELEGATE;
            case "refactoring.encapsulateFields" -> RefactoringType.ENCAPSULATE_FIELDS;
            case "refactoring.makeInstance" -> RefactoringType.CONVERT_INSTANCE_METHOD;
            case "refactoring.pull.up" -> RefactoringType.PULL_MEMBERS_UP;
            case "refactoring.move.members", "refactoring.moveMembers" -> RefactoringType.MOVE_MEMBERS;
            default -> null;
        };
    }
}
