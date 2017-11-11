package DAO;

import Exception.System.DAOException;
import object.java.bens.Company;
import object.java.bens.Coupon;
import object.java.bens.Customer;

public interface JoinTableDAO {

	void deleteCoupon(Coupon c) throws DAOException;

	
	void deleteCustomer_Coupon(Customer customer);


	void deleteCustomer(Customer customer) throws DAOException;


	void createCustomer_Coupon(Customer customer, Coupon coupon) throws DAOException;

	
	void createCompany_Coupon(Company company, Coupon coupon);

	
	boolean isThereCompanyCoupon(long compID, long couponID);

	
	boolean isThereCustomerCoupon(long custID, long couponID);

}
