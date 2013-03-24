package main.frames;

import javax.swing.SwingUtilities;

/**
 * @author RenJOuS
 * 
 */
public class MainWindow {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
