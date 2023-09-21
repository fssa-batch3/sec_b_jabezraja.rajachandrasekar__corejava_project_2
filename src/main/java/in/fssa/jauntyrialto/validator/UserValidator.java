package in.fssa.jauntyrialto.validator;

import in.fssa.jauntyrialto.dao.UserDAO;
import in.fssa.jauntyrialto.entity.UserEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.util.IntUtil;
import in.fssa.jauntyrialto.util.StringUtil;

public class UserValidator {

	/**
	 * 
	 * @param user
	 * @throws ValidationException
	 */

	public static void validate(UserEntity user) throws ValidationException {

		if (user == null) {
			throw new ValidationException("User Cannot be null");
		}

		StringUtil.rejectIfInvalidString(user.getEmail(), "Email");
		StringUtil.rejectIfInvalidString(user.getName(), "Name");
		StringUtil.rejectIfInvalidString(user.getPassword(), "Password");
		IntUtil.rejectIfInvalidNumber(user.getPhone(), "Phone Number");
		StringUtil.rejectIfInvalidName(user.getName(), "Name");
		StringUtil.rejectIfInvalidEmail(user.getEmail(), "Email");
		StringUtil.rejectIfInvalidPassword(user.getPassword());

	}

	/**
	 * 
	 * @param id
	 * @throws ValidationException
	 */
	public static void isIdValid(int id) throws ValidationException {

		try {
			IntUtil.rejectIfInvalidId(id, "Id");
			UserDAO userDAO = new UserDAO();
			userDAO.checkIdExists(id);
		} catch (PersistenceException e) {
			throw new ValidationException(e.getMessage());
		}
	}

	public boolean validateUserLogin(UserEntity user) throws ValidationException {

		if (user == null) {
			throw new ValidationException("User Cannot be null");
		}

		StringUtil.rejectIfInvalidString(user.getEmail(), "Email");
		StringUtil.rejectIfInvalidString(user.getPassword(), "Password");
		StringUtil.rejectIfInvalidEmail(user.getEmail(), "Email");
		StringUtil.rejectIfInvalidPassword(user.getPassword());
		return true;
	}

}
