package controller;

import java.io.File;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.House;

public class SavePhoto extends ActionSupport {
	private House house;//����
	private File[] upload;//�ϴ����ļ�
	private String[] uploadContenType;//�ϴ����ļ�����
	private String[] uploadFileName;//�ϴ����ļ���

	//��Ա������geter��seter����
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
		//�����ļ�������
		IOUtils io = new IOUtils("photos/" + uname);
		String urls = "";
		//�õ��ļ������п���Ϊ���
		for (int i = 0; i < uploadFileName.length; i++) {
			urls += "photos/" + uname + "/" + uploadFileName[i];
			
		}//end for
		//��ͼƬ�ļ����浽ָ����λ��
		io.filesCopy(upload, uploadFileName);
		//���÷��ݵ�ͼƬURL
		house.setPhotosUrl(urls);
		return SUCCESS;
	}
	
}
