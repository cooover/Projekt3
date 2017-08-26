package application.controller;

import application.model.TableModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DBUserController {

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
    @FXML
    void btnDeleteAction(ActionEvent event) {

    }

    @FXML
    void btnInsertAction(ActionEvent event) {

    }

    @FXML
    void btnInsertCommitAction(ActionEvent event) {

    }

    @FXML
    void btnSelectAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateCommitAction(ActionEvent event) {

    }

}