package rest;

import java.util.List;

import model.Shopping;

public class ShoppingForm {

	List<Shopping> shopList;

	public List<Shopping> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shopping> shopList) {
		this.shopList = shopList;
	}

	public ShoppingForm(List<Shopping> shopList) {
		super();
		this.shopList = shopList;
	}

	public ShoppingForm() {
		super();
		this.shopList = null;
	}

	@Override
	public String toString() {
		return "ShoppingForm [shopList=" + shopList + "]";
	}
}
