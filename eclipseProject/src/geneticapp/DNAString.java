package geneticapp;

import static geneticapp.GeneticAlgo.randInt;

import java.util.ArrayList;
import java.util.List;

/*
 * A DNA String represents a solution, it encapsulates a number of
 * genes/polygons The toString() returns the DNAstring in the following format:
 * NUMBER_OF_VERTICES NUMBER_OF_POLYGONS R G B ALPHA X0 X1 ... Y0 Y1 ... R G B
 * ALPHA X0 X1 ... Y0 Y1 ... ...
 */
public class DNAString {
	private final List<Gene> genes;
	private final int numberOfPolygons;
	private final int numberOfPointsInPolyon;

	public DNAString(final int numberOfPolygons, final int pointsInPolygon, final int initialColor) {
		this.numberOfPolygons = numberOfPolygons;
		this.numberOfPointsInPolyon = pointsInPolygon;
		this.genes = new ArrayList<>();
		for (int i = 0; i < numberOfPolygons; i++)
			this.genes.add(new Gene(pointsInPolygon, initialColor));
	}

	public DNAString(final String dna) {
		final String[] values = dna.split("\\r?\\n");
		this.numberOfPolygons = Integer.valueOf(values[0]);
		this.numberOfPointsInPolyon = Integer.valueOf(values[1]);
		this.genes = new ArrayList<>();
		for (int i = 2; i < values.length; i++)
			this.genes.add(new Gene(values[i]));
	}

	public List<Gene> getGenes() {
		return this.genes;
	}

	void mutateHard() {
		final Gene selectedGene = this.genes.get(randInt(0, this.genes.size() - 1));
		selectedGene.hardMutate();
	}

	void mutateMedium() {
		final Gene selectedGene = this.genes.get(randInt(0, this.genes.size() - 1));
		selectedGene.mediumMutate();
	}

	void mutateSoft() {
		final Gene selectedGene = this.genes.get(randInt(0, this.genes.size() - 1));
		selectedGene.softMutate();
	}

	@Override
	public String toString() {
		String str = "" + this.numberOfPolygons + "\n" + this.numberOfPointsInPolyon;
		for (int i = 0; i < this.genes.size(); i++)
			str += "\n" + this.genes.get(i).toString();
		return str;
	}
}
