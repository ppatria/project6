package com.careersforyou.jobservice.domain;

import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



import static org.assertj.core.api.Assertions.assertThat;

public class JobValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var job =
                Job.of("1234567890", "Title", "Description",
                        "Companyname", "Skill1", "Skill2");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenJobidNotDefinedThenValidationFails() {
        var job = Job.of("", "Title", "Description", "Companyname", "Skil1", "Skill2");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(2);
        List<String> constraintViolationMessages = violations.stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.toList());
        assertThat(constraintViolationMessages)
                .contains("The jobid must be defined.")
                                .contains("The jobid format must be valid.");
    }

    @Test
    void whenJobidDefinedButIncorrectThenValidationFails() {
        var job =
                Job.of("abc4567890", "Title", "Description",
                        "Companyname", "Skill1", "Skill2");
        Set<ConstraintViolation<Job>> violations = validator.validate(job);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("The jobid format must be valid.");
    }
}

