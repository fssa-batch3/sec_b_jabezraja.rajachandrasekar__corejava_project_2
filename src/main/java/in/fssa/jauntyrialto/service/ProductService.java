package in.fssa.jauntyrialto.service;

import java.util.Set;

import in.fssa.jauntyrialto.dao.ProductDAO;
import in.fssa.jauntyrialto.entity.ProductEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.exception.ServiceException;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.util.Logger;
import in.fssa.jauntyrialto.validator.ProductValidator;

public class ProductService {
	Logger logger = new Logger();

	/**
	 * 
	 * @param newProduct
	 * @throws ServiceException
	 */

	public void create(ProductEntity newProduct) throws ValidationException, ServiceException {

		try {
			ProductValidator.validate(newProduct);
			ProductDAO productDAO = new ProductDAO();
			productDAO.create(newProduct);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param id
	 * @param updatedProduct
	 * @throws ServiceException
	 * @throws ValidationException
	 */

	public void update(int id, ProductEntity updatedProduct) throws ValidationException, ServiceException {

		if (id == 0) {
			throw new ServiceException("Invalid id");
		}

		try {
			ProductValidator.validate(updatedProduct);
			ProductDAO productDAO = new ProductDAO();
			productDAO.update(id, updatedProduct);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @param id
	 * @throws ServiceException
	 */

	public void delete(int id) throws ServiceException {

		if (id == 0) {
			throw new ServiceException("Invalid id");
		}

		try {
			ProductDAO productDAO = new ProductDAO();
			productDAO.delete(id);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */

	public Set<ProductEntity> findAllProducts() throws ServiceException {

		Set<ProductEntity> ProductList = null;
		try {
			ProductDAO productDAO = new ProductDAO();
			ProductList = productDAO.findAllProducts();
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		for (ProductEntity prod : ProductList) {
			logger.debug(prod);
		}

		return ProductList;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */

	public Set<ProductEntity> findProductsByCategoryId(int id) throws ServiceException {

		Set<ProductEntity> ProductList = null;
		try {
			ProductDAO productDAO = new ProductDAO();
			ProductList = productDAO.findByCategoryId(id);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		for (ProductEntity prod : ProductList) {
			logger.debug(prod);
		}

		return ProductList;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public Set<ProductEntity> findProductsBySubCategoryId(int id) throws ServiceException {

		Set<ProductEntity> ProductList = null;
		try {
			ProductDAO productDAO = new ProductDAO();
			ProductList = productDAO.findBySubCategoryId(id);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		for (ProductEntity prod : ProductList) {
			logger.debug(prod);
		}

		return ProductList;

	}

	public ProductEntity findProductByProductId(int id) throws ServiceException {

		ProductEntity ProductDetail = null;
		try {
			ProductDAO productDAO = new ProductDAO();
			ProductDetail = productDAO.findProductById(id);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		return ProductDetail;
	}

}
