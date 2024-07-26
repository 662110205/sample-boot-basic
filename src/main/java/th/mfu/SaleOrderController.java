package th.mfu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.Customer;
import th.mfu.domain.SaleOrder;
import th.mfu.dto.SaleOrderDTO;
import th.mfu.dto.mapper.SaleOrderMapper;
import th.mfu.repository.CustomerRepository;
import th.mfu.repository.ProductRepository;
import th.mfu.repository.SaleOrderRepository;

@RestController
public class SaleOrderController {

    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SaleOrderRepository saleOrderRepository;
    @Autowired
    SaleOrderMapper saleOrderMapper;

    //create order by customer
    @PostMapping("/customers/{customerId}/orders")
    public ResponseEntity<String> createOrder(@PathVariable Long customerId, @RequestBody SaleOrderDTO orderDTO){
        //check existance
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            SaleOrder newOrder = new SaleOrder();
            saleOrderMapper.updateSaleOrderFromDto(orderDTO, newOrder);
            newOrder.setCustomer(customer.get());
            saleOrderRepository.save(newOrder);
            return new ResponseEntity<String>("Order created", HttpStatus.CREATED);
        } else{
            return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);
        }
       
    }

    //get all order
    @GetMapping("/orders")
    public ResponseEntity<Collection<SaleOrderDTO>> getAllOrders() {
        Collection<SaleOrder> orders = saleOrderRepository.findAll();
        List<SaleOrderDTO> dtos = new ArrayList<>();
        saleOrderMapper.updateSaleOrderFromEntity(orders, dtos);
        return new ResponseEntity<Collection<SaleOrderDTO>>(dtos, HttpStatus.OK);
    }

    //get for getting by customer
    @GetMapping("/customers/{customerId}/orders")
    public ResponseEntity<Collection<SaleOrderDTO>> getOrderByCustomer(@PathVariable Long customerId){
        Optional<Customer> optCustomer = customerRepository.findById(customerId);
        if (!optCustomer.isPresent()){
            return new ResponseEntity<Collection<SaleOrderDTO>>(HttpStatus.NOT_FOUND);
        }
        Collection<SaleOrder> orders = saleOrderRepository.findByCustomerId(customerId);
        List<SaleOrderDTO> dtos = new ArrayList<>();
        saleOrderMapper.updateSaleOrderFromEntity(orders, dtos);
        return new ResponseEntity<Collection<SaleOrderDTO>>(dtos, HttpStatus.OK);
        
    }
}
