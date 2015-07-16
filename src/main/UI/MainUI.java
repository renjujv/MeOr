package main.UI;

import javax.swing.SwingUtilities;

/**
 * @author RenJOuS
 * 
 */
public class MainUI {
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
