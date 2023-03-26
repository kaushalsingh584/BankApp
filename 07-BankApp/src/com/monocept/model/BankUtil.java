package com.monocept.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class BankUtil {
	private DataSource datasource;

	public BankUtil(DataSource datasource) {
		super();
		this.datasource = datasource;
	}
	
	private void close(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
		if (conn != null)
			conn.close();
		if (stmt != null)
			stmt.close();
		if (rs != null)
			rs.close();
	}

	public boolean checkCredential(String username, String password) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		try {
			conn = datasource.getConnection();
			String sql = "select password from authdata where username=?";
			stmt = conn.prepareStatement(sql);
			
			
			stmt.setString(1, username);
			rs=stmt.executeQuery();
			
			if(rs.next()) {
				String password2=rs.getString("password");
				if (password.equals(password2))
					return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, null);
		}
		return false;
		
	}

	public List<Customer> userdata() throws SQLException {
		List<Customer> customerlist=new ArrayList<Customer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs=null;
		try {
			conn = datasource.getConnection();
			String sql = "select * from userdetail";
			stmt = conn.prepareStatement(sql);
			rs=stmt.executeQuery();
			
			while (rs.next()) {
				int accountno = rs.getInt("accountno");
				String username = rs.getString("username");
				double balance = rs.getDouble("balance");
				customerlist.add(new Customer(username,balance,accountno));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, null);
		}
		return customerlist;
		
	}

	public void addcustomer(String accountno, String username, String balance) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		try {
			conn = datasource.getConnection();
			
			String sql = "insert into userdetail(accountno,username,balance) values(?,?,?)";
			String sql2 = "insert into authdata(username,password) values(?,?)";
			stmt = conn.prepareStatement(sql);
			stmt2 = conn.prepareStatement(sql2);

			stmt.setInt(1, Integer.parseInt(accountno));
			stmt.setString(2, username);
			stmt.setDouble(3, Double.parseDouble(balance));
			
			stmt2.setString(1, username);
			stmt2.setString(2, username+123);

			stmt.execute();
			stmt2.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, null);
			close(conn, stmt2, null);
		}
	}

	public List<Transaction> getTransactions(String admin) throws SQLException {
		List<Transaction> transactionlist=new ArrayList<Transaction>();
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql="";
		ResultSet rs=null;
		
		try {
			
			conn = datasource.getConnection();
			if(admin.equals("admin")) {
				 sql = "select * from transactions";
				 stmt = conn.prepareStatement(sql);
			}else {
				sql="select * from transactions where username=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, admin);
			}
	
			rs=stmt.executeQuery();
			
			while (rs.next()) {
				
				int accountno = rs.getInt("accountno");
				String username=rs.getString("username");
				String transactiontype=rs.getString("transactiontype");
				double amount = rs.getDouble("amount");
				transactionlist.add(new Transaction(username,accountno, amount,transactiontype));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, stmt, null);
		}
		return transactionlist;
		
		
	}

//	public List<Transaction> filtertransaction(String filter, String username1) throws SQLException {
//		List<Transaction> transactionlist=new ArrayList<Transaction>();
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs=null;
//		String sql="";
//			
//		try {	
//			conn = datasource.getConnection();
//			if(filter==null) {
//				 sql = "select * from transactions where username=?";
//				 stmt = conn.prepareStatement(sql);
//				 stmt.setString(1, username1);
//			}else {
//				sql="select * from transactions where username=? and transactiontype=?";
//				stmt = conn.prepareStatement(sql);
//				stmt.setString(1, username1);
//				stmt.setString(2, filter);
//			}
////			String sql="select * from transactions where username=? and transactiontype=?";
//			rs=stmt.executeQuery();
//			
//			while (rs.next()) {
//				int accountno = rs.getInt("accountno");
//				String username=rs.getString("username");
//				String transactiontype=rs.getString("transactiontype");
//				double amount = rs.getDouble("amount");
//				transactionlist.add(new Transaction(username,accountno, amount,transactiontype));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			close(conn, stmt, null);
//		}
//		return transactionlist;
//		
//	}


}