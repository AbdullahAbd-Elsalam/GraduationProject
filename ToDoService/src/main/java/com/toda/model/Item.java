package com.toda.model;

 import io.swagger.annotations.ApiModel;
 import io.swagger.annotations.ApiModelProperty;
 import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;



@Entity
@Table(name = "item")
@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Validated
@ApiModel("this documentation for item")
 public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("this is user id for item")
    @Column(name = "id")
    private int id;



    @Transient
    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "user_id")
    private String userId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_details_id")
    private Item_Details itemDetails;

    public Item(String title) {
        this.title = title;
    }
}
