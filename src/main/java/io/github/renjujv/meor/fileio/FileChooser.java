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

package io.github.renjujv.meor.fileio;

import java.awt.EventQueue;
import java.io.File;
import javax.swing.JFileChooser;

/**
 * @author RenJOuS
 *
 */
public class FileChooser extends JFileChooser {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			FileChooser chooser = new FileChooser();
			chooser.setVisible(true);
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public FileChooser() {
		setCurrentDirectory(new File("."));
		setDialogTitle("File Import");
		setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		setAcceptAllFileFilterUsed(false);

		if (showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			System.out.println("Current Directory: " + getCurrentDirectory());
			System.out.println("Selected File : " + getSelectedFile().getAbsolutePath());
			try {
				FileOps.addFolder(getSelectedFile().getAbsolutePath());
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		} else {
			System.out.println("No Selection!");
		}
	}
}