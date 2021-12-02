/*
 
 Team: Silly Serpents 
 Roster: Jason Zhou, Vansh Saboo, Tiggy, Tiffany
 APCS Pd. 8
 HW42: Be More Rational
 2021-12-1
 Time taken: 0.5 hrs

 Disco: 
 Some elegant stuff was deliberated ...
 
 - Given that the numerator and denominator could potentially be negative, set the temporary values for them in the gcd() method as positive
 so that the Euclidean method works with them (otherwise it would subtract / reduce forever)
 
 - While reducing, if both the numerator and the denominator are negative, make them both positive. 
 However, if the numerator is positive and the denominator is negative, it's still cleanest to negate both of them,
 so negate both of them if the denominator is negative
 
 - To compare two Rationals as   a/b ___ c/d, you cross multiply to get ad ___ bc 
 There's 3 cases: ad < bc, ad = bc, and ad > bc
 You could test for all three cases, but instead, you can subtract bc from both sides and get ad - bc ___ 0
 If ad > bc, then ad - bc > 0 (+), if ad = bc, then ad - bc = 0, and if ad < bc, then ad - bc < 0 (-)
 Because this complies with the instruction, you can simply return ad - bc
 
 QCC: 
 */

public class Rational {

    private int num;
    private int denom;

    public Rational() {
        this.num = 0;
        this.denom = 1;
    }

    public Rational(int num, int denom) {
        this();
        if (denom != 0) {
          this.num = num;
          this.denom = denom;
        } else {
            System.out.println("Invalid Denominator, should not be 0");

        }

    }

    public String toString() {
        return num + " / " + denom;
    }

    public double floatValue() {
        double value = (double) num / denom;
        return value;
    }

    public void multiply(Rational r) {
        this.num *= r.num;
        this.denom *= r.denom;
    }

    public void divide(Rational r) {
        if (r.num == 0) {
            System.out.println("Divide by zero error!");
        } else {
            this.num *= r.denom;
            this.denom *= r.num;
        }

    }

    public void add(Rational r) {
        // a/b + c/d = ad/bd + bc/db = (ad + bc)/bd
        this.num = this.num * r.denom + this.denom * r.num;
        this.denom *= r.denom;
        this.reduce();
    }

    public void subtract(Rational r) {
        // a/b - c/d = ad/bd - bc/db = (ad - bc)/bd
        this.num = this.num * r.denom - this.denom * r.num;
        this.denom *= r.denom;
        this.reduce();
    }
    public int gcd() {
       if(this.num == 0){return 0;}
       
       //  If the numerator or denominator is negative, you want to set them positive during the process of finding the GCD
       int numerator = Math.abs(this.num);
       int denominator = Math.abs(this.denom);
       
       while(numerator != denominator){
           if(numerator > denominator){numerator -= denominator;}
           else{denominator -= numerator;}
       }
       return numerator;
    }

    public void reduce(){
       if(gcd() != 0){
           this.num /= gcd();
           this.denom /= gcd();
           
           // If denominator is negative, negate both the numerator and the denominator
           if(this.denom < 0){
               this.num *= -1;
               this.denom *= -1;
           }
       }
    }
    public int compareTo(Rational r){
       int leftProduct = this.num * r.denom;
       int rightProduct = this.denom * r.num;
       return leftProduct - rightProduct;
    }


    public static void main(String[] args) {
        Rational r = new Rational(2, 3);
        Rational z = new Rational(6, 8);
        r.subtract(z);
        System.out.println(r.toString());

        //courtesy of Gabriel Thompson
        /*
        System.out.println("\ntoString() and constructor test cases:");
        Rational a = new Rational();
        System.out.println(a + " ...should be \"0 / 1\"");
        Rational b = new Rational(5, 2);
        System.out.println(b + " ...should be \"5 / 2\"");
        Rational c = new Rational(1, 0);
        System.out.println(c + " ...should yield error");

        System.out.println("\nfloatValue() test cases:");
        Rational d = new Rational(6, 3);
        System.out.println(d.floatValue() + " ...should be \"2.0\"");
        Rational e = new Rational(5, 3);
        System.out.println(e.floatValue() + " ...should be \"1.66666667\"");
        Rational f = new Rational(-18, 9);
        System.out.println(f.floatValue() + " ...should be \"-2.0\"");

        System.out.println("\nmultiply() test cases:");
        Rational g = new Rational(5, 4);
        Rational h = new Rational(6, 10);
        g.multiply(h);
        System.out.println(g + " ...should be \"30 / 40\"");
        Rational i = new Rational(5, 10);
        Rational j = new Rational();
        i.multiply(j);
        System.out.println(i + " ...should be \"0 / 10\"");

        System.out.println("\ndivide() test cases:");
        Rational k = new Rational(10, 3);
        Rational l = new Rational(6, 4);
        k.divide(l);
        System.out.println(k + " ...should be \"40 / 18\"");
        // This next test cases checks for how the class reacts to divison by 0 in the divide() function
        Rational m = new Rational(5, 5);
        Rational n = new Rational();
        m.divide(n);
        System.out.println(m + " ...should yield an error\n");
        */
    }

}
