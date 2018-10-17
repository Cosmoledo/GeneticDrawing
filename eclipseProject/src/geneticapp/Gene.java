package geneticapp;

import static geneticapp.GeneticAlgo.randInt;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

/*
 * A gene encapsulates a polygon
 *
 * Object Struture: index: 0 1 2 3 4 5 6 ... 7 8 9 ... value: R G B A x1 x2
 * x3... y1 y2 y3...
 */
public class Gene {
	private final String[] values;
	private final int numPointsInPolygon;

	public Gene(final int pointsInPolygon, final int initialColor) {
		this.numPointsInPolygon = pointsInPolygon;
		String str = "";
		switch (initialColor) {
		case SettingsManager.BLACK:
			str = "" + 0 + " " + 0 + " " + 0 + " " + "0.5" + " ";
			break;
		case SettingsManager.WHITE:
			str = "" + 255 + " " + 255 + " " + 255 + " " + "0.5" + " ";
			break;
		case SettingsManager.COLOR:
			str = "" + randInt(0, 255) + " " + randInt(0, 255) + " " + randInt(0, 255) + " " + "0.5" + " ";
			break;
		}
		for (int i = 0; i < pointsInPolygon * 2; i++)
			str += randInt(0, 200) + " ";
		str = str.trim();
		this.values = str.split("[ ]");
	}

	public Gene(final String g) {
		this.values = g.split("[ ]");
		this.numPointsInPolygon = (this.values.length - 4) / 2;
	}

	/*
	 * Mutators:
	 */
	// Hard mutations change a color and transparency of one polygon to a completely random value
	// together with changing one coordinate to a completely random value.
	public float getAlpha() {
		return Float.valueOf(this.values[3]);
	}

	// Medium mutations modify exactly one parameter (R,G,B,A,X...,Y... ) by a random amount
	// within the allowed range(EX:R/G/B range = 0 -> 255)
	public int getB() {
		return Integer.valueOf(this.values[2]);
	}

	public int getG() {
		return Integer.valueOf(this.values[1]);
	}

	public int getNumPointsInPolygon() {
		return this.numPointsInPolygon;
	}

	// Soft mutations modify exactly one parameter (R, G, B, A, X, Y) by a small amount.
	public int getR() {
		return Integer.valueOf(this.values[0]);
	}

	protected int[] getXCords() {
		final int[] xCords = new int[this.numPointsInPolygon];
		int helper = 0;
		for (int i = 4; i < 4 + this.numPointsInPolygon; i++) {
			xCords[helper] = Integer.valueOf(this.values[i]);
			helper++;
		}
		return xCords;
	}

	protected int[] getYCords() {
		final int[] yCords = new int[this.numPointsInPolygon];
		int helper = 0;
		for (int i = 4 + this.numPointsInPolygon; i < this.values.length; i++) {
			yCords[helper] = Integer.valueOf(this.values[i]);
			helper++;
		}
		return yCords;
	}

	protected void hardMutate() {
		final int colorIndex = randInt(0, 2);
		this.values[colorIndex] = String.valueOf(randInt(0, 255));
		this.randomizeA();
		final int xIndex = randInt(4, 4 + this.numPointsInPolygon - 1);
		this.values[xIndex] = String.valueOf(randInt(0, 200));
		final int matchingY = xIndex + this.numPointsInPolygon;
		this.values[matchingY] = String.valueOf(randInt(0, 200));
	}

	/*
	 * Getters:
	 */
	protected void mediumMutate() {
		final int index = randInt(0, 4 + this.numPointsInPolygon * 2 - 1);
		if (index < 3)
			this.mediumMutateColor(index);
		else if (index == 3)
			this.randomizeA();
		else
			this.mediumMutateCoordinate(index);
	}

	private void mediumMutateColor(final int index) {
		final int newValue = randInt(0, 255);
		this.values[index] = String.valueOf(newValue);
	}

	private void mediumMutateCoordinate(final int index) {
		final int newValue = randInt(0, 200);
		this.values[index] = String.valueOf(newValue);
	}

	/*
	 * Paint Gene Methods:
	 */
	public void paintGene(final Graphics graphics) {
		final Color color = new Color(this.getR(), this.getG(), this.getB());
		final Polygon p = new Polygon(this.getXCords(), this.getYCords(), this.getNumPointsInPolygon());
		final Graphics2D g2d = (Graphics2D) graphics;
		g2d.setColor(color);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.getAlpha()));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillPolygon(p);
	}

	// Multiplies the x and y coords of a gene to get a scaled version
	public void paintGeneEnlarged(final int scale, final Graphics graphics) {
		final int[] newX = new int[this.getXCords().length];
		for (int i = 0; i < this.getXCords().length; i++)
			newX[i] = this.getXCords()[i] * scale;
		final int[] newY = new int[this.getXCords().length];
		for (int i = 0; i < this.getYCords().length; i++)
			newY[i] = this.getYCords()[i] * scale;
		final Color color = new Color(this.getR(), this.getG(), this.getB());
		final Polygon p = new Polygon(newX, newY, this.getNumPointsInPolygon());
		final Graphics2D g2d = (Graphics2D) graphics;
		g2d.setColor(color);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.getAlpha()));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillPolygon(p);
	}

	public void randomizeA() {
		final float newA = GeneticAlgo.randFloat();
		this.setA(newA);
	}

	private void setA(final float value) {
		this.values[3] = String.valueOf(value);
	}

	/*
	 * Setters:
	 */
	protected void softMutate() {
		final int index = randInt(0, 4 + this.numPointsInPolygon * 2 - 1);
		if (index < 3)
			this.softMutateColor(index);
		else if (index == 3)
			this.randomizeA();
		else
			this.softMutateCoordinate(index);
	}

	private void softMutateColor(final int index) {
		final int currentValue = Integer.valueOf(this.values[index]);
		int mutateAmoutnt = 0;
		while (true) {
			mutateAmoutnt = randInt(-25, 25);
			if (currentValue + mutateAmoutnt >= 0 && currentValue + mutateAmoutnt <= 255)
				break;
		}
		this.values[index] = String.valueOf(currentValue + mutateAmoutnt);
	}

	private void softMutateCoordinate(final int index) {
		final int currentValue = Integer.valueOf(this.values[index]);
		int mutateAmount = 0;
		while (true) {
			mutateAmount = randInt(-20, 20);
			if (currentValue + mutateAmount >= 0 && currentValue + mutateAmount <= 200)
				break;
		}
		this.values[index] = String.valueOf(currentValue + mutateAmount);
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < this.values.length; i++)
			str += this.values[i] + " ";
		return str.trim();
	}
}
