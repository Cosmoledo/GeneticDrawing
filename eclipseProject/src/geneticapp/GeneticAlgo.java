package geneticapp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import geneticapp.Interface.GUI;

public class GeneticAlgo implements Runnable {
	private static Random randomNumberGenerator;

	static float randFloat() {
		return randomNumberGenerator.nextFloat();
	}

	static int randInt(final int min, final int max) {
		return randomNumberGenerator.nextInt(max - min + 1) + min;
	}

	private DNAString currentDNAStr;
	private BufferedImage currentDNA_Img;
	private GUI gui;
	private final SettingsManager config;
	private final FitnessCalculator fitnessCalc;
	// information for Screen Display
	private double currentFitness;
	private double fitnessPercent;
	private int benefitialMutations;
	private int mutations;
	private boolean paused;

	/*
	 * Helper/Utility Methods
	 */
	public GeneticAlgo() {
		randomNumberGenerator = new Random();
		this.fitnessCalc = new FitnessCalculator();
		this.config = new SettingsManager();
	}

	private void calculateCurrentFitness() {
		this.currentDNA_Img = this.getBufferedImageFromDNA(this.currentDNAStr);
		this.currentFitness = this.fitnessCalc.getFitness(this.currentDNA_Img);
	}

	private DNAString copyDNAString(final DNAString toCopy) {
		return new DNAString(toCopy.toString());
	}

	public void evolve() {
		// 0) Setup a random DNA string
		this.setupRandomDNAString();
		while (this.fitnessPercent < 100.0) {
			if (!this.isPaused()) {
				// 1) Copy the current DNA sequence and mutate it slightly
				final DNAString copy = this.copyDNAString(this.currentDNAStr);
				this.mutate(copy);
				// 2) Use the new DNA to render polygons onto a canvas
				final BufferedImage mutatedImage = this.getBufferedImageFromDNA(copy);
				// 3) Compare the canvas to the source image
				final double mutatedFitness = this.fitnessCalc.getFitness(mutatedImage);
				// 4) If the new painting looks more like the source image
				// than the previous painting did, then overwrite the current DNA with the new
				// DNA
				if (mutatedFitness < this.currentFitness) {
					this.currentDNAStr = copy;
					this.currentFitness = mutatedFitness;
					this.setCurrentDNA_Img(mutatedImage);
					this.benefitialMutations++;
				}
				this.update();
				// 5) repeat from 1
			}
			System.out.print("");
		}
	}

	public int getBenefitialMutations() {
		return this.benefitialMutations;
	}

	private BufferedImage getBufferedImageFromDNA(final DNAString dna) {
		final BufferedImage buff = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
		final Graphics graph = buff.createGraphics();
		graph.setColor(Color.WHITE);
		graph.fillRect(0, 0, 200, 200);
		for (int i = 0; i < dna.getGenes().size(); i++)
			dna.getGenes().get(i).paintGene(graph);
		return buff;
	}

	public SettingsManager getConfig() {
		return this.config;
	}

	public BufferedImage getCurrentDNA_Img() {
		return this.currentDNA_Img;
	}

	public DNAString getCurrentDNAStr() {
		return this.currentDNAStr;
	}

	public FitnessCalculator getFitnessCalc() {
		return this.fitnessCalc;
	}

	public double getFitnessPercent() {
		return this.fitnessPercent;
	}

	public int getMutations() {
		return this.mutations;
	}

	public boolean isPaused() {
		return this.paused;
	}

	/*
	 * Setters
	 */
	private void mutate(final DNAString dna) {
		switch (this.config.getMutationStratagy()) {
		case SettingsManager.HARD:
			dna.mutateHard();
			break;
		case SettingsManager.MEDIUM:
			dna.mutateMedium();
			break;
		case SettingsManager.SOFT:
			dna.mutateSoft();
			break;
		}
	}

	public void reset() {
		this.benefitialMutations = 0;
		this.mutations = 0;
	}

	@Override
	public void run() {
		this.evolve();
	}

	/*
	 * Getters
	 */
	public void setCurrentDNA_Img(final BufferedImage currentDNA_Img) {
		this.currentDNA_Img = currentDNA_Img;
		this.updateGeneratedImage();
	}

	public void setGui(final GUI gui) {
		this.gui = gui;
	}

	public void setPaused(final boolean paused) {
		this.paused = paused;
	}

	private void setupRandomDNAString() {
		this.currentDNAStr = new DNAString(this.config.getNumOfPolygons(), this.config.getPointsInPolygon(), this.config.getInitialDNA());
		this.calculateCurrentFitness();
	}

	public void start() {
		final Thread workerThread = new Thread(this);
		workerThread.start();
	}

	private void update() {
		this.fitnessPercent = 100 * (1 - this.currentFitness / (200 * 200 * 3 * 255));
		this.updateGUI();
		this.mutations++;
	}

	void updateGeneratedImage() {
		this.gui.updateGeneratedImage();
	}

	void updateGUI() {
		this.gui.updateStats();
	}
}
