package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

/** An implementation of a motile pacifist photosynthesizer.
 *  @author Josh Hug
 */
public class Plip extends Creature {

    /** red color. */
    private int r = 99;
    /** green color. */
    private int g;
    /** blue color. */
    private int b = 76;

    /** losing units of energy on a MOVE action */
    private static final double MOVE_ENERGY = -0.15;

    /** gaining units of energy on a STAY action. */
    private static final double STAY_ENERGY = 0.2;

    /** max of Energy */
    private static final double MAX_ENERGY = 2.0;

    /** fraction of energy for offspring */
    private static final double repEnergyGiven = 0.5;

    /** fraction of energy to bestow upon offspring. */
    private static final double repEnergyRetained = 0.5;

    /** probability of taking a move when empty space is available and have cloruses in neighbors . */
    private static final double movePropability = 0.5;

    /** creates plip with energy equal to E. */
    public Plip(double e) {
        super("plip");
        g = 63;
        energy = e;
    }

    /** creates a plip with energy equal to 1. */
    public Plip() {
        this(1);
    }

    /** Green value varies with energy linearly in between these two extremes. */
    private int green() {
        return (int) (96 * energy + 63);
    }

    /** Should return a color with red = 99, blue = 76, and green that varies
     *  linearly based on the energy of the Plip. If the plip has zero energy,
     *  it should have a green value of 63. If it has max energy, it should
     *  have a green value of 255. The green value should vary with energy
     *  linearly in between these two extremes. It's not absolutely vital
     *  that you get this exactly correct.
     */
    public Color color() {
        return color(r, green(), b);
    }

    /** Do nothing with C, Plips are pacifists. */
    public void attack(Creature c) {
    }

    /** Plips should lose 0.15 units of energy when moving. If you want to
     *  to avoid the magic number warning, you'll need to make a
     *  private static final variable. This is not required for this lab.
     */

    /** Plips should never have energy greater than 2.
     *  If an action would cause the Plip to have energy greater than 2, then it should be set to 2 instead
     */
    private void standardizeEnergy() {
        energy = Math.min(energy, MAX_ENERGY);
    }

    public void move() {
        energy += MOVE_ENERGY;
        standardizeEnergy();
    }


    /** Plips gain 0.2 energy when staying due to photosynthesis. */
    public void stay() {
        energy += STAY_ENERGY;
        standardizeEnergy();
    }

    /** Plips and their offspring each get 50% of the energy, with none
     *  lost to the process. Now that's efficiency! Returns a baby
     *  Plip.
     */
    public Plip replicate() {
        double babyEnergy = energy * repEnergyGiven;
        energy = energy * repEnergyRetained;
        return new Plip(babyEnergy);
    }

    /** Plips take exactly the following actions based on NEIGHBORS:
     *  1. If no empty adjacent spaces, STAY.
     *  2. Otherwise, if energy >= 1, REPLICATE.
     *  3. Otherwise, if any Cloruses, MOVE with 50% probability.
     *  4. Otherwise, if nothing else, STAY
     *
     *  Returns an object of type Action. See Action.java for the
     *  scoop on how Actions work. See SampleCreature.chooseAction()
     *  for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        if (empties.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        if (energy >= 1) {
            Direction repDir = empties.get(0);
            return new Action(Action.ActionType.REPLICATE, repDir);
        }

        List<Direction> cloruses = getNeighborsOfType(neighbors, "clorus");
        if (!cloruses.isEmpty()) {
            if (HugLifeUtils.random() <= movePropability) {
                Direction moveDir = HugLifeUtils.randomEntry(empties);
                return new Action(Action.ActionType.MOVE, moveDir);
            }
        }

        return new Action(Action.ActionType.STAY);
    }

}
