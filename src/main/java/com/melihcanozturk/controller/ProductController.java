package com.melihcanozturk.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.melihcanozturk.entity.Category;
import com.melihcanozturk.entity.Customer;
import com.melihcanozturk.entity.Product;
import com.melihcanozturk.entity.ProductEvaluate;
import com.melihcanozturk.service.CategoryService;
import com.melihcanozturk.service.CustomerService;

import com.melihcanozturk.service.ProductService;
import com.melihcanozturk.util.BAUtils;

public class ProductController {

	private CustomerService customerService = new CustomerService();
	private ProductService productService = new ProductService();

	private CategoryService categoryService = new CategoryService();

	public ProductController() {
		this.productService = new ProductService();

		this.categoryService = new CategoryService();
	}

	public void productBuy(Customer customer) {

		long id = BAUtils.readLong("urun id'sini gırınız"); // ürün id si girdik
		int adet = BAUtils.readInt("kaç adet almak istiyorsunuz");
		Product product = productService.find(id); // girilen id ye göre ürünü bulduk

		List<Product> products = customer.getProduct(); // kullanıcının ürünlerini listeledik.

		products.add(product); // listeye ürünü ekledik

		customer.setProduct(products); // customerın ürünlerini liste olarak ayarladık

		if (product.getStock() - adet >= 0) { // stock varsa adet kadar azalttık.

			product.setStock(product.getStock() - adet);
			productService.update(id, product);
			customerService.update(customer.getId(), customer);
		} else {
			System.out.println("stokta o kadar ürün yok.");
		}

	}

	public void createProduct() {

		String isim = BAUtils.readString("ürün adı");
		double ucret = BAUtils.readDouble("ücreti giriniz.");
		int stock = BAUtils.readInt("stock sayısı giriniz");
		long categoryId = BAUtils.readLong("category id giriniz");

		Category category = categoryService.find(categoryId);

		if (category != null) {

			Product product = new Product(isim, ucret, stock, category);
			productService.create(product);

		}

		else {
			System.out.println("eklemek istediğiniz kategoriyi kontrol ediniz...");
		}

	}

	public void stockControl() {

		List<Product> list = productService.listAll().stream().filter(p -> p.getStock() < 10)
				.collect(Collectors.toList());
		System.out.println(list);
	}

	public void listProducts() {
		productService.listAll2();
	}

	public void showComment() {
		Long id = BAUtils.readLong("lütfen yorumlarını görmek istediğiniz ürünün id sini giriniz.");

		Product product = productService.find(id);

		List<ProductEvaluate> pE = product.getProductEvaluates();
		for (ProductEvaluate productEvaluate : pE) {
			System.out.println(productEvaluate);
		}

	}

}
