package main.fileIO;


import java.awt.Desktop;

import java.io.*;


/**
 *
 */

public class OpenFile
{
	/**
	 * @param filepath
	 * @throws IOException
	 */
	public static void openFile(String filepath) throws IOException
		{
			Desktop.getDesktop().open(new File(filepath));
		}

	/**
	 * @param args
	 */
	public static void main(String[] args)
		{
			try {
				openFile(System.getProperty("user.dir") + "/README.md");
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}
