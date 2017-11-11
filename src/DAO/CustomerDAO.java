package DAO;

import java.util.Collection;

import object.java.bens.Coupon;
import object.java.bens.Customer;

public interface CustomerDAO {
	void createCustomer(Customer c);
	void removeCustomer(Customer c);
	void updateCustomer(Customer c);
	Customer getCustomer(long id);
	Customer getCustomerByName(String name );
	Collection<Customer> getAllCustomers();
	Collection<Coupon> getCoupons();
	boolean login(String custName,String password);


}
