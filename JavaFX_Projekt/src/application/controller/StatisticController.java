package application.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.database.DBConnector;
import application.model.TableActivityModel;
import application.model.TableResultModel;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;

public class StatisticController {

    @FXML
    private ComboBox<Integer> combo;
    @FXML
    private Label lbl_salary;
    @FXML
    private Button btn_check;
    @FXML
    private AnchorPane ap;
    
    public DBConnector db;
    public ObservableList<TableResultModel> data3;
    public ObservableList<TableActivityModel> data2;

    @FXML
    void btnCheckAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	try{
    		Connection conn = db.Connection();
    		ap.setVisible(true);
	    	int month = combo.getSelectionModel().getSelectedItem();
	    	int hmat=0,hx=0,hy=0,hdeleg=0;
	    	double mat=0,x=0,y=0,deleg=0;
	    	double salary=0;
	    
	
	    	ResultSet rs = conn.createStatement().executeQuery("select * from activity natural join results where month="+month+";");
	    	if(!rs.next()){
	    		throw new NullPointerException();
	    	}
	    	rs.previous();
	    	while(rs.next()) {
	    	hmat=rs.getInt(9);
	    	hx=rs.getInt(10);
	    	hy=rs.getInt(11);
	    	hdeleg=rs.getInt(12);
	    	System.out.println(hmat+" "+hx+" "+hy+" "+hdeleg);
	    	
	    	mat =rs.getDouble(3);
	    	x=rs.getDouble(4);
	    	y=rs.getDouble(5);
	    	deleg=rs.getDouble(6);
	    
	    	System.out.println(mat+" "+x+" "+y+" "+deleg);
	    	
	    	salary+=hmat*mat+hx*x+hy*y+hdeleg*deleg;
	    //	lbl_salary_br.setText(String.valueOf(salary));
	    //	lbl_salary_net.setText(String.valueOf(Math.round(salary/1.23*100)/100d));
	    	System.out.println(Math.round(salary/1.23*100)/100d);
	    	}
	    	lbl_salary.setText(String.valueOf(salary));
    	}catch(NullPointerException e) {
    		Alert a = new Alert(AlertType.INFORMATION);
			a.setContentText("W tym miesi¹cu nie wypracowano jeszcze ¿adnych godzin");
			a.setTitle("B³¹d");
			a.setHeaderText("UWAGA!");
			a.showAndWait();
			ap.setVisible(false);
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