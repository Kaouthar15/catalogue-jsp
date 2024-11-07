package services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import models.Category;
import models.Product;
import utils.HibernateUtil;

public class ProductService implements DAO<Product> {

    private static final Logger logger = Logger.getLogger(ProductService.class.getName());
    private SessionFactory sessionFactory = getSessionFactory();
    private CategoryService categoryService = new CategoryService();
    
    protected SessionFactory getSessionFactory() {
        try {
            return HibernateUtil.getSessionFactory();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Could Not create SessionFactory", ex);
            throw new IllegalStateException("Could not create SessionFactory");
        }
    }

    // Get a product using its ID
    public Product get1(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        logger.info("Product loaded successfully, Product details=" + product); 
        return product;
    }


    // List all products
    @Override
    public List<Product> list() {
        List<Product> productsList = new ArrayList<>();
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.beginTransaction();
            productsList = session.createQuery("select p from Product p", Product.class).list();
            session.getTransaction().commit();
            return productsList;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error in listing all products", e);
            return productsList;
        }
    }

    // Add a product with a category
    public void add(Product product, Long categoryId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        
        Category category = categoryService.get(categoryId);
        if (category != null) {
            product.setCategory(category);
        }

        session.persist(product);
        session.getTransaction().commit();
        
        logger.info("Product saved successfully, Product Details=" + product);
    }
    // Update a product with a category
    public void update(Product product, Long categoryId) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();

        // Set the selected Category on the Product
        Category category = categoryService.get(categoryId);
        if (category != null) {
            product.setCategory(category);
        }

        session.merge(product);
        session.getTransaction().commit();
        logger.info("Product updated successfully, Product Details=" + product);
    }

    // Select a product using a keyword
    @Override
    public List<Product> selectByKeyword(String str) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Product> productsList = session.createQuery("from Product p WHERE p.name LIKE :keyword", Product.class)
                .setParameter("keyword", "%" + str + "%").list();
        session.getTransaction().commit(); 
        return productsList;
    }

    // Remove a product
    @Override
    public void remove(Long id) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        if (product != null) {
            session.remove(product);
        }
        session.getTransaction().commit();
        logger.info("Product deleted successfully, Product details=" + product);
    }

	@Override
	public Product get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Product t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product getById(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}
}
