public class Task2 {

    // Абстрактний базовий клас Pair
    static abstract class Pair {
        abstract Pair add(Pair other);

        abstract Pair subtract(Pair other);

        abstract Pair multiply(int factor);

        abstract Pair divide(int divisor);

        @Override
        public abstract String toString();

        @Override
        public abstract boolean equals(Object obj);
    }

    // Похідний клас Money
    static class Money extends Pair {
        private int amount;

        public Money(int amount) {
            this.amount = amount;
        }

        @Override
        Pair add(Pair other) {
            if (other instanceof Money) {
                Money otherMoney = (Money) other;
                return new Money(this.amount + otherMoney.amount);
            } else {
                throw new IllegalArgumentException("Invalid operation: cannot add Money and non-Money.");
            }
        }

        @Override
        Pair subtract(Pair other) {
            if (other instanceof Money) {
                Money otherMoney = (Money) other;
                return new Money(this.amount - otherMoney.amount);
            } else {
                throw new IllegalArgumentException("Invalid operation: cannot subtract Money and non-Money.");
            }
        }

        @Override
        Pair multiply(int factor) {
            return new Money(this.amount * factor);
        }

        @Override
        Pair divide(int divisor) {
            if (divisor != 0) {
                return new Money(this.amount / divisor);
            } else {
                throw new ArithmeticException("Division by zero.");
            }
        }

        @Override
        public String toString() {
            return "Money: " + amount;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Money money = (Money) obj;
            return amount == money.amount;
        }
    }

    // Похідний клас Fraction
    static class Fraction extends Pair {
        private int numerator;
        private int denominator;

        public Fraction(int numerator, int denominator) {
            if (denominator != 0) {
                this.numerator = numerator;
                this.denominator = denominator;
            } else {
                throw new IllegalArgumentException("Denominator cannot be zero.");
            }
        }

        @Override
        Pair add(Pair other) {
            if (other instanceof Fraction) {
                Fraction otherFraction = (Fraction) other;
                int commonDenominator = this.denominator * otherFraction.denominator;
                int sumNumerator = this.numerator * otherFraction.denominator +
                        otherFraction.numerator * this.denominator;
                return simplifyFraction(sumNumerator, commonDenominator);
            } else {
                throw new IllegalArgumentException("Invalid operation: cannot add Fraction and non-Fraction.");
            }
        }
        
        @Override
        Pair subtract(Pair other) {
            if (other instanceof Fraction) {
                Fraction otherFraction = (Fraction) other;
                int commonDenominator = this.denominator * otherFraction.denominator;
                int diffNumerator = this.numerator * otherFraction.denominator -
                        otherFraction.numerator * this.denominator;
                return simplifyFraction(diffNumerator, commonDenominator);
            } else {
                throw new IllegalArgumentException("Invalid operation: cannot subtract Fraction and non-Fraction.");
            }
        }
        
        private Fraction simplifyFraction(int numerator, int denominator) {
            int gcd = gcd(numerator, denominator);
            numerator /= gcd;
            denominator /= gcd;
            return new Fraction(numerator, denominator);
        }
        
        private int gcd(int a, int b) {
            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }
            return a;
        }
        

        @Override
        Pair multiply(int factor) {
            return new Fraction(this.numerator * factor, this.denominator);
        }

        @Override
        Pair divide(int divisor) {
            if (divisor != 0) {
                return new Fraction(this.numerator, this.denominator * divisor);
            } else {
                throw new ArithmeticException("Division by zero.");
            }
        }

        @Override
        public String toString() {
            return "Fraction: " + numerator + "/" + denominator;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Fraction fraction = (Fraction) obj;
            return numerator == fraction.numerator && denominator == fraction.denominator;
        }
    }

    
    public static void task2() {
        Pair[] pairs = new Pair[5];
        pairs[0] = new Money(50);
        pairs[1] = new Money(30);
        pairs[2] = new Fraction(2, 3);
        pairs[3] = new Fraction(1, 4);
        pairs[4] = new Fraction(1, 2);
    
        System.out.println("Pairs before operations:");
        for (Pair pair : pairs) {
            System.out.println(pair.toString());
        }
    
        // Виконання арифметичних операцій та виведення результатів
        for (Pair pair : pairs) {
            System.out.println("Object: " + pair.toString());
            if (pair instanceof Money) {
                System.out.println("Add 10: " + ((Money) pair).add(new Money(10)).toString());
                System.out.println("Subtract 5: " + ((Money) pair).subtract(new Money(5)).toString());
                System.out.println("Multiply by 2: " + ((Money) pair).multiply(2).toString());
                try {
                    System.out.println("Divide by 3: " + ((Money) pair).divide(3).toString());
                } catch (ArithmeticException e) {
                    System.out.println("Division by zero.");
                }
            } else if (pair instanceof Fraction) {
                System.out.println("Add 1/3: " + ((Fraction) pair).add(new Fraction(1, 3)).toString());
                System.out.println("Subtract 1/6: " + ((Fraction) pair).subtract(new Fraction(1, 6)).toString());
                System.out.println("Multiply by 2: " + ((Fraction) pair).multiply(2).toString());
                try {
                    System.out.println("Divide by 4: " + ((Fraction) pair).divide(4).toString());
                } catch (ArithmeticException e) {
                    System.out.println("Division by zero.");
                }
            }
            System.out.println("-------------------------");
        }
    }
}
