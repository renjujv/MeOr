package main.fileIO;
import java.awt.EventQueue;

import javax.swing.JFileChooser;

/**
 * @author RenJOuS
 *
 */
@SuppressWarnings("serial")
public class FileChooser extends JFileChooser
{

	//private JPanel contentPane;

	/**
	 * Launch the application.
	 * @param args 
	 */
	public static void main(String[] args)
		{
			EventQueue.invokeLater(new Runnable() {
				public void run()
					{
						try {
							FileChooser chooser = new FileChooser();
							chooser.setVisible(true);
						}
						catch (Exception e) {
						//	e.printStackTrace();
						}
					}
			});
		}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	
	
	
	
	
	
	
	
	public FileChooser() {
    setCurrentDirectory(new java.io.File("."));
    setDialogTitle("File Import");
    setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    setAcceptAllFileFilterUsed(false);

    if (showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
      System.out.println("getCurrentDirectory(): " + getCurrentDirectory());
      System.out.println("getSelectedFile() : " + getSelectedFile().getAbsolutePath());
      try {
			core.FileOps.addFolder(getSelectedFile().getAbsolutePath());
		}
		catch (Exception e) {
		//	e.printStackTrace();
		}
    }
    else {
      System.out.println("No Selection ");
    }
	}
}
         
