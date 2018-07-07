package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.House;
import bean.RentalInfo;
import dao.HouseDao;
/*
 * ����ʵ�ַ��ݵ���Ϣ�����ӡ�ɾ�����޸��Լ��õ���Ҫ�༭�ķ���*/
public class UpdateHouse extends ActionSupport {
	
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

	/**
	 * ��ӷ��ݣ�ִ�гɹ��Ļ�����Ϣд�����ݿⲢ�ҷ���SUCCESS����෵��fail
	 */
	public String addHouse() throws SQLException, IOException {
		//�õ���session�д洢���û�����ֵ
		Map session = ActionContext.getContext().getSession();
		String uname = (String) session.get("uname");
		house.setOwner(uname);
		if (new HouseDao().addHouse(house)) {//����HouseDao���ṩ�ķ������ݿ�ķ���
			return SUCCESS;
		}
		return "fail";

	}
	/**
	 * ɾ�����ݣ�ִ�гɹ��Ļ��޸����ݿⲢ�ҷ���SUCCESS����෵��fail
	 */
	public String delMyHouse() throws SQLException {
		if (new HouseDao().delMyHouse(house.getId())) {
			return SUCCESS;
		}
		return "fail";
	}
	/**
	 * ���·�����Ϣ��ִ�гɹ��Ļ��޸����ݿ��ж�Ӧ�ķ�����Ϣ���ҷ���SUCCESS����෵��fail
	 */
	public String updateHouse() throws SQLException, IOException {
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
		//���ݿ����
		if (new HouseDao().updateHouse(house)) {
			return SUCCESS;
		}
		return "fail";
	}
	/**
	 * �༭���ݣ��õ���Ҫ�༭�ķ�����Ϣ
	 */
	public String editHouse() throws SQLException {
		//�������ݿ�õ�ָ����id����Ӧ�ķ���
		house = new HouseDao().getHouseById(house.getId());
		return SUCCESS;
	}

	
}
