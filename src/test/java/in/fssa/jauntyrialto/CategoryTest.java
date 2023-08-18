package in.fssa.jauntyrialto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.jauntyrialto.entity.CategoryEntity;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.service.CategoryService;

public class CategoryTest {
	/// TEST FOR VALID INPUT TO CREATE CATEGORY

	@Test
	public void testCreateCategoryWithValidInput() {

		CategoryService categoryService = new CategoryService();

		CategoryEntity newCategory = new CategoryEntity();
		newCategory.setName("Accessories");
		newCategory.setIs_active(true);

		assertDoesNotThrow(() -> {
			categoryService.create(newCategory);
		});

	}

	//// TEST FOR INVALID INPUT

	@Test
	public void testCreateCategoryWithInvalidInput() {

		CategoryService categoryService = new CategoryService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.create(null);
		});
		String exceptedMessage = "Invalid category input";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR CATEGORY WITH NULL

	@Test
	public void testCreateCategoryWithCategoryNameNull() {

		CategoryService categoryService = new CategoryService();

		CategoryEntity newCategory = new CategoryEntity();
		newCategory.setName(null);
		newCategory.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.create(newCategory);
		});
		String exceptedMessage = "Category name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR CATEGORY WITH EMPTY STRING

	@Test
	public void testCreateCategoryWithCategoryNameEmpty() {

		CategoryService categoryService = new CategoryService();

		CategoryEntity newCategory = new CategoryEntity();
		newCategory.setName("");
		newCategory.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.create(newCategory);
		});
		String exceptedMessage = "Category name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR CATEGORY NAME WITH PATTERN

	@Test
	public void testCreateCategoryWithPattern() {

		CategoryService categoryService = new CategoryService();

		CategoryEntity newCategory = new CategoryEntity();
		newCategory.setName("12345");
		newCategory.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.create(newCategory);
		});
		String exceptedMessage = "Category name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR CATEGORY NAME ALREADY EXISTS

	@Test
	public void testCreateCategoryWithCategoryNameAlredyExists() {

		CategoryService categoryService = new CategoryService();

		CategoryEntity newCategory = new CategoryEntity();
		newCategory.setName("Electronics");
		newCategory.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			categoryService.create(newCategory);
		});
		String exceptedMessage = "This category name is already exists";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR UPDATE CATEGORY

	@Test
	public void testUpdateCategory() {
		CategoryService categoryService = new CategoryService();

		CategoryEntity updatedCategory = new CategoryEntity();
		updatedCategory.setName("Electricals");
		updatedCategory.setIs_active(true);

		assertDoesNotThrow(() -> {
			categoryService.update(3, updatedCategory);
		});

	}

	//// TEST FOR DELETE CATEGORY

	@Test
	public void deleteCategory() throws Exception {

		CategoryService categoryService = new CategoryService();
		categoryService.delete(4);

	}
}
