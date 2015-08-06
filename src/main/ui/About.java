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

/**
 * @author RenJOuS
 * 
 * 
 */
@SuppressWarnings("serial")
public class About extends JFrame {
	private final String VERSION = "0.3.1";
	private JPanel aboutPanel;

	public static void main(String[] args) {
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
				About.class.getResource("/resources/meor-icon.png")));
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
		appLogo.setIcon(new ImageIcon(About.class
				.getResource("/resources/meor-logo.png")));
		getContentPane().add(appLogo);

		// App name
		JLabel appName = new JLabel("MeOr");
		appName.setHorizontalAlignment(SwingConstants.CENTER);
		appName.setFont(new Font("SansSerif", Font.BOLD, 18));
		appName.setBounds(12, 170, 454, 24);
		aboutPanel.add(appName);

		// App release version
		JLabel appVersion = new JLabel("version " + VERSION);
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
		JLabel licenseName = new JLabel("Licensed under GNU GPLv3");
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
				.getResource("/resources/gplv3.png")));
		getContentPane().add(licenseLogo);

	}
}
