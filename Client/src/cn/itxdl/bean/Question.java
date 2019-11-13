package cn.itxdl.bean;

public class Question implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private String answerA;
	private String answerB;
	private String answerC;
	private String answerD;
	private String answer;
	
	public Question() {
		super();
	}
	public Question(String title, String answerA, String answerB, String answerC, String answerD, String answer) {
		super();
		setTitle(title);
		setAnswerA(answerA);
		setAnswerB(answerB);
		setAnswerC(answerC);
		setAnswerD(answerD);
		setAnswer(answer);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAnswerA() {
		return answerA;
	}
	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}
	public String getAnswerB() {
		return answerB;
	}
	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	public String getAnswerC() {
		return answerC;
	}
	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}
	public String getAnswerD() {
		return answerD;
	}
	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Question [title=" + title + ", answerA=" + answerA + ", answerB=" + answerB + ", answerC=" + answerC
				+ ", answerD=" + answerD + ", answer=" + answer + "]";
	}
}
