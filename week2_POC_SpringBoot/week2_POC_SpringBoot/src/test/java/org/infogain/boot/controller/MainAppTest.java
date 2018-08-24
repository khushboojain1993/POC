package org.infogain.boot.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.infogain.boot.model.EmpAddress;
import org.infogain.boot.model.Employee;
import org.infogain.boot.services.EmployeeService;
import org.infogain.boot.services.IEmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = MainAppTest.class, secure = false)
public class MainAppTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wc;
	
	@InjectMocks
	MainApp mainApp;
	
	Employee mockemp=new Employee();
	EmpAddress mockempaddress=new EmpAddress();
	List<Employee> mockemployeelist = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception
	{
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wc).build();
		
		
		System.out.println("Testing");
		
		mockempaddress.setHouseNumber(110);
		mockempaddress.setCity("delhi");
		mockempaddress.setState("delhi");
		mockempaddress.setCountry("india");
		
		mockemp.setEmpId("100");
		mockemp.setEmpName("Kj");
		mockemp.setSalary(1000.0f);
		mockemp.setEmpaddress(mockempaddress);
		
		mockemployeelist.add(mockemp);
		
		System.out.println("Testing Done");
		
	}

	@MockBean
	public EmployeeService employeeService;
	
	
	
	/*@Test
	public void testGetAllEmployees() throws Exception
	{
		//Mockito.when(employeeService.getAllEmployees()).thenReturn(mockemployeelist);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/all").contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn(); 
		System.out.println("Test :: "+result.getResponse().getContentAsString());
		String expected = "[empId:100,empName:Kj,salary:1000.0,Address:[city: delhi,state: delhi,country: india,houseNumber: 110]]";
		List<String> expectedlist =  Arrays.asList(expected); 
		assertEquals(expectedlist, result.getResponse().getContentAsString());
		assertEquals(expected, result.getResponse().getContentAsString());

	}*/
	
	String exampleemp = "[{\"empId\":\"100\",\"empName\":\"Kj\",\"salary\":1000, \"empaddress\":[\"city\":\"delhi\",\"state\":\"delhi\",\"country\",\"india\"]}]";
	@Test
	public void testGetEmployeeById() throws Exception 
	{
		Mockito.when(employeeService.getEmployeeById(Mockito.anyString())).thenReturn(mockemp);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/100")
				.requestAttr("empId", mockemp.getEmpId())
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn(); 
		System.out.println(result.getResponse());
		
		//String expectedaddress="{city: delhi,state: delhi,country: india,houseNumber: 110}";
		//String expected = "{empId:100,empName:Kj,salary:1000.0,empaddress:[{city: delhi,state: delhi,country: india,houseNumber: 110}]}";
		
		String expected= "{\"empId\":\"100\",\"empName\":\"Kj\",\"salary\":1000, \"empaddress\":[\"city\":\"delhi\",\"state\":\"delhi\",\"country\":\"india\",\"houseNumber\":110]}";

		assertEquals(expected, result.getResponse().getContentAsString());

		System.out.println("implemented");
	
	}



}
