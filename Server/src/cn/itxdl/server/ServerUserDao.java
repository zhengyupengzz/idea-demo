package cn.itxdl.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import cn.itxdl.bean.User;

public class ServerUserDao {
	// 声明一个List类型的引用作为该类的成员变量，使用合成复用原则
	private List<User> userList;
	
	/**
	 * 自定义构造方法实现成员变量的初始化
	 */
	public ServerUserDao(List<User> userList) {
		this.userList = userList;
	}
	
	/**
	 * 自定义成员方法负责将文件中的所有学员信息读取到集合中
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public List<User> readUser() throws FileNotFoundException, IOException, ClassNotFoundException {
		// 只有文件存在时才需要读取，项目第一次启动时文件肯定不存在
		if(new File("./user.txt").exists()) {
			// 1.构造ObjectInputStream类型的对象与./user.txt文件关联
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("./user.txt"));
			// 2.将文件中的所有学员信息看做一个整体一次性读取到集合中
			userList = (List<User>) ois.readObject();
			// 3.关闭流对象并释放有关的资源
			ois.close();
		}
		// 4.返回读取到的所有学员信息
		return userList;
	}
	
	/**
	 * 自定义成员方法实现将集合中的所有学员信息一次性整体写入文件中
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */   
	public void writeUser() throws FileNotFoundException, IOException {
		// 1.构造ObjectOutputStream类型的对象与./user.txt文件关联
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("./user.txt"));
		// 2.整体写入文件中
		oos.writeObject(userList);
		// 3.关闭流对象并释放有关的资源
		oos.close();
	}
}
