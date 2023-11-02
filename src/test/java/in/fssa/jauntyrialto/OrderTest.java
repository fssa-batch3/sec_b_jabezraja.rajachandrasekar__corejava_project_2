package in.fssa.jauntyrialto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Set;

import org.junit.jupiter.api.Test;

import in.fssa.jauntyrialto.entity.OrderEntity;
import in.fssa.jauntyrialto.exception.ServiceException;
import in.fssa.jauntyrialto.service.OrderService;

class OrderTest {
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
	void ValidInput() {

		OrderService orderService = new OrderService();
		OrderEntity newProduct = new OrderEntity();

		String random = generateRandomString(7);
		newProduct.setProductId(3);
		newProduct.setUserId(2);
		newProduct.setPinCode(678943);
		newProduct.setLandMark(random);
		newProduct.setAddress(random);
		newProduct.setQty(4);
		newProduct.setTotal(4500.00);
		assertDoesNotThrow(() -> {
			orderService.create(newProduct);
		});

	}

	// TEST FOR UPDATE ORDER

	@Test
	void testUpdateOrder() {
		OrderService orderService = new OrderService();
		OrderEntity updatedOrder = new OrderEntity();
		String random = generateRandomString(7);
		updatedOrder.setPinCode(777777);
		updatedOrder.setLandMark(random);
		updatedOrder.setAddress(random);

		updatedOrder.setActive(true);

		assertDoesNotThrow(() -> {
			orderService.update(1, updatedOrder);
		});

	}

	// TEST FOR DELETE ORDER

	@Test
	void testDeleteProduct() throws Exception {

		OrderService productService = new OrderService(); //
		productService.delete(1);

	}

	// TEST FOR GET ORDER BY ID

	@Test
	void getOrderByOrderId() throws ServiceException {
		OrderService Service = new OrderService();
		assertDoesNotThrow(() -> {
			OrderEntity list = Service.findOrderByOrderId(3);
			System.out.println(list);
		});
	}

	// TEST FOR GET ORDERS BY USER ID
	@Test
	void getOrdersByUserId() throws ServiceException {
		OrderService service = new OrderService();
		assertDoesNotThrow(() -> {
			Set<OrderEntity> productList = service.findOrdersByUserId(27);
			System.out.println(productList);
		});

	}
}
