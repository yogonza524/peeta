# Peeta. What is it?
Lightweight tool for access to the different features of MercadoPago.

# What is MercadoPago
It is a payment processing tool created by MercadoLibre Argentina, with regional impact in Latin America. Allowed at https://www.mercadopago.com.ar

# Requirements
To use Peeeta you need to create an account in Mercadopago and access the keys using the developer tools

# Install dependencies
Peeta need next dependencies for work:
```xml
    <dependencies>
        <dependency>
            <groupId>io.github.yogonza524</groupId>
            <artifactId>peeta</artifactId>
            <version>1.0.1</version>
        </dependency>
    </dependencies>
```
And then just add the repository (this artifact is alowed at Sonatype)
```xml
      <repositories>
        <repository>
            <id>oss-sonatype</id>
            <name>oss-sonatype</name>
            <url>https://oss.sonatype.org/content/repositories/releases/</url>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>
      </repositories>
```
# Configure credentials
Just find your MercadoPago credentials at https://www.mercadopago.com.ar/developers/es/api-docs/basics/authentication and then
```java
Peeta p = Peeta.builder("YOUR_CLIENT_ID","YOUR_CLIENT_SECRET").build();
```

# A simple user story:
>"As a developer I want to make a very simple test of Peeta's use to see its basic operation"

Fact. Please look at the following basic example. This test list all pay methods availables at MercadoPago
```java
    @Test
    public void buildTest() {
        Peeta peeta = Peeta.builder(clientId, secret).build();
        Optional<PayMethodRequest> request = peeta.payMethods();
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        if (request.isPresent()) {
            if (request.success()) {
                request.get().ok().stream().forEach(c -> {
                    System.out.println(g.toJson(c));
                }); 
            }
            else {
                System.out.println("Error: " + g.toJson(request.get().error()));
            }
        }
    }
```
Whose answer is
```json
{
  "id": "visa",
  "name": "Visa",
  "payment_type_id": "credit_card",
  "status": "active",
  "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/visa.gif",
  "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/visa.gif",
  "deferred_capture": "supported",
  "settings": [
    {
      "card_number": {
        "validation": "standard",
        "length": 16
      },
      "bin": {
        "pattern": "^4",
        "installments_pattern": "^4",
        "exclusion_pattern": "^(400276|400615|402914|404625|405069|405515|405516|405755|405896|405897|406290|406291|406375|406652|406998|406999|408515|410082|410083|410121|410123|410853|411849|417309|421738|423623|428062|428063|428064|434795|437996|439818|442371|442548|444060|446343|446344|446347|450412|450799|451377|451701|451751|451756|451757|451758|451761|451763|451764|451765|451766|451767|451768|451769|451770|451772|451773|457596|457665|462815|463465|468508|473710|473711|473712|473714|473715|473716|473717|473718|473719|473720|473721|473722|473725|477051|477053|481397|481501|481502|481550|483002|483020|483188|489412|492528|499859|446344|446345|446346|400448)"
      },
      "security_code": {
        "length": 3,
        "card_location": "back",
        "mode": "mandatory"
      }
    }
  ],
  "additional_info_needed": [
    "cardholder_name",
    "cardholder_identification_type",
    "cardholder_identification_number"
  ],
  "min_allowed_amount": 0.0,
  "max_allowed_amount": 250000.0,
  "accreditation_time": 2880,
  "financial_institutions": [],
  "processing_modes": [
    "aggregator"
  ]
}
```
# More stories for you
Below I show you more features applicable to the context of payments with Peeta

>"As a developer I want to create a payment preference in Sandbox mode (test)"


Fact. Sandbox mode provides a test context for different types of operations. Here we will create a payment preference for a Toyota Corolla

```java
     @Test
     public void createCheckoutTest() throws Exception {
        //Model for checkout preference
        CheckoutModel model = CheckoutModel
            .builder()
            .items(Arrays.asList(CheckoutItem
                        .builder()
                        .title("Toyota Corolla 2019")
                        .picture_url("http://www.toyota.com.ar/showroom/corolla/images/gallery/02-03.jpg")
                        .quantity(1)
                        .unit_price(220584.0)
                        .build()
                ))
            .build();
        
        Optional<CheckoutBaseRequest> check = p.sandbox(true).createCheckout(model);
        
        if (check.isPresent()) {
            CheckoutBaseRequest checkout = check.get();
            System.out.println(
                    checkout.success() ? 
                        "URL of new checkout: " + checkout.ok().getSandbox_init_point() : 
                        "Error: "               + checkout.withError().getMessage()
            );
            
        }
     }
```
In response we see the following
```bash
URL of new checkout: https://sandbox.mercadopago.com/mla/checkout/pay?pref_id=139929232-0d4f364e-86c3-4215-9ddc-b9441fe3506b
```
![Checkout preference created](https://i.imgur.com/P6NZgTy.png)

> "As a developer I want to obtain the payment preference created in the previous example"

Fact. We just have to ask Peeta to look for it from the preference_id

```java
     @Test
     public void getCheckoutTest() {
         Peeta p = Peeta.builder(clientId, secret).build();
         Gson g = new GsonBuilder().setPrettyPrinting().create();
         
         Optional<CheckoutRequest> preference = p.sandbox(true).getCheckout("139929232-0d4f364e-86c3-4215-9ddc-b9441fe3506b");
         
         if (preference.isPresent()) {
            CheckoutRequest request = preference.get();
            
            if (request.success()) {
                System.out.println(g.toJson(request.ok()));
            }
            else {
                System.out.println(g.toJson(request.error()));
            }
         }
     }
```
And the response will be:
```xml
{
  "collector_id": 139929232,
  "operation_type": "regular_payment",
  "items": [
    {
      "id": "",
      "title": "Toyota Corolla 2019",
      "description": "",
      "picture_url": "http://www.toyota.com.ar/showroom/corolla/images/gallery/02-03.jpg",
      "category_id": "",
      "quantity": 1,
      "currency_id": "ARS",
      "unit_price": 220584.0
    }
  ],
  "payer": {
    "name": "",
    "surname": "",
    "email": "",
    "phone": {
      "area_code": "",
      "number": ""
    },
    "identification": {
      "type": "",
      "number": ""
    },
    "address": {
      "zip_code": "",
      "street_name": ""
    },
    "date_created": ""
  },
  "back_urls": {
    "success": "",
    "pending": "",
    "failure": ""
  },
  "auto_return": "",
  "external_reference": "",
  "expires": false,
  "payment_methods": {
    "excluded_payment_methods": [
      {
        "id": ""
      }
    ],
    "excluded_payment_types": [
      {
        "id": ""
      }
    ],
    "installments": 0
  },
  "client_id": 963,
  "marketplace": "NONE",
  "marketplace_fee": 0,
  "additional_info": "",
  "id": "139929232-0d4f364e-86c3-4215-9ddc-b9441fe3506b",
  "init_point": "https://www.mercadopago.com/mla/checkout/start?pref_id\u003d139929232-0d4f364e-86c3-4215-9ddc-b9441fe3506b",
  "sandbox_init_point": "https://sandbox.mercadopago.com/mla/checkout/pay?pref_id\u003d139929232-0d4f364e-86c3-4215-9ddc-b9441fe3506b",
  "date_created": "2018-02-10T12:54:34.959-04:00"
}
```

> "As a developer I want to update an item of the payment preference that I created in the first example."

Fact. Remember that according to MercadoPago all the items are stateless with which you must be careful as the actual process deletes any previous item, Peeta continues with the stateless scheme of MercadoPago. Let's see

```java
     @Test
     public void updateCheckoutModelTest() throws Exception {
         Peeta p = Peeta.builder(clientId, secret).build();
         Gson g = new GsonBuilder().setPrettyPrinting().create();
         
         CheckoutModel data = CheckoutModel.builder()
                 .id("139929232-0d4f364e-86c3-4215-9ddc-b9441fe3506b")
                 .items(Arrays.asList(CheckoutItem.builder()
                            .id("18")
                            .title("Toyota Avalon 2018")
                            .picture_url("https://photos7.motorcar.com/new-2018-toyota-avalon-xlepremium-11144-16516945-2-1024.jpg")
                            .quantity(1)
                            .unit_price(234567.67)
                            .build()
                 )).build();
         
         Optional<CheckoutRequest> request = p.updateCheckout(data);
         
         if (request.isPresent()) {
             CheckoutRequest r = request.get();
             if (r.success()) {
                 System.out.println(r.ok().toStringPretty());
             }
             else {
                 System.out.println(r.withError().toStringPretty());
             }
         }
     }
```
And the response will be:

```json
{
  "collector_id": 139929232,
  "operation_type": "regular_payment",
  "items": [
    {
      "id": "18",
      "title": "Toyota Avalon 2018",
      "description": "",
      "picture_url": "https://photos7.motorcar.com/new-2018-toyota-avalon-xlepremium-11144-16516945-2-1024.jpg",
      "category_id": "",
      "quantity": 1,
      "currency_id": "ARS",
      "unit_price": 234567.67
    }
  ],
  "payer": {
    "name": "",
    "surname": "",
    "email": "",
    "phone": {
      "area_code": "",
      "number": ""
    },
    "identification": {
      "type": "",
      "number": ""
    },
    "address": {
      "zip_code": "",
      "street_name": ""
    },
    "date_created": ""
  },
  "back_urls": {
    "success": "",
    "pending": "",
    "failure": ""
  },
  "auto_return": "",
  "external_reference": "",
  "expires": false,
  "payment_methods": {
    "excluded_payment_methods": [
      {
        "id": ""
      }
    ],
    "excluded_payment_types": [
      {
        "id": ""
      }
    ],
    "installments": 0
  },
  "client_id": 963,
  "marketplace": "NONE",
  "marketplace_fee": 0,
  "additional_info": "",
  "id": "139929232-0d4f364e-86c3-4215-9ddc-b9441fe3506b",
  "init_point": "https://www.mercadopago.com/mla/checkout/start?pref_id\u003d139929232-0d4f364e-86c3-4215-9ddc-b9441fe3506b",
  "sandbox_init_point": "https://sandbox.mercadopago.com/mla/checkout/pay?pref_id\u003d139929232-0d4f364e-86c3-4215-9ddc-b9441fe3506b",
  "date_created": "2018-02-10T12:54:34.959-04:00"
}
```

> "As a developer I want to know the cost of a package assessed by OCA given the dimensions and weight of it"

Fact. MercadoPago has an agreement with OCA (one of the many Argentine Postal Services), through Peeta we can obtain such information quickly and easily. Let's checkout this.

```java
     @Test
     public void shippingEntityTest() {
        Peeta p = Peeta.builder(clientId, secret).build();
        Gson g = new GsonBuilder().setPrettyPrinting().create();
        
        Optional<ShippingCalculatorRequest> sh = p.shipping(
            ShippingPackage
                .builder()
                .height(20) //centimeters
                .width(13)  //centimeters
                .weight(10) //grams
                .large(22)  //centimeters
                .zip_code("3400") //Corrientes Capital ZIP Code (Argentine)
                .free_method(
                    FreeMethods.OCA_PRIORITY.value() 
                )
                .build()
        );
         
        if (sh.isPresent()) {
            ShippingCalculatorRequest shipping = sh.get();
            System.out.println(
                shipping.success() ? 
                    shipping.ok().toStringPretty() :
                    shipping.error().toStringPretty()
            );
         }
     }
```

And the response will be
```json
{
  "custom_message": {
    "reason": ""
  },
  "options": [
    {
      "tags": [],
      "id": "415369122",
      "estimated_delivery_time": {
        "unit": "hour",
        "shipping": 48.0,
        "time_frame": {},
        "offset": {
          "shipping": 48.0
        },
        "type": "unknown_frame"
      },
      "list_cost": 139.99,
      "currency_id": "ARS",
      "shipping_option_type": "address",
      "shipping_method_type": "standard",
      "name": "Normal a domicilio",
      "display": "recommended",
      "cost": 139.99,
      "discount": {
        "promoted_amount": 0.0,
        "rate": 0.0,
        "type": "none"
      },
      "shipping_method_id": 73328
    }
  ],
  "destination": {
    "zip_code": "3400",
    "state": {
      "id": "AR-W",
      "name": "Corrientes"
    },
    "country": {
      "id": "AR",
      "name": "Argentina"
    },
    "city": {}
  }
}
```

> "As a developer I want to know the different issuers associated with a type of card, for example Mastercard"

Fact. Remember that in order to know the issuers previously, you must know the name of the credit institution (Visa, Mastercard, etc.) provided by the example "Get payment methods". Let's see:

```java
     @Test
     public void emisoresEntityTest() {
         Peeta p = Peeta.builder(clientId, secret).build();
         Gson g = new GsonBuilder().setPrettyPrinting().create();
        
         Optional<CardIssuerRequest> req = p.cardissuer("master");
         
         if (req.isPresent()) {
             CardIssuerRequest issuers = req.get();
             if (issuer.success()) {
                issuers.ok().stream().forEach(i -> {
                    System.out.println(g.toJson(i));
                });
             }
             else {
                 System.out.println(g.toJson(issuers.error()));
             }
         }
     }
```

And the response will be:
```json
{
  "id": "1078",
  "name": "Mercado Pago + Banco Patagonia",
  "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/1078.gif",
  "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/1078.gif",
  "processing_mode": "aggregator"
}
{
  "id": "1007",
  "name": "Nativa Mastercard",
  "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/1007.gif",
  "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/1007.gif",
  "processing_mode": "aggregator"
}
{
  "id": "692",
  "name": "Cencosud",
  "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/692.gif",
  "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/692.gif",
  "processing_mode": "aggregator"
}
{
  "id": "331",
  "name": "Nuevo Banco de Entre Rios",
  "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/331.gif",
  "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/331.gif",
  "processing_mode": "aggregator"
}
{
  "id": "287",
  "name": "Banco Santa Cruz",
  "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/287.gif",
  "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/287.gif",
  "processing_mode": "aggregator"
}
```

> "As a developer I want to know what are the types of personal identification that MercadoPago manages"

Fact. Peeta provides a very simple way to check this MercadoPago. Let's see

```java
     @Test
     public void dniEntityTest() {
         Peeta p = Peeta.builder(clientId, secret).build();
         Gson g = new GsonBuilder().setPrettyPrinting().create();
     
         Optional<IdentificationTypeRequest> types = p.identificationTypes();
         
         if (types.isPresent()) {
             if (types.get().success()) {
                types.get().ok().stream().forEach(c -> {
                    System.out.println(g.toJson(c));
                });
             }
             else {
                 System.out.println("Error: " + types.get().error().getMessage());
             }
         }
     }
```

And the response will be:

```json
{
  "id": "DNI",
  "name": "DNI",
  "type": "number",
  "min_length": 7,
  "max_length": 8
}
{
  "id": "CI",
  "name": "Cédula",
  "type": "number",
  "min_length": 1,
  "max_length": 9
}
{
  "id": "LC",
  "name": "L.C.",
  "type": "number",
  "min_length": 6,
  "max_length": 7
}
{
  "id": "LE",
  "name": "L.E.",
  "type": "number",
  "min_length": 6,
  "max_length": 7
}
{
  "id": "Otro",
  "name": "Otro",
  "type": "number",
  "min_length": 5,
  "max_length": 20
}
```

> "As a developer I want to know the personal data of the account associated to MercadoPago used by Peeta"

Fact. Peeta provides the facility to obtain the data associated with the account in a very simple way. Let's see

```java
     @Test
     public void userEntityMeTest() {
         Peeta p = Peeta.builder(clientId, secret).build();
         Gson g = new GsonBuilder().setPrettyPrinting().create();
     
         Optional<UserRequest> request = p.me();
         if (request.isPresent()) {
             if (request.get().success()) {
                 User u = request.get().ok();
                 System.out.println(g.toJson(u));
             }
             else {
                 BodyError e = request.get().error();
                 System.out.println(g.toJson(e));
             }
         }
     }
```

And the response will be

```json
{
  "id": 139929232,
  "nickname": "MENDOZAGONZALO75",
  "registration_date": "2013-06-07T22:10:43.000-04:00",
  "first_name": "Gonzalo",
  "last_name": "Mendoza",
  "country_id": "AR",
  "email": "yogonza524@gmail.com",
  "identification": {
    "number": "34093153",
    "type": "DNI"
  },
  "address": {
    "address": "Feliz de Azara 660",
    "city": "Capital",
    "state": "AR-W",
    "zip_code": "3400"
  },
  "phone": {
    "area_code": " ",
    "extension": "",
    "number": "3794267413",
    "verified": false
  },
  "alternative_phone": {
    "area_code": "",
    "extension": "",
    "number": ""
  },
  "user_type": "normal",
  "tags": [
    "normal",
    "user_info_verified",
    "messages_as_seller",
    "messages_as_buyer"
  ],
  "points": 1,
  "site_id": "MLA",
  "permalink": "http://perfil.mercadolibre.com.ar/MENDOZAGONZALO75",
  "shipping_modes": [
    "custom",
    "not_specified",
    "me2"
  ],
  "seller_experience": "INTERMEDIATE",
  "buyer_reputation": {
    "canceled_transactions": 0,
    "tags": []
  },
  "status": {
    "billing": {
      "allow": true,
      "codes": []
    },
    "buy": {
      "allow": true,
      "codes": [],
      "immediate_payment": {
        "reasons": [],
        "required": false
      }
    },
    "confirmed_email": true,
    "shopping_cart": {},
    "immediate_payment": false,
    "list": {
      "allow": true,
      "codes": [],
      "immediate_payment": {
        "reasons": [],
        "required": false
      }
    },
    "mercadoenvios": "not_accepted",
    "mercadopago_account_type": "personal",
    "mercadopago_tc_accepted": true,
    "required_action": "",
    "sell": {
      "allow": true,
      "codes": [],
      "immediate_payment": {
        "reasons": [],
        "required": false
      }
    },
    "site_status": "active",
    "user_type": "simple_registration"
  },
  "secure_email": "gmendoz.5g013s@mail.mercadolibre.com",
  "context": {}
}
```

> "As a developer I want to know the data of the fees of some card, for example Visa"

Fact. Remember that you must previously know the name of the card. Let's see

```java
     @Test
     public void getInstallmentsTest() {
         Peeta p = Peeta.builder(clientId, secret).build();
         Gson g = new GsonBuilder().setPrettyPrinting().create();
     
         String id = "visa";
         
         Optional<InstallmentsRequest> req = p.getInstallmentById(id);
         
         if (req.isPresent()) {
             InstallmentsRequest i = req.get();
             if (i.success()) {
                 System.out.println(g.toJson(i.ok()));
             }
             else {
                 System.out.println(g.toJson(i.error()));
             }
         }
     }
```

And the response will be:

```json
[
  {
    "payment_method_id": "visa",
    "payment_type_id": "credit_card",
    "issuer": {
      "id": "288",
      "name": "Tarjeta Shopping",
      "secure_thumbnail": "https://www.mercadopago.com/org-img/MP3/API/logos/288.gif",
      "thumbnail": "http://img.mlstatic.com/org-img/MP3/API/logos/288.gif"
    },
    "payer_costs": [
      {
        "installments": 1,
        "installment_rate": 0.0,
        "labels": [
          "CFT_0,00%|TEA_0,00%"
        ],
        "min_allowed_amount": 0,
        "max_allowed_amount": 250000,
        "recommended_message": "1"
      },
      {
        "installments": 3,
        "installment_rate": 10.97,
        "labels": [
          "CFT_87,81%|TEA_69,00%"
        ],
        "min_allowed_amount": 2,
        "max_allowed_amount": 250000,
        "recommended_message": "3"
      },
      {
        "installments": 6,
        "installment_rate": 19.51,
        "labels": [
          "CFT_86,71%|TEA_68,51%",
          "recommended_interest_installment_with_some_banks"
        ],
        "min_allowed_amount": 3,
        "max_allowed_amount": 250000,
        "recommended_message": "6"
      },
      {
        "installments": 9,
        "installment_rate": 31.46,
        "labels": [
          "CFT_97,84%|TEA_77,34%"
        ],
        "min_allowed_amount": 5,
        "max_allowed_amount": 250000,
        "recommended_message": "9"
      },
      {
        "installments": 12,
        "installment_rate": 42.11,
        "labels": [
          "recommended_installment",
          "CFT_98,26%|TEA_78,00%"
        ],
        "min_allowed_amount": 6,
        "max_allowed_amount": 250000,
        "recommended_message": "12"
      }
    ]
  }, ...
]
```
# Documentation
Peeta has a mecansimo access to documentation in the same source code.

## Documented classes (Listed at enum DocumentableClass)
* CheckoutModel
* CheckoutItem
* Identification
* IdentificationType
* MerchantOrder
* Balance
* BankReport
* SettlementReport
* Customer
* Payment
* PayMethod
* Issuer (Card Issuer)
* Installment
* Payer
* Phone
* TransactionDetail
* FeeDetail
* Card
* CardHolder
* Refund
* Source (PaymentRefundSource)
* AdditionalInfo (PaymentAditionalInfo)
* PayerInfo
* ShipmentsInfo
* BarCode

## How do I access the documentation?

Very simple, just invoke the following method from Peeta
```java
Peeta.describe(DocumentableClass.Payment);
```

This invocation always returns an object of type Documentation. Let´s see

```json
{
  "sourceUrl": "https://www.mercadopago.com.ar/developers/es/api-docs/custom-checkout/create-payments/",
  "topic": {
    "title": "Payment API",
    "description": "This service allows you to create, modify or read payments",
    "related": [
      "https://www.mercadopago.com.ar/developers/es/api-docs/custom-checkout/create-payments/idempotence/"
    ]
  },
  "actions": [
    {
      "method": "GET",
      "description": "Retrieves information about a payment",
      "endpoint": "/v1/payments/:id"
    },
    {
      "method": "POST",
      "description": "Issues a new payment",
      "endpoint": "/v1/payments"
    },
    {
      "method": "PUT",
      "description": "Updates a payment",
      "endpoint": "/v1/payments/:id"
    }
  ],
  "attributes": [
    {
      "name": "id",
      "className": "java.lang.Integer",
      "content": "Payment identifier",
      "type": "Integer",
      "mode": "readable",
      "values": [
        ""
      ]
    },
    {
      "name": "date_created",
      "className": "java.lang.String",
      "content": "Payment’s creation date",
      "type": "Date(ISO_8601)",
      "mode": "readable",
      "values": [
        ""
      ]
    },
    {
      "name": "date_approved",
      "className": "java.lang.String",
      "content": "Payment’s approval date",
      "type": "Date(ISO_8601)",
      "mode": "readable",
      "values": [
        ""
      ]
    },
    {
      "name": "date_last_updated",
      "className": "java.lang.String",
      "content": "Last modified date",
      "type": "Date(ISO_8601)",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "money_release_date",
      "className": "java.lang.String",
      "content": "Release date of payment",
      "type": "readable",
      "mode": "readable",
      "values": [
        ""
      ]
    },
    {
      "name": "collector_id",
      "className": "java.lang.String",
      "content": "Identifies the seller",
      "type": "Integer",
      "mode": "readable",
      "values": [
        ""
      ]
    },
    {
      "name": "operation_type",
      "className": "java.lang.String",
      "content": "Payment type",
      "type": "String",
      "mode": "readable",
      "values": [
        "regular_payment: Typification by default of a purchase being paid using MercadoPago",
        "money_transfer: Funds transfer between two users",
        "recurring_payment: Automatic recurring payment due to an active user subscription",
        "account_fund: Money income in the user\u0027s account",
        "payment_addition: Addition of money to an existing payment, done in MercadoPago\u0027s site",
        "cellphone_recharge: Recharge of a user\u0027s cellphone account",
        "pos_payment: Payment done through a Point Of Sale"
      ]
    },
    {
      "name": "payer",
      "className": "mercadopago.peeta.model.payment.Payer",
      "content": "Identifies the buyer",
      "type": "Object",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "binary_mode",
      "className": "java.lang.Boolean",
      "content": "When set to true, the payment can only be approved or rejected. Otherwise in_process status is added",
      "type": "Boolean",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "live_mode",
      "className": "java.lang.Boolean",
      "content": "Whether the payment will be processed in sandbox or in production mode",
      "type": "Boolean",
      "mode": "readable",
      "values": [
        ""
      ]
    },
    {
      "name": "order",
      "className": "mercadopago.peeta.model.payment.Order",
      "content": "Order identifier",
      "type": "Object",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "external_reference",
      "className": "java.lang.String",
      "content": "ID given by the merchant in their system",
      "type": "String",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "description",
      "className": "java.lang.String",
      "content": "Payment reason or item title",
      "type": "String",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "metadata",
      "className": "java.lang.String",
      "content": "Valid JSON that can be attached to the payment to record additional attributes of the merchant",
      "type": "Object",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "currency_id",
      "className": "java.lang.String",
      "content": "ID of the currency used in the payment",
      "type": "String(3)",
      "mode": "readable",
      "values": [
        "ARS: Argentine peso",
        "BRL: Brazilian real",
        "VEF: Venezuelan strong bolivar",
        "CLP: Chilean peso",
        "MXN: Mexican peso",
        "COP: Colombian peso",
        "PEN: Peruvian sol",
        "UYU: Uruguayan peso"
      ]
    },
    {
      "name": "transaction_amount",
      "className": "java.lang.Double",
      "content": "Product cost",
      "type": "Float",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "transaction_amount_refunded",
      "className": "java.lang.Double",
      "content": "Total refunded amount in this payment",
      "type": "Float",
      "mode": "readable",
      "values": [
        ""
      ]
    },
    {
      "name": "coupon_amount",
      "className": "java.lang.Double",
      "content": "Amount of the coupon discount",
      "type": "Float",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "campaign_id",
      "className": "java.lang.Integer",
      "content": "Discount campaign ID",
      "type": "Integer",
      "mode": "writable",
      "values": [
        ""
      ]
    },
    {
      "name": "coupon_code",
      "className": "java.lang.String",
      "content": "Discount campaign with a specific code",
      "type": "String",
      "mode": "writable",
      "values": [
        ""
      ]
    },
    {
      "name": "transaction_details",
      "className": "mercadopago.peeta.model.payment.TransactionDetail",
      "content": "Groups the details of the transaction",
      "type": "Object",
      "mode": "readable",
      "values": [
        ""
      ]
    },
    {
      "name": "fee_details",
      "className": "java.util.List",
      "content": "List of fees",
      "type": "Array(Object)",
      "mode": "readable",
      "values": [
        ""
      ]
    },
    {
      "name": "differential_pricing_id",
      "className": "java.lang.Integer",
      "content": "Id of the scheme for the absorption of financing fee",
      "type": "Integer",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "application_fee",
      "className": "java.lang.Double",
      "content": "Fee collected by a marketplace or MercadoPago Application",
      "type": "Float",
      "mode": "writable",
      "values": [
        ""
      ]
    },
    {
      "name": "status",
      "className": "java.lang.String",
      "content": "Payment status",
      "type": "String",
      "mode": "readable",
      "values": [
        "pending: The user has not yet completed the payment process",
        "approved: The payment has been approved and accredited",
        "authorized: The payment has been authorized but not captured yet",
        "in_process: Payment is being reviewed",
        "in_mediation: Users have initiated a dispute",
        "rejected: Payment was rejected. The user may retry payment",
        "cancelled: Payment was cancelled by one of the parties or because time for payment has expired",
        "refunded: Payment was refunded to the user",
        "charged_back: Was made a chargeback in the buyer’s credit card"
      ]
    },
    {
      "name": "status_detail",
      "className": "java.lang.String",
      "content": "Gives more detailed information on the current state or rejection cause",
      "type": "String",
      "mode": "readable",
      "values": [
        ""
      ]
    },
    {
      "name": "capture",
      "className": "java.lang.Boolean",
      "content": "Determines if the payment should be captured (true, default value) or just reserved (false)",
      "type": "Boolean",
      "mode": "writable",
      "values": [
        ""
      ]
    },
    {
      "name": "captured",
      "className": "java.lang.Boolean",
      "content": "Determines if the capture operation was performed (just for credit cards)",
      "type": "Boolean",
      "mode": "readable",
      "values": [
        ""
      ]
    },
    {
      "name": "call_for_authorize_id",
      "className": "java.lang.String",
      "content": "Identifier that must be provided to the issuing bank to authorize the payment",
      "type": "String",
      "mode": "readable",
      "values": [
        ""
      ]
    },
    {
      "name": "payment_method_id",
      "className": "java.lang.String",
      "content": "Payment method chosen to do the payment",
      "type": "String",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "issuer_id",
      "className": "java.lang.String",
      "content": "Payment method issuer",
      "type": "String",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "payment_type_id",
      "className": "java.lang.String",
      "content": "Type of payment method chosen",
      "type": "String",
      "mode": "readable",
      "values": [
        "account_money: Money in the MercadoPago account",
        "content: Printed ticket",
        "bank_transfer: Wire transfer",
        "atm: Payment by ATM",
        "credit_card: Payment by credit card",
        "debit_card: Payment by debit card",
        "prepaid_card: Payment by prepaid card"
      ]
    },
    {
      "name": "token",
      "className": "java.lang.String",
      "content": "Card token ID",
      "type": "String",
      "mode": "writable",
      "values": [
        ""
      ]
    },
    {
      "name": "card",
      "className": "mercadopago.peeta.model.payment.Card",
      "content": "Details of the card used",
      "type": "Object",
      "mode": "redable",
      "values": [
        ""
      ]
    },
    {
      "name": "statement_descriptor",
      "className": "java.lang.String",
      "content": "How will look the payment in the card bill (e.g.: MERCADOPAGO)",
      "type": "String",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "installments",
      "className": "java.lang.Integer",
      "content": "Selected quantity of installments",
      "type": "Integer",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "notification_url",
      "className": "java.lang.String",
      "content": "URL where mercadopago will send notifications associated to changes in this payment",
      "type": "String",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "callback_url",
      "className": "java.lang.String",
      "content": "URL where mercadopago does the final redirect (only for bank transfers)",
      "type": "String",
      "mode": "readable | writable",
      "values": [
        ""
      ]
    },
    {
      "name": "refunds",
      "className": "java.util.List",
      "content": "List of refunds that were made to this payment",
      "type": "Array(Object)",
      "mode": "readable",
      "values": [
        ""
      ]
    }
  ],
  "errors": [
    {
      "status": 404,
      "type": "NOT FOUND",
      "values": [
        "2000: Payment not found"
      ]
    },
    {
      "status": 403,
      "type": "FORBIDDEN",
      "values": [
        "4: The caller is not authorized to access this resource",
        "2041: Only admin users can perform the requested action",
        "3002: The caller is not authorized to perform this action"
      ]
    },
    {
      "status": 400,
      "type": "BAD REQUEST",
      "values": [
        "1: Params Error",
        "3: Token must be for test",
        ": Must provide your access_token to proceed",
        "1000: Number of rows exceeded the limits",
        "1001: Date format must be yyyy-MM-dd\u0027T\u0027HH:mm:ss.SSSZ",
        "2001: Already posted the same request in the last minute",
        "2004: POST to Gateway Transactions API fail",
        "2002: Customer not found",
        "2006: Card Token not found",
        "2007: Connection to Card Token API fail",
        "2009: Card token isssuer can\u0027t be null",
        "2010: Card not found",
        "2013: Invalid profileId",
        "2014: Invalid reference_id",
        "2015: Invalid scope",
        "2016: Invalid status for update",
        "2017: Invalid transaction_amount for update",
        "2018: The action requested is not valid for the current payment state",
        "2020: Customer not allowed to operate",
        "2021: Collector not allowed to operate",
        "2022: You have exceeded the max number of refunds for this payment",
        "2024: Payment too old to be refunded",
        "2025: Operation type not allowed to be refunded",
        "2027: The action requested is not valid for the current payment method type",
        "2029: Payment without movements",
        "2030: Collector hasn\u0027t enough money",
        "2031: Collector hasn\u0027t enough available money",
        "2034: Invalid users involved",
        "2035: Invalid params for preference Api",
        "2036: Invalid context",
        "2038: Invalid campaign_id",
        "2039: Invalid coupon_code",
        "2040: User email doesn\u0027t exist",
        "2060: The customer can\u0027t be equal to the collector",
        "3000: You must provide your cardholder_name with your card data",
        "3001: You must provide your cardissuer_id with your card data",
        "3003: Invalid card_token_id",
        "3004: Invalid parameter site_id",
        "3005: Not valid action, the resource is in a state that does not allow this operation. For more information see the state that has the resource.",
        "3006: Invalid parameter cardtoken_id",
        "3007: The parameter client_id can not be null or empty",
        "3008: Not found Cardtoken",
        "3009: unauthorized client_id",
        "3010: Not found card on whitelist",
        "3011: Not found payment_method",
        "3012: Invalid parameter security_code_length",
        "3013: The parameter security_code is a required field can not be null or empty",
        "3014: Invalid parameter payment_method",
        "3015: Invalid parameter card_number_length",
        "3016: Invalid parameter card_number",
        "3017: The parameter card_number_id can not be null or empty",
        "3018: The parameter expiration_month can not be null or empty",
        "3019: The parameter expiration_year can not be null or empty",
        "3020: The parameter cardholder.name can not be null or empty",
        "3021: The parameter cardholder.document.number can not be null or empty",
        "3022: The parameter cardholder.document.type can not be null or empty",
        "3023: The parameter cardholder.document.subtype can not be null or empty",
        "3024: Not valid action - partial refund unsupported for this transaction",
        "3025: Invalid Auth Code",
        "3026: Invalid card_id for this payment_method_id",
        "3027: Invalid payment_type_id",
        "3028: Invalid payment_method_id",
        "3029: Invalid card expiration month",
        "3030: Invalid card expiration year",
        "4000: card atributte can\u0027t be null",
        "4001: payment_method_id atributte can\u0027t be null",
        "4002: transaction_amount atributte can\u0027t be null",
        "4003: transaction_amount atributte must be numeric",
        "4004: installments atributte can\u0027t be null",
        "4005: installments atributte must be numeric",
        "4006: payer atributte is malformed",
        "4007: site_id atributte can\u0027t be null",
        "4012: payer.id atributte can\u0027t be null",
        "4013: payer.type atributte can\u0027t be null",
        "4015: payment_method_reference_id atributte can\u0027t be null",
        "4016: payment_method_reference_id atributte must be numeric",
        "4017: status atributte can\u0027t be null",
        "4018: payment_id atributte can\u0027t be null",
        "4019: payment_id atributte must be numeric",
        "4020: notificaction_url atributte must be url valid",
        "4021: notificaction_url atributte must be shorter than 500 character",
        "4022: metadata atributte must be a valid JSON",
        "4023: transaction_amount atributte can\u0027t be null",
        "4024: transaction_amount atributte must be numeric",
        "4025: refund_id can\u0027t be null",
        "4026: Invalid coupon_amount",
        "4027: campaign_id atributte must be numeric",
        "4028: coupon_amount atributte must be numeric",
        "4029: Invalid payer type",
        "4037: Invalid transaction_amount",
        "4038: application_fee cannot be bigger than transaction_amount",
        "4039: application_fee cannot be a negative value",
        "4050: payer.email must be a valid email",
        "4051: payer.email must be shorter than 254 characters"
      ]
    }
  ]
}
```
