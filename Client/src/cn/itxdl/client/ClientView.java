package cn.itxdl.client;

import java.io.IOException;

import cn.itxdl.bean.User;
import cn.itxdl.bean.UserMessage;

public class ClientView {
	// 使用合成复用原则来使用对象输出输入流
	private ClientInitClose cic;
	private ClientManagerView cmv;
	
	/**
	 * 自定义构造方法实现成员变量的初始化工作
	 */
	public ClientView(ClientInitClose cic, ClientManagerView cmv) {
		this.cic = cic;
		this.cmv = cmv;
	}
	
	/**
	 * 自定义成员方法实现客户端主界面的绘制
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void clientMainPage() throws IOException, ClassNotFoundException {
		while(true) {
			System.out.println("\n\n\t    兄弟连在线考试系统");
			System.out.println("---------------------------------------");
			System.out.print("   [1] 学员系统     ");
			System.out.println("[2] 管理员系统");
			System.out.println("          [0] 退出系统");
			System.out.println("---------------------------------------");
			System.out.println("请选择要进入的系统：");
			//Scanner sc = new Scanner(System.in);
			int choose = ClientScanner.getSc().nextInt();
			switch(choose) {
				case 1: System.out.println("正在进入学员系统..."); break;
				case 2: clientManagerLogin(); break;
				case 0: clientExit(); return;
				default: System.out.println("输入错误，请重新选择");
			}
		}
	}
	
	/**
	 * 自定义成员方法实现客户端退出的功能
	 * @throws IOException 
	 */
	public void clientExit() throws IOException {
		// 准备一个退出消息的对象发送给服务器
		UserMessage tum = new UserMessage("quit", null);
		cic.getOos().writeObject(tum);
		// 关闭客户端的Socket和流对象
		cic.clientClose();
		// 关闭客户端的扫描器
		ClientScanner.closeSc();
		System.out.println("谢谢使用，下次再见！");
	}
	
	/**
	 * 自定义成员方法实现管理员的登录功能
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void clientManagerLogin() throws IOException, ClassNotFoundException {
		System.out.println("请输入管理员的用户名：");
		//Scanner sc = new Scanner(System.in);
		String strUser = ClientScanner.getSc().next();
		System.out.println("请输入管理员的密码：");
		String strPass = ClientScanner.getSc().next();
		// 将用户选择的业务类型和相关的账户信息打包成对象
		UserMessage tum = new UserMessage("managerCheck", 
				new User(strUser, strPass));
		// 使用对象输出流将打包的兑现发送给服务器
		cic.getOos().writeObject(tum);
		System.out.println("客户端发送数据成功！");
		// 接收服务器回发的处理结果并给出提示
		tum = (UserMessage) cic.getOis().readObject();
		if("success".equals(tum.getType())) {
			System.out.println("登录成功，欢迎使用！");
			cmv.clientManagerPage();
		} else {
			System.out.println("用户名或密码错误！");
		}
	}
}
