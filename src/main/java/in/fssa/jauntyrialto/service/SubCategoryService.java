package in.fssa.jauntyrialto.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.fssa.jauntyrialto.dao.SubCategoryDAO;
import in.fssa.jauntyrialto.entity.SubCategoryEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.exception.ServiceException;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.util.Logger;
import in.fssa.jauntyrialto.validator.SubCategoryValidator;

public class SubCategoryService {
	Logger logger = new Logger();

	/**
	 * 
	 * @param newSubCategory
	 * @throws Exception
	 */

	public void create(SubCategoryEntity newSubCategory) throws ValidationException, ServiceException {

		try {
			SubCategoryValidator.validate(newSubCategory);
			SubCategoryDAO subcategoryDAO = new SubCategoryDAO();
			subcategoryDAO.create(newSubCategory);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param id
	 * @param updatedSubCategory
	 * @throws Exception
	 */

	public void update(int id, SubCategoryEntity updatedSubCategory) throws ValidationException, ServiceException {

		if (id <= 0) {
			throw new ServiceException("Invalid id");
		}

		try {
			SubCategoryValidator.validate(updatedSubCategory);
			SubCategoryDAO subcategoryDAO = new SubCategoryDAO();
			subcategoryDAO.update(id, updatedSubCategory);
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
			SubCategoryDAO subcategoryDAO = new SubCategoryDAO();
			subcategoryDAO.delete(id);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	public List<SubCategoryEntity> getAllSubCategories() throws ServiceException {

		List<SubCategoryEntity> subCategoryList = new ArrayList<>();

		try {
			SubCategoryDAO subCategoryDAO = new SubCategoryDAO();

			subCategoryList = subCategoryDAO.findAll();

			Iterator<SubCategoryEntity> iterator = subCategoryList.iterator();

			while (iterator.hasNext()) {
				logger.debug(iterator.next());
			}

		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}
		return subCategoryList;
	}

}