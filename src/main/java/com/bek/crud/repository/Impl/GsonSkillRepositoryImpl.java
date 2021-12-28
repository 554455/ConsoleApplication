package com.bek.crud.repository.Impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.bek.crud.model.Skill;
import com.bek.crud.repository.SkillRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.*;

public class GsonSkillRepositoryImpl implements SkillRepository {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final String FILE_PATH = "src\\main\\resources\\skills.json";
    private List<Skill> skillList = new ArrayList<>();

    @Override
    public Skill getById(Integer id) {
        return getAllSkillsInternal().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private List<Skill> getAllSkillsInternal() {
        try (FileReader fileReader = new FileReader(FILE_PATH)) {
            Type targetClassType = new TypeToken<ArrayList<Skill>>() {}.getType();
            return gson.fromJson(fileReader, targetClassType);
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }

    private Integer generateNewId(List<Skill> skills) {
        Skill skillWithMaxId = skills.stream().max(Comparator.comparing(Skill::getId)).orElse(null);
        Objects.nonNull(skillWithMaxId);
        return Objects.nonNull(skillWithMaxId) ? skillWithMaxId.getId() + 1 : 1;
    }

    @Override
    public List<Skill> getAll() {
        return getAllSkillsInternal();
    }

    private void writeSkillsToFile(List<Skill> skills) {
        try(Writer writer = new FileWriter(FILE_PATH)) {
            writer.write(gson.toJson(skillList));
        } catch (IOException e) {
            System.out.println("Couldn't record !!!" + e);
        }
    }


    @Override
    public  Skill save(Skill skill) {
        List<Skill> currentSkills = getAllSkillsInternal();
        Integer newMaxId = generateNewId(currentSkills);
        skill.setId(newMaxId);
        currentSkills.add(skill);
        writeSkillsToFile(currentSkills);
        return skill;
    }

    @Override
    public Skill update(Skill skill) {
        List<Skill> currentSkills = getAllSkillsInternal();
        currentSkills.forEach(s -> {
            if(s.getId().equals(skill.getId())) {
                s.setName(skill.getName());
            }
        });
        writeSkillsToFile(currentSkills);
        return skill;
    }

    @Override
    public void deleteById(Integer id) {
        List<Skill> currentSkills = getAllSkillsInternal();
        currentSkills.removeIf(s -> s.getId().equals(id));
        writeSkillsToFile(currentSkills);
    }
}
