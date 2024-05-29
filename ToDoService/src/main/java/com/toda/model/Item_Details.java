package com.toda.model;

 import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "item_details")
@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
 @Validated
public class Item_Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;


    @Column(name = "createdAt")
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = "status")
    private Boolean status;


}
