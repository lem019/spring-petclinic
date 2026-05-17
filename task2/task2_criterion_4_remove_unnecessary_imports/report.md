# Task 2 Criterion 4: Remove Unnecessary Imports

## Code Issue

The assignment requires unnecessary imports to be removed and wildcard imports to be avoided. The SonarQube issue in `Vet.java` made the `Collectors` import unnecessary after replacing `collect(Collectors.toList())` with `toList()`. Manual inspection also found wildcard static imports in:

- `src/test/java/org/springframework/samples/petclinic/owner/OwnerControllerTests.java`
- `src/test/java/org/springframework/samples/petclinic/vet/VetControllerTests.java`

## Sustainability Impact

Unused imports add noise and make code review harder because developers must distinguish required dependencies from leftovers. Wildcard imports hide the exact API surface used by a class, can create naming conflicts as libraries evolve, and reduce readability for future maintainers.

## Improvement Implemented

The unused `java.util.stream.Collectors` import was removed from `Vet.java`. Wildcard `MockMvcResultMatchers.*` imports were replaced with explicit imports for only the matchers used by each test class, such as `status`, `model`, `view`, `flash`, `content`, and `jsonPath`.

This keeps dependencies visible at the top of each file and reduces accidental coupling to unused APIs.

## Suggested PR

Title: `Replace wildcard imports with explicit test imports`

Description: Removes an unused stream collector import and replaces wildcard static imports in controller tests with explicit matcher imports.

## Evidence to Add to Appendix

Include the local SonarQube before screenshot showing the import-related issue, the after screenshot showing Maintainability = 0, and the PR page showing peer approval and merge. Add this criterion to the Task 2 contribution table.

## Files Included

The `code/` directory contains complete replacement files with their original project paths preserved.
