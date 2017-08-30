package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.database.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController {
    private static final AlertType Warning = null;
    @FXML
    private TextField tf_login;
    @FXML
    private PasswordField pf_pass;
    @FXML
    private TextField tf_pass;
    @FXML
    private Button btn_show;
    @FXML
    private Button btn_db;
    @FXML
    private Button btn_close;
    
    public DBConnector db;
    String logowanie;
    String uprawnienia;
    static int idPracownika;
    @FXML
    void showAction(MouseEvent event) {
    	pf_pass.setVisible(false);
    	tf_pass.setVisible(true);
    	String pass = pf_pass.getText();
    	tf_pass.setText(pass);
    	pf_pass.setText("");
    }
    @FXML
    void hideAction(MouseEvent event) {
    	pf_pass.setVisible(true);
    	tf_pass.setVisible(false);
    	String pass = tf_pass.getText();
    	pf_pass.setText(pass);
    	tf_pass.setText("");
    }
    @FXML
    void closeAction(MouseEvent event) {
    	System.exit(0);
    }
  
    @FXML
    void loginDBAction(MouseEvent event) throws ClassNotFoundException, SQLException, IOException {
    	Connection conn1 = db.Connection();
    	Statement stat = conn1.createStatement();
    	try {
    	ResultSet rs = stat.executeQuery("select * from users where login ='"+tf_login.getText()+"'and pass='"+pf_pass.getText()+"';");
    	boolean next = rs.next();
    	if(rs.getString("perm").equals("111")) {			 // logowanie pracodawcy
	    	if(next) { 
	    		logowanie = rs.getString("login");
	    		Stage stageTable = new Stage();
	    		Parent parent =(Parent) FXMLLoader.load(getClass().getResource("/application/view/DBAdminView.fxml"));
	    		Scene sceneTable = new Scene(parent);
	    		stageTable.setScene(sceneTable);
	    		stageTable.setTitle("Pracodawca");
	    		stageTable.getIcons().add(new Image("http://www.iconsplace.com/download/orange-database-512.png"));
	    		stageTable.show();
	    	}else {
	    		Alert a = new Alert(AlertType.INFORMATION);
	    		a.setContentText("Poda³eœ b³êdny login lub has³o");
	    		a.setTitle("B³¹d");
	    		a.setHeaderText("UWAGA!");
	    		a.showAndWait();
	    	}   
    	}else {
    		if(next) { 										// logowanie pracownika
    			idPracownika = rs.getInt("id_u");
    			logowanie = rs.getString("login");
	    		Stage stageTable = new Stage();
	    		Parent parent =(Parent) FXMLLoader.load(getClass().getResource("/application/view/DBUserView.fxml"));
	    		Scene sceneTable = new Scene(parent);
	    		stageTable.setScene(sceneTable);
	    		stageTable.setTitle("Pracownik");
	    		stageTable.getIcons().add(new Image("http://www.iconsplace.com/download/orange-database-512.png"));
	    		stageTable.show();
	    	}else {
	    		Alert a = new Alert(AlertType.INFORMATION);
	    		a.setContentText("Poda³eœ b³êdny login lub has³o");
	    		a.setTitle("B³¹d");
	    		a.setHeaderText("UWAGA!");
	    		a.showAndWait();
	    	}   
    	}
    	}catch(SQLException e) {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Poda³eœ b³êdny login lub has³o");
    		a.setTitle("B³¹d");
    		a.setHeaderText("UWAGA!");
    		a.showAndWait();
    	}
    }
    public void initialize() {
    	db = new DBConnector();
    }

}
