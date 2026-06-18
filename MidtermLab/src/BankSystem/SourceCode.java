package BankSystem;
//http://localhost/phpmyadmin/ - this link is for accessing the database.
import java.sql.*; //includes Connection, Driver Manager, SQLException, Statement, ResultSet, Date, TimeUnit, and Scanner. 
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
public class SourceCode {
	static Date date = new Date();
	static SimpleDateFormat frmt = new SimpleDateFormat ("MM/dd/yyyy");
	static SimpleDateFormat sqlFrmt = new SimpleDateFormat ("yyyy-MM-dd");

	static void userAccount(String acc, String getDate) 
	{
		getDate = frmt.format(date);
		
		System.out.println("Date: " + getDate + "	Name: " + acc);
		System.out.println("");
	}
	static void userBank(double credit_show, double balance_show) 
	{
		System.out.println("Credit:	" + credit_show + "		My Balance: " + balance_show);
		System.out.println("");
	}
	public static void main(String[] args) throws SQLException{
		String name, choice, query, display = null, sqlDate = null;
		
		final double creditLimit = 100000, balanceLimit = 100000;
		double withdraw, deposit, credit = 0, balance = 0, loan = 0, payment = 0;
		double processBal = 0, percent = 0;
		
		boolean chkBefore, chkAfter, loop = true, loan_config, chkIR = false;
		Scanner input = new Scanner(System.in), num = new Scanner(System.in);
		
		try 
		{
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_acc","root","");
			Statement state = connect.createStatement();
			ResultSet check;
			PreparedStatement prepState;
			
			Date getLoan = new Date();
			
			long dateBefore, dateAfter, preDate, postDate, preDays, postDays;
			
			System.out.println("");
			System.out.println("|---------------------------------------------------|");
			System.out.println("|-----|=======WELCOME-TO-BANKING-SYSTEM=======|-----|");
			System.out.println("|---------------------------------------------------|");
			System.out.println("");
			
			System.out.print("Enter your Name: ");	name = input.nextLine();
			
			check = state.executeQuery("SELECT * FROM bank_list WHERE FullName = '"+ name +"'");
			
			if(check.next()) 
			{
				name = check.getString("FullName");
				credit = check.getDouble("BankCredit");
				balance = check.getDouble("BankDebit");
				
				System.out.println("Welcome to Banking System, " + name + "!");
			}
			else 
			{
				String newUser = "INSERT INTO bank_list (FullName, BankCredit, BankDebit)" + 
						"VALUES (?, ?, ?)";
				credit = 2500; //for the new user as a starting credit amount.
				prepState = connect.prepareStatement(newUser);
				prepState.setString(1, name);
				prepState.setDouble(2, credit);
				prepState.setDouble(3, balance);
				prepState.execute();
				System.out.println("Hello new user. Welcome to my Banking System.");
			}
			
			do 
			{
				System.out.println("");
				System.out.println("|---------------------------------------------------|");
				System.out.println("|-----|=============|MAIN--MENU|==============|-----|");
				System.out.println("|---------------------------------------------------|");
				System.out.println("");
				userAccount(name, display);
				
				System.out.println("\nSelect the type of card you want to transact with.");
				System.out.println("A. CREDIT		B. DEBIT");
				System.out.println("C. GUIDE		D. EXIT");
				System.out.print("Choice: ");
				choice = input.nextLine();
				
				switch(choice) {
					case "A": // this option A is for loan borrowing and payment amount.
						System.out.println("");
						System.out.println("|---------------------------------------------------|");
						System.out.println("|--------|============|CREDIT|=============|--------|");
						System.out.println("|---------------------------------------------------|");
						System.out.println("");
						userBank(credit, balance);
						
						System.out.println("");
						System.out.println("Enter the transaction you wish to perform.");
						System.out.println("A. LOAN		B. PAY CREDIT");
						System.out.println("C. NOTIF	D. RETURN TO MENU");
						System.out.print("Choice: ");
						choice = input.nextLine();
						
						if(choice.equals("A")) 
						{
							System.out.println("");
							loan_config = false;
							
							check = state.executeQuery("SELECT * FROM loan_list WHERE FullName = '"+ name +"'");
							
							if(check.next()) 
							{
								loan_config = check.getBoolean("LoanConfig");
							}
							if(loan_config==true) 
							{
								System.out.println("You are already borrowed the loan. Check for NOTIF to see more info.");
								
								System.out.println("Press Enter to return to menu...");
								choice = input.nextLine();
								if(choice.equals(""))
									continue;
							}
							else 
							{
								System.out.print("Enter the amount you wish to charge to you credit account: ");
								loan = num.nextDouble();
								
								if(credit<loan) 
								{
									System.out.print("You don't have enough credit. Try to deposit your bank account.");
									continue;
								}
								else 
								{
									credit = credit-loan;
									balance = balance+loan;
									loan_config = true;
									chkIR = true;
									
									Calendar cal = Calendar.getInstance();
									cal.add(Calendar.DAY_OF_MONTH, 7);
									
									String deadline = frmt.format(cal.getTime());
									
									System.out.println("Credit: " + credit);
									System.out.println("Balance: " + balance);
									System.out.println("Loan: " + loan);
									System.out.println("Deadline Payment: " + deadline);
									
									sqlDate = sqlFrmt.format(cal.getTime());
									
									query = "UPDATE bank_list SET BankCredit = ?, BankDebit = ?"
											+ " WHERE FullName = ?";
									
									prepState = connect.prepareStatement(query);
									prepState.setDouble(1, credit);
									prepState.setDouble(2, balance);
									prepState.setString(3, name);
									prepState.executeUpdate();
									
									check = state.executeQuery("SELECT * FROM loan_list WHERE FullName = '"+ name +"'");
									if(check.next()) 
									{
										loan = check.getDouble("Loan");
										sqlDate = check.getString("LoanDeadline");
										loan_config = check.getBoolean("LoanConfig");
										chkIR = check.getBoolean("InterestRateConfig");
									}
									else 
									{
										String newUser = "INSERT INTO loan_list (FullName, Loan, LoanDeadline, LoanConfig, InterestRateConfig)" + 
												"VALUES (?, ?, ?, ?, ?)";
										credit = 2500; //for the new user as a starting credit amount.
										prepState = connect.prepareStatement(newUser);
										prepState.setString(1, name);
										prepState.setDouble(2, loan);
										prepState.setString(3, sqlDate);
										prepState.setBoolean(4, loan_config);
										prepState.setBoolean(5, chkIR);
										
										prepState.execute();
									}
								}
								System.out.println("Press Enter to return to menu...");
								choice = input.nextLine();
								if(choice.equals(""))
									continue;
							}
						}
						else if(choice.equals("B")) 
						{
							System.out.println("");
							loan_config = false;
							
							check = state.executeQuery("SELECT * FROM loan_list WHERE FullName = '"+ name +"'");
							if(check.next()) 
							{
								loan = check.getDouble("Loan");
								getLoan = check.getDate("LoanDeadline");
								loan_config = check.getBoolean("LoanConfig");
								chkIR = check.getBoolean("InterestRateConfig");
							}
							if(loan_config==true) 
							{
								dateBefore = date.getTime();
								dateAfter = getLoan.getTime();
								postDate = Math.abs(dateBefore - dateAfter);
								postDays = TimeUnit.DAYS.convert(postDate, TimeUnit.MILLISECONDS);
								
								chkBefore = date.before(getLoan);
								chkAfter = date.after(getLoan);
								if(chkAfter) 
								{
									if(chkIR) // this if for number of days that applies interest rates.
									{
										for(int i = 0; i < postDays; i++) 
										{
											percent = percent + .01;
										}
										processBal = loan + (loan * percent);
										chkIR = false;
									}
									System.out.println("Loan Payment with " + (int)(percent * 100) + "% Interest Rate: " + processBal);
								}
								if(chkBefore)
								{
									processBal = loan;
									System.out.println("Loan Payment: " + processBal);
								}
								System.out.print("Enter the amount you'd like to pay towards your loan: ");
								payment = num.nextDouble();
								if(balance<payment) 
								{
									System.out.println("You don't have enough debit. Try to deposit your bank account.");
									continue;
								}
								else if(credit>creditLimit) 
								{
									System.out.println("I'm sorry. The credit limit(100000) has been exceeded");
									continue;
								}
								else 
								{
									processBal = processBal - payment;
									if(processBal==0.0) 
									{
										System.out.println("Your loan was fully paid. Thank you for using credit transaction.");
										
										query = "DELETE FROM loan_list WHERE FullName = ?";
										
											prepState = connect.prepareStatement(query);
											prepState.setString(1, name);
											prepState.executeUpdate();
									}
									else 
									{
										System.out.println("Your loan payment still have " + processBal + " to pay."
												+ "Thank you for using credit transaction.");
									}
									balance = balance - payment;
									credit = credit + payment;
									
									query = "UPDATE bank_list SET BankCredit = ?, BankDebit = ?"
											+ " WHERE FullName = ?";
									
									prepState = connect.prepareStatement(query);
									prepState.setDouble(1, credit);
									prepState.setDouble(2, balance);
									prepState.setString(3, name);
									prepState.executeUpdate();
									
									query = "UPDATE loan_list SET Loan = ?, InterestRateConfig = 0"
											+ " WHERE FullName = ?";
									
									prepState = connect.prepareStatement(query);
									prepState.setDouble(1, processBal);
									prepState.setString(2, name);
									prepState.executeUpdate();
									
								}
								System.out.println("Press Enter to return to menu...");
								choice = input.nextLine();
								if(choice.equals(""))
									continue;
							}
							else 
							{
								System.out.println("You are not required to pay the credit unless you select the LOAN option.");
								
								System.out.println("Press Enter to return to menu...");
								choice = input.nextLine();
								if(choice.equals(""))
									continue;
							}
						}
						else if(choice.equals("C")) 
						{
							System.out.println("");
							loan_config = false;
							
							check = state.executeQuery("SELECT * FROM loan_list WHERE FullName = '"+ name +"'");
							if(check.next()) 
							{
								loan = check.getDouble("Loan");
								getLoan = check.getDate("LoanDeadline");
								loan_config = check.getBoolean("LoanConfig");
								chkIR = check.getBoolean("InterestRateConfig");
							}
							dateBefore = date.getTime();
							dateAfter = getLoan.getTime();
							
							preDate = Math.abs(dateAfter - dateBefore);
							postDate = Math.abs(dateBefore - dateAfter);
							preDays = TimeUnit.DAYS.convert(preDate, TimeUnit.MILLISECONDS);
							postDays = TimeUnit.DAYS.convert(postDate, TimeUnit.MILLISECONDS);
							
							chkBefore = date.before(getLoan);
							chkAfter = date.after(getLoan);
							
							if(loan_config==true) 
							{
								if(chkBefore)
								{
									processBal = loan;
									System.out.println("Hello " + name + ", you have a loan due on " + getLoan + ".");
									System.out.println("You were required to pay your a debt of " + processBal + ".");
									System.out.println("You have " + preDays + " days to pay or "
											+ "you will be charged interest the following day.");
								}
								if(chkAfter)
								{
									if(chkIR) // this if for number of days that applies interest rates.
									{
										for(int i = 0; i < postDays; i++) 
										{
											percent = percent + .01;
										}
										processBal = loan + (loan * percent);
									}
									System.out.println("Hello " + name + ", you have a loan due on " + getLoan + ".");
									System.out.println("You were required to pay your a debt of " + processBal + ".");
									System.out.println("Your loan payment deadline has passed by " + postDays + " days. "
											+ "\nYou can still make a payment but as a penalty, the interest rate will charged everyday after the deadline."
											+ "\nAs of now, your interest rate has gone up to " + (int)(percent * 100) + "%.");
								percent = 0;
								}
							}
							else 
							{
								System.out.println("You have no notifitcation.");
							}
							System.out.println("Press Enter to return to menu...");
							choice = input.nextLine();
							if(choice.equals(""))
								continue;
						}
						else if(choice.equals("D")) 
						{
							continue;
						}
						else 
						{
							System.out.println("Input was Invalid.");
						}
					break;
					case "B": // this option B is for deposit and withdrawal of balance amount.
						System.out.println("");
						System.out.println("|--------------------------------------------------|");
						System.out.println("|--------|============|DEBIT|=============|--------|");
						System.out.println("|--------------------------------------------------|");
						System.out.println("");
						userBank(credit, balance);
						
						System.out.println("");
						System.out.println("Enter the transaction you wish to perform.");
						System.out.println("A. DEPOSIT		B. WITHDRAW");
						System.out.println("C. RETURN TO MENU");
						System.out.print("Choice: ");
						choice = input.nextLine();
						
						if(choice.equals("A")) 
						{
							System.out.println("");
							System.out.print("Enter the amount you wish to deposit from your account: ");
							deposit = num.nextDouble();
							
							if(deposit>balanceLimit) 
							{
								System.out.println("I'm sorry. The balance limit(100000) has been exceeded");
								continue;
							}
							else 
							{
								balance+=deposit;
								System.out.println("Your new balance amount is " + balance +
										". Thank your for using debit transaction");
								
								query = "UPDATE bank_list SET BankCredit = ?, BankDebit = ?"
										+ " WHERE FullName = ?";
								
								prepState = connect.prepareStatement(query);
								prepState.setDouble(1, credit);
								prepState.setDouble(2, balance);
								prepState.setString(3, name);
								prepState.executeUpdate();
							}
							System.out.println("Press Enter to return to menu...");
							choice = input.nextLine();
							if(choice.equals(""))
								continue;
						}
						if(choice.equals("B")) 
						{
							System.out.println("");
							System.out.print("Enter the amount you wish to withdraw from your account: ");
							withdraw = num.nextDouble();
							
							if(balance<=withdraw) 
							{
								System.out.println("Your balance is empty. Try to deposit the money "
										+ "from your balance account");
								continue;
							}
							else 
							{
								balance-=withdraw;
								System.out.println("Your new balance amount is " + balance +
										". Thank your for using debit transaction");
								
								query = "UPDATE bank_list SET BankCredit = ?, BankDebit = ?"
										+ " WHERE FullName = ?";
								
								prepState = connect.prepareStatement(query);
								prepState.setDouble(1, credit);
								prepState.setDouble(2, balance);
								prepState.setString(3, name);
								prepState.executeUpdate();
							}
							System.out.println("Press Enter to return to menu...");
							choice = input.nextLine();
							if(choice.equals(""))
								continue;
						}
						if(choice.equals("C")) 
						{
							continue;
						}
						else 
						{
							System.out.println("Input was Invalid.");
						}
					break;
					case "C": // this option C is for guides and instruction for new users and aids for help.
						System.out.println("\n\n\n");
						System.out.println("Hello, and welcome again to Banking System. ");
						System.out.println("My name is Josh Michael B. Bayani and I made this program that serves for our "
										+ "\nMidterm Laboratory.");
						System.out.println("The content of the Banking System contains the two options of cash transactions.");
						System.out.println("The CREDIT option contains the borrowing and paying the loan. ");
						System.out.println("And DEBIT option was for deposit and withdrawal the amount of cash.");
						System.out.println("");
						System.out.println("Now the objectives of this program are the following:");
						System.out.println("1. Connect the Java into database and store the amount value of credits, balances "
										+ "\nand loans.");
						System.out.println("2. Uses and defines the database to insert, select and delete the table contents.");
						System.out.println("3. Make the program looks functional as it runs everytime and;");
						System.out.println("4. The interface of the program should be simple.");
						System.out.println("");
						System.out.println("Press Enter to proceed...");
						input.nextLine();
						
						System.out.println("OUTPUT OF THE PROGRAM:");
						System.out.println("");
						System.out.println("|---------------------------------------------------|");
						System.out.println("|-----|=======WELCOME-TO-BANKING-SYSTEM=======|-----|");
						System.out.println("|---------------------------------------------------|");
						System.out.println("");
						System.out.println("Enter your Name: Josh");
						System.out.println("IF THE USER IS NEW IN DB IT WILL PRINT");
						System.out.println("Hello new user. Welcome to our Banking System.");
						System.out.println("ELSE IF THE USER EXISTED IN DB");
						System.out.println("Welcome back to our Banking System, Josh!");
						System.out.println("");
						System.out.println("Press Enter to proceed...");
						input.nextLine();
						
						System.out.println("");
						System.out.println("|---------------------------------------------------|");
						System.out.println("|------|============|MAIN--MENU|=============|------|");
						System.out.println("|---------------------------------------------------|");
						System.out.println("");
						System.out.println("Date: 10/31/2022		Name: Josh");
						System.out.println("");
						System.out.println("\nSelect the type of card you want to transact with:");
						System.out.println("A. CREDIT		B. DEBIT");
						System.out.println("C. GUIDE		D. EXIT");
						System.out.println("Choice: ");
						System.out.println("");
						System.out.println("Date - displays the current date.");
						System.out.println("Name - displays your name entered.");
						System.out.println("CREDIT - this option is for borrowing and paying loan with notification");
						System.out.println("DEBIT - this options is used for depositing and withdrawal of balance.");
						System.out.println("GUIDE - this is for more information and purpose of creating the program.");
						System.out.println("EXIT - this is for exiting program by confirming the user to proceed to program"
										+ "\n or not.");
						System.out.println("");
						System.out.println("Press Enter to proceed...");
						input.nextLine();
						
						System.out.println("When the user chooses CREDIT: ");
						System.out.println("");
						System.out.println("|---------------------------------------------------|");
						System.out.println("|--------|============|CREDIT|=============|--------|");
						System.out.println("|---------------------------------------------------|");
						System.out.println("");
						System.out.println("Credit:	5000.0		My Balance: 1000.0");
						System.out.println("");
						System.out.println("Enter the transaction you wish to perform.");
						System.out.println("A. LOAN		B. PAY CREDIT");
						System.out.println("C. NOTIF	D. RETURN TO MENU");
						System.out.println("Choice: ");
						System.out.println("");
						System.out.println("");
						System.out.println("Credit - displays the current credit from the database.");
						System.out.println("My Balance - displays the current balance from the database.");
						System.out.println("LOAN - this option is for borrowing the amount of loan from credit.");
						System.out.println("PAY CREDIT - this option is for loan payment. It will enables when the "
										+ "\nLOAN option was performed");
						System.out.println("NOTIF - shortens the word \"Notification\" that sees the remaining days"
										+ "\n before the interest rate applied after the deadline. "
										+ "\n Same as PAY CREDIT, it will trigger when LOAN option was performed");
						System.out.println("RETURN TO MENU - Returns to the Main Menu.");
						System.out.println("");
						System.out.println("Press Enter to proceed...");
						input.nextLine();
						
						System.out.println("When the user chooses LOAN: ");
						System.out.println("");
						System.out.println("Enter the amount you wish to charge to your credit card: 1000");
						System.out.println("Credits: 4000");
						System.out.println("Balance: 2000");
						System.out.println("Loan Payment: 1000");
						System.out.println("Deadline Payment: 11/07/2022");
						System.out.println("");
						System.out.println("IF THE LOANCONFIG (BOOLEAN) IS TRUE");
						System.out.println("You are already borrowed the loan. Check for NOTIF option to see more info");
						System.out.println("");
						System.out.println("IF THE CREDIT IS LESS THAN INPUT");
						System.out.println("You don't have enough credit. Try to deposit your bank account.");
						System.out.println("");
						System.out.println("Press Enter to proceed...");
						input.nextLine();
						
						System.out.println("When the user chooses PAY CREDIT: ");
						System.out.println("");
						System.out.println("IF THE LOANCONFIG (BOOLEAN) IS FALSE");
						System.out.println("You are not required to pay the credit unless you select the LOAN option. ");
						System.out.println("ELSE THE PAY CREDIT STILL RUN");
						System.out.println("IF THE DATE TODAY IS BEFORE THE DEADLINE OF LOAN");
						System.out.println("Loan Payment: 1000");
						System.out.println("Enter the amount you'd like to pay toward your loan: 1000");
						System.out.println("");
						System.out.println("IF THE DATE TODAY IS AFTER THE DEADLINE OF LOAN");
						System.out.println("Loan Payment with 3% Interest Rate: 1030");
						System.out.println("Enter the amount you'd like to pay toward your loan: 530");
						System.out.println("");
						System.out.println("IF THE CREDIT IS HIGHER THAN CREDITLIMIT(100000)");
						System.out.println("I'm sorry. The credit limit(100000) has been exceeded.");
						System.out.println("");
						System.out.println("IF THE BALANCE IS LOWER THAN PAYMENT ENTERED");
						System.out.println("You don't have enough debit. Try to deposit your bank account.");
						System.out.println("");
						System.out.println("IF THE PAYMENT STILL HAVE LOAN");
						System.out.println("Your loan payment still have 500.0 to pay. Thank you for using credit transaction.");
						System.out.println("");
						System.out.println("IF THE PAYMENT IS FULLY PAID");
						System.out.println("Your loan was fully paid. Thank you for using credit transaction.");
						System.out.println("");
						System.out.println("Press Enter to proceed...");
						input.nextLine();
						
						System.out.println("When the user chooses NOTIF: ");
						System.out.println("");
						System.out.println("IF THE LOANCONFIG (BOOLEAN) IS FALSE");
						System.out.println("You have no notifcation.");
						System.out.println("ELSE THE NOTIF STILL RUN");
						System.out.println("IF THE DATE TODAY IS BEFORE THE DEADLINE OF LOAN");
						System.out.println("Hello Josh, you have a loan due on  11/07/2022.");
						System.out.println("You were required to pay a debt of 1000.0. ");
						System.out.println("You have 6 days to pay or you will be charged interest the following day.");
						System.out.println("");
						System.out.println("IF THE DATE TODAY IS AFTER THE DEADLINE OF LOAN");
						System.out.println("Hello Josh, you have a loan due on 11/07/2022.");
						System.out.println("You were required to pay a debt of 1030.0.");
						System.out.println("Your loan payment deadline has passed by 3 days."
						+ "\nYou can still make a payment but as penalty, the interest rate will charged everyday after the deadline."
						+ "\nAs of now, your interest rate has gone up to 3%.");
						System.out.println("");
						System.out.println("Press Enter to proceed...");
						input.nextLine();
						
						System.out.println("When the user chooses DEBIT: ");
						System.out.println("");
						System.out.println("|--------------------------------------------------|");
						System.out.println("|--------|============|DEBIT|=============|--------|");
						System.out.println("|--------------------------------------------------|");
						System.out.println("");
						System.out.println("Credit:	5000.0		My Balance: 1000.0");
						System.out.println("");
						System.out.println("Enter the transaction you wish to perform.");
						System.out.println("A. DEPOSIT		B. WITHDRAW");
						System.out.println("C. RETURN TO MENU");
						System.out.println("Choice: ");
						System.out.println("");
						System.out.println("");
						System.out.println("Credit - displays the current credit from the database.");
						System.out.println("My Balance - displays the current balance from the database.");
						System.out.println("DEPOSIT - this option is used to input the amount of cash in balance");
						System.out.println("WITHDRAW - this option is used to release the amount of cash in balance");
						System.out.println("RETURN TO MENU - Returns to the Main Menu.");
						System.out.println("");
						System.out.println("Press Enter to proceed...");
						input.nextLine();
						
						System.out.println("When the user chooses DEPOSIT: ");
						System.out.println("");
						System.out.println("Enter the amount you wish to deposit from your account: 2500");
						System.out.println("");
						System.out.println("Your new balance amount is: 2500.0");
						System.out.println("");
						System.out.println("IF THE DEPOSIT IS HIGHER THAN BALANCELIMIT(100000)");
						System.out.println("I'm sorry. The balance limit(100000) has been exceeded.");
						System.out.println("");
						System.out.println("Press Enter to proceed...");
						input.nextLine();
						
						System.out.println("When the user chooses WITHDRAW: ");
						System.out.println("");
						System.out.println("Enter the amount you wish to withdraw from your account: 1500");
						System.out.println("");
						System.out.println("Your new balance amount is: 1000.0");
						System.out.println("");
						System.out.println("IF THE BALANCE IS LOWER THAN WITHDRAW ENTERED");
						System.out.println("Your balance is empty.");
						System.out.println("");
						System.out.println("Press Enter to proceed...");
						input.nextLine();
						
						System.out.println("This is the content of the Banking System. Thank you for looking at our guides.");
						System.out.println("Press Enter to return to menu...");
						choice = input.nextLine();
						if(choice.equals(""))
							continue;
							System.out.println("\n\n\n\n\n");
					break;
					case "D": // this option D is for exiting the program.
						String end;
						System.out.println("\nWould you like to exit the program? [Y/N]");
						
						end = input.nextLine();
						
						if(end.equals("N"))
						{
							System.out.println("\nPlease Proceed!\n");
						}
						else if(end.equals("Y"))
						{
							System.out.println("\nThank you for visiting Banking System. Exiting the program...");
							System.exit(0);
							connect.close();
						}
					break;
					default:
						System.out.println("Input was Invalid. Please re-enter the choice.");
					break;
				}
			}
			while(loop);
		}
		catch(Exception e) 
		{
			System.out.print("Something went wrong! \nError: "+e);
		}
	}

}
