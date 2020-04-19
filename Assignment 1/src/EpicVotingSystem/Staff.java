//Note: This class is complete. Don’t change code in this class,
//unless you want to add more functionality  for Staff object

package EpicVotingSystem;
/**
 * File Name :
 * author :21700361
 * Date :
 * Description :
 */
public class Staff
{
    public int id;
    public String name;
    public int voted; //has the staff voted
    public String date;
    String password;

    public Staff(int id, String name, int voted, String date, String password)
    {
            this.id = id;
            this.name = name;
            this.voted = voted;
            this.date = date;
            this.password = password;
    }

    public void setId(int id)
    {
       this.id = id;
    }

    public void setName(String name)
    {
            this.name = name;
    }

    public void setVoted()
    {
            this.voted = 1;
    }
    
    public void setDate(String date)
    {
            this.date = date;
    }
    
    public void setPassword(String password)
    {
            this.password = password;
    }
        

    public int getId()
    {
       return id;
    }

    public String getName()
    {
            return name;
    }

    public int hasVoted()
    {
            return voted;
    }
    
    public String getDate()
    {
            return date;
    }
    
    public String getPassword()
    {
            return password;
    }
    

}
