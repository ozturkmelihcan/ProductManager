package com.melihcanozturk.service;

import java.util.List;

import com.melihcanozturk.entity.Customer;
import com.melihcanozturk.repository.CustomerDao;



public class CustomerService implements IService<Customer> {

	private CustomerDao customerDao;

	public CustomerService() {

		this.customerDao = new CustomerDao();
	}

	@Override
	public void create(Customer entity) {
		customerDao.create(entity);

	}

	@Override
	public void delete(long id) {
		customerDao.delete(id);

	}

	@Override
	public void update(long id, Customer entity) {
		customerDao.update(id, entity);

	}

	@Override
	public List<Customer> listAll() {

		return customerDao.listAll();
	}

	@Override
	public Customer find(long id) {
		Customer customer = customerDao.find(id);
		return customer;
	}

}
