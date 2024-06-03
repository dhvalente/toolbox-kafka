package br.com.apache.kafka.controller;

import br.com.apache.kafka.services.StringProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerController {

        private final StringProducerService stringProducerService;

        public ProducerController(StringProducerService stringProducerService) {
            this.stringProducerService = stringProducerService;
        }

        @PostMapping
        public ResponseEntity<?> send (@RequestBody String message) {
            stringProducerService.send(message);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
}
