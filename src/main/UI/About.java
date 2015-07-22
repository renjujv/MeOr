package main.UI;


import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;


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
	 * 
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(0, 0, 512, 512);
		lblNewLabel_3.setIcon(new ImageIcon(About.class
				.getResource("/resources/meor-material.png")));
		getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel = new JLabel("MeOr - Media Organiser");
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblNewLabel.setBounds(139, 8, 231, 25);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("version 0.0.1");
		lblNewLabel_1.setBounds(175, 44, 80, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Copyright @ 2013");
		lblNewLabel_2.setBounds(175, 67, 139, 20);
		contentPane.add(lblNewLabel_2);

		JTextArea txtMEOR = new JTextArea();
		txtMEOR.setEditable(false); // text is not editable and it need not
												// be,right??
		txtMEOR
				.setText("Media Organizer is an Open-Source software distribution free of charge under the terms of GNU General Public License."
						+ "\nDeveloped by:\nRenju Jose\nRohan Philip\nAbijith N V\nMuhammad Rafeek S R\n");
		txtMEOR.setLineWrap(true); // for setting text wrapping
		txtMEOR.setCaretPosition(0); // for setting to start of text

		JScrollPane scrolltxtArea = new JScrollPane();
		scrolltxtArea.setBounds(21, 170, 387, 70);
		contentPane.add(scrolltxtArea);
		scrolltxtArea.setViewportView(txtMEOR);
	}
}
