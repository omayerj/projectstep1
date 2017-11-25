package sql.queries;

public class JoinTableSQLQueries {

	//////////////////////////////////////////////////////////
	/////////////// TABLE CUSTOMER_COUPON
	///////////////////////////////////////////////////////////

	public static String GET_CUSTOMER_COUPON = "SELECT * FROM CUSTOMER_COUPON WHERE CUST_ID = ? AND COUPON_ID = ?";
	public static String ADD_CUSTOMER_COUPON = "INSERT INTO CUSTOMER_COUPON (CUST_ID,COUPON_ID) VALUES (?, ?)";
	public static String REMOVE_CUSTOMER_COUPON_BY_CUST_ID = "DELETE  FROM CUSTOMER_COUPON WHERE CUST_ID=?";
	public static String REMOVE_CUSTOMER_COUPON_BY_COUPON_ID = "DELETE  FROM CUSTOMER_COUPON WHERE COUPON_ID=?";

	//////////////////////////////////////////////////////////
	/////////////// TABLE COMPANY_COUPON
	///////////////////////////////////////////////////////////

	public static String ADD_COMPANY_COUPON = "INSERT INTO COMPANY_COUPON (COMP_ID,COUPON_ID) VALUES (?, ?)";
	public static String GET_COMPANY_COUPON_BY_COMP_ID = "SELECT * FROM COMPANY_COUPON WHERE COMP_ID=?";
	public static String REMOVE_COMPANY_COUPON_BY_COMP_ID = "DELETE  FROM COMPANY_COUPON WHERE COMP_ID=?";
	public static String REMOVE_COMPANY_COUPON_COUPON_BY_COUPON_ID = "DELETE  FROM COMPANY_COUPON WHERE COUPON_ID=?";

	//////////////////////////////////////////////////////////
	/////////////// JOIN
	///////////////////////////////////////////////////////////

	public static String GET_All_PURCHASES = "select * FROM Customer_Coupon JOIN Customer on (Customer.ID=Customer_Coupon.CUST_ID) JOIN coupon on (Customer_Coupon.COUPON_ID=coupon.ID) where Customer.id = ?";
	public static String GET_All_PURCHASES_TYPE = "select * FROM Customer_Coupon JOIN Customer on (Customer.ID=Customer_Coupon.CUST_ID) JOIN coupon on (Customer_Coupon.COUPON_ID=coupon.ID) where Customer.id = ? AND coupon.TYPE=?";
	public static String GET_All_PURCHASES_PRICE = "select * FROM Customer_Coupon JOIN Customer on (Customer.ID=Customer_Coupon.CUST_ID) JOIN coupon on (Customer_Coupon.COUPON_ID=coupon.ID) where Customer.id = ?  and coupon.PRICE < ?";

	public static String GET_All_COUPON_ALL_MAX_DATE_LESS = "select * FROM COMPANY_COUPON JOIN COMPANY on (COMPANY.ID=COMPANY_COUPON.COMP_ID) JOIN coupon on (COMPANY_COUPON.COUPON_ID=coupon.ID) where COMPANY.id = ?  and coupon. END_DATE <  ?";
	public static String GET_All_COUPON_ALL_PRICE_LESS = "select * FROM COMPANY_COUPON JOIN COMPANY on (COMPANY.ID=COMPANY_COUPON.COMP_ID) JOIN coupon on (COMPANY_COUPON.COUPON_ID=coupon.ID) where COMPANY.id = ?  and coupon.price <  ?";
	public static String GET_COUPON_BY_TITLE_FOR_COMPANY = "select * FROM COMPANY_COUPON JOIN COMPANY on (COMPANY.ID=COMPANY_COUPON.COMP_ID) JOIN coupon on (COMPANY_COUPON.COUPON_ID=coupon.ID) where COMPANY.id = ?  AND coupon.TYPE=?";
	public static String GET_ALL_COUPON_BY_COMPANY = "select * FROM COMPANY_COUPON JOIN COMPANY on (COMPANY.ID=COMPANY_COUPON.COMP_ID) JOIN coupon on (COMPANY_COUPON.COUPON_ID=coupon.ID) where COMPANY.id = ?";

}
