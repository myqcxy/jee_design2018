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
		if (new HouseDao().addHouse(house)) {//����HouseDao���ṩ�ķ������ݿ�ķ���
			return SUCCESS;
		}
		return "fail";

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
