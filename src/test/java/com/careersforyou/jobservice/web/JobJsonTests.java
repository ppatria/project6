package com.careersforyou.jobservice.web;


import com.careersforyou.jobservice.domain.*;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.ObjectContent;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class JobJsonTests {

    @Autowired
    private JacksonTester<Job> json;

    @Test
    void testSerialize() throws Exception {
        var job = Job.of("1234567890", "Coder", "Code stuff", "CodeRUs",
                "thinking", "typing");
        var jsonContent = json.write(job);
        assertThat(jsonContent).extractingJsonPathStringValue("@.jobid")
                .isEqualTo("1234567890");
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualTo(job.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.description")
                .isEqualTo(job.description());
        assertThat(jsonContent).extractingJsonPathStringValue("@.companyname")
                .isEqualTo(job.companyname());
        assertThat(jsonContent).extractingJsonPathStringValue("@.skill1")
                .isEqualTo(job.skill1());
        assertThat(jsonContent).extractingJsonPathStringValue("@.skill2")
                .isEqualTo(job.skill2());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
                {
                    "jobid": "1234567890",
                    "title": "Coder",
                    "description": "Code stuff",
                    "companyname": "CodeRUS",
                    "skill1": "thinking",
                    "skill2": "typing"
                }
                """;

        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(Job.of("1234567890", "Coder", "Code stuff", "CodeRUS",
                        "thinking", "typing"));
    }
}