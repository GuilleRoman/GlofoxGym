package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Member;
import com.example.demo.repositories.MemberRepository;

@RequestMapping("/members")
@RestController
public class MemberController {
    @Autowired
    public MemberRepository repository;

    @PostMapping
    public ResponseEntity<Member> saveMember(@RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String typeOfMembership) {
        Member newMember = new Member(firstName, lastName, typeOfMembership);
        try {
            this.repository.save(newMember);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(newMember, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<Object> getMembers() {
        List<Member> members = this.repository.findAll();
        return new ResponseEntity<>(members, HttpStatus.ACCEPTED);
    }

    @GetMapping
    @RequestMapping("/get")
    public ResponseEntity<Member> getMemberByName(@RequestParam String name) {
        Member member = this.repository.findByFirstName(name);
        return new ResponseEntity<>(member, HttpStatus.ACCEPTED);
    }

}
