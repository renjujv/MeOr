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

package main.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;

/**
 * @author RenJOuS
 * @author HEXcube
 * 
 */
public class About extends JFrame {
	private static final long serialVersionUID = 1530807439035354347L;
	private static final String APP_VERSION = "0.2.1";
	private static final String APP_ICON_PATH = "/resources/meor-icon.png";
	private static final String APP_LOGO_PATH = "/resources/meor-logo.png";
	private static final String LICENSE_LOGO_PATH = "/resources/gplv3.png";
	private JPanel aboutPanel;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable oops) {
			System.out.println(oops.getMessage());
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				About aboutFrame = new About();
				aboutFrame.setVisible(true);
			}
		});
	}

	// Constructor
	public About() {
		setTitle("About MeOr");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				About.class.getResource(APP_ICON_PATH)));
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 360);
		initUI();
		setElements();
	}

	// Initialize the panel
	private final void initUI() {
		aboutPanel = new JPanel();
		aboutPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		setContentPane(aboutPanel);
		aboutPanel.setLayout(null);
	}

	// Insert Elements
	private final void setElements() {

		// App logo
		JLabel appLogo = new JLabel();
		appLogo.setHorizontalAlignment(SwingConstants.CENTER);
		appLogo.setBounds(12, 0, 454, 160);
		appLogo.setIcon(new ImageIcon(About.class.getResource(APP_LOGO_PATH)));
		getContentPane().add(appLogo);

		// App name
		JLabel appName = new JLabel("MeOr");
		appName.setHorizontalAlignment(SwingConstants.CENTER);
		appName.setFont(new Font("SansSerif", Font.BOLD, 18));
		appName.setBounds(12, 170, 454, 24);
		aboutPanel.add(appName);

		// App release version
		JLabel appVersion = new JLabel("version " + APP_VERSION);
		appVersion.setFont(new Font("SansSerif", Font.PLAIN, 14));
		appVersion.setHorizontalAlignment(SwingConstants.CENTER);
		appVersion.setBounds(12, 196, 454, 24);
		aboutPanel.add(appVersion);

		// App description
		JLabel appDescription = new JLabel("Easy to use media organizer");
		appDescription.setFont(new Font("SansSerif", Font.PLAIN, 14));
		appDescription.setHorizontalAlignment(SwingConstants.CENTER);
		appDescription.setBounds(12, 222, 454, 24);
		aboutPanel.add(appDescription);

		// Copyright note
		JLabel copyrightNote = new JLabel("© MeOr Project");
		copyrightNote.setFont(new Font("Dialog", Font.PLAIN, 12));
		copyrightNote.setHorizontalAlignment(SwingConstants.CENTER);
		copyrightNote.setBounds(12, 264, 454, 20);
		aboutPanel.add(copyrightNote);

		// License
		JLabel licenseName = new JLabel("Licensed under GNU GPL");
		licenseName.setFont(new Font("Dialog", Font.PLAIN, 12));
		licenseName.setHorizontalAlignment(SwingConstants.CENTER);
		licenseName.setBounds(12, 286, 454, 20);
		aboutPanel.add(licenseName);

		// License logo
		JLabel licenseLogo = new JLabel();
		licenseLogo.setHorizontalAlignment(SwingConstants.CENTER);
		licenseLogo.setLabelFor(aboutPanel);
		licenseLogo.setBounds(348, 232, 96, 96);
		licenseLogo.setIcon(new ImageIcon(About.class
				.getResource(LICENSE_LOGO_PATH)));
		getContentPane().add(licenseLogo);
	}
}
