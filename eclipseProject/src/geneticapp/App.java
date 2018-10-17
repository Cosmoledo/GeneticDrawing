package geneticapp;

import geneticapp.Interface.GUI;

public class App {
	public static void main(final String[] args) {
		startApp();
	}

	private static void startApp() {
		final GeneticAlgo app = new GeneticAlgo();
		final GUI gui = new GUI(app);
		app.setGui(gui);
		gui.setVisible(true);
	}
}
