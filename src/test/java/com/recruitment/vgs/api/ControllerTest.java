package com.recruitment.vgs.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recruitment.vgs.api.controller.UserController;
import com.recruitment.vgs.api.domain.Request;
import com.recruitment.vgs.api.domain.Response;
import com.recruitment.vgs.api.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@WebMvcTest(UserController.class)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userServiceMock;


	@Test
	@DisplayName("Test to validate the create user")
	public void shouldReturn204ToCreateUser() throws Exception {

		Mockito.when(userServiceMock.saveUser(Mockito.any(Request.class)))
				.thenReturn(new Response());

		this.mockMvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(asJsonString(new Request())))
				.andExpect(status().isNoContent());
	}


	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
