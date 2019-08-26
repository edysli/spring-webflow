/*
 * Copyright 2004-2008 the original author or authors.
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
package org.springframework.webflow.engine.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link OutputModel}.
 */
public class OutputModelTests {

	@Test
	public void testMergeable() {
		OutputModel child = new OutputModel("child", "childvalue");
		assertTrue(child.isMergeableWith(child));
	}

	@Test
	public void testNotMergeable() {
		OutputModel child = new OutputModel("child", "childvalue");
		OutputModel parent = new OutputModel("parent", "parentvalue");
		assertFalse(child.isMergeableWith(parent));
	}

	@Test
	public void testNotMergeableWithNull() {
		OutputModel child = new OutputModel("child", "childvalue");
		assertFalse(child.isMergeableWith(null));
	}

	@Test
	public void testMerge() {
		OutputModel child = new OutputModel("child", "childvalue");
		OutputModel parent = new OutputModel("child", "parentvalue");
		parent.setType("parenttype");
		child.merge(parent);
		assertEquals("parenttype", child.getType());
	}

}
