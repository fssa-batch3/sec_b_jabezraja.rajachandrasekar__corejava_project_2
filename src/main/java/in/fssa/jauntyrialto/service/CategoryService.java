package in.fssa.jauntyrialto.service;

import in.fssa.jauntyrialto.dao.CategoryDAO;
import in.fssa.jauntyrialto.entity.CategoryEntity;
import in.fssa.jauntyrialto.exception.ServiceException;
import in.fssa.jauntyrialto.validator.CategoryValidator;

public class CategoryService {
	/**
	 * 
	 * @param newCategory
	 * @throws Exception
	 */

	public void create(CategoryEntity newCategory) throws Exception {

//		try {
//			CategoryValidator.validate(newCategory);
//			CategoryDAO categorydao = new CategoryDAO();
//			categorydao.create(newCategory);
//		} catch (ServiceException e) {
//			throw new ServiceException(e.getMessage());
//		}
		CategoryValidator.validate(newCategory);
		CategoryDAO categoryDAO = new CategoryDAO();
		categoryDAO.create(newCategory);
	}

	/**
	 * 
	 * @param id
	 * @param updatedCategory
	 * @throws Exception
	 */

	////// ServiceException, ValidationException, PersistenceException ///////
	public void update(int id, CategoryEntity updatedCategory) throws Exception {

		if (id <= 0) {
			throw new ServiceException("Invalid id");
		}

		CategoryValidator.validate(updatedCategory);

		CategoryDAO categoryDAO = new CategoryDAO();
		categoryDAO.update(id, updatedCategory);

	}

	/**
	 * 
	 * @param id
	 * @throws Exception
	 */

	public void delete(int id) throws Exception {

		if (id <= 0) {
			throw new ServiceException("Invalid id");
		}

		CategoryDAO categoryDAO = new CategoryDAO();
		categoryDAO.delete(id);

	}
}