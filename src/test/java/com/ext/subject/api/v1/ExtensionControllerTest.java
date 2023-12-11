package com.ext.subject.api.v1;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import com.ext.subject.dto.ExtensionDto.PostFixedReqDto;
import com.ext.subject.service.ExtensionService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ExtensionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ExtensionService extensionService;

	private String requestBody;

	@BeforeEach
	public void setUpRequestBody() {
		requestBody = "{ \"extName\": \"notexs\" }";
	}

	@Test
	public void GET_고정_확장자() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api-v1/excl/ext-fixed-list")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());
	}

	@Test
	public void GET_커스텀_확장자() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api-v1/excl/ext-custom-list")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").isArray())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());
	}

	@Test
	public void POST_커스텀_확장자_파라미터_예외() throws Exception {
		String requestBody = "{ \"extNam\": \"dd\" }";
		mockMvc.perform(MockMvcRequestBuilders.post("/api-v1/ext-custom")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());
	}

	@Test
	public void POST_커스텀_확장자_특수문자_예외() throws Exception {
		String requestBody = "{ \"extName\": \"_\" }";
		mockMvc.perform(MockMvcRequestBuilders.post("/api-v1/ext-custom")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());
	}

	@Test
	public void POST_커스텀_확장자_숫자_예외() throws Exception {
		String requestBody = "{ \"extName\": \"2\" }";
		mockMvc.perform(MockMvcRequestBuilders.post("/api-v1/ext-custom")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());
	}

	@Test
	public void POST_커스텀_확장자_한글_예외() throws Exception {
		String requestBody = "{ \"extName\": \"ㅇ\" }";
		mockMvc.perform(MockMvcRequestBuilders.post("/api-v1/ext-custom")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());
	}

	@Test
	public void POST_커스텀_확장자_공백_예외() throws Exception {
		String requestBody = "{ \"extName\": \"\" }";
		mockMvc.perform(MockMvcRequestBuilders.post("/api-v1/ext-custom")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());
	}

	@Test
	public void POST_커스텀_확장자_중복_예외() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api-v1/ext-custom")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").doesNotExist())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());



		mockMvc.perform(MockMvcRequestBuilders.post("/api-v1/ext-custom")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());
	}

	@Test
	public void DELETE_커스텀_확장자_존재하지않는_예외() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api-v1/ext-custom")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());
	}

	@Test
	public void DELETE_커스텀_확장자_존재() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.post("/api-v1/ext-custom")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").doesNotExist())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());

		mockMvc.perform(MockMvcRequestBuilders.delete("/api-v1/ext-custom")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").doesNotExist())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());
	}

	@Test
	public void PATCH_고정_확장자_존재하지않을때() throws Exception {
		String requestBody = "{ \"extName\": \"d\", \"isActivate\": \"true\"}";
		mockMvc.perform(MockMvcRequestBuilders.patch("/api-v1/ext-fixed")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());
	}

	@Test
	public void PATCH_고정_확장자_존재_변경_성공() throws Exception {
		List<PostFixedReqDto> list = new ArrayList<>();
		list.add(new PostFixedReqDto("d"));
		extensionService.createInitFixedList(list);


		String requestBody = "{ \"extName\": \"d\", \"isActivate\": \"false\"}";
		mockMvc.perform(MockMvcRequestBuilders.patch("/api-v1/ext-fixed")
				.content(requestBody)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$.status").isString())
			.andExpect(MockMvcResultMatchers.jsonPath("$.data").doesNotExist())
			.andExpect(MockMvcResultMatchers.jsonPath("$.message").doesNotExist());
	}
}