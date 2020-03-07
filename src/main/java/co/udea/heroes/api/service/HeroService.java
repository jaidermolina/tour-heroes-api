package co.udea.heroes.api.service;

import co.udea.heroes.api.exception.BusinessException;
import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.repository.HeroRepository;
import co.udea.heroes.api.util.Messages;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {

    private Messages messages;
    private HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository, Messages messages) {
        this.heroRepository = heroRepository;
        this.messages = messages;
    }

    public Hero getHero(Integer id){
        Optional<Hero> optionalHero = heroRepository.findById(id);
        return optionalHero.get();
    }

    public List<Hero> getHeroes(){
        return heroRepository.findAll();
    }

    public Hero addHero(Hero hero){
        Optional<Hero> optionalHero = heroRepository.findByName(hero.getName());
        if(optionalHero.isPresent()){
            throw new BusinessException(messages.get("exception.data_duplicate_name.hero"));
        }
        return heroRepository.save(hero);
    }

    public Hero getHeroByName(String name){
        return heroRepository.findByName(name).get();
    }
}
