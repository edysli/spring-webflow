/*
 * Copyright 2004-2012 the original author or authors.
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
package org.springframework.webflow.action;

import junit.framework.TestCase;

import org.springframework.webflow.execution.Event;
import org.springframework.webflow.test.MockRequestContext;

/**
 * Unit tests for {@link ResultObjectBasedEventFactory}.
 */
public class ResultObjectEventFactoryTests extends TestCase {

	private MockRequestContext context = new MockRequestContext();

	private ResultObjectBasedEventFactory factory = new ResultObjectBasedEventFactory();

	public void testAlreadyAnEvent() {
		Event event = new Event(this, "event");
		Event result = factory.createResultEvent(this, event, context);
		assertSame(event, result);
	}

	public void testMappedTypes() {
		assertTrue(factory.isMappedValueType(MyEnum.class));
		assertTrue(factory.isMappedValueType(boolean.class));
		assertTrue(factory.isMappedValueType(Boolean.class));
		assertTrue(factory.isMappedValueType(String.class));
		assertFalse(factory.isMappedValueType(Integer.class));
	}

	public void testNullResult() {
		Event result = factory.createResultEvent(this, null, context);
		assertEquals("null", result.getId());
	}

	public void testBooleanResult() {
		Event result = factory.createResultEvent(this, true, context);
		assertEquals("yes", result.getId());
		result = factory.createResultEvent(this, false, context);
		assertEquals("no", result.getId());
	}

	public void testLabeledEnumResult() {
		Event result = factory.createResultEvent(this, MyEnum.FOO, context);
		assertEquals("FOO", result.getId());
	}

	public void testOtherResult() {
		Event result = factory.createResultEvent(this, "hello", context);
		assertEquals("hello", result.getId());
	}

	private enum MyEnum {
		FOO
	}

}
