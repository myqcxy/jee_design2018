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
	/* private List<Provice> provices;// Provice对象的列表
	    private Map<Integer, List<City>> cityMap;// 以Provice对象为key,Provice对应的City对象的列表作为value
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
		value.add("河南");
		value.add("上海");
		place.put("china", value);*/
		citys = new ArrayList<City>();
		City c1 = new City(0,"郑州");
		City c2 =  new City(1,"洛阳");
		citys.add(c1);
		citys.add(c2);
		List<Zone> z1 = new ArrayList<Zone>();
		List<Zone> z2 = new ArrayList<Zone>();
		z1.add(new Zone(0,"高新区"));
		z1.add(new Zone(1,"金水区"));
		z2.add(new Zone(0,"偃师"));
		z2.add(new Zone(1,"洛龙区"));
		zoneMap = new HashMap<Integer,List<Zone>>();
		zoneMap.put(c1.getId(), z1);
		zoneMap.put(c2.getId(), z2);
		return SUCCESS;
	}
	
}
