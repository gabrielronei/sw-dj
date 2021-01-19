package br.com.gabriels.swdj.planetas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NovoPlanetaRequestBuilder {

    private String nome;
    private String clima;
    private String terreno;

    public static NovoPlanetaRequestBuilder umPlaneta() {
        return new NovoPlanetaRequestBuilder();
    }

    public NovoPlanetaRequestBuilder chamado(String nome) {
        this.nome = nome;
        return this;
    }

    public NovoPlanetaRequestBuilder comClima(String clima) {
        this.clima = clima;
        return this;
    }

    public NovoPlanetaRequestBuilder comTerreno(String terreno) {
        this.terreno = terreno;
        return this;
    }

    public NovoPlanetaRequest cria() {
        NovoPlanetaRequest novo = new NovoPlanetaRequest();
        novo.setNome(this.nome);
        novo.setClima(this.clima);
        novo.setTerreno(this.terreno);
        return novo;
    }

    public String criaComoJson() {
        try {
            return new ObjectMapper().writeValueAsString(cria());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
