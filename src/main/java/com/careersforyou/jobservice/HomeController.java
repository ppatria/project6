package com.careersforyou.jobservice;

import com.careersforyou.jobservice.config.CareersforyouProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

        private final CareersforyouProperties careersforyouProperties;

        public HomeController(CareersforyouProperties careersforyouProperties) {
            this.careersforyouProperties = careersforyouProperties;
        }

        @GetMapping("/")
        public String getGreeting() {
            return careersforyouProperties.getGreeting();
        }
}
