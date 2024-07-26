package th.mfu.dto.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import th.mfu.domain.SaleOrder;
import th.mfu.dto.SaleOrderDTO;

@Mapper(componentModel = "spring", uses = { CustomerMapper.class })
public interface SaleOrderMapper {

    // Map from DTO to Entity
    @Mapping(target = "customer", source = "customer")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateSaleOrderFromDto(SaleOrderDTO dto, @MappingTarget SaleOrder entity);

    // Map from Entity to DTO
    @Mapping(target = "customer", source = "customer")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateSaleOrderFromEntity(Collection<SaleOrder> orders, @MappingTarget List<SaleOrderDTO> dtos);

    // Map from List of Entity to List of DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateSaleOrderFromEntity(List<SaleOrder> entities, @MappingTarget List<SaleOrderDTO> dtos);

}