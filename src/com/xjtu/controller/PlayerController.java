package com.xjtu.controller;

import com.xjtu.player.Player;
import com.xjtu.poke.Card;
import com.xjtu.poke.PokeController;

public class PlayerController {
	
	
	final private int playerCount = 3;
	private Player[] players = null;
	private int first = -1;//����
	private int current = -1;//��ǰ�ֵ�˭����
	
	private PokeController pokeCtrl = new PokeController();
	
	public void initialPlayers(){
		
	}
	
	public void addPlayer(Player player){
		
	}
	public Player nextPlayer(){
		return players[(current + 1)%3];
	}
	public Player firstPlayer(){
		return players[first];
	}
	
	public Player PrePlayer(){
		return players[(current + 2) % 3];
	}
	// ϴ�� ����
	public void suffle(){
		pokeCtrl.shuffle();
	}
	
	public void deal(){
		pokeCtrl.deal(players[0].getHand(), players[1].getHand(), players[2].getHand());
	}
	
	//��ĳ������˭����   ���Ƚе���
	public void findLandlord(){
		Card lCard = pokeCtrl.getLandlordCard();
		for(int i = 0 ; i < 3; i++){
			for(Card c:players[i].getHand()){
				if(c.getColor() == lCard.getColor() && c.getValue() == lCard.getValue()){
					first = current = i;
				}
			}
		}
	}
	
	//��ѯ�е���  first   current   landlord        abstract
	
	//��ѯ����   abstract
	
	//�жϳ����Ƿ����
	
}
