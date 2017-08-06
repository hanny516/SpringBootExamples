package com.restapi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.model.Order;
import com.repositories.OrdersRepository;

@RestController
//@RequestMapping("/orders")
public class OrdersRestApi {

	@Autowired
	public OrdersRepository repo;
	
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> listAllUsers() {
        
    	List<Order> orders = repo.findAll();

    	if (orders.isEmpty()) {
            
            return new ResponseEntity<List<Order>>(new ArrayList<Order>(), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }
    @RequestMapping(value = "/find_by_owner/{cb}", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> getOrderByOwner(@PathVariable("cb") String cb) {//@PathVariable("cb") String cb
        
        List<Order> orders = repo.findByCreatedBy(cb);
        
        if (orders.isEmpty()) {
            
            return new ResponseEntity<List<Order>>(new ArrayList<Order>(), HttpStatus.OK);
        }
        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
    }
   
    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public ResponseEntity<Order> getOrder(@PathVariable("id") int id) {
        
        Order order = repo.findOne(id);
        
        if (order == null) {
            
            return new ResponseEntity<Order>(new Order(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

//    @RequestMapping(value = "/orders/cb/", method = RequestMethod.GET)
//    public ResponseEntity<List<Order>> getOrderByOwner() {//@PathVariable("cb") String cb
//        
//        List<Order> orders = repo.findByCreatedBy("hanny516@gmail.com");
//        
//        if (orders.isEmpty()) {
//            
//            return new ResponseEntity<List<Order>>(new ArrayList<Order>(), HttpStatus.OK);
//        }
//        return new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
//    }
    
    
    @RequestMapping(value = "/order/", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
    	
    	System.out.println("Order received: " + order.toString());
 
        if (repo.findOne(order.getOrderId()) != null) {
            
            return new ResponseEntity<String>("Unable to create. A User with name " + order.getOrderId() + " already exist.", HttpStatus.CONFLICT);
        }
        
        repo.save(order);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/orders/{id}").buildAndExpand(order.getOrderId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }


}
