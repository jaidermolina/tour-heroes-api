package co.udea.heroes.api.controller;

import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.service.HeroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    private HeroService heroService;

    public HeroController(HeroService heroService){
        this.heroService = heroService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Hero> getHero(@PathVariable Integer id) {
        log.info("REST request buscar heroe");
        return ResponseEntity.ok(heroService.getHero(id));
    }

    @GetMapping()
    @ApiOperation(value = "Buscar todos los heroes", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los heroes fueron buscados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> getHeroes() {
        log.info("REST request buscar todos los heroes");
        return ResponseEntity.ok(heroService.getHeroes());
    }

    @PostMapping()
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero) {
        return ResponseEntity.ok(heroService.addHero(hero));
    }

    @GetMapping("name/{name}")
    public ResponseEntity<Hero> getHeroByName(@PathVariable("name") String name) {
        log.info("REST request buscar heroe por nomnbre");
        return ResponseEntity.ok(heroService.getHeroByName(name));
    }
}
