import java.util.*;
import java.io.*;

class Fraction {
	int numerator; // 분자
	int denominator; // 분모
	
	Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
    // 덧셈
	static Fraction sum(Fraction f1, Fraction f2) {
		if (f1.denominator == f2.denominator) {
			return new Fraction(f1.numerator + f2.numerator, f1.denominator);
		} 
		
		int numerator = f1.numerator * f2.denominator + f2.numerator * f1.denominator;
		int denominator = f1.denominator * f2.denominator;
		
		return new Fraction(numerator, denominator);
	}
	
    // 약분
	void reduce() {
		int gcd = gcd();
		
		numerator /= gcd;
		denominator /= gcd;
	}
	
    // 최대 공약수 계산
	int gcd() {
		int a = this.numerator;
		int b = this.denominator;
		
		while (b > 0) {
			int temp = a;
			a = b;
			b = temp % b;
		}
		
		return a;
	}
}

public class Main {
	static final int INPUT = 2;
	static Fraction[] fractions = new Fraction[INPUT];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 분수 입력
		for (int i = 0; i < INPUT; i++) {
			st = new StringTokenizer(br.readLine());
			fractions[i] = new Fraction(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		// 분수 덧셈
		Fraction result = Fraction.sum(fractions[0], fractions[1]);
		
		// 분수 약분
		result.reduce();
		
		System.out.println(result.numerator + " " + result.denominator);
	}
}