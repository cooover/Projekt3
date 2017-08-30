package application.model;

public class TableResultModel {
	
	private int id_r;
	private int month;
	private int id_u;
	private int materialH;
	private int courseXH;
	private int courseYH;
	private int delegationH;
	public int getId_r() {
		return id_r;
	}
	public void setId_r(int id_r) {
		this.id_r = id_r;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getId_u() {
		return id_u;
	}
	public void setId_u(int id_u) {
		this.id_u = id_u;
	}
	public int getMaterialH() {
		return materialH;
	}
	public void setMaterialH(int materialH) {
		this.materialH = materialH;
	}
	public int getCourseXH() {
		return courseXH;
	}
	public void setCourseXH(int courseXH) {
		this.courseXH = courseXH;
	}
	public int getCourseYH() {
		return courseYH;
	}
	public void setCourseYH(int courseYH) {
		this.courseYH = courseYH;
	}
	public int getDelegationH() {
		return delegationH;
	}
	public void setDelegationH(int delegationH) {
		this.delegationH = delegationH;
	}
	public TableResultModel() {}
	public TableResultModel(int id_r, int month, int id_u, int materialH, int courseXH, int courseYH, int delegationH) {
		super();
		this.id_r = id_r;
		this.month = month;
		this.id_u = id_u;
		this.materialH = materialH;
		this.courseXH = courseXH;
		this.courseYH = courseYH;
		this.delegationH = delegationH;
	}
	
}
