package com.spring.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name ="authority")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Long id;
    @Column(name = "authority_name")
    private String authorityName;
    @Autowired
    @ManyToMany(mappedBy = "authorities")
    private List<User> users ;
}
