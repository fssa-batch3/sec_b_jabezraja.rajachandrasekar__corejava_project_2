package in.fssa.jauntyrialto.service;

import java.util.Set;

import in.fssa.jauntyrialto.dao.ProductDAO;
import in.fssa.jauntyrialto.entity.ProductEntity;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.validator.ProductValidator;

public class ProductService {
	/**
	 * 
	 * @param newProduct
	 * @throws Exception
	 */

	public void create(ProductEntity newProduct) throws Exception {

		ProductValidator.validate(newProduct);

		ProductDAO productDao = new ProductDAO();
		productDao.create(newProduct);

	}

	/**
	 * 
	 * @param id
	 * @param updatedProduct
	 * @throws Exception
	 */

	public void update(int id, ProductEntity updatedProduct) throws Exception {

		if (id == 0) {
			throw new ValidationException("Invalid id");
		}

		ProductValidator.validate(updatedProduct);

		ProductDAO productDao = new ProductDAO();
		productDao.update(id, updatedProduct);

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

		ProductDAO productDao = new ProductDAO();
		productDao.delete(id);

	}

	/**
	 * 
	 * @return
	 */

	public Set<ProductEntity> findAllProducts() {

		ProductDAO productDao = new ProductDAO();

		Set<ProductEntity> ProductList = productDao.findAllProducts();

		for (ProductEntity prod : ProductList) {
			System.out.println(prod);
		}

		return ProductList;

	}

	/**
	 * 
	 * @param id
	 * @return
	 */

	public Set<ProductEntity> findByCategoryId(int id) {

		ProductDAO productDao = new ProductDAO();

		Set<ProductEntity> ProductList = productDao.findByCategoryId(id);

		for (ProductEntity prod : ProductList) {
			System.out.println(prod);
		}

		return ProductList;

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Set<ProductEntity> findBySubCategoryId(int id) {

		ProductDAO productDao = new ProductDAO();

		Set<ProductEntity> ProductList = productDao.findBySubCategoryId(id);

		for (ProductEntity prod : ProductList) {
			System.out.println(prod);
		}

		return ProductList;

	}

}
