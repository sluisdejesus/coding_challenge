package bitcoin;

import java.math.BigDecimal;
import java.security.Security;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PortfolioPerformance {
    private Transaction transaction;
    private Price price;

    private static final List<Price> PRICES = List.of(
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 1, 5, 0, 0), new BigDecimal("35464.53")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 2, 5, 0, 0), new BigDecimal("35658.76")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 3, 5, 0, 0), new BigDecimal("36080.06")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 3, 13, 0, 0), new BigDecimal("37111.11")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 6, 5, 0, 0), new BigDecimal("38041.47")),
            new Price(LocalDateTime.of(2021, Month.SEPTEMBER, 7, 5, 0, 0), new BigDecimal("34029.61")));

    private static final List<Transaction> TRANSACTIONS = List.of(
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 1, 9, 0, 0), new BigDecimal("0.012")),
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 1, 15, 0, 0), new BigDecimal("-0.007")),
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 4, 9, 0, 0), new BigDecimal("0.017")),
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 5, 9, 0, 0), new BigDecimal("-0.01")),
            new Transaction(LocalDateTime.of(2021, Month.SEPTEMBER, 7, 9, 0, 0), new BigDecimal("0.1")));

    // Complete this method to return a list of daily portfolio values with one record for each day from the 01-09-2021-07-09-2021 in ascending date order
    public static List<DailyPortfolioValue> getDailyPortfolioValues() {

            //Making a list to hold daily portfolio values to display to user
            List<DailyPortfolioValue> dailyPortfolioValues = new ArrayList<>();

            // List of dates that need to be calculated (01-09-2021 until but not including 08-09-2021
            LocalDate startDate = LocalDate.of(2021, Month.SEPTEMBER, 1);
            LocalDate endDate = LocalDate.of(2021, Month.SEPTEMBER, 8);

            // .datesUntil stays within startDate and endDate
            // .collect accumulates all date data into a collection
            List<LocalDate> dates = startDate.datesUntil(endDate).collect(Collectors.toList());



            // User starts dates with 0 Bitcoin (this will pass test but will need to be amended as user may start
            // date period with an existing number of Bitcoin in their wallet)
            BigDecimal currentPrice = null, bitcoinWallet = new BigDecimal("0");

            // Index of transactions and prices to keep track while loooping
            int purchasePriceIndex = 0, transactionsIndex = 0;

            //For each date in range
            for (LocalDate date : dates) {

                // Price change - purchasePrice index is smaller than the size of PRICES list, increment index by one
                for (int index = purchasePriceIndex; index < PRICES.size(); index++) {

                    //If a price change happened on a given day
                    // get purchasePrice index, .effectiveDate() checks date, .toLocalDate converts from
                    // LocalDateTime, .equals(date) compares the dates
                    if (PRICES.get(purchasePriceIndex).effectiveDate().toLocalDate().equals(date)) {

                        // Change price to the new one found
                        currentPrice = PRICES.get(purchasePriceIndex).price();
                        purchasePriceIndex++;
                    }
                }

                // New transaction
                // sets index to be the index of the transaction
                // then we check if the index of the transaction is smaller than the TRANSACTIONS list
                // if it is, we increment index position by 1
                for (int index = transactionsIndex; index < TRANSACTIONS.size(); index++) {

                    //If a transaction happened on a given day
                    // get purchasePrice index, .effectiveDate() checks date, .toLocalDate converts from
                    // LocalDateTime, .equals(date) compares the dates
                    if (TRANSACTIONS.get(transactionsIndex).effectiveDate().toLocalDate().equals(date)) {

                        //Update bitcoinWallet by adding new value after adding transaction value
                        // .add numberOfBitcoins from the transaction at that index position
                        // then increments the index position by 1
                        bitcoinWallet = bitcoinWallet.add(TRANSACTIONS.get(transactionsIndex).numberOfBitcoins());
                        transactionsIndex++;
                    }
                }

                // adds new record to List dailyPortfolioValue using the date, bitcoinWallet * currentPrice
                dailyPortfolioValues.add(new DailyPortfolioValue(date, bitcoinWallet.multiply(currentPrice)));

            }
            return dailyPortfolioValues;
        }
    }
