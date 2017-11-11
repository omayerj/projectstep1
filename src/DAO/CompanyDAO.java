package DAO;

import java.util.Collection;

import object.java.bens.Company;
import object.java.bens.Coupon;

public interface CompanyDAO {

	void createCompany(Company c);
	void removeCompany(Company c);
	void updateCompany(Company c);
	void removeFromCompanyCoupon(Company c);	
	Company getCompany(long id);
	Company getCompanyByName(String name);
	Collection<Company> getAllCompanies();
	Collection<Coupon> getCoupons();
	boolean login(String compName,String password);

}
