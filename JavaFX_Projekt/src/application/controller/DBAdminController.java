package application.controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.database.DBConnector;
import application.model.TableModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class DBAdminController {
	
	@FXML
	private TableView<TableModel> TableTwo;
	@FXML
    private TableColumn<TableModel, Integer> col_id;
    @FXML
    private TableColumn<TableModel, String> col_name;
    @FXML
    private TableColumn<TableModel, String> col_last;
    @FXML
    private TableColumn<TableModel, Double> col_salary;
    @FXML
    private Label lbl_name;
    @FXML
    private Label lbl_last;
    @FXML
    private Label lbl_salary;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_last;
    @FXML
    private TextField tf_salary;
    @FXML
    private Button btn_insert;
    @FXML
    private Button btn_update;
    @FXML
    private Button select;
    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private Button insert;
    
    public DBConnector db;
    public ObservableList<TableModel> data;
    
    @FXML
    void btnSelectAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = db.Connection();
    	data = FXCollections.observableArrayList();
    	ResultSet rs = conn.createStatement().executeQuery("select * from employee;");
    	while(rs.next()) {
    		data.add(new TableModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4)));
    	}
    	col_id.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("id"));
    	col_name.setCellValueFactory(new PropertyValueFactory<TableModel,String>("name"));
    	col_last.setCellValueFactory(new PropertyValueFactory<TableModel,String>("last"));
    	col_salary.setCellValueFactory(new PropertyValueFactory<TableModel,Double>("salary"));
    	TableTwo.setItems(null);
    	TableTwo.setItems(data);
    }
    @FXML
    void btnDeleteAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = db.Connection();
    	try {
    	int id_del = TableTwo.getSelectionModel().getSelectedItem().getId();

    	String sql="delete from employee where id="+id_del+";";
    	
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.executeUpdate();
    	btnSelectAction(event);
    	}catch(NullPointerException e) {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Nie zaznaczono ¿adnego rekordu");
    		a.setTitle("B³¹d");
    		a.setHeaderText("UWAGA!");
    		a.showAndWait();
    	}
    	}
    	
    
    @FXML
    void btnInsertAction(ActionEvent event){
    	lbl_name.setDisable(false);
    	lbl_last.setDisable(false);
    	lbl_salary.setDisable(false);
    	tf_name.setDisable(false);
    	tf_last.setDisable(false);
    	tf_salary.setDisable(false);
    //	btn_update.setDisable(false);
    	btn_insert.setDisable(false);
    
    }
    @FXML
    void btnInsertCommitAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = db.Connection();
    	lbl_name.setDisable(false);
    	lbl_last.setDisable(false);
    	lbl_salary.setDisable(false);
    	tf_name.setDisable(false);
    	tf_last.setDisable(false);
    	tf_salary.setDisable(false);
    	btn_insert.setDisable(false);
    	if(tf_name.getText().equals("") ||tf_last.getText().equals("") ||tf_salary.getText().equals("")) {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Nie wpisa³eœ wszystkich danych");
    		a.setTitle("B³¹d");
    		a.setHeaderText("UWAGA!");
    		a.showAndWait();
    	}else {
    		String sql="insert into employee (name, last, salary) values('"+tf_name.getText()+"','"+tf_last.getText()+"',"+tf_salary.getText()+");";
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.executeUpdate();
        	btnSelectAction(event);	
    	}
    	tf_name.setText("");
    	tf_last.setText("");
    	tf_salary.setText("");
    	lbl_name.setDisable(true);
    	lbl_last.setDisable(true);
    	lbl_salary.setDisable(true);
    	tf_name.setDisable(true);
    	tf_last.setDisable(true);
    	tf_salary.setDisable(true);
    	btn_insert.setDisable(true);
    }
    @FXML
    void btnUpdateAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	try {
        	tf_name.setText(TableTwo.getSelectionModel().getSelectedItem().getName());
        	tf_last.setText(TableTwo.getSelectionModel().getSelectedItem().getLast());
        	tf_salary.setText(String.valueOf(TableTwo.getSelectionModel().getSelectedItem().getSalary()));
    		lbl_name.setDisable(false);
        	lbl_last.setDisable(false);
        	lbl_salary.setDisable(false);
        	tf_name.setDisable(false);
        	tf_last.setDisable(false);
        	tf_salary.setDisable(false);
        	btn_update.setDisable(false);
        //	btn_insert.setDisable(false);    	
    	}catch(NullPointerException e) {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Zaznacz rekord, który chcesz zmodyfikowaæ");
    		a.setTitle("B³¹d");
    		a.setHeaderText("UWAGA!");
    		a.showAndWait();
    	}

    }
    @FXML
    void btnUpdateCommitAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = db.Connection();
    	int id_u = TableTwo.getSelectionModel().getSelectedItem().getId();
    	String sql="update employee set name='"+tf_name.getText()+"', last='"+tf_last.getText()+"', salary='"+tf_salary.getText()+"' where id="+id_u+";";
    	
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.executeUpdate();
    	btnSelectAction(event);
    	tf_name.setText("");
    	tf_last.setText("");
    	tf_salary.setText("");
    	lbl_name.setDisable(true);
    	lbl_last.setDisable(true);
    	lbl_salary.setDisable(true);
    	tf_name.setDisable(true);
    	tf_last.setDisable(true);
    	tf_salary.setDisable(true);
    	btn_update.setDisable(true);
    }
    @FXML
    void initialize() {
    	db = new DBConnector();
    }

}
