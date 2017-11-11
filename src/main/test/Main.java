package main.test;
//import java.util.Date;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import coupon.client.facade.AdminFacade;
import coupon.system.CouponSystem;
import object.java.bens.ClientType;
import object.java.bens.Company;

public class Main {

	public static void main(String[] args) {
		
		/**
		 * Test for admin Facade
		 * 
		 */
	
		AdminFacade adminFacade = (AdminFacade) CouponSystem.getInstance().login("admin", "1234", ClientType.ADMINISTRATOR);
		System.out.println("****adminFacade***** :"+ adminFacade );
		if (adminFacade != null)
			{
			Company testCompany1= new Company("novanym2220", "1234", "novanym@novanym.com");

			Company testCompany= new Company("novanym220", "1234", "novanym@novanym.com");
			adminFacade.createCompany(testCompany);
			adminFacade.createCompany(testCompany1);
			 ArrayList<Company> obj = (ArrayList<Company>) adminFacade.getAllCompanies();
			 obj.get(2);
			 System.out.println("tsesy");

			 System.out.println(obj.get(2));
			adminFacade.removeCompany(obj.get(2));
			
			}
		
		/**
		 * Test for company facade
		 * 
		 */
	/*	
		CompanyFacade companyFacade = (CompanyFacade) CouponSystem.getInstance().login("company1", "1234", ClientType.COMPANY);
		System.out.println("****companyFacade***** :"+ companyFacade );
		if(companyFacade != null){
			Coupon coupin=  new Coupon("the best Coupon1 good 171",dateToDateSql(2015, 5, 30) , dateToDateSql(2017,10,10), 3,CouponType.SPORTS, "you can I can ", 100.00);
			companyFacade.createCoupon(coupin);
			
			Coupon c = new Coupon(6, "das");
			companyFacade.removeCoupon(c);
			c = new Coupon(5, "das");
			c.setEndDate(dateToDateSql(2017,10,22));
			c.setStartDate(dateToDateSql(2015,10,5));
			companyFacade.updateCoupon(c);
			System.out.println(companyFacade.getCouponsWithMaxEndDate(dateToDateSql(2019,10,1)));
			System.out.println(companyFacade.getCouponByType(CouponType.FOOD)); 
			double price =12.0;
			System.out.println(companyFacade.getCouponByPrice(price));
			System.out.println(companyFacade.getAllCoupon());
		}
	*/	
		
		/**
		 * test for customer facade
		 */
		/*
		CustomerFacade customerFacade = (CustomerFacade) CouponSystem.getInstance().login("newcustmer", "12345", ClientType.CUSTOMER);
		System.out.println("****CustomerFacade***** :"+ customerFacade );
		if(customerFacade != null){
			System.out.println("custName : newcustmer");
//			Coupon couponnew=  new Coupon(5, "title");
//			customerFacade.purchaseCoupon(couponnew);
			System.out.println(customerFacade.getAllPurchases());
			
			double price =12.0;
			System.out.println(customerFacade.getAllPurchasesByPrice(price ));
			CouponType Type =CouponType.FOOD;
			System.out.println(customerFacade.getAllPurchasesByType(Type ));
						
		}
		 */
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

//		AdminFacade adminFacade = (AdminFacade) CouponSystem.getInstance().login("admin", "1234", ClientType.ADMINISTRATOR);
//		if (adminFacade != null)
//		{
////			adminFacade.createCompany(new Company("companeTEST","123zx5","sad56zxds@dsas.sad"));
////			adminFacade.createCompany(new Company("companeTesr1001","1zx235","saddzxs@dsas.sad"));
//			adminFacade.createCompany(new Company("compane1001","1|ZX235","sadds@dsaszx.sad"));
////		////////////////add and delete
//			Collection<Company> companies = adminFacade.getAllCompanies();
//			System.out.println(companies.toString());
//			adminFacade.removeCompany((Company)companies.toArray()[companies.size()-1]);
////		/////////
//			companies = adminFacade.getAllCompanies();
////			System.out.println(companies.toString());
////			Company Company1=(Company)companies.toArray()[companies.size()-1];
////			Company1.setCompName("qwas123");
////			adminFacade.updateCompany(Company1);
////			Company companyById = adminFacade.getCompany(15);
////			System.out.println("+++++++++++++++++++++++++++++++");
////			System.out.println(companyById.toString());
//
////			test 
////			CouponSystem.getInstance().shutdown();
//		
////		
//			
//		}
		
	
	}

private static Date dateToDateSql(int year,int month,int day) {
	 
	Calendar cal = Calendar.getInstance();
	cal.set(Calendar.YEAR, year);
	cal.set(Calendar.MONTH, month - 1);
	cal.set(Calendar.DAY_OF_MONTH, day);
	return new Date(cal.getTimeInMillis());
}
}
