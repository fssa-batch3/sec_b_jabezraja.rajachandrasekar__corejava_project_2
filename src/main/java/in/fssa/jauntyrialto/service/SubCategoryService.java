package in.fssa.jauntyrialto.service;

import in.fssa.jauntyrialto.dao.SubCategoryDAO;
import in.fssa.jauntyrialto.entity.SubCategoryEntity;
import in.fssa.jauntyrialto.exception.ServiceException;
import in.fssa.jauntyrialto.validator.SubCategoryValidator;

public class SubCategoryService {
	/**
	 * 
	 * @param newSubCategory
	 * @throws Exception
	 */

	public void create(SubCategoryEntity newSubCategory) throws Exception {

		SubCategoryValidator.validate(newSubCategory);

		SubCategoryDAO subcategoryDAO = new SubCategoryDAO();
		subcategoryDAO.create(newSubCategory);

	}

	/**
	 * 
	 * @param id
	 * @param updatedSubCategory
	 * @throws Exception
	 */

	public void update(int id, SubCategoryEntity updatedSubCategory) throws Exception {

		if (id <= 0) {
			throw new ServiceException("Invalid id");
		}

		SubCategoryValidator.validate(updatedSubCategory);

		SubCategoryDAO subcategoryDAO = new SubCategoryDAO();
		subcategoryDAO.update(id, updatedSubCategory);

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

		SubCategoryDAO subcategoryDAO = new SubCategoryDAO();
		subcategoryDAO.delete(id);

	}

}