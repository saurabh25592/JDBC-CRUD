package com.jdbcpxx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CrudPs {

	//we built a getConnection() for reuse of method every time  
	//we return a value
	static Connection getConnection1() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata", "root", "root");
	}

	// Insert Data used for execute get data to  store  
	static void insertData() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the eid:");
		int eid = sc.nextInt();
		System.out.println("Enter the ename:");
		String ename = sc.next();
		System.out.println("Enter the age:");
		int age = sc.nextInt();
		System.out.println("Enter the city:");
		String city = sc.next();

		Connection c = CrudPs.getConnection1();
		PreparedStatement ps = null;

		try {
			ps = c.prepareStatement("insert into emp(eid, ename, age, city) values(?, ?, ?, ?)");
			ps.setInt(1, eid);
			ps.setString(2, ename);
			ps.setInt(3, age);
			ps.setString(4, city);

			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println("Record inserted successfully.");
			} else {
				System.out.println("Insertion failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ps.close();
		c.close();
	}

	// Update Data
	static void updateData() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the eid to update:");
		int eid = sc.nextInt();
		System.out.println("Enter the new ename:");
		String ename = sc.next();
		System.out.println("Enter the new age:");
		int age = sc.nextInt();
		System.out.println("Enter the new city:");
		String city = sc.next();

		Connection c = CrudPs.getConnection1();
		PreparedStatement ps = null;

		try {
			String update = "update emp set ename = ?, age = ?, city = ? where eid = ?";
			ps = c.prepareStatement(update);
			ps.setString(1, ename);
			ps.setInt(2, age);
			ps.setString(3, city);
			ps.setInt(4, eid);

			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println("record updated successfully.");
			} else {
				System.out.println("update failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ps.close();
		c.close();

	}

	// Delete Data
	static void deleteData() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the eid to delete:");
		int eid = sc.nextInt();

		Connection c = CrudPs.getConnection1();
		PreparedStatement ps = null;

		try {
			String delete = "delete from emp where eid = ?";
			ps = c.prepareStatement(delete);
			ps.setInt(1, eid);

			int rows = ps.executeUpdate();
			if (rows> 0) {
				System.out.println("Record deleted successfully.");
			} else {
				System.out.println("Delete failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ps.close();
		c.close();

	}

	// Fetch Data
	static void fetchData() throws Exception {
		Connection c = CrudPs.getConnection1();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			String query = "select * from emp";
			ps = c.prepareStatement(query);
			rs = ps.executeQuery();

			//we are fletch data by using  next() boolean value
			while (rs.next()) {
				int eid = rs.getInt("eid");
				String ename = rs.getString("ename");
				int age = rs.getInt("age");
				String city = rs.getString("city");
				
				

				System.out.println(eid + " " + ename + " " + age + " " + city);
			}
		}
		catch (SQLException e) {
			System.out.println(e);

		} 
				rs.close();
				ps.close();
				c.close();
	}

}
