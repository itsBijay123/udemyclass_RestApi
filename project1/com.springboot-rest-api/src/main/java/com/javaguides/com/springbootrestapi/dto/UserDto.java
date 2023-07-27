package com.javaguides.com.springbootrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Schema(
        description = "UserDto Model Information"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private long id;


    @Schema(
            description = "User First Name"
    )

    //user firstName should not be null or empty
  @NotEmpty
  @Size(min = 2, message = "Post title should have at least 2 characters")
    private String firstName;

    @Schema(
            description = " User LastName"
    )
  //user lastName should not be null or empty
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String lastName;


    @Schema(
            description="User Email Address "
    )
    //user email should not be null or empty
    //Email address should be valid
    @NotEmpty
    @Email
    private String email;
}
