package jdbc.crud;

import java.sql.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws SQLException {

		Connection conn = null;
		ConnectDB obj_conndb = new ConnectDB();
		conn = obj_conndb.dbconn();
		Customer newcustomer = new Customer();
		Article newarticle = new Article();
		Orders neworder = new Orders();
		int choice;

		Scanner s = new Scanner(System.in);
		Scanner s2 = new Scanner(System.in);
		do {
			System.out.println("1.Customer CRUD");
			System.out.println("2.Articles");
			System.out.println("3.Orders");
			System.out.println("0.Exit");
			System.out.println("Select an option:");
			choice = s.nextInt();
			switch (choice) {
			case 1:
				do {
					System.out.println("1.Create");
					System.out.println("2.Read");
					System.out.println("3.Update");
					System.out.println("4.Delete");
					System.out.println("5.Find");

					System.out.println("Select an option:");
					choice = s.nextInt();
					switch (choice) {
					case 1:
						System.out.println("Enter id:");
						newcustomer.id = s.nextInt();
						System.out.println("Enter salutation");
						newcustomer.salutation = s2.nextLine();
						System.out.println("Enter name");
						newcustomer.name = s2.nextLine();
						System.out.println("Enter surname");
						newcustomer.surname = s2.nextLine();
						System.out.println("Enter Date of birth DD-MM-YYYY");
						newcustomer.dob = s2.nextLine();
						System.out.println("Enter number");
						newcustomer.number = s.nextInt();

						obj_conndb.insert(newcustomer);
						break;
					case 2:
						obj_conndb.read();
						break;
					case 3:
						System.out.println("Enter id:");
						newcustomer.id = s.nextInt();
						System.out.println("Enter salutation");
						newcustomer.salutation = s2.nextLine();
						System.out.println("Enter name");
						newcustomer.name = s2.nextLine();
						System.out.println("Enter surname");
						newcustomer.surname = s2.nextLine();
						System.out.println("Enter Date of birth DD-MM-YYYY");
						newcustomer.dob = s2.nextLine();
						System.out.println("Enter number");
						newcustomer.number = s.nextInt();

						obj_conndb.update(newcustomer);

						break;
					case 4:

						System.out.println("Enter id:");
						newcustomer.id = s.nextInt();
						obj_conndb.delete(newcustomer);
						break;
					case 5:
						System.out.println("Enter id:");
						newcustomer.id = s.nextInt();
						obj_conndb.find(newcustomer);
						break;
					}
				} while (choice != 0);
			case 2:
				do {
					System.out.println("1.Create Article");
					System.out.println("2.Read Articles");
					System.out.println("Select an option:");
					choice = s.nextInt();
					switch (choice) {
					case 1:
						System.out.println("Article id:");
						newarticle.id = s.nextInt();
						System.out.println("Article Number");
						newarticle.article_number = s2.nextLine();
						System.out.println("Article Description");
						newarticle.article_desc = s2.nextLine();
						System.out.println("Price");
						newarticle.price = s.nextInt();

						obj_conndb.insertarticle(newarticle);
						break;
					case 2:
						obj_conndb.readarticles();
						break;
					}
				} while (choice != 0);
			case 3:
				do {
					System.out.println("1.Read Orders");
					System.out.println("Select an option:");
					choice = s.nextInt();
					switch (choice) {
					case 1:
						obj_conndb.readorders();

						break;
					case 2:
						break;
					}
				} while (choice != 0);
			}
		} while (choice != 0);
	}

}
