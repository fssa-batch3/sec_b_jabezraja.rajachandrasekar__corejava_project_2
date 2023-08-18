package in.fssa.jauntyrialto.validator;

import java.util.regex.Pattern;

import in.fssa.jauntyrialto.dao.CategoryDAO;
import in.fssa.jauntyrialto.entity.CategoryEntity;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.util.StringUtil;

public class CategoryValidator {
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
		CategoryDAO categorydao = new CategoryDAO();
		categorydao.checkCategoryExists(category.getName());

	}
}