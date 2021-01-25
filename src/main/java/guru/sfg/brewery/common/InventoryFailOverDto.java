package guru.sfg.brewery.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryFailOverDto {
    private UUID Id;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;
    //private UUID beerId = UUID.randomUUID();
    @Builder.Default
    private Integer quantityOnHand = 999;
}
