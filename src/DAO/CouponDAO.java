package DAO;

import java.util.Collection;

import object.java.bens.Coupon;
import object.java.bens.CouponType;

public interface CouponDAO {
	void createCoupon(Coupon c);
	void removeCoupon(Coupon c);
	void updateCoupon(Coupon c);
	Coupon getCoupon(long id);
	Coupon getCouponByTitle(String title);
	Collection<Coupon> getAllCoupon();
	Collection<Coupon> getCouponByType(CouponType type);
}
