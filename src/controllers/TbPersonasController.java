package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Persona;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

public class TbPersonasController implements Initializable{

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfApellidos;

    @FXML
    private TextField tfEdad;

    @FXML
    private Button btnAgregarPersona;

    @FXML
    private TableView<Persona> tbViewPersonas;

    @FXML
    private TableColumn<Persona, String> tbColNombre;

    @FXML
    private TableColumn<Persona, String> tbColApellidos;

    @FXML
    private TableColumn<Persona, Integer> tbColEdad;
    
    @FXML
    private Button btnModificar;

    @FXML
    private Button btnEliminar;
    
    @FXML
    private Label lblPersona;
    
    @FXML
    void aniadirPersona(ActionEvent event) {
    	
		Image icono = new Image(Main.class.getResourceAsStream("/img/agenda.png"));
    	
    	/*
    	 * Si algunos de los TextFields está vacio entonces saltará una Ventana 
    	 * 	de Error con los campos NULL
    	 * */
    	if (tfNombre.getText().isEmpty() || tfApellidos.getText().isEmpty() || tfEdad.getText().isEmpty() || !tfEdad.getText().matches("[0-9]*")) {
    		Alert alertWindows = new Alert(Alert.AlertType.ERROR);
    		
    		Stage stage = (Stage) alertWindows.getDialogPane().getScene().getWindow();
    		stage.getIcons().add(icono);
    		
    		alertWindows.setHeaderText(null);
    		String mensaje = "";
			if (tfNombre.getText().isEmpty()){
				mensaje += "El campo Nombre es Obligatorio \n";
			}
			if (tfApellidos.getText().isEmpty()) {
				mensaje += "El campo Apellidos es Obligatorio \n";
			}
			if (tfEdad.getText().isEmpty()) {
				mensaje += "El campo Edad es Obligatorio \n";
			}
			if (!tfEdad.getText().matches("[0-9]*")) {
				mensaje += "El campo Edad debe ser númerico \n";
			}
			
			alertWindows.setContentText(mensaje);
    		alertWindows.showAndWait();
    		
    	} else {
    	/*
    	 * Añadirá la persona a la tabla
    	 * */
    		Persona persona = new Persona(tfNombre.getText(),
	    			tfApellidos.getText(),
	                Integer.parseInt(tfEdad.getText()));
	        	
	    	ObservableList<Persona> obLstPersonas = tbViewPersonas.getItems();
	    	obLstPersonas.add(persona);
	        tbViewPersonas.setItems(obLstPersonas);
	        
	       /* Saltará una ventana de Información que
	        * 	indicará que se ha añadido la persona
	        * */
	        Alert infoWindow = new Alert(Alert.AlertType.INFORMATION);
	        
	        Stage stage = (Stage) infoWindow.getDialogPane().getScene().getWindow();
    		stage.getIcons().add(icono);
	        
	        infoWindow.setHeaderText(null);
	        infoWindow.setContentText("Persona agregada correctamente");
	        infoWindow.showAndWait();
	        
	        /* Eliminar texto dentrode los campos TextFields*/
    		tfNombre.setText(" ".toString());
        	
        	tfApellidos.setText(" ".toString());
        	
        	tfEdad.setText(" ".toString());
	        
    	}
    }
    
    @FXML
    void selectPersona(MouseEvent event) {
    	
    	if (tbViewPersonas.getSelectionModel().getSelectedItem() != null) {
    		Persona personMod = tbViewPersonas.getSelectionModel().getSelectedItem();
        	
        	tfNombre.setText(personMod.getNombre().toString());
        	
        	tfApellidos.setText(personMod.getApellido().toString());
        	
        	tfEdad.setText(personMod.getEdad()+"".toString());
    	}
    }
    
    @FXML
    void modPersona(ActionEvent event) {
    	
    }
    
    @FXML
    void eliminarPersona(ActionEvent event) {
    	
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		tbColNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
        
		tbColApellidos.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getApellido()));
        
		tbColEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
	}

}

