package th.mfu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import th.mfu.domain.Customer;
import th.mfu.domain.Product;
import th.mfu.dto.CustomerDTO;
import th.mfu.dto.ProductDTO;
import th.mfu.dto.mapper.ProductMapper;
import th.mfu.repository.ProductRepository;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository proRepo;

    @Autowired
    ProductMapper productMapper;

    //GET for a customeer

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id){
        if(!proRepo.existsById(id)){
            return new ResponseEntity<ProductDTO>(HttpStatus.NOT_FOUND);
        }  
        Optional<Product> product = proRepo.findById(id);
        ProductDTO dto = new ProductDTO();
        productMapper.updateProductFromEntity(product.get(), dto);
        return new ResponseEntity<ProductDTO>(dto, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<Collection> getAllProducts(){
        List<Product> products = proRepo.findAll();
        List<ProductDTO> dtos = new ArrayList<ProductDTO>();
        for(Product prod: products){
            ProductDTO dto = new ProductDTO();
            productMapper.updateProductFromEntity(prod, dto);
            dtos.add(dto);
        }
        return new ResponseEntity<Collection>(dtos, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody ProductDTO product){
        Product newPro = new Product();
        productMapper.updateProductFromDto(product, newPro);
        proRepo.save(newPro);
        return new ResponseEntity<String>("Product created", HttpStatus.CREATED);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        if (!proRepo.existsById(id)) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        Optional<Product> productEnt = proRepo.findById(id);
        Product product = productEnt.get();
        productMapper.updateProductFromDto(productDTO, product);;
        proRepo.save(product);
        return new ResponseEntity<String>("Product updates", HttpStatus.OK);
    }

    @DeleteMapping("products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        proRepo.deleteById(id);
        return new ResponseEntity<String>("Product deleted", HttpStatus.NO_CONTENT);
    }
}
