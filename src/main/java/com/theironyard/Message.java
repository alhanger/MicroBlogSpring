package com.theironyard;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by alhanger on 11/9/15.
 */
@Entity
public class Message {

    @Id
    @GeneratedValue
    Integer id;

    String text;

}
