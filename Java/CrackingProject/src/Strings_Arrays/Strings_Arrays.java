package Strings_Arrays;
import java.util.HashMap;
import java.util.Map;

public class Strings_Arrays {
	public static void main(String args[]){
		int test = 0;
		if(test==1||test==0){
			System.out.println("Unique test version 1:");
			System.out.println("expected: true\tresult: "+isUniqueV1("abcdefghijklmnopqrstuvwxyz"));
			System.out.println("expected: false\tresult: "+isUniqueV2("abcdefghijklmnopqrstuvwxyza"));
			System.out.println("\nUnique test version 2:");
			System.out.println("expected: true\tresult: "+isUniqueV1("abcdefghijklmnopqrstuvwxyz"));
			System.out.println("expected: false\tresult: "+isUniqueV2("abcdefghijklmnopqrstuvwxyza"));
			System.out.println("\nUnique test version 3:");
			System.out.println("expected: true\tresult: "+isUniqueV3("abcdefghijklmnopqrstuvwxyz"));
			System.out.println("expected: false\tresult: "+isUniqueV3("abcdefghijklmnopqrstuvwxyza"));
			System.out.println("\nUnique test version 4:");
			System.out.println("expected: true\tresult: "+isUniqueV4("abcdefghijklmnopqrstuvwxyz"));
			System.out.println("expected: false\tresult: "+isUniqueV4("abcdefghijklmnopqrstuvwxyza"));
			System.out.println("\nUnique test version 5:");
			System.out.println("expected: true\tresult: "+isUniqueV5("abcdefghijklmnopqrstuvwxyz"));
			System.out.println("expected: false\tresult: "+isUniqueV5("abcdefghijklmnopqrstuvwxyza")+"\n");
		}
		if(test==2||test==0){
			System.out.println("Is permutation version 1:");
			System.out.println("expected: true\tresult: "+isPermutationV1("cat", "act"));
			System.out.println("expected: false\tresult: "+isPermutationV1("cat", "taco"));
			System.out.println("expected: false\tresult: "+isPermutationV1("cat", "dog"));
			System.out.println("\nIs permutation version 2:");
			System.out.println("expected: true\tresult: "+isPermutationV2("cat", "act"));
			System.out.println("expected: false\tresult: "+isPermutationV2("cat", "taco"));
			System.out.println("expected: false\tresult: "+isPermutationV2("cat", "dog")+"\n");
		}
		if(test==3||test==0){
			System.out.println("Replace Spaces version 1:");
			System.out.println("before: Mr John Smith\tresult: "+replaceSpacesV1("Mr John Smith    ", 13));
			System.out.println("\nReplace Spaces version 2:");
			System.out.println("before: Mr John Smith\tresult: "+replaceSpacesV2("Mr John Smith    ", 13)+"\n");
		}
		if(test==4||test==0){
			System.out.println("Is palidrome permutation version 1:");
			System.out.println("expected: true\tresult: "+isPalindromePermutationV1("tacocat"));
			System.out.println("expected: true\tresult: "+isPalindromePermutationV1("racecar"));
			System.out.println("expected: true\tresult: "+isPalindromePermutationV1("raccar"));
			System.out.println("expected: true\tresult: "+isPalindromePermutationV1("Tact Coa"));
			System.out.println("expected: false\tresult: "+isPalindromePermutationV1("racear"));
			System.out.println("expected: false\tresult: "+isPalindromePermutationV1("Mr John Smith    ")+"\n");
			System.out.println("Is palidrome permutation version 2:");
			System.out.println("expected: true\tresult: "+isPalindromePermutationV2("taco cat"));
			System.out.println("expected: true\tresult: "+isPalindromePermutationV2("tacocat"));
			System.out.println("expected: true\tresult: "+isPalindromePermutationV2("racecar"));
			System.out.println("expected: true\tresult: "+isPalindromePermutationV2("raccar"));
			System.out.println("expected: true\tresult: "+isPalindromePermutationV2("Tact Coa"));
			System.out.println("expected: false\tresult: "+isPalindromePermutationV2("racear"));
			System.out.println("expected: false\tresult: "+isPalindromePermutationV2("Mr John Smith    ")+"\n");
		}
		if(test==5||test==0){
			System.out.println("One away check:");
			System.out.println("expected: true\tresult: "+oneAway("str", "str"));
			System.out.println("expected: true\tresult: "+oneAway("pale", "ple"));
			System.out.println("expected: true\tresult: "+oneAway("pales", "pale"));
			System.out.println("expected: true\tresult: "+oneAway("pale", "bale"));
			System.out.println("expected: true\tresult: "+oneAway("strl", "str"));
			System.out.println("expected: false\tresult: "+oneAway("strlb", "str"));
			System.out.println("expected: false\tresult: "+oneAway("bake", "pale")+"\n");

			
		}
		if(test==6||test==0){
			System.out.println("Compression:");
			System.out.println("before: aabbccddeeffggggggggggg");
			System.out.println("after: "+compression("aabbccddeeffggggggggggg"));
			System.out.println("before: abcd");
			System.out.println("after: "+compression("abcd"));
			System.out.println("before: aaaaaaaaaaaaaaaaaaabbbbbbdlkjsdaklfjdslaaakjajsjjdjdjdjdjjjjjjjj");
			System.out.println("after: "+compression("aaaaaaaaaaaaaaaaaaabbbbbbdlkjsdaklfjdslaaakjajsjjdjdjdjdjjjjjjjj")+"\n");
		}
		if(test==7||test==0){
			System.out.println("rotate matrix:");
			int[][] matrix= new int[8][8];
			initMatrix2(matrix);
			System.out.println("before:");
			printMatrix(matrix);
			matrix =rotateMatrix(matrix);
			System.out.println("\nafter:");
			printMatrix(matrix);
			System.out.println();
		}
		if(test==8||test==0){
			System.out.println("zero matrix:");
			int[][] matrix= new int[5][5];
			initMatrix(matrix);
			System.out.println("before:");
			printMatrix(matrix);
			matrix =zeroMatrix(matrix);
			System.out.println("\nafter:");
			printMatrix(matrix);
			System.out.println();
		}
		if(test==9||test==0){
			System.out.println("string rotation version 1:");
			System.out.println("expected: true\tresult: "+rotationV1("waterbottle", "erbottlewat"));
			System.out.println("expected: false\tresult: "+rotationV1("waterbottle", "bottle"));
			
			System.out.println("\nstring rotation version 2:");
			System.out.println("expected: true\tresult: "+rotationV2("waterbottle", "erbottlewat"));
			System.out.println("expected: false\tresult: "+rotationV2("waterbottle", "bottle"));
		}
		
		
	}
	
	/**
	 * 1.1
	 * checks if a string is unique using a hashmap.
	 */
	public static boolean isUniqueV1(String str){
		Map<Character, Integer> unique = new HashMap<>();
		for(char c: str.toLowerCase().toCharArray()){
			int occ = unique.getOrDefault(c, 0)+1;
			if(occ>1){
				return false;
			}
			unique.put(c, occ);
		}
		return true;
	}
	
	/**
	 * 1.1
	 * checks if a string is unique by removing instances of a character.
	 * If length greater than 1 less, then obviously more than one instance of a character.
	 */
	public static boolean isUniqueV2(String str){
		for(int i =0; i<str.length(); i++){
			int len = str.length();
			str = str.replaceAll(str.substring(i, i+1), "");
			if(str.length()!=len-1){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 1.1
	 * checks if a string is unique by sorting string and then doing a pass over string
	 */
	public static boolean isUniqueV3(String str){
		str = mergeSort(str);
		for(int i =0; i<str.length()-1; i++){
			if(str.charAt(i)==str.charAt(i+1)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * mergesort to sort string
	 */
	public static String mergeSort(String str){
		if(str.length()>1){
			int q = str.length()/2;
			String l = mergeSort(str.substring(0, q));
			String r = mergeSort(str.substring(q, str.length()));
			str = merge(l,r, l.length()+r.length());
		}
		return str;
	}
	
	/**
	 * merge step used to combine strings in mergesort
	 */
	public static String merge(String l, String r, int len){
		int i = 0;
		int j = 0;
		String st = "";
		l+=(char)127;
		r+=(char)127;
		for(int k = 0; k<len; k++){
			if(l.charAt(i)<=r.charAt(j)){
				st+=l.charAt(i);
				i++;
			}else{
				st+=r.charAt(j);
				j++;
			}
		}
		return st;
	}
	
	/**
	 * 1.1
	 * checks if a string is unique using char map
	 */
	public static boolean isUniqueV4(String str){
		boolean[] charArray = new boolean[128];
		for(char c : str.toCharArray()){
			int in = (int) c;
			if(charArray[in]){
				return false;
			}
			charArray[in]=true;
		}
		return true;
	}
	
	/**
	 * 1.1
	 * checks if a string is unique using bit vectors
	 */
	public static boolean isUniqueV5(String str){
		int check=0;
		for(char c : str.toCharArray()){
			int in = (int)(c-'a');
			if((check&(1<<in))>0){
				return false;
			}
			check|=(1<<in);
		}
		return true;
	}
	
	/**
	 * 1.2
	 * checks if a string is permutation of another string by using a charmap
	 */
	public static boolean isPermutationV1(String str1, String str2){
		if(str1.length()!=str2.length()){
			return false;
		}
		
		int[] charArray = new int[128];
		for(char c: str1.toCharArray()){
			charArray[(int)c]++;
		}
		for(char c: str2.toCharArray()){
			charArray[(int)c]--;
			if(charArray[(int)c]<0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 1.2
	 * checks if a string is permutation of another string sorting
	 */
	public static boolean isPermutationV2(String str1, String str2){
		if(str1.length()!=str2.length()){
			return false;
		}
		
		str1 = mergeSort(str1);
		str2 = mergeSort(str2);
		for(int i = 0; i < str1.length(); i++){
			if(str1.charAt(i)!=str2.charAt(i)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 1.3
	 * replaces spaces in string with %20 BY CHEATING
	 */
	public static String replaceSpacesV1(String str, int len){
		return str.trim().replaceAll(" ", "%20");
	}
	
	/**
	 * 1.3
	 * replaces spaces in string with %20 by shifting
	 */
	public static String replaceSpacesV2(String str, int len){
		char[] chars = str.toCharArray();
		int spaceCount=0;
		for(int i=0; i<len; i++){
			if(chars[i]==" ".charAt(0)){
				spaceCount++;
			}
		}
		int toShift = 2*spaceCount;
		for(int j = len-1; j>=0; j--){
			if(chars[j]!=" ".charAt(0)){
				chars[j+toShift]=chars[j];
			}else{
				toShift-=2;
				chars[j+toShift]="%".charAt(0);
				chars[j+1+toShift]="2".charAt(0);
				chars[j+2+toShift]="0".charAt(0);
				spaceCount-=1;
				if(spaceCount==0){
					return new String(chars);
				}
			}
		}
		return new String(chars);
	}
	
	/**
	 * 1.4
	 * checks if string is a permutation of a palindrome
	 * If palidrome then only 1 odd set of chars
	 */
	public static boolean isPalindromePermutationV1(String str){
		str = str.toLowerCase().replaceAll(" ", "");
		int[] charArray = new int[26];
		for(char c: str.toCharArray()){
			charArray[(int)c-'a']++;
		}
		int oddcount=0;
		for(int i : charArray){
			if(i%2!=0){
				oddcount++;
				if(oddcount>1){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 1.4
	 * checks if string is a permutation of a palindrome using bit vector
	 */
	public static boolean isPalindromePermutationV2(String str){
		str = str.toLowerCase().replaceAll(" ", "");
		int checker=0;
		for(char c: str.toCharArray()){
			int in = (int)(c-'a');
			if((checker&(1<<in))>0){
				checker^=(1<<in);
			}else{
				checker|=(1<<in);
			}
		}
		int checksum = checker-1;
		return (checksum&checker)==0;
	}
	
	/**
	 * 1.5
	 * checks if 2 strings are only one edit away
	 */
	public static boolean oneAway(String str1, String str2){
		if(str1.equals(str2)){
			return true;
		}
		
		if(Math.abs(str1.length()-str2.length())>1){
			return false;
		}
		
		String larger = (str1.length()>=str2.length()) ? str1 : str2;
		String smaller = (larger.equals(str1)) ? str2 : str1;
		boolean difference = larger.length()!=smaller.length();
		
		for(int i = 0; i<smaller.length();i++){
			if(smaller.charAt(i)!=larger.charAt(i)){
				if(difference){
					smaller = smaller.substring(0, i)+larger.charAt(i)+smaller.substring(i,smaller.length());
				}else{
					smaller = smaller.substring(0, i)+larger.charAt(i)+smaller.substring(i+1,smaller.length());
				}

				if(smaller.equals(larger)){
					return true;
				}else{
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 1.6
	 * compresses strings
	 */
	public static String compression(String str){
		StringBuilder st = new StringBuilder();
		int repeat = 1;
		for(int i = 0; i < str.length()-1; i++){
			if(str.charAt(i)==str.charAt(i+1)){
				repeat++;
			}else{
				st.append(str.charAt(i)+""+repeat);
				repeat=1;
			}
		}
		st.append(str.charAt(str.length()-1)+""+repeat);
		return (st.toString().length()>=str.length())? str : st.toString();
	}
	
	/**
	 * 1.7
	 * rotate matrix
	 */
	public static int[][] rotateMatrix(int[][] matrix){
		int[][] matrixTwo = new int[matrix.length][matrix[0].length];
		int xoffsetInit=matrix.length-1;
		int yoffsetInit=0;
		for(int i =0; i< matrix.length; i++){
			int xoffset=xoffsetInit;
			int yoffset=yoffsetInit;
			for(int j =0; j< matrix[i].length;j++){
//				System.out.println("before: ("+i+" , "+j+")"+" = "+matrix[i][j]);
//				System.out.println("before: ("+(i+yoffset)+" , "+(j+xoffset)+")"+" = "+matrix[i+yoffset][j+xoffset]);
//				System.out.println("after: ("+(i+yoffset)+" , "+(j+xoffset)+")");
//				System.out.println("difference: ("+xoffset+" , "+yoffset+")");
				
				matrixTwo[i+yoffset][j+xoffset]=matrix[i][j];
				xoffset--;
				yoffset++;
				//printMatrix(matrixTwo);
			}
			xoffsetInit--;yoffsetInit--;
		}
		return matrixTwo;
	}
	
	/**
	 * 1.8
	 * zero matrix
	 */
	public static int[][] zeroMatrix(int[][] matrix){
		boolean[] zeroRow= new boolean[matrix.length];
		boolean[] zeroColumn = new boolean[matrix[0].length];
		for(int i =0; i< matrix.length; i++){
			for(int j =0; j< matrix[i].length;j++){
				if(matrix[i][j]==0){
					zeroRow[i]=true;
					zeroColumn[j]=true;
				}
			}
		}
		
		for(int i =0; i< matrix.length; i++){
			for(int j =0; j< matrix[i].length;j++){
				if(zeroRow[i]||zeroColumn[j]){
					matrix[i][j]=0;
				}
			}
		}
		return matrix;
	}
	
	public static void initMatrix(int[][] matrix){
		for(int i =0; i< matrix.length; i++){
			for(int j = 0; j< matrix[0].length; j++){
				matrix[i][j]=(Math.random()<.9)? 1:0;
			}
		}
	}
	
	public static void initMatrix2(int[][] matrix){
		for(int i =0; i< matrix.length; i++){
			for(int j = 0; j< matrix[0].length; j++){
				matrix[i][j]=(int)(Math.random()*10);
			}
		}
	}
	
	public static void printMatrix(int[][] matrix){
		for(int i =0; i< matrix.length; i++){
			for(int j = 0; j< matrix[0].length; j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 1.9
	 * string rotation using sorting
	 */
	public static boolean rotationV1(String str1, String str2){
		str1 = mergeSort(str1);
		str2 = mergeSort(str2);
		return str1.contains(str2);
	}
	
	/**
	 * 1.9
	 * string rotation using concatenation
	 */
	public static boolean rotationV2(String str1, String str2){
		str2 = str2+str2;
		return str2.contains(str1);
	}
}
