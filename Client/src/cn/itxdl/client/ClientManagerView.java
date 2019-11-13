package cn.itxdl.client;

import java.io.IOException;

import cn.itxdl.bean.User;
import cn.itxdl.bean.UserMessage;

public class ClientManagerView {
	// 声明成员变量是为了输出输入流
	private ClientInitClose cic;
	
	/**
	 * 自定义构造方法实现成员变量的初始化
	 */
	public ClientManagerView(ClientInitClose cic) {
		this.cic = cic;
	}
	/**
	 * 自定义成员方法实现管理主界面的绘制
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void clientManagerPage() throws IOException, ClassNotFoundException {
		while(true) {
			System.out.println("\n\n\t 欢迎来到管理员系统");
			System.out.println("---------------------------------------");
			System.out.print("   [1] 学员管理     ");
			System.out.println("[2] 试题管理");
			System.out.println("          [0] 退出系统");
			System.out.println("---------------------------------------");
			System.out.println("请选择要进入的管理系统：");
			//Scanner sc = new Scanner(System.in);
			int choose = ClientScanner.getSc().nextInt();
			switch(choose) {
				case 1: clientUserPage(); break;
				case 2: clientQuestionPage(); break;
				case 0: return;
				default: System.out.println("输入错误，请重新选择");
			}
		}
	}
	
	/**
	 * 自定义成员方法实现学员管理界面的绘制
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void clientUserPage() throws IOException, ClassNotFoundException {
		while(true) {
			System.out.println("\n\n\t 欢迎进入学员管理系统");
			System.out.println("---------------------------------------");
			System.out.print("   [1] 增加学员     ");
			System.out.println("[2] 删除学员");
			System.out.print("   [3] 修改学员     ");
			System.out.println("[4] 查找学员");
			System.out.println("          [0] 退出系统");
			System.out.println("---------------------------------------");
			System.out.println("请选择要进行的操作：");
			//Scanner sc = new Scanner(System.in);
			int choose = ClientScanner.getSc().nextInt();
			switch(choose) {
				case 1: clientAddUser(); break;
				case 2: clientDelUser(); break;
				case 3: clientSetUser(); break;
				case 4: clientFinUser(); break;
				case 0: return;
				default: System.out.println("输入错误，请重新选择");
			}
		}
	}
	
	/**
	 * 自定义成员方法实现增加学员的功能
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void clientAddUser() throws IOException, ClassNotFoundException {
		System.out.println("请输入要增加的学员用户名：");
		String strUser = ClientScanner.getSc().next();
		System.out.println("请输入要增加的学员密码：");
		String strPass = ClientScanner.getSc().next();
		// 打包对象通过输出流发送给服务器
		UserMessage tum = new UserMessage("addUser", 
				new User(strUser, strPass));
		cic.getOos().writeObject(tum);
		System.out.println("客户端发送增加学员消息成功！");
		// 接收服务器回发的处理结果并给出提示
		tum = (UserMessage) cic.getOis().readObject();
		if("success".equals(tum.getType())) {
			System.out.println("增加学员成功！");
		} else {
			System.out.println("增加学员失败！");
		}
	}
    /**
     * 自定义成员方法实现修改学员的功能
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void clientSetUser() throws IOException, ClassNotFoundException {
        System.out.println("请输入要修改的学员用户名：");
        String strUser = ClientScanner.getSc().next();
        System.out.println("请输入要修改的学员密码：");
        String strPass = ClientScanner.getSc().next();
        // 打包对象通过输出流发送给服务器
        UserMessage tum = new UserMessage("setUser",
                new User(strUser, strPass));
        cic.getOos().writeObject(tum);
        System.out.println("客户端发送修改学员消息成功！");
        // 接收服务器回发的处理结果并给出提示
        tum = (UserMessage) cic.getOis().readObject();
        if("success".equals(tum.getType())) {
            System.out.println("修改学员成功！");
        } else {
            System.out.println("修改学员失败！");
        }
    }
    /**
     * 自定义成员方法实现删除学员的功能
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void clientDelUser() throws IOException, ClassNotFoundException {
        System.out.println("请输入要删除的学员用户名：");
        String strUser = ClientScanner.getSc().next();
        // 打包对象通过输出流发送给服务器
        UserMessage tum = new UserMessage("delUser",
                new User(strUser, null));
        cic.getOos().writeObject(tum);
        System.out.println("客户端发送删除学员消息成功！");
        // 接收服务器回发的处理结果并给出提示
        tum = (UserMessage) cic.getOis().readObject();
        if("success".equals(tum.getType())) {
            System.out.println("删除学员成功！");
        } else {
            System.out.println("删除学员失败！");
        }
    }
    /**
     * 自定义成员方法实现查找学员的功能
     */
    public void clientFinUser() throws IOException, ClassNotFoundException {
        System.out.println("请输入要查找的学员用户名：");
        String strUser = ClientScanner.getSc().next();
        // 打包对象通过输出流发送给服务器
        UserMessage tum = new UserMessage("finUser",
                new User(strUser, null));
        cic.getOos().writeObject(tum);
        System.out.println("客户端发送查找学员消息成功！");
        // 接收服务器回发的处理结果并给出提示
        tum = (UserMessage) cic.getOis().readObject();
        if("success".equals(tum.getType())) {
            System.out.println("该学员存在！");
        } else {
            System.out.println("该学员不存在！");
        }
    }
	/**
	 * 自定义成员方法实现试题管理界面的绘制
     */
	public void clientQuestionPage() {
		while(true) {
			System.out.println("\n\n\t 欢迎进入试题管理系统");
			System.out.println("---------------------------------------");
			System.out.print("   [1] 增加试题     ");
			System.out.println("[2] 删除试题");
			System.out.print("   [3] 修改试题     ");
			System.out.println("[4] 查找试题");
			System.out.print("   [5] 显示试题     ");
			System.out.println("[6] 导入试题");
			System.out.println("   [0] 退出系统");
			System.out.println("---------------------------------------");
			System.out.println("请选择要进行的操作：");
			//Scanner sc = new Scanner(System.in);
			int choose = ClientScanner.getSc().nextInt();
			switch(choose) {
				case 1: System.out.println("正在增加试题..."); break;
				case 2: System.out.println("正在删除试题..."); break;
				case 3: System.out.println("正在修改试题..."); break;
				case 4: System.out.println("正在查找试题..."); break;
				case 5: System.out.println("正在显示试题..."); break;
				case 6: System.out.println("正在导入试题..."); break;
				case 0: return;
				default: System.out.println("输入错误，请重新选择");
			}
		}
	}
}
