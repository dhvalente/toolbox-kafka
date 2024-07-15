package br.com.apache.kafka.model;

import java.io.Serializable;


public class Payment implements Serializable {

    private Long id;
    private Long idUser;
    private Long idProduct;
    private String cardNumber;

    public Long getId() {
        return id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idProduct=" + idProduct +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
