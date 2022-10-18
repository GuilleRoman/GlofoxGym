package com.example.demo.models;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "members")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;
    private String firstName;
    private String lastName;
    private String typeOfMembership;

    @ManyToMany(mappedBy = "members")
    private Set<Class> classes = new HashSet<>();

    public Member() {
    }

    public Member(long id, String firstName, String lastName, String typeOfMembership) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.typeOfMembership = typeOfMembership;
        this.classes = new HashSet<Class>();
    }

    public Member(String firstName, String lastName, String typeOfMembership) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.typeOfMembership = typeOfMembership;
        this.classes = new HashSet<Class>();
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTypeOfMembership() {
        return this.typeOfMembership;
    }

    public void setTypeOfMembership(String typeOfMembership) {
        this.typeOfMembership = typeOfMembership;
    }

}