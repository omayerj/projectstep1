package coupon.client.facade;

import java.util.Collection;

import javax.management.RuntimeErrorException;

import DBDAO.CompanyDBDAO;
import DBDAO.CouponDBDAO;
import DBDAO.CustomerDBDAO;
import DBDAO.JoinTableDBDAO;
import object.java.bens.ClientType;
import object.java.bens.Company;
import object.java.bens.Coupon;
import object.java.bens.Customer;

public class AdminFacade implements CouponClientFacade {

	private CompanyDBDAO companyDBdao = new CompanyDBDAO();
	private CustomerDBDAO customerDBdao = new CustomerDBDAO();
	private CouponDBDAO couponDBdao = new CouponDBDAO();
	private JoinTableDBDAO JoinTableDBdao =new JoinTableDBDAO();
	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		if (name.equals("admin") && password.equals("1234")) {
			return this;
		}
		return null;
	}

	public void createCompany(Company c) {
		System.out.println("AdminFacade::createCompany");
		Company companyDB = companyDBdao.getCompanyByName(c.getCompName());
		if (companyDB == null) {
			companyDBdao.createCompany(c);
			return;
		}
		throw new RuntimeErrorException(null, "this Company name is exists " + companyDB.getCompName());
	}

	public Collection<Company> getAllCompanies() {
		return companyDBdao.getAllCompanies();
	}

	public void removeCompany(Company c) {
		System.out.println("AdminFacade::removeCompany");
		Collection<Long> couponIdS = companyDBdao.getCouponsId(c);
		for (Long long1 : couponIdS) {
			Coupon coupon =new Coupon();
			coupon.setId(long1);
			couponDBdao.removeCoupon(coupon);
			JoinTableDBdao.deleteCoupon(coupon);
		}
		companyDBdao.removeCompany(c);
	}

	public void updateCompany(Company c) {
		companyDBdao.updateCompany(c);
	}

	public Company getCompany(long id) {
		return companyDBdao.getCompany(id);
	}

	
	////////////////////////////////////
	// Customer
	/////////////////////////////////////

	public void createCustomer(Customer c) {

		System.out.println("AdminFacade::createCustomer");
		Customer customer = customerDBdao.getCustomerByName(c.getCustName());
		if (customer == null) {
			customerDBdao.createCustomer(c);
			return;
		}
		throw new RuntimeErrorException(null, "customer name is exists " + customer.toString());
	}

	// TODO
	public void updateCustomer(Customer c) {
		customerDBdao.updateCustomer(c);
	}

	public Collection<Customer> getAllCustomers() {
		return customerDBdao.getAllCustomers();
	}

	public Customer getCustomer(long id) {
		return customerDBdao.getCustomer(id);
	}

	public void removeCustomer(Customer c){
		System.out.println("AdminFacade::removeCustomer");
		customerDBdao.removeCustomer(c);
		JoinTableDBdao.deleteCustomer_Coupon(c);
				
	}
}
