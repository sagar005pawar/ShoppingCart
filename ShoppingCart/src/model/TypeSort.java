package model;

import java.util.Comparator;

public class TypeSort implements Comparator<Products> {

	@Override
	public int compare(Products o1, Products o2) {
		return o1.getType().compareToIgnoreCase(o2.getType());
	}

}
