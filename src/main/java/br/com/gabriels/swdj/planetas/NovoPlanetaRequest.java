package br.com.gabriels.swdj.planetas;

import javax.validation.constraints.NotBlank;
import java.util.function.Function;

public class NovoPlanetaRequest implements CampoUnicoPlaneta {
    @NotBlank
    private String nome;

    @NotBlank
    private String clima;

    @NotBlank
    private String terreno;

    public String getNome() {
        return nome;
    }

    public String getClima() {
        return clima;
    }

    public String getTerreno() {
        return terreno;
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

    public Planeta toModel(Function<String, Integer> numeroDeAparicoes) {
        return new Planeta(this.nome, this.clima, this.terreno, numeroDeAparicoes.apply(this.nome));
    }
}
