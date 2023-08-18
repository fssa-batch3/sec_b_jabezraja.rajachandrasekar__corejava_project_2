package in.fssa.jauntyrialto.service;

import in.fssa.jauntyrialto.dao.CategoryDAO;
import in.fssa.jauntyrialto.entity.CategoryEntity;
import in.fssa.jauntyrialto.exception.ValidationException;
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
//		} catch (Persistance e) {
//			throw new ServiceException(e.getMessage());
//		}
		CategoryValidator.validate(newCategory);
		CategoryDAO categorydao = new CategoryDAO();
		categorydao.create(newCategory);
	}

	/**
	 * 
	 * @param id
	 * @param updatedCategory
	 * @throws Exception
	 */

	public void update(int id, CategoryEntity updatedCategory) throws Exception {

		if (id == 0) {
			throw new ValidationException("Invalid id");
		}

		CategoryValidator.validate(updatedCategory);

		CategoryDAO categorydao = new CategoryDAO();
		categorydao.update(id, updatedCategory);

	}

	/**
	 * 
	 * @param id
	 * @throws Exception
	 */

	public void delete(int id) throws Exception {

		if (id == 0) {
			throw new ValidationException("Invalid id");
		}

		CategoryDAO categorydao = new CategoryDAO();
		categorydao.delete(id);

	}
}