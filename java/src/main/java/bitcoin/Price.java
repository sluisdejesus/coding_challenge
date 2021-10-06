package bitcoin;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(LocalDateTime effectiveDate, BigDecimal price) {

    public Price(LocalDateTime effectiveDate, BigDecimal price){
        this.effectiveDate = effectiveDate;
        this.price = price;
    }

    public LocalDateTime getEffectiveDate(){
        return effectiveDate;
    }

    public BigDecimal getPrice(){
        return price;
    }
}