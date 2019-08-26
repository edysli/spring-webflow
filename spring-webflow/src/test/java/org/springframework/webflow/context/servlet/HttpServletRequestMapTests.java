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
package org.springframework.webflow.context.servlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * Unit test for the {@link HttpServletRequestMap} class.
 * 
 * @author Ulrik Sandberg
 */
public class HttpServletRequestMapTests {

	private HttpServletRequestMap tested;

	private MockHttpServletRequest request;

	@Before
	public void setUp() throws Exception {
		request = new MockHttpServletRequest();
		tested = new HttpServletRequestMap(request);
	}

	@After
	public void tearDown() throws Exception {
		request = null;
		tested = null;
	}

	@Test
	public void testGetAttribute() {
		request.setAttribute("Some key", "Some value");
		// perform test
		Object result = tested.getAttribute("Some key");
		assertEquals("Some value", result);
	}

	@Test
	public void testSetAttribute() {
		// perform test
		tested.setAttribute("Some key", "Some value");
		assertEquals("Some value", request.getAttribute("Some key"));
	}

	@Test
	public void testRemoveAttribute() {
		request.setAttribute("Some key", "Some value");
		// perform test
		tested.removeAttribute("Some key");
		assertNull(request.getAttribute("Some key"));
	}

	@Test
	public void testGetAttributeNames() {
		request.setAttribute("Some key", "Some value");
		request.removeAttribute("javax.servlet.context.tempdir");
		// perform test
		Iterator<String> names = tested.getAttributeNames();
		assertNotNull("Null result unexpected", names);
		assertTrue("More elements", names.hasNext());
		String name = names.next();
		assertEquals("Some key", name);
	}
}
