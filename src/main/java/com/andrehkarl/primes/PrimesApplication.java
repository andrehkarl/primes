package com.andrehkarl.primes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableCaching
/**
 * Spring Boot application class
 */
public class PrimesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrimesApplication.class, args);
    }

    @RequestMapping("/primes/{initial}")
    @Cacheable(cacheNames="primeNumbers", key="#initial")
    public Primes calculatePrimes(@PathVariable("initial") int initial,
                                  @RequestParam(value = "parallelalgo" , required = false) Boolean parallel) {
        Primes result = new Primes(initial, parallel);
        return result;
    }
}
