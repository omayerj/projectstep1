package coupon.system;

import java.sql.SQLException;

import coupon.client.facade.AdminFacade;
import coupon.client.facade.CompanyFacade;
import coupon.client.facade.CouponClientFacade;
import coupon.client.facade.CustomerFacade;
import object.java.bens.ClientType;

public class CouponSystem {
	//private ConnectionPool connectionPool;
	private boolean shadwon;

	public boolean isShadwon() {
		return shadwon;
	}

	private static CouponSystem INSTANCE;
	
	private CouponSystem()
	{
		System.out.println("////////////////CouponSystem///////////////////////");
	}
	
	public synchronized static CouponSystem getInstance()
	{
		System.out.println("getInstance");
	
		if (INSTANCE == null)
		{
			INSTANCE = new CouponSystem();
		}
		return INSTANCE;
	}
	
	public CouponClientFacade login(String name, String password, ClientType clientType)
	{
		System.out.println("login");
		System.out.println("///////////////////////////////////////");
		System.out.println("name : "+name+" password : "+ password+" Client Type : " +clientType);
		System.out.println("///////////////////////////////////////");
		
		CouponClientFacade result = null;
		switch (clientType)
		{
			case ADMINISTRATOR:
					result = new AdminFacade().login(name, password, clientType);
				break;
			case COMPANY:
				result = new CompanyFacade().login(name, password, clientType);
			break;
			case CUSTOMER:
				result = new CustomerFacade().login(name, password, clientType);
			break;				
				
		}
		return result;
		
	}
	
	public void shutdown (){
		
			shadwon=true;
			try {
			ConnectionPool.getInstance().closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
	
	
}
