package model;

public class Shopping{
	
	String PrName;
	int QN;
	String Type;
	double Price;
	double Amt;
	
	int Id;	
	int QA;
	double total;
	
	public String getPrName() {
		return PrName;
	}
	public void setPrName(String prName) {
		PrName = prName;
	}
	public int getQN() {
		return QN;
	}
	public void setQN(int qN) {
		QN = qN;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public double getAmt() {
		return Amt;
	}
	public void setAmt(double amt) {
		Amt = amt;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getQA() {
		return QA;
	}
	public void setQA(int qA) {
		QA = qA;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}