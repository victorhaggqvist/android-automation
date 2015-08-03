# Android test automation
Android test automation using Selendroid.

## Requirements
- Selendroid 0.16.0

## Running tests
Tests assume use of `selendroid-standalone-0.16.0-with-dependencies.jar`, though other versions might work to.

A server instance using mentioned jar should be started with the given apk before running tests.

Tests may be executed either using `mvn test` or by opening the Maven project for example IntelliJ to run them.

### Notes
Tests that should find Toasts don't seem to work, this is mentioned in `WrongGoalWeightTest` which tries to do that.

Tests using different units/gender are distributed across the route tests.

One might want to add tests for all possible paths through each route when running in production.
