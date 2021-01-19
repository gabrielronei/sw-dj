package br.com.gabriels.swdj.planetas;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc
public class PlanetaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deve_criar_um_novo_planeta() throws Exception {
        String novoPlanetaRequestJson = NovoPlanetaRequestBuilder.umPlaneta()
                .chamado("Naboo")
                .comClima("Temperado")
                .comTerreno("colinas, pântanos, florestas, montanhas")
                .criaComoJson();

        mockMvc.perform(MockMvcRequestBuilders.post("/planetas")
                .contentType(APPLICATION_JSON)
                .content(novoPlanetaRequestJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void deve_retornar_erro_com_nome_nulo_ou_vazio(String nome) throws Exception {
        String novoPlanetaRequestJson = NovoPlanetaRequestBuilder.umPlaneta()
                .chamado(nome)
                .comClima("Temperado")
                .comTerreno("colinas, pântanos, florestas, montanhas")
                .criaComoJson();

        mockMvc.perform(MockMvcRequestBuilders.post("/planetas")
                .contentType(APPLICATION_JSON)
                .content(novoPlanetaRequestJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void deve_retornar_erro_com_clima_nulo_ou_vazio(String clima) throws Exception {
        String novoPlanetaRequestJson = NovoPlanetaRequestBuilder.umPlaneta()
                .chamado("Naboo")
                .comClima(clima)
                .comTerreno("colinas, pântanos, florestas, montanhas")
                .criaComoJson();

        mockMvc.perform(MockMvcRequestBuilders.post("/planetas")
                .contentType(APPLICATION_JSON)
                .content(novoPlanetaRequestJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void deve_retornar_erro_com_terreno_nulo_ou_vazio(String terreno) throws Exception {
        String novoPlanetaRequestJson = NovoPlanetaRequestBuilder.umPlaneta()
                .chamado("Naboo")
                .comClima("Temperado")
                .comTerreno(terreno)
                .criaComoJson();

        mockMvc.perform(MockMvcRequestBuilders.post("/planetas")
                .contentType(APPLICATION_JSON)
                .content(novoPlanetaRequestJson))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
