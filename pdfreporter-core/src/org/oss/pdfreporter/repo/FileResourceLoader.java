package org.oss.pdfreporter.repo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.oss.pdfreporter.net.FileUrl;
import org.oss.pdfreporter.net.IURL;
import org.oss.pdfreporter.net.MalformedURLException;
import org.oss.pdfreporter.registry.IRegistry;


public class FileResourceLoader {
	private static final Logger logger = Logger.getLogger(FileResourceLoader.class.getName());
	private final static String URL_MATCH = "^[a-z]+://.*";
	
	public static InputStream getInputStream(String resourceName) {
		try {
			IURL url = getURL(resourceName);
			return url==null ? null : url.openStream();
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Error loading resource: " + resourceName, e);
			return null;
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Failed to open connection: " + resourceName, e);
			return null;
		}
	}

	public static IURL getURL(String resourceName) {
		try {
			// TODO (17.05.2013, Martin, Open Software Solutions): The regular expression fails with an error , find out why
			if (resourceName.matches(URL_MATCH)) {
				return IRegistry.getINetFactory().newURL(resourceName);
			}
//			String resource = resourceName.replace("\"", ""); // remove quotes
			IURL url = findFirstConfiguredFileResource(resourceName);
			return url==null ? null : url;
		} catch (MalformedURLException e) {
			logger.log(Level.SEVERE, "Invalid URL: " + resourceName, e);
			return null;
		} 
	}
	
	public static List<IURL> getConfiguredFileResources() {
		return new DelegatingUrlList(findFiles(RepositoryManager.getInstance().getRepositoryFolders(), new AccepptAll(),false));
	}
	
	public static List<IURL> findConfiguredFileResources(String resource) {
		return new DelegatingUrlList(findFiles(RepositoryManager.getInstance().getRepositoryFolders(), new FileResourceFilter(resource),false));
	}
	
	public static List<FileSystemResource> findConfiguredFileSystemResources(String resource) {
		return new DelegatingFileSystemResourceList(findFiles(RepositoryManager.getInstance().getRepositoryFolders(), new FileResourceFilter(resource),false));
	}

	public static IURL findFirstConfiguredFileResource(String resource) {
		List<File> files = findFiles(RepositoryManager.getInstance().getRepositoryFolders(), new FileResourceFilter(resource),true);
		return files.isEmpty() ? null : new FileUrl(files.get(0));
	}
	
	
	private static List<File> findFiles(List<String> rootFolders, FilenameFilter filter, boolean onlyFirst) {
		List<File> resultAll = new ArrayList<File>();
		for (String folder : rootFolders) {
			List<File> result = findFiles(new File(folder),filter,onlyFirst);
			if (!result.isEmpty() && onlyFirst) {
				resultAll.add(result.get(0));
				break;
			}
			resultAll.addAll(result);
		}
		logger.finest("find resource: " + filter + " in " + rootFolders.toString() + ", found: " + resultAll.toString());
		return resultAll;
	}
	
	private static List<File> findFiles(File folder, FilenameFilter filter, boolean onlyFirst) {
		List<File> resources = new ArrayList<File>();
		if (folder.exists()) {			
			for (File f : folder.listFiles()) {
				if (f.isFile()) {
					if (filter.accept(folder, f.getName())) {						
						resources.add(f);
					}
				} else {
					resources.addAll(findFiles(f,filter,onlyFirst));
				}
				if (onlyFirst && !resources.isEmpty()) {
					break;
				}
			}
		} else {
			logger.warning("Resource folder: " + folder.getName() + " does not exist.");
		}
		return resources;
	}
	
	private static class FileResourceFilter implements FilenameFilter {

		private final String path, file;
		
		public FileResourceFilter(String resource) {
			super();
			int idx = resource.lastIndexOf(RepositoryManager.PATH_DELIMITER);
			if (idx>=0) {
				file = resource.substring(idx + 1);
				path = resource.substring(0,idx);
			} else {
				file = resource;
				path = null;
			}
		}

		@Override
		public boolean accept(File dir, String name) {
			return path==null ? name.equals(file) : dir.getPath().endsWith(path) && name.equals(file);
		}

		@Override
		public String toString() {
			return (path==null ? file : path + RepositoryManager.PATH_DELIMITER + file);
		}
	}
	
	private static class AccepptAll implements FilenameFilter {

		@Override
		public boolean accept(File dir, String name) {
			return true;
		}

		@Override
		public String toString() {
			return "*.*";
		}
		
	}
	
	private static class DelegatingUrlList extends AbstractList<IURL> {

		private final List<File> delegate;
		
		public DelegatingUrlList(List<File> delegate) {
			super();
			this.delegate = delegate;
		}

		@Override
		public IURL get(int index) {
			return new FileUrl(delegate.get(index));
		}

		@Override
		public int size() {
			return delegate.size();
		}
	}

	private static class DelegatingFileSystemResourceList extends AbstractList<FileSystemResource> {

		private final List<File> delegate;
		
		public DelegatingFileSystemResourceList(List<File> delegate) {
			super();
			this.delegate = delegate;
		}

		@Override
		public FileSystemResource get(int index) {
			return new FileSystemResource(delegate.get(index));
		}

		@Override
		public int size() {
			return delegate.size();
		}
	}

}
