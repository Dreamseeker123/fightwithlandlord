package com.xjtu.server;


import org.junit.Test;

import com.xjtu.controller.PlayerController;
import com.xjtu.gamestate.GameState;
import com.xjtu.player.Player;
import com.xjtu.player.RealPlayer;

public class ServerPlayerController extends PlayerController{

	//1.创建serversocket
	//2.wait等待连接并且判断是不是为3个
	//
	private Server server;
	private int port=9989;	
	private boolean flag=false;
	
	public ServerPlayerController(){
		server=new Server(this);
		
	}
	@Test
	public void toStartServer(){
		//并将游戏状态设为等待
		
		gameState=GameState.WAITCLIENT;
		//实例化初始值  把本机设置为第一个玩家 并将count值设置为1	 isReady 设置为true
	//	this.getPlayers()[this.playerCount]=this.firstPlayer();
	//  this.setPlayers(this.getPlayers());
		
		players[playerCount++]=new RealPlayer();
		players[0].setReady(true);
		
		//开启服务器  等待客户端连接
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
				
				deal();//地主三张牌的保存
				gameState=GameState.CALLING;
				break;
			case CALLING:
				//找到拿到地主牌的玩家设置为东家？优先叫地主  修改状态
				findLandlord();
				//createTimer(25);
				gameState=GameState.WAITCALL;
				break;
			case WAITCALL:
				// 轮流叫地主    
				//确定地主是谁？ 东家
				createTimer(25);
				waitCallLandlord();
				//gameState=GameState.PLAYING;
				break;
			case PLAYING:
				//地主先出牌
				//对比每个玩家传递过来的playingCards按规则找出最大的牌
				//删除Current的玩家的牌中的playingCards牌
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
			 if(flag){//第二次叫地主 
				 //叫地主结束
				 
				 //判断谁是地主？
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
				 waitCallLandlord();//完全不需要
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
//					//第二次轮到地主叫，分为    叫地主  or 不叫
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
		//1.是否有三个玩家 		
		if(players[0].equals("null")||players[1].equals("null")||players[2].equals("null")){
			
			System.out.println("玩家不够，请等待！！！");
			return;
			//server 等待客户连接
		}//三个玩家是否都准备？？
		else if(this.getPlayers()[0].isReady()
		&&this.getPlayers()[1].isReady()
		&&this.getPlayers()[2].isReady()){
			
			gameState=GameState.SHUFFLE;
		}
	}
	
	
	
}



//轮询叫地主  first   current   landlord        abstract
//轮询出牌   abstract
//创建Server Socket  ，等待连接 ， 根据GameState执行不同逻辑
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
 *			发送给客户消息包  当前的状态 waticlient
 *			}
 *		else if(){
 *			}
 * 
 * 
 * }
 */