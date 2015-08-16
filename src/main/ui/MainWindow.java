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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.Box;
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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import main.fileio.FileChooser;
import javax.swing.UIManager;

/**
 * @author RenJOuS
 * 
 */
@SuppressWarnings("serial")
// GUI Class MainFrame
class MainWindow extends JFrame {
	private JTextField searchField;
	private JButton btnGo;
	private JLabel lblSearch;
	private JTree categtree;
	private DefaultListModel<Object> mylistmodel = new DefaultListModel<Object>();
	private JPanel itemspanel;
	private String[] values;
	private static final String APP_ICON_PATH = "/resources/meor-icon.png";

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {// exception handling for MainWindow
				try {
					MainWindow GUI = new MainWindow();
					GUI.setVisible(true);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
		});
	}

	// Constructor
	MainWindow() throws Exception {

		try {
			setIconImage(Toolkit.getDefaultToolkit().getImage(
					MainWindow.class.getResource(APP_ICON_PATH)));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		setTitle("MeOr - Media Organiser");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setMenuBar();
		setStatBar();
		// setThemeSel();
		setCategpanel();
		setItemspanel();
	}

	private void setMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setSize(getWidth(), 25);
		getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem refreshmnItem = new JMenuItem("Refresh");
		fileMenu.add(refreshmnItem);
		refreshmnItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mylistmodel.lastElement();
			}
		});

		JMenuItem importmnItem = new JMenuItem("Import");
		importmnItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileChooser selfiles = new FileChooser();
				selfiles.setVisible(true);
				try {
					for (int i = 0; i < values.length; i++)
						mylistmodel.addElement(values[i]);
					//
					// Thread t1 = new Thread(new Runnable() {
					// public void run() {
					// for(int i=0;i<values.length;i++)
					// mylistmodel.addElement(values[i]);
					// }
					// });
					// t1.start();

				} catch (Exception e) {
					// Catch null pointer exception
					System.out.println(e.getMessage());
				}
			}
		});
		fileMenu.add(importmnItem);
		JMenuItem clearlistmnItem = new JMenuItem("Clear list");
		fileMenu.add(clearlistmnItem);
		clearlistmnItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mylistmodel.removeAllElements();
			}
		});

		JMenuItem exitmnItem = new JMenuItem("Exit");
		fileMenu.add(exitmnItem);
		exitmnItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);

		JMenuItem aboutmnItem = new JMenuItem("About");
		helpMenu.add(aboutmnItem);
		aboutmnItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				About help = new About();
				help.setVisible(true);
				help.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});

		menuBar.add(Box.createRigidArea(new Dimension(400, 5)));

		lblSearch = new JLabel("Search");
		lblSearch.setVisible(true);
		lblSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSearch.setIcon(new ImageIcon(MainWindow.class
				.getResource("/resources/search.png")));
		menuBar.add(lblSearch);

		searchField = new JTextField();
		searchField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				searchField.setText(null);
			}
		});
		searchField.setToolTipText("Search");
		searchField.setText("Enter title");
		// searchField.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(searchField);
		searchField.setColumns(5);

		btnGo = new JButton("Go");
		btnGo.setVisible(true);
		btnGo.setHorizontalAlignment(SwingConstants.RIGHT);
		btnGo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(values[0]);
				// for(int i=0;i<values.length;i++){
				// mylistmodel.addElement(values[i]);
				// }

			}
		});
		menuBar.add(btnGo);
	}

	private void setStatBar() {
		JPanel statBar = new JPanel(new BorderLayout());
		statBar.setName("StatusBar");
		getContentPane().add(statBar, BorderLayout.SOUTH);
		JProgressBar progressBar = new JProgressBar(0, 100);
		statBar.add(progressBar, BorderLayout.EAST);

	}

	// commented theme selector for future implementation!!
	{
		// public void setThemeSel(){
		// // theme selector
		// JLabel lblTheme = new JLabel("Theme");
		// lblTheme.setVerticalAlignment(SwingConstants.TOP);
		// lblTheme.setHorizontalAlignment(SwingConstants.TRAILING);
		// menuBar.add(lblTheme);
		//
		// JRadioButton rdbtnnative = new JRadioButton("Native");
		// rdbtnnative.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0)
		// {
		// try {
		// UIManager.setLookAndFeel("Nimbus");
		// }
		// catch (ClassNotFoundException | InstantiationException
		// | IllegalAccessException | UnsupportedLookAndFeelException e) {
		// System.out.println(e.getMessage());
		// }
		// SwingUtilities.updateComponentTreeUI(getComponent(4));
		// ((Window) getContentPane()).pack();
		// }
		// });
		// rdbtnnative.setSelected(true);
		// rdbtnnative.setVerticalAlignment(SwingConstants.TOP);
		// rdbtnnative.setHorizontalAlignment(SwingConstants.TRAILING);
		// buttonGroup.add(rdbtnnative);
		// menuBar.add(rdbtnnative);
		//
		// JRadioButton rdbtncrossplat = new JRadioButton("Cross Platform");
		// rdbtncrossplat.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0)
		// {
		// try {
		// UIManager.setLookAndFeel("Windows");
		// }
		// catch (ClassNotFoundException | InstantiationException
		// | IllegalAccessException | UnsupportedLookAndFeelException e) {
		// System.out.println(e.getMessage());
		// }
		// SwingUtilities.updateComponentTreeUI(getContentPane());
		// ((Window) getContentPane()).pack();
		// }
		// });
		// rdbtnnative.setVerticalAlignment(SwingConstants.TOP);
		// rdbtncrossplat.setHorizontalAlignment(SwingConstants.TRAILING);
		// buttonGroup.add(rdbtncrossplat);
		// menuBar.add(rdbtncrossplat);
		// // theme selector ends
		// }
	}

	private void setCategpanel() {
		final JPanel categpanel = new JPanel();
		categpanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(categpanel, BorderLayout.WEST);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setPreferredSize(new Dimension(150, getHeight()));
		categpanel.add(scrollPane);

		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Categories");
		categtree = new JTree(top);
		categtree.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		DefaultMutableTreeNode media = new DefaultMutableTreeNode("Media");
		DefaultMutableTreeNode software = new DefaultMutableTreeNode(
				"Softwares");
		DefaultMutableTreeNode document = new DefaultMutableTreeNode(
				"Documents");
		DefaultMutableTreeNode audio = new DefaultMutableTreeNode("Audio");
		DefaultMutableTreeNode video = new DefaultMutableTreeNode("Video");
		DefaultMutableTreeNode windows = new DefaultMutableTreeNode("Windows");
		DefaultMutableTreeNode linux = new DefaultMutableTreeNode("Linux");
		DefaultMutableTreeNode macintosh = new DefaultMutableTreeNode("Mac");
		DefaultMutableTreeNode android = new DefaultMutableTreeNode("Android");
		DefaultMutableTreeNode other = new DefaultMutableTreeNode("Others");
		DefaultMutableTreeNode spreadsheet = new DefaultMutableTreeNode(
				"Spreadsheets");
		DefaultMutableTreeNode ebook = new DefaultMutableTreeNode("eBooks");
		DefaultMutableTreeNode text = new DefaultMutableTreeNode("Rich Text");
		DefaultMutableTreeNode presentation = new DefaultMutableTreeNode(
				"Presentations");
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
		// categtree.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(categtree);
		// tree in categories panel ends
		// tree event listeners commence here
		categtree.addTreeExpansionListener(new TreeExpansionListener() {
			@Override
			public void treeCollapsed(TreeExpansionEvent event) {
				System.out.println(event.getPath());
			}

			@Override
			public void treeExpanded(TreeExpansionEvent event) {
				System.out.println(event.getPath());
			}
		});

		categtree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent tse) {
				String selectedCat = String.valueOf(tse.getPath());
				int lsindex = selectedCat.lastIndexOf(", ");
				selectedCat = selectedCat.substring(lsindex + 2,
						selectedCat.length() - 1);
				System.out.println(selectedCat);

				try {
					values = core.FileOps.getFiles(selectedCat);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				mylistmodel.removeAllElements();
				for (int i = 0; i < values.length; i++)
					mylistmodel.addElement(values[i]);

			}
		});
		// tree event listener ends here
	}

	private void setItemspanel() {
		itemspanel = new JPanel();
		itemspanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		itemspanel.setLayout(new BorderLayout(0, 0));
		getContentPane().add(itemspanel, BorderLayout.CENTER);

		JScrollPane itemscrollPane = new JScrollPane();
		itemscrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		itemscrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		itemspanel.add(itemscrollPane);
		final JList<Object> list = new JList<Object>();
		/*
		 * list.addPropertyChangeListener(new PropertyChangeListener() { public
		 * void propertyChange(PropertyChangeEvent evt) {
		 * //mylistmodel.addElement(); //mylistmodel.addElement(values);
		 * 
		 * } });
		 */list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(30);
		list.setModel(mylistmodel);

		// {
		// // String[] values = new String[] {"fg", "fgu", "ghg"};
		//
		// String[] values = gluecode.FileOps.getFiles("Music");
		// public int getSize()
		// {
		// return values.length;
		// }
		// public Object getElementAt(int index)
		// {
		// return values[index];
		// }
		// });
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int cc = e.getClickCount();
				if (cc == 2) {
					String selected = list.getSelectedValue().toString();
					System.out.println("Mouse clicked on list");
					try {
						main.fileio.OpenFile.openFile(selected);
					} catch (IOException e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		itemscrollPane.setViewportView(list);
	}

}