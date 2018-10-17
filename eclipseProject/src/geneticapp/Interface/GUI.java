package geneticapp.Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import geneticapp.GeneticAlgo;
import geneticapp.SVGGenerator;
import geneticapp.SettingsManager;

public class GUI extends JFrame {
	private final SettingsView settingsWindow;
	private final GeneticAlgo app;
	private final NumberFormat formatter;
	private boolean running;
	private final SVGGenerator svgGenerator;
	private final FileFilter imageFilter;
	// Variables declaration - do not modify
	private JMenuItem Exit;
	private JMenu File;
	private JMenuItem Open;
	private JMenu Settings;
	private JLabel benefitialMutationsLabel1;
	private JMenuItem export;
	private JFileChooser fileChooser;
	private JLabel fitnessLabel;
	private JPanel generatedImagePanel;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JMenuBar jMenuBar1;
	private JRadioButton mutateHard;
	private JRadioButton mutateMedium;
	private JRadioButton mutateSoft;
	private ButtonGroup mutationGroup;
	private JLabel mutationsLabel1;
	private JButton pauseStart;
	private JPanel sidePanel;
	private JPanel sourceImagePanel;
	private JMenuItem viewSettings;

	// End of variables declaration
	public GUI(final GeneticAlgo app) {
		this.initComponents();
		this.app = app;
		this.setSize(915, 520);
		this.setLocationRelativeTo(null);
		this.settingsWindow = new SettingsView(app, this);
		this.formatter = new DecimalFormat("#0.00");
		this.svgGenerator = new SVGGenerator();
		this.imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
		this.fileChooser.setDialogTitle("If pic is larger than 200*200 px  it is automatically downsized");
	}

	private void ExitActionPerformed(final ActionEvent evt) {
		System.exit(0);
	}

	private void exportActionPerformed(final ActionEvent evt) {
		final int returnVal = this.fileChooser.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			final String s = this.fileChooser.getSelectedFile().getAbsolutePath() + ".svg";
			try {
				final OutputStream output = new FileOutputStream(s);
				this.svgGenerator.generateSVG((CanvasPanel) this.generatedImagePanel, output);
			} catch (final FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}
	}

	private void initComponents() {
		this.mutationGroup = new ButtonGroup();
		this.fileChooser = new JFileChooser();
		this.jLabel1 = new JLabel();
		this.jLabel2 = new JLabel();
		this.sourceImagePanel = new ImagePanel();
		this.generatedImagePanel = new CanvasPanel();
		this.pauseStart = new JButton();
		this.mutateHard = new JRadioButton();
		this.mutateMedium = new JRadioButton();
		this.mutateSoft = new JRadioButton();
		this.sidePanel = new JPanel();
		this.fitnessLabel = new JLabel();
		this.mutationsLabel1 = new JLabel();
		this.benefitialMutationsLabel1 = new JLabel();
		this.jMenuBar1 = new JMenuBar();
		this.File = new JMenu();
		this.Open = new JMenuItem();
		this.export = new JMenuItem();
		this.Exit = new JMenuItem();
		this.Settings = new JMenu();
		this.viewSettings = new JMenuItem();
		this.fileChooser.setDialogTitle("This is my open dialog");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("EvoLisa");
		this.jLabel1.setFont(new Font("Rod", 0, 12));
		this.jLabel1.setText("Source image");
		this.jLabel2.setFont(new Font("Rod", 0, 12));
		this.jLabel2.setText("Generated image");
		this.sourceImagePanel.setBorder(BorderFactory.createEtchedBorder());
		this.sourceImagePanel.setPreferredSize(new Dimension(200, 200));
		final GroupLayout sourceImagePanelLayout = new GroupLayout(this.sourceImagePanel);
		this.sourceImagePanel.setLayout(sourceImagePanelLayout);
		sourceImagePanelLayout.setHorizontalGroup(sourceImagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 196, Short.MAX_VALUE));
		sourceImagePanelLayout.setVerticalGroup(sourceImagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 196, Short.MAX_VALUE));
		this.generatedImagePanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		this.generatedImagePanel.setPreferredSize(new Dimension(400, 400));
		final GroupLayout generatedImagePanelLayout = new GroupLayout(this.generatedImagePanel);
		this.generatedImagePanel.setLayout(generatedImagePanelLayout);
		generatedImagePanelLayout.setHorizontalGroup(generatedImagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 398, Short.MAX_VALUE));
		generatedImagePanelLayout.setVerticalGroup(generatedImagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 410, Short.MAX_VALUE));
		this.pauseStart.setFont(new Font("Rod", 0, 18));
		this.pauseStart.setText("Start/Pause");
		this.pauseStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				GUI.this.pauseStartActionPerformed(evt);
			}
		});
		this.mutationGroup.add(this.mutateHard);
		this.mutateHard.setFont(new Font("Rod", 0, 18));
		this.mutateHard.setText("Mutate Hard");
		this.mutateHard.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				GUI.this.mutateHardActionPerformed(evt);
			}
		});
		this.mutationGroup.add(this.mutateMedium);
		this.mutateMedium.setFont(new Font("Rod", 0, 18));
		this.mutateMedium.setSelected(true);
		this.mutateMedium.setText("Mutate Medium");
		this.mutateMedium.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				GUI.this.mutateMediumActionPerformed(evt);
			}
		});
		this.mutationGroup.add(this.mutateSoft);
		this.mutateSoft.setFont(new Font("Rod", 0, 18));
		this.mutateSoft.setText("Mutate Soft");
		this.mutateSoft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				GUI.this.mutateSoftActionPerformed(evt);
			}
		});
		this.sidePanel.setBorder(BorderFactory.createEtchedBorder());
		this.fitnessLabel.setFont(new Font("Rod", 0, 16));
		this.mutationsLabel1.setFont(new Font("Rod", 0, 16));
		this.benefitialMutationsLabel1.setFont(new Font("Rod", 0, 16));
		final GroupLayout sidePanelLayout = new GroupLayout(this.sidePanel);
		this.sidePanel.setLayout(sidePanelLayout);
		sidePanelLayout.setHorizontalGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING,
				sidePanelLayout.createSequentialGroup().addContainerGap()
						.addGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.fitnessLabel, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
								.addComponent(this.benefitialMutationsLabel1, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(this.mutationsLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(36, 36, 36)));
		sidePanelLayout.setVerticalGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(sidePanelLayout.createSequentialGroup().addComponent(this.mutationsLabel1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.benefitialMutationsLabel1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(this.fitnessLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		this.File.setText("File");
		this.Open.setText(" Open");
		this.Open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				GUI.this.OpenActionPerformed(evt);
			}
		});
		this.File.add(this.Open);
		this.export.setText("ExportSVG");
		this.export.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				GUI.this.exportActionPerformed(evt);
			}
		});
		this.File.add(this.export);
		this.Exit.setText("Exit");
		this.Exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				GUI.this.ExitActionPerformed(evt);
			}
		});
		this.File.add(this.Exit);
		this.jMenuBar1.add(this.File);
		this.Settings.setText("Settings");
		this.viewSettings.setText("View");
		this.viewSettings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				GUI.this.viewSettingsActionPerformed(evt);
			}
		});
		this.Settings.add(this.viewSettings);
		this.jMenuBar1.add(this.Settings);
		this.setJMenuBar(this.jMenuBar1);
		final GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
								.addComponent(this.sourceImagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(this.pauseStart, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE).addComponent(this.mutateSoft)
								.addComponent(this.mutateHard, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE).addComponent(this.mutateMedium))
						.addGap(28, 28, 28)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addComponent(this.jLabel2, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE))
								.addGroup(layout.createSequentialGroup().addComponent(this.generatedImagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(28, 28, 28)
										.addComponent(this.sidePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE).addComponent(this.jLabel2))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.sidePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(layout.createSequentialGroup().addComponent(this.sourceImagePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)
										.addComponent(this.mutateHard).addGap(18, 18, 18).addComponent(this.mutateMedium).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
										.addComponent(this.mutateSoft).addGap(18, 18, 18).addComponent(this.pauseStart, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
								.addComponent(this.generatedImagePanel, GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE))
						.addContainerGap(47, Short.MAX_VALUE)));
	}

	BufferedImage loadImage(final File f) {
		BufferedImage in = null;
		try {
			in = ImageIO.read(f);
		} catch (final IOException ex) {
			System.err.println("File problem");
		}
		return in;
	}

	private void mutateHardActionPerformed(final ActionEvent evt) {
		this.app.getConfig().setMutationStratagy(SettingsManager.HARD);
	}

	private void mutateMediumActionPerformed(final ActionEvent evt) {
		this.app.getConfig().setMutationStratagy(SettingsManager.MEDIUM);
	}

	private void mutateSoftActionPerformed(final ActionEvent evt) {
		this.app.getConfig().setMutationStratagy(SettingsManager.SOFT);
	}

	private void OpenActionPerformed(final ActionEvent evt) {
		this.fileChooser.setFileFilter(this.imageFilter);
		final int returnVal = this.fileChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			final File file = this.fileChooser.getSelectedFile();
			// if the selected image is larger than 200 * 200 px, it is downsized to 200 * 200
			BufferedImage img = this.loadImage(file);
			if (img.getWidth() > 200 || img.getHeight() > 200) {
				img = this.resizeImage(img, 200, 200);
				this.app.getFitnessCalc().setSourceImage(img);
			} else
				this.app.getFitnessCalc().setSourceImage(this.loadImage(file));
		}
		// set the image to the sourceImage panel of the GUI
		final ImagePanel p = (ImagePanel) this.sourceImagePanel;
		p.setImage(this.app.getFitnessCalc().getSourceImage());
		p.repaint();
		if (this.running) {
			this.app.reset();
			this.app.start();
		}
	}

	private void pauseStartActionPerformed(final ActionEvent evt) {
		if (this.app.getFitnessCalc().getSourceImage() != null)
			if (!this.running) {
				this.app.start();
				this.running = true;
			} else if (!this.app.isPaused())
				this.app.setPaused(true);
			else
				this.app.setPaused(false);
	}

	BufferedImage resizeImage(final BufferedImage original, final int newWidth, final int newHeight) {
		final BufferedImage resized = new BufferedImage(newWidth, newHeight, original.getType());
		final Graphics2D g = resized.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g.drawImage(original, 0, 0, newWidth, newHeight, 0, 0, original.getWidth(), original.getHeight(), null);
		g.dispose();
		return resized;
	}

	public void updateGeneratedImage() {
		final CanvasPanel diaplayPanel = (CanvasPanel) this.generatedImagePanel;
		diaplayPanel.setCurrentDNA(this.app.getCurrentDNAStr());
		diaplayPanel.repaint();
	}

	public void updateStats() {
		this.mutationsLabel1.setText("Mutations: " + String.valueOf(this.app.getMutations()));
		this.benefitialMutationsLabel1.setText("Benefitial: " + String.valueOf(this.app.getBenefitialMutations()));
		this.fitnessLabel.setText(String.valueOf("Fitness: " + this.formatter.format(this.app.getFitnessPercent())) + " %");
	}

	private void viewSettingsActionPerformed(final ActionEvent evt) {
		this.settingsWindow.setLocationRelativeTo(this);
		this.settingsWindow.setVisible(true);
	}
}
