package SchoolRegSystem;
//http://localhost/phpmyadmin/ - this link is for accessing the database.
import java.sql.*; //includes Connection, Driver Manager, SQLException, Statement, ResultSet, and Scanner. 
import java.util.Arrays;
import java.util.Scanner;

class SystemLogin { // check if the student number is in the sql records.
	static String std_id;
	static int password;
	static Scanner input = new Scanner(System.in),
					 num = new Scanner(System.in);
	
	static void systemRegister() { // enters the student number and password.
		System.out.println("\t\tSCHOOL REGISTRATION SYSTEM");
		System.out.println("\t\t      <<< LOG-IN >>>");
		
		System.out.print("\nEnter your student number: ");
		std_id = input.nextLine();
		System.out.print("Enter the password: ");
		password = num.nextInt();
	}
	static void checkError() { // display if the password is incorrect
		System.out.println("The password is incorrect, try again and enter '12345'.");
		
		System.out.println("Press Enter to proceed...");
		input.nextLine();
	}
	static void checkSuccess() { // display the login success
		System.out.println("Login success!");
		
	}
}
class StudentData{ 
	static String std_lastname, std_firstname,
				  std_status, std_course, 
				  std_year, std_ay,
				  std_sem;
	static int std_units;
	
	public void showStudentData() throws SQLException{ // shows the student info from database
		try 
		{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_registration_list","root",""); // connects the database
			Statement state = connect.createStatement();
			ResultSet check;
			
			check = state.executeQuery("SELECT * FROM std_data WHERE StudentNumber = '"+ SystemLogin.std_id +"'"); //inserts the code through database 
			if(check.next()) 
			{
				SystemLogin.std_id = check.getString("StudentNumber");
				std_firstname = check.getString("First Name");
				std_lastname = check.getString("Last Name");
				std_status = check.getString("Status");
				std_course = check.getString("Course Program");
				std_year = check.getString("Year Level");
				std_ay = check.getString("School Year");
				std_sem = check.getString("Semester");
				std_units = check.getInt("Total Units");
				
				System.out.println("\n\t\tSCHOOL REGISTRATION SYSTEM");
				System.out.println("\t\t  <<< STUDENT’S DATA >>>");
				
				System.out.println("Student Number	: " + SystemLogin.std_id);
				System.out.println("Last Name	: " + std_lastname);
				System.out.println("First Name	: " + std_firstname);
				
				System.out.println("\nStatus		: " + std_status);
				
				System.out.println("\nCourse Program	: " + std_course);
				if(StudentData.std_status.equals("ENROLLED")) 
				{
					
					System.out.println("Year Level	: " + std_year);
					System.out.println("School Year	: " + std_ay);
					System.out.println("Semester	: " + std_sem);
					System.out.println("Total Units	: " + std_units);
				}
				
				System.out.println("\n************************************************************");
			}
			else 
			{
				System.out.println("\nI'm sorry, The student no. "+ SystemLogin.std_id +" is incorrect or it doesn't exist in records,"
									+ " Please try again.");
				
			}
			
			

		}
		catch(Exception e) 
		{
			System.out.print("Something went wrong! \nError: "+e);
		}
	}
}
class StudentCredentials extends StudentData{ // shows the student credentials from database
	String std_diploma, std_form137,
				  std_form138, std_psa,
				  std_gmrc;
	String[] displayArray = {std_diploma, std_form137, std_form138, std_psa, std_gmrc};
	
	public void showCredentials() throws SQLException{
		try 
		{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_registration_list","root",""); // connects the database
			Statement state = connect.createStatement();
			ResultSet check;
			
			check = state.executeQuery("SELECT * FROM std_credentials WHERE StudentNumber = '"+ SystemLogin.std_id +"'");
			if(check.next()) 
			{
				SystemLogin.std_id = check.getString("StudentNumber");
				displayArray[0] = check.getString("School Diploma");
				displayArray[1] = check.getString("Form 137");
				displayArray[2] = check.getString("Form 138");
				displayArray[3] = check.getString("School PSA");
				displayArray[4] = check.getString("School Good Moral");
				
				System.out.println("\t\t    <<< CREDENTIALS >>>");
				
				if(StudentData.std_status.equals("ENROLLED") || StudentData.std_status.equals("GRADUATE")) 
				{
					System.out.println("\nForm 137 		: " + displayArray[1]);
					System.out.println("Form 138 		: " + displayArray[2]);
					System.out.println("Good Moral Certificate 	: " + displayArray[4]);
					System.out.println("PSA			: " + displayArray[3]);
					System.out.println("SHS Diploma 		: " + displayArray[0]);
					
					if(StudentData.std_status.equals("ENROLLED"))
					{
						System.out.println("\n\t\t  *** CURRENTLY ACTIVE ***");
					}
					
					if(StudentData.std_status.equals("GRADUATE")) 
					{
						System.out.println("\n\t     *** CREDENTIALS TO BE RELEASED ***");
					}
					
					System.out.println("\n************************************************************");
				}
				
				
				
				
			}
			else 
			{
				System.out.println("\n\t\t ** Credentials Released**");
				
				System.out.println("\n\t\t*** WITHDRAWN OR TRANSFER ***");
				
				System.out.println("\n************************************************************");
			}
				
				
		}
		catch(Exception e) 
		{
			System.out.print("Something went wrong! \nError: "+e);
		}
	}
}
class Main {
	public static void main(String[] args) throws SQLException {
		SystemLogin login = new SystemLogin();
		StudentData std_data = new StudentData();
		StudentCredentials std_crd = new StudentCredentials();
		int user_pass[] = {12345};
		
		do {
		SystemLogin.systemRegister();
		if(SystemLogin.password != user_pass[0]) {
			SystemLogin.checkError();
		}
		else {
			SystemLogin.checkSuccess();
			std_data.showStudentData();
			std_crd.showCredentials();
			System.out.println("Press Enter to proceed...");
			SystemLogin.input.nextLine();
		}
		} while (true);
	}
}
