/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercadopago.peeta.requests;

import java.io.Serializable;
import java.util.Objects;
import lombok.Builder;
import mercadopago.peeta.error.BodyError;
import mercadopago.peeta.util.CheckoutBaseRequest;
import mercadopago.peeta.model.checkout.CheckoutModel;

/**
 *
 * @author gonzalo
 */
@Builder
public class CheckoutRequest extends CheckoutBaseRequest implements Serializable{

    private int status;
    private CheckoutModel response;
    private BodyError responseError;

    public CheckoutRequest(int status, CheckoutModel response, BodyError responseError) {
        this.status = status;
        this.response = response;
        this.responseError = responseError;
    }

    public CheckoutRequest(int status, BodyError responseError) {
        this.status = status;
        this.responseError = responseError;
    }

    public CheckoutRequest(int status, CheckoutModel response) {
        this.status = status;
        this.response = response;
    }

    public BodyError getResponseError() {
        return responseError;
    }

    public void setResponseError(BodyError responseError) {
        this.responseError = responseError;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    public CheckoutModel getResponse() {
        return response;
    }

    public void setResponse(CheckoutModel response) {
        this.response = response;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.status;
        hash = 11 * hash + Objects.hashCode(this.response);
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
        final CheckoutRequest other = (CheckoutRequest) obj;
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.response, other.response)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PayCreatedOk{" + "status=" + status + ", response=" + response + '}';
    }

    @Override
    public BodyError withError() {
        return this.responseError == null ? BodyError.builder().build() : this.responseError;
    }

    @Override
    public CheckoutModel ok() {
        return this.response == null? CheckoutModel.builder().build(): this.response;
    }

    
    
    
    
}
