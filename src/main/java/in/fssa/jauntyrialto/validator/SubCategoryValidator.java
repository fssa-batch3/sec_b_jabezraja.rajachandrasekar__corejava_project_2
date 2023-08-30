package in.fssa.jauntyrialto.validator;

import java.util.regex.Pattern;

import in.fssa.jauntyrialto.dao.SubCategoryDAO;
import in.fssa.jauntyrialto.entity.SubCategoryEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
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

		validateCategoryId(subcategory.getCategoryId());

		StringUtil.rejectIfInvalidString(subcategory.getName(), "SubCategory name");

		if (!Pattern.matches(NAME_PATTERN, subcategory.getName())) {
			throw new ValidationException("SubCategory name doesn't match the pattern");
		}

		SubCategoryDAO subcategoryDAO = new SubCategoryDAO();
		try {
			subcategoryDAO.checkSubCategoryExists(subcategory.getName());
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

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

		SubCategoryDAO subcategoryDAO = new SubCategoryDAO();
		try {
			subcategoryDAO.checkCategoryNotExists(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ValidationException(e.getMessage());
		}

	}
}