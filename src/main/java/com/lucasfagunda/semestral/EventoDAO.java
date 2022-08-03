package com.lucasfagunda.semestral;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class EventoDAO {
    public Evento getById(Integer id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            ResultSet resultadoEvento = connection.createStatement().executeQuery("SELECT * FROM evento WHERE id = " + id + ";");
            
            Evento eventoSelecionado = new Evento();
            
            resultadoEvento.next();
            
            eventoSelecionado.setId(resultadoEvento.getString("id"));
            eventoSelecionado.setNome(resultadoEvento.getString("nome"));
            eventoSelecionado.setHorarioInicio(resultadoEvento.getString("hora_inicio"));
            eventoSelecionado.setHorarioFim(resultadoEvento.getString("hora_fim"));
            eventoSelecionado.setDescricao(resultadoEvento.getString("descricao"));
            eventoSelecionado.setDataInicio(resultadoEvento.getString("data_inicio"));
            eventoSelecionado.setDataFim(resultadoEvento.getString("data_fim"));
            eventoSelecionado.setEventoPrincipal(Boolean.parseBoolean(resultadoEvento.getString("principal")));
            eventoSelecionado.setIdPai(resultadoEvento.getString("idPai"));
            eventoSelecionado.setIdEstab(resultadoEvento.getString("idEstab"));
            
            return eventoSelecionado;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Evento> getAllPai(Estabelecimento estabelecimento) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            ResultSet resultadoEvento = connection.createStatement().executeQuery("SELECT * FROM evento WHERE idPai = 'null' and idEstab = '" + estabelecimento.id + "';");
            
                List<Evento> dadosBanco = new ArrayList<Evento>();

                if(resultadoEvento.next()){
                    resultadoEvento = connection.createStatement().executeQuery("SELECT * FROM evento WHERE idPai = 'null' and idEstab = '" + estabelecimento.id + "';");
                    while(!resultadoEvento.isLast()) {
                        Evento eventoSelecionado = new Evento();
                 
                        resultadoEvento.next();
                        eventoSelecionado.setId(resultadoEvento.getString("id"));
                        eventoSelecionado.setNome(resultadoEvento.getString("nome"));
                        eventoSelecionado.setHorarioInicio(resultadoEvento.getString("hora_inicio"));
                        eventoSelecionado.setHorarioFim(resultadoEvento.getString("hora_fim"));
                        eventoSelecionado.setDescricao(resultadoEvento.getString("descricao"));
                        eventoSelecionado.setGenero(resultadoEvento.getString("genero_musical"));
                        eventoSelecionado.setDataInicio(resultadoEvento.getString("data_inicio"));
                        eventoSelecionado.setDataFim(resultadoEvento.getString("data_fim"));
                        eventoSelecionado.setEventoPrincipal(Boolean.parseBoolean(resultadoEvento.getString("principal")));
                        eventoSelecionado.setIdPai(resultadoEvento.getString("idPai"));
                        eventoSelecionado.setIdEstab(resultadoEvento.getString("idEstab"));

                        dadosBanco.add(eventoSelecionado);
                        

                    }
                    connection.close();
                    return (dadosBanco);
                }
                
                connection.close();
                return (dadosBanco);
                
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void update(Evento evento) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            connection.createStatement().executeUpdate("update evento "
                    + "set nome = '" + evento.nome + "', "
                    + "genero_musical = '" + evento.genero + "', "
                    + "descricao = '" + evento.descricao + "', "
                    + "data_inicio = '" + evento.dataInicio + "', "
                    + "data_fim = '" + evento.dataFim + "', "
                    + "hora_inicio = '" + evento.horarioInicio + "', "
                    + "hora_fim = '" + evento.horarioFim + "' "
                    + "where id = '" + evento.id + "';");
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean mainEventExists() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            ResultSet resultadoBusca = connection.createStatement().executeQuery("select * from evento " //
                    + "where principal = 'true' " //
                    + "and idEstab = '" + PesquisaController.estabelecimento.id + "';");
            
            return resultadoBusca.next();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public Evento getMainEvent() {
        try {
            Connection connection = new ConnectionFactory().getConnection();
            ResultSet resultadoBusca = connection.createStatement().executeQuery("select * from evento " //
                    + "where principal = 'true' " //
                    + "and idEstab = '" + PesquisaController.estabelecimento.id + "';");
            
            Evento eventoBD = new Evento();
            
            if(resultadoBusca.next()) {
                resultadoBusca = connection.createStatement().executeQuery("select * from evento " //
                    + "where principal = 'true' " //
                    + "and idEstab = '" + PesquisaController.estabelecimento.id + "';");
            
                resultadoBusca.next();
                eventoBD.setId(resultadoBusca.getString("id"));
                eventoBD.setNome(resultadoBusca.getString("nome"));
                eventoBD.setHorarioInicio(resultadoBusca.getString("hora_inicio"));
                eventoBD.setHorarioFim(resultadoBusca.getString("hora_fim"));
                eventoBD.setDescricao(resultadoBusca.getString("descricao"));
                eventoBD.setGenero(resultadoBusca.getString("genero_musical"));
                eventoBD.setDataInicio(resultadoBusca.getString("data_inicio"));
                eventoBD.setDataFim(resultadoBusca.getString("data_fim"));
                eventoBD.setEventoPrincipal(Boolean.parseBoolean(resultadoBusca.getString("principal")));
                eventoBD.setIdPai(resultadoBusca.getString("idPai"));
                eventoBD.setIdEstab(resultadoBusca.getString("idEstab"));
            }
            
            return eventoBD;
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean verifyEventByIdEstab(String idEstab){
        try {
            Connection connection = ConnectionFactory.getConnection();
            ResultSet resultadoBusca = connection.createStatement().executeQuery("select * from evento where idEstab = '" + idEstab + "';");
            
            return resultadoBusca.next();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Evento> getAllSubEventByIdPai(Integer idPai, Integer idEstab) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            ResultSet resultadoEvento = connection.createStatement().executeQuery("SELECT * FROM evento WHERE idPai =  '" + idPai + "' and idEstab = '" + idEstab + "';");
            
            List<Evento> dadosBanco = new ArrayList<Evento>();
            
            
            if(resultadoEvento.next()) {
                resultadoEvento = connection.createStatement().executeQuery("SELECT * FROM evento WHERE idPai =  '" + idPai + "';");
                while(!resultadoEvento.isLast()) {
                    Evento eventoSelecionado = new Evento();

                    resultadoEvento.next();

                    eventoSelecionado.setId(resultadoEvento.getString("id"));
                    eventoSelecionado.setNome(resultadoEvento.getString("nome"));
                    eventoSelecionado.setHorarioInicio(resultadoEvento.getString("hora_inicio"));
                    eventoSelecionado.setHorarioFim(resultadoEvento.getString("hora_fim"));
                    eventoSelecionado.setDescricao(resultadoEvento.getString("descricao"));
                    eventoSelecionado.setGenero(resultadoEvento.getString("genero_musical"));
                    eventoSelecionado.setDataInicio(resultadoEvento.getString("data_inicio"));
                    eventoSelecionado.setDataFim(resultadoEvento.getString("data_fim"));
                    eventoSelecionado.setEventoPrincipal(Boolean.parseBoolean(resultadoEvento.getString("principal")));
                    eventoSelecionado.setIdPai(resultadoEvento.getString("idPai"));
                    eventoSelecionado.setIdEstab(resultadoEvento.getString("idEstab"));

                    dadosBanco.add(eventoSelecionado);


                }
                return (dadosBanco);
            } else {
                return dadosBanco;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean verifyExsistsById(String id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            ResultSet resultadoBusca = connection.createStatement().executeQuery("SELECT * FROM evento WHERE id = '" + id + "';");
            
            if(resultadoBusca.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void save(Evento evento) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            connection.createStatement().executeUpdate("INSERT INTO evento values(" //
                    + "'" + evento.id + "'," //
                    + "'" + evento.nome+ "'," //
                    + "'" + evento.horarioInicio+ "'," //
                    + "'" + evento.horarioFim+ "'," //
                    + "'" + evento.genero + "'," //
                    + "'" + evento.descricao+ "'," //
                    + "'" + evento.dataInicio+ "'," //
                    + "'" + evento.dataFim + "'," //
                    + "'" + evento.eventoPrincipal + "'," //
                    + "'" + evento.idPai + "',"//
                    + "'" + evento.idEstab
                    + "');");
            connection.close();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void setMainEvent(String id, String idEstab) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            connection.createStatement().executeUpdate("update evento set principal = 'false' where idEstab = '" + idEstab + "';");
            connection.createStatement().executeUpdate("update evento set principal = 'true' where idEstab = '" + idEstab + "' and id = '" + id + "';");
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void delete (Integer id) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            
            connection.createStatement().executeUpdate("DELETE FROM evento WHERE id = " + id);
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}

