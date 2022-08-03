package com.lucasfagunda.semestral;

import static com.lucasfagunda.semestral.PesquisaController.estabelecimento;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;


public class NovoEstabelecimentoController implements Initializable {
    
    private Stage stage;
    
    @FXML
    private TextField tipoLocalTextField;
    @FXML
    private TextField cnpjTextField;

    @FXML
    private TextField distanciaTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField enderecoTextField;

    @FXML
    private TextField generoMusicalTextField;

    @FXML
    private TextField horaAberturaTextField;

    @FXML
    private TextField horaFechamentoTextField;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField precoTextField;

    @FXML
    private MenuItem restauranteMenuItem;

    @FXML
    private PasswordField senhaPasswordField;

    @FXML
    private TextField telefoneTextField;
    
    @FXML
    private AnchorPane mainAnchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
     @FXML
    void salvar() throws IOException, Exception {
        AES aes = new AES();
        aes.initFromStrings("CHuO1Fjd8YgJqTyapibFBQ==","e3IYYJC2hxe24/EO");
        estabelecimento = new Estabelecimento();
        estabelecimento.setId(String.valueOf(new Random().nextInt(999)));
        estabelecimento.setNome(nomeTextField.getText());
        estabelecimento.setEmail(emailTextField.getText());
        estabelecimento.setSenha(aes.encrypt(senhaPasswordField.getText()));
         System.out.println(estabelecimento.senha);
        estabelecimento.setEndereco(enderecoTextField.getText());
        estabelecimento.setHoraAbertura(horaAberturaTextField.getText());
        estabelecimento.setHoraFechamento(horaFechamentoTextField.getText());
        estabelecimento.setDistancia(Double.parseDouble(distanciaTextField.getText()));
        estabelecimento.setGenero(generoMusicalTextField.getText());
        estabelecimento.setCnpj(cnpjTextField.getText());
        estabelecimento.setFone(telefoneTextField.getText());
        estabelecimento.setEntrada(Double.parseDouble(precoTextField.getText()));
        estabelecimento.setTipo(tipoLocalTextField.getText());
        
        new EstabelecimentoDAO().save(estabelecimento);
        
        App.setRoot("login");
    }
    
    
    @FXML
    void voltar() throws IOException {
        App.setRoot("login");
    }
    
}
