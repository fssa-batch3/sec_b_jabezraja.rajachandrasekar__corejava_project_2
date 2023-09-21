package in.fssa.jauntyrialto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.jauntyrialto.entity.UserEntity;
import in.fssa.jauntyrialto.exception.ServiceException;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.service.UserService;

class UserTest {
	//// GENERATE AUTOMATIC STRING FOR EMAIL

	private String generateRandomString(int length) {
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder randomString = new StringBuilder();
		java.util.Random random = new java.util.Random();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			randomString.append(characters.charAt(index));
		}

		return randomString.toString();
	}

	//// GENERATE AUTOMATIC LONG FOR PHONE NUMBER

	private long generateRandomPhoneNumber(int length) {
		java.util.Random random = new java.util.Random();

		long minPhoneNumber = 600_000_0001L;
		long maxPhoneNumber = 999_999_9999L;

		long phoneNumber = minPhoneNumber + (long) (random.nextDouble() * (maxPhoneNumber - minPhoneNumber + 1));

		return phoneNumber;
	}

	@Test
	void testCreateUserWithValidInput() {
		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();

		newUser.setName("joe Putin");

		String randomString = generateRandomString(8);
		newUser.setEmail(randomString + "@gmail.com");

		long randomPhoneNumber = generateRandomPhoneNumber(10);
		newUser.setPhone(randomPhoneNumber);

		newUser.setPassword("asdfA@12!");
		newUser.setConfirmPassword("asdfA@12!");

		assertDoesNotThrow(() -> {
			userService.create(newUser);
		});
	}

	//// TEST FOR INVALID INPUT

	@Test
	void testCreateUserWithInvalidInput() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(null);
		});
		String exceptedMessage = "User Cannot be null";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}

	//// TEST FOR EMAIL WITH NULL

	@Test
	void testCreateUserWithEmailNull() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();
		newUser.setName("Asif");
		newUser.setEmail(null);
		newUser.setPhone(7908946112l);
		newUser.setPassword("asdfA@12!");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String exceptedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}

	//// TEST FOR EMAIL WITH EMPTY STRING

	@Test
	void testCreateUserWithEmailEmpty() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();
		newUser.setName("Asif");
		newUser.setEmail("");
		newUser.setPhone(7908946112l);
		newUser.setPassword("asdfA@12!");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String exceptedMessage = "Email cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}

	//// TEST FOR EMAIL WITH PATTERN
	@Test
	void testCreateUserWithEmailPattern() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();
		newUser.setName("Asif");
		newUser.setEmail("kepler123gmail.com");
		newUser.setPhone(7908946112l);
		newUser.setPassword("asdfA@12!");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String exceptedMessage = "Invalid Email Id";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}

	//// ??????????? TEST FOR EMAIL WITH ALREADY EXISTS ???????????

	@Test
	void testCreateUserWithEmailExists() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();
		newUser.setName("king");
		newUser.setEmail("kingjjj777@gmail.com");
		newUser.setPhone(7980946112l);
		newUser.setPassword("asdfA@12!");
		newUser.setConfirmPassword("asdfA@12!");

		Exception exception = assertThrows(ServiceException.class, () -> {
			userService.create(newUser);
		});
		String exceptedMessage = "User already exist";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}

	/// TEST FOR PASSWORD WITH NULL

	@Test
	void testCreateUserWithPasswordNull() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();

		newUser.setName("king");
		newUser.setEmail("king777@gmail.com");
		newUser.setPhone(7689946112l);
		newUser.setPassword(null);

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	//// TEST FOR PASSWORD WITH EMPTY STRING

	@Test
	void testCreateUserWithPasswordEmpty() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();

		newUser.setName("king");
		newUser.setEmail("king777@gmail.com");
		newUser.setPhone(7689946112l);
		newUser.setPassword("");

		Exception exception = assertThrows(Exception.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Password cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	//// TEST FOR PASSWORD WITH PASSWORD LENGTH

	@Test
	void testCreateUserWithPasswordLength() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();

		newUser.setName("king");
		newUser.setEmail("king777@gmail.com");
		newUser.setPhone(7689946112l);
		newUser.setPassword("king34!");

		Exception exception = assertThrows(Exception.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Password does not match the requested pattern";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	//// TEST FOR PASSWORD WITH PATTERN

	@Test
	void testCreateUserWithPasswordPattern() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();

		newUser.setName("king");
		newUser.setEmail("king777@gmail.com");
		newUser.setPhone(7689946112l);
		newUser.setPassword("HELLO12334");

		Exception exception = assertThrows(Exception.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Password does not match the requested pattern";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	//// TEST FOR NAME WITH NULL

	@Test
	void testCreateUserWithNameNull() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();

		newUser.setName(null);
		newUser.setEmail("king777@gmail.com");
		newUser.setPhone(7909946112l);
		newUser.setPassword("Legend@12");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	//// TEST FOR NAME WITH EMPTY STRING

	@Test
	void testCreateUserWithNameEmpty() {
		UserService userService = new UserService();
		UserEntity newUser = new UserEntity();

		newUser.setName("");
		newUser.setEmail("king777@gmail.com");
		newUser.setPhone(7909946112l);
		newUser.setPassword("Legend@12");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	//// TEST FOR NAME WITH PATTERN

	@Test
	void testCreateUserWithNamePattern() {
		UserService userService = new UserService();
		UserEntity newUser = new UserEntity();

		newUser.setName("3456");
		newUser.setEmail("king777@gmail.com");
		newUser.setPhone(7909946112l);
		newUser.setPassword("Legend@12");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Name must contain only alphabets with minimum 3 letters can have characters like(',-) with a single space and followed by letters";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	//// TEST FOR PHONE NUMBER WITH 0

	@Test
	void testCreateUserWithPhoneNumber() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();

		newUser.setName("Raja");
		newUser.setEmail("king777@gmail.com");
		newUser.setPhone(0);
		newUser.setPassword("Legend@12");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Phone Number must start between 6 - 9 and have total of 10 digits";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);

	}

	//// TEST FOR PHONE NUMBER WITH LENGHT

	@Test
	void testCreateUserWithPhoneNumberLength() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();

		newUser.setName("Raja");
		newUser.setEmail("king777@gmail.com");
		newUser.setPhone(789087857650l);
		newUser.setPassword("Legend@12");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Phone Number must start between 6 - 9 and have total of 10 digits";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);

	}

	//// TEST FOR PHONE NUMBER WITH PATTERN

	@Test
	void testCreateUserWithInvalidPhoneNumber() {

		UserService userService = new UserService();

		UserEntity newUser = new UserEntity();

		newUser.setName("Raja");
		newUser.setEmail("king777@gmail.com");
		newUser.setPhone(4087857650l);
		newUser.setPassword("Legend@12");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.create(newUser);
		});
		String expectedMessage = "Phone Number must start between 6 - 9 and have total of 10 digits";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);

	}

////TEST FOR UPDATE USER

	@Test
	void testUpdateUser() {

		UserService userService = new UserService();

		UserEntity updateUser = new UserEntity();

		updateUser.setName("Royal");
		updateUser.setEmail("VqMhLfeG@gmail.com");
		updateUser.setPassword("asdfA@12!");
		long randomPhoneNumber = generateRandomPhoneNumber(10);
		updateUser.setPhone(randomPhoneNumber);

		assertDoesNotThrow(() -> {
			userService.update(10, updateUser);
		});

	}

	@Test
	void testUpdateUserWithZero() {

		UserService userService = new UserService();

		UserEntity updateUser = new UserEntity();

		updateUser.setName("Ramana");
		updateUser.setPassword("Ramana#08");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.update(0, updateUser);
		});
		String expectedMessage = "Id cannot be zero or below zero";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage, actualMessage);

	}

	@Test
	void testUpdateUserWithInvalidId() {

		UserService userService = new UserService();

		UserEntity updateUser = new UserEntity();

		updateUser.setName("Ramana");
		updateUser.setPassword("Ramana#08");

		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.update(8, updateUser);
		});
		String expectedMessage = "User not found";
		String actualMessage = exception.getMessage();
		System.out.println(actualMessage);

		assertEquals(expectedMessage, actualMessage);

	}

	//// TEST FOR DELETE USER

	@Test
	void deleteUser() throws ServiceException, ValidationException {

		UserService userService = new UserService();
		assertDoesNotThrow(() -> {
			userService.delete(8);
		});

	}

	// Test Longin With Valid Input
	@Test
	void testLoginUserWithValidInput() {

		UserService userService = new UserService();
		UserEntity user = new UserEntity();
		user.setEmail("PTl0LGNB@gmail.com");
		user.setPassword("asdfA@12!");
		try {
			assertTrue(userService.userLogin(user));
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

//	// Password null check
//	@Test
//	void testLoginUserWithPasswordNull() {
//		UserService userService = new UserService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			userService.userLogin("kingjjj777@gmail.com", null);
//		});
//
//		String expectedMessage = "Password cannot be null or empty";
//		String actualMessage = exception.getMessage();
//		assertEquals(expectedMessage, actualMessage);
//	}

//	// Password is Empty
//	@Test
//	void testLoginUserWithPasswordEmpty() {
//
//		UserService userService = new UserService();
//
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			userService.userLogin("kingjjj777@gmail.com", "");
//		});
//
//		String expectedMessage = "Password cannot be null or empty";
//		String actualMessage = exception.getMessage();
//		assertEquals(expectedMessage, actualMessage);
//	}

//	// Password Pattern check
//	@Test
//	void testLoginUserWithPasswordInValidPattern() {
//
//		UserService userService = new UserService();
//		Exception exception = assertThrows(ValidationException.class, () -> {
//			userService.userLogin("kingjjj777@gmail.com", "king1234");
//		});
//		String expectedMessage = "Password does not match the requested pattern";
//		String actualMessage = exception.getMessage();
//		assertEquals(expectedMessage, actualMessage);
//
//	}

//	// Invalid login credentials
//	@Test
//	void testLoginUserWithInvalidLoginCredentails() {
//		UserService userService = new UserService();
//
//		Exception exception = assertThrows(ServiceException.class, () -> {
//			userService.userLogin("kingjjj777@gmail.com", "King5465");
//		});
//
//		String expectedMessage = "Invalid Login Credentials";
//		String actualMessage = exception.getMessage();
//		assertEquals(expectedMessage, actualMessage);
//	}

	// Id is Invalid
	@Test
	void testDeleteUserWithInvalidId() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.delete(0);
		});

		String expectedMessage = "Id cannot be zero or below zero";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	// Id Not Found
	@Test
	void testDeleteUserWithNotFoudId() {

		UserService userService = new UserService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			userService.delete(55);
		});

		String expectedMessage = "User not found";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	// TEST FOR GET ALL USERS

	@Test
	void getAllUsers() throws ServiceException {
		UserService userService = new UserService();
		assertDoesNotThrow(() -> {
			Set<UserEntity> userList = userService.findAll();
			System.out.println(userList);
		});
	}

	// TEST FOR GET USER BY ID

	@Test
	void getUserById() throws ServiceException {
		UserService userService = new UserService();
		assertDoesNotThrow(() -> {
			UserEntity userList = userService.findById(4);
			System.out.println(userList);
		});
	}

	// TEST FOR GET USER BY EMAIL
	@Test
	void getUserByEmail() throws ServiceException {
		UserService userService = new UserService();
		assertDoesNotThrow(() -> {
			UserEntity userList = userService.findUserByEmail("MTw3TBdh@gmail.com");
			System.out.println(userList);
		});
	}
}
