package in.fssa.jauntyrialto.interfaces;

import java.util.Set;

import in.fssa.jauntyrialto.exception.PersistenceException;

public interface ProductInterfaces<T> {
	public abstract Set<T> findAllProducts() throws PersistenceException;

	public abstract void create(T object) throws PersistenceException;

	public abstract void update(int id, T object) throws PersistenceException;

	public abstract void delete(int id) throws PersistenceException;

	public abstract Set<T> findByCategoryId(int id) throws PersistenceException;

	public abstract Set<T> findBySubCategoryId(int id) throws PersistenceException;

	public abstract T findProductById(int id) throws PersistenceException;
}
