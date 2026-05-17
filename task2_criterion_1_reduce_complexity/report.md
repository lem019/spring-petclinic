# Task 2 Criterion 1: Reducing Code Complexity and Optimizing Algorithms

## Code Issue

SonarQube reported maintainability issues where simple logic was written with avoidable control-flow or indirect expressions:

- `src/main/java/org/springframework/samples/petclinic/owner/Owner.java`: "Merge this if statement with the enclosing one." This appeared in pet lookup logic where nested conditions could be expressed as one guard condition.
- `src/main/java/org/springframework/samples/petclinic/owner/PetController.java`: "Immediately return this expression instead of assigning it to the temporary variable `owner`."
- `src/main/java/org/springframework/samples/petclinic/system/CrashController.java`: "Replace generic exceptions with specific library exceptions or a custom exception."
- `src/test/java/org/springframework/samples/petclinic/system/CrashControllerTests.java`: "Replace this lambda with method reference `testee::triggerException`."

## Sustainability Impact

Unnecessary nesting and temporary variables increase the mental effort required to understand common request paths. In `Owner#getPet(Integer)`, nested `if` statements made the method look more complex than the actual rule: return a saved pet whose id matches the requested id. Generic exceptions also make error behavior harder to classify and maintain because future developers cannot distinguish this deliberate demo crash from other runtime failures.

## Improvement Implemented

The nested conditions in the owner pet lookup methods were merged into single guard expressions. `PetController#findOwner` now returns the repository lookup directly. `CrashController` now throws a custom `ExpectedCrashException`, and its unit test uses a method reference while checking the specific exception type.

These changes reduce cognitive complexity, make intent more explicit, and preserve existing application behavior.

## Suggested PR

Title: `Reduce control-flow complexity in owner and crash handling`

Description: Simplifies avoidable branching and indirect expressions identified by SonarQube, replaces a generic runtime exception with a domain-specific custom exception, and updates the related unit test.

## Evidence to Add to Appendix

Include the local SonarQube before screenshot showing the related Maintainability issues, the after screenshot showing Maintainability = 0, and the PR page showing peer approval and merge. Add this criterion to the Task 2 contribution table.

## Files Included

The `code/` directory contains complete replacement files with their original project paths preserved.
