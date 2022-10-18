package com.example.demo.controllers;

import java.text.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Class;
import com.example.demo.models.Member;
import com.example.demo.repositories.ClassRepository;

@RequestMapping("/classes")
@RestController
public class ClassController {
    @Autowired
    public ClassRepository repository;

    @Autowired
    public MemberController memberController;

    @PostMapping
    public ResponseEntity<Class> saveClass(@RequestParam String name, @RequestParam Long capacity,
            @RequestParam String start_date,
            @RequestParam String end_date) throws ParseException {

        String pattern = "dd-MM-yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate new_date = LocalDate.parse(start_date, formatter);
        LocalDate new_end_date = LocalDate.parse(end_date, formatter);
        long days = ChronoUnit.DAYS.between(new_date, new_end_date);
        for (long i = 0; i <= days; i++) {
            Class currentClass = new Class(name, capacity, new_date.toString());
            this.repository.save(currentClass);
            new_date = new_date.plusDays(1);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Object> getClasses() {
        List<Class> classes = this.repository.findAll();
        return new ResponseEntity<>(classes, HttpStatus.ACCEPTED);
    }

    @PostMapping("/bookings")
    public ResponseEntity<Class> saveBooking(@RequestParam String memberName, @RequestParam String date,
            @RequestParam String className) {

        Class currentClass = this.repository.findByNameAndDate(className, date);
        Member currentMember = this.memberController.repository.findByFirstName(memberName);
        Set<Member> memberToAdd = new HashSet<>();
        memberToAdd.add(currentMember);
        if (currentClass == null
                || currentMember == null) {
            return new ResponseEntity<>(currentClass, HttpStatus.EXPECTATION_FAILED);
        } else {
            currentClass.setMembers(memberToAdd);
            this.repository.save(currentClass);
        }

        return new ResponseEntity<>(currentClass, HttpStatus.CREATED);
    }
}
