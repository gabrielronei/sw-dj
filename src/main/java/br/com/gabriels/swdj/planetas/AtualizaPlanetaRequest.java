package br.com.gabriels.swdj.planetas;

import javax.validation.constraints.*;
import java.util.function.Function;

public class AtualizaPlanetaRequest implements CamposParaAtualizarPlaneta, CampoUnicoPlaneta {
    @NotBlank
    private String nome;

    @NotBlank
    private String clima;

    @NotBlank
    private String terreno;

    @PositiveOrZero
    private Integer numeroDeAparicoesEmFilme = 0;

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getClima() {
        return this.clima;
    }

    @Override
    public String getTerreno() {
        return this.terreno;
    }

    @Override
    public Integer getNumeroDeAparicoesEmFilme() {
        return this.numeroDeAparicoesEmFilme;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public void setTerreno(String terreno) {
        this.terreno = terreno;
    }

    public void setNumeroDeAparicoesEmFilme(Integer numeroDeAparicoesEmFilme) {
        this.numeroDeAparicoesEmFilme = numeroDeAparicoesEmFilme;
    }

    public void atualiza(Planeta planeta, Function<String, Integer> numeroDeAparicoes) {
        this.setNumeroDeAparicoesEmFilme(numeroDeAparicoes.apply(nome));
        planeta.atualiza(this);
    }
}
