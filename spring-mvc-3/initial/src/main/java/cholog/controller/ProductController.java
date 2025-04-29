package cholog.controller;

import cholog.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/products")
    public ResponseEntity<Void> searchProduct(@RequestParam String keyword) {
        if (true) {
            throw new IllegalArgumentException("Invalid keyword: " + keyword);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/products/{id}")
    public void getProduct(@PathVariable Long id) {
        if (true) {
            throw new NotFoundException("Product not found: id=" + id);
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<Void> handlerIllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
