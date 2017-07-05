package rest;

import java.util.List;

import model.Products;

public class ProductForm {

	List<Products> prodList;

	public List<Products> getProdList() {
		return prodList;
	}

	public void setProdList(List<Products> prodList) {
		this.prodList = prodList;
	}

	public ProductForm(List<Products> prodList) {
		super();
		this.prodList = prodList;
	}

	public ProductForm() {
		super();
		this.prodList = null;
	}

	@Override
	public String toString() {
		return "ProductForm [prodList=" + prodList + "]";
	}	
}
