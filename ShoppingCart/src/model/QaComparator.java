package model;

import java.util.Comparator;

public class QaComparator implements Comparator<Products> {

	@Override
	public int compare(Products p1, Products p2) {
		if (p1.getQA() > p2.getQA()) {
			return 1;
		} else if(p1.getQA() < p2.getQA()){
			return -1;
		}else {
			return 0;
		}
	}

}
