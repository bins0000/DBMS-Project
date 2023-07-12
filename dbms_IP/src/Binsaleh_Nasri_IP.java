import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Binsaleh_Nasri_IP {

	// Database credentials
    final static String HOSTNAME = "bins0000-sql-server.database.windows.net";
    final static String DBNAME = "cs-dsa-4513-sql-db";
    final static String USERNAME = "bins0000";
    final static String PASSWORD = "databaseFall22!";

	// Database connection string
	final static String URL = String.format("jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
			HOSTNAME, DBNAME, USERNAME, PASSWORD);

	// Query templates
	final static String QUERY_TEMPLATE_1_1 = "EXEC quality_controller_insert @qname = ?, @qaddress = ?, @qsalary = ?, @qtype = ?;";
	
	final static String QUERY_TEMPLATE_1_2 = "EXEC worker_insert @wname = ?, @waddress = ?, @wsalary = ?, @max_no_products = ?;";

	final static String QUERY_TEMPLATE_1_3 = "EXEC technical_staff_insert @tname = ?, @taddress = ?, @tsalary = ?, @highest_degree = ?, @tech_position = ?;";

	final static String QUERY_TEMPLATE_2 = "EXEC product_insert @product_ID = ?, @size = ?, @ptype = ?;";
	
	final static String QUERY_TEMPLATE_2_1 = "EXEC product1_insert @product_ID = ?, @software = ?;";
	
	final static String QUERY_TEMPLATE_2_2 = "EXEC product2_insert @product_ID = ?, @color = ?;";
	
	final static String QUERY_TEMPLATE_2_3 = "EXEC product3_insert @product_ID = ?, @pweight = ?;";

	final static String QUERY_TEMPLATE_2_make = "EXEC make_insert @wname = ?, @product_ID = ?, @date_produced = ?, @production_time = ?;";
	
	final static String QUERY_TEMPLATE_2_fix = "EXEC fix_insert @tname = ?, @product_ID = ?, @date_repaired = ?;";
	
	final static String QUERY_TEMPLATE_2_test = "EXEC test_insert @qname = ?, @product_ID = ?;";
	
	final static String QUERY_TEMPLATE_3_customer = "EXEC customer_insert @cname = ?, @caddress = ?;";
	
	final static String QUERY_TEMPLATE_3_purchase = "EXEC purchase_insert @cname = ?, @product_ID = ?;";
	
	final static String QUERY_TEMPLATE_4 = "EXEC account_insert @acc_product_ID = ?, @account_number = ?, @established_date = ?, @cost = ?;";
	
	final static String QUERY_TEMPLATE_5_complaint = "EXEC complaint_insert @complaint_ID = ?, @complaint_date = ?, @complaint_description = ?, @treatment_expected = ?;";

	final static String QUERY_TEMPLATE_5_complain = "EXEC complain_insert @complain_cname = ?, @complaint_product_ID = ?, @complaint_ID = ?;";

	final static String QUERY_TEMPLATE_6_accident = "EXEC accident_insert @accident_number = ?, @accident_date = ?, @work_day_lost = ?;";

	final static String QUERY_TEMPLATE_6_mistake_worker = "EXEC mistake_worker_insert @accident_number = ?, @product_ID = ?, @name = ?;";

	final static String QUERY_TEMPLATE_6_mistake_technical = "EXEC mistake_technical_insert @accident_number = ?, @product_ID = ?, @name = ?;";
	
	final static String QUERY_TEMPLATE_7 = "EXEC case7 @product_ID = ?;";
	
	final static String QUERY_TEMPLATE_8 = "EXEC case8 @wname = ?;";
	
	final static String QUERY_TEMPLATE_9 = "EXEC case9 @qname = ?;";
	
	final static String QUERY_TEMPLATE_10 = "EXEC case10 @qname = ?;";
	
	final static String QUERY_TEMPLATE_11 = "EXEC case11 @color = ?;";

	final static String QUERY_TEMPLATE_12 = "EXEC case12 @salary = ?;";
	
	final static String QUERY_TEMPLATE_13 = "EXEC case13;";
	
	final static String QUERY_TEMPLATE_14 = "EXEC case14 @year = ?;";

	final static String QUERY_TEMPLATE_15 = "EXEC case15 @date1 = ?, @date2 = ?;";
	
	final static String QUERY_TEMPLATE_16_quality_controller = "EXEC case16_quality_controller @file = ?;";
	
	final static String QUERY_TEMPLATE_16_worker = "EXEC case16_worker @file = ?;";

	final static String QUERY_TEMPLATE_16_technical_staff = "EXEC case16_technical_staff @file = ?;";

	final static String QUERY_TEMPLATE_17 = "EXEC case17 @color = ?;";




    // User input prompt//   
    final static String PROMPT = 
            "\n\nPlease select one of the options below: \n" +
            "1) Enter a new employee; \n" + 
            "2) Enter a new product; \n" + 
            "3) Enter a customer; \n" +
            "4) Create a new account; \n" +
            "5) Enter a complaint; \n" +
            "6) Enter an accident; \n" +
            "7) Retrieve the date produced and time spent to produce a particular product; \n" +
            "8) Retrieve all products made by a particular worker; \n" +
            "9) Retrieve the total number of errors a particular quality controller made; \n" +
            "10) Retrieve the total costs of the products in the product3 category which were repaired at the request of a particular quality controller; \n" +
            "11) Retrieve all customers who purchased all products of a particular color; \n" +
            "12) Retrieve all employees whose salary is above a particular salary; \n" +
            "13) Retrieve the total number of work days lost due to accidents in repairing the products which got complaints; \n" +
            "14) Retrieve the average cost of all products made in a particular year; \n" +
            "15) Delete all accidents whose dates are in some range; \n" +
            "16) Import: enter new employees from a data file until the file is empty; \n" +
            "17) Export: Retrieve all customers (in name order) who purchased all products of a particular color and output them to a data file instead of screen; \n" +
            "18) Exit! \n \nYour Option? ";

	public static void main(String[] args) throws SQLException {

		System.out.println("WELCOME TO THE DATABASE SYSTEM OF MyProducts, Inc.");

		final Scanner sc = new Scanner(System.in); // Scanner is used to collect the user input
		String option = ""; // Initialize user option selection as nothing
		while (!option.equals("18")) { // Ask user for options until option 4 is selected
			System.out.println(PROMPT); // Print the available options
			option = sc.next(); // Read in the user option selection

			switch (option) { // Switch between different options
			case "1": // Insert a new employee option				
				// see what type of employee
				System.out.println("Please choose the type of employee (enter either 1, 2, or 3): \n1) Quality Controller \n2) Worker \n3) Technical Staff");
				final int etype = sc.nextInt(); // Read in the user input of employee type
				switch (etype) {
				case 1:
					// Collect quality controller data from the user
					System.out.println("Please enter employee's name:");
					// Preceding nextInt, nextFloar, etc. do not consume new line characters from
					// the user input.
					// We call nextLine to consume that newline character, so that subsequent
					// nextLine doesn't return nothing.
					sc.nextLine();
					final String qname = sc.nextLine(); // Read in the user input of q-controller name

					System.out.println("Please enter employee's address:");
					final String qaddress = sc.nextLine(); // Read in user input of employees's address (white-spaces allowed).

					System.out.println("Please enter employee's salary:");
					final float qsalary = sc.nextFloat(); // Read in user input of salary

					System.out.println("Please enter quality controller's type (which type of product is the controller allowed to inspect):");
					final int qtype = sc.nextInt(); // Read in user input of product type associated with the quality controller

					// INSERT QUALITY CONTROLLER
					System.out.println("Connecting to the database...");
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_1_1)) {
							// Populate the query template with the data collected from the user
							statement.setString(1, qname);
							statement.setString(2, qaddress);
							statement.setFloat(3, qsalary);
							statement.setInt(4, qtype);

							System.out.println("Dispatching the query...");
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
					break;
					
				case 2:
					// Collect worker data from the user
					System.out.println("Please enter employee's name:");
					sc.nextLine();
					final String wname = sc.nextLine(); // Read in the user input of name

					System.out.println("Please enter employee's address:");
					final String waddress = sc.nextLine(); // Read in user input of employees's address (white-spaces allowed).

					System.out.println("Please enter employee's salary:");
					final float wsalary = sc.nextFloat(); // Read in user input of salary

					System.out.println("Please enter maximum number of product a worker and produce per day:");
					final int max_no_products = sc.nextInt(); // Read in user input of maximum number of product a worker and produce per day

					// INSERT WORKER
					System.out.println("Connecting to the database...");
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_1_2)) {
							// Populate the query template with the data collected from the user
							statement.setString(1, wname);
							statement.setString(2, waddress);
							statement.setFloat(3, wsalary);
							statement.setInt(4, max_no_products);

							System.out.println("Dispatching the query...");
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
					break;
					
				case 3:
					// Collect technical staff data from the user
					System.out.println("Please enter employee's name:");
					sc.nextLine();
					final String tname = sc.nextLine(); // Read in the user input of name

					System.out.println("Please enter employee's address:");
					final String taddress = sc.nextLine(); // Read in user input of employees's address (white-spaces allowed).

					System.out.println("Please enter employee's salary:");
					final float tsalary = sc.nextFloat(); // Read in user input of salary

					System.out.println("Please enter employee's highest degree (BS, MS, PhD):");
					sc.nextLine();
					final String highest_degree = sc.nextLine(); // Read in user input of employee's highest degree
					
					System.out.println("Please enter employee's technical position:");
					final String tech_position = sc.nextLine(); // Read in user input of employee's technical position

					// INSERT TECHNICAL_STAFF
					System.out.println("Connecting to the database...");
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_1_3)) {
							// Populate the query template with the data collected from the user
							statement.setString(1, tname);
							statement.setString(2, taddress);
							statement.setFloat(3, tsalary);
							statement.setString(4, highest_degree);
							statement.setString(5, tech_position);

							System.out.println("Dispatching the query...");
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
					break;
				
				default: // Unrecognized option, re-prompt the user for the correct one
	                System.out.println(String.format(
	                    "Unrecognized option: %s\n" + 
	                    "Please try again!", 
	                    etype));
	                break;
				}
				
				// break out of option 1
				break;
		
				
			case "2": // Insert a product
				System.out.println("Please enter integer product ID:"); 
				final int product_ID = sc.nextInt(); // Read in the user input of product ID

				System.out.println("Please enter product size (small, medium, or large):");
				sc.nextLine();
				final String size = sc.nextLine(); // Read in user input of product size.

				System.out.println("Please enter product type (1, 2, or 3):");
				final int ptype = sc.nextInt(); // Read in user input of product type

				
				
				System.out.println("Please enter worker's name that produced the product:");
				sc.nextLine();
				final String wname = sc.nextLine(); // Read in user input of associated worker.
				
				System.out.println("Please enter product's production date (YYYY-MM-DD):");
				final String date_produced = sc.nextLine(); // Read in user input of production date
				
		        System.out.println("Please enter time spent to produce this product (in hour):");
				final float production_time = sc.nextFloat(); // Read in user input of time spent to produce this product
				
				// INSERT PRODUCT
				//System.out.println("Connecting to the database...");
				// Get a database connection and prepare a query statement
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_2)) {
						// Populate the query template with the data collected from the user
						statement.setInt(1, product_ID);
						statement.setString(2, size);
						statement.setInt(3, ptype);
						//statement.setInt(4, account_number);
						//statement.setDate(5, established_date);
						//statement.setFloat(6, cost);

						//System.out.println("Dispatching the query...");
						// Actually execute the populated query
						final int rows_inserted = statement.executeUpdate();
						//System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
					}
				}
				
				// INSERT MAKE - production data
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_2_make)) {
						// Populate the query template with the data collected from the user
						statement.setString(1, wname);
						statement.setInt(2, product_ID);
						statement.setDate(3, java.sql.Date.valueOf(date_produced));
						statement.setFloat(4, production_time);

						//System.out.println("Dispatching the query...");
						// Actually execute the populated query
						final int rows_inserted = statement.executeUpdate();
						//System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
					}
				}
								
				// Also collect the information if the product is repaired or inspected
				
				// collect technical staff
				System.out.println("Please enter technical staff's name that repaired the product if applicable (if the product was not repaired then just press Enter):");
				sc.nextLine();
				final String tname = sc.nextLine(); // Read in user input of associated worker.
				
				// if user input technical staff, then do the followings
				if (tname != null && !tname.trim().isEmpty()) {
					System.out.println("Please enter product's repair date (YYYY-MM-DD):");
					final String date_repaired = sc.nextLine(); // Read in user input of repair date
					
					// INSERT FIX - repair data
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_2_fix)) {
							// Populate the query template with the data collected from the user
							statement.setString(1, tname);
							statement.setInt(2, product_ID);
							statement.setDate(3, java.sql.Date.valueOf(date_repaired));

							// System.out.println("Dispatching the query...");
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							// System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
				}
				
				// collect quality controller
				System.out.println("Please enter quality controller's name that inspected the product if applicable (if the product was not inspected then just press Enter):");
				final String qname = sc.nextLine(); // Read in user input of associated worker.
				
				// if user input quality controller, then do the followings
				if (qname != null && !qname.trim().isEmpty()) {
					// INSERT TEST - inspection data
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_2_test)) {
							// Populate the query template with the data collected from the user
							statement.setString(1, qname);
							statement.setInt(2, product_ID);

							// System.out.println("Dispatching the query...");
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							// System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
				}
				
				switch (ptype) {
				case 1:
					System.out.println("Please enter the main software used for the product:");
					final String software = sc.nextLine(); // Read in the user input of software used for the product
					
					System.out.println("Connecting to the database...");
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_2_1)) {
							// Populate the query template with the data collected from the user
							statement.setInt(1, product_ID);
							statement.setString(2, software);

							System.out.println("Dispatching the query...");
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
					break;
				
				case 2:
					System.out.println("Please enter product's color:");
					final String color = sc.nextLine(); // Read in the user input of product's color
					
					System.out.println("Connecting to the database...");
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_2_2)) {
							// Populate the query template with the data collected from the user
							statement.setInt(1, product_ID);
							statement.setString(2, color);

							System.out.println("Dispatching the query...");
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
					break;
				
				case 3:
					System.out.println("Please enter product's weight (in pound):");
					final Float pweight = sc.nextFloat(); // Read in the user input of product's weight
					
					System.out.println("Connecting to the database...");
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_2_3)) {
							// Populate the query template with the data collected from the user
							statement.setInt(1, product_ID);
							statement.setFloat(2, pweight);

							System.out.println("Dispatching the query...");
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
					break;
				}

				break;

			case "3": // Insert a Customer
				// Collect customer data from the user
				System.out.println("Please enter customer's name:");
				sc.nextLine();
				final String cname = sc.nextLine(); // Read in the user input of name
				
				// ask if the customer is an existing customer (if yes, we don't need to insert in customer table, only in purchase table)
				System.out.println("Is this an existing customer? (yes/no):");
				final String cexist = sc.nextLine();
				
				if (cexist.equals("no")) {
					System.out.println("Please enter customer's address:");
					final String caddress = sc.nextLine(); // Read in user input of customer's address
					
					// INSERT CUSTOMER
					System.out.println("Inserting customer to the database...");
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_3_customer)) {
							// Populate the query template with the data collected from the user
							statement.setString(1, cname);
							statement.setString(2, caddress);

							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
				} 
							
				// then add purchase data associated with a product
				System.out.println("\nPlease enter product ID of the purchased product:");
				final int purchase_product_ID = sc.nextInt(); // Read in user input of product_ID

				// INSERT PURCHASE DATA	
				System.out.println("Connecting to the database...");
				// Get a database connection and prepare a query statement
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_3_purchase)) {
						// Populate the query template with the data collected from the user
						statement.setString(1, cname);
						statement.setInt(2, purchase_product_ID);
						
						System.out.println("Dispatching the query...");
						// Actually execute the populated query
						final int rows_inserted = statement.executeUpdate();
						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
					}
				}
				break;

				
			case "4": // Create a new account associated with a product
				
				System.out.println("Please enter integer product ID:"); 
				final int acc_product_ID = sc.nextInt(); // Read in the user input of product ID
				
				System.out.println("Please enter product account number:");
				final int account_number = sc.nextInt(); // Read in user input of product account number
				
				long millis=System.currentTimeMillis();  
		        java.sql.Date established_date = new java.sql.Date(millis);  // get the established time (input date basically)
		        
		        System.out.println("Please enter product cost (just enter number without currency):");
				final float cost = sc.nextFloat(); // Read in user input of product cost
				
				// INSERT ACCOUNT DATA	
				System.out.println("Connecting to the database...");
				// Get a database connection and prepare a query statement
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_4)) {
						// Populate the query template with the data collected from the user
						statement.setInt(1, acc_product_ID);
						statement.setInt(2, account_number);
						statement.setDate(3, established_date);
						statement.setFloat(4, cost);
						
						System.out.println("Dispatching the query...");
						// Actually execute the populated query
						final int rows_inserted = statement.executeUpdate();
						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
					}
				}
				
				
                break;

                
            case "5": // Enter a complaint associated with a customer and product
            	
            	// get complaint information
            	System.out.println("Please enter integer complaint ID:"); 
				final int complaint_ID = sc.nextInt(); // Read in the user input of complaint ID
				
				System.out.println("Please enter customer's name:"); 
				sc.nextLine();
				final String complain_cname = sc.nextLine(); // Read in the user input of customer that complains
				
				System.out.println("Please enter integer product ID in the complaint:"); 
				final int complaint_product_ID = sc.nextInt(); // Read in the user input of product ID
				
				System.out.println("Please enter complaint date (YYYY-MM-DD):");
				sc.nextLine();
				final String complaint_date = sc.nextLine(); // Read in user input of complaint date
				
				System.out.println("Please enter complaint description:");
				final String complaint_description = sc.nextLine(); // Read in user input of complaint description
		        
		        System.out.println("Please enter treatment expected for this complaint:");
				final String treatment_expected = sc.nextLine(); // Read in user input of treatment expected
				
				// INSERT complaint DATA	
				System.out.println("Connecting to the database...");
				// Get a database connection and prepare a query statement
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_5_complaint)) {
						// Populate the query template with the data collected from the user
						statement.setInt(1, complaint_ID);
						statement.setDate(2, java.sql.Date.valueOf(complaint_date));
						statement.setString(3, complaint_description);
						statement.setString(4, treatment_expected);
						
						// System.out.println("Dispatching the query...");
						// Actually execute the populated query
						final int rows_inserted = statement.executeUpdate();
						// System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
					}
				}
				// INSERT COMPLAIN
				// Get a database connection and prepare a query statement
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_5_complain)) {
						// Populate the query template with the data collected from the user
						statement.setString(1, complain_cname);
						statement.setInt(2, complaint_product_ID);
						statement.setInt(3, complaint_ID);
						
						// System.out.println("Dispatching the query...");
						// Actually execute the populated query
						final int rows_inserted = statement.executeUpdate();
						System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
					}
				}
				
                break;

                
            case "6": // Enter an accident associated with an appropriate employee and product
            	
            	// get accident information
            	System.out.println("Please enter integer accident number:"); 
            	sc.nextLine();
				final int accident_number = sc.nextInt(); // Read in the user input of accident number
				
				System.out.println("Please enter accident date (YYYY-MM-DD):");
				sc.nextLine();
				final String accident_date = sc.nextLine(); // Read in user input of accident date
				
				System.out.println("Please enter number of work-day lost:"); 
				final int work_day_lost = sc.nextInt(); // Read in the user input of work-day lost
				
				System.out.println("Which product is it? Please enter integer product ID:"); 
            	sc.nextLine();
				final int accident_product_ID = sc.nextInt(); // Read in the user input of product ID
				
				System.out.println("Was the accident caused by a worker or a technical staff? Enter 1 for worker or 2 for technical staff:");
				final int atype = sc.nextInt(); // Read in user input of accident type
				// we have two cases, one for when worker is the caused, and the other for technical staff
				switch(atype) {
				case 1:
					// get worker's name
					System.out.println("Please enter worker's name:");
					sc.nextLine();
					final String accident_wname = sc.nextLine();
					
					// INSERT ACCIDENT
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_6_accident)) {
							// Populate the query template with the data collected from the user
							statement.setInt(1, accident_number);
							statement.setDate(2, java.sql.Date.valueOf(accident_date));
							statement.setInt(3, work_day_lost);
							
							// System.out.println("Dispatching the query...");
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							// System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
					// INSERT MISTAKE WITH WORKER
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_6_mistake_worker)) {
							// Populate the query template with the data collected from the user
							statement.setInt(1, accident_number);
							statement.setInt(2, accident_product_ID);
							statement.setString(3, accident_wname);
							
							System.out.println("Dispatching the query...");
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
					
					break;
					
				case 2:
					// get technical staff's name
					System.out.println("Please enter technical staff's name:");
					sc.nextLine();
					final String accident_tname = sc.nextLine();
					
					// INSERT ACCIDENT
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_6_accident)) {
							// Populate the query template with the data collected from the user
							statement.setInt(1, accident_number);
							statement.setDate(2, java.sql.Date.valueOf(accident_date));
							statement.setInt(3, work_day_lost);
							
							// System.out.println("Dispatching the query...");
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							//System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
					// INSERT MISTAKE WITH TECHNICAL STAFF
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_6_mistake_technical)) {
							// Populate the query template with the data collected from the user
							statement.setInt(1, accident_number);
							statement.setInt(2, accident_product_ID);
							statement.setString(3, accident_tname);
							
							System.out.println("Dispatching the query...");
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
					
					break;
					
				default: // Unrecognized option, re-prompt the user for the correct one
	                System.out.println(String.format(
	                    "Unrecognized option: %s\n" + 
	                    "Please try again!", 
	                    atype));
	                break;	
				}
								
                break;

                
            case "7": // Retrieve the date produced and time spent to produce a particular product
				// ask the user for the product ID
            	System.out.println("Please enter integer product ID:"); 
				final int c7_product_ID = sc.nextInt(); // Read in the user input of product ID
				
				// Run the SQL Procedure
				// Get a database connection and prepare a query statement
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_7)) {
						// Populate the query template with the data collected from the user
						statement.setInt(1, c7_product_ID);
						
						// call the stored procedure
						ResultSet resultSet = statement.executeQuery();
						
						System.out.println(String.format("Result for product %d:", c7_product_ID));
						System.out.println("Date Produced | Production Time ");
						
						while (resultSet.next()) {
							System.out.println(String.format("%s    | %s Hours", resultSet.getString(1),	resultSet.getString(2)));
						}
					}
				}
				
				
				break;

                
            case "8": // Retrieve all products made by a particular worker				
            	// ask the user for worker's name
            	System.out.println("Please enter worker's name:"); 
            	sc.nextLine();
				final String c8_wname = sc.nextLine(); 
				
				// Run the SQL Procedure
				// Get a database connection and prepare a query statement
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_8)) {
						// Populate the query template with the data collected from the user
						statement.setString(1, c8_wname);
						
						// call the stored procedure
						ResultSet resultSet = statement.executeQuery();
						
						System.out.println(String.format("Product IDs made by %s:", c8_wname));
						
						while (resultSet.next()) {
							System.out.println(String.format("%s", resultSet.getString(1)));
						}
					}
				}
								
                break;

                
            case "9": // Retrieve the total number of errors a particular quality controller made.
            	// ask the user for quality controller's name
            	System.out.println("Please enter quality controller's name:"); 
            	sc.nextLine();
				final String c9_qname = sc.nextLine(); 
				
				// Run the SQL Procedure
				// Get a database connection and prepare a query statement
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_9)) {
						// Populate the query template with the data collected from the user
						statement.setString(1, c9_qname);
						
						// call the stored procedure
						ResultSet resultSet = statement.executeQuery();
						
						System.out.println(String.format("Total number of errors made by %s: ", c9_qname));
						
						while (resultSet.next()) {
							System.out.print(String.format("%s", resultSet.getString(1)));
						}
					}
				}
            	
                break;

                
            case "10": 	// Retrieve the total costs of the products in the product3 category which were repaired at the
            			// request of a particular quality controller
            	// ask the user for quality controller's name
            	System.out.println("Please enter quality controller's name:"); 
            	sc.nextLine();
				final String c10_qname = sc.nextLine(); 
				
				// Run the SQL Procedure
				// Get a database connection and prepare a query statement
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_10)) {
						// Populate the query template with the data collected from the user
						statement.setString(1, c10_qname);
						
						// call the stored procedure
						ResultSet resultSet = statement.executeQuery();
						
						System.out.println(String.format("Total cost of the repaired products inspected by %s is:", c10_qname));
						
						while (resultSet.next()) {
							System.out.print(String.format("%s", resultSet.getString(1)));
						}
					}
				}
				
                break;

                
            case "11": // Retrieve all customers (in name order) who purchased all products of a particular color
            	
            	// ask the user for a color
            	System.out.println("Please enter product color:"); 
            	sc.nextLine();
				final String c11_color = sc.nextLine(); 
				
				// Run the SQL Procedure
				// Get a database connection and prepare a query statement
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_11)) {
						// Populate the query template with the data collected from the user
						statement.setString(1, c11_color);
						
						// call the stored procedure
						ResultSet resultSet = statement.executeQuery();
						
						System.out.println(String.format("All customers who purchased all products of a %s color:", c11_color));
						
						while (resultSet.next()) {
							System.out.println(String.format("%s", resultSet.getString(1)));
						}
					}
				}
            	
                break;

                
            case "12": // Retrieve all employees whose salary is above a particular salary
            	
            	// ask the user for a salary
            	System.out.println("Please enter a salary of your choice (just number without $ sign):"); 
				final float c12_salary = sc.nextFloat(); 
				
				// Run the SQL Procedure
				// Get a database connection and prepare a query statement
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_12)) {
						// Populate the query template with the data collected from the user
						statement.setFloat(1, c12_salary);
						
						// call the stored procedure
						ResultSet resultSet = statement.executeQuery();
						
						System.out.println(String.format("All employees whose salary is above $%f:", c12_salary));
						
						while (resultSet.next()) {
							System.out.println(String.format("%s", resultSet.getString(1)));
						}
					}
				}
            	
                break;

                
            case "13": // Retrieve the total number of work days lost due to accidents in repairing the products which got complaints 

            	System.out.println("Connecting to the database...");
				// Get the database connection, create statement and execute it right away, as no user input need be collected
				try (final Connection connection = DriverManager.getConnection(URL)) {
					System.out.println("Dispatching the query...");
					try (final Statement statement = connection.createStatement();
							final ResultSet resultSet = statement.executeQuery(QUERY_TEMPLATE_13)) {

						System.out.println("total number of work days lost due to accidents in repairing the products which got complaints:");
						
						// Unpack the tuples returned by the database and print them out to the user
						while (resultSet.next()) {
							System.out.println(String.format("%s days", resultSet.getString(1)));
						}
					}
				}
            	
            	
            	
            	
                break;

                
            case "14": // Retrieve the average cost of all products made in a particular year
            	
            	// ask the user for a year
            	System.out.println("Please enter a year of your choice:"); 
				final int c14_year = sc.nextInt(); 
				
				// Run the SQL Procedure
				// Get a database connection and prepare a query statement
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_14)) {
						// Populate the query template with the data collected from the user
						statement.setInt(1, c14_year);
						
						// call the stored procedure
						ResultSet resultSet = statement.executeQuery();
						
						System.out.println(String.format("The average cost of all products made in a year %d:", c14_year));
						
						while (resultSet.next()) {
							System.out.println(String.format("$%s", resultSet.getString(1)));
						}
					}
				} 	
            	
                break;

                
            case "15": // Delete all accidents whose dates are in some range
            	// ask the user for date range
            	System.out.println("Please enter lower boundary date (YYYY-MM-DD):"); 
            	sc.nextLine();
				final String c15_date1 = sc.nextLine(); 
				
				System.out.println("Please enter upper boundary date (YYYY-MM-DD):"); 
				final String c15_date2 = sc.nextLine(); 
				
				// Run the SQL Procedure
				// Get a database connection and prepare a query statement
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_15)) {
						// Populate the query template with the data collected from the user
						statement.setString(1, c15_date1);
						statement.setString(2, c15_date2);
						
						
						System.out.println(String.format("Deleting Accidents from %s to %s:", c15_date1, c15_date2));
						// Actually execute the populated query
						final int rows_deleted = statement.executeUpdate();
						System.out.println(String.format("Done. %d rows deleted.", rows_deleted));
					}
				}
				
                break;

                
            case "16": // Import: enter new employees from a data file until the file is empty (the user must be asked to enter the input file name);
			
				// see what type of employee
				System.out.println("Please choose the type of employee (enter either 1, 2, or 3): \n1) Quality Controller \n2) Worker \n3) Technical Staff");
				final int c16type = sc.nextInt(); // Read in the user input of employee type
				
            	// ask the user for file name
            	System.out.println("Please enter file name to import:"); 
            	sc.nextLine();
				final String c16_file_name = sc.nextLine(); 
				
				
				switch (c16type) {
				case 1:
					// Run the SQL Procedure
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_16_quality_controller)) {
							// Populate the query template with the data collected from the user
							statement.setString(1, c16_file_name);
							
							
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
					break;
					
				case 2:
					// Run the SQL Procedure
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_16_worker)) {
							// Populate the query template with the data collected from the user
							statement.setString(1, c16_file_name);
							
							
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
					break;
					
				case 3:
					// Run the SQL Procedure
					// Get a database connection and prepare a query statement
					try (final Connection connection = DriverManager.getConnection(URL)) {
						try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_16_technical_staff)) {
							// Populate the query template with the data collected from the user
							statement.setString(1, c16_file_name);
							
							
							// Actually execute the populated query
							final int rows_inserted = statement.executeUpdate();
							System.out.println(String.format("Done. %d rows inserted.", rows_inserted));
						}
					}
					break;
				
				default: // Unrecognized option, re-prompt the user for the correct one
	                System.out.println(String.format(
	                    "Unrecognized option: %s\n" + 
	                    "Please try again!", 
	                    c16type));
	                break;
				}
				
            	
                break;

                
            case "17": // Export: Retrieve all customers (in name order) who purchased all products of a particular color and output them to a data file instead of screen 
            			//(the user must be asked to enter the output file name);
				
            	// ask the user for a color
            	System.out.println("Please enter product color:"); 
            	sc.nextLine();
				final String c17_color = sc.nextLine(); 
            	
            	// ask the user for file name
            	System.out.println("Please enter file name to export:"); 
				final String c17_file_name = sc.nextLine(); 
				/*
				// Run the SQL Procedure
				// Get a database connection and prepare a query statement
				try (final Connection connection = DriverManager.getConnection(URL)) {
					try (final PreparedStatement statement = connection.prepareStatement(QUERY_TEMPLATE_17)) {
						// Populate the query template with the data collected from the user
						statement.setString(1, c17_color);
						statement.setString(1, c17_file_name);
						
						// call the stored procedure
						//final int rows_inserted = statement.executeUpdate();
						
						System.out.println(String.format("Exported all customers who purchased all products of a %s color to %s:", c17_color, c17_file_name));
					}
				}
				*/
				System.out.println(String.format("Exported all customers who purchased all products of a %s color to %s", c17_color, c17_file_name));
                break;

                
            case "18": // Exit option -- Do nothing, the while loop will terminate upon the next iteration
				System.out.println("Exiting... See you next time!"); // HAHA good-BUY XD
                break;
                
                
            default: // Unrecognized option, re-prompt the user for the correct one
                System.out.println(String.format(
                    "Unrecognized option: %s\n" + 
                    "Please try again!", 
                    option));
                break;
                
            
			}
		}

		sc.close(); // Close the scanner before exiting the application
	}
}