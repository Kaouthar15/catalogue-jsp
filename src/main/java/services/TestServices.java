package services;

import java.util.List;

import models.Category;

public class TestServices {
	public static void main(String[] args) {
		CategoryService cs= new CategoryService();
		List<Category> lstCategories = cs.list();
		System.out.println("List of categories : "+lstCategories.size());
		lstCategories.stream().forEach(System.out::println);
	}
}
