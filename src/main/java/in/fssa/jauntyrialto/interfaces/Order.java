package in.fssa.jauntyrialto.interfaces;

import java.util.Set;

import in.fssa.jauntyrialto.exception.PersistenceException;

public interface Order<T> {

	public abstract void create(T object) throws PersistenceException;

	public abstract void update(int id, T object) throws PersistenceException;

	public abstract void delete(int id) throws PersistenceException;

	public abstract Set<T> findByUserId(int id) throws PersistenceException;

	public abstract Set<T> findAll() throws PersistenceException;

	public abstract T findProductById(int id) throws PersistenceException;

}
