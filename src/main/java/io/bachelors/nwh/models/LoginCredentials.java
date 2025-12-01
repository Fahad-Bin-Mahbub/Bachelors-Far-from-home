package io.bachelors.nwh.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginCredentials {
    private String email;
    private String password;

}
