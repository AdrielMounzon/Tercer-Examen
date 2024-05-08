package org.example.prueba2;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TarjetaDto {
    private String cardNumber;
    private String cvv;
    private String expirationDate;

    // Getters y Setters
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public JsonNode toJsonNode() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convertir el objeto a JSON
            String json = mapper.writeValueAsString(this);
            // Crear un JsonNode a partir del JSON
            return mapper.readTree(json);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
