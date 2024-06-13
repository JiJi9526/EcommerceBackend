package Ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UsersInfo {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
