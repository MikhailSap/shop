package ru.sap.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import ru.sap.database.model.Role;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private List<Role> roles;
}
