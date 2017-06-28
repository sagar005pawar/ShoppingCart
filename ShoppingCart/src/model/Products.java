package model;

import java.io.Serializable;

public class Products implements Cloneable, Serializable, Comparable<Products> {
	private int Id;	
	private String PrName;
	private int QA;
	private double Price;
	private String Type;
	
	private int QN;
	private double Amt;	
//	private double total;

	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getPrName() {
		return PrName;
	}
	public void setPrName(String prName) {
		PrName = prName;
	}
	public int getQA() {
		return QA;
	}
	public void setQA(int qA) {
		QA = qA;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public int getQN() {
		return QN;
	}
	public void setQN(int qN) {
		QN = qN;
	}
	public double getAmt() {
		return Amt;
	}
	public void setAmt(double amt) {
		Amt = amt;
	}
//	public double getTotal() {
//		return total;
//	}
//	public void setTotal(double total) {
//		this.total = total;
//	}

	public Products(int id, String prName, int qA, double price, String type) {
		super();
		Id = id;
		PrName = prName;
		QA = qA;
		Price = price;
		Type = type;
	}

	public Products() {
		super();
		Id = 0;
		PrName = null;
		QA = 0;
		Price = 0.0d;
		Type = null;
	}
	
	@Override
	public String toString() {
		return "Products [Id=" + Id + ", PrName=" + PrName + ", QA=" + QA + ", Price=" + Price + ", Type=" + Type + "]";
	}

	@Override
	public int compareTo(Products p) {
		if (this.Id > p.Id) {
			return 1;
		} else if(this.Id < p.Id){
			return -1;
		}else {
			return 0;
		}
	}
		
}
