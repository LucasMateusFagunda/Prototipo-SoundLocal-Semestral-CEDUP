package com.lucasfagunda.semestral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class EstabelecimentoDAO {
    
    public String getByLogin(Estabelecimento estabelecimentoLogin) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            ResultSet resultadoBusca = connection.createStatement().executeQuery("SELECT * FROM estabelecimento WHERE email = '" 
                            + estabelecimentoLogin.email + "' AND senha = '" 
                            + estabelecimentoLogin.senha + "'");
            resultadoBusca.next();
            return(resultadoBusca.getString("id"));
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean login(Estabelecimento estabelecimentoLogin) throws SQLException{
        try{
            Connection connection = ConnectionFactory.getConnection();
            ResultSet resultadoBusca = connection. //
                    createStatement(). //
                    executeQuery("SELECT count(*) FROM estabelecimento WHERE email = '" 
                            + estabelecimentoLogin.email + "' AND senha = '" 
                            + estabelecimentoLogin.senha + "'");

            resultadoBusca.next();
            int quantidadeDeUsuario = resultadoBusca.getInt(1);
            
            return (quantidadeDeUsuario > 0);
        
        }catch(Exception e){
        throw new RuntimeException(e);
        }
    }
    
    public void save(Estabelecimento estabelecimento) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            connection.createStatement().executeUpdate("INSERT INTO estabelecimento values(" //
                    + "'" + estabelecimento.id + "', " //
                    + "'" + estabelecimento.nome + "', " //
                    + "'" + estabelecimento.tipo + "', " //
                    + "'" + estabelecimento.email + "', " //
                    + "'" + estabelecimento.senha + "', " //
                    + "'" + estabelecimento.endereco + "', " //
                    + "'" + estabelecimento.horaAbertura + "', " //
                    + "'" + estabelecimento.horaFechamento + "', " //
                    + "'" + estabelecimento.distancia + "', " //
                    + "'" + estabelecimento.genero + "', " //
                    + "'" + estabelecimento.cnpj + "', " //
                    + "'" + estabelecimento.fone + "', " //
                    + "'" + estabelecimento.entrada + "'" //
                    + ");");
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void delete(Estabelecimento estabelecimento) {
        try{
            Connection connection = ConnectionFactory.getConnection();
            connection.createStatement().executeUpdate("Delete FROM estabelecimento WHERE id = '" + estabelecimento.id + "';");
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
    public List<Estabelecimento> getAll(){
        try {
            Connection connection = ConnectionFactory.getConnection();
            ResultSet resultadoBusca = connection.createStatement().executeQuery("SELECT * FROM estabelecimento");
            List<Estabelecimento> listaEstabelcimento = new ArrayList<Estabelecimento>();
            if(resultadoBusca.next()) {
                resultadoBusca = connection.createStatement().executeQuery("SELECT * FROM estabelecimento");
                while(!resultadoBusca.isLast()) {
                    Estabelecimento estabelecimentoBD = new Estabelecimento();
                    resultadoBusca.next();
                    estabelecimentoBD.setId(resultadoBusca.getString("id"));
                    estabelecimentoBD.setNome(resultadoBusca.getString("nome"));
                    estabelecimentoBD.setTipo(resultadoBusca.getString("tipo"));
                    estabelecimentoBD.setEmail(resultadoBusca.getString("email"));
                    estabelecimentoBD.setSenha(resultadoBusca.getString("senha"));
                    estabelecimentoBD.setEndereco(resultadoBusca.getString("endereco"));
                    estabelecimentoBD.setHoraAbertura(resultadoBusca.getString("hora_abertura"));
                    estabelecimentoBD.setHoraFechamento(resultadoBusca.getString("hora_fechamento"));
                    estabelecimentoBD.setDistancia(resultadoBusca.getDouble("distancia"));
                    estabelecimentoBD.setGenero(resultadoBusca.getString("genero_musical"));
                    estabelecimentoBD.setCnpj(resultadoBusca.getString("cnpj"));
                    estabelecimentoBD.setFone(resultadoBusca.getString("telefone"));
                    estabelecimentoBD.setEntrada(Double.parseDouble(resultadoBusca.getString("entrada")));
                    
                    listaEstabelcimento.add(estabelecimentoBD);
                }
            }
            return (listaEstabelcimento);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Estabelecimento> pesquisa(String searchNome, String searchTipo, // 
                                            String searchHoraAbertura, String searchHoraFechamento, //
                                            Double searchDistancia, String searchGenero, //
                                            Double searchEntrada){
        try {
            Connection connection = ConnectionFactory.getConnection();
            
            PreparedStatement prepareStatement = connection.prepareStatement("select * from estabelecimento where " +
                    "nome like(?) and " +
                    "tipo like(?) and " +
                    "hora_abertura >= ? and " +
                    "hora_fechamento <= ? and " +
                    "distancia <= ? and " + 
                    "genero_musical like(?) and " +
                    "entrada <= ?;");
            
            prepareStatement.setString(1, "%" + searchNome + "%");
            prepareStatement.setString(2, "%" + searchTipo + "%");
            prepareStatement.setString(3, searchHoraAbertura);
            prepareStatement.setString(4, searchHoraFechamento);
            prepareStatement.setDouble(5, searchDistancia);
            prepareStatement.setString(6, "%" + searchGenero + "%");
            prepareStatement.setDouble(7, searchEntrada);
            
            ResultSet resultadoEstab = prepareStatement.executeQuery();
            
            
            List<Estabelecimento> dadosBanco = new ArrayList<Estabelecimento>();
            while(resultadoEstab.next()) {

                Estabelecimento estabSelecionado = new Estabelecimento();

                estabSelecionado.setId(resultadoEstab.getString("id"));
                estabSelecionado.setNome(resultadoEstab.getString("nome"));
                estabSelecionado.setTipo(resultadoEstab.getString("tipo"));
                estabSelecionado.setHoraAbertura(resultadoEstab.getString("hora_abertura"));
                estabSelecionado.setHoraFechamento(resultadoEstab.getString("hora_fechamento"));
                estabSelecionado.setDistancia(resultadoEstab.getDouble("distancia"));
                estabSelecionado.setGenero(resultadoEstab.getString("genero_musical"));
                estabSelecionado.setEntrada(resultadoEstab.getDouble("entrada"));

                dadosBanco.add(estabSelecionado);
            }
            return (dadosBanco); 
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
