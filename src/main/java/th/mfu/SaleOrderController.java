package th.mfu;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleOrderController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SaleOrderRepository saleOrderRepository;

    //create order by customer
    @PostMapping("/customers/{customerId}/orders")
    public ResponseEntity<String> createOrder(@PathVariable Long customerId, @RequestBody SaleOrder saleOrder){
        //check existance
        if (!customerRepository.existsById(customerId)) {
            return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);
        }
        Optional<Customer> optCustomer = customerRepository.findById(customerId);
        //set order
        saleOrder.setCustomer(optCustomer.get());
        saleOrderRepository.save(saleOrder);
        return new ResponseEntity<String>("Order created", HttpStatus.CREATED);
        
    }

    //get all order
    @GetMapping("/orders")
    public ResponseEntity<Collection<SaleOrder>> getAllOrders() {
        return new ResponseEntity<Collection<SaleOrder>>(saleOrderRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<Collection<SaleOrder>> getOrderByCustomer(@PathVariable Long customerId){
        if (!customerRepository.existsById(customerId)){
            return new ResponseEntity<Collection<SaleOrder>>(HttpStatus.NOT_FOUND);
        }
        Collection<SaleOrder> orders = saleOrderRepository.findByCustomerId(customerId);
        return new ResponseEntity<Collection<SaleOrder>>(orders, HttpStatus.OK);
        
    }
}
