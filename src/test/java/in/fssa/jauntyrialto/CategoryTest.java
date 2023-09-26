package in.fssa.jauntyrialto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.jauntyrialto.entity.CategoryEntity;
import in.fssa.jauntyrialto.exception.ServiceException;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.service.CategoryService;

class CategoryTest {

	// GENERATE AUTOMATIC STRING FOR EMAIL
	private String generateRandomString(int length) {
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder randomString = new StringBuilder();
		java.util.Random random = new java.util.Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(characters.length());
			randomString.append(characters.charAt(index));
		}
		return randomString.toString();
	}

	// TEST FOR VALID INPUT
	@Test
	void testCategoryWithValidInput() {

		CategoryService categoryService = new CategoryService();
		CategoryEntity newCategory = new CategoryEntity();

		String random = generateRandomString(7);
		newCategory.setName(random);
		newCategory.setIsActive(true);

		assertDoesNotThrow(() -> {
			categoryService.create(newCategory);
		});

	}

	// TEST FOR INVALID INPUT

	@Test
	void testCategoryWithInvalidInput() {

		CategoryService categoryService = new CategoryService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.create(null);
		});
		String exceptedMessage = "Invalid category input";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}

	// TEST FOR CATEGORYNAME WITH NULL

	@Test
	void testCategoryNameWithNull() {

		CategoryService categoryService = new CategoryService();

		CategoryEntity newCategory = new CategoryEntity();
		newCategory.setName(null);
		newCategory.setIsActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.create(newCategory);
		});
		String exceptedMessage = "Category name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}

	// TEST FOR CATEGORYNAME WITH EMPTY STRING

	@Test
	void testCategoryNameWithEmpty() {

		CategoryService categoryService = new CategoryService();

		CategoryEntity newCategory = new CategoryEntity();
		newCategory.setName("");
		newCategory.setIsActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.create(newCategory);
		});
		String exceptedMessage = "Category name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}

	// TEST FOR CATEGORY NAME WITH PATTERN

	@Test
	void testCategoryNameWithPattern() {

		CategoryService categoryService = new CategoryService();

		CategoryEntity newCategory = new CategoryEntity();
		newCategory.setName("12345");
		newCategory.setIsActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.create(newCategory);
		});
		String exceptedMessage = "Category name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}

	// TEST FOR CATEGORY ALREADY EXISTS

	@Test
	void testCategoryAlredyExists() {

		CategoryService categoryService = new CategoryService();

		CategoryEntity newCategory = new CategoryEntity();
		newCategory.setName("Electronics");
		newCategory.setIsActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.create(newCategory);
		});
		String exceptedMessage = "This category name is already exists";
		String actualMessage = exception.getMessage();

		assertEquals(exceptedMessage, actualMessage);
	}

	// TEST FOR UPDATE CATEGORY

	@Test
	void testUpdateCategory() {
		CategoryService categoryService = new CategoryService();
		CategoryEntity updatedCategory = new CategoryEntity();

		String random = generateRandomString(7);
		updatedCategory.setName(random);
		updatedCategory.setIsActive(true);

		assertDoesNotThrow(() -> {
			categoryService.update(3, updatedCategory);
		});

	}

	// TEST FOR DELETE CATEGORY

	@Test
	void deleteCategory() throws Exception {

		CategoryService categoryService = new CategoryService();
		categoryService.delete(4);

	}

	// TEST FOR GET ALL CATEGORIES

	@Test
	void getAllCategories() throws ServiceException {
		CategoryService service = new CategoryService();
		assertDoesNotThrow(() -> {
			@SuppressWarnings("static-access")
			Set<CategoryEntity> List = service.findAll();
			System.out.println(List);
		});
	}
}
