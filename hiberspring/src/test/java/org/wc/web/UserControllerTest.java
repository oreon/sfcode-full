package org.wc.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wc.restful.User;
import org.wc.restful.UserList;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

@Test
public class UserControllerTest {
	private static final String REST_SERVICE_URL = "http://localhost:80/app/users";
	private RestTemplate restTemplate;

	@BeforeClass
	protected void beforeClass() {
		restTemplate = new RestTemplate();
	}

	public void create() {
		createAndAssertUser();
	}

	public void read() {
		User createdUser = createAndAssertUser();
		User user = restTemplate.getForObject(REST_SERVICE_URL + "/{userId}",
				User.class, createdUser.getId());
		assertUser(user, createdUser);
	}

	public void update() {
		User user = createAndAssertUser();
		user.setName("Updated user name");
		restTemplate.put(REST_SERVICE_URL + "/{userId}", user, user.getId());
		User updatedUser = restTemplate.getForObject(REST_SERVICE_URL
				+ "/{userId}", User.class, user.getId());
		assertUser(updatedUser, user);
	}

	public void updateIncorrectUrl() {
		User user = createAndAssertUser();
		user.setName("Updated user name");
		try {
			restTemplate.put(REST_SERVICE_URL + "/{userId}", user,
					user.getId() + 1);
			fail("Expecting HttpClientErrorException: 400 Bad Request");
		} catch (HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.BAD_REQUEST);
		}
	}

	public void delete() {
		User createdUser = createAndAssertUser();
		restTemplate
				.delete(REST_SERVICE_URL + "/{userId}", createdUser.getId());
		try {
			restTemplate.getForObject(REST_SERVICE_URL + "/{userId}",
					User.class, createdUser.getId());
			fail("Expecting HttpClientErrorException: 400 Bad Request");
		} catch (HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.BAD_REQUEST);
		}
	}

	public void list() {
		UserList initialUsers = restTemplate.getForObject(REST_SERVICE_URL,
				UserList.class);
		User createdUser = createAndAssertUser();
		UserList users = restTemplate.getForObject(REST_SERVICE_URL,
				UserList.class);
		List<User> createdUsers = new ArrayList<User>(users.getUsers());
		createdUsers.removeAll(initialUsers.getUsers());
		assertEquals(createdUsers.size(), 1);
		assertUser(createdUsers.get(0), createdUser);
	}

	private User createAndAssertUser() {
		User user = new User();
		user.setId(0);
		user.setName("User name");
		user.setRegistrationDate(new Date());
		return createAndAssertUser(user);
	}

	private User createAndAssertUser(User user) {
		User createdUser = restTemplate.postForObject(REST_SERVICE_URL, user,
				User.class);
		assertUserNoId(createdUser, user);
		return createdUser;
	}

	private void assertUserNoId(User actual, User expected) {
		assertTrue(actual.getId() > 0);
		assertEquals(actual.getName(), expected.getName());
		assertEquals(actual.getRegistrationDate(), expected
				.getRegistrationDate());
	}

	private void assertUser(User actual, User expected) {
		assertTrue(actual.getId() > 0);
		assertEquals(actual.getName(), expected.getName());
		assertEquals(actual.getRegistrationDate(), expected
				.getRegistrationDate());
	}
}
