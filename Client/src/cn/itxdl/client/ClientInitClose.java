package cn.itxdl.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientInitClose {
	// 声明Socket类型和对象流类型的引用作为成员变量
	private Socket s;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	// 提供公有的get和set方法供外界使用
	public ObjectOutputStream getOos() {
		return oos;
	}
	public void setOos(ObjectOutputStream oos) {
		this.oos = oos;
	}
	public ObjectInputStream getOis() {
		return ois;
	}
	public void setOis(ObjectInputStream ois) {
		this.ois = ois;
	}

	/**
	 * 自定义成员方法实现客户端的初始化工作
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public void clientInit() throws UnknownHostException, IOException {
		// 1.构造Socket类型的对象并提供服务器的通信地址和端口号
		s = new Socket(InetAddress.getLocalHost(), 8888);
		System.out.println("连接服务器成功！");
		// 2.使用输入输出流进行通信，对象流
		oos = new ObjectOutputStream(s.getOutputStream());
		ois = new ObjectInputStream(s.getInputStream());
		System.out.println("客户端初始化成功！");
	}
	
	/**
	 * 自定义成员方法实现客户端的关闭工作
	 * @throws IOException 
	 */
	public void clientClose() throws IOException {
		// 3.关闭Socket并释放有关的资源
		ois.close();
		oos.close();
		s.close();
		//System.out.println("客户端成功关闭！");
	}
}
