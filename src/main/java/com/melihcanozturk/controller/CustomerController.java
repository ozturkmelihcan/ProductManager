package com.melihcanozturk.controller;

import java.util.HashMap;
import java.util.List;

import com.melihcanozturk.entity.Customer;
import com.melihcanozturk.repository.CustomerDao;
import com.melihcanozturk.service.CustomerService;
import com.melihcanozturk.util.BAUtils;

public class CustomerController {

	private CustomerService customerService = new CustomerService();
	private CustomerDao customerDao = new CustomerDao();
	private ProductController productController = new ProductController();
	private ProductEvaluateController evaluateController = new ProductEvaluateController();

	public CustomerController() {
		customerService = new CustomerService();
	}

	public List<Customer> findByEmail(String email) {

		return customerDao.findByEmail(email);
	}

	public void listCustomer() {
		customerService.listAll().forEach(customer -> System.out.println(customer));

	}

	public void signUp() {
		String name = BAUtils.readString("isim giriniz");
		String lastname = BAUtils.readString("soyad giriniz.");
		String email = BAUtils.readString("email giriniz");
		String tc = BAUtils.readString("lütfen tc giriniz");
		String password = BAUtils.readString("parola giriniz");
		boolean numeric = tc.chars().allMatch(Character::isDigit);
		if (email.contains("@") && !email.startsWith("@")) {
			if (numeric) {
				if (tc.length() == 11) {
					Customer customer = new Customer(name, lastname, email, password, tc);
					customerDao.create(customer);
					System.out.println("başarıyla kayıt yapıldı.");
				} else {
					System.out.println("tc 11 haneli olmalıdır.");
				}

			} else {
				System.out.println("tc kimlik karakter içeremez.");

			}

		} else {
			System.out.println("email @ içermek zorundadır.");

		}

	}

	private void customerManager(Customer customer) {

		boolean kontrol = true;
		while (kontrol) {

			HashMap<Integer, String> menuItems = new HashMap<Integer, String>();
			menuItems.put(1, "Buy a product");
			menuItems.put(2, "Comment");
			menuItems.put(3, "List out-of-stock products");
			menuItems.put(4, "List all");
			menuItems.put(5, "List comments by Product");
			menuItems.put(6, "Exit");
			menuItems.put(7, "return menu");
			int key = BAUtils.menu(menuItems);

			switch (key) {
			case 1:
				productController.productBuy(customer);

				break;
			case 2:
				evaluateController.comment2(customer);
				break;
			case 3:
				productController.stockControl();
				break;
			case 4:
				productController.listProducts();
				break;
			case 5:
				productController.showComment();
				break;
			case 6:
				System.out.println("sistem kapatılıyor.");
				System.exit(0);
			case 7:
				kontrol = false;
				break;
			default:
				break;
			}
		}
	}

	public void signIn() {
		String email = BAUtils.readString("Enter email: ");
		String password = BAUtils.readString("Enter password: ");
		List<Customer> customer = findByEmail(email);

		if (customer.isEmpty()) {
			System.out.println("böyle bir kullanıcı yok. lütfen kayıt olunuz...");
		} else if (customer != null) {
			if (customer.get(0).getPassword().equals(password)) {
				customerManager(customer.get(0));
			} else {
				System.out.println("şifre kontrolü yapınız...");
			}
		}

	}

}
