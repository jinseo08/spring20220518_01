package com.its.people.controller;

import com.its.people.dto.PeopleDTO;
import com.its.people.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping("/save-form")
    public String save_form(){
        return "save-form";
    }

    @PostMapping("/save")
    public String save(@RequestParam("name") String name, @RequestParam("age") int age) {
        //System.out.println("PeopleController.save");
        //System.out.println("name = " + name + ", age = " + age);
        peopleService.save(name,age);
        return null;
    }

    @PostMapping("/save1")
    public String save1(@RequestParam("name") String name, @RequestParam("age") int age) {
        PeopleDTO peopleDTO = new PeopleDTO();
        peopleDTO.setName(name);
        peopleDTO.setAge(age);
        peopleService.save1(peopleDTO);
        return null;
    }

    @PostMapping("/save2")
    public String save2(@ModelAttribute PeopleDTO peopleDTO) {
//        PeopleDTO peopleDTO = new PeopleDTO();
//        peopleDTO.setName(name);
//        peopleDTO.setAge(age);
        // @ModelAttribute PeopleDTO peopleDTO 쓰면 위의 과정 3줄이 필요없음
        System.out.println("peopleDTO = " + peopleDTO);
        boolean saveResult = peopleService.save1(peopleDTO);
        if(saveResult){
            System.out.println("저장성공");
            return "index";
        } else  {
            System.out.println("저장실패");
            return "save-form";
        }
    }

    @GetMapping("/findAll")
    public String findAll(Model model){
        List<PeopleDTO> peopleDTOList = peopleService.findAll();
        model.addAttribute("peopleList", peopleDTOList);
        return "list";
    }




}
