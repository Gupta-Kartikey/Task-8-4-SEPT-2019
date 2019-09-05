package DB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	static ArrayList<Emp> list = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);
	

	public static void menu() throws ClassNotFoundException, SQLException {
		System.out.println("1. Add Employee");
		System.out.println("2. View All Employee");
		System.out.println("3. Remove Employee");
		System.out.println("4. Clear Total Data");
		System.out.println("5. Change Salary");
		System.out.println("6. Search Employee");
		System.out.println("7. View Dept wise List");
		System.out.println("8. View Sorted Employee");
		System.out.println("9. Exit");
		System.out.println("Enter the desired option");
		

		int i = sc.nextInt();
		sc.nextLine();
		switch (i) {
		case 1:
			addEmp();
			break;
		case 2:
			viewEmp();
			break;
		case 3:
			removeEmp();
			break;
		case 4:
			clearData();
			break;
		case 5:
			changeSal();
			break;
		case 6:
			searchEmp();
			break;
		case 7:
			viewList();
			break;
		case 8:
			sort();
			break;
		case 9:
			sc.close();
			System.exit(0);
			break;
		default:
			System.out.println("Select item from the menu");
			menu();
		}

	}

	public static void addEmp() throws ClassNotFoundException, SQLException {

		String ename, desig, dept;
		int sal, eno = 0;
		System.out.println("Enter name of Employee");
		ename = sc.nextLine();
		System.out.println("Enter dept(it/acts/mkt/hr) of Employee");
		dept = sc.nextLine();
		System.out.println("Enter Designation of Employee");
		desig = sc.nextLine();
		System.out.println("Enter Salary of the Employee");
		sal = sc.nextInt();
		sc.nextLine();
		eno = eno + 1;
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","abcd1234");
		CallableStatement cs=con.prepareCall("{call addEmp(?,?,?,?,?)}");
		cs.setString(2, ename);
		cs.setInt(1, eno);
		cs.setInt(3, sal);
		cs.setString(4, desig);
		cs.setString(5, dept);
		cs.execute();
		con.close();
	    menu();
	}

	public static void viewEmp() throws ClassNotFoundException, SQLException {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","abcd1234");
		String sql = "SELECT * from emp";
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		show(rs);
		con.close();
		menu();
	}

	public static void removeEmp() throws ClassNotFoundException, SQLException {
		
			System.out.println("Enter the Employee id which you want to remove");
			int i = sc.nextInt();
			sc.nextLine();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","abcd1234");
			String sql = "DELETE from emp where eno= "+i;
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			con.close();
		    menu();
	}

	public static void clearData() throws ClassNotFoundException, SQLException {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","abcd1234");
		CallableStatement cs=con.prepareCall("{call delList()}");
		cs.execute();
		con.close();
		menu();
	}

	public static void changeSal() throws ClassNotFoundException, SQLException {
		
			System.out.println("Employee id whose salary you want to edit");
			int i = sc.nextInt();
			System.out.println("Enter new salary");
			int s = sc.nextInt();
			sc.nextLine();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","abcd1234");
			String sql = "UPDATE emp SET sal ="+s+" where eno="+i;
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			con.close();
			menu();
			}

	public static void searchEmp() throws ClassNotFoundException, SQLException {
			System.out.println("Employee id whom you want to search");
			int i = sc.nextInt();
			sc.nextLine();
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","abcd1234");
			String sql = "SELECT * FROM emp WHERE eno= "+i;
			Statement stmt = con.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			show(rs);
			con.close();
				menu();
	}

	public static void sort() throws ClassNotFoundException, SQLException {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","abcd1234");
		
		System.out.println("Sorting on basis of which field");
		System.out.println("1. Employee no. and Ascending");
		System.out.println("2. Employee no. and Descending");
		System.out.println("3. Employee name and Ascending");
		System.out.println("4. Employee name and Descending");
		System.out.println("5. Salery and Ascending");
		System.out.println("6. Salary and Descending");
		System.out.println("7. Designation and Ascending");
		System.out.println("8. Designation and Descending");
		System.out.println("9. Department and Ascending");
		System.out.println("10. Department and Descending");
		System.out.println("/n Enter your choice /n");
		int j=sc.nextInt();
		sc.nextLine();
		String sql;
		Statement stmt;
		ResultSet rs;
		switch(j)
		{
		case 1:	
			
			sql = "SELECT * FROM emp ORDER BY eno ASC ";
		    stmt = con.createStatement();
		    rs=stmt.executeQuery(sql);
		    show(rs);
			con.close();
			break;
		case 2:
			sql = "SELECT * FROM emp ORDER BY eno DESC ";
		    stmt = con.createStatement();
		    rs=stmt.executeQuery(sql);
		    show(rs);
			con.close();
			break;

		case 3:
			sql = "SELECT * FROM emp ORDER BY ename ASC ";
		    stmt = con.createStatement();
		    rs=stmt.executeQuery(sql);
		    show(rs);
			con.close();
			break;

		case 4:
			sql = "SELECT * FROM emp ORDER BY ename DESC ";
		    stmt = con.createStatement();
		    rs=stmt.executeQuery(sql);
		    show(rs);
			con.close();
			break;

		case 5:
			sql = "SELECT * FROM emp ORDER BY sal ASC ";
		    stmt = con.createStatement();
		    rs=stmt.executeQuery(sql);
		    show(rs);
			con.close();
			break;

		case 6:
			sql = "SELECT * FROM emp ORDER BY sal DESC ";
		    stmt = con.createStatement();
		    rs=stmt.executeQuery(sql);
		    show(rs);
			con.close();
			break;

		case 7:
			sql = "SELECT * FROM emp ORDER BY desig ASC ";
		    stmt = con.createStatement();
		    rs=stmt.executeQuery(sql);
		    show(rs);
			con.close();
			break;
		case 8:
			sql = "SELECT * FROM emp ORDER BY desig DESC ";
		    stmt = con.createStatement();
		    rs=stmt.executeQuery(sql);
		    show(rs);
			con.close();
			break;
		case 9:
			sql = "SELECT * FROM emp ORDER BY dept ASC ";
		    stmt = con.createStatement();
		    rs=stmt.executeQuery(sql);
		    show(rs);
			con.close();
			break;
		case 10:
			sql = "SELECT * FROM emp ORDER BY dept DESC ";
		    stmt = con.createStatement();
		    rs=stmt.executeQuery(sql);
		    show(rs);
			con.close();
			break;
		default:
			System.out.println("Choose a Valid Option");
		}
	}
	public static void show(ResultSet rs) throws SQLException{
		ResultSetMetaData rsmd=rs.getMetaData();
		int colNo=rsmd.getColumnCount();
		while(rs.next()){
			for(int i=1;i<=colNo;i++){
				if(i>1){
					System.out.print(", ");
				}
				String colVal=rs.getString(i);
				System.out.print(rsmd.getColumnName(i)+" "+colVal);
			}
			System.out.println(" ");
		}
	}

	public static void viewList() throws ClassNotFoundException, SQLException {

		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","abcd1234");
		
		String sql = "SELECT * FROM emp ORDER BY dept ";
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
		show(rs);
		con.close();
			menu();
		}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver Loaded");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","abcd1234");
		System.out.println("Connection Successfull");
		String sql = " CREATE TABLE IF NOT EXISTS emp (eno INT PRIMARY KEY,ename VARCHAR(20),sal INT,desig VARCHAR(20),dept VARCHAR(10))";
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		menu();

	}

}
