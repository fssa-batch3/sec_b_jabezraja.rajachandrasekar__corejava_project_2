package in.fssa.jauntyrialto.validator;

import java.util.regex.Pattern;

import in.fssa.jauntyrialto.dao.ProductDAO;
import in.fssa.jauntyrialto.entity.ProductEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.util.StringUtil;

public class ProductValidator {

	private static final String NAME_PATTERN = "^[a-zA-Z0-9 ]+$";

	/**
	 * 
	 * @param product
	 * @throws ValidationException
	 */

	public static void validate(ProductEntity product) throws ValidationException {
		if (product == null) {
			throw new ValidationException("Invalid product input");
		}

		validateName(product.getName());
		validateDescription(product.getDescription());
		validateSubCategoryId(product.getSubCategoryId());
		validatePrice(product.getPrice());
	}

	/**
	 * 
	 * @param name
	 * @throws ValidationException
	 */
	public static void validateName(String name) throws ValidationException {

		StringUtil.rejectIfInvalidString(name, "Product name");

		if (!Pattern.matches(NAME_PATTERN, name)) {
			throw new ValidationException("Product name doesn't match the pattern");
		}

	}

	/**
	 * 
	 * @param price
	 * @throws ValidationException
	 */

	public static void validatePrice(double price) throws ValidationException {

		if (price <= 0) {

			throw new ValidationException("Price can't be zero or negative");

		}
	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void validateSubCategoryId(int id) throws ValidationException {

		if (id <= 0) {

			throw new ValidationException("Invalid SubCategory_Id");
		}

		ProductDAO productDao = new ProductDAO();
		try {
			productDao.checkSubCategoryExists(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param description
	 * @throws ValidationException
	 */

	public static void validateDescription(String description) throws ValidationException {

		StringUtil.rejectIfInvalidString(description, "Description");

	}

}