package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import bean.House;
import dao.HouseDao;
/*
 * ����ʵ�ַ��ݵ���Ϣ�����ӡ�ɾ�����޸��Լ��õ���Ҫ�༭�ķ���*/
public class UpdateHouse extends ActionSupport {
	
	private House house;//����
	
	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	/**
	 * ��ӷ��ݣ�ִ�гɹ��Ļ�����Ϣд�����ݿⲢ�ҷ���SUCCESS����෵��fail
	 */
	/**
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String addHouse() throws SQLException, IOException {
		//�õ���session�д洢���û�����ֵ
		Map session = ActionContext.getContext().getSession();
		String uname = (String) session.get("uname");
		house.setOwner(uname);
		int id=new HouseDao().addHouse(house);
		if (id>-1) {//����HouseDao���ṩ�ķ������ݿ�ķ���
			house.setId(id);
			return SUCCESS;
		}
		return "fail";

	}
	public String afterUpdateHouse() throws SQLException{
		house = new HouseDao().getHouseById(house.getId());
		return SUCCESS;
	}
	@Override
	public void validate() {
		if(house.getRent()==-1||house.getRent()<0){
			addFieldError("house.rent","��𲻺Ϸ�");	
		}
		/*if(house.getArea()==-1||house.getArea()<0){
			addFieldError("house.area","������Ϸ�");	
		}*/
	}

	/**
	 * ɾ�����ݣ�ִ�гɹ��Ļ��޸����ݿⲢ�ҷ���SUCCESS����෵��fail
	 */
	/**
	 * @return
	 * @throws SQLException
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
	/**
	 * @return
	 * @throws SQLException
	 * @throws IOException
	 */
	public String updateHouse() throws SQLException, IOException {
	
		//���ݿ����
		if (new HouseDao().updateHouse(house)) {
			return SUCCESS;
		}
		return "fail";
	}
	/**
	 * �༭���ݣ��õ���Ҫ�༭�ķ�����Ϣ
	 */
	/**
	 * @return
	 * @throws SQLException
	 */
	public String editHouse() throws SQLException {
		//�������ݿ�õ�ָ����id����Ӧ�ķ���
		house = new HouseDao().getHouseById(house.getId());
		return SUCCESS;
	}

	
}
