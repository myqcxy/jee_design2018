package dao;

import bean.Admin;
import bean.*;

public interface AdminDao {
	/**
	 * */
	public void addPlace(City city)throws Exception;//��ӵص�
	public void editPlace(Zone zone)throws Exception;//�޸ĵص�
	public boolean isLagel(Admin admin)throws Exception;//��֤��¼�Ƿ�Ϸ�
}
