package model;

public class Products {
	int Id;	
	String PrName;
	int QA;
	double Price;
	String Type;
	
	int QN;
	double Amt;	
//	double total;

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
		
}
