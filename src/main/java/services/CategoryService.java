package services;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import models.Category;
import utils.HibernateUtil;

public class CategoryService implements DAO<Category> {
	private static final Logger logger = Logger.getLogger(CategoryService.class.getName());
	private SessionFactory sessionFactory = getSessionFactory();
	
	protected SessionFactory getSessionFactory() {
		try {
			return HibernateUtil.getSessionFactory();
		}catch(Exception ex) {
			logger.log(Level.SEVERE, "Could not create SessionFactory", ex);
			throw new IllegalStateException("Could not create SessionFactory");
		}
	}
//	add a category
	@Override
	public void add(Category category) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.persist(category);
		session.getTransaction().commit();
		logger.info("Catygory saved successfully, Category Details="+category);
		
	}
//	update a category
	@Override
	public void update(Category category) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction(); 
		session.merge(category);
		session.getTransaction().commit();
		logger.info("category updated successfully, Category Details="+category);
	}
	
//	remove a category
	@Override
	public void remove(Long categoryId) {
		System.out.println("remove category");
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Category category = (Category) session.getReference(Category.class, Long.valueOf(categoryId));
		if(null != category){
			session.remove(category);
		}
		session.getTransaction().commit();
		logger.info("Category deleted successfully, category details="+category);
	}
	
//  get a category using an id
	@Override
	public Category get(Long categoryId) {
	    Session session = this.sessionFactory.getCurrentSession();
	    // No transaction started here
	    Category category = (Category) session.getReference(Category.class, Long.valueOf(categoryId));
	    
	    logger.info("Category loaded successfully, Category details=" + category); 
	    return category;
	}
	
	@Override
	public Category getById(Long categoryId) {
		Session session =  this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		Category category = (Category) session.getReference(Category.class, Long.valueOf(categoryId));
		session.getTransaction().commit();
		logger.info("Category loaded successfully, Category details="+category); 
		return category;
	}
//	list all the categories
	@Override
	public List<Category> list() {
		List<Category> CategoriesList = new ArrayList<>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.beginTransaction();
			CategoriesList = session.createQuery("select c from Category c", Category.class).list();
			session.getTransaction().commit();
			return CategoriesList;
		} catch (Exception e) {
			System.out.println("Error in getAllCategories\n"+e);
			e.printStackTrace();
			return CategoriesList;
		}
	}
//	selecting a category using a keyword 
	@Override
	public List<Category> selectByKeyword(String str) {
		Session session = this.sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Category> categoriesList = session.createQuery("from Category c WHERE c.name LIKE '%"+str+"%'", Category.class).list();
		session.getTransaction().commit(); 
		System.out.println(categoriesList);
		return categoriesList;
	}
	
}
