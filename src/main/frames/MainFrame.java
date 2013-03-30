package main.frames;


import main.fileIO.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ScrollPaneConstants;
import javax.swing.Box;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JList;
import javax.swing.AbstractListModel;


/**
 * @author RenJOuS
 * 
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	//private final ButtonGroup buttonGroup = new ButtonGroup();
	// main window
	/**
	 * @throws Exception
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public MainFrame() throws Exception {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				MainFrame.class.getResource("/main/frames/Meor_icon.png")));
		setVisible(true);
		setTitle("MeOr - Media Organiser");
		setSize(860, 715);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// menu bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setSize(543, 25);
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem refreshmnItem = new JMenuItem("Refresh");
		
		fileMenu.add(refreshmnItem);
		
		JMenuItem importmnItem = new JMenuItem("Import");
		importmnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
				{
					Filechooser selfiles = new Filechooser();
					selfiles.setVisible(true);

				}
		});
		fileMenu.add(importmnItem);
		JMenuItem exitmnItem = new JMenuItem("Exit");
		exitmnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
				{
					System.exit(0);
				}
		});
		fileMenu.add(exitmnItem);

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem aboutmnItem = new JMenuItem("About");
		aboutmnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
				{
					About help = new About();
					help.setVisible(true);
					help.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				}
		});
		helpMenu.add(aboutmnItem);
		// menu bar ends
		Component rigidArea = Box.createRigidArea(new Dimension(334, 21)); // trailing
																									// alignment
																									// for
																									// theme
																									// selector
		rigidArea.setMaximumSize(new Dimension(1200, 21)); // for resizing rigid
																			// area during window
																			// maximization
		menuBar.add(rigidArea);
//		// theme selector
//		JLabel lblTheme = new JLabel("Theme");
//		// lblTheme.setVerticalAlignment(SwingConstants.TOP);
//		// lblTheme.setHorizontalAlignment(SwingConstants.TRAILING);
//		menuBar.add(lblTheme);
//
//		JRadioButton rdbtnnative = new JRadioButton("Native");
//		rdbtnnative.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0)
//				{
//					try {
//						UIManager.setLookAndFeel("Nimbus");
//					}
//					catch (ClassNotFoundException | InstantiationException
//							| IllegalAccessException | UnsupportedLookAndFeelException e) {
//						e.printStackTrace();
//					}
//					SwingUtilities.updateComponentTreeUI(getComponent(4));
//					((Window) getContentPane()).pack();
//				}
//		});
//		rdbtnnative.setSelected(true);
//		rdbtnnative.setVerticalAlignment(SwingConstants.TOP);
//		rdbtnnative.setHorizontalAlignment(SwingConstants.TRAILING);
//		buttonGroup.add(rdbtnnative);
//		menuBar.add(rdbtnnative);
//
//		JRadioButton rdbtncrossplat = new JRadioButton("Cross Platform");
//		rdbtncrossplat.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0)
//				{
//					try {
//						UIManager.setLookAndFeel("Windows");
//					}
//					catch (ClassNotFoundException | InstantiationException
//							| IllegalAccessException | UnsupportedLookAndFeelException e) {
//						e.printStackTrace();
//					}
//					SwingUtilities.updateComponentTreeUI(getContentPane());
//					((Window) getContentPane()).pack();
//				}
//		});
//		rdbtnnative.setVerticalAlignment(SwingConstants.TOP);
//		rdbtncrossplat.setHorizontalAlignment(SwingConstants.TRAILING);
//		buttonGroup.add(rdbtncrossplat);
//		menuBar.add(rdbtncrossplat);
//		// theme selector ends
		getContentPane().setLayout(null); // absolute layout
		// items panel ends

		// categories panel
		final JLayeredPane categpanel = new JLayeredPane();
		// categpanel.setMaximumSize(new Dimension(169, 1000));
		// categpanel.setMinimumSize(new Dimension(169, 333));
		categpanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Categories", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		categpanel.setBounds(0, 0, 169, getHeight());
		getContentPane().add(categpanel);
		categpanel.setLayout(new BoxLayout(categpanel, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		categpanel.add(scrollPane);

		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(
				"Collection") {
			{
				DefaultMutableTreeNode node_1;
				DefaultMutableTreeNode node_2;
				node_1 = new DefaultMutableTreeNode("Media");
				node_2 = new DefaultMutableTreeNode("Audio");
				node_2.add(new DefaultMutableTreeNode("Songs"));
				node_2.add(new DefaultMutableTreeNode("Clips"));
				node_2.add(new DefaultMutableTreeNode("Recorded"));
				node_1.add(node_2);
				add(node_1);
				node_2 = new DefaultMutableTreeNode("Video");
				node_2.add(new DefaultMutableTreeNode("Movies"));
				node_2.add(new DefaultMutableTreeNode("Clips"));
				node_1.add(node_2);
				add(node_1);
				node_2 = new DefaultMutableTreeNode("Images");
				node_2.add(new DefaultMutableTreeNode("Animated"));
				node_2.add(new DefaultMutableTreeNode("Other"));
				node_2.add(new DefaultMutableTreeNode("Transparent"));
				node_1.add(node_2);
				add(node_1);
				node_1 = new DefaultMutableTreeNode("Softwares");
				node_2 = new DefaultMutableTreeNode("Platform");
				node_2.add(new DefaultMutableTreeNode("Windows"));
				node_2.add(new DefaultMutableTreeNode("Linux"));
				node_2.add(new DefaultMutableTreeNode("Macintosh"));
				node_2.add(new DefaultMutableTreeNode("Android"));
				node_2.add(new DefaultMutableTreeNode("Other"));
				node_1.add(node_2);
				add(node_1);
			}
		}));
		tree.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(tree);
		// tree in categories panel ends
		// items panel
		JPanel itemspanel = new JPanel();
		itemspanel.setBounds(172, 3, 1180, 711);
		getContentPane().add(itemspanel);
		itemspanel.setLayout(new BorderLayout(0, 0));
		JScrollPane itemscrollPane = new JScrollPane();
		itemscrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		itemscrollPane
		.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		itemspanel.add(itemscrollPane);

		final JList list = new JList();
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(30);
		list.setModel(new AbstractListModel() {
		// String[] values = new String[] {"fg", "fgu", "ghg"};

			String[] values = gluecode.FileOps.getFiles("Music");
			public int getSize()
				{
					return values.length;
				}
			public Object getElementAt(int index)
				{
					return values[index];
				}
		});
		refreshmnItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
					{
							list.revalidate();
							list.repaint();
					}
		});
		list.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
						int cc=e.getClickCount();
						if(cc==2){
							String selected= list.getSelectedValue().toString();
							System.out.println("Mouse clicked on list");
							try {
								main.fileIO.OpenFile.openFile(selected);
							}
							catch (IOException e1) {
								e1.printStackTrace();
							}
						}
				}
			});
		itemscrollPane.setViewportView(list);
	}
}