package in.fssa.jauntyrialto.validator;

import java.util.regex.Pattern;

import in.fssa.jauntyrialto.dao.SubCategoryDAO;
import in.fssa.jauntyrialto.entity.SubCategoryEntity;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.util.StringUtil;

public class SubCategoryValidator {
	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";

	/**
	 * 
	 * @param subcategory
	 * @throws ValidationException
	 */

	public static void validate(SubCategoryEntity subcategory) throws ValidationException {
		if (subcategory == null) {
			throw new ValidationException("Invalid SubCategory input");
		}

		validateCategoryId(subcategory.getCategory_id());

		StringUtil.rejectIfInvalidString(subcategory.getName(), "SubCategory name");

		if (!Pattern.matches(NAME_PATTERN, subcategory.getName())) {
			throw new ValidationException("SubCategory name doesn't match the pattern");
		}
		SubCategoryDAO subcategorydao = new SubCategoryDAO();
		subcategorydao.checkSubCategoryExists(subcategory.getName());

	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */

	public static void validateCategoryId(int id) throws ValidationException {

		if (id <= 0) {
			throw new ValidationException("Invalid Category id");
		}

		SubCategoryDAO subcategorydao = new SubCategoryDAO();
		subcategorydao.checkCategoryExists(id);

	}
}