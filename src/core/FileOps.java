/**
 * 
 */
package core;

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
	
	public static final String[] categories = {"Media","Documents","Softwares"};
	public static final String[][] subcategories = {
			{"Audio","Video","Images","."},
			{"Rich Text","Spreadsheet","Presentation","eBooks","Web Pages","."},
			{"Windows","Linux","Mac","Android","."}};
	private static final String[][][] extensions = {
			{
				{ "mp3", "aac", "ogg", "flac", "m4a", "wma", "wav", "ape", "." },
				{ "avi", "mkv", "wmv", "webm", "flv", "mp4", "3gp", "." },
				{ "jpeg", "jpg", "png", "gif", "." }
			},
			{
				{ "pdf", "doc", "txt", "odt", "." },
				{ "ppt", "pptx","odp", "." },
				{ "xls", "odg", "." },
				{ "ePub", "." },
				{ "htm", "html","xhtml","mht","." }
			},
			{
				{ "exe", "msi", "." },
				{ "deb", "rpm", "run", "mint","app", "." },
				{ "app", "."},
				{ "apk", "."}
			}
			};
	
	private static DataBase database;
	
	/**
	 * @param FOLDER absolute path of folder to be added
	 * @throws Exception
	 */
	public static void addFolder(String FOLDER) throws Exception {
		Files.walkFileTree(Paths.get(FOLDER), new ProcessFile());
	}

	/**
	 * @author hexcube
	 *
	 */
	private static final class ProcessFile extends SimpleFileVisitor<Path> {
		private DataBase dbase = new DataBase();

		ProcessFile() throws Exception {
			dbase.create();
		}

		@Override
		public FileVisitResult visitFile(Path aFile, BasicFileAttributes aAttrs)
				throws IOException {
			try {
				dbase.insert(aFile, aAttrs.size());
			} catch (Exception ignore) {
				ignore.printStackTrace();
			}
			return FileVisitResult.CONTINUE;
		}

		protected void finalize() {
			dbase.closeConnection();
		}
	}

	/**
	 * @param category possible values:All,Media,Documents,Softwares,Audio,Video,Images,Rich Text,Spreadsheet,Presentation,eBooks,Web Pages,Windows,Linux,Mac,Android
	 * @return
	 * @throws Exception
	 */
	public static String[] getFiles(String category) throws Exception{
		List<String> filelist = new ArrayList<String>();
		database = new DataBase();
		boolean iscategory = false,allcategories=false;
		if(category.equals("Categories"))
			allcategories=true;
		for(int i=0; i < categories.length; i++)
			{
			if (category.equals(categories[i]))
				iscategory = true;
			for (int j = 0; !subcategories[i][j].equals("."); j++)
				if (category.equals(subcategories[i][j]) || iscategory || allcategories)
					for (int k = 0; !extensions[i][j][k].equals("."); k++)
						filelist.addAll(database.retrieve(extensions[i][j][k]));
			if(iscategory)
				break;
			}
		database.closeConnection();
		String[] files = filelist.toArray(new String[filelist.size()]);
		return files;
	}
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String files[]=getFiles("Categories");
		for(int i=0;i<files.length;i++)
		System.out.println(files[i]);
	}

}
