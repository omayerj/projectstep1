package sql.queries;

public class CouponSQLQueries {
	
	//////////////////////////////////////////////////////////
	/////////////// TABLE  COUPON
	///////////////////////////////////////////////////////////
	
	public static String CREATE_COUPON = "INSERT INTO COUPON  (TITLE,STARTING_DATE,END_DATE,AMOUNT, TYPE, MESSAGE, PRICE) VALUES (?, ?, ?,?, ?, ?,?)";
	public static String REMOVE_COUPON = "DELETE  FROM COUPON WHERE ID=";
	public static String UPDATE_COUPON = "UPDATE COUPON set END_DATE= ?, STARTING_DATE=? where ID=?";
	public static String GET_COUPON_BY_ID = "SELECT * FROM COUPON where ID=?";
	public static String GET_COUPON_BY_TYPE = "SELECT * FROM COUPON where TYPE = ?";
	public static String GET_COUPON_BY_TITLE="SELECT * FROM COUPON where TITLE = ?";
	public static String GET_COUPONS_LESS_FROM_NOW="SELECT * FROM COUPON where END_DATE <  ?";

}
