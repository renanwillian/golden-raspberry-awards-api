package com.renanwillian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Entity
public class Studio implements Serializable {

    private static final long serialVersionUID = -5949563852671157176L;

    @Id
    @SequenceGenerator(allocationSize = 1, name = "studio_seq", sequenceName = "studio_studio_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "studio_seq")
    @Column(name = "studio_id", nullable = false)
    private Long studioId;

    @Column(nullable = false)
    private String name;

    public Studio() {}

    public Studio(String name) {
        this.name = name;
    }

    public Long getStudioId() {
        return studioId;
    }

    public void setStudioId(Long idProducer) {
        this.studioId = idProducer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
