package geneticapp.Interface;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import geneticapp.DNAString;

/*
 * Canvas class for painting/rendering with Graphics2D
 */
public class CanvasPanel extends JPanel {
	DNAString currentDNA;

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		if (this.currentDNA != null)
			this.renderDNAString(this.currentDNA, g);
	}

	private void renderDNAString(final DNAString dna, final Graphics gfx) {
		gfx.setColor(Color.WHITE);
		gfx.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (int i = 0; i < dna.getGenes().size(); i++)
			dna.getGenes().get(i).paintGeneEnlarged(2, gfx);
	}

	public void setCurrentDNA(final DNAString currentDNA) {
		this.currentDNA = currentDNA;
	}
}
