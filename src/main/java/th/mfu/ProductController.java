package th.mfu;

import java.util.Collection;
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

@RestController
public class ProductController {

    @Autowired
    private ProductRepository proRepo;

    //GET for a customeer

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        Optional<Product> product = proRepo.findById(id);
        if(product.isPresent()){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }  
        return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<Collection<Product>> getAllProducts(){
        return new ResponseEntity<Collection<Product>>(proRepo.findAll(), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product){
        proRepo.save(product);
        return new ResponseEntity<String>("Product created", HttpStatus.CREATED);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        proRepo.deleteById(id);
        return new ResponseEntity<String>("Product deleted", HttpStatus.NO_CONTENT);
    }
}
