package cn.itxdl.server.main;

import java.util.LinkedList;
import java.util.List;

import cn.itxdl.bean.User;
import cn.itxdl.server.ServerInitClose;
import cn.itxdl.server.ServerLogic;
import cn.itxdl.server.ServerUserDao;

public class ServerMainTest {

	public static void main(String[] args) {
		
		try {
			// 声明List类型的引用指向该类型的对象
			List<User> userList = new LinkedList<User>();
			// 声明服务器学员读写类型的引用指向该类型的对象
			ServerUserDao sud = new ServerUserDao(userList);
			// 调用方法将文件中所有学员信息一次性读取出来到集合中
			userList = sud.readUser();
			// 遍历读取到的所有学员信息实现测试
			System.out.println("从文件中读取到的学员信息有：");
			for(User tu: userList) {
				System.out.println(tu);
			}
			
			
			// 声明服务器初始化关闭类型的引用指向该类型的对象
			ServerInitClose sic = new ServerInitClose();
			// 调用方法进行初始化
			sic.serverInit();
			
			// 声明服务器逻辑业务处理的引用指向该类型的对象
			ServerLogic sl = new ServerLogic(sic, userList);
			// 调用方法接收数据
			sl.serverReceiveMsg();
			
			// 当项目退出时，将集合中的所有学员信息写入到文件中
			sud.writeUser();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
