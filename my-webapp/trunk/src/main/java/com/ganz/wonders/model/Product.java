package com.ganz.wonders.model;

import java.util.ArrayList;
import java.util.List;

public class Product {
	String name;
	String description;
	int qtyInStock;
	double price;
	String image;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQtyInStock() {
		return qtyInStock;
	}

	public void setQtyInStock(int qtyInStock) {
		this.qtyInStock = qtyInStock;
	}

	public static List<Product> createProducts() {

		List<Product> products = new ArrayList<Product>();
		for (int i = 0; i < 5; i++) {
			Product product = new Product();
			product.setName("PRD " + i);
			product.setDescription("DESC " + i);
			products.add(product);
		}
		
		return products;
	}

}
