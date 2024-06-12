package Ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String firstName;
    private String lastName;
    private String email;
    private int password;
    private String role;
    private Date createDate;
    private Date updateDate;
    private boolean isDeleted;


}
