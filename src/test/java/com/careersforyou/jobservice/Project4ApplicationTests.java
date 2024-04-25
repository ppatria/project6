package com.careersforyou.jobservice;

import com.careersforyou.jobservice.domain.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class Project4ApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void whenGetRequestWithIdThenJobReturned() {
		var jobidNum = "1231231230";
		var jobToCreate = Job.of(jobidNum, "Title", "Description",
				"Companyname", "Skill1", "Skill2");
		Job expectedJob = webTestClient
				.post()
				.uri("/jobs")
				.bodyValue(jobToCreate)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Job.class).value(job -> assertThat(job).isNotNull())
				.returnResult().getResponseBody();

		webTestClient
				.get()
				.uri("/jobs/" + jobidNum)
				.exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(Job.class).value(actualJob -> {
					assertThat(actualJob).isNotNull();
					assertThat(actualJob.jobid()).isEqualTo(expectedJob.jobid());
				});
	}

	@Test
	void whenPostRequestThenJobCreated() {
		var expectedJob = Job.of("1231231231", "Title", "Description",
				"Companyname", "Skill1", "Skill2");

		webTestClient
				.post()
				.uri("/jobs")
				.bodyValue(expectedJob)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Job.class).value(actualJob -> {
					assertThat(actualJob).isNotNull();
					assertThat(actualJob.jobid()).isEqualTo(expectedJob.jobid());
				});
	}

	@Test
	void whenPutRequestThenJobUpdated() {
		var jobidNum = "1231231232";
		var jobToCreate = Job.of(jobidNum, "Title", "Description",
				"Companyname", "Skill1", "Skill2");
		Job createdJob = webTestClient
				.post()
				.uri("/jobs")
				.bodyValue(jobToCreate)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Job.class).value(job -> assertThat(job).isNotNull())
				.returnResult().getResponseBody();
		var jobToUpdate = new Job(createdJob.id(), createdJob.jobid(), createdJob.title(), createdJob.description(),
				createdJob.companyname(), createdJob.skill1(), createdJob.skill2(), createdJob.version());

		webTestClient
				.put()
				.uri("/jobs/" + jobidNum)
				.bodyValue(jobToUpdate)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Job.class).value(actualJob -> {
					assertThat(actualJob).isNotNull();
					assertThat(actualJob.skill1()).isEqualTo(jobToUpdate.skill1());
				});
	}

	@Test
	void whenDeleteRequestThenJobDeleted() {
		var jobidNum = "1231231233";
		var jobToCreate = Job.of(jobidNum, "Title", "Description",
				"Companyname", "Skill1", "Skill2");
		webTestClient
				.post()
				.uri("/jobs")
				.bodyValue(jobToCreate)
				.exchange()
				.expectStatus().isCreated();

		webTestClient
				.delete()
				.uri("/jobs/" + jobidNum)
				.exchange()
				.expectStatus().isNoContent();

		webTestClient
				.get()
				.uri("/jobs/" + jobidNum)
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(String.class).value(errorMessage ->
						assertThat(errorMessage).isEqualTo("The job with jobid " + jobidNum + " was not found.")
				);
	}

}
