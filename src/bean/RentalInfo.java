package bean;

import java.util.Date;

public class RentalInfo {
	String rentMode;//出租方式
	House house;//被出租房屋
	String phoneNumber;//联系电话
	Date rentDur;//出租期限
	double rent;//租金
	public String getRentMode() {
		return rentMode;
	}
	public void setRentMode(String rentMode) {
		this.rentMode = rentMode;
	}
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getRentDur() {
		return rentDur;
	}
	public void setRentDur(Date rentDur) {
		this.rentDur = rentDur;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	
}
