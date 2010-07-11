package com.sm.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class StudentManager {
	public static void main(String[] args) {
		Student stu = new Student();

		stu.setFirstName("nehchal");
		stu.setLastName("kaur");
		System.out.print(stu.getFormattedName());
		stu.setYob(1985);

		List<Student> lst = new ArrayList<Student>();
		System.out.println(stu.getAge());

		List<Integer> ages = new ArrayList<Integer>();
		ages.add(2);
		ages.add(4);
		ages.add(6);
		ages.add(1);

		lst.add(stu);

		Student pintu = new Student();
		pintu.setFirstName("parampreet");
		pintu.setYob(1982);

		lst.add(pintu);

		Student gappu = new Student();
		gappu.setFirstName("Gurpreet");
		gappu.setYob(1983);

		lst.add(gappu);
		int totalAge = 0;

		for (int i = 0; i < lst.size(); i++)
			System.out.println(lst.get(i).getFormattedName());

		for (int i = 0; i < lst.size(); i++) {
			Student st = lst.get(i);
			totalAge = totalAge + st.getAge();

		}
		System.out.println(totalAge);
		
		/*
		for (int i = 0; i < lst.size(); i++) {
			saveStudent(lst.get(i));
		}*/
		
		listStudent();
	}

	public static void saveStudent(Student st) {
		try {
			Connection conn = connect();
			String agest = "u are " + st.getAge() + " years old";

			String qry = "insert into students (firstname, lastname ) values ('"
					+ st.getFirstName() + " ', '" + st.getLastName() + " ')";

			Statement statement = conn.createStatement();

			statement.execute(qry);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void listStudent() {
		try {
			Connection conn = connect();
			

			String qry = "select * from students";
			Statement statement = conn.createStatement();

			ResultSet rs = statement.executeQuery(qry);
			
			while(rs.next()){
				//System.out.println ( rs.getString("firstname") );
			
				//System.out.println ( rs.getString("lastname") );
				
				Student stu = new Student();
				stu.setFirstName(rs.getString("firstname"));
				stu.setLastName(rs.getString("lastname"));
				stu.getFormattedName();
				System.out.println(stu.getFormattedName());
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

	public static void calcMaxAge(List<Student> lst) {
		int maxage = 0;

		for (int i = 0; i < lst.size(); i++) {
			Student st = lst.get(i);
			if (st.getAge() > maxage) {
				maxage = st.getAge();
			}
		}

		System.out.print(maxage);
	}
}
