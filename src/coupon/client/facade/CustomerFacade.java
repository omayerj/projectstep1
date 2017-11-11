package coupon.client.facade;

import java.sql.Date;
import java.util.Collection;

import javax.management.RuntimeErrorException;

import DBDAO.CouponDBDAO;
import DBDAO.CustomerDBDAO;
import DBDAO.JoinTableDBDAO;
import object.java.bens.ClientType;
import object.java.bens.Coupon;
import object.java.bens.CouponType;
import object.java.bens.Customer;

public class CustomerFacade implements CouponClientFacade {

	private Customer thisCustomer;

	private CustomerDBDAO customerDBdao = new CustomerDBDAO();
	private CouponDBDAO couponDBdao = new CouponDBDAO();
	private JoinTableDBDAO JoinTableDBdao = new JoinTableDBDAO();

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		System.out.println(" CustomerFacade::login");
		if (customerDBdao.login(name, password)) {
			thisCustomer = customerDBdao.getCustomerByName(name);
			
			return this;
		}
		return null;
	}

	/**
	 * Perform a customer client purchase of coupon by changing the relevent
	 * data on database.
	 * 
	 * @param coupon
	 *            The coupon to purchase. Only ID matters.
	 */
	public void purchaseCoupon(Coupon c) {
		System.out.println(" CustomerFacade::purchaseCoupon");

		Coupon couponFromDB = couponDBdao.getCoupon(c.getId());
		System.out.println(this.thisCustomer.toString());
		if (JoinTableDBdao.isThereCustomerCoupon(this.thisCustomer.getId(), couponFromDB.getId()))
			throw new RuntimeErrorException(null,
					"you have already purchased this coupon, and therefore cannot purchase it again!");

		if (couponFromDB.getAmount() == 0)
			throw new RuntimeErrorException(null, "Coupon out of stock!");

		Date now = new Date(System.currentTimeMillis());
		if (couponFromDB.getEndDate().compareTo(now) < 1)
			throw new RuntimeErrorException(null, "Coupon is Expired !");

		couponFromDB.setAmount(couponFromDB.getAmount() - 1);
		JoinTableDBdao.createCustomer_Coupon(thisCustomer, couponFromDB);
		couponDBdao.updateCoupon(couponFromDB);

	}

	public Collection<Coupon> getAllPurchases() {
		return JoinTableDBdao.getAllPurchases(this.thisCustomer.getId());
	}
	public Collection<Coupon> getAllPurchasesByType(CouponType  Type){
		return JoinTableDBdao.getAllPurchasesByTypeDAO(this.thisCustomer.getId(),Type);
	}
	public  Collection<Coupon> getAllPurchasesByPrice(double price){
		return JoinTableDBdao.getAllPurchasesByPriceDAO(this.thisCustomer.getId(),price);
		
	}
	public void createCustomer(Customer c) {
		customerDBdao.createCustomer(c);
	}

	public void removeCustomer(Customer c) {
		customerDBdao.removeCustomer(c);
	}

}
