package th.mfu.dto;

import java.util.List;

// import javax.persistence.ManyToMany;
// import javax.persistence.ManyToOne;

// import com.fasterxml.jackson.annotation.JsonBackReference;

import th.mfu.domain.Customer;
import th.mfu.domain.Product;

public class SaleOrderDTO {
    
    private Long id;
    private String notes;

    private CustomerDTO customer;

    private List<ProductDTO> products;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public CustomerDTO getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
    public List<ProductDTO> getProducts() {
        return products;
    }
    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

}
