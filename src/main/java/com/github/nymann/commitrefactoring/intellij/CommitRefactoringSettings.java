package com.github.nymann.commitrefactoring.intellij;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;

import com.intellij.util.messages.Topic;

@State(
        name = "CommitRefactoring",
        storages = @Storage("CommitRefactoring.xml")
)
@Service(Service.Level.APP)
public final class CommitRefactoringSettings implements PersistentStateComponent<CommitRefactoringSettings.State> {

    public static final Topic<TemplateChangeListener> TEMPLATE_CHANGED_TOPIC =
            Topic.create("Template changed", TemplateChangeListener.class);

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
        notifyTemplateChanged(template);
    }

    private void notifyTemplateChanged(String newTemplate) {
        ApplicationManager.getApplication().getMessageBus()
                .syncPublisher(TEMPLATE_CHANGED_TOPIC)
                .onTemplateChanged(newTemplate);
    }

    public static class State {
        public String template = "${refactoring}";
    }
}
