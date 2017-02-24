package application;

import java.util.ArrayList;

public class SnakeManager {
	public static ArrayList<Snake> snakes;
	public static ArrayList<ServerBridge> sockets;
	public SnakeManager() {
		snakes = new ArrayList<Snake>();
		sockets = new ArrayList<ServerBridge>();
		System.out.println("Snake Manager initialized");
	}
	public static void addSnake(Snake snake){
		snakes.add(snake);
		ServerBridge socket = new ServerBridge();
		socket.bindToSnake(snake);
		sockets.add(socket);
		System.out.print("Snake added: ");
		System.out.println(snake.toString());
	}
	public static Snake getSnake(int index){
		return snakes.get(index);
	}
	public static void connectSnakesToServer(String serverAddress, int port){
		for(ServerBridge bridge: sockets){
			bridge.connectToServer(serverAddress, port);
		}
	}
	public static void killSnake(int index){
		snakes.get(index).die();
	}
	public static String move(int index){
		return "" + snakes.get(index).update();
	}
	public static void closeAllBridges(){
		for(int i = sockets.size()-1; i >= 0; i --){
			sockets.get(i).closeSocket();
		}
	}

}