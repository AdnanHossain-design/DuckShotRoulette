import java.util.Scanner;
import java.util.Random;

public class Game 
{
	
	public static void main(String[] args) 
	{
		startgame();

	}
	public static void startgame() 
	{
		
		Scanner input = new Scanner(System.in); 
	
		//System.out.print("Please enter your name: ");
        //String userName = input.nextLine();
     
        // Create an array of levels
        Level[] levels = {new Level2(), new Level3()};
        
        // Start each level
        for (Level level : levels) 
        {
            level.start();
            
        }
        
	 
       
    }

}


