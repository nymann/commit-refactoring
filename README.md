# commit-refactoring

[![Build](https://github.com/nymann/commit-refactoring/workflows/Build/badge.svg)](https://github.com/nymann/commit-refactoring/actions)
[![Version](https://img.shields.io/jetbrains/plugin/v/25085-commit-refactoring.svg)](https://plugins.jetbrains.com/plugin/25085-commit-refactoring)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/25085-commit-refactoring.svg)](https://plugins.jetbrains.com/plugin/25085-commit-refactoring)

<!-- Plugin description -->

# Commit Refactoring

Warning: everything besides Java is untested, other languages might work.

Sets the commit message automatically after each IntelliJ refactoring.

## How it works

1. You do an IntelliJ based refactoring (Extract method)
2. You open IntelliJ's commit window (<kbd>Command</kbd>+<kbd>K</kbd>)
3. Commit message will be predefined to "Extract method {methodName}"

<!-- Plugin description end -->

## Installation

- Using the IDE built-in plugin system:

  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "commit-refactoring"</kbd>
  > <kbd>Install</kbd>

- Using JetBrains Marketplace:

  Go to [JetBrains Marketplace](https://plugins.jetbrains.com/plugin/25085-commit-refactoring) and install it by clicking
  the <kbd>Install to ...</kbd> button in case your IDE is running.

  You can also download the [latest release](https://plugins.jetbrains.com/plugin/25085-commit-refactoring/versions) from
  JetBrains Marketplace and install it manually using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

- Manually:

  Download the [latest release](https://github.com/nymann/commit-refactoring/releases/latest) and install it manually
  using
  <kbd>Settings/Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template

[docs:plugin-description]: https://plugins.jetbrains.com/docs/intellij/plugin-user-experience.html#plugin-description-and-presentation
