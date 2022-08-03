
package com.lucasfagunda.semestral;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author Mário
 */
public class PesquisaController implements Initializable {


    @FXML
    private TextField pesquisaTextField;
    @FXML
    private SplitMenuButton menuTipoLocal;
    @FXML
    private TextField distanciaTextField;
    @FXML
    private TextField horaInicTextField;
    @FXML
    private TextField horaFinalTextField;
    @FXML
    private TextField generoTextField;
    @FXML
    private TextField entradaTextField;
    @FXML
    private TableView<Estabelecimento> pesquisaTableView;
    @FXML
    private TableColumn<Estabelecimento, String> nomeColumn;
    @FXML
    private TableColumn<Estabelecimento, String> tipoColumn;
    @FXML
    private TableColumn<Estabelecimento, Double> distanciaColumn;
    @FXML
    private TableColumn<Estabelecimento, String> horaAbreColumn;
    @FXML
    private TableColumn<Estabelecimento, String> horaFechaColumn;
    @FXML
    private TableColumn<Estabelecimento, String> generoColumn;
    @FXML
    private TableColumn<Estabelecimento, String> entradaColumn;
    
    static ObservableList<Estabelecimento> estabelecimentos;
    static Estabelecimento estabelecimento;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { //Inicializa a tabela de Estabelecimentos
        estabelecimento = new Estabelecimento();
        this.nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.tipoColumn.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        this.distanciaColumn.setCellValueFactory(new PropertyValueFactory<>("distancia"));
        this.horaAbreColumn.setCellValueFactory(new PropertyValueFactory<>("horaAbertura"));
        this.horaFechaColumn.setCellValueFactory(new PropertyValueFactory<>("horaFechamento"));
        this.generoColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));
        this.entradaColumn.setCellValueFactory(new PropertyValueFactory<>("entrada"));
        
        this.estabelecimentos = pesquisaTableView.getItems();
        
        atualizarLista();
    }    
    
    void atualizarLista() {
        estabelecimentos.clear();
        estabelecimentos.addAll(new EstabelecimentoDAO().getAll());
        pesquisaTableView.refresh();
        
    }
    
    @FXML
    private void pesquisa() { //Método de pesquisa e validação dos campos utilizados para busca
        String alertMessage = "";
        boolean badInput = false;
        
        String searchNome = pesquisaTextField.getText();
        String searchTipo = menuTipoLocal.getText();
        String searchHoraAbertura = horaInicTextField.getText();
        String searchHoraFechamento = horaFinalTextField.getText();
        String definedDistancia = distanciaTextField.getText();
        String searchGenero = generoTextField.getText();
        String definedEntrada = entradaTextField.getText();
        
        //Validação campo Tipo
        if (searchTipo.equals("Qualquer Tipo") || searchTipo.equals("Tipo do Local")){
            searchTipo = "";
        }
        
        //Definição do método de validação para os subsequentes campos
        Pattern regexPattern = Pattern.compile("[a-zA-Z]+");
        
        //Validação campo Abertura
        Matcher regexMatcher = regexPattern.matcher(searchHoraAbertura);
        if (searchHoraAbertura.isEmpty()){
            searchHoraAbertura = "00:01";
        } else if(regexMatcher.find()){
            badInput = true;
            alertMessage += "Hora de abertura inválida ";
        }
        
        //Validação campo Fechamento
        regexMatcher = regexPattern.matcher(searchHoraFechamento);
        if(regexMatcher.find()){
            badInput = true;
            alertMessage += "Hora de fechamento inválida ";
        }else if (searchHoraFechamento.isEmpty()){
            searchHoraFechamento = "23:59";
        }
        
        //Validação campo Distância
        Double searchDistancia = 99D;
        regexMatcher = regexPattern.matcher(definedDistancia);
        if(regexMatcher.find()){
            badInput = true;
            alertMessage += "Distância inválida ";
        }else if (!definedDistancia.isEmpty()){
            searchDistancia = Double.parseDouble(definedDistancia);
        }
        
        //Validação campo Entrada
        Double searchEntrada = 99D;
        regexMatcher = regexPattern.matcher(definedEntrada);
        if(regexMatcher.find()){
            badInput = true;
            alertMessage += "Entrada inválida ";
        }else if (!definedEntrada.isEmpty()){
            searchDistancia = Double.parseDouble(definedEntrada);
        }
        
        //Mostra um alerta caso alguma validação falhe
        if(badInput){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Autenticação");
            alert.setHeaderText(null);
            alert.setContentText(alertMessage);
            
            alert.showAndWait(); 
        }else{ //caso as validações passem, realiza a pesquisa pelo Banco de Dados
        estabelecimentos.clear();
        estabelecimentos.addAll(new EstabelecimentoDAO().pesquisa(
                                    searchNome, searchTipo, searchHoraAbertura, searchHoraFechamento, //
                                    searchDistancia, searchGenero, searchEntrada));
        pesquisaTableView.refresh();
        }
    }
    
    @FXML
    private void debug(){
        System.out.println("nome: " + pesquisaTextField.getText());
        System.out.println("tipo: " + menuTipoLocal.getText());
        System.out.println("abertura: " + horaInicTextField.getText());
        System.out.println("fechamento: " + horaFinalTextField.getText());
        System.out.println("distancia: " + distanciaTextField.getText());
        System.out.println("genero: " + generoTextField.getText());
        System.out.println("entrada: " + entradaTextField.getText());
    }
    
    @FXML
    private void acessar() throws IOException {
        estabelecimento = pesquisaTableView.getSelectionModel().getSelectedItem();
        App.setRoot("paginaEstabelecimento");
    }
    
    @FXML
    private void setTipoBar(){
        menuTipoLocal.setText("Bar");
    }
    @FXML
    private void setTipoRestaurante(){
        menuTipoLocal.setText("Restaurante");
    }
    @FXML
    private void setTipoQualquer(){
        menuTipoLocal.setText("Qualquer Tipo");
    }
    
        @FXML
    private void sair() throws IOException {
        App.setRoot("login");
    }

}
