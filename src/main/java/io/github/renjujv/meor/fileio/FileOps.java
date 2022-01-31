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
package io.github.renjujv.meor.fileio;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import io.github.renjujv.meor.database.DataBase;

/**
 * @author HEXcube
 * 
 */
public class FileOps {

	public static final String[] categories = { "Media", "Documents", "Softwares" };
	public static final String[][] subcategories = {
		{ "Audio", "Video", "Images", "." },
		{ "Rich Text", "Spreadsheet", "Presentation", "eBooks", "Web Pages", "." },
		{ "Windows", "Linux", "Mac", "Android", "." } };
	private static final String[][][] extensions = {
		{
			{ "mp3", "aac", "ogg", "flac", "m4a", "wma", "wav", "ape", "." },
			{ "avi", "mkv", "wmv", "webm", "flv", "mp4", "3gp", "." },
			{ "jpeg", "jpg", "png", "gif", "." }
		},
		{
			{ "pdf", "doc", "txt", "odt", "." },
			{ "ppt", "pptx", "odp", "." },
			{ "xls", "odg", "." },
			{ "ePub", "." },
			{ "htm", "html", "xhtml", "mht", "." }
		},
		{
			{ "exe", "msi", "." },
			{ "deb", "rpm", "run", "mint", "app", "." },
			{ "app", "." },
			{ "apk", "." }
		}
	};

	/**
	 * @param FOLDER
	 *            absolute path of folder to be added
	 * @throws Exception
	 */
	public static void addFolder(String FOLDER) throws Exception {
		Files.walkFileTree(Paths.get(FOLDER), new ProcessFile());
	}

	/**
	 * @param category
	 *            possible
	 *            values:All,Media,Documents,Softwares,Audio,Video,Images,Rich
	 *            Text,Spreadsheet,Presentation,eBooks,Web
	 *            Pages,Windows,Linux,Mac,Android
	 * @return
	 * @throws Exception
	 */
	public static String[] getFiles(String category) throws Exception {
		List<String> filelist = new ArrayList<String>();
		DataBase database = new DataBase();
		boolean iscategory = false, allcategories = false;
		if (category.equals("Categories"))
			allcategories = true;
		for (int i = 0; i < categories.length; i++) {
			if (category.equals(categories[i]))
				iscategory = true;
			for (int j = 0; !subcategories[i][j].equals("."); j++)
				if (category.equals(subcategories[i][j]) || iscategory
						|| allcategories)
					for (int k = 0; !extensions[i][j][k].equals("."); k++)
						filelist.addAll(database.retrieve(extensions[i][j][k]));
			if (iscategory)
				break;
		}
		database.closeConnection();
		return filelist.toArray(new String[0]);
	}

	public static void openFile(String filepath) throws IOException {
		Desktop.getDesktop().open(new File(filepath));
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
//		String[] files = getFiles("Categories");
//		for (String file : files) System.out.println(file);
	}
}
