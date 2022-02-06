package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Basket {
    private final List<Item> items;

    public Basket() {
        this.items = new ArrayList<>();
    }

    public void add(final Item item) {
        this.items.add(item);
    }

    List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal total() {
        return new TotalCalculator().calculate();
    }

    private class TotalCalculator {
        private final List<Item> items;

        TotalCalculator() {
            this.items = items();
        }

        private BigDecimal subtotal() {
            return items.stream().map(Item::price)
                    .reduce(BigDecimal::add)
                    .orElse(BigDecimal.ZERO)
                    .setScale(2, RoundingMode.HALF_UP);
        }

        /**
         * TODO: This could be a good place to apply the results of
         *  the discount calculations.
         *  It is not likely to be the best place to do those calculations.
         *  Think about how Basket could interact with something
         *  which provides that functionality.
         */
        private BigDecimal discounts() {
            //Buy 1, get 1 free
            BigDecimal twoBigDecimal = new BigDecimal(2);
            //If we have an even number of items we can just discount 50% = divide subtotal by 2
            if (this.items.size() % 2 == 0) {
                return this.subtotal().divide(twoBigDecimal);
            } else {
                //If we have an odd number then the discounted price will be 50% off of the first n even numbers plus
                //the price of the last item:
                return this.subtotal().divide(twoBigDecimal).add(this.items.get(this.items.size() - 1).price());
            }
        }

        private BigDecimal calculate() {
            return subtotal().subtract(discounts());
        }
    }
}
