package farm2Pot.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserControllerTest {


    @GetMapping("/test")
    public ResponseEntity<String> test() {
        System.out.println("user test!!");
        return ResponseEntity.ok("OK");
    }
}
