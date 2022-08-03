package com.lucasfagunda.semestral;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListaEventosController implements Initializable {
    
    @FXML
    private TableView<Evento> eventosTableView;
    @FXML
    private TableColumn<Evento, Boolean> principalColumn;
    @FXML
    private TableColumn<Evento, String> nomeColumn;
    @FXML
    private TableColumn<Evento, String> dataColumn;
    @FXML
    private TableColumn<Evento, String> descricaoColumn;
    
    static ObservableList<Evento> eventos;
    static Evento evento;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        evento = new Evento();
        this.principalColumn.setCellValueFactory(new PropertyValueFactory<>("eventoPrincipal"));
        this.nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        this.descricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        
        this.eventos = eventosTableView.getItems();
        
        atualizarLista();
    }    
    
    @FXML
    private void adicionar() throws IOException {        
        App.setRoot("novoEvento");
    }

    @FXML
    private void editar() throws IOException {
        evento = this.eventosTableView.getSelectionModel().getSelectedItem();
        
        App.setRoot("subEvento");
    }

    @FXML
    private void excluir() {
        Evento eventoSelecionado = this.eventosTableView.getSelectionModel().getSelectedItem();
        
        new EventoDAO().delete(Integer.parseInt(eventoSelecionado.getId()));
        atualizarLista();
    }

    @FXML
    private void defPrincipal() {
        evento = this.eventosTableView.getSelectionModel().getSelectedItem();
        evento.setEventoPrincipal(true);
        new EventoDAO().setMainEvent(evento.id, evento.idEstab);
        atualizarLista();
    }

    @FXML
    private void voltar() throws IOException {
        App.setRoot("paginaEstabelecimento");
    }
    
    void atualizarLista() {
        eventos.clear();
        eventos.addAll(new EventoDAO().getAllPai(PesquisaController.estabelecimento));
        eventosTableView.refresh();
        
    }

    public static Evento getEvento() {
        return evento;
    }

    public static void setEvento(Evento evento) {
        ListaEventosController.evento = evento;
    }
    
}
