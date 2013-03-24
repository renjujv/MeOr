package main.fileIO;

import java.awt.Desktop;

import java.io.*;

/**
 *
 */

public class OpenFile {
	public static void main(String[] args) throws IOException {
		Desktop.getDesktop().open(new File(System.getProperty("user.dir")+"/README.md"));
	}
}