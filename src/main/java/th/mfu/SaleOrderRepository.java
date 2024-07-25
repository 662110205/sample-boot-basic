package th.mfu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SaleOrderRepository extends CrudRepository<SaleOrder, Long> {
    List<SaleOrder> findAll();

}
