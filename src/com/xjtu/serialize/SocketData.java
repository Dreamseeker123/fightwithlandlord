package com.xjtu.serialize;

import java.util.ArrayList;
import java.util.List;

import com.xjtu.gamestate.GameState;
import com.xjtu.player.Player;
import com.xjtu.poke.Card;

public class SocketData {
	
	private Player[] players;//������ҵ���Ϣ
	private List<Card> lastThreePokers;//���ŵ���
	private GameState gameState;//��Ϸ״̬
	private int first;//����
	private int current;//��ǰ�ֵ�˭����
	private int winner;
	private int yourIndex;
	private List<Card> lastCardsOnDesk;
	
	
	
}
