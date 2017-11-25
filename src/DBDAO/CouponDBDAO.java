package DBDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import DAO.CouponDAO;
import coupon.system.ConnectionPool;
import object.java.bens.Coupon;
import object.java.bens.CouponType;
import sql.queries.CompanySQLQueries;
import sql.queries.CouponSQLQueries;
import sql.queries.JoinTableSQLQueries;

public class CouponDBDAO implements CouponDAO {
	private ConnectionPool connectionPool;

	public CouponDBDAO() {
		try {
			connectionPool = ConnectionPool.getInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void createCoupon(Coupon c) {
		System.out.println("createCoupon");
		System.out.println("///////////////////////////////////////");
		System.out.println(c.toString());
		System.out.println("///////////////////////////////////////");
		Connection conn = null;

		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			PreparedStatement preparedStatement = conn.prepareStatement(CouponSQLQueries.CREATE_COUPON);
			preparedStatement.setString(1, c.getTitle());
			preparedStatement.setDate(2, (Date) c.getStartDate());
			preparedStatement.setDate(3, (Date) c.getEndDate());
			preparedStatement.setInt(4, c.getAmount());
			preparedStatement.setString(5, c.getType().toString());
			preparedStatement.setString(6, c.getMessage());
			preparedStatement.setDouble(7, c.getPrice());
			System.out.println(preparedStatement);

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
	public void removeCoupon(Coupon c) {
		System.out.println("remove Coupon");
		System.out.println("///////////////////////////////////////");
		System.out.println(c.toString());
		System.out.println("///////////////////////////////////////");
		Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();

			
			PreparedStatement preparedStatement =
					conn.prepareStatement(CouponSQLQueries.REMOVE_COUPON+c.getId());

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
	public void updateCoupon(Coupon c) {
		System.out.println("update Coupon");
		System.out.println("///////////////////////////////////////");
		System.out.println(c.toString());
		System.out.println("///////////////////////////////////////");
		Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			PreparedStatement preparedStatement =
					conn.prepareStatement(CouponSQLQueries.UPDATE_COUPON);
			preparedStatement.setString(1, c.getEndDate()+"");
			preparedStatement.setString(2, c.getStartDate()+"");
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
	public Coupon getCoupon(long id) {

		System.out.println("get coupon by id");
		System.out.println("///////////////////////////////////////");
		Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			ResultSet rs;

			PreparedStatement preparedStatement =
			conn.prepareStatement(CouponSQLQueries.GET_COUPON_BY_ID);
			preparedStatement.setLong(1, id);
			rs = preparedStatement.executeQuery();
			System.out.println("SQL ****************SQL");
			System.out.println(preparedStatement.toString());
			
			while (rs.next()) {
				
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
	public Collection<Coupon> getAllCoupon() {

		System.out.println("get All Coupon");
		System.out.println("///////////////////////////////////////");
		Connection conn = null;
		ArrayList<Coupon> listReslt=new ArrayList<Coupon>();
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			ResultSet rs;

			PreparedStatement preparedStatement =
			conn.prepareStatement(JoinTableSQLQueries.GET_ALL_COUPON_BY_COMPANY);
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
			return listReslt;
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
	public Collection<Coupon> getCouponByType(CouponType type) {

		System.out.println("get Coupon By Type");
		System.out.println("///////////////////////////////////////");
		Connection conn = null;
		ArrayList<Coupon> listReslt=new ArrayList<Coupon>();
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			ResultSet rs;

			PreparedStatement preparedStatement =
			conn.prepareStatement(CouponSQLQueries.GET_COUPON_BY_TYPE);
			preparedStatement.setString(1, type.toString());
			rs = preparedStatement.executeQuery();
			System.out.println("SQL ****************SQL");
			System.out.println(preparedStatement.toString());
			
			while (rs.next()) {
				Long id =rs.getLong("ID");
				String title=  rs.getString("TITLE");
				Date startDate =rs.getDate("STARTING_DATE");
				Date endDate=rs.getDate("END_DATE");
				int amount=rs.getInt("AMOUNT");
				String message=rs.getString("MESSAGE");
				double price=rs.getDouble("PRICE");
				
				Coupon c = new Coupon();
				c.setId(id);
				c.setTitle(title);
				c.setStartDate(startDate);
				c.setEndDate(endDate);
				c.setAmount(amount);
				c.setType(type);
				c.setMessage(message);
				c.setPrice(price);
				System.out.println( c.toString());
				listReslt.add(c);
			
				
				
			}
			return listReslt;
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
	public Coupon getCouponByTitle(String title) {

		System.out.println("get coupon by title");
		System.out.println("///////////////////////////////////////");
		Connection conn = null;
		
		try {
			// could be in wait state if no connection available!
			conn = connectionPool.getConnection();
			ResultSet rs;

			PreparedStatement preparedStatement =
			conn.prepareStatement(CouponSQLQueries.GET_COUPON_BY_TITLE);
			preparedStatement.setString(1, title);
			rs = preparedStatement.executeQuery();
			System.out.println("SQL ****************SQL");
			System.out.println(preparedStatement.toString());
			
			while (rs.next()) {
				
				long id = rs.getLong("ID");
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
