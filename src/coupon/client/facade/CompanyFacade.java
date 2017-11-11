package coupon.client.facade;

import java.sql.Date;
import java.util.Collection;

import javax.management.RuntimeErrorException;

import DBDAO.CompanyDBDAO;
import DBDAO.CouponDBDAO;
import DBDAO.JoinTableDBDAO;
import object.java.bens.ClientType;
import object.java.bens.Company;
import object.java.bens.Coupon;
import object.java.bens.CouponType;

public class CompanyFacade implements CouponClientFacade {

	private CompanyDBDAO companyDBdao = new CompanyDBDAO();

	private CouponDBDAO couponDBdao = new CouponDBDAO();

	private JoinTableDBDAO JoinTableDBdao = new JoinTableDBDAO();

	private Company thisCompany;

	@Override
	public CouponClientFacade login(String name, String password, ClientType clientType) {
		System.out.println(" CompanyFacade::login");
		if (companyDBdao.login(name, password)) {
			thisCompany = companyDBdao.getCompanyByName(name);
			return this;
		}
		return null;
	}

	public Coupon getCoupon(long id) {
		return couponDBdao.getCoupon(id);
	}

	public Collection<Coupon> getCouponByType(CouponType type) {
		System.out.println("CompanyFacade::getCouponByType");
		return JoinTableDBdao.getCouponsByTypeAndCompany(this.thisCompany.getId(), type);
	}

	public Collection<Coupon> getAllCoupon() {

		return companyDBdao.getCoupons();
	}

	public void removeCoupon(Coupon c) {
		System.out.println("CompanyFacade::RemoveCoupon");
		couponDBdao.removeCoupon(c);
		JoinTableDBdao.deleteCoupon(c);
	}

	public void createCoupon(Coupon c) {
		System.out.println("CompanyFacade::createCoupon");

		Coupon coupnsFromDB = couponDBdao.getCouponByTitle(c.getTitle());
		if (coupnsFromDB == null) {
			couponDBdao.createCoupon(c);
			Coupon CouponDB = couponDBdao.getCouponByTitle(c.getTitle());
			JoinTableDBdao.createCompany_Coupon(this.thisCompany, CouponDB);
		} else {
			throw new RuntimeErrorException(null, "Coupon title is exists");
		}
	}

	public void updateCoupon(Coupon c) {
		Coupon updateCouponforDB = new Coupon();
		updateCouponforDB.setId(c.getId());
		updateCouponforDB.setEndDate(c.getEndDate());
		updateCouponforDB.setStartDate(c.getStartDate());

		couponDBdao.updateCoupon(c);
	}

	public Collection<Coupon> getCouponsWithMaxEndDate(Date maxEndDate) {
		System.out.println("CompanyFacade::getCouponsWithMaxEndDate");
		Collection<Coupon> lestCompanyS = JoinTableDBdao.getCouponsWithMaxEndDateDB(thisCompany.getId(), maxEndDate);
		return lestCompanyS;

	}

	public Collection<Coupon> getCouponByPrice(double price) {
		System.out.println("CompanyFacade::getCouponByPrice");
		Collection<Coupon> lestCompanyS = JoinTableDBdao.getCouponsWithMaxPriceDB(thisCompany.getId(), price);
		return lestCompanyS;
		
	}
}
