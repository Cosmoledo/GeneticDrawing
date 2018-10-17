package geneticapp.Interface;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import geneticapp.GeneticAlgo;
import geneticapp.SettingsManager;

public class SettingsView extends JFrame {
	private final GeneticAlgo app;
	// Variables declaration - do not modify
	private JRadioButton black;
	private JRadioButton color;
	private ButtonGroup initilizeDNAColor;
	private JPanel jPanel1;
	private JLabel labelInitilizeDNA;
	private JLabel label_numOfPolygons;
	private JLabel lablePointsInPolygon;
	private JLabel numPolygonsLabel;
	private JLabel pointsInPolygonLabel;
	private JButton pointsMinus1;
	private JButton pointsMinus10;
	private JButton pointsPlus1;
	private JButton pointsPlus10;
	private JButton polyMinus1;
	private JButton polyMinus10;
	private JButton polyPlus1;
	private JButton polyPlus10;
	private JRadioButton white;
	// End of variables declaration

	public SettingsView(final GeneticAlgo app, final JFrame parent) {
		this.initComponents();
		this.setSize(500, 240);
		this.setLocationRelativeTo(parent);
		this.app = app;
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
	}

	private void blackActionPerformed(final ActionEvent evt) {
		this.app.getConfig().setInitialDNA(SettingsManager.BLACK);
	}

	private void colorActionPerformed(final ActionEvent evt) {
		this.app.getConfig().setInitialDNA(SettingsManager.COLOR);
	}

	private void initComponents() {
		this.initilizeDNAColor = new ButtonGroup();
		this.jPanel1 = new JPanel();
		this.lablePointsInPolygon = new JLabel();
		this.numPolygonsLabel = new JLabel();
		this.polyPlus1 = new JButton();
		this.polyMinus1 = new JButton();
		this.polyPlus10 = new JButton();
		this.polyMinus10 = new JButton();
		this.label_numOfPolygons = new JLabel();
		this.pointsInPolygonLabel = new JLabel();
		this.pointsPlus1 = new JButton();
		this.pointsMinus1 = new JButton();
		this.pointsPlus10 = new JButton();
		this.pointsMinus10 = new JButton();
		this.labelInitilizeDNA = new JLabel();
		this.white = new JRadioButton();
		this.black = new JRadioButton();
		this.color = new JRadioButton();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Settings");
		this.lablePointsInPolygon.setFont(new Font("Rod", 0, 14));
		this.lablePointsInPolygon.setText("Points in Polygon:");
		this.numPolygonsLabel.setFont(new Font("Rod", 0, 18));
		this.numPolygonsLabel.setText("50");
		this.polyPlus1.setText("+1");
		this.polyPlus1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				SettingsView.this.polyPlus1ActionPerformed(evt);
			}
		});
		this.polyMinus1.setText("-1");
		this.polyMinus1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				SettingsView.this.polyMinus1ActionPerformed(evt);
			}
		});
		this.polyPlus10.setText("+10");
		this.polyPlus10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				SettingsView.this.polyPlus10ActionPerformed(evt);
			}
		});
		this.polyMinus10.setText("-10");
		this.polyMinus10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				SettingsView.this.polyMinus10ActionPerformed(evt);
			}
		});
		this.label_numOfPolygons.setFont(new Font("Rod", 0, 14));
		this.label_numOfPolygons.setText("Number of Polygons:");
		this.pointsInPolygonLabel.setFont(new Font("Rod", 0, 18));
		this.pointsInPolygonLabel.setText("3");
		this.pointsPlus1.setText("+1");
		this.pointsPlus1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				SettingsView.this.pointsPlus1ActionPerformed(evt);
			}
		});
		this.pointsMinus1.setText("-1");
		this.pointsMinus1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				SettingsView.this.pointsMinus1ActionPerformed(evt);
			}
		});
		this.pointsPlus10.setText("+10");
		this.pointsPlus10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				SettingsView.this.pointsPlus10ActionPerformed(evt);
			}
		});
		this.pointsMinus10.setText("-10");
		this.pointsMinus10.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				SettingsView.this.pointsMinus10ActionPerformed(evt);
			}
		});
		this.labelInitilizeDNA.setFont(new Font("Rod", 0, 14));
		this.labelInitilizeDNA.setText("Initilize DNA:");
		this.initilizeDNAColor.add(this.white);
		this.white.setFont(new Font("Rod", 0, 14));
		this.white.setText("White");
		this.white.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				SettingsView.this.whiteActionPerformed(evt);
			}
		});
		this.initilizeDNAColor.add(this.black);
		this.black.setFont(new Font("Rod", 0, 14));
		this.black.setSelected(true);
		this.black.setText("Black");
		this.black.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				SettingsView.this.blackActionPerformed(evt);
			}
		});
		this.initilizeDNAColor.add(this.color);
		this.color.setFont(new Font("Rod", 0, 14));
		this.color.setText("Color");
		this.color.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent evt) {
				SettingsView.this.colorActionPerformed(evt);
			}
		});
		final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
		this.jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(jPanel1Layout.createSequentialGroup().addGap(14, 14, 14).addComponent(this.label_numOfPolygons, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.numPolygonsLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
												.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.lablePointsInPolygon, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.pointsInPolygonLabel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
												.addGap(18, 18, 18)
												.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
														.addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.pointsPlus1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(this.pointsMinus1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(this.pointsPlus10, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(this.pointsMinus10, GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE))
														.addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.polyPlus1).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(this.polyMinus1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(this.polyPlus10, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(this.polyMinus10, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
										.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(this.labelInitilizeDNA).addGap(56, 56, 56).addComponent(this.white).addGap(18, 18, 18)
												.addComponent(this.black).addGap(18, 18, 18).addComponent(this.color)))
								.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.numPolygonsLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addComponent(this.polyMinus1)
						.addComponent(this.polyPlus10).addComponent(this.polyMinus10).addComponent(this.label_numOfPolygons, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addComponent(this.polyPlus1))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.lablePointsInPolygon, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.pointsInPolygonLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE).addComponent(this.pointsMinus1).addComponent(this.pointsPlus10)
						.addComponent(this.pointsMinus10).addComponent(this.pointsPlus1))
				.addGap(18, 18, 18)
				.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(this.labelInitilizeDNA, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE).addComponent(this.black).addComponent(this.color)).addComponent(this.white))
				.addContainerGap(154, Short.MAX_VALUE)));
		final GroupLayout layout = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	}

	private void pointsMinus10ActionPerformed(final ActionEvent evt) {
		if (this.app.getConfig().getPointsInPolygon() > 13) {
			this.app.getConfig().setPointsInPolygon(this.app.getConfig().getPointsInPolygon() - 10);
			this.update();
		}
	}

	private void pointsMinus1ActionPerformed(final ActionEvent evt) {
		if (this.app.getConfig().getPointsInPolygon() > 3) {
			this.app.getConfig().setPointsInPolygon(this.app.getConfig().getPointsInPolygon() - 1);
			this.update();
		}
	}

	private void pointsPlus10ActionPerformed(final ActionEvent evt) {
		if (this.app.getConfig().getPointsInPolygon() < 50) {
			this.app.getConfig().setPointsInPolygon(this.app.getConfig().getPointsInPolygon() + 10);
			this.update();
		}
	}

	private void pointsPlus1ActionPerformed(final ActionEvent evt) {
		if (this.app.getConfig().getPointsInPolygon() < 50) {
			this.app.getConfig().setPointsInPolygon(this.app.getConfig().getPointsInPolygon() + 1);
			this.update();
		}
	}

	private void polyMinus10ActionPerformed(final ActionEvent evt) {
		if (this.app.getConfig().getNumOfPolygons() > 10) {
			this.app.getConfig().setNumOfPolygons(this.app.getConfig().getNumOfPolygons() - 10);
			this.update();
		}
	}

	// All methods are for updating the SettingsManager class and also update the text of the SettingsView
	private void polyMinus1ActionPerformed(final ActionEvent evt) {
		if (this.app.getConfig().getNumOfPolygons() > 0) {
			this.app.getConfig().setNumOfPolygons(this.app.getConfig().getNumOfPolygons() - 1);
			this.update();
		}
	}

	private void polyPlus10ActionPerformed(final ActionEvent evt) {
		if (this.app.getConfig().getNumOfPolygons() < 250) {
			this.app.getConfig().setNumOfPolygons(this.app.getConfig().getNumOfPolygons() + 10);
			this.update();
		}
	}

	private void polyPlus1ActionPerformed(final ActionEvent evt) {
		if (this.app.getConfig().getNumOfPolygons() < 250) {
			this.app.getConfig().setNumOfPolygons(this.app.getConfig().getNumOfPolygons() + 1);
			this.update();
		}
	}

	private void update() {
		this.numPolygonsLabel.setText(String.valueOf(this.app.getConfig().getNumOfPolygons()));
		this.pointsInPolygonLabel.setText(String.valueOf(this.app.getConfig().getPointsInPolygon()));
	}

	private void whiteActionPerformed(final ActionEvent evt) {
		this.app.getConfig().setInitialDNA(SettingsManager.WHITE);
	}
}
