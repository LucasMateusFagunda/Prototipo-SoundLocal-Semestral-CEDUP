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


public class VisualizarSubEventoController implements Initializable {

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
        preencher();
    }    
    
    public void preencher() {
        Evento subEvento = PaginaEstabelecimentoController.subEvento;
        nomeEventoTextField.setText(subEvento.nome);
        generoEventoTextField.setText(subEvento.genero);
        descricaoEventoTextArea.setText(subEvento.descricao);
        dataEventoInicioTextField.setText(subEvento.dataInicio);
        dataEventoFimTextField.setText(subEvento.dataFim);
        horaEventoInicioTextField.setText(subEvento.horarioInicio);
        horaEventoFimTextField.setText(subEvento.horarioFim);
    }
    
    @FXML
    void voltar() throws IOException {
        App.setRoot("paginaEstabelecimento");
    }

    
}
