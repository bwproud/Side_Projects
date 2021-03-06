import java.util.Random;

public class MyRandom {
	public MyRandom(){}
	private static Random rn = new Random();

	public EntryPair nextEP(){
		int num = nextNum();
		String s= nextString(1, 5);
		return new EntryPair(s, num);
	}
	
	public static int nextNum(){
		return rn.nextInt(20)+1;
	}
	
	public static int rand(int lo, int hi) {
	     int n = hi - lo + 1;
	     int i = rn.nextInt() % n;
	     if (i < 0) i = -i;
	     return lo + i;
	  }

	  public static String nextString(int lo, int hi) {
	     int n = rand(lo, hi);
	     byte b[] = new byte[n];
	     for (int i = 0; i < n; i++)
	     b[i] = (byte)rand('a', 'z');
	     return new String(b, 0);
	  }
}
