package dao;

import bean.Admin;
import bean.Place;

public interface AdminDao {
	/**
	 * */
	public void addPlace(Place p)throws Exception;//��ӵص�
	public void editPlace(Place p)throws Exception;//�޸ĵص�
	public boolean isLagel(Admin admin)throws Exception;//��֤��¼�Ƿ�Ϸ�
}
