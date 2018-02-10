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
            <groupId>com.mercadopago</groupId>
            <artifactId>sdk</artifactId>
            <version>0.3.4</version>
        </dependency>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.2</version>
        </dependency>
        <dependency>
            <groupId>org.codehaus.jettison</groupId>
            <artifactId>jettison</artifactId>
            <version>1.3.7</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.18</version>
            <type>jar</type>
        </dependency>
    </dependencies>
```
For mercadopago SDK artifact you need specify the repository like this
```xml
      <repositories>
        <repository>
            <id>mercadopago</id>
            <url>https://github.com/mercadopago/sdk-java/raw/master/releases</url>
        </repository>
      </repositories>
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
