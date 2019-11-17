package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long     id;
    private String   name;
    private String   surname;
    @Column(unique = true)
    private String   email;
    private String   number;
    private String   password;
    private String   subscription;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
