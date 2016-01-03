package com.xjtu.poke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokeController 
{
	private Pokers pokers = new Pokers();
	private Random random = new Random();
	
	private List<Card> lastThreePokers = new ArrayList<Card>();
	private Card landlordCard = null;
	
	List<Card> pokerList = new ArrayList();
	
	public PokeController()
	{
		//����
		System.out.println("PokeController�õ�����������"+pokers.getPokerList().size());
		
		pokerList = pokers.getPokerList();
	}
	
	public Card getLandlordCard() {
		return landlordCard;
	}

	public void setLandlordCard(Card landlordCard) {
		this.landlordCard = landlordCard;
	}

	public void shuffle()//ϴ��
	{	
		for(int index=0; index<pokerList.size(); index++)
		{
			int pos = random.nextInt(53);
			Card card = pokerList.get(index);
			pokerList.set(index, pokerList.get(pos));
			pokerList.set(pos, card);
		}
		
		pokers.setPokerList(pokerList);
//		for(int i=0;i<pokers.getPokerList().size();++i)
//		{
//			Card card = new Card();
//			card = pokerList.get(i);
//			System.out.println("��ֵ��"+card.getValue()+"��ɫ��"+card.getColor());
//		}
	}
	
	
	public void deal(List<Card> player_1,List<Card> player_2,List<Card> player_3)//����
	{
		int a1 = random.nextInt(50);
		landlordCard = pokerList.get(a1);
		for(int index=0; index<pokerList.size()-3; index++)
		{
			player_1.add(pokerList.get(index++));
			player_2.add(pokerList.get(index++));
			player_3.add(pokerList.get(index++));	
		}	
		lastThreePokers.add(pokerList.get(51));
		lastThreePokers.add(pokerList.get(52));
		lastThreePokers.add(pokerList.get(53));
	}

	
	
	public Pokers getPokers() {
		return pokers;
	}

	public void setPokers(Pokers pokers) {
		this.pokers = pokers;
	}

	public List<Card> getLastThreePokers() {
		return lastThreePokers;
	}

	public void setLastThreePokers(List<Card> lastThreePokers) {
		this.lastThreePokers = lastThreePokers;
	}
	
}
