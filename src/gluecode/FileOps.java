/**
 * 
 */
package gluecode;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import database.DataBase;

/**
 * @author hexcube
 * 
 */
public class FileOps {

	public static void addFolder(String FOLDER) throws Exception {
		Files.walkFileTree(Paths.get(FOLDER),new ProcessFile());
	}

	private static final class ProcessFile extends SimpleFileVisitor<Path> {
		DataBase database = new DataBase();

		ProcessFile() throws Exception {
			database.create();
		}

		@Override
		public FileVisitResult visitFile(Path aFile, BasicFileAttributes aAttrs)
				throws IOException {
			try {
				database.insert(aFile, aAttrs.size());
			} catch (Exception ignore) {
				ignore.printStackTrace();
			}
			return FileVisitResult.CONTINUE;
		}
		
		protected void finalize(){
			database.closeConnection();
		}	
	}

	public static String[] getFiles(String category) throws Exception {
		String[] categories = { "Music", "Videos", "Images", "Softwares" };
		String[][] extensions = {
				{ "mp3", "aac", "ogg", "flac", "m4a", "wma", "wav", "ape", "." },
				{ "avi", "mkv", "wma", "webm", "flv", "mp4", "3gp", ".", },
				{ "exe", "msi", "deb", "rpm", "app", ".", },
				{ "jpeg", "jpg", "png", "gif", "." } };
		List<String> filelist = new ArrayList<String>();
		DataBase database = new DataBase();
		for (int i = 0; i < categories.length; i++)
			if (category.equals(categories[i]))
				for (int j = 0; !extensions[i][j].equals("."); j++)
					filelist.addAll(database.retrieve(extensions[i][j]));
		String[] files = filelist.toArray(new String[filelist.size()]);
		database.closeConnection();
		return files;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		getFiles("Music");
	}

}
