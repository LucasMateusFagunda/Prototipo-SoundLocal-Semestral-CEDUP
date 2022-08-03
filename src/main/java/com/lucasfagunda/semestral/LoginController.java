
package com.lucasfagunda.semestral;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mário
 */
public class LoginController implements Initializable {
    
    @FXML
    TextField loginEmailTextField;
    
    @FXML
    PasswordField loginSenhaPasswordField;
    
    @FXML
    private AnchorPane mainAnchorPane;
    
    static Estabelecimento estabelecimentoAtivo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stage stage = new Stage();
        stage.setHeight(mainAnchorPane.getHeight());
        stage.setWidth(mainAnchorPane.getWidth());
    }    
    
    @FXML
    void login() throws IOException, SQLException, Exception{
        
        AES aes = new AES();
        aes.initFromStrings("CHuO1Fjd8YgJqTyapibFBQ==","e3IYYJC2hxe24/EO");
        
        Estabelecimento EstabelecimentoLogin = new Estabelecimento();
        EstabelecimentoLogin.setEmail(loginEmailTextField.getText());
        EstabelecimentoLogin.setSenha(aes.encrypt(loginSenhaPasswordField.getText()));
        
        if(new EstabelecimentoDAO().login(EstabelecimentoLogin) == true){
            estabelecimentoAtivo = new Estabelecimento();
            estabelecimentoAtivo.idAtivo = new EstabelecimentoDAO().getByLogin(EstabelecimentoLogin);
            App.setRoot("pesquisa");
        }else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Autenticação");
            alert.setHeaderText(null);
            alert.setContentText("Email ou senha inválidos");
            
            alert.showAndWait(); 
        }
    }
    
    @FXML
    void guestLogin() throws IOException{
        estabelecimentoAtivo = null;
        App.setRoot("pesquisa");
    }
    
    @FXML
    public void cadastrar() throws IOException {
        App.setRoot("novoEstabelecimento");
    }
    
    //Método chamado quando o usuário pressiona alguma tecla no teclado (em um TextField marcado com ele)
    @FXML
    void onUserTyped(){
        System.out.println("Tais digitando");
    }
    
}
