package com.github.nymann.commitrefactoring.adapter.in.intellij;

import com.github.nymann.commitrefactoring.domain.CodeElement;
import com.intellij.refactoring.listeners.RefactoringEventData;

public interface CodeElementProvider {
    CodeElement create(RefactoringEventData eventData);
}
