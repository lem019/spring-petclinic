/*
 * Copyright 2012-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data access contract for {@link Owner} records.
 * <p>
 * The application uses this repository as the owner service boundary: controllers call
 * these methods for owner lookup, creation, and updates while Spring Data JPA provides
 * the implementation at runtime. Method names intentionally follow Spring Data query
 * conventions so the behavior can be inferred without a handwritten query.
 * </p>
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Wick Dynex
 */
public interface OwnerRepository extends JpaRepository<Owner, Integer> {

	/**
	 * Retrieve {@link Owner}s whose last name starts with the supplied text.
	 * @param lastName last-name prefix to search for; an empty string returns the
	 * broadest match set
	 * @param pageable page request controlling result size and offset
	 * @return a page of matching {@link Owner}s, or an empty page when no match exists
	 */
	Page<Owner> findByLastNameStartingWith(String lastName, Pageable pageable);

	/**
	 * Retrieve an {@link Owner} by database identifier.
	 * @param id owner identifier to search for
	 * @return an {@link Optional} containing the owner when found, or an empty
	 * {@link Optional} otherwise
	 */
	Optional<Owner> findById(Integer id);

}
