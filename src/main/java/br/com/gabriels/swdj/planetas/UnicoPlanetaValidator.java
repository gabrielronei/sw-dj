package br.com.gabriels.swdj.planetas;

import org.springframework.validation.*;

public class UnicoPlanetaValidator implements Validator {

    private PlanetaRepository planetaRepository;

    public UnicoPlanetaValidator(PlanetaRepository planetaRepository) {
        this.planetaRepository = planetaRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CampoUnicoPlaneta.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {
        CampoUnicoPlaneta novoPlanetaRequest = (CampoUnicoPlaneta) object;
        if(planetaRepository.existsByNome(novoPlanetaRequest.getNome())) {
            errors.rejectValue("nome", "Já existe um planeta com este nome");
            System.out.println(errors);
        }
    }
}
