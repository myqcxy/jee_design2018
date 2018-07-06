package controller;

import java.io.File;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SavePhoto extends ActionSupport {
	File [] upload;
	String [] uploadContenType;
	String [] uploadFileName;
	public File[] getUpload() {
		return upload;
	}
	public void setUpload(File[] upload) {
		this.upload = upload;
	}
	public String[] getUploadContenType() {
		return uploadContenType;
	}
	public void setUploadContenType(String[] uploadContenType) {
		this.uploadContenType = uploadContenType;
	}
	public String[] getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String[] uoloadFileName) {
		this.uploadFileName = uoloadFileName;
	}
	@Override
	public String execute() throws Exception {
		Map session = ActionContext.getContext().getSession();
		String uname = (String) session.get("uname");
		IOUtils io = new IOUtils("/photos/"+uname);
		io.filesCopy(upload, uploadFileName);
		return SUCCESS;
	}
	
}
