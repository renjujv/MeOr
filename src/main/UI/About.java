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
import javax.swing.JScrollPane;
import javax.swing.border.MatteBorder;
import java.awt.Color;


/**
 * @author RenJOuS
 * 
 */
@SuppressWarnings("serial")
public class About extends JFrame
{	
	private double version=0.1;
	private JPanel aboutpanel;
	public static void main(String[] args)
		{
			EventQueue.invokeLater(new Runnable() {
				public void run()
					{
							About aboutframe = new About();
							aboutframe.setVisible(true);
					}
			});
		}
	//Constructor
	public About() {
		setType(Type.POPUP);
		setTitle("About");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(About.class.getResource("/main/UI/Meor_icon.png")));
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		initUI();
		setElements();
		
	}
	//Initialize the panel
	public void initUI(){
		aboutpanel = new JPanel();
		aboutpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(aboutpanel);
		aboutpanel.setLayout(null);
	}
	//Insert Elements
	public void setElements(){
		//Icon
		JLabel meorIcon = new JLabel("MeOr");
		meorIcon.setToolTipText("MeOr");
		meorIcon.setLabelFor(aboutpanel);
		meorIcon.setBounds(0, 0, 185, 160);
		meorIcon.setIcon(new ImageIcon(About.class.getResource("/main/UI/Meor_icon.png")));
		getContentPane().add(meorIcon);
		
		//Text labels start
		JLabel lblpage = new JLabel("MeOr - Media Organiser");
		lblpage.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblpage.setBounds(139, 8, 231, 25);
		aboutpanel.add(lblpage);

		JLabel lblversion = new JLabel("version "+version);
		lblversion.setBounds(175, 44, 80, 14);
		aboutpanel.add(lblversion);

		JLabel lblcopyrightnote = new JLabel("Copyright - RenJOuS�");
		lblcopyrightnote.setBounds(175, 67, 139, 20);
		aboutpanel.add(lblcopyrightnote);
		//Text labels end
		
		//Credits text
		JTextArea txtMEOR = new JTextArea();
		txtMEOR.setEditable(false);
		txtMEOR.setText("Credits:\nMedia Organizer is an Open-Source software distribution free of charge"
				+ "\nunder the terms of GNU General Public License.\r\nDeveloped by:\r\nRenju Jose,\nRohan "
				+ "Philip,\nAbijith N V,\nMuhammad Rafeek S R");
		txtMEOR.setLineWrap(true); // for setting text wrapping
		txtMEOR.setCaretPosition(0); // for setting to start of text
		
		//ScrollPane surrounding credits
		JScrollPane scrollPanecredits = new JScrollPane();
		scrollPanecredits.setViewportBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		scrollPanecredits.setBounds(10, 150, 425, 110);
		aboutpanel.add(scrollPanecredits);
		scrollPanecredits.setViewportView(txtMEOR);
	}
}