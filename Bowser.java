import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;

/**
 * Write a description of class Bowser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bowser extends Creature
{   public Bowser(World w)
    {
        super(720,true,"Fire");
        getImage().scale( 150, 100);
        w.addObject(getHealthBar(),300,w.getHeight()-50);
        getHealthBar().getImage().setTransparency(0);
    }
    
    /**
     * Act - do whatever the Bowser wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        CreatureWorld playerWorld=(CreatureWorld) getWorld();
        if(getHealthBar().getCurrent()<=0)
        {
            getWorld().showText("Bowser has fainted...",getWorld().getWidth()/2,getWorld().getHeight()/2+26);
            Greenfoot.delay(30);
            
            if(playerWorld.getNewOneCreature(0).getHealthBar().getCurrent() > 0)
            {
                switchCreature(0);
                playerWorld.setTurnNumber(true);
                getWorld().showText("", getWorld().getWidth()/2,getWorld().getHeight()/2+26);
                getWorld().removeObject(this);
            }
            else if (playerWorld.getNewOneCreature(1).getHealthBar().getCurrent() > 0)
            {
                switchCreature(1);
                playerWorld.setTurnNumber(true);
                getWorld().showText("", getWorld().getWidth()/2,getWorld().getHeight()/2+26);
                getWorld().removeObject(this);
            }
            
        }
    }
    
    /**
     * attack allows this creature to attack
     * 
     * @param idx is the paramiter
     * @return Nothing is returned
     */
     public void attack( int idx )
    {
        CreatureWorld world = (CreatureWorld)getWorld();
        Creature enemy = world.getPlayerTwo();
        String enemyType = enemy.getType();
        attackAnimation();
        
        if( idx == 0 )
        {
            enemy.getHealthBar().add( -30 );
            
        }
        else
        {
            if( enemyType.equalsIgnoreCase("Magic") )
            {
                enemy.getHealthBar().add( -60/2 );
                getWorld().showText("It's not very effective...",getWorld().getWidth()/2,getWorld().getHeight()/2+26);
                Greenfoot.delay(30);
            }
            else if(enemyType.equalsIgnoreCase("Tech"))
            {
                enemy.getHealthBar().add( -60*2 );
                getWorld().showText("It's super effective!",getWorld().getWidth()/2,getWorld().getHeight()/2+26);
                Greenfoot.delay(30);
            }
            else if(enemyType.equalsIgnoreCase("Water"))
            {
                enemy.getHealthBar().add( -60/2 );
                getWorld().showText("It's not very effective...",getWorld().getWidth()/2,getWorld().getHeight()/2+26);
                Greenfoot.delay(30);
            }
            else
            {
                enemy.getHealthBar().add( -60 );
            }
        }
        world.setTurnNumber(false);
    }
    
    /**
     * attackAnimation gives this creature an attack animation
     * 
     * @param There is no paramiter
     * @return Nothing is returned
     */
    private void attackAnimation()
    {
        int originalX = getX();
        int originalY = getY();
        
        for( int i = 0; i < 15; i++ )
        {
            setLocation( getX()+1, getY()-2);
            Greenfoot.delay(1);
        }
        setLocation( originalX, originalY );
    }
    
     /**
     * switchCreature switches the current creature for another
     * 
     * @param idx is the paramiter
     * @return Nothing is returned
     */
    public void switchCreature( int idx )
    {
        CreatureWorld playerWorld=(CreatureWorld) getWorld();
        Creature switchCreature;
        
        if( idx == 0)
        {
            switchCreature = playerWorld.getNewOneCreature(0);
        }
        else
        {
            switchCreature = playerWorld.getNewOneCreature(1);
        }
        
        if(switchCreature.getHealthBar().getCurrent()<=0)
        {
            JOptionPane.showMessageDialog( null, "This creature has fainted! Please select a different creature." );
        }
        else
        {
            while( getX() >0)
            {
                setLocation( getX()-5, getY() );
                Greenfoot.delay(2);
            }
            getImage().setTransparency(0);
            getHealthBar().getImage().setTransparency(0);
            
            if( idx == 0 )
            {
                playerWorld.changePlayerOne("Mario");
            }
            else
            {
                playerWorld.changePlayerOne("Alien");
            }
            switchCreature.switchedIn();
            playerWorld.setTurnNumber(false);
        }
    }
    
     /**
     * switchedIn switches in a new creature
     * 
     * @param There is no paramiter
     * @return Nothing is returned
     */
    public void switchedIn()
    {
        getImage().setTransparency(255);
        getHealthBar().getImage().setTransparency(255);
        
        while( getX() <75)
        {
            setLocation( getX()+5, getY() );
            Greenfoot.delay(2);
        }
    }
    
}
