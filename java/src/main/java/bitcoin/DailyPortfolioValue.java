package bitcoin;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DailyPortfolioValue(LocalDate date, BigDecimal value) {

    public DailyPortfolioValue(LocalDate date, BigDecimal value){
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate(){
        return date;
    }

    public BigDecimal getValue(){
        return value;
    }
}