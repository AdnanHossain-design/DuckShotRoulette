import java.util.Random;
import java.util.Scanner;

class Level1 extends Level 
{
    void start() 
    {
        Scanner input = new Scanner(System.in);

        System.out.println("----------Level 1-----------");

        int round = 1;
        
        int[] dealerHealth = {2};
        int[] playerHealth = {2};
        currentChamber = 0;
        boolean playersTurn = true;
        boolean dealersTurn = false;
        boolean[] lund = {false};
        Level2 level2 = new Level2();

        while (round < 8) 
        {
        	int[] blank = new int[1];
            int[] live = new int[1];
            
            System.out.println("\n   -------Round " + round + "-------");
            

            if (round == 1) 
            {
                blank[0] = 3;
                live[0] = 2;
            } 
            else if (round == 2)
            {
                blank[0] = 3;
                live[0] = 2;
            }
            else if (round > 2) 
            {
                blank[0] = 3;
                live[0] = 3;
            }
            else 
            {
                blank[0] = 2;
                live[0] = 3;
            }
            
            int totalBullets = blank[0] + live[0];
            
            
            System.out.println("Lives: " + live[0]);
            System.out.println("Blanks: " + blank[0]);
            
            
            // Create an array to represent the gun
            String[] gun = new String[totalBullets];

            // Fill the gun with blanks and lives randomly
            Random random = new Random();
            for (int i = 0; i < blank[0]; i++)
            {
                int randomPosition = random.nextInt(totalBullets);
                while (gun[randomPosition] != null) 
                {
                    randomPosition = random.nextInt(totalBullets);
                }
                gun[randomPosition] = "Blank";
            }

            for (int i = 0; i < live[0]; i++) 
            {
                int randomPosition = random.nextInt(totalBullets);
                while (gun[randomPosition] != null) 
                {
                    randomPosition = random.nextInt(totalBullets);
                }
                gun[randomPosition] = "Live";
            }

            // Display each bullet in the gun
            //System.out.println("\nGun with bullets:");
            //for (String bullet : gun) 
            {
                //System.out.print(bullet + " ");
            }
            //System.out.println("\n");
            

            while (playerHealth[0] > 0 && dealerHealth[0] > 0 && totalBullets > 0) 
            {
                System.out.println("\nPlayer: " + playerHealth[0]);
                System.out.println("Dealer: " + dealerHealth[0]);
                //System.out.println("Total Bullets: " + totalBullets);
                
                
                if(playersTurn) 
                {
                	System.out.println("\n\n-----Your turn-----");
                    System.out.println("Shoot Dealer (X) | Shoot Yourself (Skips dealer's turn if blank) (Y)");

                    String choice = "";
	                while (!choice.equalsIgnoreCase("X") && !choice.equalsIgnoreCase("Y")) 
	                {
	                    System.out.println("Shoot Dealer (X) | Shoot Yourself (Y) (Skips dealer's turn if blank)");
	                    if (input.hasNext()) {
	                        choice = input.next();
	                    }
	                }
	                
	                playerShootGun(gun, dealerHealth, playerHealth, live, blank, choice, totalBullets);

                    if (choice.equalsIgnoreCase("Y") && gun[currentChamber-1].equals("Blank")) 
                    {
                        dealersTurn= false;
                    }
                    
                    else
                    {
                        dealersTurn= true;
                    }
                    totalBullets--;
                } 
                
                if (totalBullets > 0 && playerHealth[0] > 0 && dealerHealth[0] > 0 && dealersTurn)
                {
                	dealerShootGun(gun, dealerHealth, playerHealth, live, blank, totalBullets, lund);
                	
                	if(gun[currentChamber-1].equals("Blank"))
                	{
                		playersTurn=false;
                	}
                	
                	else
                	{
                		playersTurn = true;
                	}
                    totalBullets--;    

                }
                
                if (totalBullets == 0 && playerHealth[0] > 0 && dealerHealth[0] > 0) 
                {
                    System.out.println("\n\nYou survived. Next round.");
                    round++;
                }
                else if (playerHealth[0] > 0 && dealerHealth[0] == 0) 
                {
                    System.out.println("\n\nCongrats. Move to level 2.");
                    level2.start();
                    return;
                } 
                else if (playerHealth[0] == 0 && dealerHealth[0] > 0) 
                {
                    System.out.println("You lost. Begin level 1 again.");
                    round = 1;
                    dealerHealth[0] = 2;
                    playerHealth[0] = 2;
                    break;
                }
            }
        }
    }

    private int currentChamber = 0;

    // Method to shoot from the gun in the order of loading
    private void playerShootGun(String[] gun, int[] dealerHealth, int[] playerHealth, int[] live, int[] blank, String choice, int totalBullets) 
    {
        if (currentChamber < gun.length) 
        {
            String bullet = gun[currentChamber];

            if (bullet.equals("Live")) 
            {
                System.out.println("\nBang!.");
                
                if(choice.equalsIgnoreCase("X"))
                {
                	
                System.out.println("Dealer was hit.");
                dealerHealth[0]--;
                
                }
                else if (choice.equalsIgnoreCase("Y"))
                {
                	
                System.out.println("You shot yourself.");
                playerHealth[0]--;
                	
                }
                live[0]--;
                
            } 
            if (bullet.equals("Blank")) 
            {
                System.out.println("\nClick!.");
                
                if(choice.equalsIgnoreCase("X"))
                {
                	
                System.out.println("Dealer was not hit.");
                
                }
                else if (choice.equalsIgnoreCase("Y"))
                {
                	
                System.out.println("You were not shot. Your turn again.");
                
                }
                blank[0]--;
                
            }
            currentChamber++;
        }
        
    }


    private void dealerShootGun(String[] gun, int[] dealerHealth, int[] playerHealth, int[] live, int[] blank, int totalBullets, boolean[] lund) 
    {
    	{
            if (currentChamber < gun.length) 
            {
                String bullet = gun[currentChamber];
                System.out.println("\n-----Dealer's turn-----");

                if (live[0] > blank[0]) 
                {
                    System.out.println("Dealer aims at you.");
                    if (bullet.equals("Live")) 
                    {
                        System.out.println("\nBang!.");
                        System.out.println("You were hit.");
                        playerHealth[0]--;
                        live[0]--;
                        
                    } 
                    else 
                    {
                        System.out.println("\nClick.");
                        System.out.println("You were not hit.");
                        blank[0]--;
                        
                    }
                    lund[0] = false;
                } 
                else if (live[0] < blank[0]) 
                {
                    System.out.println("Dealer aims at himself.");
                    if (bullet.equals("Live")) 
                    {
                        System.out.println("\nBang!.");
                        System.out.println("Dealer shot himself.");
                        dealerHealth[0]--;
                        live[0]--;
                        lund[0] = false;
                        
                    } 
                    else 
                    {
                        System.out.println("\nClick.");
                        System.out.println("Dealer was not hit. Dealer's turn again. ");
                        lund[0] = true;
                        blank[0]--;
                        
                    }
                } 
                else if (live[0] == blank[0]) 
                {
                    double chance = Math.random();
                    if (chance < 0.5) 
                    {
                        System.out.println("Dealer aims at you.");
                        if (bullet.equals("Live")) 
                        {
                            System.out.println("\nBang!");
                            System.out.println("You were hit.");
                            playerHealth[0]--;
                            live[0]--;
                            
                        } 
                        else 
                        {
                            System.out.println("\nClick.");
                            System.out.println("You were not hit.");
                            blank[0]--;
                        }
                        lund[0] = false;
                    } 
                    else 
                    {
                        System.out.println("Dealer aims at himself.");
                        if (bullet.equals("Live")) 
                        {
                            System.out.println("\nBang!");
                            System.out.println("Dealer shot himself.");
                            dealerHealth[0]--;
                            live[0]--;
                            lund[0] = false;
                            
                            
                        } 
                        else 
                        {
                            System.out.println("\nClick.");
                            System.out.println("Dealer was not hit.");
                            blank[0]--;
                            lund[0] = true;
                            
                            
                        }
                    }
                }
            }
            currentChamber++;
            
            System.out.println("\nPlayer: " + playerHealth[0]);
            System.out.println("Dealer: " + dealerHealth[0]);

        }
    }
}
