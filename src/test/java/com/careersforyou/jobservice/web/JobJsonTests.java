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
        var job = new Job("1234567890", "Coder", "Code stuff", "CodeRUs",
                "thinking", "typing");
        var jsonContent = json.write(job);
        assertThat(jsonContent).extractingJsonPathStringValue("@.jobid").asString()
                .isEqualTo("1234567890");
        assertThat(jsonContent).extractingJsonPathStringValue("@.title").asString()
                .isEqualTo(job.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.description").asString()
                .isEqualTo(job.description());
        assertThat(jsonContent).extractingJsonPathStringValue("@.companyname").asString()
                .isEqualTo(job.companyname());
        assertThat(jsonContent).extractingJsonPathStringValue("@.skill1").asString()
                .isEqualTo(job.skill1());
        assertThat(jsonContent).extractingJsonPathStringValue("@.skill2").asString()
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
        ObjectContent<Job> objectContent = json.parse(content);
        Job expectedJob = objectContent.getObject();

        assertThat(expectedJob)
                .usingRecursiveComparison()
                .isEqualTo(new Job("1234567890", "Coder", "Code stuff", "CodeRUS",
                        "thinking", "typing"));
    }
}
