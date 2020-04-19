package EpicVotingSystem;
/**
 * File Name :
 * author : 21700361
 * Date :
 * Description :
 */
import java.io.*; 
import java.text.DecimalFormat;
import java.util.*;


public class VotingController
{
    //Create an Arraylist read & store staff & candidate data from file
    public ArrayList<Staff> staffs = new ArrayList<Staff>();
    public ArrayList<Candidate> candidates = new ArrayList<Candidate>();
    public ArrayList<admin> admins = new ArrayList<admin>();

   //Type to access individual staff & candidate from array list
    private Staff theStaff;
    private Candidate theCandidate;
    private admin theAdmin;
    
    public int voters = 0;//Total voters

    
    //VotingController constructor
    public VotingController()
    {
        loadStaffData();
        loadCandidateData();
        loadAdminData();
    }

    //loads candidates from file. This method is complete and working ok.
    public void loadCandidateData()
    {
        try
        {
             String fileName = "candidates.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));

             String candidateData;

             while((candidateData = reader.readLine())!= null)
             {
                 String[] candidateDetails = candidateData.split(",");
                 int code = Integer.parseInt(candidateDetails[0]);
                 int votes = Integer.parseInt(candidateDetails[2]);
                 theCandidate = new Candidate(code, candidateDetails[1], votes);
                 candidates.add(theCandidate);
             }
             reader.close();
         }
         catch(IOException e)
         {
             System.out.println("Error! There was a problem loading candidate details from the file");
         }
    }
    
    
    //loads staff from the staff text file.
    public void loadStaffData()
    {
        try
        {
             String fileName = "staff.txt";
             File theFile = new File(fileName);
             BufferedReader reader = new BufferedReader(new FileReader(theFile));

             String staffData;

             while((staffData = reader.readLine())!= null)
             {
                 String[] staffDetails = staffData.split(",");
                 int id = Integer.parseInt(staffDetails[0]);
                 String name = (staffDetails[1]);
                 int voted = Integer.parseInt(staffDetails[2]);
                 String date = (staffDetails[3]);
                 String password = (staffDetails[4]);
                 theStaff = new Staff(id, name, voted, date, password);
                 staffs.add(theStaff);
                 
                 if (theStaff.hasVoted() == 1)
                 {
                	 voters++;
                 }
             }

             reader.close();
         }
         catch(IOException e)
         {
             System.out.println("Error! There was a problem loading staff details from the file");
         }
    }
    
    
    //loads admin data from admin text file.
    public void loadAdminData()
     {
        	try
         {
              String fileName = "admin.txt";
              File theFile = new File(fileName);
              BufferedReader reader = new BufferedReader(new FileReader(theFile));

              String adminData;

              while((adminData = reader.readLine())!= null)
              {
                  String[] adminDetails = adminData.split(",");
                  int id = Integer.parseInt(adminDetails[0]);
                  String name = (adminDetails[1]);
                  String username = (adminDetails[2]);
                  String password = (adminDetails[3]);
                  theAdmin = new admin (id, name, username, password);
                  admins.add(theAdmin);
                  
              }
              reader.close();
          }
          catch(IOException e)
          {
              System.out.println("Error! There was a problem loading admin details from the file");
          }
     }

    
    //returns a staff if found in the staffs ArrayList
    public Staff getStaff(int id)
    {
        Iterator<Staff> it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.getId()== id)
            {
                return theStaff;
            }
        }
        return null;
    }
    
    
    //Returns staff that have voted
    public Staff displayStaffVoted()
    {
        Iterator<Staff> it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.hasVoted() == 1)
            {
            	System.out.print("\n" + theStaff.getId() + "\t\t" + theStaff.getName() + "\t\t" + theStaff.getDate());
            }
        }
        return null;
    }
    
    
    //Returns staff that have not voted
    public Staff displayStaffNotVoted()
    {
        Iterator<Staff> it = staffs.iterator();
        while(it.hasNext())
        {
            theStaff = (Staff) it.next();
            if(theStaff.hasVoted() == 0)
            {
            	System.out.print("\n" + theStaff.getId() + "\t\t" + theStaff.getName());
            }
        }
        return null;
    }
    
    
  //returns an admin if found in the admin ArrayList
    public admin getAdmin(String username)
    {
        Iterator<admin> it = admins.iterator();
        while(it.hasNext())
        {
            theAdmin = (admin) it.next();
            if((theAdmin.getUsername()).equals(username))
            {
                return theAdmin;
            }
        }
        return null;
    }

    
  //Looks if the new admin ID already exists in the admin ArrayList
    public boolean adminIdCheck(int newAdminId)
    {
        Iterator<admin> it = admins.iterator();
        while(it.hasNext())
        {
            theAdmin = (admin) it.next();
            if(theAdmin.getId() == newAdminId)
            {
                return false;
            }
        }
        return true;
    }
	
    
    //Looks if the new admin username already exists in the admin ArrayList
    public boolean adminUsernameCheck(String newAdminUsername)
    {
        Iterator<admin> it = admins.iterator();
        while(it.hasNext())
        {
            theAdmin = (admin) it.next();
            if(theAdmin.getUsername() == newAdminUsername)
            {
                return false;
            }
        }
        return true;
    }
    
    //Saves new admin account in the text file
    public void newAdminAccount(int newAdminId, String newAdminName, String newAdminUsername, String newAdminPwd) {
   
    	theAdmin = new admin(newAdminId, newAdminName, newAdminUsername, newAdminPwd);
    	admins.add(theAdmin);
    	saveAdminData();
    }
    
    //Deletes admin account
    public void deleteAdminAccount()
    {
    	admins.remove(theAdmin);//Removes admin account
    	saveAdminData();//Updates the text file
    }
    
    
    //Looks if the new staff ID already exists in the staff ArrayList
      public boolean staffIdCheck(int newStaffId)
      {
          Iterator<Staff> it = staffs.iterator();
          while(it.hasNext())
          {
              theStaff = (Staff) it.next();
              if(theStaff.getId() == newStaffId)
              {
                  return false;
              }
          }
          return true;
      }
      
    //Saves new staff account in the text file
      public void newStaffAccount(int newStaffId, String newStaffName, int newStaffVoted, String newStaffDate, String newStaffPwd) {
     
      	theStaff = new Staff(newStaffId, newStaffName, newStaffVoted, newStaffDate, newStaffPwd);
      	staffs.add(theStaff);
      	saveStaffData();
      }
  	
      //Deletes staff account
      public void deleteStaffAccount()
      {
      	staffs.remove(theStaff);//Removes staff account
      	saveStaffData();//Updates the text file
      }
      

    //Looks if the new candidate ID already exists in the candidate ArrayList
      public boolean candidateCodeCheck(int newCandidateCode)
      {
          Iterator<Candidate> it = candidates.iterator();
          while(it.hasNext())
          {
              theCandidate = (Candidate) it.next();
              if(theCandidate.getCandidateCode() == newCandidateCode)
              {
                  return false;
              }
          }
          return true;
      }
      
      //Saves new candidate account in the text file
      public void newCandidateAccount(int newCandidateCode, String newCandidateName, int newCandidateVotes) {
     
      	theCandidate = new Candidate(newCandidateCode, newCandidateName, newCandidateVotes);
      	candidates.add(theCandidate);
      	saveCandidateData();
      }
      
      //Deletes candidate account
      public void deleteCandidateAccount()
      {
      	candidates.remove(theCandidate);//Removes candidate account
      	saveCandidateData();//Updates the text file
      }
    
    
    //returns the collection of candidates
    public ArrayList<Candidate> getCandidates()
    {
        return candidates;
    }
    
    //returns the collection of staff
    public ArrayList<Staff> getStaff()
    {
        return staffs;
    }
    
    public ArrayList<admin> getAdmin()
    {
        return admins;
    }

    //returns total number of staffs in the collection
    public int getTotalVoters()
    {
        return staffs.size();
    }


    //every staff vote must be saved to file
    public void recordVote(String date)
    {
        theStaff.setVoted();
        theStaff.setDate(date);
        theCandidate.addVote();
        saveStaffData();//save to file
        saveCandidateData();//save to file
    }


    //updates staff data in staff text file 
    public void saveStaffData()
    {
        try
        {
            BufferedWriter writer = new  BufferedWriter(new FileWriter("staff.txt"));
            Iterator<Staff> it = staffs.iterator();
            String staffDetails;
            while(it.hasNext())
            {
                theStaff = (Staff) it.next();
                staffDetails = theStaff.getId() + "," +theStaff.getName() + "," + theStaff.hasVoted() + "," + theStaff.getDate() + "," + theStaff.getPassword() + "\n";
                writer.write(staffDetails);
            }
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

  
  //updates candidate data in candidate text file 
    public void saveCandidateData()
    {
    	 try
         {
             BufferedWriter writer = new  BufferedWriter(new FileWriter("candidates.txt"));
             Iterator<Candidate> it = candidates.iterator();
             String candidateDetails;
             while(it.hasNext())
             {
                 theCandidate = (Candidate) it.next();
                 candidateDetails = theCandidate.getCandidateCode() + "," +theCandidate.getName() + "," + theCandidate.getVotes() +"\n";
                 writer.write(candidateDetails);
             }
             writer.close();
         }
         catch(IOException e)
         {
             System.out.println(e);
         }
    }
    
    
    //updates admin data in admin text file 
    public void saveAdminData()
    {
    	 try
         {
             BufferedWriter writer = new  BufferedWriter(new FileWriter("admin.txt"));
             Iterator<admin> it = admins.iterator();
             String adminDetails;
             while(it.hasNext())
             {
                 theAdmin = (admin) it.next();
                 adminDetails = theAdmin.getId() + "," +theAdmin.getName() + "," + theAdmin.getUsername()  + "," + theAdmin.getPassword() +"\n";
                 writer.write(adminDetails);
             }
             writer.close();
         }
         catch(IOException e)
         {
             System.out.println(e);
         }
    }


    
    
    //returns a candidate if found in the candidates ArrayList
    public Candidate getCandidate(int candidateCode)
    {
    	 Iterator<Candidate> it = candidates.iterator();
         while(it.hasNext())
         {
             theCandidate = (Candidate) it.next();
             if(theCandidate.getCandidateCode()== candidateCode)
             {
                 return theCandidate;
             }
         }
        return null;
   
    }
    
 
     
}