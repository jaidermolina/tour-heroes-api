package co.udea.heroes.api.service;

import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.repository.HeroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    private HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public Hero getHero(Integer id){
        Optional<Hero> optionalHero = heroRepository.findById(id);
        return optionalHero.get();
    }

    public List<Hero> getHeroes(){
        return heroRepository.findAll();
    }

    public Hero addHero(Hero hero){
        return heroRepository.save(hero);
    }
}
