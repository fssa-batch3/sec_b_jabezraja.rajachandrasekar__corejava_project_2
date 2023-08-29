package in.fssa.jauntyrialto.service;

import java.util.Set;

import in.fssa.jauntyrialto.dao.ProductDAO;
import in.fssa.jauntyrialto.entity.ProductEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.exception.ServiceException;
import in.fssa.jauntyrialto.validator.ProductValidator;

public class ProductService {
	/**
	 * 
	 * @param newProduct
	 * @throws Exception
	 */

	public void create(ProductEntity newProduct) throws Exception {

		ProductValidator.validate(newProduct);

		ProductDAO productDAO = new ProductDAO();
		productDAO.create(newProduct);

	}

	/**
	 * 
	 * @param id
	 * @param updatedProduct
	 * @throws Exception
	 */

	public void update(int id, ProductEntity updatedProduct) throws Exception {

		if (id == 0) {
			throw new ServiceException("Invalid id");
		}

		ProductValidator.validate(updatedProduct);

		ProductDAO productDAO = new ProductDAO();
		productDAO.update(id, updatedProduct);

	}

	/**
	 * 
	 * @param id
	 * @throws Exception
	 */

	public void delete(int id) throws Exception {

		if (id == 0) {
			throw new ServiceException("Invalid id");
		}

		ProductDAO productDAO = new ProductDAO();
		productDAO.delete(id);

	}

	/**
	 * 
	 * @return
	 * @throws PersistenceException
	 */

	public Set<ProductEntity> findAllProducts() throws PersistenceException {

		ProductDAO productDAO = new ProductDAO();

		Set<ProductEntity> ProductList = productDAO.findAllProducts();

		for (ProductEntity prod : ProductList) {
			System.out.println(prod);
		}

		return ProductList;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */

	public Set<ProductEntity> findProuctsByCategoryId(int id) throws PersistenceException {

		ProductDAO productDAO = new ProductDAO();

		Set<ProductEntity> ProductList = productDAO.findByCategoryId(id);

		for (ProductEntity prod : ProductList) {
			System.out.println(prod);
		}

		return ProductList;

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws PersistenceException
	 */
	public Set<ProductEntity> findProductsBySubCategoryId(int id) throws PersistenceException {

		ProductDAO productDAO = new ProductDAO();

		Set<ProductEntity> ProductList = productDAO.findBySubCategoryId(id);

		for (ProductEntity prod : ProductList) {
			System.out.println(prod);
		}

		return ProductList;

	}

}
