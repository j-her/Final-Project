import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Creature here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Creature extends Actor
{
    private HealthBar creatureBar;
    private int healthNumber;
    private boolean playerOneCreature;
    private String type;
    /**
     * Default constructor for objects of the Creature class
     * 
     * @param There are no parameters
     * @return an object of the Creature class
     */
    public Creature()
    {
        //Improper Indentation
        healthNumber=500;
        playerOneCreature=true;
        creatureBar=new HealthBar( healthNumber, healthNumber, 10);
    }

    /**
     * Constructor that allows customization of objects of the Creature class
     * 
     * @param health is the amount of health the Creature object will have
     * @param whichPlayer discusses whether the creature belongs to player 1 or player 2
     * @return an object of the Creature class
     */
    public Creature( int health, boolean isPlayerOne, String creatureType )
    {
        //Improper Indentation
        healthNumber =health;
        playerOneCreature =isPlayerOne;
        type = creatureType;
        creatureBar=new HealthBar( healthNumber, healthNumber, 10);
    }
    
    /**
     * HealthBar getHealthBar gives all creatures a healthbar
     * 
     * @param There is no paramiter
     * @return creature bar is returned
     */
    protected HealthBar getHealthBar()
    {
        return creatureBar;
    }
    
    /**
     * getWhetherPlayerOne decides wether or not it is playerones move
     * 
     * @param There is no paramiter
     * @return playerone creature is returned
     */
    public boolean getWhetherPlayerOne()
    {
        return playerOneCreature;
    }
    
    /**
     * getType gives each creature a type of class
     * 
     * @param There is no paramiter
     * @return type is returned
     */
    public String getType()
    {
        return type;
    }
    
    
    /**
     * attack is the code that is run when the Creature attacks its enemy
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void attack(int idx )
    {
        //empty method that will get overriden in subclasses
    }
    
    public void switchCreature(int idx)
    {
        //empty method that will get overriden in subclasses
    }
    
    public void switchedIn()
    {
        //empty method that will get overriden in subclasses
    }
    
    
    /**
     * act will complete actions that the Creature object should
     * accomplish while the scenario is running
     * 
     * @param There are no parameters
     * @return Nothing is returned
     */
    public void act() 
    {
        //empty method that will get overriden in subclasses
    }   

}
