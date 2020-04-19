 package EpicVotingSystem;

import java.io.*; 
import java.util.*;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * File Name :
 * author :21700361
 * Date :
 * Description :
 */

public class VotingInterface
{
    private VotingController vc;
    private Staff theStaff;
    private Candidate theCandidate;
    private admin theAdmin;
    public int loginAttempts = 0;//unsuccessful login attempts

  
 
    String date = new Date().toString();//Timestamp for staff voting
    private BufferedReader in = new BufferedReader( new InputStreamReader( System.in ));

    
	//Here is the start of your program. 
   public static void main(String[] args)
    {
        VotingInterface vi = new VotingInterface();
        vi.start();
    }

	//This methods is complete and does not require change.
    public void start()
    {
        vc = new VotingController();
        commenceVoting();
    }
    
    

	
	//This method helps to manage admin session after a successfull login
	 public boolean manageAdmin()
     {
        boolean adminQuit = false;
        boolean systemQuit = false;


        while (!adminQuit){
       	 
        	System.out.println("\n\nChoose one of the following options: ");
        	System.out.println("C => Continue voting");
       	 	System.out.println("E => End voting");
       	 	System.out.println("V => See voting results");
       	 	System.out.println("D => Display accounts");
       	 

        	String Input = getInput();
       	 
    		
    		
    		if (Input.equalsIgnoreCase("v"))
        	{
    			 printVoteResults();
    			 adminQuit = true;
        	}
       	 
    		else if (Input.equalsIgnoreCase("d"))
        	{
    			displayAccounts();
    			adminQuit = true;
        	}
       	 
    		else if (Input.equalsIgnoreCase("C"))
    		{
                //back to voting
                adminQuit = true;
    		}   
    		
    		 else if(Input.equalsIgnoreCase("e"))
    		 {
                 //stop system
                 adminQuit = true;
                 systemQuit = true;
                 System.out.println("\n***Voting System Closed***");
             }
    		
    		 else 
           {
              System.out.println("\n***Invalid input. Please try again***");
              manageAdmin();
           }
       	 

         }

        return systemQuit;
     }
	
     //validates administrator log in
     public void validateAdmin()
     { 
    	 //Admin Options
    	 System.out.println("\n\nChoose one of the following options: ");
    	 System.out.println("L => Log in");
    	 System.out.println("Q => Quit");
  
    	 String Input = getInput();
    	 
    	 //Quit
    	 if (Input.equalsIgnoreCase("q")){
    		 
    		 commenceVoting();
    	 }
    	 
    	 
    	 // staff login  
    	 else if (Input.equalsIgnoreCase("l")){
    	 
    	try
    	{
     	   System.out.print("\nEnter username: ");
     	   String username = getInput();
     	   theAdmin = vc.getAdmin(username);
     	  
     	  //Finds the username in admin text file 
     	  if (new String(theAdmin.getUsername()).equals(username)){
  	
     		  	//Password
     	    	System.out.print("Enter password: ");
     	    	String pwd = getInput();
     	    	
     	    	if (new String(theAdmin.getPassword()).equals(pwd))
     		   {
     	    		//welcome message after a successful admin login
     			   System.out.printf("\n***Welcome %s!***", theAdmin.getName());
     			   manageAdmin();
     		   }
     	    	
     	    	 //If login not successfull
     		     else {
     		    	 
     		     System.out.println("\n***Incorrect password. Please try again***");
          		 validateAdmin();
      		   }
     	    	
     	  }
     	  }
     	   
    	
     	 //Invalid usrname entered  
     	 catch (NullPointerException ex)
 		{
     	   System.out.println("\n***Invalid username. Please enter a valid username***");
 	       validateAdmin();
         }
   }
    	 //Invalid input
    	 else {
    		 	System.out.println("\n***Invalid input. Please try again***");
    		 	validateAdmin();
    	 }
     }
    	 

     
     //Choosing accounts to display
     public void displayAccounts() {
    	 
    	 System.out.println("\n\nSelect the account you woukd like to view: ");
    	 System.out.println("A => Admin");
    	 System.out.println("C => Candidate");
    	 System.out.println("S => Staff");
    	 System.out.println("V => Staff voted");
    	 System.out.println("N => Staff not voted");
    	 
    	 String Input = getInput();
    	 
    	 //Admin
    	 if(Input.equalsIgnoreCase("a"))
         {
    		 displayAdmin();
         }
    	 
    	 //Candidates
    	 else if(Input.equalsIgnoreCase("c"))
         {
    		 displayCandidates();
         }
    	 
    	 //Staff
    	 else if(Input.equalsIgnoreCase("s"))
         {
    		 displayStaff();
         }
    	 
    	 //Staff voted
    	 else if(Input.equalsIgnoreCase("v"))
         {
    		 displayStaffVoted();
         }
    	 
    	 //Staff not voted
    	 else if(Input.equalsIgnoreCase("n"))
         {
    		 displayStaffNotVoted();
         }
    	 
    	 else
         {
            System.out.println("\n***Invalid input. Please try again***");
            displayAccounts();
         }
     	 
    	 
    	 
    	 
     }
     
     
     //Display admin accounts
     public void displayAdmin() {
    	 
    	  //Column titles 
	  	    System.out.printf("%s %10s %14s %14s,%17s %8s %14s %15s", "\n\nID", "Name", "\tUsername", "\tPassword", "\n----", "\t-----","\t------","\t-------");
	  	    
	  	     
	  	    //Displays the admin list
	  	    int i = vc.admins.indexOf(0); 
	  	    for (i = 0; i < vc.admins.size(); i++) 
	  	    {
	  		   System.out.print("\n" + vc.admins.get(i).id + "\t" + vc.admins.get(i).name  + "\t" + vc.admins.get(i).username + "\t" + vc.admins.get(i).password);
	  	    }
	  	    
	  	  System.out.print("\n\n\nPress m to modify (add, delete, update) an admin account \n or h to go back to homepage: ");
	  	  
	  	  
	  	String Input = getInput();
   	 
	  	if(Input.equalsIgnoreCase("m"))
        {
	  		modifyAdmin();
        }
   	 
	  	else if(Input.equalsIgnoreCase("h"))
        {
   		 commenceVoting();
        }
   	 
	  	else
	  	{
	  	System.out.println("\n***Invalid input. Please try again***");
	  	displayAdmin();
	  	}
	  	  
	  	  
	  	    
     }
     
   //Modify admin account
     public void modifyAdmin(){
    	 
    	 System.out.println("\n\nChoose one of the following options: ");
    	 System.out.println("A => Add");
    	 System.out.println("D => Delete");
    	 System.out.println("U => Update");
    	 
    	 
    	 String Input = getInput();
       	 
 	  	if(Input.equalsIgnoreCase("a"))
         {
    		 addAdmin();
         }
    	 
 	  	else if(Input.equalsIgnoreCase("d"))
         {
    		 deleteAdmin();
         }
    	 
 	  	else if(Input.equalsIgnoreCase("u"))
        {
 	  		updateAdmin();
        }
   	 
 	  	else
 	  	{
 	  	System.out.println("\n***Invalid input. Please try again***");
 	  	modifyAdmin();
 	  	}
    	 
    }
     
     
     //Adds new admin account to the text file
     public void addAdmin() {
 
    	 try {
    		 
    		 //Declaring variables
    		 String newAdminName;	
    	     String newAdminUsername;
    	     String newAdminPwd;
    	     
    	     System.out.print("\n\nEnter ID for new admin account: ");
    	     int newAdminId = Integer.parseInt(getInput());//Getting input
    	     
    	     
    	 //Checks if ID entered already exists
    	 if(vc.adminIdCheck(newAdminId) == false)
    	 { 
    		 System.out.println("\n***Sorry the ID entered already exists. Please try again***");
    		 addAdmin();  
    	 }

    	 
    	 //If entered ID is unique
    	 else 	{
    		 
    		 System.out.print("\nEnter full name: ");
    				newAdminName = getInput();
    				
    				 //If input empty	
    				 if ((newAdminName.isEmpty())) {
    					 
    					 System.out.println("\n***Invalid name. Please try again***");
    	    	         addAdmin();
    	    	       }
    				 
    				 //if not empty
    				 else {
    					 
    					 System.out.print("Enter username: ");
    					 newAdminUsername = getInput();
    					 
    					 
    					 if ((newAdminUsername.isEmpty())) {
	    					 
	    					 System.out.println("\n***Invalid username. Please try again***");
	    	    	         addAdmin();
	    	    	       }
    					 else {
    					 
    					 //Check if username already exists
    					 if (vc.adminUsernameCheck(newAdminUsername) == false){
    						 
    						 System.out.println("\n***Sorry the username entered already exists. Please try again***");
				    		 addAdmin(); 
    						 
    					 }
    					 
    					 //If username entered is unique
    					 else {
    						 
    						 //if input empty
    						 if ((newAdminUsername.isEmpty())) {
    	    					 
    	    					 System.out.println("\n***Invalid username. Please try again***");
    	    	    	         addAdmin();
    	    	    	       }
    						 
    						 //If not empty
    					     else {
    					    	 
    					    	 System.out.print("Enter password: ");
    					    	 newAdminPwd = getInput();
    					    	 
    					    	//if input empty
        						 if ((newAdminPwd.isEmpty())) {
        	    					 
        	    					 System.out.println("\n***Invalid password. Please try again***");
        	    	    	         addAdmin();
        	    	    	       }
    					     
        						 
        						 //If not empty
        						 else {
        							 
        						   //Saves new account in the text file
      	                           vc.newAdminAccount(newAdminId, newAdminName, newAdminUsername, newAdminPwd);
      	                           System.out.printf("\n***You have successfully added %s to the admin list***", newAdminName);
      	                           validateAdmin(); 
    					 }
    					     }
    						 }
    					 }
    				 }
    	 }
    	 }
    	 
    	 //If ID entered is not integers
    	 catch (NumberFormatException ex)
		 {
    		 System.out.println("\n***The ID entered is invalid. Please enter a valid ID***");
			 addAdmin();
		 } 
     }

     //Deletes an admin account 
     public void deleteAdmin() {
    	 
    	 try {
    		 
    		 //Gets input
    		 System.out.print("\nEnter admin username to delete: ");
 	         theAdmin = vc.getAdmin(getInput());
 	         
 	        //Confirmation message 
 	        System.out.printf("\n***You chose %s, Enter 'y' to confirm or 'n' to cancel***", theAdmin.getName());
            String Input = getInput();
            
            //Responds to input
            if (Input.equalsIgnoreCase("y"))  
            {
        	   vc.deleteAdminAccount();
        	   System.out.println("\n***The admin account has been successfully deleted***");
        	   validateAdmin();
            }
            else if (Input.equalsIgnoreCase("n")) 
            {
            	deleteAdmin();
            }
            else
    	    {
               System.out.println("\n***Invalid input. Please try again***");
    	       deleteAdmin();
    	    }
    	}
    	 
    	 //If username entered is not valid
    	 catch (Exception e) {
    		 System.out.println("\n***The username entered is invalid. Please enter a valid username***");
			 deleteAdmin();
 		}    	 
     }

     //Updates an admin account
     public void updateAdmin() {
    	 
    	 
    	try { 
    	 
    		 //Asks for account to update
    		 System.out.print("\nEnter admin username to update: ");
    		 theAdmin = vc.getAdmin(getInput());
    		 System.out.printf("\n\n***You chose %s, Enter 'y' to confirm or 'n' to cancel***", theAdmin.getName());
         	 String Input = getInput();
    	
         	 //Cancels
         	if (Input.equalsIgnoreCase("n")) {
         		
         		updateAdmin();
         	}
         	 
         	
         	//Confirms 
         	else if (Input.equalsIgnoreCase("y"))  
        	   {
         		
         		System.out.println("\n\nChoose one of the following options: ");
            	System.out.println("N => Update name");
           	 	System.out.println("P => Update password");
         		
             	 Input = getInput();
             	
             	 //Updates password 
              	if (Input.equalsIgnoreCase("p"))
              	{
         		  System.out.print("\nEnter new password: ");
         		  String pwd = getInput();
         		  
         		  //if input is empty.
         		 if ((pwd.isEmpty()))
            	  {
         			System.out.println("\n***Invalid password. Please try again***");
         			updateAdmin();
            	  }
         			
         			//If not empty
         			else {
         			 
         			theAdmin.setPassword(pwd);//Saves new password 
             		 vc.saveAdminData(); //Updates the text file
             		 System.out.println("\n***The account has been successfully updated***");
             		 }
         		 }
              	
              	
              		//Updates name
              		else if (Input.equalsIgnoreCase("n")) {
              			
              		  System.out.print("\nEnter new name: ");
              		  String name = getInput();
               		  
               		  //if input is empty.
               		 if ((name.isEmpty()))
                  	  {
               			System.out.println("\n***Invalid name. Please try again***");
               			updateAdmin();
                  	  }
               			
               			//If not empty
               			else {
               			 
               			 theAdmin.setName(name);//Saves new name 
                   		 vc.saveAdminData(); //Updates the text file
                   		 System.out.println("\n***The account has been successfully updated***");
                   		 }
              			
              		}
              		
              		
              	}
              	
         	else {
         		System.out.println("\n***Invalid input. Please try again***");
         	}
    	}
    	
    	//If invalid username entered
    	 catch (Exception e)
 		{
 		   System.out.println("\n***The username entered is invalid. Please enter a valid username***");
		   updateAdmin();
 		}
    	
     }

     //Displays candidate list
     public void displayCandidates() {
    	 
   	  //Column titles 
	  	    System.out.printf("%s %16s %5s ,%17s %8s %17s ", "\n\nCode", "Name", "Votes", "\n----", "----","----");
	  	    
	  	     
	  	    //Displays the candidate list
	  	    int i = vc.candidates.indexOf(0); 
	  	    for (i = 0; i < vc.candidates.size(); i++) 
	  	    {
	  		   System.out.print("\n" + vc.candidates.get(i).candidateCode + "\t" + vc.candidates.get(i).name  + "\t" + vc.candidates.get(i).votes);
	  	    }
	  	    
	  	  System.out.print("\n\n\nPress m to modify (add, delete, update) a candidate account \n or h to go back to homepage: ");
	  	  
	  	  
		  	String Input = getInput();
	   	 
		  	if(Input.equalsIgnoreCase("m"))
	        {
		  		modifyCandidate();
	        }
	   	 
		  	else if(Input.equalsIgnoreCase("h"))
	        {
	   		 commenceVoting();
	        }
	   	 
		  	else
		  	{
		  	System.out.println("\n***Invalid input. Please try again***");
		  	displayCandidates();
		  	}
		  	  
   	 
    }
     
     //Modify a candidate account
     public void modifyCandidate() {
    	 
    	 System.out.println("\n\nChoose one of the following options: ");
    	 System.out.println("A => Add");
    	 System.out.println("D => Delete");
    	 System.out.println("U => Update");
    	 
    	 
    	 String Input = getInput();
       	 
 	  	if(Input.equalsIgnoreCase("a"))
         {
    		 addCandidate();
         }
    	 
 	  	else if(Input.equalsIgnoreCase("d"))
         {
    		 deleteCandidate();
         }
    	 
 	  	else if(Input.equalsIgnoreCase("u"))
        {
 	  		updateCandidate();
        }
   	 
 	  	else
 	  	{
 	  	System.out.println("\n***Invalid input. Please try again***");
 	  	modifyCandidate();
 	  	}
    	 
     }
     
   
     //Add a new candidate account
     public void addCandidate() {
    	 
try {
    		 
    		 //Declaring variables
    		 String newCandidateName;	
    	     int newCandidateVotes;
    	     
    	     //Getting input
    	     System.out.print("\nEnter candidate code for new candidate account: ");
    	     int newCandidateCode = Integer.parseInt(getInput());//Getting input
    	     
    	     
    	 //Checks if candidate code entered already exists
    	 if(vc.candidateCodeCheck(newCandidateCode) == false)//If exists
    	 { 
    		 System.out.println("\n***Sorry the candidate code entered already exists. Please try again***");
    		 addCandidate();  
    	 }
    	 
    	 //If unique
    	 else {
        		 
        		 System.out.print("Enter full name: ");
        		 newCandidateName = getInput();
        				
        				 //If input empty	
        				 if ((newCandidateName.isEmpty())) {
        					 
        					 System.out.println("\n***Invalid name. Please try again***");
        					 addCandidate();
        	    	       }
        				 
        				 //if not empty
        				 else {
        					 
        					 //Candidate vote count is set to zero
        					 newCandidateVotes = Integer.parseInt("0"); 
        					 
 							   //Saves new account in the text file
	                           vc.newCandidateAccount(newCandidateCode, newCandidateName, newCandidateVotes);
	                           System.out.printf("\n***You have successfully added %s to the candidate list***", newCandidateName);
	                           validateAdmin(); 	 
    	 }
    	 	}
}
        				//If candidate code is not integers
        		    	 catch (NumberFormatException ex)
        				 {
        		    		 System.out.println("\n***The candidate code entered is invalid. Please enter a valid ID***");
        					 addCandidate();
        				 } 
 
     }
     
     
   //Deletes a candidate account 
     public void deleteCandidate() {
    	 
    	 try {
    		 
    		 //Gets input
    		 System.out.print("\nEnter candidate code to delete: ");
 	         theCandidate = vc.getCandidate(Integer.parseInt(getInput()));
 	         
 	        //Confirmation message 
 	        System.out.printf("\n***You chose %s, Enter 'y' to confirm or 'n' to cancel***", theCandidate.getName());
            String Input = getInput();
            
            //Responds to input
            if (Input.equalsIgnoreCase("y"))  
            {
        	   vc.deleteCandidateAccount();
        	   System.out.println("\n***The candidate account has been successfully deleted***");
        	   validateAdmin();
            }
            else if (Input.equalsIgnoreCase("n")) 
            {
            	deleteCandidate();
            }
            else
    	    {
               System.out.println("\n***Invalid input! Please try again***");
    	       deleteCandidate();
    	    }
    	}
    	 
    	 //If candidate code entered is invalid
    	 catch (Exception e) {
    		 
    		 System.out.println("\n***The candidate code entered is invalid. Please enter a valid code***");
			 deleteCandidate();
 		}    	 
     }

     
     //Update candidate account
     public void updateCandidate(){
    	 
    	 try { 
        	 
    		 //Asks for account to update
    		 System.out.print("\nEnter candidate code to update: ");
    		 theCandidate = vc.getCandidate(Integer.parseInt(getInput()));
    		 System.out.printf("\n***You chose %s, Enter 'y' to confirm or 'n' to cancel***", theCandidate.getName());
         	 String Input = getInput();
    	
         	 //Cancels
         	if (Input.equalsIgnoreCase("n")) {
         		
         		updateCandidate();
         	}
         	 
         	
         	//Confirms 
         	else if (Input.equalsIgnoreCase("y"))  
        	   {
         			  
         			  System.out.print("\nEnter new name: ");
              		  String name = getInput();
               		  
               		  //if input is empty.
               		 if ((name.isEmpty()))
                  	  {
               			System.out.println("\n***Invalid name. Please try again***");
               			updateCandidate();
                  	  }
               			
               			//If not empty
               			else {
               			 
               			 theCandidate.setName(name);//Saves new name 
                   		 vc.saveCandidateData(); //Updates the text file
                   		 System.out.println("\n***The account has been successfully updated***");
                   		 }
              			
              		}
    	}
    	
    	//If invalid Code is entered
    	 catch (Exception e)
 		{
 		   System.out.println("\n***The candidate code entered is invalid. Please enter a valid code***");
		   updateCandidate();
 		}
    	
    	 
     }
     
     
     //Display staff accounts
     public void displayStaff() {
    	 
      	  //Column titles 
    	 System.out.printf("%s %11s %11s %10s","\n\nID", "Name", "Voted", "Date");
    	 System.out.print("\n---------------------------------------");
   	  	    //System.out.printf("%s %11s %11s %10s,%17s %8s %11s %6s", "\n\nID", "Name", "Voted", "Date", "\n---", "----","----", "----");
   	  	    
   	  	     
   	  	    //Displays the staff list
   	  	    int i = vc.staffs.indexOf(0); 
   	  	    for (i = 0; i < vc.staffs.size(); i++) 
   	  	    {
   	  		   System.out.print("\n" + vc.staffs.get(i).id + "\t" + vc.staffs.get(i).name  + "\t" + vc.staffs.get(i).voted + "\t" + vc.staffs.get(i).date);
   	  	    }
   	  	    
   	  	  System.out.print("\n\n\nPress m to modify (add, delete, update) a staff account \n or h to go back to homepage: ");
	  	  
	  	  
		  	String Input = getInput();
	   	 
		  	if(Input.equalsIgnoreCase("m"))
	        {
		  		modifyStaff();
	        }
	   	 
		  	else if(Input.equalsIgnoreCase("h"))
	        {
	   		 commenceVoting();
	        }
	   	 
		  	else
		  	{
		  	System.out.println("\n***Invalid input. Please try again***");
		  	displayStaff();
		  	}
		  	  
      	 
       }
       
     //Displays staff that have voted 
     public void displayStaffVoted() {
    	 
    	 System.out.printf("%s%18s%24s", "ID", "Name", "Date\n");
    	 System.out.printf("%s%18s%23s", "---", "-----", "-----");
    	 vc.displayStaffVoted();
     }
     
     
     //Displays staff that have not voted
     public void displayStaffNotVoted() {
    	 
    	 System.out.printf("\n%s%18s", "ID", "Name");
    	 System.out.print("\n--------------------------");
    	 vc.displayStaffNotVoted();
     }


     public void modifyStaff() {

    	 System.out.println("\n\nChoose one of the following options: ");
    	 System.out.println("A => Add");
    	 System.out.println("D => Delete");
    	 System.out.println("U => Update");
    	 
    	 
    	 String Input = getInput();
       	 
 	  	if(Input.equalsIgnoreCase("a"))
         {
    		 addStaff();
         }
    	 
 	  	else if(Input.equalsIgnoreCase("d"))
         {
    		 deleteStaff();
         }
    	 
 	  	else if(Input.equalsIgnoreCase("u"))
        {
 	  		updateStaff();
        }
   	 
 	  	else
 	  	{
 	  	System.out.println("\n***Invalid input. Please try again***");
 	  	modifyStaff();
 	  	}
    	 
     }

     //Add a staff account to the text file
     public void addStaff() {
    	 
    	 try{
    		 
    		 //Declaring variables
    		 String newStaffName, newStaffPwd, newStaffDate = null;
    	 	 int newStaffVoted;
    	 
    	 	   System.out.print("\nEnter ID for new staff account: ");
    	 	   int newStaffId = Integer.parseInt(getInput());//Getting input
      	     
      	     
      	 //Checks if ID entered already exists
      	 if(vc.staffIdCheck(newStaffId) == false)
      	 { 
      		 System.out.println("\n***Sorry the ID entered already exists. Please try again***");
      		 addStaff();  
      	 }
    	 	 
      	 else {
      		 
      		//New staff password
      		System.out.print("Enter password: ");
      		newStaffPwd = getInput();
      		 
      	  //If input empty	
      	  if ((newStaffPwd.isEmpty()))
    	    {
          	System.out.println("\n***Invalid password. Please try again***");
    		   addStaff();
    	    }
      	  
      	  //If not empty
      	  else {
      		 
      		//New staff name 
     		System.out.print("Enter full name: ");
            newStaffName = getInput();
            
            //If input empty.
            if ((newStaffName.isEmpty()))
      	    {
               System.out.println("\n***Invalid name. Please try again***");
      		   addStaff();
      	    }
            
            //If not empty
            else {
            	
               //Sets staff vote count to 0
               newStaffVoted = Integer.parseInt("0");
               
               //Saves new account in the text file
               vc.newStaffAccount(newStaffId, newStaffName, newStaffVoted, newStaffDate, newStaffPwd);
               System.out.printf("\n***You have successfully added %s to the staff list***", newStaffName);
               validateAdmin(); 
			  
      }
            }
      	}
    	 }
            
          //If staff ID is not integers
	    	 catch (NumberFormatException ex)
			 {
	    		 System.out.println("\n***The staff code entered is invalid. Please enter a valid ID***");
				 addStaff();
			 } 
      		 
      
     }
     
     //Deletes a staff account 
     public void deleteStaff() {
    	 
    	 try {
    		 
    		 //Gets input
    		 System.out.print("\nEnter staff ID to delete: ");
 	         theStaff= vc.getStaff(Integer.parseInt(getInput()));
 	         
 	        //Confirmation message 
 	        System.out.printf("\n***You chose %s, Enter 'y' to confirm or 'n' to cancel***", theStaff.getName());
            String Input = getInput();
            
            //Responds to input
            if (Input.equalsIgnoreCase("y"))  
            {
        	   vc.deleteStaffAccount();
        	   System.out.println("\n***The staff account has been successfully deleted***");
        	   validateAdmin();
            }
            else if (Input.equalsIgnoreCase("n")) 
            {
            	deleteStaff();
            }
            else
    	    {
               System.out.println("\n***Invalid input! Please try again***");
    	       deleteStaff();
    	    }
    	}
    	 
    	//If ID entered is invalid
    	 catch (Exception e) {
    		 
    		 System.out.println("\n***The ID entered is invalid. Please enter a valid ID***");
			 deleteStaff();
 		}    	 
     }

    
     //Updates a staff account
     public void updateStaff() {
    	 
    	 
    	 try { 
        	 
    		 //Asks for account to update
    		 System.out.print("\nEnter staff ID to update: ");
    		 theStaff = vc.getStaff(Integer.parseInt(getInput()));
    		 System.out.printf("\n***You chose %s, Enter 'y' to confirm or 'n' to cancel***", theStaff.getName());
         	 String Input = getInput();
    	
         	 //Cancels
         	if (Input.equalsIgnoreCase("n")) {
         		
         		updateStaff();
         	}
         	 
         	
         	//Confirms 
         	else if (Input.equalsIgnoreCase("y"))  
        	   {
         		
         		System.out.println("\n\nChoose one of the following options: ");
            	System.out.println("N => Update name");
           	 	System.out.println("P => Update password");
         		
             	 Input = getInput();
             	
             	 //Updates password 
              	if (Input.equalsIgnoreCase("p"))
              	{
         		  System.out.print("\nEnter new password: ");
         		  String pwd = getInput();
         		  
         		  //if input is empty.
         		 if ((pwd.isEmpty()))
            	  {
         			System.out.println("\n***Invalid password. Please try again***");
         			updateStaff();
            	  }
         			
         			//If not empty
         			else {
         			 
         			theStaff.setPassword(pwd);//Saves new password 
             		 vc.saveStaffData(); //Updates the text file
             		 System.out.println("\n***The account has been successfully updated***");
             		 }
         		 }
              	
              	
              		//Updates name
              		else if (Input.equalsIgnoreCase("n")) {
              			
              		  System.out.print("\nEnter full name: ");
              		  String name = getInput();
               		  
               		  //if input is empty.
               		 if ((name.isEmpty()))
                  	  {
               			System.out.println("\n***Invalid name. Please try again***");
               			updateStaff();
                  	  }
               			
               			//If not empty
               			else {
               			 
               			 theStaff.setName(name);//Saves new name 
                   		 vc.saveStaffData(); //Updates the text file
                   		 System.out.println("\n***The account has been successfully updated***");
                   		 }
              			
              		}
              		else {
                 		System.out.println("\n***Invalid input. Please try again***");
                 		updateStaff();
        	   }
        	   }
         	else {
         		System.out.println("\n***Invalid input. Please try again***");
         		updateStaff();
         	}
    	 }
    	 
         	//If invalid ID entered
       	 catch (Exception e)
    		{
    		   System.out.println("\n***The ID entered is invalid. Please enter a valid ID***");
    		   updateStaff();
    		}
    	    	 
     }
    	 

     
	 //screen input reader. This method is complete and working ok.
     private String getInput()
     {
        String theInput = "";

        try
        {
            theInput = in.readLine().trim();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return theInput;
     }
	 
     
   //Voting system Home page
    public void commenceVoting()
    {
        
    	boolean SystemQuit = false;
    	
    	while (!SystemQuit)
    		
    	{
    		String Input = null;
    		
    		System.out.println("\n\n\t\t*********************Welcome to Epic Voting System!*********************\n");
    		 System.out.println("\n\nChoose one of the following options: ");
        	 System.out.println("V => Vote as a staff");
        	 System.out.println("A => Login as Administrator");
        	 System.out.println("H => Help");

    		Input = getInput();
    		
    		
    		//Staff login
    		if (Input.equalsIgnoreCase("v"))
        	{
        		manageVote();
        	}
    		
    		//Admin login
    		else if (Input.equalsIgnoreCase("a"))
    		{
    			validateAdmin();

    		}
    		
    		//Help section
    		else if (Input.equalsIgnoreCase("h"))
    		{
    			//displayStaffNotVoted();
    			
    			help();
    		}
    		
    		//Invalid input
    		else 
    		{
    			System.out.println("\n***Invalid input! Please try again***");
    		}
    	}

    }
    
    
    //Help section
    private void help() {
    	
    	System.out.println("\nHi this is the help section");
    	System.out.println("\n***Press Q to quit***");
    	
    	String Input = getInput();
    	 
    	 //Quit
    	 if (Input.equalsIgnoreCase("q")){
    		 
    		 commenceVoting();
    	 }
    	 
    	 //Invalid input
    	 else {
 		 	System.out.println("\n***Invalid input. Please try again***");
 		 	help(); 	
 	 }
 }

    
    //Staff login
    public void manageVote()
    {
    		//Staff options
    	 System.out.println("\n\nChoose one of the following options: ");
    	 System.out.println("L => Log in");
    	 System.out.println("Q => Quit");
  
    	 String Input = getInput();
    	 
    	 //Quit
    	 if (Input.equalsIgnoreCase("q")){
    		 
    		 commenceVoting();
    	 }
    	 
    	 
    	 // staff login  
    	 else if (Input.equalsIgnoreCase("l")){
    	 
    	 try { 	
     	System.out.print("\nEnter Staff ID:");
     	int id = (Integer.parseInt(getInput())); 
    	 
     	if (vc.getStaff(id) != null) {
     		
     		 theStaff = vc.getStaff(id);
     		 
     		   System.out.print("Enter password: ");
			   String pwd = getInput();
			   
			   //Checks if the Staff password matches the input.
			   if (theStaff.getPassword().equals(pwd)){   		 
     		 
				   	if (theStaff.hasVoted() == 1)
				   	{
				   		System.out.println("\n***You have already voted. Goodbye!***");
				   		commenceVoting();
				   	}
				   	else {
				   		displayVotingScreen();
				   	}   
			   }
     	
			   //Invalid password
			  else {
				   System.out.println("\n***Incorrect password. Please enter a valid password***");
				   loginAttempts++;
     		
				   //More than 3 failed login attempts
				   if (loginAttempts > 3)
       	 {
					   loginAttempts = 0;
					   System.out.println("\n***Too many unsuccessful login attempts***");
					   System.out.println("\nReturning back to the main screen...");
					   commenceVoting();
       	 }
				   //Less than 3 failed attempts
				   else {
     		
					   manageVote();
				   }
     	}
    }
     	
     	//Non-existent ID
     	else {
     		
     		System.out.println("\n***Invalid ID. Please enter a valid ID***");
     		loginAttempts++;
   		
   		 
   			if (loginAttempts > 3)
   	       	 {
   	     		  loginAttempts = 0;
   	       		  System.out.println("\n***Too many unsuccessful login attempts***");
   	       		  System.out.println("\n\nReturning back to the main screen...");
   	       		  commenceVoting();
   	       	 }
   	     		else {
   	     		
   	     		manageVote();
   	     		}
     	}
}
    	 
    	 //Invalid ID
    	 catch(NumberFormatException ex){
    		 
    		 System.out.println("\n***Invalid ID. Please enter a valid ID***");
    		 loginAttempts++;
    		
    		 
    			if (loginAttempts > 3)
    	       	 {
    	     		  loginAttempts = 0;
    	       		  System.out.println("\n***Too many unsuccessful login attempts***");
    	       		  System.out.println("\nReturning back to the main screen...");
    	       		  commenceVoting();
    	       	 }
    	     		else {
    	     		
    	     		manageVote();
    	     		}
    	 }
   }
    	 
    	 else {
    		 	System.out.println("\n***Invalid input. Please try again***");
    		 	manageVote(); 	
    	 }

     }

    
    //Voting screen after a staff successfully logs in 
     public void displayVotingScreen()
     {    	 
    	 	//welcome message after a successful staff login
		   System.out.printf("\n***Welcome %s!***", theStaff.getName());
    	
	  	//displays candidate list and voting instructions
    	 getStaffVote();

     }
  
     
    //Voting instructions and recording staff vote
     private void getStaffVote()
     {
	   	 	try{
    	 		
	  	    //Column titles 
	  	    System.out.printf("%s %7s %s %7s", "\n\nCode", "Name", "\n----", "----");
	  	    
	  	     
	  	    //Displays the candidate list
	  	    int i = vc.candidates.indexOf(0); 
	  	    for (i = 0; i < vc.candidates.size(); i++) 
	  	    {
	  		   System.out.print("\n" + vc.candidates.get(i).candidateCode + "\t" + vc.candidates.get(i).name);
	  	    }
	  	  
    	 //voting instructions 
	  	  System.out.print("\n\nEnter candidate code and press ENTER: ");
	  	  theCandidate = vc.getCandidate(Integer.parseInt(getInput())); //Gets candidate code
	  	  System.out.printf("\n***You chose %s, Enter 'y' to continue or 'n' to cancel***", theCandidate.getName());
	  	  String Input = getInput();
	  	  
	  	  //to continue
	  	 if (Input.equalsIgnoreCase("y")){
	  		System.out.printf("\n***You have successfully voted for %s, on %s***\n", theCandidate.getName(), date);
	  		System.out.print("\n***Thanks for voting!***\n");
	  		vc.recordVote(date);//Records the vote
	  		commenceVoting();	
	  	 }
	  	 
	  	 //to cancel
	  	 else if (Input.equalsIgnoreCase("n")){
	  		 
	  		getStaffVote();
	  	 }
	  	
	  	 //Invalid input
	  	 else {
	  		System.out.println("\n***Invalid input! Please try again***\n");
	  		getStaffVote();
	  	 }
    	 	}
    	 	//Candidate code not recognised
    	 	catch (Exception e){
    	 		 System.out.println("\n***The candidate code entered is invalid. Please enter a valid code***\n");
    	 		getStaffVote();
    	 	}
     }
     
     //Voting results for the admin
     public void printVoteResults()
     {
    	{		//Staff stats
    	      	System.out.println("\nStaff Representative Voting STATISTICS");
    	       	System.out.println("----------------------------------------\n");
    	       	System.out.printf("Numbers on voting list: \t%s", vc.staffs.size());
    	       	System.out.printf("\nNumbers voted: \t\t\t%s", vc.voters);
    	       	
    	       	
    	       	//Candidate stats
    	       	System.out.println("\n\nVoting Results");
    	        System.out.println("---------------");
    	    	System.out.printf("%s%10s%15s", "ID", "Name", "Votes");
    	    	
    	       	//Retrieves candidate list 
    	       	int i = vc.candidates.indexOf(0); 
    	  	    for (i = 0; i < vc.candidates.size(); i++) 
    	  	    {
    	  		     System.out.print("\n" + vc.candidates.get(i).candidateCode + "\t" + vc.candidates.get(i).getName() + "\t" + vc.candidates.get(i).votes );
    	  	    }
      	 }
    }
}
 