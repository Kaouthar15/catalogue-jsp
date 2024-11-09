package services;

import java.util.List;

import models.User;

public class TestServices {
	public static void main(String[] args) {
		UserService cs= new UserService();
		List<User> listUser = cs.list();
		System.out.println("List of users : "+listUser.size());
		listUser.stream().forEach(System.out::println);
	}
}
