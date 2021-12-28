package com.bek.crud.view;

import com.bek.crud.controllers.SkillController;
import com.bek.crud.model.Skill;

import java.util.List;
import java.util.Scanner;

public class SkillView {
    private final SkillController controller = new SkillController();
    private final Scanner scanner = new Scanner(System.in);

    public void createSkill() {
        System.out.println("Enter name skill");
        String name = scanner.nextLine();
        Skill s = controller.createSkill(name);
        System.out.println("You have entered: " + s);
    }

    public void updateSkill(){
        System.out.println("Enter id skill");
        Integer id = scanner.nextInt();
        System.out.println("Enter name skill");
        String name = scanner.nextLine();
        Skill s = controller.updateSkill(id, name);
        System.out.println("You have entered: " + s);
    }

    public void getAll(){
        List<Skill> s = controller.getAll();
        System.out.println(s.toString());
    }

    public void getById(){
        System.out.println("Enter id skill");
        Integer id = scanner.nextInt();
        Skill s = controller.getById(id);
        System.out.println(s.toString());
    }

    public void deleteByIdSkill(){
        System.out.println("Enter id skill");
        Integer id = scanner.nextInt();
        System.out.println("Deleted user with id: "
                + controller.getById(id).getId()
                + " and name: " + controller.getById(id).getName());
    }
}
