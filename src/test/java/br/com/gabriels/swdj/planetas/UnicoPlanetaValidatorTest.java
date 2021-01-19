package br.com.gabriels.swdj.planetas;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

@SpringBootTest
class UnicoPlanetaValidatorTest {

    @Autowired
    private PlanetaRepository planetaRepository;

    @Mock
    private Errors errors;
    private UnicoPlanetaValidator unicoPlanetaValidator;

    @BeforeEach
    void setUp() {
        this.unicoPlanetaValidator = new UnicoPlanetaValidator(planetaRepository);
    }

    @Test
    public void validate__deveria_registrar_erro_se_nome_ja_existente() {
        Planeta planeta = new Planeta("Naboo", "Temperado", "Gelado", 4);
        planetaRepository.save(planeta);

        NovoPlanetaRequest novoPlanetaRequest = NovoPlanetaRequestBuilder.umPlaneta()
                .chamado("Naboo")
                .comClima("Temperado")
                .comTerreno("colinas, pântanos, florestas, montanhas")
                .cria();

        unicoPlanetaValidator.validate(novoPlanetaRequest, errors);
        verify(errors, atLeastOnce()).rejectValue(anyString(), anyString());
    }

    @Test
    public void validate__nao_deveria_registrar_erro() {
        NovoPlanetaRequest novoPlanetaRequest = NovoPlanetaRequestBuilder.umPlaneta()
                .chamado("Naboo")
                .comClima("Temperado")
                .comTerreno("colinas, pântanos, florestas, montanhas")
                .cria();

        unicoPlanetaValidator.validate(novoPlanetaRequest, errors);
        verify(errors, never()).rejectValue(anyString(), anyString());
    }
}