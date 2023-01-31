package spring.example.spring.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("CUSTOMER")
@ToString
public class Customer extends Member{
}
