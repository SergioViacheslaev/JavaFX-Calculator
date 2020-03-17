package org.home.calculator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Sergei Viacheslaev
 */
public enum CalculationOperations {
    ADD {
        @Override
        public BigDecimal compute(BigDecimal num1, BigDecimal num2) {
            return num1.add(num2);
        }
    },

    SUBTRACT {
        @Override
        public BigDecimal compute(BigDecimal num1, BigDecimal num2) {
            return num1.subtract(num2);
        }
    },

    DIVIDE {
        @Override
        public BigDecimal compute(BigDecimal num1, BigDecimal num2) {
            return num1.divide(num2, 16, RoundingMode.HALF_UP);
        }
    },

    MULTIPLY {
        @Override
        public BigDecimal compute(BigDecimal num1, BigDecimal num2) {
            return num1.multiply(num2).setScale(16, RoundingMode.HALF_UP);
        }
    };

    public abstract BigDecimal compute(BigDecimal num1, BigDecimal num2);

}
