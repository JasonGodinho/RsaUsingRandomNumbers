import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class RandomNum {

	public static void main(String[] args) throws IOException {
		RandomNum r = new RandomNum();
		ArrayList<Integer> randomNumbersList = new ArrayList<Integer>();
		System.out.println("Retrieving a list of random numbers from Random.org for calculating p and q");
		randomNumbersList = r.makeRandomNumberCall("https://www.random.org/integers/?num=1000&min=1&max=70&col=1&base=10&format=plain&rnd=new");
		int p = 0, q= 0, n = 1;
		for(int num: randomNumbersList){
			if(r.isNumberPrime(num)){
				if(p == 0){
					p = num;
					System.out.println("p: " + p);
				} else {
					q = num;
					System.out.println("q: " +q);
					break;
				}
			}	
		}
		n = p * q;
		System.out.println("n: " +n);
		
		int lambda = r.determineLCM(p-1,q-1);
		int e = 1;
		
		System.out.println("Retrieving a list of random numbers from Random.org for calculating e");
		randomNumbersList = r.makeRandomNumberCall("https://www.random.org/integers/?num=1000&min=2&max="+ (lambda-1) +"&col=1&base=10&format=plain&rnd=new");
		for(int num: randomNumbersList){
			if(areNumbersRelativelyPrime(num, lambda)){
				System.out.println("e: " + num);
				e = num;
				break;
			}
		}
		
		int d = 3;
		while(((d*e)%n)!=1){
			d++;
		}
		System.out.println("d: "+d);
		
		System.out.println("Public Key:  (" +  n + ", "+ e+")");
		System.out.println("Private Key: (" +  n + ", "+ d+")");		
	}
	
	private boolean isNumberPrime(int n) {
	    if (n%2==0)
	    	return false;
	    
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	
	//Find if numbers are co-prime
	private static boolean areNumbersRelativelyPrime(int a, int b) {
	    return gcd(a,b) == 1;
	}

	//Helper for finding GCD of 2 values
	private static long gcd(long a, long b)
	{
		long t;
	    while(b != 0){
	        t = a;
	        a = b;
	        b = t%b;
	    }
	    return a;
	}
	
	public int determineLCM(int a, int b) {
		int num1, num2;
		if (a > b) {
			num1 = a;
			num2 = b;
		} else {
			num1 = b;
			num2 = a;
		}
		for (int i = 1; i <= num2; i++) {
			if ((num1 * i) % num2 == 0) {
				return i * num1;
			}
		}
		throw new Error("Error");
	}
	
	public ArrayList<Integer> makeRandomNumberCall(String urlToRead) throws IOException{
		StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      int code = conn.getResponseCode();
	      switch(code){
	    	  case 200:
	    		  break;
	      }
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String resultLine;
	      ArrayList<Integer> randomNumbersList = new ArrayList<Integer>();
	      
	      while ((resultLine = rd.readLine()) != null) {
	         randomNumbersList.add(Integer.parseInt(resultLine));
	      }
	      rd.close();
	      return randomNumbersList;
	}
}
