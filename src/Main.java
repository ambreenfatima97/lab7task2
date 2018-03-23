
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import java.sql.*;
import java.util.Scanner;

public class Main 
{

    public static void main(String[] args) 
    {
	
        try
        {
            int j=1;
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/uni", "root", "azlan2008");
            Scanner input = new Scanner(System.in);
            System.out.println("\n Choose \n 1:Display All Data\n 2:Delete a Record\n 3:Search a Record\n 0:exit");
            j=input.nextInt();
            while(j!=0) 
            {
                Statement s = null;
                ResultSet R= null;
                
                
                
                if (j == 1) 
                {
                    s = connection.createStatement();
                    R= s.executeQuery("select * from student");

                    System.out.println("Sr#\tName\tReg.no\tClass\tSection\tNumber\t\tAddress\t");
                    while (R.next()) 
                    {
                        for (int k = 1; k <= 7; k++) 
                        {
                            System.out.print(R.getString(k) + " \t");
                        }
                        System.out.println("\n");
                        //System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
                    }
                }
                else if (j == 2) 
                {
                    System.out.println("\nEnter a Reg # to Delete");

                    int reg = input.nextInt();
                    s = connection.createStatement();
                    String query = "SELECT * from student WHERE reg_no=" + reg;
                    R= s.executeQuery(query);
                    if (R.first()) 
                    {
                        s = connection.createStatement();
                        query = "DELETE from student WHERE reg_no=" + reg;
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.executeUpdate();
                        System.out.println("Record Deleted\n");
                    } 
                    else System.out.println("Invalid Registration Number, Try Again\n");
                }
                else if(j==3)
                {
                    System.out.println("Enter a Name \n");
                    input.skip("[\r\n]+");
                    
                    String name=input.nextLine();
                    s = connection.createStatement();
                    String query = "SELECT * from student WHERE name='" + name+"'";
                    R= s.executeQuery(query);
                    if(R.first())
                    {
                        
                        System.out.println("Sr#\tName\tReg.no\tClass\tSection\tNumber\t\tAddress");
                        for (int k = 1; k <= 7; k++) 
                        {
                            System.out.print(R.getString(k) + " \t");
                        }
                        System.out.println("\n");
                    }
                    else
                    {
                        System.out.println("Invalid Name\n");
                    }

                }
                else
                {
                    System.out.println("Wrong Option\n");
                }
                
                
                System.out.println("\n Choose \n 1:Display All Data\n 2:Delete a Record\n 3:Search a Record\n 0:exit");
                j=input.nextInt();
            }
            connection.close();
        }
        catch(Exception e)
        { 
        	System.out.println("Error:"+e.getMessage());
        }
    }
}
