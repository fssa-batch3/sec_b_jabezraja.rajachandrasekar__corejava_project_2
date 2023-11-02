package in.fssa.jauntyrialto.service;

import java.util.Set;

import in.fssa.jauntyrialto.dao.OrderDAO;
import in.fssa.jauntyrialto.entity.OrderEntity;
import in.fssa.jauntyrialto.exception.PersistenceException;
import in.fssa.jauntyrialto.exception.ServiceException;
import in.fssa.jauntyrialto.exception.ValidationException;
import in.fssa.jauntyrialto.util.Logger;

public class OrderService {
	Logger logger = new Logger();

	public void create(OrderEntity newProduct) throws ValidationException, ServiceException {

		try {
//			ProductValidator.validate(newProduct);
			OrderDAO productDAO = new OrderDAO();
			productDAO.create(newProduct);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	public void delete(int id) throws ServiceException {

		if (id == 0) {
			throw new ServiceException("Invalid id");
		}

		try {
			OrderDAO orderDAO = new OrderDAO();
			orderDAO.delete(id);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	public void update(int id, OrderEntity updatedOrder) throws ValidationException, ServiceException {

		if (id == 0) {
			throw new ServiceException("Invalid id");
		}

		try {
//			ProductValidator.validate(updatedProduct);
			OrderDAO orderDAO = new OrderDAO();
			orderDAO.update(id, updatedOrder);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

	public OrderEntity findOrderByOrderId(int id) throws ServiceException {
		OrderEntity order = null;
		try {
			OrderDAO orderDAO = new OrderDAO();
			order = orderDAO.getOrderById(id);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}
		return order;
	}

	public Set<OrderEntity> findOrdersByUserId(int id) throws ServiceException {

		Set<OrderEntity> list = null;
		try {
			OrderDAO order = new OrderDAO();
			list = order.findOrdersByUserId(id);
		} catch (PersistenceException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

		for (OrderEntity prod : list) {
			logger.debug(prod);
		}

		return list;

	}
}
