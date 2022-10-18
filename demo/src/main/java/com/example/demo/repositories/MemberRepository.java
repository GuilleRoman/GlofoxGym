package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event.ID;

import com.example.demo.models.Member;

public interface MemberRepository extends JpaRepository<Member, ID> {

    public Member findByFirstName(String firstName);

}
