# Task 2 Criterion 2: Minimizing Resource Usage

## Code Issue

SonarQube reported the following maintainability issue:

- `src/main/java/org/springframework/samples/petclinic/vet/Vet.java`: "Replace this usage of `Stream.collect(Collectors.toList())` with `Stream.toList()` and ensure that the list is unmodified."

The project was also checked for open file, stream, reader, and database resources. `src/test/java/org/springframework/samples/petclinic/system/I18nPropertiesSyncTest.java` already used try-with-resources for `Files.walk(...)` and `Files.newBufferedReader(...)`, but one file-reading loop used `Files.readAllLines(...)` rather than an explicit try-with-resources block.

## Sustainability Impact

Using `Collectors.toList()` is more verbose and returns a mutable list, which can allow accidental modification of a derived collection. This increases defensive coding needs and can create avoidable allocation or mutation risk. Unclosed resources would be more serious because leaked file handles, streams, or database connections can reduce application reliability and waste system resources over time.

## Improvement Implemented

`Vet#getSpecialties()` now uses `Stream.toList()`. This expresses the intended result directly and returns an unmodifiable list, reducing mutation risk for callers. The file-reading loop in `I18nPropertiesSyncTest` now uses `try (Stream<String> lineStream = Files.lines(file))`, making resource closure explicit and consistent with the existing `Files.walk(...)` and `Files.newBufferedReader(...)` usage.

## Suggested PR

Title: `Use direct stream collection for vet specialties`

Description: Replaces the older `Collectors.toList()` pattern with `Stream.toList()` and makes file reading use an explicit try-with-resources block.

## Evidence to Add to Appendix

Include the local SonarQube before screenshot showing the `Stream.collect(Collectors.toList())` issue, the after screenshot showing Maintainability = 0, and the PR page showing peer approval and merge. Add this criterion to the Task 2 contribution table.

## Files Included

The `code/` directory contains the updated `Vet.java` and the reviewed resource-handling test file.
