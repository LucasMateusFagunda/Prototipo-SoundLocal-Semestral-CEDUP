package com.lucasfagunda.semestral;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class PaginaEstabelecimentoController implements Initializable {

    @FXML
    private Button acessarSubEventoButton;
    @FXML
    private Button eventosButton;
    @FXML
    private Label nomeEstabLabel;
    @FXML
    private Label foneEstabLabel;
    @FXML
    private Label emailEstabLabel;
    @FXML
    private ImageView imageImageView;
    @FXML
    private Label endereco1Label;
    @FXML
    private Label endereco2Label;
    @FXML
    private Label eventoAtivoLabel;
    @FXML
    private Label horarioEstabLabel;
    @FXML
    private Label genEstabLabel;
    @FXML
    private Label nomeEventoLabel;
    @FXML
    private Label genEventoLabel;
    @FXML
    private Label dataEventoLabel;
    @FXML
    private Label horaEventoLabel;
    @FXML
    private Label descEventoLabel;
    
    @FXML
    private TableView<Evento> listaSubEventosTableView;
    
    @FXML
    private TableColumn<Evento, String> descricaoColumn;

    @FXML
    private TableColumn<Evento, String> nomeColumn;
    
    ObservableList<Evento> listaSubEventos;
    static Evento subEvento = new Evento();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.descricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
        this.listaSubEventos = listaSubEventosTableView.getItems();
        
        setEvento();
        preencher();
        atualizarLista();
    }    
    
    public void preencher() {
        Estabelecimento estab = PesquisaController.estabelecimento;
        Evento evento = ListaEventosController.evento;
        
        nomeEstabLabel.setText(estab.nome);
        foneEstabLabel.setText(estab.fone);
        emailEstabLabel.setText(estab.email);
        endereco1Label.setText(estab.endereco);
        horarioEstabLabel.setText(estab.horaAbertura + "-" + estab.horaFechamento);
        genEstabLabel.setText(estab.genero);
        
        if (new EventoDAO().mainEventExists()) {
            nomeEventoLabel.setText(evento.nome);
            descEventoLabel.setText(evento.descricao);
            genEventoLabel.setText(evento.genero);
            dataEventoLabel.setText(evento.dataInicio + "-" + evento.dataFim);
            horaEventoLabel.setText(evento.horarioInicio + "-" + evento.horarioFim);
        } else {
            nomeEventoLabel.setText(null);
            descEventoLabel.setText(null);
            genEventoLabel.setText(null);
            dataEventoLabel.setText(null);
            horaEventoLabel.setText(null);
        }
        
        if(LoginController.estabelecimentoAtivo != null) {
            if(LoginController.estabelecimentoAtivo.idAtivo.equals(PesquisaController.estabelecimento.id)) {
                eventosButton.setVisible(true);
            } else {
                eventosButton.setVisible(false);
            }
        }
    }
    
    public void setEvento() {
        if(new EventoDAO().verifyEventByIdEstab(PesquisaController.estabelecimento.id)) {
            if(new EventoDAO().mainEventExists()) {
                ListaEventosController.evento = new EventoDAO().getMainEvent();
            } else {
                ListaEventosController.evento = new Evento();
            }
            
        } 
    }
    
    public void atualizarLista() {
        listaSubEventos.clear();
        if(new EventoDAO().mainEventExists()) {
            listaSubEventosTableView.setVisible(true);
            acessarSubEventoButton.setVisible(true);
            eventoAtivoLabel.setVisible(false);
            listaSubEventos.addAll(new EventoDAO().getAllSubEventByIdPai(//
                    Integer.parseInt(ListaEventosController.evento.id),//
                    Integer.parseInt(ListaEventosController.evento.idEstab)));
        } else {
            listaSubEventosTableView.setVisible(false);
            acessarSubEventoButton.setVisible(false);
            eventoAtivoLabel.setVisible(true);

            ListaEventosController.evento = new Evento();
            listaSubEventos.clear();
        }
    }
    
    
    @FXML
    private void eventos() throws IOException {
        App.setRoot("listaEventos");
    }
    
    @FXML
    private void acessar() throws IOException {
        subEvento = listaSubEventosTableView.getSelectionModel().getSelectedItem();
        App.setRoot("visualizarSubEvento");
    }
    
    @FXML
    private void voltar() throws IOException {
        App.setRoot("pesquisa");
    }
    
}
