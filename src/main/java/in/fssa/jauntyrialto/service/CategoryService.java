package in.fssa.jauntyrialto.service;

import in.fssa.jauntyrialto.dao.CategoryDAO;
import in.fssa.jauntyrialto.entity.CategoryEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.exception.ServiceException;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.util.Logger;
import in.fssa.jauntyrialto.validator.CategoryValidator;

public class CategoryService {
	Logger logger = new Logger();

	/**
	 * 
	 * @param newCategory
	 * @throws Exception
	 */

	public void create(CategoryEntity newCategory) throws ValidationException, ServiceException {
		try {
			CategoryValidator.validate(newCategory);
			CategoryDAO categoryDAO = new CategoryDAO();
			categoryDAO.create(newCategory);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * 
	 * @param id
	 * @param updatedCategory
	 * @throws Exception
	 */

	public void update(int id, CategoryEntity updatedCategory) throws ValidationException, ServiceException {

		if (id <= 0) {
			throw new ServiceException("Invalid id");
		}

		try {
			CategoryValidator.validate(updatedCategory);
			CategoryDAO categoryDAO = new CategoryDAO();
			categoryDAO.update(id, updatedCategory);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param id
	 * @throws Exception
	 */

	public void delete(int id) throws ServiceException {

		if (id <= 0) {
			throw new ServiceException("Invalid id");
		}

		try {
			CategoryDAO categoryDAO = new CategoryDAO();
			categoryDAO.delete(id);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}
}