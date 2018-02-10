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
# A simple user stoyry:
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
