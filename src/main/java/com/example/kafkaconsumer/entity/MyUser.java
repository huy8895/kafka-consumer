package com.example.kafkaconsumer.entity;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MyUser extends AbstractClass{
    private int id;
    private String name;
    private String[] address;
}
