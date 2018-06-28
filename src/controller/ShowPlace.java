package controller;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import dao.PlaceDao;

public class ShowPlace extends ActionSupport {
	Map<String,List<String>> place;
	List a;
	public List getA() {
		return a;
	}
	public void setA(List a) {
		this.a = a;
	}
	public Map<String, List<String>> getPlace() {
		
		return place;
	}
	public void setPlace(Map<String, List<String>> place) {
		this.place = place;
	}
	@Override
	public String execute() throws Exception {
		place = new PlaceDao().getAllPlace();
		System.out.println("\n\n\nsdfsdfsdf\n\n\n");
		a=(List) place.keySet();
		return SUCCESS;
	}
	
}
