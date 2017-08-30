package application.model;

public class TableActivityModel {

	private int id_a;
	private int id_u;
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
	public TableActivityModel() {}
	public TableActivityModel(int id_a, int id_u, double material, double courseX, double courseY, double delegation) {
		super();
		this.id_a = id_a;
		this.id_u = id_u;
		this.material = material;
		this.courseX = courseX;
		this.courseY = courseY;
		this.delegation = delegation;
	}
	
}
