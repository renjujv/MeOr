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
package io.github.renjujv.meor.ui;

import io.github.renjujv.meor.fileio.FileChooser;
import io.github.renjujv.meor.fileio.FileOps;

import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author RenJOuS
 * 
 *  */
class MainWindow extends JFrame{
	private final Color borderHighlightColor = new Color(0, 139, 139, 126);
	private final Color borderShadowColor = new Color(0, 79, 79, 121);
	private final DefaultListModel<String> listModel = new DefaultListModel<>();
	private String[] allFilesArray;
	private ArrayList<String> currentFilesArray;
	private boolean filteredList = false;
	private String filterQuery = null;

	public static void main(String[] args) {
		MainWindow mainUI = new MainWindow();
		EventQueue.invokeLater(() -> {
			mainUI.initComponents();
			mainUI.setVisible(true);
		});
	}
	
	public void initComponents(){
		String APP_ICON_PATH = "/meor_icon.png";
		URL appIconURL = Objects.requireNonNull(MainWindow.class.getResource(APP_ICON_PATH));
		setIconImage(Toolkit.getDefaultToolkit().getImage(appIconURL));

		setTitle("MeOr - Media Organiser");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLooknFeel("System");

		JPanel contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);

		JMenuBar menuBar = setMenuBar();
		setSearchBar(menuBar);

		setCategoryPanel();
		setItemsPanel();
		setStatusBar();

		pack();			//fixed collapse of window on restore down
	}

	public void setCategoryPanel(){
		JPanel categoryPanel = new JPanel();
		BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED, borderHighlightColor, borderShadowColor);
		categoryPanel.setBorder(bevelBorder);
		categoryPanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(categoryPanel, BorderLayout.WEST);

		JScrollPane categoryScrollPane = new JScrollPane();
		categoryScrollPane.setViewportBorder(null);
		categoryPanel.add(categoryScrollPane);

		JTree categoryTree = new JTree(addCategoriesAndSubCategories());
		categoryTree.setVisibleRowCount(40);
		categoryScrollPane.setViewportView(categoryTree);
		addTreeEventListeners(categoryTree);
	}

	private void addTreeEventListeners(JTree categoryTree) {
		categoryTree.addTreeSelectionListener(treeSelectionEvent -> {
			String selectedCategory = String.valueOf(treeSelectionEvent.getPath());
			int lsindex = selectedCategory.lastIndexOf(", ");
			selectedCategory = selectedCategory.substring(lsindex+2, selectedCategory.length()-1);
			try {
				currentFilesArray = FileOps.getFiles(selectedCategory, filterQuery);
				listModel.removeAllElements();
				listModel.addAll(currentFilesArray);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
	}

	private DefaultMutableTreeNode addCategoriesAndSubCategories() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Categories");
		DefaultMutableTreeNode media = new DefaultMutableTreeNode("Media");
		DefaultMutableTreeNode software = new DefaultMutableTreeNode("Softwares");
		DefaultMutableTreeNode document = new DefaultMutableTreeNode("Documents");
		DefaultMutableTreeNode audio = new DefaultMutableTreeNode("Audio");
		DefaultMutableTreeNode video = new DefaultMutableTreeNode("Video");
		DefaultMutableTreeNode image = new DefaultMutableTreeNode("Images");
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
		media.add(image);

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

		return top;
	}

	public void setItemsPanel(){
		JPanel itemsPanel = new JPanel();
		BevelBorder bevelBorder = new BevelBorder(BevelBorder.RAISED, borderHighlightColor, borderShadowColor);
		itemsPanel.setBorder(bevelBorder);
		itemsPanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(itemsPanel, BorderLayout.CENTER);

		JScrollPane itemsScrollPane = new JScrollPane();
		itemsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		itemsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		itemsPanel.add(itemsScrollPane);
		JList<String> itemsList = new JList<>();
		String borderTitle = "FILES";
		TitledBorder titledBorder = new TitledBorder(null, borderTitle, TitledBorder.LEADING, TitledBorder.TOP,
				null, borderHighlightColor);
		itemsList.setBorder(titledBorder);
		itemsList.setVisibleRowCount(40);
		itemsList.setLayoutOrientation(JList.VERTICAL);
		itemsList.setModel(listModel);
		addDoubleClickEventListenerForItemsList(itemsList);
		itemsScrollPane.setViewportView(itemsList);
	}

	private void addDoubleClickEventListenerForItemsList(JList<String> itemsList) {
		itemsList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clickCount = e.getClickCount();
				if(clickCount == 2) {
					String selected= itemsList.getSelectedValue();
					System.out.println("Mouse clicked on list");
					try {
						FileOps.openFile(selected);
					}
					catch (IOException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
	}

	public JMenuBar setMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		addFileMenu(menuBar);
		addThemeMenu(menuBar);
		addHelpMenu(menuBar);

		return menuBar;
	}

	private void addThemeMenu(JMenuBar menuBar) {
		JMenu themeMenu = new JMenu("Theme");
		menuBar.add(themeMenu);

		//ButtonGroup for grouping and adding exclusivity for themes RadioButtonMenuItem
		ButtonGroup themeMenuGroup = new ButtonGroup();

		JRadioButtonMenuItem nimbusThemeMenuItem = new JRadioButtonMenuItem("Nimbus");
		addThemeMenuItemEventListener(nimbusThemeMenuItem, "javax.swing.plaf.nimbus.NimbusLookAndFeel");
		themeMenuGroup.add(nimbusThemeMenuItem);
		themeMenu.add(nimbusThemeMenuItem);

		JRadioButtonMenuItem MotifThemeMenuItem = new JRadioButtonMenuItem("Motif");
		addThemeMenuItemEventListener(MotifThemeMenuItem, "com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		themeMenuGroup.add(MotifThemeMenuItem);
		themeMenu.add(MotifThemeMenuItem);

		JRadioButtonMenuItem SystemThemeMenuItem = new JRadioButtonMenuItem("System");
		addThemeMenuItemEventListener(SystemThemeMenuItem, UIManager.getSystemLookAndFeelClassName());
		themeMenuGroup.add(SystemThemeMenuItem);
		themeMenu.add(SystemThemeMenuItem);

		JRadioButtonMenuItem XPlatformThemeMenuItem = new JRadioButtonMenuItem("Cross-Platform (Metal)");
		addThemeMenuItemEventListener(XPlatformThemeMenuItem, UIManager.getCrossPlatformLookAndFeelClassName());
		themeMenuGroup.add(XPlatformThemeMenuItem);
		themeMenu.add(XPlatformThemeMenuItem);

		nimbusThemeMenuItem.setSelected(true);
	}

	private void addThemeMenuItemEventListener(JRadioButtonMenuItem ThemeMenuItem, String LookAndFeelClassName) {
		ThemeMenuItem.addActionListener(actionEvent -> {
			try {
				UIManager.setLookAndFeel(LookAndFeelClassName);
				SwingUtilities.updateComponentTreeUI(getContentPane());
				SwingUtilities.updateComponentTreeUI(getJMenuBar());
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException
					| UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
		});
	}

	private void addHelpMenu(JMenuBar menuBar) {
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem aboutMenuItem = new JMenuItem("About");
		helpMenu.add(aboutMenuItem);
		aboutMenuItem.addActionListener(actionEvent -> {
			About help = new About();
			help.setVisible(true);
		});
	}

	private void addFileMenu(JMenuBar menuBar) {
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		JMenuItem refreshMenuItem = new JMenuItem("Refresh", KeyEvent.VK_R);
		fileMenu.add(refreshMenuItem);
		refreshMenuItem.addActionListener(actionEvent -> SwingUtilities.updateComponentTreeUI(getRootPane()));

		JMenuItem importMenuItem = new JMenuItem("Import", KeyEvent.VK_I);
		fileMenu.add(importMenuItem);
		addImportFilesMenuItemListener(importMenuItem);

		JMenuItem clearMenuItem = new JMenuItem("Clear list", KeyEvent.VK_C);
		fileMenu.add(clearMenuItem);
		clearMenuItem.addActionListener(actionEvent -> listModel.removeAllElements());

		JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
		fileMenu.add(exitMenuItem);
		exitMenuItem.addActionListener(actionEvent -> System.exit(0));
	}

	private void addImportFilesMenuItemListener(JMenuItem importMenuItem) {
		importMenuItem.addActionListener(actionEvent -> new SwingWorker<>() {
			@Override
			protected Object doInBackground() {
				try {
					FileChooser filechooser = new FileChooser();
					filechooser.setVisible(true);					// long process moved to worker thread
					for (String value : currentFilesArray) listModel.addElement(value);
				} catch (Exception exception) {
					System.out.println(exception.getMessage());
				}
				return null;
			}
		}.execute());
	}

	public void setSearchBar(JMenuBar menuBar) {
		String SEARCH_LOGO = "/search.png";
		menuBar.add(Box.createHorizontalStrut(400));				//For spacing between MenuItems

		JLabel searchLabel = new JLabel("Search");
		URL iconURL = Objects.requireNonNull(MainWindow.class.getResource(SEARCH_LOGO));
		searchLabel.setIcon(new ImageIcon(iconURL));
		menuBar.add(searchLabel);

		JTextField searchField = new JTextField(20);
		searchField.setToolTipText("Enter search text and press Go");
		menuBar.add(searchField);

		JButton searchButton = new JButton("Go");
		searchButton.setToolTipText("Enter text and Click here");
		menuBar.add(searchButton);

		searchButton.addActionListener( actionEvent -> {
			filterQuery = searchField.getText();
			filteredList = !filterQuery.equals("");
			System.out.println("Filterquery: "+filterQuery);
			ArrayList<String> listElements = Collections.list(listModel.elements());
			ArrayList<String> filteredListElements = new ArrayList<>();
			for(String currentElement: listElements) {
				if(currentElement.contains(filterQuery)) filteredListElements.add(currentElement);
			}
			listModel.removeAllElements();
			System.out.println("Elements removed..");
			listModel.addAll(filteredListElements);
			System.out.println("Filtered Elements added..");
		});
	}

	public void setStatusBar(){
		JPanel statusBar = new JPanel(new BorderLayout());
		statusBar.setName("StatusBar");
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		JProgressBar progressBar = new JProgressBar(0,100);
		statusBar.add(progressBar,BorderLayout.EAST);
	}

	private void setLooknFeel(String selectedtheme) {
		try {
			for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
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
}