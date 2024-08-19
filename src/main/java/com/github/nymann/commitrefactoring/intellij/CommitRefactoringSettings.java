package com.github.nymann.commitrefactoring.intellij;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.messages.Topic;
import org.jetbrains.annotations.NotNull;

@State(name = "CommitRefactoring", storages = @Storage("CommitRefactoring.xml"))
@Service(Service.Level.APP)
public final class CommitRefactoringSettings implements PersistentStateComponent<CommitRefactoringSettings.State> {

    public static final Topic<SettingsChangeListener> SETTINGS_CHANGED_TOPIC = Topic.create("Commit Refactoring settings changed", SettingsChangeListener.class);

    private final State state = new State();

    public static CommitRefactoringSettings getInstance() {
        return ApplicationManager
                .getApplication()
                .getService(CommitRefactoringSettings.class);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull State state) {
        this.state.template = state.template;
    }

    public String getTemplate() {
        return state.template;
    }

    public void setTemplate(String template) {
        state.template = template;
        notifySettingsChanged();
    }

    public String getDefaultCommitMessage() {
        return state.defaultCommitMessage;
    }

    public void setDefaultCommitMessage(String defaultCommitMessage) {
        state.defaultCommitMessage = defaultCommitMessage;
        notifySettingsChanged();
    }

    private void notifySettingsChanged() {
        ApplicationManager
                .getApplication()
                .getMessageBus()
                .syncPublisher(SETTINGS_CHANGED_TOPIC)
                .onSettingsChanged();
    }

    public static class State {
        public String template = "${refactoring}";
        public String defaultCommitMessage = "UNSAFE";
    }
}
