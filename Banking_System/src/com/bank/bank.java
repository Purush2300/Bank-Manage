package com.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class bank {
	static Connection  conn=null;
	static Statement stmt=null;
	static ResultSet   res=null;
	static PreparedStatement prep=null;
	public static Scanner sc =new Scanner(System.in);
	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/Employees";
		String un="root";
		String pass="{enter password}";
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection(url, un, pass);
				conn.setAutoCommit(false);
				System.out.println("withdraw/transfer");
				String choice=sc.next();
				switch(choice) {
				case "withdraw":{
					withdraw();
					break;
				}
				case "transfer":{
					 transction();
					 System.out.println("after transfer");
					 printSql(conn);
					 break;
				}
				}
//			   
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
	}

	


	private static void withdraw() throws SQLException {
		
		System.out.println("enter name");
		String name=sc.next();
		System.out.println("enter pin");
		int p=sc.nextInt();
		String sql="select name,balance from User where name=? and pin=?";
		prep=conn.prepareStatement(sql);
		
		prep.setString(1, name);
		prep.setInt(2, p);
		 res=prep.executeQuery();
		if(!res.next()) {
			System.out.println("name and pin are incorrect");
			return;
		}
		int balance=res.getInt("balance");
		
		System.out.println("enter the amount you want to widthdraw");
		int amount=sc.nextInt();
		
		
		if(amount<=0) {
			System.out.println("Invalid amount");
			return;
		}
		if(balance<amount) {
			System.out.println("Insuffcient balance");
			return;
		}
		
		String s="UPDATE User SET balance = balance - ? WHERE name=? AND pin=?";
		
		 PreparedStatement updateStmt = conn.prepareStatement(s);
		    updateStmt.setInt(1, amount);
		    updateStmt.setString(2, name);
		    updateStmt.setInt(3, p);

		    int rows = updateStmt.executeUpdate();

		    if (rows == 1) {
		        conn.commit();
		        System.out.println("Withdrawal Successful");
		        System.out.println("Remaining Balance : " + (balance - amount));
		    } else {
		        conn.rollback();
		        System.out.println("Withdrawal Failed");
		    }
		
		
	}




	private static void transction() throws SQLException {
		System.out.println("enter sender name");
		String sender=sc.next();
		System.out.println("enter receiver name");
		String receiver=sc.next();
		System.out.println("enter the amount");
		int amount=sc.nextInt();
		
		int x=updatesend(sender,-amount);
		int y=updatesend(receiver,amount);
		if (isCheck(x,y)) {
			System.out.println("Transcatiion Sucessfull");
		conn.commit();
		} else {
			System.out.println("Transcatiion Failed");
			conn.rollback();
		}
		
	}

	private static boolean isCheck(int x, int y) {
		System.out.println("do you want to proceed to payment yes/no");
		return sc.next().equalsIgnoreCase("yes")&& x==1 && y==1;
	}

	private static int updatesend(String user, int amount) throws SQLException {
		String sql="update User set balance=balance+? where name=?";
		PreparedStatement prep=conn.prepareStatement(sql);
		prep.setInt(1, amount);
		prep.setString(2, user);
		int i=prep.executeUpdate();
		return i;
		
	}

	private static void printSql(Connection conn) throws SQLException {

	    Statement stmt = conn.createStatement();
	    ResultSet res = stmt.executeQuery("SELECT * FROM User");

	    System.out.println("---------------------------------------------------------------------------------------------------------------");
	    System.out.printf("| %-3s | %-15s | %-30s | %-15s | %-10s | %-5s |%n",
	            "ID", "NAME", "EMAIL", "DEPARTMENT", "BALANCE", "PIN");
	    System.out.println("---------------------------------------------------------------------------------------------------------------");

	    while (res.next()) {
	        System.out.printf("| %-3d | %-15s | %-30s | %-15s | %-10d | %-5d |%n",
	                res.getInt(1),
	                res.getString(2),
	                res.getString(3),
	                res.getString(4),
	                res.getInt(5),
	                res.getInt(6));
	    }

	    System.out.println("---------------------------------------------------------------------------------------------------------------");

	    res.close();
	    stmt.close();
	}
}
