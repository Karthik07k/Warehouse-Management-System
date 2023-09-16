package in.nareshit.raghu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource("classpath:application-test.properties")
public class WarehouseApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("TEST UOM # SAVE OPERATION")
	@Order(1)
	@Disabled
	public void testUomSave() throws Exception {

		//a. Create one Http Request using Mock
		MockHttpServletRequestBuilder request =
				MockMvcRequestBuilders
				.post("/rest/api/uom/create")
				.contentType(MediaType.APPLICATION_JSON)
				//.header("Content-Type", "application/json")
				.content("{\"uomType\":\"PACKING\",\"uomModel\": \"TESTA\", \"uomDesc\": \"SAMPLE ONE A\"}")
				;


		//b. Execute Request and Get Result using mockMvc (Environment)
		MvcResult result = mockMvc.perform(request).andReturn();

		//c. Read Response from Result
		MockHttpServletResponse response = result.getResponse();

		//d. Validate/Assert Response using JUnit API
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		if(!response.getContentAsString().contains("created")) {
			fail("Uom is not created!!");
		}
	}


	//2. Uom Delete --success case
	@Test
	@DisplayName("TEST UOM # DELETE OPERATION")
	@Order(2)
	@Disabled
	public void testUomDeleteSuccess() throws Exception {

		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders
				.delete("/rest/api/uom/remove/{id}",102);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		if(!response.getContentAsString().contains("Deleted")) {
			fail("Uom is not Deleted!!");
		}

	}

	//2. Uom Delete --fail case
	@Test
	@DisplayName("TEST UOM # DELETE OPERATION- NOT EXIST")
	@Order(3)
	@Disabled
	public void testUomDeleteFail() throws Exception {

		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders
				.delete("/rest/api/uom/remove/{id}",996);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
		if(!response.getContentAsString().contains("Not exist")) {
			fail("Uom is Exist it seem!!");
		}

	}

	@Test
	@DisplayName("TEST UOM GET ALL OPERTATION ")
	@Order(4)
	@Disabled
	public void testUomGetAll() throws Exception {
		//a. Create one Http Request using Mock
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders.get("/rest/api/uom/all");

		//b. Execute Request and Get Result using mockMvc (Environment)
		MvcResult result = mockMvc.perform(request).andReturn();

		//c. Read Response from Result
		MockHttpServletResponse response = result.getResponse();

		//d. Validate/Assert Response using JUnit API
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
	}
	
	
	@Test
	@DisplayName("TEST UOM GET ONE OPERTATION ")
	@Order(5)
	public void testUomGetOneSuccess() throws Exception {
		//a. Create one Http Request using Mock
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders.get("/rest/api/uom/fetch/{id}",52);

		//b. Execute Request and Get Result using mockMvc (Environment)
		MvcResult result = mockMvc.perform(request).andReturn();

		//c. Read Response from Result
		MockHttpServletResponse response = result.getResponse();

		//d. Validate/Assert Response using JUnit API
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
	}
	
	
	@Test
	@DisplayName("TEST UOM GET ONE OPERTATION FAIL")
	@Order(6)
	public void testUomGetOneFail() throws Exception {
		//a. Create one Http Request using Mock
		MockHttpServletRequestBuilder request = 
				MockMvcRequestBuilders.get("/rest/api/uom/fetch/{id}",502);

		//b. Execute Request and Get Result using mockMvc (Environment)
		MvcResult result = mockMvc.perform(request).andReturn();

		//c. Read Response from Result
		MockHttpServletResponse response = result.getResponse();

		//d. Validate/Assert Response using JUnit API
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
		assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
		if(!response.getContentAsString().contains("Not exist")) {
			fail("Given record may be exist");
		}
	}

	
	@Test
	@DisplayName("TEST UOM # UPDATE OPERATION")
	@Order(7)
	public void testUomUpdate() throws Exception {

		//a. Create one Http Request using Mock
		MockHttpServletRequestBuilder request =
				MockMvcRequestBuilders
				.put("/rest/api/uom/modify")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\": 52, \"uomType\":\"PACKING\",\"uomModel\": \"TESTA\", \"uomDesc\": \"SAMPLE ONE A\"}")
				;

		//b. Execute Request and Get Result using mockMvc (Environment)
		MvcResult result = mockMvc.perform(request).andReturn();

		//c. Read Response from Result
		MockHttpServletResponse response = result.getResponse();

		//d. Validate/Assert Response using JUnit API
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		if(!response.getContentAsString().contains("updated")) {
			fail("Uom update is failed!!");
		}
	}

}
