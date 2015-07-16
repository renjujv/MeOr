package main.UI;


import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Toolkit;


/**
 * @author RenJOuS
 * 
 */
@SuppressWarnings("serial")
public class About extends JFrame
{	
	private JPanel aboutPage;
	public static void main(String[] args)
		{
			EventQueue.invokeLater(new Runnable() {
				public void run()
					{
							About frame = new About();
							frame.setVisible(true);
					}
			});
		}
	
	public About() {
		setType(Type.POPUP);
		setTitle("About");
		setResizable(false);
		setOpacity(0.5f);
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/main/UI/Meor_icon.png")));
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		aboutPage = new JPanel();
		aboutPage.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(aboutPage);
		aboutPage.setLayout(null);
		setElements();
		
	}
	public void setElements(){
		JLabel meorIcon = new JLabel("MeOr");
		meorIcon.setToolTipText("MeOr");
		meorIcon.setLabelFor(aboutPage);
		meorIcon.setBounds(0, 0, 185, 160);
		meorIcon.setIcon(new ImageIcon(About.class
				.getResource("/main/UI/Meor_icon.png")));
		getContentPane().add(meorIcon);

		JLabel lblpage = new JLabel("MeOr - Media Organiser");
		lblpage.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblpage.setBounds(139, 8, 231, 25);
		aboutPage.add(lblpage);

		JLabel lblversion = new JLabel("version 0.0.1");
		lblversion.setBounds(175, 44, 80, 14);
		aboutPage.add(lblversion);

		JLabel lblcopyrightnote = new JLabel("Copyright @ 2013");
		lblcopyrightnote.setBounds(175, 67, 139, 20);
		aboutPage.add(lblcopyrightnote);
		
		JTextArea txtMEOR = new JTextArea();
		txtMEOR.setBounds(10, 150, 425, 110);
		aboutPage.add(txtMEOR);
		txtMEOR.setEditable(false); // text is not editable and it need not
				// be,right??
		txtMEOR.setText("Media Organizer is an Open-Source software\ndistribution free of charge"
				+ "under the terms of GNU\nGeneral Public License.\r\nDeveloped by:\r\nRenju Jose,Rohan "
				+ "Philip,Abijith N V,Muhammad Rafeek S R\n");
		txtMEOR.setLineWrap(true); // for setting text wrapping
		txtMEOR.setCaretPosition(0); // for setting to start of text
	}
}