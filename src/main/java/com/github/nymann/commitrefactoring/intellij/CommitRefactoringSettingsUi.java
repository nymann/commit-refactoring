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

        reset();
        return panel;
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
        return !templateField
                .getText()
                .equals(CommitRefactoringSettings
                        .getInstance()
                        .getTemplate());
    }

    @Override
    public void apply() {
        CommitRefactoringSettings
                .getInstance()
                .setTemplate(templateField.getText());
    }

    @Override
    public void reset() {
        templateField.setText(CommitRefactoringSettings
                .getInstance()
                .getTemplate());
    }

    @Override
    public void disposeUIResources() {
        panel = null;
        templateField = null;
    }
}
