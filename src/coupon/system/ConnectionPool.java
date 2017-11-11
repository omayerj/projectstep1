package coupon.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.management.RuntimeErrorException;

import context.Context;

public class ConnectionPool {

	private static ConnectionPool INSTANCE;
	private static int MAX_CONNECTIONS = 5;
	private Set<Connection> myConnection;
	private Object key = new Object();

	public synchronized static ConnectionPool getInstance() throws ClassNotFoundException, SQLException {
		if (INSTANCE == null) {
			INSTANCE = new ConnectionPool();
			 Thread thread = new Thread(new DailyCoupounExpirationTask());
			   thread.start();
		}
		return INSTANCE;
	}

	private ConnectionPool() throws ClassNotFoundException, SQLException {
		myConnection = new HashSet<>();
		Class.forName(Context.mySql_jbdc);
		for (int i = 0; i < MAX_CONNECTIONS; i++) {
			Connection connection = DriverManager.getConnection(Context.Connection, Context.USER_DB,
					Context.PASSWORD_DB);
			myConnection.add(connection);
		}
	}

	public void closeConnection() throws SQLException {
		synchronized (key) {

			Iterator<Connection> iter = myConnection.iterator();
			while (iter.hasNext()) {
				Connection c = iter.next();
				c.close();
			}
		}

	}

	public Connection getConnection() throws InterruptedException {
		synchronized (key) {
			
			while (myConnection.size() == 0)
				key.wait();
			if (CouponSystem.getInstance().isShadwon())
			{
				throw new  RuntimeErrorException(null, "system is down");
			}
			else  
			{		
				Connection result = myConnection.iterator().next();
				myConnection.remove(result);
				return result;
			}
		}
	}

	public void returnConnection(Connection conn) {
		synchronized (key) {
			myConnection.add(conn);
			key.notifyAll();
		}
	}
}
