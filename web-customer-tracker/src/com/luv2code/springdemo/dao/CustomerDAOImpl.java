package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// Inject session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		// Get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// Create a query
		Query<Customer> theQuery = session.createQuery("FROM Customer ORDER BY lastName",Customer.class);
		
		// Execute and get result list
		List<Customer> customers = theQuery.getResultList();
		
		// Return result list
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
			
		// Get current session
		Session session = sessionFactory.getCurrentSession();
		
		// Save the customer - Write HQL
		session.saveOrUpdate(theCustomer);
	
	}

	@Override
	public Customer getCustomerById(int theId) {
		
		// Get current SEssion
		Session session = sessionFactory.getCurrentSession();
		
		// Get customer by Id :
		
		Customer customer = session.get(Customer.class, theId);
		
		// Return the customer
		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		// Get current SEssion
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Query
		Query theQuery = session.createQuery("DELETE FROM Customer WHERE id =: customerId");
		
		// Set parameters
		theQuery.setParameter("customerId",theId);
		
		// Execute Query !
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		 Query<Customer> theQuery = null;
	        
	        //
	        // only search by name if theSearchName is not empty
	        //
	        if (theSearchName != null && theSearchName.trim().length() > 0) {
	            // search for firstName or lastName ... case insensitive
	            theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
	            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
	        }
	        else {
	            // theSearchName is empty ... so just get all customers
	            theQuery =currentSession.createQuery("from Customer", Customer.class);            
	        }
	        
	        // execute query and get result list
	        List<Customer> customers = theQuery.getResultList();
	                
	        // return the results        
	        return customers;
	}
	
	
	
	
}
