package demo.springapi.customerapi.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import demo.springapi.customerapi.enums.OrderStatus;
import demo.springapi.customerapi.enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatchOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private OrderType type = OrderType.EXPORT;
    private OrderStatus status = OrderStatus.PENDINNG;
    private final LocalDateTime dateTime = LocalDateTime.now();
}
