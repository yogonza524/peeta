/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.model.checkout;

import java.io.Serializable;
import java.util.Objects;
import mercadopago.peeta.util.AttributeDoc;

/**
 * Buyer Information
 * @author gonzalo
 */
public class Payer implements Serializable{
    
    @AttributeDoc(content = "Buyer name", type = "String(256)")
    private String name;
    
    @AttributeDoc(content = "Buyer last name", type = "String(256)")
    private String surname;
    
    @AttributeDoc(content = "Buyer e-mail address", type = "String(256)")
    private String email;
    
    @AttributeDoc(content = "Buyer phone")
    private Phone phone;
    
    @AttributeDoc(content = "Personal identification")
    private Identification identification;
    
    @AttributeDoc(content = "Buyer address")
    private Address address;

    @AttributeDoc(content = "Registration date")
    private String date_created;
    
    public Payer() {
    }

    public Payer(String name, String surname, String email, String date_created, Phone phone, Identification identification, Address address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.date_created = date_created;
        this.phone = phone;
        this.identification = identification;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Identification getIdentification() {
        return identification;
    }

    public void setIdentification(Identification identification) {
        this.identification = identification;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.surname);
        hash = 61 * hash + Objects.hashCode(this.email);
        hash = 61 * hash + Objects.hashCode(this.date_created);
        hash = 61 * hash + Objects.hashCode(this.phone);
        hash = 61 * hash + Objects.hashCode(this.identification);
        hash = 61 * hash + Objects.hashCode(this.address);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Payer other = (Payer) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Payer{" + "name=" + name + ", surname=" + surname + ", email=" + email + ", date_created=" + date_created + ", phone=" + phone + ", identification=" + identification + ", address=" + address + '}';
    }
    
}
