/**
 * Copyright (C) MeOr Project
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package main.fileio;

import java.awt.EventQueue;
import javax.swing.JFileChooser;

/**
 * @author RenJOuS
 *
 */
@SuppressWarnings("serial")
public class FileChooser extends JFileChooser {

	// private JPanel contentPane;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					FileChooser chooser = new FileChooser();
					chooser.setVisible(true);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */

	public FileChooser() {
		setCurrentDirectory(new java.io.File("."));
		setDialogTitle("File Import");
		setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		setAcceptAllFileFilterUsed(false);

		if (showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			System.out.println("getCurrentDirectory(): "
					+ getCurrentDirectory());
			System.out.println("getSelectedFile() : "
					+ getSelectedFile().getAbsolutePath());
			try {
				core.FileOps.addFolder(getSelectedFile().getAbsolutePath());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("No Selection ");
		}
	}
}
