package br.com.gabriels.swdj.planetas;

import br.com.gabriels.swdj.planetas.api.dto.PlanetasResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BuscaAparicoesDoPlanetaService {

    @Value("${star.wars.api.planets.search}")
    private String url;

    public Integer busca(String nomePlaneta) {
        String uri = UriComponentsBuilder.fromUriString(this.url.concat(nomePlaneta)).toUriString();

        PlanetasResponse planetasResponse = new RestTemplate()
                .getForObject(uri, PlanetasResponse.class);

        return planetasResponse.getNumeroDeAparicoesEmFilmes();
    }
}
