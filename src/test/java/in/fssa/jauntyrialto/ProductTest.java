package in.fssa.jauntyrialto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.jauntyrialto.entity.ProductEntity;
import in.fssa.jauntyrialto.exception.ServiceException;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.service.ProductService;

class ProductTest {

////GENERATE AUTOMATIC STRING FOR EMAIL

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

	// TEST FOR VALID INPUT TO CREATE PRODUCT

	@Test
	void testProductWithValidInput() {

		ProductService productService = new ProductService();
		ProductEntity newProduct = new ProductEntity();

		String random = generateRandomString(7);
		newProduct.setName(random);
		newProduct.setSubCategoryId(1);
		newProduct.setDescription("The most high tech");
		newProduct.setPrice(4500.00);
		newProduct.setMainImg("https://iili.io/HUm4pSe.jpg");
		newProduct.setSubImg1("https://iili.io/HUm4ml9.jpg");
		newProduct.setSubImg2("https://iili.io/HUm6dDx.jpg");
		newProduct.setSubImg3("https://iili.io/HUm4bK7.jpg");
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
		newProduct.setMainImg("https://iili.io/HUm4pSe.jpg");
		newProduct.setSubImg1("https://iili.io/HUm4ml9.jpg");
		newProduct.setSubImg2("https://iili.io/HUm6dDx.jpg");
		newProduct.setSubImg3("https://iili.io/HUm4bK7.jpg");
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
		newProduct.setMainImg("https://iili.io/HUm4pSe.jpg");
		newProduct.setSubImg1("https://iili.io/HUm4ml9.jpg");
		newProduct.setSubImg2("https://iili.io/HUm6dDx.jpg");
		newProduct.setSubImg3("https://iili.io/HUm4bK7.jpg");
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
		newProduct.setMainImg("https://iili.io/HUm4pSe.jpg");
		newProduct.setSubImg1("https://iili.io/HUm4ml9.jpg");
		newProduct.setSubImg2("https://iili.io/HUm6dDx.jpg");
		newProduct.setSubImg3("https://iili.io/HUm4bK7.jpg");
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
		newProduct.setMainImg("https://iili.io/HUm4pSe.jpg");
		newProduct.setSubImg1("https://iili.io/HUm4ml9.jpg");
		newProduct.setSubImg2("https://iili.io/HUm6dDx.jpg");
		newProduct.setSubImg3("https://iili.io/HUm4bK7.jpg");
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
		newProduct.setMainImg("https://iili.io/HUm4pSe.jpg");
		newProduct.setSubImg1("https://iili.io/HUm4ml9.jpg");
		newProduct.setSubImg2("https://iili.io/HUm6dDx.jpg");
		newProduct.setSubImg3("https://iili.io/HUm4bK7.jpg");
		newProduct.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Description cannot be null or empty";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR UPDATE PRODUCT

	@Test
	void testUpdateProduct() {
		ProductService productService = new ProductService();
		ProductEntity updatedProduct = new ProductEntity();

		String random = generateRandomString(7);
		updatedProduct.setName(random);
		updatedProduct.setSubCategoryId(6);
		String randomDescription = generateRandomString(70);
		updatedProduct.setDescription(randomDescription);
		updatedProduct.setPrice(4500.00);
		updatedProduct.setMainImg("https://iili.io/HUm4pSe.jpg");
		updatedProduct.setSubImg1("https://iili.io/HUm4ml9.jpg");
		updatedProduct.setSubImg2("https://iili.io/HUm6dDx.jpg");
		updatedProduct.setSubImg3("https://iili.io/HUm4bK7.jpg");

		updatedProduct.setActive(true);

		assertDoesNotThrow(() -> {
			productService.update(1, updatedProduct);
		});

	}

	// TEST FOR DELETE PRODUCT

	@Test
	void testDeleteProduct() throws Exception {

		ProductService productService = new ProductService(); //
		productService.delete(3);

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
		newProduct.setMainImg("https://iili.io/HUm4pSe.jpg");
		newProduct.setSubImg1("https://iili.io/HUm4ml9.jpg");
		newProduct.setSubImg2("https://iili.io/HUm6dDx.jpg");
		newProduct.setSubImg3("https://iili.io/HUm4bK7.jpg");
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
		newProduct.setMainImg("https://iili.io/HUm4pSe.jpg");
		newProduct.setSubImg1("https://iili.io/HUm4ml9.jpg");
		newProduct.setSubImg2("https://iili.io/HUm6dDx.jpg");
		newProduct.setSubImg3("https://iili.io/HUm4bK7.jpg");
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
		newProduct.setMainImg("https://iili.io/HUm4pSe.jpg");
		newProduct.setSubImg1("https://iili.io/HUm4ml9.jpg");
		newProduct.setSubImg2("https://iili.io/HUm6dDx.jpg");
		newProduct.setSubImg3("https://iili.io/HUm4bK7.jpg");
		newProduct.setActive(true);

		Exception exception = assertThrows(ValidationException.class, () -> {
			productService.create(newProduct);
		});
		String exceptedMessage = "Price can't be zero or negative";
		String actualMessage = exception.getMessage();

		assertTrue(exceptedMessage.equals(actualMessage));
	}

	// TEST FOR GET ALL PRODUCTS

	@Test
	void getAllProducts() throws ServiceException {
		ProductService productService = new ProductService();
		assertDoesNotThrow(() -> {
			Set<ProductEntity> productList = productService.findAllProducts();
			System.out.println(productList);
		});
	}

	// TEST FOR GET PRODUCT BY PRODUCT ID

	@Test
	void getProductByProductId() throws ServiceException {
		ProductService productService = new ProductService();
		assertDoesNotThrow(() -> {
			ProductEntity productList = productService.findProductByProductId(5);
			System.out.println(productList);
		});
	}

	// TEST FOR GET PRODUCT BY PRODUCT NAME

	@Test
	void getProductByProductName() throws ServiceException {
		ProductService productService = new ProductService();
		assertDoesNotThrow(() -> {
			ProductEntity productList = productService.findProductByProductName("Dark Chocolate Bar");
			System.out.println(productList);
		});
	}

	// TEST FOR GET PRODUCT BY CATEGORY ID
	@Test
	void getProductByCategoryId() throws ServiceException {
		ProductService productService = new ProductService();
		assertDoesNotThrow(() -> {
			Set<ProductEntity> productList = productService.findProductsByCategoryId(1);
			System.out.println(productList);
		});
	}

}