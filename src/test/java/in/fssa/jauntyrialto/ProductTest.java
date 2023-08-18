package in.fssa.jauntyrialto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.jauntyrialto.entity.ProductEntity;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.service.ProductService;

public class ProductTest {

	/// TEST FOR VALID INPUT TO CREATE PRODUCT

	@Test
	public void testCreateProductWithValidInput() {

		ProductService productService = new ProductService();

		ProductEntity newProduct = new ProductEntity();
		newProduct.setName("POCO M16");
		newProduct.setSub_category_id(1);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(4500.00);
		newProduct.setIs_active(true);

		assertDoesNotThrow(() -> {
			productService.create(newProduct);
		});

	}

	//// TEST FOR INVALID INPUT

	@Test
	public void testCreateProductWithInvalidInput() {

		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(null);
		});
		String exceptedMessage = "Invalid product input";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR PRODUCT WITH NULL

	@Test
	public void testCreateProductWithProductNameNull() {

		ProductService productService = new ProductService();

		ProductEntity newProduct = new ProductEntity();
		newProduct.setName(null);
		newProduct.setSub_category_id(1);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(4500.00);
		newProduct.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Product name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

////TEST FOR PRODUCT WITH EMPTY STRING

	@Test
	public void testCreateProductWithProductNameEmpty() {

		ProductService productService = new ProductService();

		ProductEntity newProduct = new ProductEntity();
		newProduct.setName("");
		newProduct.setSub_category_id(1);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(4500.00);
		newProduct.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Product name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR PRODUCT NAME WITH PATTERN

	@Test
	public void testCreateProductWithPattern() {

		ProductService productService = new ProductService();

		ProductEntity newProduct = new ProductEntity();
		newProduct.setName("$Hello123");
		newProduct.setSub_category_id(1);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(4500.00);
		newProduct.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Product name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductWithDescriptionNull() {
		ProductService productService = new ProductService();
		ProductEntity newProduct = new ProductEntity();

		newProduct.setName("POCO M16");
		newProduct.setSub_category_id(1);
		newProduct.setDescription(null);
		newProduct.setPrice(4500.00);
		newProduct.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	@Test
	public void testCreateProductWithDescriptionEmpty() {
		ProductService productService = new ProductService();
		ProductEntity newProduct = new ProductEntity();

		newProduct.setName("POCO M16");
		newProduct.setSub_category_id(1);
		newProduct.setDescription("");
		newProduct.setPrice(4500.00);
		newProduct.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR SUBCATEGORY ID WITH 0

	@Test
	public void testCreateProductWithSubCategoryIdZero() {

		ProductService productService = new ProductService();
		ProductEntity newProduct = new ProductEntity();

		newProduct.setName("POCO M16");
		newProduct.setSub_category_id(0);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(4500.00);
		newProduct.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Invalid SubCategory_Id";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	//// TEST FOR SUBCATEGORY DOESNOT EXISTS

	@Test
	public void testCreateTypeWithSubCategoryDoesnotExists() {

		ProductService productService = new ProductService();
		ProductEntity newProduct = new ProductEntity();

		newProduct.setName("POCO M16");
		newProduct.setSub_category_id(100);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(4500.00);
		newProduct.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "sub_category id does not exists";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR PRICE ID WITH 0

	@Test
	public void testCreateProductWithPriceZero() {

		ProductService productService = new ProductService();
		ProductEntity newProduct = new ProductEntity();

		newProduct.setName("POCO M16");
		newProduct.setSub_category_id(1);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(0);
		newProduct.setIs_active(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Price can't be zero or negative";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

//	// TEST FOR GET ALL PRODUCTS
//
//	@Test
//	public void getAllProducts() {
//		ProductService productService = new ProductService();
//		Set<ProductEntity> productList = productService.findAllProducts();
//		System.out.println(productList);
//	}

}