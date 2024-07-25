package th.mfu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;

@RestController
public class CustomerController { 

    @Autowired
    private CustomerRepository custRepo;
    // public static Map<Integer, Customer> customerDB = new HashMap<Integer, Customer>();
    // private int nextId = 0;

    //GET for a customeer

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        if(!custRepo.existsById(id))
            return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
        Optional<Customer> customer = custRepo.findById(id);
        return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
    }

    @GetMapping("/customers")
    public ResponseEntity<Collection> getAllCustomers(){
        return new ResponseEntity<Collection>(custRepo.findAll(), HttpStatus.OK);
    }

    //POST for creating a customer 
    @PostMapping("/customers")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
        if(!custRepo.findByName(customer.getName()).isEmpty()){
            return new ResponseEntity<String>("Customer already exists", HttpStatus.CONFLICT);
        }
        custRepo.save(customer);
        return new ResponseEntity<String>("Customer created", HttpStatus.CREATED);
    }

    @DeleteMapping("customers/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        custRepo.deleteById(id);
        return new ResponseEntity<String>("Customer deleted", HttpStatus.NO_CONTENT);
    }
}