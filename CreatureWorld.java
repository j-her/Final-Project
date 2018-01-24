import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import javax.swing.JOptionPane;
import java.util.List;

/**
 * Name: Jackson Hercina
 * Course: CS20S
 * Teacher: Mr. Hardman
 * Lab Final, Program Final
 * Date Last Modified: Thursday, January 25th, 2018 
 * 
 */
public class CreatureWorld extends World
{
    private String playerOneCreature;
    private String playerTwoCreature;
    private boolean playerOneTurn;
    private boolean playerOneMenusAdded;
    private boolean playerTwoMenusAdded;
    private String playerOneName;
    private String playerTwoName;
    private Menu oneFightMenu;
    private Menu oneSwitchMenu;
    private Menu twoFightMenu;
    private Menu twoSwitchMenu;
    private boolean start;
    private Creature[] playerOneCreatures;
    private Creature[] playerTwoCreatures;
    /**
     * Default constructor for objects of class MyWorld.
     * 
     * @param There are no parameters
     * @return an object of class MyWorld
     */
    public CreatureWorld()
    {  
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(400, 400, 1); 
        playerOneCreatures= new Creature[]{new Mario(this), new Alien(this), new Bowser(this)};
        playerTwoCreatures= new Creature[]{new Sans(this), new Bendy(this), new Freddy(this)};
        prepareCreatures();

        start = true;
        playerOneCreature = "Mario";
        playerTwoCreature = "Sans";
        playerOneMenusAdded = false;
        playerTwoMenusAdded = false;
        Greenfoot.start();
    }
    
    /**
     * prepareCreatures prepares creatures for battle by providing images of the creatures
     * 
     * @param There are no paramiters
     * @return Nothing is returned
     */
    private void prepareCreatures()
    {
        for( int i = 0; i < playerOneCreatures.length; i++ )
        {
            if( i == 0 )
            {
                addObject( playerOneCreatures[i], playerOneCreatures[i].getImage().getWidth()/2, getHeight() - playerOneCreatures[i].getImage().getHeight()/2);
            }
            else
            {
                playerOneCreatures[i].getImage().setTransparency(0);
                addObject( playerOneCreatures[i], 0, getHeight() - playerOneCreatures[i].getImage().getHeight()/2 );
            }
        }
        for( int j = 0; j < playerTwoCreatures.length; j++ )
        {
            if( j == 0 )
            {
                addObject( playerTwoCreatures[j], getWidth()-playerTwoCreatures[j].getImage().getWidth()/2, playerTwoCreatures[j].getImage().getHeight()/2);
            }
            else
            {
                playerTwoCreatures[j].getImage().setTransparency(0);
                addObject( playerTwoCreatures[j], getWidth(), playerTwoCreatures[j].getImage().getHeight()/2 );
            }
        }
    }
    
    /**
     * getPlayerOne prepares playerone for battle by asking for a name 
     * 
     * @param There are no paramiters
     * @return the current playerone is returned
     */
    public Creature getPlayerOne()
    {
        Creature currentPlayerOne;
        if( playerOneCreature.equalsIgnoreCase("Mario") )
        {
            currentPlayerOne = playerOneCreatures[0];
        }
        else if( playerOneCreature.equalsIgnoreCase("Alien") )
        {
            currentPlayerOne = playerOneCreatures[1];
        }
        else
        {
            currentPlayerOne = playerOneCreatures[2];
        }
        
        return currentPlayerOne;
    }
    
    /**
     * getPlayerTwo prepares playertwo for battle by asking for a name 
     * 
     * @param There are no paramiters
     * @return the current playertwo is returned
     */
    public Creature getPlayerTwo()
    {
        Creature currentPlayerTwo;
        if( playerTwoCreature.equalsIgnoreCase("Sans") )
        {
            currentPlayerTwo = playerTwoCreatures[0];
        }
        else if( playerTwoCreature.equalsIgnoreCase("Bendy") )
        {
            currentPlayerTwo = playerTwoCreatures[1];
        }
        else
        {
            currentPlayerTwo = playerTwoCreatures[2];
        }
        
        return currentPlayerTwo;
    }
    
    /**
     * changePlayerOne allows playerone to change creatures when one is knocked out 
     * 
     * @param The amount of creatures is the paramiter
     * @return Nothing is returned
     */
    public void changePlayerOne( String creature )
    {
        playerOneCreature = creature;
        removeObject( oneFightMenu );
        removeObject( oneSwitchMenu );
        playerOneMenusAdded = false;
    }
    
    /**
     * changePlayerTwo allows playertwo to change creatures when one is knocked out 
     * 
     * @param The amount of creatures is the paramiter
     * @return Nothing is returned
     */
    public void changePlayerTwo( String creature )
    {
        playerTwoCreature = creature;
        removeObject( twoFightMenu );
        removeObject( twoSwitchMenu );
        playerTwoMenusAdded = false;
    }
    
    /**
     * getNewOneCreature gets a new creature when playerone switches creatures 
     * 
     * @param The index is the paramiter
     * @return Player ones creatures is returned
     */
    public Creature getNewOneCreature( int index )
    {
        return playerOneCreatures[index];
    }
    
    /**
     * getNewTwoCreature gets a new creature when playertwo switches creatures 
     * 
     * @param The index is the paramiter
     * @return Player twos creatures is returned
     */
    public Creature getNewTwoCreature( int index )
    {
        return playerTwoCreatures[index];
    }

    /**
     * getTurnNumber gives playerone a turn number
     * 
     * @param There is no paramiter
     * @return Player ones turn is returned
     */
    public boolean getTurnNumber()
    {
        return playerOneTurn;
    }

    /**
     * setTurnNumber sets up playerones turn number
     * 
     * @param The turn is the paramiter
     * @return Nothing is returned
     */
    public void setTurnNumber(boolean turn)
    {
        playerOneTurn = turn;
    }

    /**
     * act will complete actions that the CreatureWorld object should
     * accomplish while the scenario is running
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void act()
    {
        List allObjects=getObjects(null);
        boolean playerOneLose = true;
        boolean playerTwoLose = true;
        
        if( start == true)
        {
            playerOneName = JOptionPane.showInputDialog( "Player One, please enter your name:", null );
            playerTwoName = JOptionPane.showInputDialog( "Player Two, please enter your name:", null );
            
            playerOneTurn = true;
            
            start = false; 
        }
        else if( playerOneTurn == true)
        {
            showText( String.format("%-15s, it is your turn", playerOneName), getWidth() /2, getHeight() /2);
            showText( "", getWidth() /2, getHeight() /2 + 26 );
        }
        else
        {
            showText( String.format("%-15s, it is your turn", playerTwoName), getWidth() /2, getHeight() /2);
            showText( "", getWidth() /2, getHeight() /2 + 26 );
        }

        if( playerOneMenusAdded == false )
        {
            if( playerOneCreature.equalsIgnoreCase("Mario" ))
            {
                oneFightMenu = new Menu( " Fight ", " Mario Stomp \n Fireball ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                oneSwitchMenu = new Menu(" Switch ", " Alien \n Bowser ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            else if( playerOneCreature.equalsIgnoreCase("Alien" ))
            {
                oneFightMenu = new Menu( " Fight ", " Head Bite \n Acid Blood ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                oneSwitchMenu = new Menu(" Switch ", " Mario \n Bowser ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            else
            {
                oneFightMenu = new Menu( " Fight ", " Punch \n Fire Breath ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                oneSwitchMenu = new Menu(" Switch ", " Mario \n Alien ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            
            addObject( oneFightMenu, 177, getHeight() - 100 );
            addObject( oneSwitchMenu, 241, getHeight() - 100 );

        
            playerOneMenusAdded = true;
        }
        
        if( playerTwoMenusAdded == false )
        {
            if( playerTwoCreature.equalsIgnoreCase("Sans" ))
            {
                twoFightMenu = new Menu( " Fight ", " Gaster Blast \n Blue Attack ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                twoSwitchMenu = new Menu(" Switch ", " Bendy \n Freddy ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            else if( playerTwoCreature.equalsIgnoreCase("Bendy" ))
            {
                twoFightMenu = new Menu( " Fight ", " Tackle \n Ink Pump ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                twoSwitchMenu = new Menu(" Switch ", " Sans \n Freddy ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            else
            {
                twoFightMenu = new Menu( " Fight ", " Jumpscare \n Hat Toss ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new FightCommands());
                twoSwitchMenu = new Menu(" Switch ", " Sans \n Bendy ", 24, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, new SwitchCommands());
            }
            
            addObject( twoFightMenu, 135, 75 );
            addObject( twoSwitchMenu, 199, 75 );

             
            playerTwoMenusAdded = true;
        }
        
        for( int i = 0; playerOneLose == true && i < playerOneCreatures.length; i++ )
        {
            if( playerOneCreatures[i].getHealthBar().getCurrent()>=0)
            {
                playerOneLose = false;
            }
        }
        for( int i = 0; playerTwoLose == true && i < playerTwoCreatures.length; i++ )
        {
            if( playerTwoCreatures[i].getHealthBar().getCurrent()>=0)
            {
                playerTwoLose = false;
            }
        }

        if(playerOneLose == true)
        {
            removeObjects(allObjects);
            showText("playerTwo wins...",getWidth()/2,getHeight()/2);
            Greenfoot.stop();
        }

        if(playerTwoLose == true)
        {
            removeObjects(allObjects);
            showText("you win!...",getWidth()/2,getHeight()/2);
            Greenfoot.stop();
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     * 
     * @param There is no paramiters
     * @return Nothing is returned
     */
    private void prepare()
    {
        
    }
}
