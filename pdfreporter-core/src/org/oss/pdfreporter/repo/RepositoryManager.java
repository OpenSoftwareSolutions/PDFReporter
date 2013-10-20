/*******************************************************************************
 * Copyright (c) 2013 Open Software Solutions GmbH.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.html
 * 
 * Contributors:
 *     Open Software Solutions GmbH - initial API and implementation
 ******************************************************************************/
package org.oss.pdfreporter.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RepositoryManager {
	private final static Logger logger = Logger.getLogger(RepositoryManager.class.getName());
	
	public static final String PATH_DELIMITER = "/";
	private final static RepositoryManager INSTANCE = new RepositoryManager(); 
	private final static String DEFAULT_RESOURCE_FOLDER = "./resource/";
	private final static String DEFAULT_REPORT_FOLDER = "./data/";
	
	private final List<String> repositoryFolders;
	
	private RepositoryManager() {
		this.repositoryFolders = new ArrayList<String>();
		reset();
	}

	public static RepositoryManager getInstance() {
		return INSTANCE;
	}
	
	public List<String> getRepositoryFolders() {
		ensureDefaultFoldersSet();
		return repositoryFolders;
	}
	
	public void setDefaultResourceFolder(String folder) {
		repositoryFolders.set(0, folder);
	}
	
	public String getDefaultResourceFolder() {
		return repositoryFolders.get(0);
	}
	
	public void setDefaulReportFolder(String folder) {
		repositoryFolders.set(1, folder);
	}
	
	public String getDefaulReportFolder() {
		return repositoryFolders.get(1);
	}
	
	public void addExtraReportFolder(String folder) {
		if (folder!=null) {			
			repositoryFolders.add(folder);
		}
	}
	
	public void reset() {
		repositoryFolders.clear();
		repositoryFolders.add(null); 
		repositoryFolders.add(null); 
	}
	
	private void ensureDefaultFoldersSet() {
		if (getDefaultResourceFolder()==null) {
			logger.warning("Set Resourcefolder to '" + DEFAULT_RESOURCE_FOLDER + "', you should call setDefaultResourceFolder()." );
			setDefaultResourceFolder(DEFAULT_RESOURCE_FOLDER);
		}
		if (getDefaulReportFolder()==null) {
			logger.warning("Set Reportfolder to '" + DEFAULT_REPORT_FOLDER + "', you should call setDefaulReportFolder()." );
			setDefaulReportFolder(DEFAULT_REPORT_FOLDER);
		}
	}

	@Override
	public String toString() {
		return "Open Software SolutionsRepositoryManager [repositoryFolders="
				+ repositoryFolders + "]";
	}
}
