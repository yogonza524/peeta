/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.GsonBuilder;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import mercadopago.peeta.bank_report.BankReport;
import mercadopago.peeta.enums.DocumentableClass;
import mercadopago.peeta.model.checkout.CheckoutModel;
import mercadopago.peeta.model.order.MerchantOrder;
import mercadopago.peeta.model.users.balance.Balance;
import mercadopago.peeta.util.Action;
import mercadopago.peeta.util.Attribute;
import mercadopago.peeta.util.Topic;
import mercadopago.core.Peeta;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import mercadopago.peeta.util.AttributeDoc;

/**
 *
 * @author usuario
 */
public class ReflectionTest {
    
    public ReflectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     @Ignore
     public void attributesTest() {
         Class clazz = BankReport.class;
         for(Field field : clazz.getDeclaredFields()){
            Class type = field.getType();
            String name = field.getName();
            
            System.out.println("Type: " + type);
            System.out.println("Name: " + name);
            
            AttributeDoc a = field.getAnnotation(AttributeDoc.class);
            System.out.println("Mode: " + a.mode());
            System.out.println("Type: " + a.type());
            System.out.println("Related: " + a.related());
            System.out.println("Content: " + a.content());
            System.out.println("Values: " + Arrays.toString(a.values()));
            System.out.println("--------------------------------------");
          }
     }
     
     @Test
     @Ignore
     public void attributeEntityTest() {
         List<Attribute> list = Peeta.describeAttributes(DocumentableClass.SettlementReportClass);
         
         list.stream().forEach(item -> {
             System.out.println(new GsonBuilder().setPrettyPrinting().create()
                 .toJson(item)
             );
         });
     }
     
     @Test
     @Ignore
     public void errorEntityTest() {
         List<mercadopago.peeta.util.Error> list = Peeta.describeError(DocumentableClass.Payment);
         
         list.stream().forEach(item -> {
             System.out.println(new GsonBuilder().setPrettyPrinting().create()
                 .toJson(item)
             );
         });
     }
     
     @Test
     @Ignore
     public void topicTest() {
         Optional<Topic> topic = Peeta.describeTopic(DocumentableClass.BalanceClass);
         
         if (topic.isPresent()) {
             Topic t = topic.get();
             
             System.out.println(new GsonBuilder().setPrettyPrinting().create()
                     .toJson(t)
             );
         }
     }
     
     @Test
     @Ignore
     public void actionTest() {
         List<Action> actions = Peeta.describeActions(DocumentableClass.CheckoutClass);
         
         actions.stream().forEach(item -> {
             System.out.println(new GsonBuilder().setPrettyPrinting().create()
                .toJson(item)
             );
         });
     }
     
     @Test
     public void describeAllTest() {
        System.out.println(
                Peeta.describe(DocumentableClass.Payment
                ).pretty()
        );
     }
}
