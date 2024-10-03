import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Level2 extends Level{
	
	
    void start() {
        
    	Scanner input = new Scanner(System.in);
	    System.out.println("\n----------Level 2-----------");

	    int round = 1;
	    int[] dealerHealth = {30};
        int[] playerHealth = {30};
        currentChamber=0;
        boolean[] playersTurn = {true};
        boolean[] dealersTurn = {false};
        ArrayList<String> playerInventory = new ArrayList<>();
        ArrayList<String> dealerInventory = new ArrayList<>();
        String[] items = {"Handcuffs", "Beer", "Magnifying Glass", "Cigarette", "Saw"};
        int itemChoice  = 9;
        boolean[] checkDealer = {false};
        boolean[] checkHandcuffs = {false};
        boolean [] doubleDamage = {false};
        
        Level3 level3 = new Level3();
        
        

	    while (round<15)
	    {
	        System.out.println("\n   -------Round " + round + "-------");

	        
	        int[] blank = new int[1];
            int[] live = new int[1];
	        
	        if (round==1) 
	       
	        {
	            blank[0] = 7;
	            live[0] = 7;
	        } 
	        else if (round==2)
	        {
	            blank[0] = 2;
	            live[0] = 2;
	        }
	        else if (round==3)
	        {
	        	blank[0] = 3;
	            live[0] = 3;
	        }
	        
	        else if (round==4)
	        {
	        	blank[0] = 2;
	            live[0] = 3;
	        }
	        else if (round > 4 && round % 2 != 0) 
	        {
	        	blank[0] = 3;
	            live[0] = 4;
	        }
	        else {
	        	blank[0] = 4;
	            live[0] = 4;
	        }
	        int totalBullets = blank[0] + live[0];
	        
	        
	        String[] gun = new String[totalBullets];

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

	        System.out.println("\nGun with bullets:");
	        for (String bullet : gun) 
	        {
	            System.out.print(bullet + " ");
	        }
	        System.out.println("\n");
	        
            
           
            
            while (playerHealth[0] > 0 && dealerHealth[0] > 0 && totalBullets > 0) 
            {
            	System.out.println("\nPlayer: " + playerHealth[0]);
                System.out.println("Dealer: " + dealerHealth[0]);
                System.out.println("Lives: " + live[0]);
                System.out.println("Blanks: " + blank[0]);

                System.out.println("Total Bullets: " + totalBullets);
                
                
                if(playersTurn[0]) 
                {
                	if(playerInventory.size()<8) 
                	{
                		for(int i=0; i<2;i++) 
                		{
	            	    playerInventory.add(items[random.nextInt(items.length)]);
                		}
                			if (dealerInventory.size()==8) 
                			{
                			System.out.print("\nYou get 2 items.");
                			}
                			else 
                			{
                			System.out.print("\nYou both get 2 items.");
                			}
                	} 
                    
                	if (dealerInventory.size()<8)
					{
                		for(int i=0; i<2;i++) 	
                		{
                    	dealerInventory.add(items[random.nextInt(items.length)]);
                		}
                			if (playerInventory.size()==8) 
                			{
                				System.out.print("\nDealer gets 2 items.");
                			}
                    }
                	
   
	            	System.out.print("\nPlayer's Inventory:");
	            	for (int i = 0; i < playerInventory.size(); i++) 
	            	{
	                    System.out.print(" " + (i + 1) + "." + playerInventory.get(i));
	                }
	            	
	            	System.out.print("\nDealer's Inventory:");
	            	for (int i = 0; i < dealerInventory.size(); i++) 
	            	{
	                    System.out.print(" " + (i + 1) + "." + dealerInventory.get(i));
	                }
                   
	                System.out.println("\n\n-----Your turn-----");
	            	
	            	playerChooseItem(gun, dealerHealth, playerHealth, items, itemChoice, playerInventory, totalBullets, live, blank, doubleDamage, checkHandcuffs);
                    
	            	String choice = "";
	                while (!choice.equalsIgnoreCase("X") && !choice.equalsIgnoreCase("Y")) 
	                {
	                    System.out.println("\nShoot Dealer (X) | Shoot Yourself (Y) (Skips dealer's turn if blank)");
	                    if (input.hasNext()) 
	                    {
	                        choice = input.next();
	                    }
	                }
                    
	                playerShootGun(gun, dealerHealth, playerHealth, live, blank, choice, totalBullets, doubleDamage, checkHandcuffs);

                    if (choice.equalsIgnoreCase("Y") && gun[currentChamber-1].equals("Blank")) 
                    {
                    	dealersTurn[0]= false;   
                    }
                    
                    else if (checkHandcuffs[0]) 
                    {
                    	dealersTurn[0]= false;
                       
                    }
                    
                    
                    else
                    { 
                        dealersTurn[0]= true;
                    }
                    totalBullets--;
                    checkHandcuffs[0] = false;
                }

                
                if (totalBullets > 0 && playerHealth[0] > 0 && dealerHealth[0] > 0 && dealersTurn[0])
                {
                	dealerShootGun(gun, dealerHealth, playerHealth, live, blank, totalBullets, checkDealer);
                	
                	if(gun[currentChamber-1].equals("Blank") && checkDealer[0])
                	{
                		playersTurn[0]=false;
                	}
                	
                	else
                	{
                		playersTurn[0] = true;
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
                    System.out.println("\n\nCongrats. Move to the final level .");
                    level3.start();
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

    private void playerChooseItem(String[] gun,int[] dealerHealth, int[] playerHealth, String[] items, int itemChoice, ArrayList<String> playerInventory, int totalBullets,int[] live, int[] blank, boolean[] doubleDamage, boolean[] checkHandcuffs) 
    {
    Scanner input = new Scanner(System.in);

        while (itemChoice >= playerInventory.size() && playerInventory.size()!=0) 
        {

        	if (playerInventory.size()>1) 
        	{
            System.out.println("\nChoose to use an item (1-" + playerInventory.size() + ") | Skip (0)");
        	}
        	else if(playerInventory.size()==1)
        	{
        		System.out.println("\nChoose to use an item (1) | Skip (0)");
        	}
        	
        	
        	
        	if (input.hasNextInt()) 
            {
            	itemChoice = input.nextInt();
            	if (itemChoice > 0 && itemChoice <= playerInventory.size()) 
            	{
                    System.out.println("You used a " + playerInventory.get(itemChoice - 1) + ".");
                    if(playerInventory.get(itemChoice - 1).equals("Cigarette")) 
                    {
                    	playerHealth[0]++;
                    	System.out.println("Your health increased.");
                    	System.out.println("\nPlayer: " + playerHealth[0]);
                    	System.out.println("Dealer: " + dealerHealth[0]);
                    }
                    
                    else if(playerInventory.get(itemChoice - 1).equals("Magnifying Glass")) 
                    {
                    	 System.out.println("Current bullet in chamber: " + gun[currentChamber]);

                    }
                    
                    else if(playerInventory.get(itemChoice - 1).equals("Beer")) 
                    {
                        System.out.println("You cock the current bullet out of the chamber.");
                        String removedBullet = gun[currentChamber];
                        System.out.println("A " + removedBullet + " bullet pops out.");
                        
                        gun[currentChamber] = null; 

                        if ("Live".equals(removedBullet)) 
                        {
                            live[0]--;
                        }
                        else if ("Blank".equals(removedBullet)) 
                        {
                            blank[0]--;
                        }

                        currentChamber = advanceChamber(gun, currentChamber);

                    }
                    
                    else if(playerInventory.get(itemChoice - 1).equals("Saw")) 
                    { 
                    	System.out.println("You sawd off the gun barrel. Double damage.");
                    	 doubleDamage[0] = true;

                    }
                    
                    else if(playerInventory.get(itemChoice - 1).equals("Handcuffs")) 
                    { 
                    	 System.out.println("You handcuff the dealer. Dealer skips his next turn. ");
                     	 checkHandcuffs[0]= true;

                    }
                    
                    playerInventory.remove(itemChoice - 1);
                    
                    if(playerInventory.size()!=0)
                    {
	            	for (int i = 0; i < playerInventory.size(); i++) 
	            		{
	            		System.out.print("\nPlayer's Inventory:");
	                    System.out.print(" " + (i + 1) + "." + playerInventory.get(i));
	            		}
                    }

            	} 
            	else if (itemChoice==0)
            	{
            		break;
            	}
            }
            else 
            {
                input.next(); 
            }
        }
    }
    
    private int advanceChamber(String[] gun, int currentChamber) 
    {
        do 
        {
            currentChamber = (currentChamber + 1) % gun.length; 
        } 
        while (gun[currentChamber] == null || gun[currentChamber].isEmpty()); 

        return currentChamber;
    }
    
    private void playerShootGun(String[] gun, int[] dealerHealth, int[] playerHealth, int[] live, int[] blank, String choice, int totalBullets, boolean[] doubleDamage, boolean[] checkHandcuffs) 
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
                
                	if(doubleDamage[0]) 
                	{
                	dealerHealth[0]=dealerHealth[0]-2;
                	}
                	else 
                	{
                	dealerHealth[0]--;
                	}
                }
                else if (choice.equalsIgnoreCase("Y"))
                {
                	
                System.out.println("You shot yourself.");
                
                	if(doubleDamage[0]) 
                	{
                	playerHealth[0] = playerHealth[0]-2;
                	}
                	else 
                	{
                	playerHealth[0]--;
                	}
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
            
            doubleDamage[0] = false;
            
            System.out.println("\nPlayer: " + playerHealth[0]);
            System.out.println("Dealer: " + dealerHealth[0]);
        }
        
    }

    private void dealerShootGun(String[] gun, int[] dealerHealth, int[] playerHealth, int[] live, int[] blank, int totalBullets, boolean[] checkDealer) 
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
                    checkDealer[0] = false;
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
                        checkDealer[0] = false;
                        
                    } 
                    else 
                    {
                        System.out.println("\nClick.");
                        System.out.println("Dealer was not hit. Dealer's turn again. ");
                        checkDealer[0] = true;
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
                        checkDealer[0] = false;
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
                            checkDealer[0] = false;
                            
                            
                        } 
                        else 
                        {
                            System.out.println("\nClick.");
                            System.out.println("Dealer was not hit.");
                            blank[0]--;
                            checkDealer[0] = true;
                            
                            
                        }
                    }
                }
            }
            currentChamber++;

        }
    }
	
    
   }
