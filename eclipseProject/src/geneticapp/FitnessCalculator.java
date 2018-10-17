package geneticapp;

import java.awt.Color;
import java.awt.image.BufferedImage;

/*
 * This class calculates the "fitness" or suitability of a particular image
 */
public class FitnessCalculator {
	private BufferedImage sourceImage;

	public FitnessCalculator() {
	}

	public FitnessCalculator(final BufferedImage sourceImage) {
		this.sourceImage = sourceImage;
	}

	// The fitness function is a pixel by pixel comparison where the fitness for
	// each pixel is summed and compared to the parent.
	protected double getFitness(final BufferedImage img) {
		double fitness = 0;
		Color c1, c2;
		for (int x = 0; x < this.sourceImage.getWidth(); x++)
			for (int y = 0; y < this.sourceImage.getHeight(); y++) {
				c1 = new Color(this.sourceImage.getRGB(x, y));
				c2 = new Color(img.getRGB(x, y));
				fitness += Math.abs(c1.getRed() - c2.getRed());
				fitness += Math.abs(c1.getGreen() - c2.getGreen());
				fitness += Math.abs(c1.getBlue() - c2.getBlue());
			}
		return fitness;
	}

	public BufferedImage getSourceImage() {
		return this.sourceImage;
	}

	public void setSourceImage(final BufferedImage sourceImage) {
		this.sourceImage = sourceImage;
	}
}
