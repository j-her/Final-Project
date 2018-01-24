import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;

/**
 * Write a description of class Freddy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Freddy extends Creature
{
      public Freddy(World w)
    {
        super(800,false,"Tech");
        getImage().scale( 150, 100);
        w.addObject(getHealthBar(),100,25);
        getHealthBar().getImage().setTransparency(0);
    }
    
    /**
     * Act - do whatever the Freddy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
         CreatureWorld playerWorld=(CreatureWorld) getWorld();
        if(getHealthBar().getCurrent()<=0)
         {
           getWorld().showText("Freddy has fainted...",getWorld().getWidth()/2,getWorld().getHeight()/2+26);
           Greenfoot.delay(30);
           
           if(playerWorld.getNewTwoCreature(0).getHealthBar().getCurrent() > 0)
           {
                switchCreature(0);
                playerWorld.setTurnNumber(false);
                getWorld().showText("", getWorld().getWidth()/2,getWorld().getHeight()/2+26);
                getWorld().removeObject(this);
           }
           else if (playerWorld.getNewTwoCreature(1).getHealthBar().getCurrent() > 0)
           {
                switchCreature(1);
                playerWorld.setTurnNumber(false);
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
        CreatureWorld world=(CreatureWorld) getWorld();
        Creature enemy = world.getPlayerOne();
        String enemyType = enemy.getType();
        attackAnimation();
       
        
       if( idx == 0 )
       {
            enemy.getHealthBar().add( -30 );
           
       }
       else
       {
            if( enemyType.equalsIgnoreCase("Poison") )
            {
                enemy.getHealthBar().add( -55*2 );
                getWorld().showText("It's super effective!",getWorld().getWidth()/2,getWorld().getHeight()/2+26);
                Greenfoot.delay(30);
            }
            else if( enemyType.equalsIgnoreCase("Fire") )
            {
                enemy.getHealthBar().add( -55/2 );
                getWorld().showText("It's not very effective...",getWorld().getWidth()/2,getWorld().getHeight()/2+26);
                Greenfoot.delay(30);
            }
            else
            {
                enemy.getHealthBar().add( -55 );
            }
       }
       world.setTurnNumber(true);
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
            setLocation( getX()-1, getY()+2);
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
            switchCreature = playerWorld.getNewTwoCreature(0);
        }
        else
        {
            switchCreature = playerWorld.getNewTwoCreature(1);
        }
        
        if(switchCreature.getHealthBar().getCurrent()<=0)
        {
            JOptionPane.showMessageDialog( null, "This creature has fainted! Please select a different creature." );
        }
        else
        {
            while( getX() < getWorld().getWidth()-1)
            {
                setLocation( getX()+5, getY() );
                Greenfoot.delay(2);
            }
            getImage().setTransparency(0);
            getHealthBar().getImage().setTransparency(0);
            
            if( idx == 0 )
            {
                playerWorld.changePlayerTwo("Sans");
            }
            else
            {
                playerWorld.changePlayerTwo("Bendy");
            }
            switchCreature.switchedIn();
            playerWorld.setTurnNumber(true);
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
        
        while( getX() >325)
        {
            setLocation( getX()-5, getY() );
            Greenfoot.delay(2);
        }
    }
}
