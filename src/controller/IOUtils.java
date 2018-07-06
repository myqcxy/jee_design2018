package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class IOUtils {
	String realpath;
	
	public void setRealpath(String realpath) {
		this.realpath = realpath;
	}
	public IOUtils(String relativePath) {
		String realpath = ServletActionContext.getServletContext().getRealPath(relativePath);
		this.realpath = realpath;
	}
	public void fileCopy(File file,String fileName) throws IOException{
		
		File f = new File(realpath);
		if(!f.exists()){ f.mkdir();}
		FileUtils.copyFile(file, new File(f,fileName));
		
	}
	public String filesCopy(File []files,String []filesName) throws IOException{
		String urls = "";
		for(int i=0;i<files.length;i++){
			fileCopy(files[i],filesName[i]);
			urls += realpath + "\\" + filesName[i];
			
		}
		return urls;
	}
}
