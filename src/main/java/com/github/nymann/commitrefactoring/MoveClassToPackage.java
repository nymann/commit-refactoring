package com.github.nymann.commitrefactoring;

import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiPackage;

public class MoveClassToPackage implements CommitMessage {
    private final PsiClass psiClass;
    private final PsiPackage destinationPackage;

    public MoveClassToPackage(PsiClass psiClass, PsiPackage destinationPackage) {
        this.psiClass = psiClass;
        this.destinationPackage = destinationPackage;
    }

    @Override
    public String getMessage() {
        return "Moved class '" + psiClass.getName() + "' to '" + this.destinationPackage.getName() + "'";
    }
}
