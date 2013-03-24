package main.frames;

import main.fileIO.*;
import gluecode.FileOps;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JRadioButton;
import javax.swing.JMenu;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
/**
 * @author RenJOuS
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	private final ButtonGroup buttonGroup = new ButtonGroup();
	//main window
	/**
	 * @throws Exception 
	 * 
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public MainFrame() throws Exception{
		setVisible(true);
		setTitle("Media Organizer");
		setSize(560,415);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			JMenuBar menuBar = new JMenuBar();
			menuBar.setSize(543, 25);
			setJMenuBar(menuBar);
			//menu bar
			
			JMenu mnNewMenu = new JMenu("File");
			menuBar.add(mnNewMenu);
			
			JMenuItem Import = new JMenuItem("Import");
			Import.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
						Filechooser selfiles = new Filechooser();
						selfiles.setVisible(true);
						
				}
			});
			mnNewMenu.add(Import);
			
			JMenuItem Export = new JMenuItem("Export");
			mnNewMenu.add(Export);
			
			JMenuItem Exit = new JMenuItem("Exit");
			mnNewMenu.add(Exit);
			
			JMenu mnHelp = new JMenu("Help");
			mnHelp.setHorizontalAlignment(SwingConstants.LEFT);
			mnHelp.setHorizontalTextPosition(SwingConstants.LEFT);
			menuBar.add(mnHelp);
			
			JMenuItem mntmAbout = new JMenuItem("About");
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
						About help= new About();   
					   help.setVisible(true);
					   //help.setSize(250,100);
					   help.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				}
			});
			mnHelp.add(mntmAbout);
			//menu bar ends
			Component rigidArea = Box.createRigidArea(new Dimension(317, 23));		//trailing alignment for theme selector
			rigidArea.setMaximumSize(new Dimension(1200, 23));		// for resizing rigid area during window maximization
			menuBar.add(rigidArea);
			//theme selector
			JLabel lblTheme = new JLabel("Theme");
			lblTheme.setVerticalAlignment(SwingConstants.TOP);
			lblTheme.setHorizontalAlignment(SwingConstants.TRAILING);
			menuBar.add(lblTheme);
			
			JRadioButton rdbtnnative = new JRadioButton("Native");
			rdbtnnative.setSelected(true);
			rdbtnnative.setVerticalAlignment(SwingConstants.TOP);
			rdbtnnative.setHorizontalAlignment(SwingConstants.TRAILING);
			buttonGroup.add(rdbtnnative);
			menuBar.add(rdbtnnative);
			
			JRadioButton rdbtnmodern = new JRadioButton("Modern");
			rdbtnnative.setVerticalAlignment(SwingConstants.TOP);
			rdbtnmodern.setHorizontalAlignment(SwingConstants.TRAILING);
			buttonGroup.add(rdbtnmodern);
			menuBar.add(rdbtnmodern);
			//theme selector ends
			getContentPane().setLayout(null);	//absolute layout
			//items panel ends
			
			//categories panel
			JLayeredPane categpanel = new JLayeredPane();
			categpanel.setMaximumSize(new Dimension(169, 1000));
			categpanel.setMinimumSize(new Dimension(169, 333));
			categpanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Categories", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			categpanel.setBounds(0, 0, 169, 333);
			getContentPane().add(categpanel);
			categpanel.setLayout(new BoxLayout(categpanel, BoxLayout.X_AXIS));
			
			JScrollPane scrollPane = new JScrollPane();
			categpanel.add(scrollPane);
			
			JTree tree = new JTree();
			tree.setModel(new DefaultTreeModel(
				new DefaultMutableTreeNode("Collection") {
					{
						DefaultMutableTreeNode node_1;
						DefaultMutableTreeNode node_2;
						node_1 = new DefaultMutableTreeNode("Media");
							node_2= new DefaultMutableTreeNode("Audio");
							node_1.add(new DefaultMutableTreeNode("Video"));
							node_1.add(new DefaultMutableTreeNode("Images"));
							node_2.add(new DefaultMutableTreeNode("Songs"));
							node_2.add(new DefaultMutableTreeNode("Clips"));
							node_2.add(new DefaultMutableTreeNode("Recorded"));
//							node_2.add(new DefaultMutableTreeNode("Movies"));
//							node_2.add(new DefaultMutableTreeNode("Clips"));
							node_1.add(node_2);
							add(node_1);
						node_1 = new DefaultMutableTreeNode("Softwares");
						node_1.add(new DefaultMutableTreeNode("License"));
						node_1.add(new DefaultMutableTreeNode("Price"));
						node_2= new DefaultMutableTreeNode("Platform");
						node_2.add(new DefaultMutableTreeNode("Windows"));
						node_2.add(new DefaultMutableTreeNode("Linux"));
						node_2.add(new DefaultMutableTreeNode("Macintosh"));
						node_2.add(new DefaultMutableTreeNode("Android"));
						node_2.add(new DefaultMutableTreeNode("Other"));
						//node_2= new DefaultMutableTreeNode("Price");
						node_1.add(node_2);
						add(node_1);
					}
				}
			));
			tree.setBorder(new LineBorder(new Color(0, 0, 0)));
			scrollPane.setViewportView(tree);
			//tree in categories panel ends
			//items panel
			JPanel itemspanel = new JPanel();
			itemspanel.setBounds(172, 3, 375, 330);
			getContentPane().add(itemspanel);
			itemspanel.setLayout(new BorderLayout(0, 0));
			
			JList list = new JList();
			list.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setModel(new AbstractListModel() {
				//String[] values = new String[] {"Avatar.avi", "tonight.mp3"};
				String[] values = FileOps.getFiles("Music");
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			itemspanel.add(list, BorderLayout.CENTER);
	}
}