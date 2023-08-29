package in.fssa.jauntyrialto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.jauntyrialto.entity.SubCategoryEntity;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.service.SubCategoryService;

class SubCategoryTest {

	// TEST FOR VALID INPUT TO CREATE SUBCATEGORY

	@Test
	void testSubCategoryWithValidInput() {

		SubCategoryService subCategoryService = new SubCategoryService();

		SubCategoryEntity newSubCategory = new SubCategoryEntity();
		newSubCategory.setName("WashingMachine");
		newSubCategory.setCategoryId(1);
		newSubCategory.setActive(true);

		assertDoesNotThrow(() -> {
			subCategoryService.create(newSubCategory);
		});

	}

	// TEST FOR INVALID INPUT

	@Test
	void testSubCategoryWithInvalidInput() {

		SubCategoryService subCategoryService = new SubCategoryService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			subCategoryService.create(null);
		});
		String exceptedMessage = "Invalid SubCategory input";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR SUBCATEGORYNAME WITH NULL

	@Test
	void testSubCategoryNameWithNull() {

		SubCategoryService subCategoryService = new SubCategoryService();

		SubCategoryEntity newSubCategory = new SubCategoryEntity();
		newSubCategory.setName(null);
		newSubCategory.setCategoryId(1);
		newSubCategory.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			subCategoryService.create(newSubCategory);
		});
		String exceptedMessage = "SubCategory name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR SUBCATEGORYNAME WITH EMPTY STRING

	@Test
	void testSubCategoryNameWithEmpty() {

		SubCategoryService subCategoryService = new SubCategoryService();

		SubCategoryEntity newSubCategory = new SubCategoryEntity();
		newSubCategory.setName("");
		newSubCategory.setCategoryId(1);
		newSubCategory.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			subCategoryService.create(newSubCategory);
		});
		String exceptedMessage = "SubCategory name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR SUBCATEGORY NAME WITH PATTERN (MATCH)

	@Test
	void testSubCategoryNameWithPattern() {

		SubCategoryService subCategoryService = new SubCategoryService();

		SubCategoryEntity newSubCategory = new SubCategoryEntity();
		newSubCategory.setName("12345");
		newSubCategory.setCategoryId(1);
		newSubCategory.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			subCategoryService.create(newSubCategory);
		});
		String exceptedMessage = "SubCategory name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR SUBCATEGORY NAME ALREADY EXISTS

	@Test
	void testSubCategoryIsAlredyExists() {

		SubCategoryService subCategoryService = new SubCategoryService();

		SubCategoryEntity newSubCategory = new SubCategoryEntity();
		newSubCategory.setName("MicroOvan");
		newSubCategory.setCategoryId(1);
		newSubCategory.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			subCategoryService.create(newSubCategory);
		});
		String exceptedMessage = "This SubCategory name is already exists";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR UPDATE SUBCATEGORY

	@Test
	void testUpdateSubCategory() {
		SubCategoryService subCategoryService = new SubCategoryService();

		SubCategoryEntity updatedSubCategory = new SubCategoryEntity();
		updatedSubCategory.setName("MicroOvan");
		updatedSubCategory.setCategoryId(1);
		updatedSubCategory.setActive(true);

		assertDoesNotThrow(() -> {
			subCategoryService.update(1, updatedSubCategory);
		});

	}

	// TEST FOR DELETE SUBCATEGORY

	@Test
	void testDeleteSubCategory() throws Exception {

		SubCategoryService subCategoryService = new SubCategoryService();

		subCategoryService.delete(3);

	}

	// TEST FOR CATEGORY ID WITH 0

	@Test
	void testCategoryIdWithZero() {

		SubCategoryService subCategoryService = new SubCategoryService();
		SubCategoryEntity newSubCategory = new SubCategoryEntity();

		newSubCategory.setCategoryId(0);

		Exception exception = assertThrows(ValidationException.class, () -> {
			subCategoryService.create(newSubCategory);
		});
		String exceptedMessage = "Invalid Category id";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR CATEGORY EXISTS

	@Test
	void testCategoryExists() {

		SubCategoryService subCategoryService = new SubCategoryService();
		SubCategoryEntity newSubCategory = new SubCategoryEntity();

		newSubCategory.setCategoryId(100);
		newSubCategory.setName("Micro Ovan");
		newSubCategory.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			subCategoryService.create(newSubCategory);
		});
		String exceptedMessage = "Category does not exists";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

}
