package DBDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import DAO.CustomerDAO;
import coupon.system.ConnectionPool;
import object.java.bens.Coupon;
import object.java.bens.Customer;
import sql.queries.CompanySQLQueries;

public class CustomerDBDAO implements CustomerDAO {

	private ConnectionPool connectionPool;
	private long loginId;
	public CustomerDBDAO()
	{
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createCustomer(Customer c) {
		System.out.println("createCustomer");
		System.out.println("///////////////////////////////////////");
		System.out.println(c.toString());
		System.out.println("///////////////////////////////////////");
		Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			
			PreparedStatement preparedStatement =
					conn.prepareStatement(CompanySQLQueries.CREATE_CUSTOMER);
			preparedStatement.setString(1, c.getCustName());
			preparedStatement.setString(2, c.getPassword());


			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " rows affected");
			
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		

	}

	@Override
	public void removeCustomer(Customer c) {
		System.out.println("removeCustomer");
		System.out.println("///////////////////////////////////////");
		System.out.println(c.toString());
		System.out.println("///////////////////////////////////////");
		Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			
			PreparedStatement preparedStatement =
					conn.prepareStatement(CompanySQLQueries.REMOVE_COMPANY);
			preparedStatement.setLong(1, c.getId());
			System.out.println("SQL ****************SQL");
			System.out.println(preparedStatement.toString());
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " rows affected");
			
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}

	}

	@Override
	public void updateCustomer(Customer c) {
		System.out.println("updateCustomer");
		System.out.println("///////////////////////////////////////");
		System.out.println(c.toString());
		System.out.println("///////////////////////////////////////");
		Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			PreparedStatement preparedStatement =
					conn.prepareStatement(CompanySQLQueries.UPDATE_CUSTOMER);
			preparedStatement.setString(1, c.getCustName());
			preparedStatement.setString(2, c.getPassword());
			preparedStatement.setString(3, c.getId()+"");
			
			System.out.println("SQL ****************SQL");
			System.out.println(preparedStatement.toString());
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " rows affected");
			
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}


		
	}

	@Override
	public Customer getCustomer(long id) {

		System.out.println("getCompanyByID");
		System.out.println("///////////////////////////////////////");
		Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			ResultSet rs;

			PreparedStatement preparedStatement =
			conn.prepareStatement(CompanySQLQueries.GET_CUSTOMER_BY_ID);
			preparedStatement.setLong(1, id);
			rs = preparedStatement.executeQuery();
			System.out.println("SQL ****************SQL");
			System.out.println(preparedStatement.toString());
			
			while (rs.next()) {
//				int id = rs.getInt("ID");
				String custName = rs.getString("CUST_NAME");
				String password =rs.getString("PASSWORD");
				Customer c = new Customer();
				c.setId(id);
				c.setCustName(custName);
				c.setPassword(password);
				
				return c;
			}
			
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		return null;
	}

	@Override
	public Collection<Customer> getAllCustomers() {
		Connection conn = null;
		ArrayList<Customer> result = new ArrayList<>();
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs;

			
			rs = stmt.executeQuery(CompanySQLQueries.GET_ALL_CUSTOMERS);
			while (rs.next()) {
				int id = rs.getInt("ID");
				String custName = rs.getString("CUST_NAME");
				String password =rs.getString("PASSWORD");
				Customer c = new Customer();
				c.setId(id);
				c.setCustName(custName);
				c.setPassword(password);
//				c.setCoupons(coupons);
				result.add(c);
			}
			
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		System.out.println("getAllCustomers");
		System.out.println("///////////////////////////////////////");
		return result;
	}

	
	@Override
	public Collection<Coupon> getCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(String custName, String password) {
		boolean log = false;
		Connection conn = null;

		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			ResultSet rs;
			
			PreparedStatement statement = conn.prepareStatement(CompanySQLQueries.LOGIN_BY_CUSTOMER);    
			statement.setString(1, custName);
			statement.setString(2, password); 
			rs = statement.executeQuery();
			
			System.out.println("SQL ****************SQL");
			System.out.println(statement.toString());

			while (rs.next()) {
				int id = rs.getInt("ID");
				String cName = rs.getString("CUST_NAME");
				String pass = rs.getString("PASSWORD");

				if (cName != null) {
					log = true;
					Customer c = new Customer();
					c.setId(id);
					c.setCustName(custName);
					c.setPassword(pass);
					loginId=id;
				}
			}

		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		System.out.println("///////////////////////////////////////");
		System.out.println("login by Customer : " +log);
		System.out.println("///////////////////////////////////////");
		return log;
	}

	@Override
	public Customer getCustomerByName(String name) {
		System.out.println("getCustomerByName");
		System.out.println("///////////////////////////////////////");
		Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			ResultSet rs;

			PreparedStatement preparedStatement =
			conn.prepareStatement(CompanySQLQueries.GET_CUSTOMER_BY_NAME);
			preparedStatement.setString(1, name);
			rs = preparedStatement.executeQuery();
			System.out.println("SQL ****************SQL");
			System.out.println(preparedStatement.toString());
			
			while (rs.next()) {
				int id = rs.getInt("ID");
				String password =rs.getString("PASSWORD");
				Customer c = new Customer();
				c.setId(id);
				c.setCustName(name);
				c.setPassword(password);
				System.out.println(c.toString());
				return c;
			}
			
		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		}
		finally
		{
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		return null;
		}

}
