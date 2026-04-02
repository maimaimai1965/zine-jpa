package ua.mai.zine.kafka.mock_service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class StatusCheckController {

    private static final Logger log = LogManager.getLogger(StatusCheckController.class);

    @GetMapping("/200")
    ResponseEntity<String> response200String() {
        log.info("response 200");
        return ResponseEntity.ok().body("200");
    }

    @GetMapping("/500")
    ResponseEntity<String> response500String() {
        log.info("response 500");
        return ResponseEntity.internalServerError().build();
    }

}
