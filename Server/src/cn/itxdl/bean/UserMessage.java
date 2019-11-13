package cn.itxdl.bean;

public class UserMessage implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String type;
	private User user;
	
	public UserMessage() {
		super();
	}
	public UserMessage(String type, User user) {
		super();
		setType(type);
		setUser(user);
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "UserMessage [type=" + type + ", user=" + user + "]";
	}
}
