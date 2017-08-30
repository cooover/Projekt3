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
	private Label lbl_name;
    @FXML
    private Label lbl_last;
    @FXML
    private Label lbl_log;
    @FXML
    private Label lbl_pass;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_last;
    @FXML
    private TextField tf_log;
    @FXML
    private TextField tf_pass;
    @FXML
    private Button btn_insert;
    @FXML
    private Button btn_update;
    @FXML
    private Button insert;
    @FXML
    private Button select;
    @FXML
    private Button delete;
    @FXML
    private Button update;
    @FXML
    private TableColumn<TableModel, String> col_log;
    @FXML
    private TableColumn<TableModel, String> col_pass;
	@FXML
	private TableView<TableModel> TableTwo;
	@FXML
    private TableColumn<TableModel, Integer> col_id;
    @FXML
    private TableColumn<TableModel, String> col_name;
    @FXML
    private TableColumn<TableModel, String> col_last;   
    @FXML
    private TableView<TableModel> TableThree;
    @FXML
    private TableColumn<TableModel, Integer> col3_id_a;
    @FXML
    private TableColumn<TableModel, Integer> col3_id_u;
    @FXML
    private TableColumn<TableModel, Double> col3_mat;
    @FXML
    private TableColumn<TableModel, Double> col3_x;
    @FXML
    private TableColumn<TableModel, Double> col3_y;
    @FXML
    private TableColumn<TableModel, Double> col3_deleg;
    @FXML
    private Button btn_select_p;
    @FXML
    private Button btn_update_p;
    @FXML
    private Label lbl_mat;
    @FXML
    private Label lbl_x;
    @FXML
    private Label lbl_y;
    @FXML
    private Label lbl_deleg;
    @FXML
    private TextField tf_mat;
    @FXML
    private TextField tf_x;
    @FXML
    private TextField tf_y;
    @FXML
    private TextField tf_deleg;
    @FXML
    private Button btn_updateP;
    @FXML
    private Label lbl_id_emp;
    @FXML
    private TextField tf_id_emp;
    @FXML
    private Button btn_insertP;
    
    public DBConnector db;
    public ObservableList<TableModel> data;
    public ObservableList<TableModel> data3;
    
    @FXML
    void btnSelectAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = db.Connection();
    	data = FXCollections.observableArrayList();
    	ResultSet rs = conn.createStatement().executeQuery("select * from users where perm='100';");
    	while(rs.next()) {
    		data.add(new TableModel(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(5),rs.getString(6)));
    	}
    	col_id.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("id_u"));
    	col_name.setCellValueFactory(new PropertyValueFactory<TableModel,String>("name"));
    	col_last.setCellValueFactory(new PropertyValueFactory<TableModel,String>("last"));
    	col_log.setCellValueFactory(new PropertyValueFactory<TableModel,String>("login"));
    	col_pass.setCellValueFactory(new PropertyValueFactory<TableModel,String>("pass"));
    	TableTwo.setItems(null);
    	TableTwo.setItems(data);
    }
    @FXML
    void btnDeleteAction(ActionEvent event) throws ClassNotFoundException, SQLException {
     	Connection conn = db.Connection();
    	try {
    	int id_del = TableTwo.getSelectionModel().getSelectedItem().getId_u();

    	String sql="delete from users where id_u="+id_del+";";
    	
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
    	select.setDisable(true);
    	update.setDisable(true);
    	delete.setDisable(true);
    	insert.setDisable(true);
    	lbl_name.setDisable(false);
    	lbl_last.setDisable(false);
    	lbl_log.setDisable(false);
    	lbl_pass.setDisable(false);
    	tf_name.setDisable(false);
    	tf_last.setDisable(false);
    	tf_log.setDisable(false);
    	tf_pass.setDisable(false);
    //	btn_update.setDisable(false);
    	btn_insert.setDisable(false);
    
    }
    @FXML
    void btnInsertCommitAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = db.Connection();
    	lbl_name.setDisable(false);
    	lbl_last.setDisable(false);
    	lbl_log.setDisable(false);
    	lbl_pass.setDisable(false);
    	tf_name.setDisable(false);
    	tf_last.setDisable(false);
    	tf_log.setDisable(false);
    	tf_pass.setDisable(false);
    	btn_insert.setDisable(false);
    	if(tf_name.getText().equals("") ||tf_last.getText().equals("") ||tf_log.getText().equals("") ||tf_pass.getText().equals("")) {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Nie wpisa³eœ wszystkich danych");
    		a.setTitle("B³¹d");
    		a.setHeaderText("UWAGA!");
    		a.showAndWait();
    	}else {
    		String sql="insert into users (login, pass, name, last) values('"+tf_log.getText()+"','"+tf_pass.getText()+"','"+tf_name.getText()+"','"+tf_last.getText()+"');";
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.executeUpdate();
        	btnSelectAction(event);	
    	}
    	tf_name.setText("");
    	tf_last.setText("");
    	tf_log.setText("");
    	tf_pass.setText("");
    	lbl_name.setDisable(true);
    	lbl_last.setDisable(true);
    	lbl_log.setDisable(true);
    	lbl_pass.setDisable(true);
    	tf_name.setDisable(true);
    	tf_last.setDisable(true);
    	tf_log.setDisable(true);
    	tf_pass.setDisable(true);
    	btn_insert.setDisable(true);
    	select.setDisable(false);
    	update.setDisable(false);
    	insert.setDisable(false);
    	delete.setDisable(false);
    }
    @FXML
    void btnUpdateAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	try {

        	tf_name.setText(TableTwo.getSelectionModel().getSelectedItem().getName());
        	tf_last.setText(TableTwo.getSelectionModel().getSelectedItem().getLast());
        	tf_log.setText(TableTwo.getSelectionModel().getSelectedItem().getLogin());
        	tf_pass.setText(TableTwo.getSelectionModel().getSelectedItem().getPass());
    		lbl_name.setDisable(false);
        	lbl_last.setDisable(false);
        	lbl_log.setDisable(false);
        	lbl_pass.setDisable(false);
        	tf_name.setDisable(false);
        	tf_last.setDisable(false);
        	tf_log.setDisable(false);
        	tf_pass.setDisable(false);
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
    	int id_u = TableTwo.getSelectionModel().getSelectedItem().getId_u();
    	String sql="update users set login='"+tf_log.getText()+"', pass='"+tf_pass.getText()+"', name='"+tf_name.getText()+"', last='"+tf_last.getText()+"' where id_u="+id_u+";";
    	
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.executeUpdate();
    	btnSelectAction(event);
    	tf_name.setText("");
    	tf_last.setText("");
    	tf_log.setText("");
    	tf_pass.setText("");
    	lbl_name.setDisable(true);
    	lbl_last.setDisable(true);
    	lbl_log.setDisable(true);
    	lbl_pass.setDisable(true);
    	tf_name.setDisable(true);
    	tf_last.setDisable(true);
    	tf_log.setDisable(true);
    	tf_pass.setDisable(true);
    	btn_update.setDisable(true);

    }
    @FXML
    void btnSelectPAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = db.Connection();
    	data3 = FXCollections.observableArrayList();
    	ResultSet rs = conn.createStatement().executeQuery("select * from activity;");
    	while(rs.next()) {
    		data3.add(new TableModel(rs.getInt(1),rs.getInt(2),rs.getDouble(3),rs.getDouble(4),rs.getDouble(5),rs.getDouble(6)));
    	}
    	col3_id_a.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("id_a"));
    	col3_id_u.setCellValueFactory(new PropertyValueFactory<TableModel,Integer>("id_u"));
    	col3_mat.setCellValueFactory(new PropertyValueFactory<TableModel,Double>("material"));
    	col3_x.setCellValueFactory(new PropertyValueFactory<TableModel,Double>("courseX"));
    	col3_y.setCellValueFactory(new PropertyValueFactory<TableModel,Double>("courseY"));
    	col3_deleg.setCellValueFactory(new PropertyValueFactory<TableModel,Double>("delegation"));
    	TableThree.setItems(null);
    	TableThree.setItems(data3);
    }
    @FXML
    void btnUpdatePAction(ActionEvent event) {
    	try {
        	tf_mat.setText(String.valueOf(TableThree.getSelectionModel().getSelectedItem().getMaterial()));
        	tf_x.setText(String.valueOf(TableThree.getSelectionModel().getSelectedItem().getCourseX()));
        	tf_y.setText(String.valueOf(TableThree.getSelectionModel().getSelectedItem().getCourseY()));
        	tf_deleg.setText(String.valueOf(TableThree.getSelectionModel().getSelectedItem().getDelegation()));
    		lbl_mat.setDisable(false);
        	lbl_x.setDisable(false);
        	lbl_y.setDisable(false);
        	lbl_deleg.setDisable(false);
        	tf_mat.setDisable(false);
        	tf_x.setDisable(false);
        	tf_y.setDisable(false);
        	tf_deleg.setDisable(false);
        	btn_updateP.setDisable(false);
   	
    	}catch(NullPointerException e) {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Zaznacz rekord, który chcesz zmodyfikowaæ");
    		a.setTitle("B³¹d");
    		a.setHeaderText("UWAGA!");
    		a.showAndWait();
    	}
    }

    @FXML
    void btnUpdatePCommitAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = db.Connection();
    	int id_u = TableThree.getSelectionModel().getSelectedItem().getId_u();
    	String sql="update activity set id_u="+id_u+", training_materials="+String.valueOf(tf_mat.getText())+", courseX="+String.valueOf(tf_x.getText())+", courseY="+String.valueOf(tf_y.getText())+", delegation="+String.valueOf(tf_deleg.getText())+"where id_u="+id_u+";";
    	
    	PreparedStatement ps = conn.prepareStatement(sql);
    	ps.executeUpdate();
    	btnSelectPAction(event);
    	tf_mat.setText("");
    	tf_x.setText("");
    	tf_y.setText("");
    	tf_deleg.setText("");
    	lbl_mat.setDisable(true);
    	lbl_x.setDisable(true);
    	lbl_y.setDisable(true);
    	lbl_deleg.setDisable(true);
    	tf_mat.setDisable(true);
    	tf_x.setDisable(true);
    	tf_y.setDisable(true);
    	tf_deleg.setDisable(true);
    	btn_updateP.setDisable(true);
    }

    @FXML
    void btnInsertActionP(ActionEvent event) {
    	lbl_id_emp.setVisible(true);
    	tf_id_emp.setVisible(true);
    	lbl_mat.setDisable(false);
    	lbl_x.setDisable(false);
    	lbl_y.setDisable(false);
    	lbl_deleg.setDisable(false);
    	tf_mat.setDisable(false);
    	tf_x.setDisable(false);
    	tf_y.setDisable(false);
    	tf_deleg.setDisable(false);
    	btn_insertP.setDisable(false);
    	btn_select_p.setDisable(false);
    	btn_update_p.setDisable(false);
    }
    @FXML
    void btnInsertPCommitAction(ActionEvent event) throws ClassNotFoundException, SQLException {
    	Connection conn = db.Connection();
    	lbl_mat.setDisable(false);
    	lbl_x.setDisable(false);
    	lbl_y.setDisable(false);
    	lbl_deleg.setDisable(false);
    	tf_mat.setDisable(false);
    	tf_x.setDisable(false);
    	tf_y.setDisable(false);
    	tf_deleg.setDisable(false);
    	btn_insertP.setDisable(false);
    	if(tf_mat.getText().equals("") ||tf_x.getText().equals("") ||tf_y.getText().equals("") ||tf_deleg.getText().equals("") ||tf_id_emp.getText().equals("")) {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setContentText("Nie wpisa³eœ wszystkich danych");
    		a.setTitle("B³¹d");
    		a.setHeaderText("UWAGA!");
    		a.showAndWait();
    	}else {
    		String sql="insert into activity (id_u, training_materials, courseX, courseY, delegation) values("+String.valueOf(tf_id_emp.getText())+","+String.valueOf(tf_mat.getText())+","+String.valueOf(tf_x.getText())+","+String.valueOf(tf_y.getText())+","+String.valueOf(tf_deleg.getText())+");";
        	PreparedStatement ps = conn.prepareStatement(sql);
        	ps.executeUpdate();
        	btnSelectPAction(event);	
    	}
    	tf_mat.setText("");
    	tf_x.setText("");
    	tf_y.setText("");
    	tf_deleg.setText("");
    	lbl_mat.setDisable(true);
    	lbl_x.setDisable(true);
    	lbl_y.setDisable(true);
    	lbl_deleg.setDisable(true);
    	tf_mat.setDisable(true);
    	tf_x.setDisable(true);
    	tf_y.setDisable(true);
    	tf_deleg.setDisable(true);
    	btn_insertP.setDisable(true);
    	btn_select_p.setDisable(false);
    	btn_update_p.setDisable(false);
    	lbl_id_emp.setVisible(false);
    	tf_id_emp.setVisible(false);
    }
    
    @FXML
    void initialize() {
    	db = new DBConnector();
    }

}
