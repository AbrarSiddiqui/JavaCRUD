
package jdbc.crud;

import java.sql.*;

public class ConnectDB {
	Statement statement = null;
	Connection conn = null;
	Customer newcustomer = new Customer();
	PreparedStatement prepquery, checkid;
	ResultSet RS;
	public static void main(String[] args) {

		ConnectDB obj_ConnectDB = new ConnectDB();
		System.out.println(obj_ConnectDB.dbconn());
	}

	public Connection dbconn() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ecom", "postgres", "");
			if (conn != null) {
				System.out.println("Connected");
			} else {
				System.out.println("Connection failed");
			}

		} catch (Exception e) {
			System.out.println(e);

		}
		return conn;
	}

	public void insert(Customer newcustomer) throws SQLException {
		if (checkID(newcustomer.id) != 1) {
			prepquery = conn.prepareStatement(
					"INSERT INTO customers(customer_id,name, surname, customer_number,salutation,dob)\r\n"
							+ "VALUES (?,?,?,?,?,?)");
			prepquery.setInt(1, newcustomer.id);
			prepquery.setString(2, newcustomer.name);
			prepquery.setString(3, newcustomer.surname);
			prepquery.setInt(4, newcustomer.number);
			prepquery.setString(5, newcustomer.salutation);
			prepquery.setString(6, newcustomer.dob);

			prepquery.executeUpdate();
			System.out.print("Customer Record Inserted" + "\n");

		} else
			System.out.print("Customer ID already Exist:" + "\n");
	}

	public void update(Customer newcustomer) throws SQLException {
		if (checkID(newcustomer.id) != 0) {
			prepquery = conn.prepareStatement(
					"Update customers Set customer_id=?,name=?, surname=?, customer_number=?,salutation=?,dob=? Where customer_id=?");
			prepquery.setInt(1, newcustomer.id);
			prepquery.setString(2, newcustomer.name);
			prepquery.setString(3, newcustomer.surname);
			prepquery.setInt(4, newcustomer.number);
			prepquery.setString(5, newcustomer.salutation);
			prepquery.setString(6, newcustomer.dob);
			prepquery.setInt(7, newcustomer.id);

			prepquery.executeUpdate();
			System.out.print("Customer Record Updated" + "\n");

		} else
			System.out.print("Customer ID do not Exist:" + "\n");
	}

	public void delete(Customer newcustomer) throws SQLException {
		if (checkID(newcustomer.id) != 0) {
			prepquery = conn.prepareStatement("Delete from customers Where customer_id=?");
			prepquery.setInt(1, newcustomer.id);
			prepquery.executeUpdate();
			System.out.print("Customer Record Deleted" + "\n");
		} else
			System.out.print("Customer ID already Exist:" + "\n");
	}

	public void find(Customer newcustomer) throws SQLException {
		prepquery = conn.prepareStatement("select * from customers where customer_id=?");
		prepquery.setInt(1, newcustomer.id);
		RS = prepquery.executeQuery();
		if (RS.next()) {
			System.out.print(RS.getString(1) + " ");
			System.out.print(RS.getString(2) + " ");
			System.out.print(RS.getString(3) + " ");
			System.out.print(RS.getString(4) + " ");
			System.out.print(RS.getString(5) + " ");
			System.out.print("\n");
		} else
			System.out.print("Customer ID do not Exist:" + "\n");
	}
	
	public void read() throws SQLException {
		prepquery = conn.prepareStatement("select * from customers");
		RS = prepquery.executeQuery();
		while (RS.next()) {
			System.out.print(RS.getString(1) + " ");
			System.out.print(RS.getString(2) + " ");
			System.out.print(RS.getString(3) + " ");
			System.out.print(RS.getString(4) + " ");
			System.out.print(RS.getString(5) + " ");
			System.out.print("\n");
		}
	}
	public void readorders() throws SQLException {
		prepquery = conn.prepareStatement("select * from orders");
		RS = prepquery.executeQuery();
		while (RS.next()) {
			System.out.print(RS.getString(1) + " ");
			System.out.print(RS.getString(2) + " ");
			System.out.print(RS.getString(3) + " ");
			System.out.print(RS.getString(4) + " ");
			System.out.print(RS.getString(5) + " ");
			System.out.print(RS.getString(6) + " ");
			System.out.print("\n");
		}
	}
	public void deleteorders(Customer newcustomer) throws SQLException {
		if (checkID(newcustomer.id) != 0) {
			prepquery = conn.prepareStatement("Delete from customers Where customer_id=?");
			prepquery.setInt(1, newcustomer.id);
			prepquery.executeUpdate();
			System.out.print("Customer Record Deleted" + "\n");
		} else
			System.out.print("Customer ID already Exist:" + "\n");
	}
	public void insertarticle(Article newarticle) throws SQLException {
		if (checkarticleID(newarticle.id) != 1) {
			prepquery = conn.prepareStatement(
					"INSERT INTO articles(article_id,article_number,article_desc, price)\r\n"
							+ "VALUES (?,?,?,?)");
			prepquery.setInt(1, newarticle.id);
			prepquery.setString(2, newarticle.article_number);
			prepquery.setString(3, newarticle.article_desc);
			prepquery.setInt(4, newarticle.price);

			prepquery.executeUpdate();
			System.out.print("Article Record Inserted" + "\n");

		} else
			System.out.print("Customer ID already Exist:" + "\n");
	}
	public void readarticles() throws SQLException {
		prepquery = conn.prepareStatement("select * from articles");
		RS = prepquery.executeQuery();
		while (RS.next()) {
			System.out.print(RS.getString(1) + " ");
			System.out.print(RS.getString(2) + " ");
			System.out.print(RS.getString(3) + " ");
			System.out.print(RS.getString(4) + " ");
			System.out.print("\n");
		}
	}

	public int checkID(int id) throws SQLException {
		checkid = conn.prepareStatement("select customer_id from customers where customer_id=?");
		checkid.setInt(1, id);
		RS = checkid.executeQuery();
		if (RS.next()) {
			return 1;
		} else
			return 0;

	}
	public int checkarticleID(int id) throws SQLException {
		checkid = conn.prepareStatement("select article_id from articles where article_id=?");
		checkid.setInt(1, id);
		RS = checkid.executeQuery();
		if (RS.next()) {
			return 1;
		} else
			return 0;

	}
	public int checkorderID(int id) throws SQLException {
		checkid = conn.prepareStatement("select order_id from orders where order_id=?");
		checkid.setInt(1, id);
		RS = checkid.executeQuery();
		if (RS.next()) {
			return 1;
		} else
			return 0;

	}
}
