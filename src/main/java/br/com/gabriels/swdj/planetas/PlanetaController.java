package br.com.gabriels.swdj.planetas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/planetas")
public class PlanetaController {

    @Autowired
    private BuscaAparicoesDoPlanetaService buscaAparicoesDoPlanetaService;

    @Autowired
    private PlanetaRepository planetaRepository;

    @InitBinder({"novoPlanetaRequest", "atualizaPlanetaRequest"})
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new UnicoPlanetaValidator(planetaRepository));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<PlanetaOutputDTO> cadastrar(@RequestBody @Valid NovoPlanetaRequest novoPlanetaRequest, UriComponentsBuilder uriBuilder) {
        Planeta planeta = novoPlanetaRequest.toModel(nome -> buscaAparicoesDoPlanetaService.busca(nome));
        planetaRepository.save(planeta);

        URI uri = uriBuilder.path("/planetas/{id}")
                .buildAndExpand(planeta.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new PlanetaOutputDTO(planeta));
    }

    @GetMapping
    public ResponseEntity<List<PlanetaOutputDTO>> listar() {
        return ResponseEntity.ok(planetaRepository.findAll().stream().map(PlanetaOutputDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/{nome}")
    public ResponseEntity<PlanetaOutputDTO> buscarPorNome(@PathVariable("nome") String nome) {
        Planeta planeta = planetaRepository.findByNome(nome).orElseThrow(NaoEncontradoException::new);
        return ResponseEntity.ok(new PlanetaOutputDTO(planeta));
    }

    @GetMapping("/{id:[0-9]*}")
    public ResponseEntity<PlanetaOutputDTO> buscarPorId(@PathVariable("id") Long id) {
        Planeta planeta = planetaRepository.findById(id).orElseThrow(NaoEncontradoException::new);
        return ResponseEntity.ok(new PlanetaOutputDTO(planeta));
    }

    @Transactional
    @PutMapping("/{id:[0-9]*}")
    public ResponseEntity<PlanetaOutputDTO> atualizar(@PathVariable("id") Long id, @RequestBody @Valid AtualizaPlanetaRequest atualizaPlanetaRequest) {
        Planeta planeta = planetaRepository.findById(id).orElseThrow(NaoEncontradoException::new);
        atualizaPlanetaRequest.atualiza(planeta, nome -> buscaAparicoesDoPlanetaService.busca(nome));

        planetaRepository.save(planeta);
        return ResponseEntity.ok(new PlanetaOutputDTO(planeta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable("id") Long id) {
        planetaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
