package services;

import models.Product;

import java.util.List;

public class StatisticsService {

	private ProductService productService = new ProductService();

	// Get the total number of products
	public Long getTotalProducts() {
		List<Product> products = productService.list();
		return (long) products.size();
	}

	// Get the total sales (assuming you have a field 'sales' in Product model)
	public Double getTotalSales() {
		List<Product> products = productService.list();
		return products.stream().mapToDouble(p -> p.getPrice() * p.getQuantity()) // Sales = price * quantity
				.sum();
	}

	// Get the average price of products
	public Double getAveragePrice() {
		List<Product> products = productService.list();
		return products.stream().mapToDouble(Product::getPrice).average().orElse(0);
	}
}
