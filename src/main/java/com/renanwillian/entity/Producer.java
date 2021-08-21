package com.renanwillian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Entity
public class Producer implements Serializable {

    private static final long serialVersionUID = 3542417544635305958L;

    @Id
    @SequenceGenerator(allocationSize = 1, name = "producer_seq", sequenceName = "producer_producer_id_seq")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "producer_seq")
    @Column(name = "producer_id", nullable = false)
    private Long producerId;

    @Column(nullable = false)
    private String name;

    public Producer() {}

    public Producer(String name) {
        this.name = name;
    }

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long idProducer) {
        this.producerId = idProducer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
