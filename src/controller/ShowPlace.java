package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import bean.City;
import bean.Zone;
import dao.PlaceDao;


public class ShowPlace extends ActionSupport {
	/* private List<Provice> provices;// Provice������б�
	    private Map<Integer, List<City>> cityMap;// ��Provice����Ϊkey,Provice��Ӧ��City������б���Ϊvalue
	*/
	Map<Integer,List<Zone>> zoneMap;
	List<City> citys;
	
	

	public Map<Integer, List<Zone>> getZoneMap() {
		return zoneMap;
	}

	public void setZoneMap(Map<Integer, List<Zone>> zoneMap) {
		this.zoneMap = zoneMap;
	}

	public List<City> getCitys() {
		return citys;
	}

	public void setCitys(List<City> citys) {
		this.citys = citys;
	}

	@Override
	public String execute() throws Exception {
		/*place = new PlaceDao().getAllPlace();
		System.out.println("\n\n\nsdfsdfsdf\n\n\n");
		a=(List) place.keySet();*/
		/*place = new HashMap<String,List<String>>();
		List value = new ArrayList<String>();
		value.add("����");
		value.add("�Ϻ�");
		place.put("china", value);*/
		citys = new ArrayList<City>();
		City c1 = new City(0,"֣��");
		City c2 =  new City(1,"����");
		citys.add(c1);
		citys.add(c2);
		List<Zone> z1 = new ArrayList<Zone>();
		List<Zone> z2 = new ArrayList<Zone>();
		z1.add(new Zone(0,"������"));
		z1.add(new Zone(1,"��ˮ��"));
		z2.add(new Zone(0,"��ʦ"));
		z2.add(new Zone(1,"������"));
		zoneMap = new HashMap<Integer,List<Zone>>();
		zoneMap.put(c1.getId(), z1);
		zoneMap.put(c2.getId(), z2);
		return SUCCESS;
	}
	
}
