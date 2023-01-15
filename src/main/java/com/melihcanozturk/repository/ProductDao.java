package com.melihcanozturk.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.melihcanozturk.entity.Product;


public class ProductDao implements IRepository<Product> {

//	private Session session;
//	private Transaction transaction;
//
//	private void openTransaction() {
//
//		session = dataBaseConnectionHibernate();
//		transaction = session.beginTransaction();
//
//	}
//
//	private void accessTransaction() {
//		transaction.commit();
//		session.close();
//	}
//
//	private void errorTransaction() {
//		if (transaction != null) {
//			transaction.rollback();
//		}
//
//	}
//	
//	@Override
//	public void create(Product t) {
//		try {
//			openTransaction();
//			session.save(t);
//			accessTransaction();
//		} catch (Exception e) {
//			errorTransaction();
//		}
//	}
	
	@Override
	public void create(Product entity) {
		Session session = null;
		try {
			session = dataBaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			System.out.println("Product data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Product to DB");
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Product deletedProduct = find(id);
			if (deletedProduct != null) {
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deletedProduct);
				session.getTransaction().commit();
				System.out.println("Product data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Product to DB");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, Product entity) {
		Session session = null;
		try {
			Product product = find(id);

			if (product != null) {
				product.setName(entity.getName());
				product.setId(entity.getId());
				product.setCategory(entity.getCategory());
				product.setCustomers(entity.getCustomers());
				product.setPrice(entity.getPrice());
				product.setProductEvaluates(entity.getProductEvaluates());
				product.setStock(entity.getStock());
				
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(product);
				session.getTransaction().commit();
				System.out.println("Product data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Product to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Product> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select product from Product as product";
		TypedQuery<Product> typedQuery = session.createQuery(query, Product.class);
		List<Product> productList = typedQuery.getResultList();
		
		return productList;
	}
	
	public List<Product> listAll2() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select product from Product as product";
		TypedQuery<Product> typedQuery = session.createQuery(query, Product.class);
		List<Product> productList = typedQuery.getResultList();
		productList.forEach(System.out::println);
		return productList;
	}

	@Override
	public Product find(long id) {
		Session session = dataBaseConnectionHibernate();
		Product product;
		try {
			product = session.find(Product.class, id);
		
			if (product != null) {
				System.out.println("Product Found--> " + product);
			
				return product;
			} else {
				System.out.println("Product not found");
				
				return null;
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while adding Product to DB");
		} finally {
			session.close();
		}
		
		return null;
	}

}
