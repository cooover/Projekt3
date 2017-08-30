package application.model;

public class TableModel {
	private int id_u;
	private String login;
	private String pass;
	private String name;
	private String last;
	public int getId_u() {
		return id_u;
	}
	public void setId_u(int id_u) {
		this.id_u = id_u;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public TableModel() {}
	public TableModel(int id_u, String login, String pass, String name, String last) {
		super();
		this.id_u = id_u;
		this.login = login;
		this.pass = pass;
		this.name = name;
		this.last = last;
	}
	
}
	
	
