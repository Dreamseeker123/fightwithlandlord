package com.xjtu.poke;

public class Card {
	public  enum PokerColor
	{
		heart,spade,club,diamond,joker_small,joker_big
	}
	
	private PokerColor color;//��ɫ
	private int value;//��ֵ
	
	
	public PokerColor getColor() {
		return color;
	}
	public void setColor(PokerColor color) {
		this.color = color;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	

}
