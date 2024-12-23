package com.example.blog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "title can not be null")
    @Size(min = 3,max = 10,message = "title size can not be less than 3 or more than 10 ")
    @Column(columnDefinition = "varchar(10) not null")
    private String title;

    @NotEmpty(message = "description can not be null")
    @Size(min = 5,max = 50,message = " body can not be less than 5 or mor than 50")
    private String description;   //Body

    @ManyToOne
    @JsonIgnore
    private User user;

}
