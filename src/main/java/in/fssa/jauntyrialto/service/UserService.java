package in.fssa.jauntyrialto.service;

import java.util.Set;

import in.fssa.jauntyrialto.dao.UserDAO;
import in.fssa.jauntyrialto.entity.UserEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.exception.ServiceException;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.util.Logger;
import in.fssa.jauntyrialto.validator.UserValidator;

public class UserService {

	static Logger logger = new Logger();

	public void create(UserEntity newUser) throws ValidationException, ServiceException {

		try {
			UserValidator.validate(newUser);
			UserDAO userDAO = new UserDAO();
			userDAO.create(newUser);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	public void update(int id, UserEntity updatedUser) throws ServiceException, ValidationException {

		try {
			UserValidator.CheckUserExistsWithId(id);
			UserValidator.validateName(updatedUser.getName());
			UserValidator.validatePassword(updatedUser.getPassword());

			UserDAO userDAO = new UserDAO();
			userDAO.update(id, updatedUser);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	public void delete(int id) throws ServiceException, ValidationException {

		try {
			UserValidator.CheckUserExistsWithId(id);

			UserDAO userDAO = new UserDAO();
			userDAO.delete(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

	}

	public static Set<UserEntity> findAll() throws ServiceException {

		Set<UserEntity> userList = null;

		try {
			UserDAO userDAO = new UserDAO();

			userList = userDAO.findAll();

			for (UserEntity user : userList) {
				logger.debug(user);
			}
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return userList;

	}

	public static UserEntity findById(int id) throws ServiceException, ValidationException {

		UserEntity user = null;

		try {

			UserValidator.CheckUserExistsWithId(id);
			UserDAO userDAO = new UserDAO();

			user = userDAO.findById(id);
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}

		return user;

	}

}
