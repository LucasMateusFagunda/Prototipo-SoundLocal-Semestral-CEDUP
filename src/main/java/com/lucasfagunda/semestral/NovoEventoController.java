package com.lucasfagunda.semestral;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.Random;

import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NovoEventoController implements Initializable {


    @FXML
    private TextField nomeEventoTextField;
    @FXML
    private TextField generoEventoTextField;
    @FXML
    private TextArea descricaoEventoTextArea;
    @FXML
    private TextField dataEventoInicioTextField;
    @FXML
    private TextField dataEventoFimTextField;
    @FXML
    private TextField horaEventoInicioTextField;
    @FXML
    private TextField horaEventoFimTextField;
    @FXML
    private CheckBox eventoPrincipalCheckBox;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
    
    @FXML
    private void salvar() throws IOException {
        Random random = new Random();
        
        
        boolean controle = true;
        
        while(controle) {
            Integer idInt = random.nextInt(999);
            System.out.println(idInt);
            controle = new EventoDAO().verifyExsistsById(idInt.toString());
            if (!controle) {
                ListaEventosController.evento.setId(idInt.toString());
                ListaEventosController.evento.setNome(nomeEventoTextField.getText());
                ListaEventosController.evento.setGenero(generoEventoTextField.getText());
                ListaEventosController.evento.setDescricao(descricaoEventoTextArea.getText());
                ListaEventosController.evento.setDataInicio(dataEventoInicioTextField.getText());
                ListaEventosController.evento.setDataFim(dataEventoFimTextField.getText());
                ListaEventosController.evento.setHorarioInicio(horaEventoInicioTextField.getText());
                ListaEventosController.evento.setHorarioFim(horaEventoFimTextField.getText());
                ListaEventosController.evento.setEventoPrincipal(false);
                ListaEventosController.evento.setIdEstab(PesquisaController.estabelecimento.id);
                new EventoDAO().save(ListaEventosController.getEvento());
                App.setRoot("listaEventos");
              
            }
        }
    }

    @FXML
    private void cancelar() throws IOException {
        App.setRoot("listaEventos");
    }
}
