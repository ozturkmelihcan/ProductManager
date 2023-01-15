package com.melihcanozturk.controller;


import java.util.List;

import com.melihcanozturk.entity.Customer;
import com.melihcanozturk.entity.Product;
import com.melihcanozturk.entity.ProductEvaluate;
import com.melihcanozturk.service.CustomerService;
import com.melihcanozturk.service.ProductEvaluateService;
import com.melihcanozturk.service.ProductService;
import com.melihcanozturk.util.BAUtils;

public class ProductEvaluateController {

	private ProductService productService = new ProductService();
	private ProductEvaluateService evaluateService = new ProductEvaluateService();
	private CustomerService customerService = new CustomerService();

	public void comment(Customer customer) {

		Long id = BAUtils.readLong("lütfen yorum yapmak istediğiniz ürünün id'sini giriniz...");
		Product product = productService.find(id);

		product = customer.getProduct().get(0);
		System.out.println("ürünleriniz:");
		List<Product> cp = customer.getProduct();

		System.out.println(cp.contains(product));

		if (cp.contains(product)) {
			String comment = BAUtils.readString("yorumunuzu yazınız");
			int puan = BAUtils.readInt("puanınızı veriniz");
			ProductEvaluate evaluate = new ProductEvaluate();
			evaluate.setComment(comment);
			evaluate.setPoint(puan);

			product.getProductEvaluates().add(evaluate);
			productService.update(id, product);
			customerService.update(customer.getId(), customer);
		} else {
			System.out.println("almadıgınız ürün hakkında yorum yapamazsınız...");
		}

	}

	public void comment2(Customer customer) {

		customerService.listAll();

		Long id = BAUtils.readLong("lütfen yorum yapmak istediğiniz ürünün id'sini seçiniz");
		Product product = productService.find(id);

		productService.update(product.getId(), product);
		ProductEvaluate evaluate = new ProductEvaluate();
		String yorum = BAUtils.readString("lütfen yorum yapınız.");
		evaluate.setComment(yorum);
		int puanla = BAUtils.readInt("lütfen ürünü puanlayınız.");
		
		evaluate.setPoint(puanla);
		evaluate.setProduct(product);
		evaluate.setCustomer(customer);

		evaluateService.create(evaluate);

	}

}
