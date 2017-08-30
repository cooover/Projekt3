package application.model;

public class TableModel {
	private int id_u;
	private String login;
	private String pass;
	private String name;
	private String last;
	private int id_a;
	private double material;
	private double courseX;
	private double courseY;
	private double delegation;
	public int getId_a() {
		return id_a;
	}
	public void setId_a(int id_a) {
		this.id_a = id_a;
	}
	public int getId_u() {
		return id_u;
	}
	public void setId_u(int id_u) {
		this.id_u = id_u;
	}
	public double getMaterial() {
		return material;
	}
	public void setMaterial(double material) {
		this.material = material;
	}
	public double getCourseX() {
		return courseX;
	}
	public void setCourseX(double courseX) {
		this.courseX = courseX;
	}
	public double getCourseY() {
		return courseY;
	}
	public void setCourseY(double courseY) {
		this.courseY = courseY;
	}
	public double getDelegation() {
		return delegation;
	}
	public void setDelegation(double delegation) {
		this.delegation = delegation;
	}
	
	public TableModel(int id_a, int id_u, double material, double courseX, double courseY, double delegation) {
		super();
		this.id_a = id_a;
		this.id_u = id_u;
		this.material = material;
		this.courseX = courseX;
		this.courseY = courseY;
		this.delegation = delegation;
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
