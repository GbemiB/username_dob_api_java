package com.recruitment.vgs.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recruitment.vgs.api.controller.UserController;
import com.recruitment.vgs.api.domain.UserRequestDto;
import com.recruitment.vgs.api.domain.UserResponseDto;
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

	UserRequestDto userRequestDto = new UserRequestDto();

	@Test
	@DisplayName("Test to validate the create user controller")
	public void shouldReturn204ToCreateUser() throws Exception {

		Mockito.when(userServiceMock.saveUser(Mockito.any(UserRequestDto.class)))
				.thenReturn(new UserResponseDto());

		this.mockMvc.perform(post("").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(asJsonString(new UserRequestDto())))
				.andExpect(status().isNoContent());
	}

	@Test
	@DisplayName("Test to validate the create user controller")
	public void shouldReturn500ToCreateUser() throws Exception {

		Mockito.when(userServiceMock.saveUser(Mockito.any(UserRequestDto.class)))
				.thenReturn(new UserResponseDto());

		this.mockMvc.perform(post("").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(asJsonString(new UserRequestDto())))
				.andExpect(status().isInternalServerError());
	}

	@Test
	@DisplayName("Test to validate the create user controller")
	public void shouldReturn200ToUpdateUser() throws Exception {

		Mockito.when(userServiceMock.saveUser(Mockito.any(UserRequestDto.class)))
				.thenReturn(new UserResponseDto());

		this.mockMvc.perform(put("/username").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(asJsonString(new UserRequestDto())))
				.andExpect(status().isNoContent());
	}

	@Test
	@DisplayName("Test to validate the update user controller")
	public void shouldReturn500ToUpdateUser() throws Exception {

		Mockito.when(userServiceMock.updateUser(Mockito.any(UserRequestDto.class)))
				.thenReturn(new UserResponseDto());

		this.mockMvc.perform(put("/username").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(asJsonString(new UserRequestDto())))
				.andExpect(status().isInternalServerError());
	}

	@Test
	@DisplayName("Test to validate the create user controller")
	public void shouldReturn204ToGetUser() throws Exception {

		Mockito.when(userServiceMock.getUser(Mockito.anyString()))
				.thenReturn(new UserResponseDto());

		this.mockMvc.perform(get("/username").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(asJsonString(new UserRequestDto())))
				.andExpect(status().isNoContent());
	}

	@Test
	@DisplayName("Test to validate the update user controller")
	public void shouldReturn500ToGetUser() throws Exception {

		Mockito.when(userServiceMock.getUser(Mockito.anyString()))
				.thenReturn(new UserResponseDto());

		this.mockMvc.perform(put("/username").contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content(asJsonString(new UserRequestDto())))
				.andExpect(status().isInternalServerError());
	}

	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
