package main.frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Toolkit;

/**
 * @author RenJOuS
 *
 */
@SuppressWarnings("serial")
public class About extends JFrame
{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 * @param args 
	 */
	public static void main(String[] args)
		{
			EventQueue.invokeLater(new Runnable() {
				public void run()
					{
						try {
							About frame = new About();
							frame.setVisible(true);
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
			});
		}

	/**
	 * Create the frame.
	 */
	public About() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Media Organiser");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel.setBounds(139, 8, 176, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("version 0.0.1");
		lblNewLabel_1.setBounds(139, 44, 80, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Copyright @ 2013");
		lblNewLabel_2.setBounds(139, 67, 139, 20);
		contentPane.add(lblNewLabel_2);
		
		JTextArea txtrMediaOrganizerIs = new JTextArea();
		txtrMediaOrganizerIs.setLineWrap(true);
		txtrMediaOrganizerIs.setEditable(false);
		txtrMediaOrganizerIs.setText("Media Organizer is an Open-Source software distribution free of charge under the terms of GNU General Public License");
		txtrMediaOrganizerIs.setBounds(21, 98, 387, 153);
		contentPane.add(txtrMediaOrganizerIs);
	}
}
