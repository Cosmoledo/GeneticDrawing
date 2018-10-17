package geneticapp.Interface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/*
 * Class for drawing a BufferedImage to a JPanel
 */
public class ImagePanel extends JPanel {
	private BufferedImage image;

	public ImagePanel() {
	}

	public ImagePanel(final BufferedImage image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.image, 0, 0, this);
	}

	public void setImage(final BufferedImage image) {
		this.image = image;
	}
}
