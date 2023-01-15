package com.melihcanozturk.util;

import java.util.HashMap;

import com.melihcanozturk.controller.AdminController;
import com.melihcanozturk.controller.CategoryController;
import com.melihcanozturk.controller.CustomerController;
import com.melihcanozturk.controller.ProductController;
import com.melihcanozturk.controller.ProductEvaluateController;
import com.melihcanozturk.entity.Customer;

public class ShoppingMenu {

	private AdminController adminController = new AdminController();
	private CategoryController categoryController = new CategoryController();
	private CustomerController customerController = new CustomerController();
	private ProductController productController = new ProductController();
	private ProductEvaluateController evaluateController = new ProductEvaluateController();

	public ShoppingMenu() {
		super();
		this.adminController = new AdminController();
		this.categoryController = new CategoryController();
		this.productController = new ProductController();
	}

	public void menu() {
		while (true) {
			HashMap<Integer, String> menuItems = new HashMap<Integer, String>();
			menuItems.put(1, "Admin");
			menuItems.put(2, "Customer Login");
			menuItems.put(3, "exit");
			int key = BAUtils.menu(menuItems);
			switch (key) {
			case 1:

				adminMenu();
				break;
			case 2:
				customerMenu();
				break;

			case 3:
				System.exit(0);
			default:
				break;
			}
		}

	}

	private void adminMenu() {
		while (true) {

			HashMap<Integer, String> menuItems = new HashMap<Integer, String>();
			menuItems.put(1, "Add Category");
			menuItems.put(2, "Add Product");
			menuItems.put(3, "List Customers");
			menuItems.put(4, "Return menu");

			int key = BAUtils.menu(menuItems);

			switch (key) {
			case 1:
				categoryController.createCategory(); // KISIMLARI DOLDUR....

				break;
			case 2:
				productController.createProduct();

				break;
			case 3:
				customerController.listCustomer();

				break;
			case 4:
				menu();
				break;

			default:
				break;
			}
		}
	}

	private void customerMenu() {
		HashMap<Integer, String> menuItems = new HashMap<Integer, String>();
		menuItems.put(1, "Sign up");
		menuItems.put(2, "Sign in");

		int key = BAUtils.menu(menuItems);

		switch (key) {
		case 1:
			customerController.signUp();

			break;
		case 2:
			customerController.signIn();

			break;
		default:
			break;
		}
	}

}
