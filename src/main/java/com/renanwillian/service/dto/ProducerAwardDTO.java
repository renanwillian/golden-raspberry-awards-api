package com.renanwillian.service.dto;

import java.io.Serializable;

public class ProducerAwardDTO implements Serializable {

    private static final long serialVersionUID = -7094484989522684295L;

    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getPreviousWin() {
        return previousWin;
    }

    public void setPreviousWin(Integer previousWin) {
        this.previousWin = previousWin;
    }

    public Integer getFollowingWin() {
        return followingWin;
    }

    public void setFollowingWin(Integer followingWin) {
        this.followingWin = followingWin;
    }

    public static final class Builder {

        private ProducerAwardDTO producerAwardDTO;

        public Builder() {
            producerAwardDTO = new ProducerAwardDTO();
        }

        public Builder withProducer(String producer) {
            producerAwardDTO.setProducer(producer);
            return this;
        }

        public Builder withInterval(Integer interval) {
            producerAwardDTO.setInterval(interval);
            return this;
        }

        public Builder withPreviousWin(Integer previousWin) {
            producerAwardDTO.setPreviousWin(previousWin);
            return this;
        }

        public Builder withFollowingWin(Integer followingWin) {
            producerAwardDTO.setFollowingWin(followingWin);
            return this;
        }

        public ProducerAwardDTO build() {
            return producerAwardDTO;
        }
    }
}
