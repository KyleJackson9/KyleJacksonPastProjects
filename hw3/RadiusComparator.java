import java.util.Comparator;

/**
 * MassComparator.java
 */

public class RadiusComparator implements Comparator<Planet> {

    public RadiusComparator() {
    	super();
    }

    /** Returns the difference in mass as an int.
     *  Round after calculating the difference. */
    public int compare(Planet planet1, Planet planet2) {
        return (int) Math.abs(planet1.radius() -planet2.radius());
    }
}