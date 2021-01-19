package br.com.gabriels.swdj.planetas.api.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

class Resultados {

    private Integer numeroAparicoesEmFilmes = 0;

    public Integer getNumeroDeAparicoesEmFilmes() {
        return numeroAparicoesEmFilmes;
    }

    @JsonSetter("films")
    private void setNumeroDeAparicoesEmFilmes(List<String> films) {
        this.numeroAparicoesEmFilmes = films.size();
    }
}
