package com.luv2code.springdemo.dao;

import com.luv2code.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // create a query
        Query theQuery = session.createQuery("from Customer order by lastName", Customer.class);

        // get the result form query
        List<Customer> customers = theQuery.getResultList();

        // return result
        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int id) {

        // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // now receive data from the database using primary key
        Customer customer = session.get(Customer.class, id);

        return customer;
    }

    @Override
    public void deleteCustomer(int id) {
        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", id);

        query.executeUpdate();
    }
}
