package vez.reactive.shipping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private final ApplicationContext applicationContext;

    @Autowired
    public HealthController(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @GetMapping({"/", "/actuator/info"})
    ResponseEntity<String> info() {
        return ResponseEntity.ok(applicationContext.getId()
                + " is alive and running on " + Thread.currentThread() + "\n"
        );
    }
}

