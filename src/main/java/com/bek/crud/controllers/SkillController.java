package com.bek.crud.controllers;

import com.bek.crud.model.Skill;
import com.bek.crud.repository.Impl.GsonSkillRepositoryImpl;
import com.bek.crud.repository.SkillRepository;

import java.util.List;

public class SkillController {
    private final SkillRepository repo = new GsonSkillRepositoryImpl();

    public Skill createSkill(String name) {
        Skill skill = new Skill();
        skill.setName(name);
        return repo.save(skill);
    }

    public Skill updateSkill(Integer id, String name) {
        Skill skill = new Skill();
        skill.setId(id);
        skill.setName(name);
        return repo.update(skill);
    }

    public List<Skill> getAll(){
        return repo.getAll();
    }

    public void deleteByIdSkill(Integer id){
        repo.deleteById(id);
    }

    public Skill getById(Integer id){
        return repo.getById(id);
    }
}
