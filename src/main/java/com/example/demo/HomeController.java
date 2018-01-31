package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.InvalidNameException;
import javax.naming.Name;
import javax.validation.Valid;
import java.util.Enumeration;

@Controller
public class HomeController {



        @Autowired
        PeopleRepository peopleRepository;

        @RequestMapping("/")
        public String listPeople(Model model) {
            model.addAttribute("people", peopleRepository.findAll());
            return "list";

        }

        @GetMapping("/add")
        public String peopleForm(Model model) {

            model.addAttribute("people", new People());
            return "peopleform";
        }

        @PostMapping("/process")
        public String processForm(@Valid People people, BindingResult result) {
            if (result.hasErrors()) {
                return "peopleform";

            }
            peopleRepository.save(people);
            return "redirect:/";

        }

        @RequestMapping("/detail/{id}")
        public String showPeople(@PathVariable("id") long id, Model model) {
            model.addAttribute("people", peopleRepository.findOne(id));
            return "peopleform";


        }

        @RequestMapping("/update/{id}")
        public String updatepeople(@PathVariable("id") long id, Model model) {
            model.addAttribute("people", peopleRepository.findOne(id));
            return "peopllform";

        }
        @RequestMapping("/delete/{id}")
        public String delpeople ( @PathVariable("id") long id){
           peopleRepository.delete(id);
            return "redirect:/";


        }
    }





