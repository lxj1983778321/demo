package test.com.example.demo.controller; 

import com.example.demo.controller.UseController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* UseController Tester. 
* 
* @author <Authors name> 
* @since <pre>5ÔÂ 19, 2020</pre> 
* @version 1.0 
*/ 
public class UseControllerTest { 

    UseController useController = new UseController();

@Test
public void testQuery() throws Exception {
    System.out.println(useController.query(1));
} 


} 
