package com.github.nymann.commitrefactoring.intellij.extensions;

import com.intellij.openapi.vcs.CheckinProjectPanel;
import com.intellij.openapi.vcs.changes.CommitContext;
import com.intellij.openapi.vcs.checkin.CheckinHandler;
import com.intellij.openapi.vcs.checkin.CheckinHandlerFactory;
import org.jetbrains.annotations.NotNull;

public class RefactoringCheckinHandlerFactory extends CheckinHandlerFactory {
    @Override
    public @NotNull CheckinHandler createHandler(@NotNull CheckinProjectPanel panel, @NotNull CommitContext commitContext) {
        return new RefactoringCheckinHandler();
    }
    private static class RefactoringCheckinHandler extends CheckinHandler {
        @Override
        public void checkinSuccessful() {
            super.checkinSuccessful();
        }
    }
}
