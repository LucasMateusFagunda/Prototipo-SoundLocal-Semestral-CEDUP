package com.lucasfagunda.semestral;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class NovoSubEventoController implements Initializable {

    @FXML
    private TextField dataEventoFimTextField;

    @FXML
    private TextField dataEventoInicioTextField;

    @FXML
    private TextArea descricaoEventoTextArea;

    @FXML
    private TextField generoEventoTextField;

    @FXML
    private TextField horaEventoFimTextField;

    @FXML
    private TextField horaEventoInicioTextField;

    @FXML
    private TextField nomeEventoTextField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    void cancelar() throws IOException {
        App.setRoot("subEvento");
    }

    @FXML
    void salvar() throws IOException {
        Random random = new Random();


        boolean controle = true;
        while(controle) {
            Integer idInt = random.nextInt(999);
            System.out.println(idInt);
            controle = new EventoDAO().verifyExsistsById(idInt.toString());
            if (!controle) {
                SubEventoController.subEvento.setId(String.valueOf(random.nextInt(999)));
                SubEventoController.subEvento.setNome(nomeEventoTextField.getText());
                SubEventoController.subEvento.setGenero(generoEventoTextField.getText());
                SubEventoController.subEvento.setDescricao(descricaoEventoTextArea.getText());
                SubEventoController.subEvento.setDataInicio(dataEventoInicioTextField.getText());
                SubEventoController.subEvento.setDataFim(dataEventoFimTextField.getText());
                SubEventoController.subEvento.setHorarioInicio(horaEventoInicioTextField.getText());
                SubEventoController.subEvento.setHorarioFim(horaEventoFimTextField.getText());
                SubEventoController.subEvento.setIdPai(ListaEventosController.getEvento().getId());
                SubEventoController.subEvento.setEventoPrincipal(false);
                SubEventoController.subEvento.setIdEstab(PesquisaController.estabelecimento.id);
                
                
                
                
                new EventoDAO().save(SubEventoController.getSubEvento());


                App.setRoot("subEvento");
            }
        }
    }
    
}
