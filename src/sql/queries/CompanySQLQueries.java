package sql.queries;

public class CompanySQLQueries {

	//////////////////////////////////////////////////////////
	/////////////// TABLE COMPANY
	///////////////////////////////////////////////////////////
	public static String SELECT_ALL_COMPANIES = "SELECT * FROM COMPANY";
	public static String SELECT_COMPANIES_BY_NAME = "SELECT * FROM COMPANY  where COMP_NAME=?";
	public static String CREATE_COMPANY = "INSERT INTO COMPANY (COMP_NAME, PASSWORD, EMAIL) VALUES (?, ?, ?)";
	public static String REMOVE_COMPANY = "DELETE  FROM COMPANY WHERE ID=";
	public static String UPDATE_COMPANY = "UPDATE COMPANY set COMP_NAME= ?, PASSWORD=? ,EMAIL=? where ID=?";
	public static String GET_COMPANY_BY_ID = "SELECT * FROM COMPANY where ID=?";
	public static String LOGIN_BY_COMPANIES = "SELECT * FROM COMPANY where COMP_NAME=? and PASSWORD=?";

}
