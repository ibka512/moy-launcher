# MOY Launcher 0.1 scope

## Product direction

MOY Launcher is the first stage of MOY AIOS (*Mind Of You / My Own Intelligence*). It turns the home screen into a calm, fast starting point for everyday phone use. It takes inspiration from Smartisan OS interaction ideas, but uses its own code, visual identity and assets.

## In scope now

1. Establish a buildable, independently installable Launcher3-based app.
2. Rebrand the app, package IDs, release artifacts and user-facing project documentation.
3. Design and implement a four-panel home layout that prioritises quick actions over dense productivity features.
4. Add a clear placeholder entry for future AI capture and recall.
5. Test ordinary launcher behaviour on a real Android device before adding privileged integrations.

## Explicitly out of scope for 0.1

- A full Android ROM or replacement system shell.
- An AI workspace or autonomous cross-app agent.
- Capturing other apps’ content without a user-initiated flow and the appropriate Android permission.
- Root, Shizuku, Accessibility or QuickSwitch-dependent features as a requirement for normal home-screen use.

## Later stages

### MOY Launcher 0.2

- Refined four-panel layout, cards, shortcuts and drag interactions.
- An opt-in AI capture flow for text or screenshots initiated by the user.
- A compatibility layer for optional privileged actions.

### MOY Shell / MOY AIOS

- System-level integrations only where a normal launcher cannot safely provide the required capability.
- AOSP or LineageOS integration after the launcher interaction model has been validated on real devices.

## Technical baseline

- Base: Lawnchair 15 / AOSP Launcher3.
- Language: Kotlin and Java already used by the base project.
- Minimum Android version: 8.0 (API 26).
- Current test target: Android 15-era devices; device-specific verification will follow once a primary test phone is selected.
