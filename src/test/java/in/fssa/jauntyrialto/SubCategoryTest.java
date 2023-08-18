package in.fssa.jauntyrialto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.jauntyrialto.entity.SubCategoryEntity;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.service.SubCategoryService;

public class SubCategoryTest {

	/// TEST FOR VALID INPUT TO CREATE SUBCATEGORY

	@Test
	public void testCreateSubCategoryWithValidInput() {

		SubCategoryService subCategoryService = new SubCategoryService();

		SubCategoryEntity newSubCategory = new SubCategoryEntity();
		newSubCategory.setName("WashingMachine");
		newSubCategory.setCategory_id(1);
		newSubCategory.setIs_active(true);

		assertDoesNotThrow(() -> {
			subCategoryService.create(newSubCategory);
		});

	}

	//// TEST FOR INVALID INPUT

	@Test
	public void testCreateSubCategoryWithInvalidInput() {

		SubCategoryService subCategoryService = new SubCategoryService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			subCategoryService.create(null);
		});
		String exceptedMessage = "Invalid SubCategory input";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR SUBCATEGORY WITH NULL

	@Test
	public void testCreateSubCategoryWithSubCategoryNameNull() {

		SubCategoryService subCategoryService = new SubCategoryService();

		SubCategoryEntity newSubCategory = new SubCategoryEntity();
		newSubCategory.setName(null);
		newSubCategory.setCategory_id(1);
		newSubCategory.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			subCategoryService.create(newSubCategory);
		});
		String exceptedMessage = "SubCategory name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR SUBCATEGORY WITH EMPTY STRING

	@Test
	public void testCreateSubCategoryWithSubCategoryNameEmpty() {

		SubCategoryService subCategoryService = new SubCategoryService();

		SubCategoryEntity newSubCategory = new SubCategoryEntity();
		newSubCategory.setName("");
		newSubCategory.setCategory_id(1);
		newSubCategory.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			subCategoryService.create(newSubCategory);
		});
		String exceptedMessage = "SubCategory name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR SUBCATEGORY NAME WITH PATTERN

	@Test
	public void testCreateSubCategoryWithPattern() {

		SubCategoryService subCategoryService = new SubCategoryService();

		SubCategoryEntity newSubCategory = new SubCategoryEntity();
		newSubCategory.setName("12345");
		newSubCategory.setCategory_id(1);
		newSubCategory.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			subCategoryService.create(newSubCategory);
		});
		String exceptedMessage = "SubCategory name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR SUBCATEGORY NAME ALREADY EXISTS

	@Test
	public void testCreateSubCategoryWithSubCategoryNameAlredyExists() {

		SubCategoryService subCategoryService = new SubCategoryService();

		SubCategoryEntity newSubCategory = new SubCategoryEntity();
		newSubCategory.setName("Monitors");
		newSubCategory.setCategory_id(1);
		newSubCategory.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			subCategoryService.create(newSubCategory);
		});
		String exceptedMessage = "This SubCategory name is already exists";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR UPDATE SUBCATEGORY

	@Test
	public void testUpdateSubCategory() {
		SubCategoryService subCategoryService = new SubCategoryService();

		SubCategoryEntity updatedSubCategory = new SubCategoryEntity();
		updatedSubCategory.setName("MicroOvan");
		updatedSubCategory.setCategory_id(1);
		updatedSubCategory.setIs_active(true);

		assertDoesNotThrow(() -> {
			subCategoryService.update(1, updatedSubCategory);
		});

	}

	//// TEST FOR DELETE SUBCATEGORY

	@Test
	public void deleteSubCategory() throws Exception {

		SubCategoryService subCategoryService = new SubCategoryService();

		subCategoryService.delete(3);

	}

	// TEST FOR CATEGORY ID WITH 0

	@Test
	public void testCreateProductWithCategoryIdZero() {

		SubCategoryService subCategoryService = new SubCategoryService();
		SubCategoryEntity newSubCategory = new SubCategoryEntity();

		newSubCategory.setCategory_id(0);

		Exception exception = assertThrows(ValidationException.class, () -> {
			subCategoryService.create(newSubCategory);
		});
		String exceptedMessage = "Invalid Category id";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR CATEGORY DOESNOT EXISTS

	@Test
	public void testCreateTypeWithCategoryDoesnotExists() {

		SubCategoryService subCategoryService = new SubCategoryService();
		SubCategoryEntity newSubCategory = new SubCategoryEntity();

		newSubCategory.setCategory_id(100);
		newSubCategory.setName("Micro Ovan");
		newSubCategory.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			subCategoryService.create(newSubCategory);
		});
		String exceptedMessage = "Category does not exists";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

}
