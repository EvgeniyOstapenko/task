package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

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
    @NotEmpty
    private String   name;
    @NotEmpty
    private String   surname;
    @Column(unique = true)
    private String   email;
    @NotEmpty
    private String   number;
    @NotEmpty
    private String   password;
    private String   subscription;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
