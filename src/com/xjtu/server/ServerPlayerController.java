package com.xjtu.server;


import org.junit.Test;

import com.xjtu.controller.PlayerController;
import com.xjtu.gamestate.GameState;
import com.xjtu.player.Player;
import com.xjtu.player.RealPlayer;

public class ServerPlayerController extends PlayerController{

	//1.����serversocket
	//2.wait�ȴ����Ӳ����ж��ǲ���Ϊ3��
	//
	private Server server;
	private int port=9989;	
	private boolean flag=false;
	
	public ServerPlayerController(){
		server=new Server(this);
		
	}
	@Test
	public void toStartServer(){
		//������Ϸ״̬��Ϊ�ȴ�
		
		gameState=GameState.WAITCLIENT;
		//ʵ������ʼֵ  �ѱ�������Ϊ��һ����� ����countֵ����Ϊ1	 isReady ����Ϊtrue
	//	this.getPlayers()[this.playerCount]=this.firstPlayer();
	//  this.setPlayers(this.getPlayers());
		
		players[playerCount++]=new RealPlayer();
		players[0].setReady(true);
		
		//����������  �ȴ��ͻ�������
		server.startServer(port);
		
		new Thread(){
			public void run() {
				runMain();
			};
		}.start();;
		
		
		
	}

	private void runMain() {
		while(true){
			switch(gameState){
			case WAITCLIENT:
				isToShuffle();
				break;
			case SHUFFLE:
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				suffle();
				gameState=GameState.DEAL;
				break;
			case DEAL:
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				deal();//���������Ƶı���
				gameState=GameState.CALLING;
				break;
			case CALLING:
				//�ҵ��õ������Ƶ��������Ϊ���ң����Ƚе���  �޸�״̬
				findLandlord();
				//createTimer(25);
				gameState=GameState.WAITCALL;
				break;
			case WAITCALL:
				// �����е���    
				//ȷ��������˭�� ����
				createTimer(25);
				waitCallLandlord();
				//gameState=GameState.PLAYING;
				break;
			case PLAYING:
				//�����ȳ���
				//�Ա�ÿ����Ҵ��ݹ�����playingCards�������ҳ�������
				//ɾ��Current����ҵ����е�playingCards��
				break;
			case WAITPLAY:
				
				break;
			case END:
				
				break;
			case WAITNEXT:
				
				break;
		
			}
		}
		
	}
	private void createTimer(int timeout) {
		// TODO Auto-generated method stub
		this.timeout=timeout;
		//Thread t = new Thread(new TimerThread());
		timerThread.stop();
		timerThread.start();
		//gameState = GameState.WAITCALL;
	}
	private void waitCallLandlord() {
		
	if(timeout<=0||getPlayers()[current].getIsCallLandlord()==1||getPlayers()[current].getIsCallLandlord()==-1){
	
		 if(timeout<=0) getPlayers()[current].setIsCallLandlord(-1);
		 if(current==first){
			 if(flag){//�ڶ��νе��� 
				 //�е�������
				 
				 //�ж�˭�ǵ�����
//				 if(this.getPlayers()[first].getIsCallLandlord() == 1 )
//				 {
//					current = first;
//				 	first = first; 
//				 	gameState = GameState.PLAYING;
//				 }else if(this.prePlayer().getIsCallLandlord() == 1)
//				 {
//				 	 current = first;
//					 first = (first+2)%3;
//				 	gameState = GameState.PLAYING;
//				 }else if(this.nextPlayer().getIsCallLandlord() == 1)
//				 {
//				 	current = first;
//					 first = (first+1)%3;
//				 	gameState = GameState.PLAYING;
//				 }else
//				 {
//					 gameState = GameState.SHUFFLE;
//				 }

			 }else{
				 flag=true;
				 current++;
				 current%=3;
				 waitCallLandlord();//��ȫ����Ҫ
			 }
		 }
		
//		if(timeout<=0) {
//			getPlayers()[current].setIsCallLandlord(-1);
//			}
//				if(flag==false){
//					current++;
//					current%=3;
//				}
//				
//				if(current==first){
//					//�ڶ����ֵ������У���Ϊ    �е���  or ����
//					flag=true;
//					lookFor();
//					gameState=GameState.PLAYING;
//				}
//				
//				lookFor();
//			}		
		}
	}
		
	

	private void isToShuffle() {
		// TODO Auto-generated method stub
		//1.�Ƿ���������� 		
		if(players[0].equals("null")||players[1].equals("null")||players[2].equals("null")){
			
			System.out.println("��Ҳ�������ȴ�������");
			return;
			//server �ȴ��ͻ�����
		}//��������Ƿ�׼������
		else if(this.getPlayers()[0].isReady()
		&&this.getPlayers()[1].isReady()
		&&this.getPlayers()[2].isReady()){
			
			gameState=GameState.SHUFFLE;
		}
	}
	
	
	
}



//��ѯ�е���  first   current   landlord        abstract
//��ѯ����   abstract
//����Server Socket  ���ȴ����� �� ����GameStateִ�в�ͬ�߼�
/************
 * while()
 * {
 *		if(gamestate.WAITCLIENT){
 *			if(count < 2){
 *				accept()	
 *				}
 *			else{
 *					
 *			}
 *			���͸��ͻ���Ϣ��  ��ǰ��״̬ waticlient
 *			}
 *		else if(){
 *			}
 * 
 * 
 * }
 */