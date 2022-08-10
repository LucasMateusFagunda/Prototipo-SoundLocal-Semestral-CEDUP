# Prototipo-SoundLocal-Semestral-CEDUP

A pandemia do COVID-19 afetou de maneira inegável o funcionamento de inúmeros estabelecimentos por todo o país. Sofreram em especial os que focam na música ao vivo. O pior passou, restrições foram gradualmente flexibilizadas, porém as dificuldades não cessaram.

Objetivo de elaboração de um protótipo de produto de software que sirva como auxílio não só para a retomada dos estabelecimentos afetados, mas também facilitar a pessoas encontrar novos locais para frequentar.

## Objetivos do Trabalho
### Objetivo Geral

Projetar um protótipo de produto de software que permita a interatividade entre pessoas, locais e gostos musicais

### Objetivo específico

* Possibilitar a busca de locais baseando-se em filtros.

* Apresentar ao usuário informações relevantes sobre o local selecionado.

## Requisitos
### Requisitos Funcionais
* Manter Estabelecimentos
* Manter Eventos
* Gerenciar Busca de locais baseando-se em filtros
* Efetuar Login com níveis de acesso (utilizando criptografia)

### Requisitos Não-Funcionais
* Possuir Interface de fácil uso
* Possuir Seção de imagens do estabelecimento
* Ser programado em java
* Possibilitar visualização de página de Estabelecimento

## Imagens das Telas
### Tela de Login
<img src="https://github.com/LucasMateusFagunda/Prototipo-SoundLocal-Semestral-CEDUP/blob/main/Imagens/login.png">

### Tela de Cadastro
<img src="https://github.com/LucasMateusFagunda/Prototipo-SoundLocal-Semestral-CEDUP/blob/main/Imagens/cadastro.png">

### Pagina do Estabelecimento
<img src="https://github.com/LucasMateusFagunda/Prototipo-SoundLocal-Semestral-CEDUP/blob/main/Imagens/paginaEstabelecimento.png">

### Lista de Eventos
<img src="https://github.com/LucasMateusFagunda/Prototipo-SoundLocal-Semestral-CEDUP/blob/main/Imagens/ListaEvento.png">

### Pagina Sub Evento
<img src="https://github.com/LucasMateusFagunda/Prototipo-SoundLocal-Semestral-CEDUP/blob/main/Imagens/subevento.png">

## Como Executar
### Requisitos
* Java 18
* Banco de dados MYSQL local
* Wamp Server ou outro de sua preferência
* JavaFX

### Banco de dados
```
CREATE DATABASE soudlocal;

CREATE TABLE estabelecimento (
  id varchar(255) NOT NULL,
  nome varchar(255) NOT NULL,
  tipo varchar(255) DEFAULT NULL,
  email varchar(255) NOT NULL,
  senha varchar(255) NOT NULL,
  endereco varchar(255) DEFAULT NULL,
  hora_abertura time NOT NULL,
  hora_fechamento time NOT NULL,
  distancia double DEFAULT NULL,
  genero_musical varchar(255) DEFAULT NULL,
  cnpj varchar(255) DEFAULT NULL,
  telefone varchar(255) DEFAULT NULL,
  entrada double DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE evento (
  id varchar(255) NOT NULL,
  nome varchar(255) NOT NULL,
  hora_inicio varchar(255) NOT NULL,
  hora_fim varchar(255) NOT NULL,
  genero_musical varchar(255) DEFAULT NULL,
  descricao varchar(255) DEFAULT NULL,
  data_inicio varchar(255) NOT NULL,
  data_fim varchar(255) DEFAULT NULL,
  principal varchar(255) DEFAULT NULL,
  idPai varchar(255) DEFAULT NULL,
  idEstab varchar(255) DEFAULT NULL,
  PRIMARY KEY ('id'),
  KEY idPai ('idPai'),
  KEY idEstab ('idEstab')
);
```
