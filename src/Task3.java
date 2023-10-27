public class Task3 {

    // Інтерфейс для пар чисел
    interface Pair {
        Pair add(Pair other);
        Pair subtract(Pair other);
        Pair multiply(int factor);
        Pair divide(int divisor);
    }

    // Клас Money реалізує інтерфейс Pair
    record Money(int amount) implements Pair {
        @Override
        public Pair add(Pair other) {
            if (other instanceof Money) {
                Money otherMoney = (Money) other;
                return new Money(this.amount + otherMoney.amount);
            } else {
                throw new IllegalArgumentException("Invalid operation: cannot add Money and non-Money.");
            }
        }

        @Override
        public Pair subtract(Pair other) {
            if (other instanceof Money) {
                Money otherMoney = (Money) other;
                return new Money(this.amount - otherMoney.amount);
            } else {
                throw new IllegalArgumentException("Invalid operation: cannot subtract Money and non-Money.");
            }
        }

        @Override
        public Pair multiply(int factor) {
            return new Money(this.amount * factor);
        }

        @Override
        public Pair divide(int divisor) {
            if (divisor != 0) {
                return new Money(this.amount / divisor);
            } else {
                throw new ArithmeticException("Division by zero.");
            }
        }
    }

    // Клас Fraction реалізує інтерфейс Pair
    record Fraction(int numerator, int denominator) implements Pair {
        public Fraction {
            if (denominator == 0) {
                throw new IllegalArgumentException("Denominator cannot be zero.");
            }
        }

        @Override
        public Pair add(Pair other) {
            if (other instanceof Fraction) {
                Fraction otherFraction = (Fraction) other;
                int commonDenominator = this.denominator * otherFraction.denominator();
                int sumNumerator = this.numerator * otherFraction.denominator() +
                        otherFraction.numerator() * this.denominator;
                return simplifyFraction(sumNumerator, commonDenominator);
            } else {
                throw new IllegalArgumentException("Invalid operation: cannot add Fraction and non-Fraction.");
            }
        }

        @Override
        public Pair subtract(Pair other) {
            if (other instanceof Fraction) {
                Fraction otherFraction = (Fraction) other;
                int commonDenominator = this.denominator * otherFraction.denominator();
                int diffNumerator = this.numerator * otherFraction.denominator() -
                        otherFraction.numerator() * this.denominator;
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
        public Pair multiply(int factor) {
            return new Fraction(this.numerator * factor, this.denominator);
        }

        @Override
        public Pair divide(int divisor) {
            if (divisor != 0) {
                return new Fraction(this.numerator, this.denominator * divisor);
            } else {
                throw new ArithmeticException("Division by zero.");
            }
        }
    }

    public static void task3() {
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
