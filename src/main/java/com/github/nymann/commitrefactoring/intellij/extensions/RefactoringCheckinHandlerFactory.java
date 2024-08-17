package com.github.nymann.commitrefactoring.intellij.extensions;

import com.github.nymann.commitrefactoring.intellij.IntelliJRefactoringService;
import com.intellij.openapi.vcs.CheckinProjectPanel;
import com.intellij.openapi.vcs.changes.CommitContext;
import com.intellij.openapi.vcs.checkin.CheckinHandler;
import com.intellij.openapi.vcs.checkin.CheckinHandlerFactory;
import org.jetbrains.annotations.NotNull;

public class RefactoringCheckinHandlerFactory extends CheckinHandlerFactory {
    @Override
    public @NotNull CheckinHandler createHandler(@NotNull CheckinProjectPanel panel, @NotNull CommitContext commitContext) {
        return new RefactoringCheckinHandler(panel
                .getProject()
                .getService(IntelliJRefactoringService.class), panel);
    }

    private static class RefactoringCheckinHandler extends CheckinHandler {

        private final IntelliJRefactoringService refactoringService;

        private RefactoringCheckinHandler(IntelliJRefactoringService refactoringService, CheckinProjectPanel panel) {
            this.refactoringService = refactoringService;
            this.refactoringService.setPanel(panel);
        }


        @Override
        public void checkinSuccessful() {
            refactoringService.clearRefactorings();
            super.checkinSuccessful();
        }
    }
}
