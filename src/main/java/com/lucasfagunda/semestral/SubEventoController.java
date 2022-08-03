package com.lucasfagunda.semestral;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;




public class SubEventoController implements Initializable {
    
    @FXML
    private TableColumn<Evento, String> dataColumn;

    @FXML
    private TextField dataEventoFimTextField;

    @FXML
    private TextField dataEventoInicioTextField;

    @FXML
    private TableColumn<Evento, String> descricaoColumn;

    @FXML
    private TextArea descricaoEventoTextArea;

    @FXML
    private TextField generoEventoTextField;

    @FXML
    private TextField horaEventoFimTextField;

    @FXML
    private TextField horaEventoInicioTextField;

    @FXML
    private TableColumn<Evento, String> nomeColumn;

    @FXML
    private TextField nomeEventoTextField;

    @FXML
    private TableView<Evento> subEventoTableView;
    
    static ObservableList<Evento> eventos;
    static Evento subEvento;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        subEvento = new Evento();
        
        this.nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        this.descricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        this.eventos = subEventoTableView.getItems();
        
        setSubEventoText();
        atualizarLista();
        
    }    
    
    

    @FXML
    void adicionar(ActionEvent event) throws IOException {
        App.setRoot("novoSubEvento");
    }

    @FXML
    void editar(ActionEvent event) {

    }
    
    @FXML
    void salvar() throws IOException {
        ListaEventosController.evento.nome = nomeEventoTextField.getText();
        ListaEventosController.evento.genero = generoEventoTextField.getText();
        ListaEventosController.evento.descricao = descricaoEventoTextArea.getText();
        ListaEventosController.evento.dataInicio = dataEventoInicioTextField.getText();
        ListaEventosController.evento.dataFim = dataEventoFimTextField.getText();
        ListaEventosController.evento.horarioInicio = horaEventoInicioTextField.getText();
        ListaEventosController.evento.horarioFim = horaEventoFimTextField.getText();
        
        new EventoDAO().update(ListaEventosController.evento);
        App.setRoot("listaEventos");
    }
    
    @FXML
    void excluir(ActionEvent event) {
        subEvento = subEventoTableView.getSelectionModel().getSelectedItem();
        new EventoDAO().delete(Integer.parseInt(subEvento.id));
        atualizarLista();
    }

    @FXML
    void voltar(ActionEvent event) throws IOException {
        App.setRoot("listaEventos");
    }
    
    void atualizarLista() {
        eventos.clear();
        eventos.addAll(new EventoDAO().getAllSubEventByIdPai(Integer.parseInt(ListaEventosController.evento.id), Integer.parseInt(ListaEventosController.evento.idEstab)));
        subEventoTableView.refresh();
        
    }

    
    void setSubEventoText() {
        nomeEventoTextField.setText(ListaEventosController.getEvento().nome);
        horaEventoInicioTextField.setText(ListaEventosController.getEvento().horarioInicio);
        horaEventoFimTextField.setText(ListaEventosController.getEvento().horarioFim);
        generoEventoTextField.setText(ListaEventosController.getEvento().genero);
        descricaoEventoTextArea.setText(ListaEventosController.getEvento().descricao);
        dataEventoInicioTextField.setText(ListaEventosController.getEvento().dataInicio);
        dataEventoFimTextField.setText(ListaEventosController.getEvento().dataFim);
    }
    
    
    
    public static ObservableList<Evento> getEventos() {
        return eventos;
    }

    public static void setEventos(ObservableList<Evento> eventos) {
        SubEventoController.eventos = eventos;
    }

    public static Evento getSubEvento() {
        return subEvento;
    }

    public static void setSubEvento(Evento subEvento) {
        SubEventoController.subEvento = subEvento;
    }

    public TableColumn<Evento, String> getDataColumn() {
        return dataColumn;
    }

    public void setDataColumn(TableColumn<Evento, String> dataColumn) {
        this.dataColumn = dataColumn;
    }

    public TextField getDataEventoFimTextField() {
        return dataEventoFimTextField;
    }

    public void setDataEventoFimTextField(TextField dataEventoFimTextField) {
        this.dataEventoFimTextField = dataEventoFimTextField;
    }

    public TextField getDataEventoInicioTextField() {
        return dataEventoInicioTextField;
    }

    public void setDataEventoInicioTextField(TextField dataEventoInicioTextField) {
        this.dataEventoInicioTextField = dataEventoInicioTextField;
    }

    public TableColumn<Evento, String> getDescricaoColumn() {
        return descricaoColumn;
    }

    public void setDescricaoColumn(TableColumn<Evento, String> descricaoColumn) {
        this.descricaoColumn = descricaoColumn;
    }

    public TextArea getDescricaoEventoTextArea() {
        return descricaoEventoTextArea;
    }

    public void setDescricaoEventoTextArea(TextArea descricaoEventoTextArea) {
        this.descricaoEventoTextArea = descricaoEventoTextArea;
    }

    public TextField getGeneroEventoTextField() {
        return generoEventoTextField;
    }

    public void setGeneroEventoTextField(TextField generoEventoTextField) {
        this.generoEventoTextField = generoEventoTextField;
    }

    public TextField getHoraEventoFimTextField() {
        return horaEventoFimTextField;
    }

    public void setHoraEventoFimTextField(TextField horaEventoFimTextField) {
        this.horaEventoFimTextField = horaEventoFimTextField;
    }

    public TextField getHoraEventoInicioTextField() {
        return horaEventoInicioTextField;
    }

    public void setHoraEventoInicioTextField(TextField horaEventoInicioTextField) {
        this.horaEventoInicioTextField = horaEventoInicioTextField;
    }

    public TableColumn<Evento, String> getNomeColumn() {
        return nomeColumn;
    }

    public void setNomeColumn(TableColumn<Evento, String> nomeColumn) {
        this.nomeColumn = nomeColumn;
    }

    public TextField getNomeEventoTextField() {
        return nomeEventoTextField;
    }

    public void setNomeEventoTextField(TextField nomeEventoTextField) {
        this.nomeEventoTextField = nomeEventoTextField;
    }

    public TableView<Evento> getSubEventoTableView() {
        return subEventoTableView;
    }

    public void setSubEventoTableView(TableView<Evento> subEventoTableView) {
        this.subEventoTableView = subEventoTableView;
    }
    
    
    
}
