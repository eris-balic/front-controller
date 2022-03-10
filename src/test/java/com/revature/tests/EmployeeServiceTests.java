package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.dao.EmployeeDao;
import com.revature.models.Employee;
import com.revature.service.EmployeeService;

public class EmployeeServiceTests {
	private EmployeeService eserv;
	private EmployeeDao mockdao;
	
	@Before
	public void setup() {
		mockdao = mock(EmployeeDao.class);
		eserv = new EmployeeService(mockdao);
	}
	@After
	public void teardown() {
		mockdao = null;
		eserv = null;
	}
	@Test
	public void testConfirmLogin_success() {
		Employee e1 = new Employee(20, "Bruce", "Banner", "thehulk", "green");
		Employee e2 = new Employee(21, "Client", "Banner", "hawkeye", "arrows");
		
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(e1);
		employees.add(e2);
		
		when(mockdao.findAll()).thenReturn(employees);
	
	Employee actual = eserv.confirmLogin("hawkeye", "arrows");
	Employee expected = e2;
	assertEquals(expected, actual);
	}
	@Test
	public void testConfirmLogin_fail() {
		assertNull(null);
}
}