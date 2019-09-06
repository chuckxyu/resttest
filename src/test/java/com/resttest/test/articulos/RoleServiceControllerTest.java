package com.resttest.test.articulos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.articulos.model.Role;
import com.resttest.test.AbstractTest;

public class RoleServiceControllerTest extends AbstractTest {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getRoleList() throws Exception {
		String uri = "/api/roles";
		
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Role[] rolelist = super.mapFromJson(content, Role[].class);
		assertTrue(rolelist.length == 0);

	}

}
