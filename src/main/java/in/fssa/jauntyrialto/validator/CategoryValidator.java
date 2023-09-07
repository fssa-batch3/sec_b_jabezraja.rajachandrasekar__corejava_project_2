package in.fssa.jauntyrialto.validator;

import java.util.regex.Pattern;

import in.fssa.jauntyrialto.dao.CategoryDAO;
import in.fssa.jauntyrialto.entity.CategoryEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.util.Logger;
import in.fssa.jauntyrialto.util.StringUtil;

public class CategoryValidator {
	static Logger logger = new Logger();

	private static final String NAME_PATTERN = "^[A-Za-z][A-Za-z\\\\s]*$";

	/**
	 * 
	 * @param category
	 * @throws ValidationException
	 */
	public static void validate(CategoryEntity category) throws ValidationException {
		if (category == null) {
			throw new ValidationException("Invalid category input");
		}

		StringUtil.rejectIfInvalidString(category.getName(), "Category name");

		if (!Pattern.matches(NAME_PATTERN, category.getName())) {
			throw new ValidationException("Category name doesn't match the pattern");
		}

		CategoryDAO categoryDAO = new CategoryDAO();
		try {
			categoryDAO.checkCategoryExists(category.getName());
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ValidationException(e.getMessage());
		}

	}
}