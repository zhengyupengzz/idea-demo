package cn.itxdl.client.main;

import cn.itxdl.client.ClientInitClose;
import cn.itxdl.client.ClientManagerView;
import cn.itxdl.client.ClientView;

public class ClientMainTest {

	public static void main(String[] args) {
		
		try {
			// 声明客户端初始化关闭类型的引用指向该类型的对象
			ClientInitClose cic = new ClientInitClose();
			// 调用成员方法实现初始化工作
			cic.clientInit();
			
			// 声明客户端管理界面类型的引用指向该类型的对象
			ClientManagerView cmv = new ClientManagerView(cic);
			
			// 声明客户端界面层类型的引用指向该类型的对象
			ClientView cv = new ClientView(cic, cmv);
			// 调用成员方法绘制界面进行测试
			cv.clientMainPage();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
