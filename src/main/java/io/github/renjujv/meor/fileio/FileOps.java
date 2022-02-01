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

import io.github.renjujv.meor.core.Database;
import io.github.renjujv.meor.entity.Category;

/**
 * @author HEXcube
 * 
 */
public class FileOps {
	private static final String categoriesTitle = "Categories";

	/**
	 * @param absoluteFolderPath directory path to be scanned to add files
	 * @throws Exception
	 */
	public static void addFolder(String absoluteFolderPath) throws Exception {
		Files.walkFileTree(Paths.get(absoluteFolderPath), new ProcessFile());
	}

	/**
	 * @param categoryName
	 * possible values: All,Media,Documents,Softwares,Audio,Video,Images,Rich Text,Spreadsheet,
	 * Presentation,eBooks,Web Pages,Windows,Linux,Mac,Android
	 * @return ArrayList of filesNames as string
	 * @throws Exception
	 */
	public static ArrayList<String> getFiles(String categoryName, String query) {
		Category category = new Category();
		ArrayList<String> filelist = new ArrayList<String>();
		Database database = new Database();
		boolean iscategory = false, allcategories = false;
		if (categoryName.equals(categoriesTitle))
			allcategories = true;
		for (int i = 0; i < category.getCategories().length; i++) {
			if (categoryName.equals(category.getCategories()[i]))
				iscategory = true;
			for (int j = 0; !category.getSubCategories()[i][j].equals("."); j++)
				if (categoryName.equals(category.getSubCategories()[i][j]) || iscategory
						|| allcategories)
					for (int k = 0; !category.getExtensions()[i][j][k].equals("."); k++)
						filelist.addAll(database.retrieve(category.getExtensions()[i][j][k], query));
			if (iscategory)
				break;
		}
		database.closeConnection();
		return filelist;
	}

	/**
	 *
	 * @param filepath Open the provided file using System according to the filename extension
	 * @throws IOException
	 */
	public static void openFile(String filepath) throws IOException {
		Desktop.getDesktop().open(new File(filepath));
	}
}
