package main.UI;

import main.UI.About;
import main.fileIO.Filechooser;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuItem;
import javax.swing.JTree;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
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

//GUI Class MainFrame

public class CopyOfMainWindow extends JFrame{
	private JTextField searchField;
	private JButton btnGo;
	@SuppressWarnings("unused")
	private JLabel lblSearch;
	private JTree categtree;
	DefaultListModel<Object> mylistmodel = new DefaultListModel<Object>();
	private JPanel itemspanel;
	private String windowicon = "/main/UI/Meor_icon.png";
	private String[] values;
	
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
				{//exception handling for MainWindow
						try {
							CopyOfMainWindow GUI = new CopyOfMainWindow();
							GUI.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
				}
		});
	}
	
	//Constructor
	public CopyOfMainWindow() throws Exception{

		setIconImage(Toolkit.getDefaultToolkit().getImage(CopyOfMainWindow.class.getResource(windowicon)));
		setTitle("MeOr - Media Organiser");
		setSize(860, 715);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setMenuBar();
//		setThemeSel();
		setCategpanel();
		setItemspanel();
	}

	public void setMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		menuBar.setSize(543, 25);
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem refreshmnItem = new JMenuItem("Refresh");
		fileMenu.add(refreshmnItem);
		refreshmnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				mylistmodel.lastElement();
			}
		});

		JMenuItem importmnItem = new JMenuItem("Import");
		importmnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				Filechooser selfiles = new Filechooser();
				selfiles.setVisible(true);
				try {
					for(int i=0;i<values.length;i++)
						mylistmodel.addElement(values[i]);
				} catch (Exception e) {
					// Catch null pointer eception
					e.printStackTrace();
				}
			}
		});
		fileMenu.add(importmnItem);
		JMenuItem clearlistmnItem = new JMenuItem("Clear list");
		fileMenu.add(clearlistmnItem);
		clearlistmnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mylistmodel.removeAllElements();
			}
		});
		
		
		JMenuItem exitmnItem = new JMenuItem("Exit");
		fileMenu.add(exitmnItem);
		exitmnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});
		

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem aboutmnItem = new JMenuItem("About");
		helpMenu.add(aboutmnItem);
		aboutmnItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				About help = new About();
				help.setVisible(true);
				help.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		


		JLabel lblSearch = new JLabel("Search");
		lblSearch.setVisible(true);
		lblSearch.setIcon(new ImageIcon(CopyOfMainWindow.class.getResource("/main/fileIO/search.png")));
		menuBar.add(lblSearch);

		searchField = new JTextField();
		//		searchField.setVisible(false);// invisible
		searchField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				searchField.setText(null);
			}
		});
		searchField.setToolTipText("Search");
		searchField.setText("Enter title");
		menuBar.add(searchField);
		searchField.setColumns(5);

		btnGo = new JButton("Go");
		btnGo.setVisible(true);
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(values[0]);
				for(int i=0;i<values.length;i++){
					mylistmodel.addElement(values[i]);
				}

			}
		});
		menuBar.add(btnGo);
	}

// commented theme selector for future implementation!!	
	{
//	public void setThemeSel(){
//		//	theme selector
//		JLabel lblTheme = new JLabel("Theme");
//		lblTheme.setVerticalAlignment(SwingConstants.TOP);
//		lblTheme.setHorizontalAlignment(SwingConstants.TRAILING);
//		menuBar.add(lblTheme);
//
//		JRadioButton rdbtnnative = new JRadioButton("Native");
//		rdbtnnative.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0)
//			{
//				try {
//					UIManager.setLookAndFeel("Nimbus");
//				}
//				catch (ClassNotFoundException | InstantiationException
//						| IllegalAccessException | UnsupportedLookAndFeelException e) {
//					e.printStackTrace();
//				}
//				SwingUtilities.updateComponentTreeUI(getComponent(4));
//				((Window) getContentPane()).pack();
//			}
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
//			{
//				try {
//					UIManager.setLookAndFeel("Windows");
//				}
//				catch (ClassNotFoundException | InstantiationException
//						| IllegalAccessException | UnsupportedLookAndFeelException e) {
//					e.printStackTrace();
//				}
//				SwingUtilities.updateComponentTreeUI(getContentPane());
//				((Window) getContentPane()).pack();
//			}
//		});
//		rdbtnnative.setVerticalAlignment(SwingConstants.TOP);
//		rdbtncrossplat.setHorizontalAlignment(SwingConstants.TRAILING);
//		buttonGroup.add(rdbtncrossplat);
//		menuBar.add(rdbtncrossplat);
//		//	theme selector ends
//	}
	}
	public void setCategpanel(){
		final JLayeredPane categpanel = new JLayeredPane();
		categpanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Categories", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		categpanel.setBounds(0, 0, 169, getHeight());
		add(categpanel);
		categpanel.setLayout(new BoxLayout(categpanel, BoxLayout.X_AXIS));

		JScrollPane scrollPane = new JScrollPane();
		categpanel.add(scrollPane);

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Categories");
		categtree = new JTree(top);
		DefaultMutableTreeNode media = new DefaultMutableTreeNode("Media");
		DefaultMutableTreeNode software = new DefaultMutableTreeNode("Softwares");
		DefaultMutableTreeNode document = new DefaultMutableTreeNode("Documents");
		DefaultMutableTreeNode audio = new DefaultMutableTreeNode("Audio");
		DefaultMutableTreeNode video = new DefaultMutableTreeNode("Video");
		DefaultMutableTreeNode windows = new DefaultMutableTreeNode("Windows");
		DefaultMutableTreeNode linux = new DefaultMutableTreeNode("Linux");
		DefaultMutableTreeNode macintosh = new DefaultMutableTreeNode("Mac");
		DefaultMutableTreeNode android = new DefaultMutableTreeNode("Android");
		DefaultMutableTreeNode other = new DefaultMutableTreeNode("Others");
		DefaultMutableTreeNode spreadsheet = new DefaultMutableTreeNode("Spreadsheets");
		DefaultMutableTreeNode ebook = new DefaultMutableTreeNode("eBooks");
		DefaultMutableTreeNode text = new DefaultMutableTreeNode("Rich Text");
		DefaultMutableTreeNode presentation = new DefaultMutableTreeNode("Presentations");
		DefaultMutableTreeNode pdf = new DefaultMutableTreeNode("Pdfs");
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
		document.add(pdf);
		//categtree.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(categtree);
		// tree in categories panel ends
		//tree event listeners commence here
		categtree.addTreeExpansionListener(new TreeExpansionListener() {
			public void treeCollapsed(TreeExpansionEvent event) {
				System.out.println(event.getPath());
			}
			public void treeExpanded(TreeExpansionEvent event) {
				System.out.println(event.getPath());
			}
		});

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
	}

	@SuppressWarnings("unchecked")
	public void setItemspanel(){
		itemspanel = new JPanel();
		itemspanel.setBounds(172, 3, 1195, 711);
		add(itemspanel);
		itemspanel.setLayout(new BorderLayout(0, 0));
		JScrollPane itemscrollPane = new JScrollPane();
		itemscrollPane
		.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		itemscrollPane
		.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		itemspanel.add(itemscrollPane);

		@SuppressWarnings("rawtypes")
		final JList list = new JList();
		list.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				//mylistmodel.addElement();
				//mylistmodel.addElement(values);

			}
		});
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
