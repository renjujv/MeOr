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

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import main.fileio.FileChooser;

import javax.swing.border.BevelBorder;

import java.awt.Color;
import javax.swing.border.TitledBorder;
/**
 * @author RenJOuS
 * 
 */
@SuppressWarnings("serial")
//GUI Class MainFrame

class MainWindowTesting extends JFrame{
	private JMenuBar menuBar;
	private JList<String> itemsList;
	private DefaultListModel<String> myListModel = new DefaultListModel<String>();
	private String[] values;
	private static final String APP_ICON_PATH = "/resources/meor_icon.png";
	private static final String SEARCH_LOGO = "/resources/search.png";

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowTesting GUI = new MainWindowTesting();
					GUI.setVisible(true);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
		});
	}
	//Constructor
	MainWindowTesting() {
		try {
			setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindowTesting.class.getResource(APP_ICON_PATH)));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		setTitle("MeOr - Media Organiser");
		setExtendedState(Frame.MAXIMIZED_BOTH);		//tell me if this code makes the MainWindowLegacy start maximized in your system
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLooknFeel("Nimbus");						//set LnF before adding components
		setContentPane();
		setMenuBar();
		setSearchBar();
		setCategoryPanel();
		setItemsPanel();
		setStatusBar();
		pack();										//rectified collapse of window on restore down
	}

	private void setContentPane() {
		JPanel contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);
	}
	private void setMenuBar(){
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		//set Mnemonic 
		JMenuItem refreshMenuItem = new JMenuItem("Refresh", KeyEvent.VK_R);
		fileMenu.add(refreshMenuItem);
		refreshMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				SwingUtilities.updateComponentTreeUI(getRootPane());
			}
		});

		JMenuItem importMenuItem = new JMenuItem("Import", KeyEvent.VK_I);
		fileMenu.add(importMenuItem);
		importMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				FileChooser selfiles = new FileChooser();
				selfiles.setVisible(true);
				try {
					for(int i=0;i<values.length;i++)
						myListModel.addElement(values[i]);
				} catch (Exception e) {
					// Catch null pointer exception
					System.out.println(e.getMessage());
				}
			}
		});

		JMenuItem clearMenuItem = new JMenuItem("Clear list", KeyEvent.VK_C);
		fileMenu.add(clearMenuItem);
		clearMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myListModel.removeAllElements();
			}
		});

		JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
		fileMenu.add(exitMenuItem);
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				System.exit(0);
			}
		});

		JMenu themeMenu = new JMenu("Theme");
		menuBar.add(themeMenu);
		//ButtonGroup for grouping and adding exclusivity for themes RadioButtonMenuItem
		ButtonGroup themeMenuGroup = new ButtonGroup();

		JRadioButtonMenuItem themeMenuItem1 = new JRadioButtonMenuItem("Nimbus");
		themeMenuItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
						SwingUtilities.updateComponentTreeUI(getContentPane());
						SwingUtilities.updateComponentTreeUI(getJMenuBar());
					} catch (ClassNotFoundException | InstantiationException
							| IllegalAccessException
							| UnsupportedLookAndFeelException e1) {
						e1.printStackTrace();
					}
			}
		});
		themeMenuGroup.add(themeMenuItem1);
		themeMenu.add(themeMenuItem1);

		JRadioButtonMenuItem themeMenuItem2 = new JRadioButtonMenuItem("Motif");
		themeMenuItem2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
					SwingUtilities.updateComponentTreeUI(getContentPane());
					SwingUtilities.updateComponentTreeUI(getJMenuBar());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
			}
		});
		themeMenuGroup.add(themeMenuItem2);
		themeMenu.add(themeMenuItem2);

		JRadioButtonMenuItem themeMenuItem3 = new JRadioButtonMenuItem("System");
		themeMenuItem3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					SwingUtilities.updateComponentTreeUI(getContentPane());
					SwingUtilities.updateComponentTreeUI(getJMenuBar());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
			}
		});
		themeMenuGroup.add(themeMenuItem3);
		themeMenu.add(themeMenuItem3);
		JRadioButtonMenuItem themeMenuItem4 = new JRadioButtonMenuItem("Cross-Platform (Metal)");
		themeMenuItem4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					SwingUtilities.updateComponentTreeUI(getContentPane());
					SwingUtilities.updateComponentTreeUI(getJMenuBar());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					e1.printStackTrace();
				}
			}
		});
		themeMenuGroup.add(themeMenuItem4);
		themeMenu.add(themeMenuItem4);
		//set Nimbus theme selected by default
		themeMenuItem1.setSelected(true);

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem aboutMenuItem = new JMenuItem("About");
		helpMenu.add(aboutMenuItem);
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				About help = new About();
				help.setVisible(true);
			}
		});
	}

	private void setSearchBar() {
			menuBar.add(Box.createHorizontalStrut(400));		//For spacing between MenuItems
			JLabel searchLabel = new JLabel("Search");
			try {
				searchLabel.setIcon(new ImageIcon(MainWindowTesting.class.getResource(SEARCH_LOGO)));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			menuBar.add(searchLabel);

			JTextField searchField = new JTextField(5);
			searchField.setToolTipText("Enter search text and press Go");
			searchField.setText("Enter text to search");
			menuBar.add(searchField);
			searchField.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					searchField.setText(null);				//Clear field on selection
					searchField.setCaretPosition(0);		//set caret position to beginning of field
				}
			});

			JButton searchButton = new JButton("Go");
			searchButton.setToolTipText("Enter text and Click here");
			menuBar.add(searchButton);
			searchButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(myListModel.contains(searchField.getText())) {
			            int index = myListModel.indexOf(searchField.getText());
			            System.out.println(" found at index " + index);
			        } else {
			        	System.out.println(values[0]);
			        }
				}
			});
	}

	private void setStatusBar(){
		JPanel statusBar = new JPanel(new BorderLayout());
		statusBar.setName("StatusBar");
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		JProgressBar progressBar = new JProgressBar(0,100);
		statusBar.add(progressBar,BorderLayout.EAST);

	}

	private void setLooknFeel(String selectedtheme) {
		try {
			for(LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
				if(selectedtheme.equals(info.getName())){
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException | NullPointerException e) {
			e.printStackTrace();
		}
	}

	private void setCategoryPanel(){
		JPanel categoryPanel = new JPanel();
		categoryPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 139, 139), new Color(0, 139, 139), new Color(0, 139, 139), new Color(0, 139, 139)));
		categoryPanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(categoryPanel,BorderLayout.WEST);
		JScrollPane categoryScrollPane = new JScrollPane();
		categoryScrollPane.setViewportBorder(null);
		categoryPanel.add(categoryScrollPane);

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Categories");
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
		JTree categoryTree = new JTree(top);
		categoryTree.setVisibleRowCount(40);
		categoryScrollPane.setViewportView(categoryTree);
		// tree in categories panel ends
		//tree event listeners commence here
		categoryTree.addTreeExpansionListener(new TreeExpansionListener() {
			public void treeCollapsed(TreeExpansionEvent event) {
				System.out.println(event.getPath());
			}
			public void treeExpanded(TreeExpansionEvent event) {
				System.out.println(event.getPath());
			}
		});

		categoryTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent tse)
			{
				String selectedCat= String.valueOf(tse.getPath());
				int lsindex=selectedCat.lastIndexOf(", ");
				selectedCat=selectedCat.substring(lsindex+2,selectedCat.length()-1);
				System.out.println(selectedCat);

				try {
					values = core.FileOps.getFiles(selectedCat);
				}
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
				myListModel.removeAllElements();
				for(int i=0;i<values.length;i++)
					myListModel.addElement(values[i]);

			}
		});
		//tree event listener ends here
	}

	private void setItemsPanel(){
		JPanel itemsPanel = new JPanel();
		itemsPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 139, 139), new Color(0, 139, 139), new Color(0, 139, 139), new Color(0, 139, 139)));
		itemsPanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(itemsPanel, BorderLayout.CENTER);

		JScrollPane itemsScrollPane = new JScrollPane();
		itemsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		itemsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		itemsPanel.add(itemsScrollPane);
		itemsList = new JList<String>();
		itemsList.setBorder(new TitledBorder(null, "FILES", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 139, 139)));
		itemsList.setVisibleRowCount(40);
		itemsList.setLayoutOrientation(JList.VERTICAL);
		itemsList.setModel(myListModel);
		itemsList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int cc=e.getClickCount();
				if(cc==2){
					String selected= itemsList.getSelectedValue().toString();
					System.out.println("Mouse clicked on list");
					try {
						main.fileio.OpenFile.openFile(selected);
					}
					catch (IOException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		itemsScrollPane.setViewportView(itemsList);
	}
}