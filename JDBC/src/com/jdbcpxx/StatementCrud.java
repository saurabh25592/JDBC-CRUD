package com.jdbcpxx;

import java.sql.*;
import java.util.Scanner;

public class StatementCrud {

	// we built a getConnection() for reuse of method every time
	static Connection getConnection1() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata", "root", "root");
	}

	// Insert Data using Statement
	static void insertData() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the id:");
		int eid = sc.nextInt();
		System.out.println("Enter the ename:");
		String ename = sc.next();
		System.out.println("Enter the age:");
		int age = sc.nextInt();
		System.out.println("Enter the city:");
		String city = sc.next();

		// establish connection every time by using store values
		Connection c = StatementCrud.getConnection1();
		Statement stmt = null;

		try {
			stmt = c.createStatement();
			String insert = "insert into emp(eid, ename, age, city) values(" + eid + ", '" + ename + "', " + age + ", '"
					+ city + "')";
			int rowsAffected = stmt.executeUpdate(insert);
			if (rowsAffected > 0) {
				System.out.println("Record inserted successfully.");
			} else {
				System.out.println("Insertion failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stmt.close();
		c.close();

	}

	// Update Data using Statement
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

		Connection c = StatementCrud.getConnection1();
		Statement stmt = null;

		try {
			stmt = c.createStatement();
			String update = "update emp set ename = '" + ename + "', age = " + age + ", city = '" + city
					+ "' where eid = " + eid;
			int rowsAffected = stmt.executeUpdate(update);
			if (rowsAffected > 0) {
				System.out.println("record updated successfully.");
			} else {
				System.out.println("update failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stmt.close();
		c.close();
	}

	// Delete Data using Statement
	static void deleteData() throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the id to delete:");
		int eid = sc.nextInt();

		Connection c = StatementCrud.getConnection1();
		Statement stmt = null;

		try {
			stmt = c.createStatement();
			String delete = "DELETE FROM emp WHERE eid = " + eid;
			int rowsAffected = stmt.executeUpdate(delete);
			if (rowsAffected > 0) {
				System.out.println("Record deleted successfully.");
			} else {
				System.out.println("Delete failed.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		stmt.close();
		c.close();
	}

	// Fetch Data using Statement
	static void fetchData() throws Exception {
		Connection c = StatementCrud.getConnection1();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = c.createStatement();
			String query = "SELECT * FROM emp";
			rs = stmt.executeQuery(query);

			System.out.println("ID\tName\tAge\tCity");
			System.out.println("----------------------------");
			while (rs.next()) {
				int eid = rs.getInt("eid");
				String ename = rs.getString("ename");
				int age = rs.getInt("age");
				String city = rs.getString("city");

				System.out.println(eid + "\t" + ename + "\t" + age + "\t" + city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 rs.close();
		 stmt.close();
		 c.close();

	}
}
