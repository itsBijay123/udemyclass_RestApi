package com.javaguides.com.springbootrestapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )

    private long id;

    @Column(name ="first_Name",nullable = false)
    private String firstName;

    @Column(name ="last_Name",nullable = false)
    private String lastname;

    @Column(name ="email",nullable = false)
    private String email;
}
