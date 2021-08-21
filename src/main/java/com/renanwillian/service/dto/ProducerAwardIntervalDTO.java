package com.renanwillian.service.dto;

import java.io.Serializable;
import java.util.List;

public class ProducerAwardIntervalDTO implements Serializable {

    private static final long serialVersionUID = -7094484989522684295L;

    private List<ProducerAwardDTO> min;
    private List<ProducerAwardDTO> max;

    public List<ProducerAwardDTO> getMin() {
        return min;
    }

    public void setMin(List<ProducerAwardDTO> min) {
        this.min = min;
    }

    public List<ProducerAwardDTO> getMax() {
        return max;
    }

    public void setMax(List<ProducerAwardDTO> max) {
        this.max = max;
    }


    public static final class Builder {

        private final ProducerAwardIntervalDTO producerAwardIntervalDTO;

        public Builder() {
            producerAwardIntervalDTO = new ProducerAwardIntervalDTO();
        }

        public Builder withMin(List<ProducerAwardDTO> min) {
            producerAwardIntervalDTO.setMin(min);
            return this;
        }

        public Builder withMax(List<ProducerAwardDTO> max) {
            producerAwardIntervalDTO.setMax(max);
            return this;
        }

        public ProducerAwardIntervalDTO build() {
            return producerAwardIntervalDTO;
        }
    }
}
