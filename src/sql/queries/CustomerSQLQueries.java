package sql.queries;

public class CustomerSQLQueries {
//////////////////////////////////////////////////////////
/////////////// TABLE  CUSTOMER
///////////////////////////////////////////////////////////

public static String LOGIN_BY_CUSTOMER = "SELECT * FROM CUSTOMER where CUST_NAME=? and PASSWORD=?";
public static String SELECT_BY_CUSTOMER_NAME = "SELECT * FROM CUSTOMER where CUST_NAME=?";	
public static String CREATE_CUSTOMER = "INSERT INTO CUSTOMER (CUST_NAME, PASSWORD) VALUES (?, ?)";
public static String REMOVE_CUSTOMER = "DELETE  FROM CUSTOMER WHERE ID=?";
public static String UPDATE_CUSTOMER = "UPDATE CUSTOMER set CUST_NAME= ?, PASSWORD=? where ID=?";
public static String GET_CUSTOMER_BY_ID = "SELECT * FROM CUSTOMER where ID=?";
public static String GET_CUSTOMER_BY_NAME = "SELECT * FROM CUSTOMER where CUST_NAME=?";
public static String GET_ALL_CUSTOMERS = "SELECT * FROM CUSTOMER";
}
