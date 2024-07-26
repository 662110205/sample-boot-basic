package th.mfu.dto.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import th.mfu.domain.Product;
import th.mfu.dto.ProductDTO;

@Mapper(componentModel="spring")
public interface ProductMapper {

    // map from DTO to Entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateProductFromDto(ProductDTO dto, @MappingTarget Product entity);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateProductFromEntity(Product entity,@MappingTarget ProductDTO dto);

    // Map from List of Entity to List of DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateProductFromEntity(List<Product> entities, @MappingTarget List<ProductDTO> dtos);
    

}
