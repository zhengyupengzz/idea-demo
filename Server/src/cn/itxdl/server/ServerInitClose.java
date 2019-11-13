package cn.itxdl.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerInitClose {
	// 声明引用负责记录Socket对象和输入输出流对象
	private ServerSocket ss;
	private Socket s;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	// 提供公有的get和set方法
	public ObjectInputStream getOis() {
		return ois;
	}
	public void setOis(ObjectInputStream ois) {
		this.ois = ois;
	}
	public ObjectOutputStream getOos() {
		return oos;
	}
	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}
	
	/**
	 * 自定义成员方法实现服务器的初始化工作
	 * @throws IOException 
	 */
	public void serverInit() throws IOException {
		// 1.创建ServerSocket类型的对象并提供端口号
		ss = new ServerSocket(8888);
		
		// 2.等待客户端的连接请求，调用accept方法
		System.out.println("等待客户端的连接请求...");
		s = ss.accept();
		System.out.println("客户端连接成功！");
		
		// 3.使用输入输出流进行通信，对象流
		ois = new ObjectInputStream(s.getInputStream());
		oos = new ObjectOutputStream(s.getOutputStream());
		System.out.println("服务器初始化成功！");
	}

	/**
	 * 自定义成员方法实现服务器的关闭操作
	 * @throws IOException 
	 */
	public void serverClose() throws IOException {
		// 4.关闭Socket并释放有关的资源
		oos.close();
		ois.close();
		s.close();
		ss.close();
		//System.out.println("服务器成功关闭！");
	}
}
