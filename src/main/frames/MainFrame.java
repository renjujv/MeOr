package main.frames;


import main.fileIO.*;
import main.frames.About;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.Box;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.JLayeredPane;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


/**
 * @author RenJOuS
 * 
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	private JTextField searchField;
	private JButton btnGo;
	@SuppressWarnings("unused")
	private JLabel lblSearch;
	private JTree categtree;
	String[] values;
	DefaultListModel<Object> mylistmodel = new DefaultListModel<Object>();
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
					for(int i=0;i<values.length;i++)
						mylistmodel.addElement(values[i]);
					

				}
		});
		
		JMenuItem clearlistmnItem = new JMenuItem("Clear list");
		clearlistmnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					mylistmodel.removeAllElements();
			}
		});
		fileMenu.add(clearlistmnItem);
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

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setVisible(false);
		lblSearch.setIcon(new ImageIcon(MainFrame.class.getResource("/main/fileIO/search.png")));
		menuBar.add(lblSearch);

		searchField = new JTextField();
		searchField.setVisible(false);// invisible
		searchField.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
					
			}
		});
		searchField.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
						searchField.setText(null);
				}
		});
		searchField.setToolTipText("Search");
		searchField.setText("Enter title");
		menuBar.add(searchField);
		searchField.setColumns(10);
		
		btnGo = new JButton("Go");
		btnGo.setVisible(false);
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					System.out.println(values[0]);
					for(int i=0;i<values.length;i++){
						mylistmodel.addElement(values[i]);
					}
					
			}
		});
		menuBar.add(btnGo);
		
		// theme selector
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
		categpanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Categories", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		categpanel.setBounds(0, 0, 169, getHeight());
		getContentPane().add(categpanel);
		categpanel.setLayout(new BoxLayout(categpanel, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		categpanel.add(scrollPane);

		final DefaultMutableTreeNode top = new DefaultMutableTreeNode("Categories");
		categtree = new JTree(top);
		DefaultMutableTreeNode media = new DefaultMutableTreeNode("Media");
		DefaultMutableTreeNode software = new DefaultMutableTreeNode("Softwares");
		DefaultMutableTreeNode document = new DefaultMutableTreeNode("Documents");
		final DefaultMutableTreeNode audio = new DefaultMutableTreeNode("Audio");
		DefaultMutableTreeNode video = new DefaultMutableTreeNode("Video");
		DefaultMutableTreeNode windows = new DefaultMutableTreeNode("Windows");
		DefaultMutableTreeNode android = new DefaultMutableTreeNode("Android");
		DefaultMutableTreeNode linux = new DefaultMutableTreeNode("Linux");
		DefaultMutableTreeNode macintosh = new DefaultMutableTreeNode("Mac");
		DefaultMutableTreeNode other = new DefaultMutableTreeNode("Others");
		DefaultMutableTreeNode spreadsheet = new DefaultMutableTreeNode("Spreadsheets");
		DefaultMutableTreeNode ebook = new DefaultMutableTreeNode("eBooks");
		DefaultMutableTreeNode text = new DefaultMutableTreeNode("Rich Text");
		DefaultMutableTreeNode presentation = new DefaultMutableTreeNode("Presentations");
		//	DefaultMutableTreeNode pdf = new DefaultMutableTreeNode("Pdfs");
		top.add(media);
		top.add(software);
		top.add(document);
		media.add(audio);
		media.add(video);
		software.add(windows);
		software.add(linux);
		software.add(macintosh);
		software.add(android);
		software.add(other);
		document.add(spreadsheet);
		document.add(ebook);
		document.add(text);
		document.add(presentation);
		//	document.add(pdf);
		categtree.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(categtree);
		// tree in categories panel ends
		//tree event listeners commence here
		//		tree.addTreeExpansionListener(new TreeExpansionListener() {
		//				public void treeCollapsed(TreeExpansionEvent event) {
		//						System.out.println(event.getPath());
		//				}
		//				public void treeExpanded(TreeExpansionEvent event) {
		//						System.out.println(event.getPath());
		//				}
		//			});
		//		
		categtree.addTreeSelectionListener(new TreeSelectionListener() {
				public void valueChanged(TreeSelectionEvent tse)
					{
						String selectedCat= String.valueOf(tse.getPath());
						int lsindex=selectedCat.lastIndexOf(", ");
						selectedCat=selectedCat.substring(lsindex+2,selectedCat.length()-1);
						System.out.println(selectedCat);
						
						try {
							values = gluecode.FileOps.getFiles(selectedCat);
						}
						catch (Exception e) {
							e.printStackTrace();
						}
						mylistmodel.removeAllElements();
						for(int i=0;i<values.length;i++)
							mylistmodel.addElement(values[i]);
						
					}
		});
		//tree event listener ends here
		// items panel
		JPanel itemspanel = new JPanel();
		itemspanel.setBounds(172, 3, 1195, 711);
		getContentPane().add(itemspanel);
		itemspanel.setLayout(new BorderLayout(0, 0));
		JScrollPane itemscrollPane = new JScrollPane();
		itemscrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		itemscrollPane
		.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		itemspanel.add(itemscrollPane);

		final JList list = new JList();
//		list.addPropertyChangeListener(new PropertyChangeListener() {
//			public void propertyChange(PropertyChangeEvent evt) {
//					//mylistmodel.addElement();
//					//mylistmodel.addElement(values);
//					
//			}
//		});
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(30);
		list.setModel(mylistmodel);
		
//			{
//		// String[] values = new String[] {"fg", "fgu", "ghg"};
//
//			String[] values = gluecode.FileOps.getFiles("Music");
//			public int getSize()
//				{
//					return values.length;
//				}
//			public Object getElementAt(int index)
//				{
//					return values[index];
//				}
//		});
		refreshmnItem.addActionListener(new ActionListener() {
				/**
				 * @param arg0
				 */
				public void actionPerformed(ActionEvent arg0)
					{
						mylistmodel.lastElement();
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