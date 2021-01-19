package br.com.gabriels.swdj.planetas;

import javax.persistence.*;
import javax.validation.constraints.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Planeta {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String nome;

    @NotBlank
    private String clima;

    @NotBlank
    private String terreno;

    @PositiveOrZero
    private Integer numeroDeAparicoesEmFilme = 0;

    @Deprecated
    public Planeta() {
    }

    public Planeta(@NotBlank String nome, @NotBlank String clima, @NotBlank String terreno, Integer numeroDeAparicoesEmFilme) {
        this.nome = nome;
        this.clima = clima;
        this.terreno = terreno;
        this.numeroDeAparicoesEmFilme = numeroDeAparicoesEmFilme;
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public String getClima() {
        return clima;
    }

    public String getTerreno() {
        return terreno;
    }

    public Integer getNumeroDeAparicoesEmFilme() {
        return numeroDeAparicoesEmFilme;
    }

    public void atualiza(CamposParaAtualizarPlaneta camposParaAtualizarPlaneta) {
        this.nome = camposParaAtualizarPlaneta.getNome();
        this.clima = camposParaAtualizarPlaneta.getClima();
        this.terreno = camposParaAtualizarPlaneta.getTerreno();
        this.numeroDeAparicoesEmFilme = camposParaAtualizarPlaneta.getNumeroDeAparicoesEmFilme();
    }
}
