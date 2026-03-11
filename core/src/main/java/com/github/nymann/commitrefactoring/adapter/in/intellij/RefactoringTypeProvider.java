package com.github.nymann.commitrefactoring.adapter.in.intellij;

import com.github.nymann.commitrefactoring.domain.RefactoringType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface RefactoringTypeProvider {
    @Nullable RefactoringType resolve(@NotNull String refactoringId);
}
