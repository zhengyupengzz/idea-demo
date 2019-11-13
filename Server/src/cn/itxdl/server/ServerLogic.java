package cn.itxdl.server;

import java.io.IOException;
import java.util.List;

import cn.itxdl.bean.User;
import cn.itxdl.bean.UserMessage;

public class ServerLogic {
	// 使用合成复用原则
	private ServerInitClose sic;
	// 将集合传递过来
	private List<User> userList;

    /**
	 * 自定义构造方法实现成员变量的初始化工作
	 */
	public ServerLogic(ServerInitClose sic, List<User> userList) {
		this.sic = sic;
		this.userList = userList;
	}
	
	/**
	 * 自定义成员方法实现客户端发来对象的接收
	 */
	public void serverReceiveMsg() throws ClassNotFoundException, IOException {
		while(true) {
			System.out.println("等待客户端发送数据...");
			// 使用对象输入流接收客户端发来的对象
			UserMessage tum = (UserMessage) sic.getOis().readObject();
			System.out.println("服务器接收到的对象是：" + tum);
			// 调用方法实现管理员账号信息的校验
			//serverManagerCheck(tum);
			switch(tum.getType()) {
				case "managerCheck": serverManagerCheck(tum); break;
				case "quit": serverExit(); return;
				case "addUser": serverAddUser(tum); break;
				case "delUser": serverDelUser(tum); break;
				case "finUser": serverFinUser(tum); break;
				case "setUser": serverSetUser(tum); break;
				default: System.out.println("哥们别急，我还没有写完呢！");
			}
		}
	}
	
	/**
	 * 自定义成员方法实现将学员信息添加到集合中
	 */
	public void serverAddUser(UserMessage tum) throws IOException {
		// 判断集合中是否已经存在该学员，学员信息不允许重复
		for(User tu: userList) {
			if(tu.getUserName().equals(tum.getUser().getUserName())) {
				// 若存在则修改类型为失败，然后使用输出流发送给客户端
				tum.setType("fail");
				sic.getOos().writeObject(tum);
				return;
			}
		}
		// 若代码能执行到这里，则证明集合中不存在
		userList.add(tum.getUser());
		tum.setType("success");
		sic.getOos().writeObject(tum);
		//System.out.println("服务器发送增加学员消息成功！");
	}

    /**
     * 自定义成员方法实现将学员信息修改到集合中
     */
    public void serverSetUser(UserMessage tum) throws IOException {
        // 判断集合中是否已经存在该学员
        for(User tu: userList) {
            if(tu.getUserName().equals(tum.getUser().getUserName())) {
                // 若代码能执行到这里，则证明集合中存在
                userList.set(userList.indexOf(tu),tum.getUser());
                tum.setType("success");
                sic.getOos().writeObject(tum);
                //System.out.println("服务器发送修改学员消息成功！");
                return;
            }
        }
        // 若不存在则修改失败，然后使用输出流发送给客户端
        tum.setType("fail");
        sic.getOos().writeObject(tum);
        
    }
    /**
     * 自定义成员方法实现将学员信息删除
     */
    public void serverDelUser(UserMessage tum) throws IOException {
        // 判断集合中是否已经存在该学员
        for(User tu: userList) {
            if(tu.getUserName().equals(tum.getUser().getUserName())) {
                // 若代码能执行到这里，则证明集合中存在
                userList.remove(tu);
                tum.setType("success");
                sic.getOos().writeObject(tum);
                //System.out.println("服务器发送删除学员消息成功！");
                return;
            }
        }
        // 若不存在则删除失败，然后使用输出流发送给客户端
        tum.setType("fail");
        sic.getOos().writeObject(tum);
        
    }
    /**
     * 自定义成员方法实现从集合中查找学员信息
     */
    public void serverFinUser(UserMessage tum) throws IOException {
        // 判断集合中是否已经存在该学员
        for(User tu: userList) {
            if(tu.getUserName().equals(tum.getUser().getUserName())) {
                // 若代码能执行到这里，则证明集合中存在
                userList.get(userList.indexOf(tu));
                tum.setType("success");
                sic.getOos().writeObject(tum);
                //System.out.println("服务器发送查找学员消息成功！");
                return;
            }
        }
        // 若不存在则查找失败，然后使用输出流发送给客户端
        tum.setType("fail");
        sic.getOos().writeObject(tum);
        
        
    }

    /**
	 * 自定义成员方法实现服务器的退出功能
	 */
	public void serverExit() throws IOException {
		// 关闭相关的Socket和流对象
		sic.serverClose();
		System.out.println("服务器成功退出！！！");
	}
	/**
	 * 自定义成员方法实现管理员账号和密码信息的校验
	 */
	public void serverManagerCheck(UserMessage um) throws IOException {
		// 假设内置的账号和密码是：admin  123456
		if("admin".equals(um.getUser().getUserName()) &&
				"123456".equals(um.getUser().getPassWord())) {
			um.setType("success");
		} else {
			um.setType("fail");
		}
		// 将服务器校验的结果通过对象输出流发送给客户端
		sic.getOos().writeObject(um);
		System.out.println("服务器发送处理结果成功！");
	}
}
