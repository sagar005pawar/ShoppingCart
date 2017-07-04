package model;

import java.util.Comparator;

public class PriceComparator implements Comparator<Products> {

	@Override
	public int compare(Products o1, Products o2) {
		if (o1.getPrice() > o2.getPrice()) {
			return -1;
		} else if(o1.getPrice() < o2.getPrice()) {
			return 1;
		} else {
			return 0;
		}
	}
}
