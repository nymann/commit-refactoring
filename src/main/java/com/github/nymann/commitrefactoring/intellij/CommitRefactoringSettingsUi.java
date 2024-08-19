package com.github.nymann.commitrefactoring.intellij;

import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class CommitRefactoringSettingsUi implements Configurable {
    private JPanel panel;
    private JTextField templateField;
    private JTextField defaultCommitMessage;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Commit Refactoring";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(resetButtonRow());
        panel.add(templateRow());
        panel.add(defaultMessage());

        reset();
        return panel;
    }

    private JPanel defaultMessage() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        JLabel label = new JLabel("Default message:");
        defaultCommitMessage = new JTextField();
        defaultCommitMessage.setMaximumSize(new Dimension(Integer.MAX_VALUE, defaultCommitMessage.getPreferredSize().height));

        inputPanel.add(label);
        inputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        inputPanel.add(defaultCommitMessage);
        return inputPanel;
    }

    private @NotNull JPanel resetButtonRow() {
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetToDefault());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(resetButton);
        return buttonPanel;
    }

    private @NotNull JPanel templateRow() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
        JLabel label = new JLabel("Template:");
        templateField = new JTextField();
        templateField.setMaximumSize(new Dimension(Integer.MAX_VALUE, templateField.getPreferredSize().height));

        inputPanel.add(label);
        inputPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        inputPanel.add(templateField);
        return inputPanel;
    }

    public void resetToDefault() {
        templateField.setText("${refactoring}");
    }

    @Override
    public boolean isModified() {
        CommitRefactoringSettings settings = CommitRefactoringSettings
                .getInstance();
        boolean templateHasChanged = !templateField
                .getText()
                .equals(settings
                        .getTemplate());
        boolean defaultCommitMessageHasChanged = !defaultCommitMessage
                .getText()
                .equals(settings.getDefaultCommitMessage());
        return templateHasChanged || defaultCommitMessageHasChanged;
    }

    @Override
    public void apply() {
        CommitRefactoringSettings instance = CommitRefactoringSettings.getInstance();
        instance.setTemplate(templateField.getText());
        instance.setDefaultCommitMessage(defaultCommitMessage.getText());
    }

    @Override
    public void reset() {
        CommitRefactoringSettings settings = CommitRefactoringSettings.getInstance();
        templateField.setText(settings.getTemplate());
        defaultCommitMessage.setText(settings.getDefaultCommitMessage());
    }

    @Override
    public void disposeUIResources() {
        panel = null;
        templateField = null;
        defaultCommitMessage = null;
    }
}
