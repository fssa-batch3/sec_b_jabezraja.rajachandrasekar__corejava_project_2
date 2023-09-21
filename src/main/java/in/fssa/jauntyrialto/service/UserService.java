package in.fssa.jauntyrialto.service;

import java.util.Set;

import in.fssa.jauntyrialto.dao.UserDAO;
import in.fssa.jauntyrialto.entity.UserEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.exception.ServiceException;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.util.Logger;
import in.fssa.jauntyrialto.util.Password;
import in.fssa.jauntyrialto.util.StringUtil;
import in.fssa.jauntyrialto.validator.UserValidator;

public class UserService {

	static Logger logger = new Logger();

	public void create(UserEntity newUser) throws ValidationException, ServiceException {

		try {

			UserDAO userDAO = new UserDAO();
			UserValidator.validate(newUser);

			String newPassword = Password.hashPassword(newUser.getPassword());
			newUser.setPassword(newPassword);

			String confirmPassword = Password.hashPassword(newUser.getConfirmPassword());
			newUser.setConfirmPassword(confirmPassword);

			userDAO.create(newUser);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	public void update(int id, UserEntity updatedUser) throws ServiceException, ValidationException {

		try {
			UserValidator.isIdValid(id);
			UserValidator.validate(updatedUser);

			UserDAO userDAO = new UserDAO();
			userDAO.update(id, updatedUser);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	public void delete(int id) throws ServiceException, ValidationException {

		try {
			UserValidator.isIdValid(id);

			UserDAO userDAO = new UserDAO();
			userDAO.delete(id);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	public Set<UserEntity> findAll() throws ServiceException {

		Set<UserEntity> userList = null;

		try {
			UserDAO userDAO = new UserDAO();

			userList = userDAO.findAll();

			for (UserEntity user : userList) {
				logger.debug(user);
			}
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return userList;

	}

	public UserEntity findById(int id) throws ServiceException, ValidationException {

		UserEntity user = null;

		try {

			UserValidator.isIdValid(id);
			UserDAO userDAO = new UserDAO();
			user = userDAO.findById(id);

		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return user;

	}

	public UserEntity findUserByEmail(String email) throws ValidationException, ServiceException {

		UserEntity user = null;
		try {
			StringUtil.rejectIfInvalidEmail(email, "Email");
			UserDAO userDAO = new UserDAO();
			user = userDAO.findByEmail(email);

		} catch (PersistenceException e) {
			throw new ServiceException(e.getMessage());
		}
		return user;
	}

	public boolean userLogin(UserEntity user) throws ServiceException {

		try {

			UserValidator userValidator = new UserValidator();
			userValidator.validateUserLogin(user);

			UserDAO userDAO = new UserDAO();

			UserEntity fromDB = userDAO.checkUserCredentials(user.getEmail());

			if (!Password.checkPassword(user.getPassword(), fromDB.getPassword())) {
				throw new PersistenceException("Invalid Credentials");
			}
			return true;
		} catch (PersistenceException | ValidationException e) {
			throw new ServiceException(e.getMessage());
		}

	}

}
