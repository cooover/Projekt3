package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import application.database.DBConnector;
import application.model.TableModel;
import application.model.TableResultModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DBUserController {

    @FXML
    private ComboBox<Integer> combo;
    @FXML
    private TextField tf_deleg_h;
    @FXML
    private TextField tf_mat_h;
    @FXML
    private TextField tf_x_h;
    @FXML
    private TextField tf_y_h;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_close;
    @FXML
    private Button btn_show;
   
    
    public DBConnector db;
    public ObservableList<TableResultModel> data;
    @FXML
    void btnBackAction(ActionEvent event) {
    	tf_deleg_h.setText("");
    	tf_mat_h.setText("");
    	tf_x_h.setText("");
    	tf_y_h.setText("");
    }
    @FXML
    void btnCloseAction(ActionEvent event) {
    	System.exit(0);
    }
    @FXML
    void btnOkAction(ActionEvent event) throws ClassNotFoundException, SQLException, IOException {
    	Connection conn = db.Connection();
    	if(tf_mat_h.getText().equals("") ||tf_x_h.getText().equals("") ||tf_y_h.getText().equals("") ||tf_deleg_h.getText().equals("")) {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Nie wpisa³eœ wszystkich danych");
    		a.setTitle("B³¹d");
    		a.setHeaderText("UWAGA!");
    		a.showAndWait();
    	}else {
	    	try {
	    		int month = combo.getSelectionModel().getSelectedItem();
	        	ResultSet rs = conn.createStatement().executeQuery("select * from results where month="+month+" and id_u="+LoginController.idPracownika+";");
	        	if(rs.next()){
	        		throw new MySQLIntegrityConstraintViolationException();
	        	}
	    		String sql="insert into results (month, id_u, training_materials_h, courseX_h, courseY_h, delegation_h) values("+combo.getSelectionModel().getSelectedItem()+","+LoginController.idPracownika+","+tf_mat_h.getText()+","+tf_x_h.getText()+","+tf_y_h.getText()+","+tf_deleg_h.getText()+");";
	        	PreparedStatement ps = conn.prepareStatement(sql);
	        	ps.executeUpdate();
	
	        	Stage stageTable = new Stage();
	    		Parent parent =(Parent) FXMLLoader.load(getClass().getResource("/application/view/EndView.fxml"));
	    		Scene sceneTable = new Scene(parent);
	    		stageTable.setScene(sceneTable);
	    		stageTable.setTitle("WYNIKI");
	    		stageTable.getIcons().add(new Image("http://www.iconsplace.com/download/orange-database-512.png"));
	    		stageTable.show();
	    	}catch(MySQLSyntaxErrorException e) {
	    		Alert a = new Alert(AlertType.INFORMATION);
	    		a.setContentText("Wprowadzi³eœ z³y znak");
	    		a.setTitle("B³¹d");
	    		a.setHeaderText("UWAGA!");
	    		a.showAndWait();
	    	}catch (MySQLIntegrityConstraintViolationException e) {
				Alert a = new Alert(AlertType.INFORMATION);
				a.setContentText("W tym miesi¹cu dodano ju¿ przepracowane godziny. W celu zmiany skonaktuj siê z administratorem");
				a.setTitle("B³¹d");
				a.setHeaderText("UWAGA!");
				a.showAndWait();
	    	}
    	}
    }
    @FXML
    void btnShowAction(ActionEvent event) throws IOException {
    	Stage stageTable = new Stage();
		Parent parent =(Parent) FXMLLoader.load(getClass().getResource("/application/view/EndView.fxml"));
		Scene sceneTable = new Scene(parent);
		stageTable.setScene(sceneTable);
		stageTable.setTitle("WYNIKI");
		stageTable.getIcons().add(new Image("http://www.iconsplace.com/download/orange-database-512.png"));
		stageTable.show();
    }
    public void initialize(){
    	db = new DBConnector();
    	for(int i =1; i<=12; i++) {
    	combo.getItems().add(i);
    	}
    	combo.getSelectionModel().select(0);
    }

}
