package application.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.database.DBConnector;
import application.model.TableActivityModel;
import application.model.TableModel;
import application.model.TableResultModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class EndController {

    @FXML
    private ComboBox<Integer> combo;
    @FXML
    private AnchorPane ap_result;
    @FXML
    private Label lbl_salary_net;
    @FXML
    private Label lbl_salary_br;
    @FXML
    private Button btn_check;
    
    public DBConnector db;
    public ObservableList<TableResultModel> data3;
    public ObservableList<TableActivityModel> data2;
    public PropertyValueFactory a;
    @FXML
    void btnCheckAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	try{
    		Connection conn = db.Connection();
	    	ap_result.setVisible(true);
	    	int month = combo.getSelectionModel().getSelectedItem();
	    	int hmat=0,hx=0,hy=0,hdeleg=0;
	    	double mat=0,x=0,y=0,deleg=0;
	    	System.out.println(month);
	    	System.out.println(LoginController.idPracownika);
	    	ResultSet rs = conn.createStatement().executeQuery("select * from results where month="+month+" and id_u="+LoginController.idPracownika+";");
	    	if(!rs.next()){
	    		throw new NullPointerException();
	    	}
	    	//rs.next();
	    	hmat=rs.getInt(4);
	    	hx=rs.getInt(5);
	    	hy=rs.getInt(6);
	    	hdeleg=rs.getInt(7);
	    	System.out.println(hmat+" "+hx+" "+hy+" "+hdeleg);
	    	
	    	rs = conn.createStatement().executeQuery("select * from activity where id_u="+LoginController.idPracownika+";");
	    	rs.next();
	    	mat =rs.getDouble(3);
	    	x=rs.getDouble(4);
	    	y=rs.getDouble(5);
	    	deleg=rs.getDouble(6);
	    
	    	System.out.println(mat+" "+x+" "+y+" "+deleg);
	    	double salary;
	    	salary=hmat*mat+hx*x+hy*y+hdeleg*deleg;
	    	lbl_salary_br.setText(String.valueOf(salary));
	    	lbl_salary_net.setText(String.valueOf(Math.round(salary/1.23*100)/100d));
    	}catch(NullPointerException e) {
    		Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText("W tym miesi¹cu nie dodano jeszcze iloœci przepracowanych godzin");
			a.setTitle("B³¹d");
			a.setHeaderText("UWAGA!");
			a.showAndWait();
    	}
    }
    public void initialize(){
    	db = new DBConnector();
    	for(int i =1; i<=12; i++) {
    	combo.getItems().add(i);
    	}
    	combo.getSelectionModel().select(0);
    }

}
