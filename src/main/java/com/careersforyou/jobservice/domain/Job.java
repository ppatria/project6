package com.careersforyou.jobservice.domain;

import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record Job (

        // These validation constraints ensure when HTTP requests are made, they require to specify valid fields

        // NotBlank means there has to be something filled out for that field
        @NotBlank(message = "The job ID must be defined.")
        // This first pattern restricts jobid to only numbers and it has to be exactly 10 digits long
        @Pattern(
                regexp = "^([0-9]{10})$",
                message = "The job ID format must be valid."
        )
        String jobid,
        @NotBlank(message = "The job title must be defined."
        )
        // The next patterns restrict the field to consisting of lower or uppercase letters, numbers and spaces
        @Pattern(
                regexp = "^[a-zA-Z0-9 ]+$",
                message = "The job title must contain only letters and numbers."
        )
        String title,
        @NotBlank(message = "The job description must be defined."
        )
        @Pattern(
                regexp = "^[a-zA-Z0-9.,! ]+$"

        )
        String description,
        @NotBlank(message = "The company name must be defined."
        )
        @Pattern(
                regexp = "^[a-zA-Z0-9 ]+$",
                message = "The company name must contain only letters and numbers."
        )
        String companyname,
        @NotBlank(message = "Skill1 must be defined."
        )
        @Pattern(
                regexp = "^[a-zA-Z0-9 ]+$",
                message = "The company name must contain only letters and numbers."
        )
        String skill1,
        @NotBlank(message = "Skill2 must be defined."
        )
        @Pattern(
                regexp = "^[a-zA-Z0-9 ]+$",
                message = "The company name must contain only letters and numbers."
        )
        String skill2
){}