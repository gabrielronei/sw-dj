package br.com.gabriels.swdj.planetas;

public class PlanetaOutputDTO {

    private String nome;
    private String terreno;
    private String clima;
    private Integer numeroDeAparicoesEmFilmes;

    public PlanetaOutputDTO(Planeta planeta) {
        this.nome = planeta.getNome();
        this.clima = planeta.getClima();
        this.terreno = planeta.getTerreno();
        this.numeroDeAparicoesEmFilmes = planeta.getNumeroDeAparicoesEmFilme();
    }

    public String getNome() {
        return nome;
    }

    public String getTerreno() {
        return terreno;
    }

    public String getClima() {
        return clima;
    }

    public Integer getNumeroDeAparicoesEmFilmes() {
        return numeroDeAparicoesEmFilmes;
    }
}
