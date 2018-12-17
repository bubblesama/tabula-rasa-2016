package com.bb.advent2018.day13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartMap {
	
	//model
	private Cell[][] map;
	public List<Cart> carts;
	public int w;
	public int h;
	public int tick;
	private static CartSorter sorter = new CartSorter();
	
	public CartMap(int width, int height) {
		super();
		this.w = width;
		this.h= height;
		this.map = new Cell[w][h];
		this.tick = 0;
		this.carts = new ArrayList<Cart>();
		for (int i=0; i<width; i++) {
			for (int j=0; j<height; j++) {
				map[i][j] = new Cell(i,j,TrackType.NONE);
			}
		}
	}

	public Cell getCell(int i, int j) {
		return map[i][j];
	}
	
	public boolean add(Cart e) {
		return carts.add(e);
	}
	
	public int getCrashed() {
		int result = 0;
		for (Cart cart: carts) {
			if (cart.isCrashed){
				result++;
			}
		}
		return result;
	}
	
	public boolean shouldCrash(int cartId, int i, int j) {
		boolean result = false;
		for (Cart cart: carts) {
			if (cart.id != cartId && cart.i == i && cart.j == j) {
				result = true;
			}
		}
		if (result) {
			System.out.println("CartMap#shouldCrash crash on ("+i+","+j+")");
		}
		return result;
	}
	
	public void tick() {
		Collections.shuffle(carts);
		carts.sort(sorter);
		if (tick < 10) {
			String showCarts = "";
			for (Cart cart: carts) {
				showCarts += cart.show();
			}
			//System.out.println("carts: "+showCarts);
		}
		for (Cart cart: carts) {
			cart.step(this);
		}
		tick++;
	}

}
