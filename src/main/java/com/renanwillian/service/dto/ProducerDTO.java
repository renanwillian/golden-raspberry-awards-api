package com.renanwillian.service.dto;

import com.renanwillian.entity.Producer;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;

@Schema(name = "Producer")
public class ProducerDTO {

    @Schema(hidden = true)
    private Long producerId;

    @NotEmpty
    private String name;

    public ProducerDTO() {}

    public ProducerDTO(Producer producer) {
        this.producerId = producer.getProducerId();
        this.name = producer.getName();
    }

    public Long getProducerId() {
        return producerId;
    }

    public void setProducerId(Long producerId) {
        this.producerId = producerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}