package com.xjtu.playercontroller;

import com.xjtu.player.Player;

public class PlayerController {
	
	final private int playerCount = 3;
	private Player[] players = null;
	private int first = -1;//����
	private int current = -1;//��ǰ�ֵ�˭����
	private int landlordCard = -1;//�õ������Ƶ���
	private int landlord = -1;//����
	
	public void initialPlayers(){
		
	}
	
	public void addPlayer(Player player){
		
	}
	public Player NextPlayer(){
		return players[(current + 1)%3];
	}
	public Player FirstPlayer(){
		return players[first];
	}
	
	// ϴ�� ����
	
	//��ĳ������˭����   ���Ƚе���
	
	//��ѯ�е���  first   current   landlord        abstract
	
	//��ѯ����   abstract
	
	//�жϳ����Ƿ����
	
}
