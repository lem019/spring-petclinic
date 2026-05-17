# Task 2 Criterion 3: Avoid Redundant Coding

## Code Issue

SonarQube reported several maintainability issues caused by duplicated values, weak naming, and tests that executed code without assertions:

- `OwnerController.java`: duplicate literal `"error"` used three times.
- `NamedEntity.java`, `Vets.java`, and `ClinicServiceTests.java`: local variables or fields hid existing fields.
- `PetValidatorTests.java`: constant names did not follow Java constant naming conventions.
- `MySqlIntegrationTests.java`, `PetClinicIntegrationTests.java`, and `PostgresIntegrationTests.java`: tests called `vets.findAll()` without assertions.

## Sustainability Impact

Repeated literals make future changes error-prone because the same concept must be updated in multiple places. Shadowed names make code harder to read because developers must track whether a reference points to a field or a local variable. Tests without assertions provide weak regression protection because they may pass without proving expected behavior.

## Improvement Implemented

The repeated `"error"` flash attribute name was moved into a single `ERROR_ATTRIBUTE` constant. Shadowing names were replaced with clearer names such as `entityName`, `vetList`, `ownerPage`, `petTypes`, and `allVets`. Pet validator constants were renamed to `PET_NAME`, `PET_TYPE_NAME`, and `PET_BIRTH_DATE`. Integration tests now assert that `vets.findAll()` returns data while still exercising the cached second call.

These changes reduce duplication, improve readability, and make the test suite more meaningful.

## Suggested PR

Title: `Remove redundant literals and clarify maintainability tests`

Description: Consolidates repeated constants, removes confusing shadowed names, and strengthens integration tests with explicit assertions.

## Evidence to Add to Appendix

Include the local SonarQube before screenshot showing duplicate literal, naming, and missing assertion issues, the after screenshot showing Maintainability = 0, and the PR page showing peer approval and merge. Add this criterion to the Task 2 contribution table.

## Files Included

The `code/` directory contains complete replacement files with their original project paths preserved.
