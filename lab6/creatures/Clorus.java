package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

/** An implementation of a motile angry photosynthesizer.
 *  @author Kyle Jackson
 */
public class Clorus extends Creature {
    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;
    //private double energy;

    /** creates plip with energy equal to E. */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /** creates a plip with energy equal to 1. */
    public Clorus() {
        this(1);
    }

    public Color color() {
        return color(r, g, b);
    }

    /** Do nothing with C, Plips are pacifists. */
    public void attack(Creature c) {
        energy += c.energy();
    }


    public void move() {
        energy -= .03;
    }


    /** Plips gain 0.2 energy when staying due to photosynthesis. */
    public void stay() {
        energy -=.01;
    }


    public Clorus replicate() {
        energy = energy/2;
        return new Clorus(energy);
    }


    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plip = getNeighborsOfType(neighbors, "plip");
        if (energy == 0){
            return new Action(Action.ActionType.DIE);
        }
         else if (empties.size() == 0){
        return new Action(Action.ActionType.STAY);
        }   else if (energy >= 1){
            
            Direction moveDir = empties.get((int) Math.random()*empties.size());
            return new Action(Action.ActionType.REPLICATE, moveDir);
        } else if (plip.size() > 0 && Math.random() > 0.5){
            int x = (int)Math.random()*plip.size();
            Direction moveDir = plip.get(x);
            return new Action(Action.ActionType.ATTACK, moveDir);
        } else {
            Direction moveDir = empties.get((int) Math.random()*empties.size());
            return new Action(Action.ActionType.MOVE, moveDir);
        }
    }
}
