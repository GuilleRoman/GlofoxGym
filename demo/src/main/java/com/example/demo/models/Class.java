package com.example.demo.models;

import java.io.Serializable;
import java.util.HashSet;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "classes")
public class Class implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long id;
    private String name;
    private long capacity;
    private String date;
    // private String end_date;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "class_members", inverseJoinColumns = @JoinColumn(name = "member_name", referencedColumnName = "firstName"), joinColumns = {
            @JoinColumn(name = "class_name", referencedColumnName = "name"),
            @JoinColumn(name = "class_date", referencedColumnName = "date") })
    private Set<Member> members = new HashSet<>();

    public Set<Member> getMembers() {
        return this.members;
    }

    public void setMembers(Set<Member> members) {
        this.members = members;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCapacity() {
        return this.capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public String getStart_date() {
        return this.date;
    }

    public void set_date(String start_date) {
        this.date = start_date;
    }

    // public String getEnd_date() {
    // return this.end_date;
    // }

    // public void setEnd_date(String end_date) {
    // this.end_date = end_date;
    // }

    public Class() {
    }

    public Class(long id, String name, long capacity, String date) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.date = date;
        this.members = new HashSet<Member>();
        // this.end_date = end_date;
    }

    public Class(String name, long capacity, String date) {
        this.name = name;
        this.capacity = capacity;
        this.date = date;
        this.members = new HashSet<Member>();
        // this.end_date = end_date;
    }

}
