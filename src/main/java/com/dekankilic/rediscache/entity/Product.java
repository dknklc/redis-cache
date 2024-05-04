package com.dekankilic.rediscache.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Double price;
    private int stock;
}

// In Spring Boot Caching, add dependency to pom.xml, then add @EnableCaching annotation to SpringBootApplication, and add @Cacheable annotation to the method in the service.
// After adding a new Product, what will happen? I have a problem, I cannot see the newly added product in the response coming from Cache.So, I need to say to Spring, please update the Cache on this particular event. To do this,
// Use @CacheEvict("products", allEntries = true) allEntries = true means update all products in the Cache. But, there is a way of picking only the updated entry in the Cache.
// The question is that What if I have a multiple instances of this application runnig?Because I am using in-memory caching, every instance will maintain its own copy because in simple spring boot caching, everything is stored
// on the concurrent map. Thus, we want to use some sort of distributed Cache. I am going to use Redis. What is its advantage? We will have a central distributed Cache which will be used by all of our instances.
// We do not change anything in our program. Just change the pom.xml to say our spring application use redis instead of simple one.

// Problem Occurs when not using RedisConfig :
// Exception : DefaultSerializer requires a Serializable payload but received an object of type [org.springframework.http.ResponseEntity]
// Solution : Cache objects should be Serializable but ResponseEntity is not Serializable.
