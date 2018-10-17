package geneticapp;

import java.util.HashMap;
import java.util.Map;

/*
 *  Class for managing all of the apps settings:
 *  Number of polygons, points in each polygon,
 *  how the DNA should be initilized, mutation strategy.
 */
public class SettingsManager {
	public static final int COLOR = 0;
	public static final int WHITE = 1;
	public static final int BLACK = 2;
	public static final int HARD = 3;
	public static final int MEDIUM = 4;
	public static final int SOFT = 5;
	private final Map<String, Integer> config;

	public SettingsManager() {
		this.config = new HashMap<>();
		this.config.put("numOfPolygons", 50);
		this.config.put("pointsInPolygon", 6);
		this.config.put("initilizeDNA", BLACK);
		this.config.put("mutationStrategy", MEDIUM);
	}

	// Setters
	public int getInitialDNA() {
		return this.config.get("initilizeDNA");
	}

	public int getMutationStratagy() {
		return this.config.get("mutationStrategy");
	}

	public int getNumOfPolygons() {
		return this.config.get("numOfPolygons");
	}

	public int getPointsInPolygon() {
		return this.config.get("pointsInPolygon");
	}

	// Getters
	void print() {
		for (final int value : this.config.values())
			System.out.println("value: " + value);
	}

	public void setInitialDNA(final int number) {
		this.config.put("initilizeDNA", number);
	}

	public void setMutationStratagy(final int number) {
		this.config.put("mutationStrategy", number);
	}

	public void setNumOfPolygons(final int number) {
		this.config.put("numOfPolygons", number);
	}

	public void setPointsInPolygon(final int number) {
		this.config.put("pointsInPolygon", number);
	}
}
