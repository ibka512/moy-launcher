# MOY Launcher

MOY Launcher is the first usable component of **MOY AIOS** — *Mind Of You / My Own Intelligence*.

It is an Android home app built for a phone that feels calm, direct and personal: a Smartisan-inspired four-panel home experience, quick cross-app actions and an optional AI entry point. The first release deliberately stays focused on everyday convenience rather than turning a phone into a work computer.

## Current status

`0.1 Alpha` is the project foundation. It preserves the stable Launcher3-based home-screen behavior from Lawnchair 15 while MOY’s visual language and four-panel interaction are built in separate, reviewable changes.

The initial implementation goals are:

- A four-panel MOY home layout for frequently used apps and actions.
- Drag-and-drop oriented shortcuts and cards.
- A single AI capture/recall entry point; no AI workspace.
- Clear separation between normal launcher features and later root/Shizuku or system-ROM features.

See [MOY scope](docs/MOY_SCOPE.md) for the staged plan and boundaries.

## Build baseline

This branch starts from Lawnchair 15, itself based on AOSP Launcher3. The current MOY package IDs begin with `io.github.ibka512.moy.launcher`, allowing test builds to coexist with Lawnchair.

The project currently targets Android 15-era devices and uses JDK 17 with Android SDK Build Tools 36.1.0.

## Credits and license

MOY Launcher is derived from [Lawnchair](https://github.com/LawnchairLauncher/lawnchair), which is based on Android Open Source Project Launcher3. Original copyright notices and the Apache License 2.0 are retained in this repository.
