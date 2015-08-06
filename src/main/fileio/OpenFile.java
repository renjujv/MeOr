package main.fileio;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * @author RenJOuS
 *
 */

public class OpenFile {
	/**
	 * @param filepath
	 * @throws IOException
	 */
	public static void openFile(String filepath) throws IOException {
		Desktop.getDesktop().open(new File(filepath));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			openFile(System.getProperty("user.dir") + "/README.md");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}