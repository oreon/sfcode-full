package com.sm.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TeacherManager {

	public static void main(String args[]) {
		Teacher teacher = new Teacher();
		teacher.setFirstName("harpreet");
		teacher.setLastName("kohli");
		saveTeacher(teacher);
		listTeacher();
	}

	public static void saveTeacher(Teacher tc) {
		try {
			Connection conn = connect();
			// String agest = "u are " + st.getAge() + " years old";

			String qry = "insert into Teachers (firstname, lastname ) values ('"
					+ tc.getFirstName() + " ', '" + tc.getLastName() + " ')";

			Statement statement = conn.createStatement();

			statement.execute(qry);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void listTeacher() {
		try {
			Connection conn = connect();

			String qry = "select * from Teachers";
			Statement statement = conn.createStatement();

			ResultSet rs = statement.executeQuery(qry);

			while (rs.next()) {
				System.out.println(rs.getString("firstname"));

				System.out.println(rs.getString("lastname"));

				/*
				 * Teacher tc = new Teacher();
				 * tc.setFirstName(rs.getString("firstname"));
				 * tc.setLastName(rs.getString("lastname"));
				 * //tc.getFormattedName();
				 * System.out.println(tc.getFormattedName());
				 */
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static Connection connect() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		Connection conn = null;
		String userName = "root";
		String password = "root";
		String url = "jdbc:mysql://localhost/sms";
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(url, userName, password);
		return conn;
	}

}