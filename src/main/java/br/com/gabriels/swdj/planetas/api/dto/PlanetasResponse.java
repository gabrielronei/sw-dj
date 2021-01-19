package br.com.gabriels.swdj.planetas.api.dto;

import java.util.List;

public class PlanetasResponse {
    private List<Resultados> results;

    public void setResults(List<Resultados> results) {
        this.results = results;
    }

    public Integer getNumeroDeAparicoesEmFilmes() {
        if (this.results.size() <= 0) return 0;
        return this.results.get(0).getNumeroDeAparicoesEmFilmes();
    }
}