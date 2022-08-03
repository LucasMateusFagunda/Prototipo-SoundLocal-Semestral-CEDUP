package com.lucasfagunda.semestral;

public class Evento {
    
    String id;
    String nome;
    String genero;
    String descricao;
    String dataInicio;
    String dataFim;
    String horarioInicio;
    String horarioFim;
    Boolean eventoPrincipal;
    String idPai;
    String idEstab;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
    
    public String getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(String horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public String getHorarioFim() {
        return horarioFim;
    }

    public void setHorarioFim(String horarioFim) {
        this.horarioFim = horarioFim;
    }

    public Boolean getEventoPrincipal() {
        return eventoPrincipal;
    }

    public void setEventoPrincipal(Boolean eventoPrincipal) {
        this.eventoPrincipal = eventoPrincipal;
    }

    public String getIdPai() {
        return idPai;
    }

    public void setIdPai(String idPai) {
        this.idPai = idPai;
    }

    public String getIdEstab() {
        return idEstab;
    }

    public void setIdEstab(String idEstab) {
        this.idEstab = idEstab;
    }
    
}
