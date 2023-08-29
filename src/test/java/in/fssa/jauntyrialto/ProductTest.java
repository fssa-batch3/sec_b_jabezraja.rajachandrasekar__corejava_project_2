package in.fssa.jauntyrialto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import in.fssa.jauntyrialto.entity.ProductEntity;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.service.ProductService;

class ProductTest {

	// TEST FOR VALID INPUT TO CREATE PRODUCT

	@Test
	void testProductWithValidInput() {

		ProductService productService = new ProductService();

		ProductEntity newProduct = new ProductEntity();
		newProduct.setName("POCO M16");
		newProduct.setSubCategoryId(1);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(4500.00);
		newProduct.setActive(true);

		assertDoesNotThrow(() -> {
			productService.create(newProduct);
		});

	}

	// TEST FOR INVALID INPUT

	@Test
	void testProductWithInvalidInput() {

		ProductService productService = new ProductService();
		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(null);
		});
		String exceptedMessage = "Invalid product input";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR PRODUCTNAME WITH NULL

	@Test
	void testProductNameWithNull() {

		ProductService productService = new ProductService();

		ProductEntity newProduct = new ProductEntity();
		newProduct.setName(null);
		newProduct.setSubCategoryId(1);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(4500.00);
		newProduct.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Product name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR PRODUCT WITH EMPTY STRING

	@Test
	void testProductNameWithEmpty() {

		ProductService productService = new ProductService();

		ProductEntity newProduct = new ProductEntity();
		newProduct.setName("");
		newProduct.setSubCategoryId(1);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(4500.00);
		newProduct.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Product name cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR PRODUCT NAME WITH PATTERN
	@Test
	void testProductNameWithPattern() {

		ProductService productService = new ProductService();

		ProductEntity newProduct = new ProductEntity();
		newProduct.setName("$Hello123");
		newProduct.setSubCategoryId(1);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(4500.00);
		newProduct.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Product name doesn't match the pattern";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR PRODUCT DESCRIPTION WITH NULL
	@Test
	void testProductDescriptionWithNull() {
		ProductService productService = new ProductService();
		ProductEntity newProduct = new ProductEntity();

		newProduct.setName("POCO M16");
		newProduct.setSubCategoryId(1);
		newProduct.setDescription(null);
		newProduct.setPrice(4500.00);
		newProduct.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR PRODUCT DESCRIPTION WITH EMPTY
	@Test
	void testProductDescriptionWithEmpty() {
		ProductService productService = new ProductService();
		ProductEntity newProduct = new ProductEntity();

		newProduct.setName("POCO M16");
		newProduct.setSubCategoryId(1);
		newProduct.setDescription("");
		newProduct.setPrice(4500.00);
		newProduct.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR SUBCATEGORY ID WITH 0
	@Test
	void testSubCategoryIdIsZero() {

		ProductService productService = new ProductService();
		ProductEntity newProduct = new ProductEntity();

		newProduct.setName("POCO M16");
		newProduct.setSubCategoryId(0);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(4500.00);
		newProduct.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Invalid SubCategory_Id";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR SUBCATEGORY EXISTS

	@Test
	void testSubCategoryExists() {

		ProductService productService = new ProductService();
		ProductEntity newProduct = new ProductEntity();

		newProduct.setName("POCO M16");
		newProduct.setSubCategoryId(100);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(4500.00);
		newProduct.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "sub_category id does not exists";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR PRICE ID WITH 0

	@Test
	void testProductPriceIsZero() {

		ProductService productService = new ProductService();
		ProductEntity newProduct = new ProductEntity();

		newProduct.setName("POCO M16");
		newProduct.setSubCategoryId(1);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(0);
		newProduct.setActive(true);

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