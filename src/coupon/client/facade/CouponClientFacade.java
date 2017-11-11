package coupon.client.facade;

import object.java.bens.ClientType;

public interface CouponClientFacade {

	CouponClientFacade login(String name, String password, ClientType clientType);
	
}
