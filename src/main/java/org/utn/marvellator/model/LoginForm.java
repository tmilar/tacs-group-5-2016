package org.utn.marvellator.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class LoginForm {

    @NotBlank
    @Length(min=2, max=8)
    private String userName;
    @NotBlank
    @Length(min=4, max=15)
 private String password;
}
