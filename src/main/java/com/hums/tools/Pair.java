package com.hums.tools;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Pair<E1,E2> implements Serializable{
	
	private E1 element1;
	private E2 element2;
	
	public Pair() {
		
	}
	
	public Pair(E1 e1, E2 e2) {
		element1 = e1;
		element2 = e2;
	}
	
	public E1 getElement1() {
		return this.element1;
	}
	
	public E2 getElement2() {
		return this.element2;
	}
	
	public void setElement1(E1 value) {
		this.element1 = value;
	}
	
	public void setElement2(E2 value) {
		this.element2 = value;
	}
}
