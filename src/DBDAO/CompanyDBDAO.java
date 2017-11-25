package DBDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import DAO.CompanyDAO;
import coupon.system.ConnectionPool;
import object.java.bens.Company;
import object.java.bens.Coupon;
import object.java.bens.CouponType;
import sql.queries.CompanySQLQueries;
import sql.queries.JoinTableSQLQueries;

public class CompanyDBDAO implements CompanyDAO {

	private ConnectionPool connectionPool;
	private long loginId;

	public CompanyDBDAO() {
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	@Override
	public Collection<Coupon> getCoupons() {

		Connection conn = null;
		ArrayList<Coupon> listReslt = new ArrayList<>();

		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			System.out.println(this.loginId);
//			Statement stmt = conn.createStatement();
			ResultSet rs;

	
			PreparedStatement preparedStatement = conn.prepareStatement(JoinTableSQLQueries.GET_ALL_COUPON_BY_COMPANY);
			preparedStatement.setLong(1, this.loginId);
			rs = preparedStatement.executeQuery();
			System.out.println("SQL ****************SQL");
			System.out.println(preparedStatement.toString());

			while (rs.next()) {
				Long id =rs.getLong("ID");
				String title=  rs.getString("TITLE");
				Date startDate =rs.getDate("STARTING_DATE");
				Date endDate=rs.getDate("END_DATE");
				int amount=rs.getInt("AMOUNT");
				String type = rs.getString("TYPE");
				String message=rs.getString("MESSAGE");
				double price=rs.getDouble("PRICE");
				
				Coupon c = new Coupon();
				c.setId(id);
				c.setTitle(title);
				c.setStartDate(startDate);
				c.setEndDate(endDate);
				c.setAmount(amount);
				c.setType(Enum.valueOf(CouponType.class,type));
				c.setMessage(message);
				c.setPrice(price);
				System.out.println( c.toString());
				listReslt.add(c);		
				
			}

		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		System.out.println("getAllCompanies");
		System.out.println("///////////////////////////////////////");
		return listReslt;

	}
	
	public Collection<Long> getCouponsId(Company c) {

		Connection conn = null;
		// ArrayList<Company> result = new ArrayList<>();
		ArrayList<Long> couponIdS = new ArrayList<>();
		try {

			conn = connectionPool.getConnection();
			ResultSet rs;

			PreparedStatement preparedStatement = conn
					.prepareStatement(JoinTableSQLQueries.GET_COMPANY_COUPON_BY_COMP_ID);
			preparedStatement.setLong(1, c.getId());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
//				long companyId = rs.getLong("COMP_ID");
				long couponId = rs.getLong("COUPON_ID");
				couponIdS.add(couponId);
			}

		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		System.out.println("get All Coupons Id");
		System.out.println("///////////////////////////////////////");
		System.out.println(couponIdS);
		
		return couponIdS;
	}

	@Override
	public Collection<Company> getAllCompanies() {

		Connection conn = null;
		ArrayList<Company> result = new ArrayList<>();

		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			Statement stmt = conn.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery(CompanySQLQueries.SELECT_ALL_COMPANIES);
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("COMP_NAME");
				String password = rs.getString("PASSWORD");
				String email = rs.getString("EMAIL");

				Company c = new Company();
				c.setId(id);
				c.setCompName(name);
				c.setEmail(email);
				c.setPassword(password);

				result.add(c);
			}

		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		System.out.println("getAllCompanies");
		System.out.println("///////////////////////////////////////");
		return result;

	}

	@Override
	public void createCompany(Company c) {
		System.out.println("createCompany");
		System.out.println("///////////////////////////////////////");
		System.out.println(c.toString());
		System.out.println("///////////////////////////////////////");
		Connection conn = null;

		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			PreparedStatement preparedStatement = conn.prepareStatement(CompanySQLQueries.CREATE_COMPANY);
			preparedStatement.setString(1, c.getCompName());
			preparedStatement.setString(2, c.getPassword());
			preparedStatement.setString(3, c.getEmail());

			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " rows affected");

		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				connectionPool.returnConnection(conn);
		}

	}

	public boolean login(String name, String password) {
		boolean log = false;
		Connection conn = null;

		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			ResultSet rs;

			PreparedStatement statement = conn.prepareStatement(CompanySQLQueries.LOGIN_BY_COMPANIES);
			statement.setString(1, name);
			statement.setString(2, password);
			rs = statement.executeQuery();

			System.out.println("SQL ****************SQL");
			System.out.println(statement.toString());

			while (rs.next()) {
				long id = rs.getLong("ID");
				String cName = rs.getString("COMP_NAME");
				String pass = rs.getString("PASSWORD");
				String email = rs.getString("EMAIL");

				if (cName != null) {
					log = true;
					Company c = new Company();
					c.setId(id);
					c.setCompName(cName);
					c.setPassword(pass);
					c.setEmail(email);
					loginId = id;
				}

			}

		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		System.out.println("///////////////////////////////////////");
		System.out.println("login by Company : " + log);
		System.out.println("///////////////////////////////////////");
		return log;
	}

	@Override
	public void removeCompany(Company c) {
		System.out.println("removeCompany");
		System.out.println("///////////////////////////////////////");
		System.out.println(c.toString());
		System.out.println("///////////////////////////////////////");
		Connection conn = null;

		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			PreparedStatement preparedStatement = conn.prepareStatement(CompanySQLQueries.REMOVE_COMPANY + c.getId());

			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " rows affected");

		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				connectionPool.returnConnection(conn);
		}

	}

	@Override
	public void updateCompany(Company c) {
		System.out.println("updateCompany");
		System.out.println("///////////////////////////////////////");
		System.out.println(c.toString());
		System.out.println("///////////////////////////////////////");
		Connection conn = null;

		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			PreparedStatement preparedStatement = conn.prepareStatement(CompanySQLQueries.UPDATE_COMPANY);
			preparedStatement.setString(1, c.getCompName());
			preparedStatement.setString(2, c.getPassword());
			preparedStatement.setString(3, c.getEmail());
			preparedStatement.setString(4, c.getId() + "");
			System.out.println("SQL ****************SQL");
			System.out.println(preparedStatement.toString());
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " rows affected");

		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				connectionPool.returnConnection(conn);
		}

	}

	@Override
	public Company getCompany(long id) {

		System.out.println("getCompanyByID");
		System.out.println("///////////////////////////////////////");
		Connection conn = null;

		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			ResultSet rs;

			PreparedStatement preparedStatement = conn.prepareStatement(CompanySQLQueries.GET_COMPANY_BY_ID);
			preparedStatement.setLong(1, id);
			rs = preparedStatement.executeQuery();
			System.out.println("SQL ****************SQL");
			System.out.println(preparedStatement.toString());

			while (rs.next()) {
				String name = rs.getString("COMP_NAME");
				String password = rs.getString("PASSWORD");
				String email = rs.getString("EMAIL");

				Company c = new Company();
				c.setId(id);
				c.setCompName(name);
				c.setEmail(email);
				c.setPassword(password);

				return c;
			}

		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		return null;

	}

	@Override
	public Company getCompanyByName(String name) {

		System.out.println("getCompanyByName");
		System.out.println("///////////////////////////////////////");
		Connection conn = null;

		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			ResultSet rs;

			PreparedStatement preparedStatement = conn.prepareStatement(CompanySQLQueries.SELECT_COMPANIES_BY_NAME);
			preparedStatement.setString(1, name);
			rs = preparedStatement.executeQuery();
			System.out.println("SQL ****************SQL");
			System.out.println(preparedStatement.toString());

			while (rs.next()) {
				Long id = rs.getLong("ID");
				String password = rs.getString("PASSWORD");
				String email = rs.getString("EMAIL");

				Company c = new Company();
				c.setId(id);
				c.setCompName(name);
				c.setEmail(email);
				c.setPassword(password);

				return c;
			}

		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				connectionPool.returnConnection(conn);
		}
		return null;
	}

	@Override
	public void removeFromCompanyCoupon(Company c) {
		System.out.println("removefromCompanyCoupon");
		System.out.println("///////////////////////////////////////");
		System.out.println(c.toString());
		System.out.println("///////////////////////////////////////");
		Connection conn = null;

		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			PreparedStatement preparedStatement = conn
					.prepareStatement(JoinTableSQLQueries.REMOVE_COMPANY_COUPON_BY_COMP_ID);
			preparedStatement.setString(1, c.getId() + "");
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected + " rows affected");

		} catch (SQLException | InterruptedException e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				connectionPool.returnConnection(conn);
		}

	}




}
