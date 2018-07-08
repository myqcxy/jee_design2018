package controller;

import java.io.File;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.House;

public class SavePhoto extends ActionSupport {
	private House house;//房屋
	private File[] upload;//上传的文件
	private String[] uploadContenType;//上传的文件类型
	private String[] uploadFileName;//上传的文件名

	//成员变量的geter个seter方法
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

	
	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}
	@Override
	public String execute() throws Exception {
		Map session = ActionContext.getContext().getSession();
		String uname = (String) session.get("uname");
		//创建文件操作类
		IOUtils io = new IOUtils("photos/" + uname);
		String urls = "";
		//得到文件名，有可能为多个
		for (int i = 0; i < uploadFileName.length; i++) {
			urls += "photos/" + uname + "/" + uploadFileName[i];
			
		}//end for
		//将图片文件保存到指定的位置
		io.filesCopy(upload, uploadFileName);
		//设置房屋的图片URL
		house.setPhotosUrl(urls);
		return SUCCESS;
	}
	
}
