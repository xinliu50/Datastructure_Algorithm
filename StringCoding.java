import java.util.*;
public class StringCoding {

	public static void main(String[] args) {
		String a = ", , , ,        a, eaefa";
		//System.out.println(a.length());
		
		String b = a.trim();
		int l = b.split("\\s+").length;
		System.out.println(l);
		
	}
	public static void count(int n) {
		if(n == 1)
			return;
		System.out.println(n);
		count(--n);
		System.out.println(n);
	}
	
	/*Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

	An input string is valid if:
	
	Open brackets must be closed by the same type of brackets.
	Open brackets must be closed in the correct order.
	Note that an empty string is also considered valid.
	 * */
	public static boolean isValid(String s) {
        if(s.length() == 0)
            return true;
        if(s.length()%2 != 0)
            return false;
        var stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i ++){
            char c = s.charAt(i);
            if(c == '(' || c == '{' || c == '[' )
                stack.push(c);
            else{
                if(stack.isEmpty())
                    return false;
                char temp = stack.pop();
                if(c == ']' && temp != '[' || c == '}' && temp != '{' || c == ')' && temp != '('){
                        return false;
                }   
            }
        }
        return stack.isEmpty();
    }
	//only checks "()"
	public static boolean isValidLoop(String s) {
		if(s.isBlank())
			return true;
		int p = 0;
		for(int i = 0; i < s.length(); i ++) {
			if(p < 0)
				return false;
			if(s.charAt(i) == '(')
				p++;
			else 
				p--;			
		}
		return p == 0;
	}
	
	public static boolean isValidRec(String s) {
		if(s.isBlank())
			return true; 
		
		return check(s,0,0);
	}
	
	public static boolean check(String s, int index, int p) {
		if(index == s.length())
			return p == 0;
		else if(p < 0)
			return false;	
		if(s.charAt(index) == '(')
			p++;
		else
			p--;		
		return check(s,++index,p);
	}
	/*Implement strStr().
	Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
	Example 1:
	Input: haystack = "hello", needle = "ll"
	Output: 2
	Example 2:
	Input: haystack = "aaaaa", needle = "bba"
	Output: -1
	Clarification:
	What should we return when needle is an empty string? This is a great question to ask during an interview.
	For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent 
	to C's strstr() and Java's indexOf().*/
	
	public static int strStr(String haystack, String needle) {
        if(needle.isEmpty() || haystack == needle)
            return 0;
        if(haystack.length() < needle.length())
            return -1;
        int n = needle.length();
        for(int i = 0; i < haystack.length(); i ++){
            if(i+n <= haystack.length()){
                String sub = haystack.substring(i,i+n);
                if(sub.equals(needle)){
                    return i;       
                }
            }else
                return -1;
        }
        return -1;
    }
	public static int strStrWithoutAPI(String haystack, String needle) {
        if(needle.isEmpty() || haystack == needle)
            return 0;
        if(haystack.length() < needle.length())
            return -1;
        int n = needle.length();
        for(int i = 0; i < haystack.length(); i ++){
            if(i+n <= haystack.length()){
                String sub = "";
                int index = i;
                while(index < i+n){
                    sub += haystack.charAt(index);
                    index ++;
                }             
                if(sub.equals(needle)){
                    return i;       
                }
            }else
                return -1;
        }
        return -1;
    }
	/*The count-and-say sequence is the sequence of integers with the first five terms as following:
	1.     1
	2.     11
	3.     21
	4.     1211
	5.     111221
	1 is read off as "one 1" or 11.
	11 is read off as "two 1s" or 21.
	21 is read off as "one 2, then one 1" or 1211.
	
	Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence. 
	You can do so recursively, in other words from the previous member read off the digits, counting 
	the number of digits in groups of the same digit.
	Note: Each term of the sequence of integers will be represented as a string.*/
	
	 public static String countAndSay(int n) {
	        String result = "1";
	        for(int i = 2; i <= n ;  i ++){
	            result = read(result);
	        }
	        return result;
	    }
	    public static String read(String s){
	        int fre = 1;
	        String result = "";
	        char c = s.charAt(0);
	        for(int i = 1; i < s.length(); i ++){
	            if(s.charAt(i) == s.charAt(i-1)){
	                fre ++;
	            }else{
	                result = result + fre + s.charAt(i-1);
	                fre = 1;
	            }
	        }
	        result = result + fre + s.charAt(s.length()-1);
	        return result;
	    }
	   //using StringBuilder decrease the runtime significantly 
	    public static String countAndSayBuilder(int n) {
	        String result = "1";
	        for(int i = 2; i <= n; i ++){
	            result = read(result);
	        }
	        return result;
	    }
	    public static String readBuilder(String s){
	        StringBuilder result = new StringBuilder();
	        int fre = 1;
	        for(int i = 1; i < s.length(); i ++){
	            if(s.charAt(i) == s.charAt(i-1)){
	                fre ++;
	            }else{
	                result.append(fre);
	                result.append(s.charAt(i-1));
	                fre = 1;
	            }
	        }
	        result.append(fre);
	        result.append(s.charAt(s.length()-1));
	        return result.toString();
	    }
	    /*Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
	     * return the length of last word (last word means the last appearing word if we loop from 
	     * left to right) in the string.
		If the last word does not exist, return 0.
		Note: A word is defined as a maximal substring consisting of non-space characters only.
		Example:
		Input: "Hello World"
		Output: 5*/
	    public static int lengthOfLastWord(String s) {
	        if(s.length() == 0)
	            return 0;
	        s = s.trim();
	        int space = s.lastIndexOf(" ");
	        return s.length() - space - 1;
	    }
	   /*Given a string, determine if it is a palindrome, considering only alphanumeric characters
	    *  and ignoring cases.
		Note: For the purpose of this problem, we define empty string as valid palindrome.
		Example 1:
		Input: "A man, a plan, a canal: Panama"
		Output: true
		Example 2:
		Input: "race a car"
		Output: false*/
	    public static boolean isPalindrome(String s) {
	    	s = s.toLowerCase();
	    	int i = 0;
	    	int k = s.length()-1;
	    	while(i <= k) {
	    		while((i<=k) && s.charAt(i) >= 48 && s.charAt(i) <= 57 || 
	    				s.charAt(i) >= 65 && s.charAt(i) <= 90	||
	    				s.charAt(i) >= 97 && s.charAt(i) <= 122)
	    			i ++;
	    		while((k>=0) && s.charAt(k) >= 48 && s.charAt(k) <= 57 || 
	    				s.charAt(k) >= 65 && s.charAt(k) <= 90	||
	    				s.charAt(k) >= 97 && s.charAt(k) <= 122)
	    			k --;
	    		if(i <= k && s.charAt(i) != s.charAt(k))
	    			return false;
	    		i ++;
	    		k --;
	    	}
	    	return true;
	    }
	    public static boolean isPalindromeCharacter(String s) {
	        int i = 0;
	        int k = s.length()-1;
	        while(i <= k){
	            while(i <= s.length()-1 && !Character.isLetterOrDigit(s.charAt(i)))
	                i++;
	            while(k >= 0 && !Character.isLetterOrDigit(s.charAt(k)))
	                k--;
	            if(k < i)
	                return true;
	            if(Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(k)))
	                return false;
	            i ++;
	            k --;
	        }
	        return true;
	    }
	    /*Write a function that reverses a string. The input string is given as an array 
	     * of characters char[].
		Do not allocate extra space for another array, you must do this by modifying the 
		input array in-place with O(1) extra memory.
		You may assume all the characters consist of printable ascii characters.
		Example 1:
		
		Input: ["h","e","l","l","o"]
		Output: ["o","l","l","e","h"]
		Example 2:
		
		Input: ["H","a","n","n","a","h"]
		Output: ["h","a","n","n","a","H"]*/
	    
	    public static void reverseString(char[] s) {
	        if(s.length == 1)
	            return;
	        
	        for(int i = 0, k = s.length-1; i < k;){
	            char temp = s[i];
	            s[i] = s[k];
	            s[k] = temp;
	            i++;
	            k--;
	        }
	    }
	    
	    public static void reverseStringRec(char[] s) {
	        reverseRec(s,0,s.length-1);
	     }
	     public static void reverseRec(char[]s, int left, int right){
	         if(left>= right)
	             return;
	         char temp = s[left];
	         s[left] = s[right];
	         s[right] = temp;
	          reverseRec(s,++left,--right);
	     }
	     /*Write a function that takes a string as input and reverse only the vowels of a string.
		Example 1:
		Input: "hello"
		Output: "holle"
		Example 2:
		Input: "leetcode"
		Output: "leotcede"*/
	     public String reverseVowels(String s) {
	         char[] result = new char[s.length()];
	         for(int i = 0, k = s.length()-1; i <= k;){
	             while(i<=k && !isVowel(s.charAt(i))){
	                 result[i] = s.charAt(i);
	                 i++;
	             }
	             while(k>=0 && !isVowel(s.charAt(k))){
	                 result[k] = s.charAt(k);
	                 k --; 
	            }
	             if(i <= k){
	                result[i] = s.charAt(k);
	                result[k] = s.charAt(i);
	             }
	              i ++;
	              k --;
	         }
	         return String.copyValueOf(result);
	     }
	     public boolean isVowel(char c){
	         if(c == 'a' || c == 'A' || c == 'i' || c == 'I' || c == 'o' 
	            || c == 'O' || c == 'U' || c == 'u' || c == 'e' || c == 'E')
	             return true;
	         return false;
	     }
	     
	     public static String reverseVowelsOp(String s) {
	         char[] result = s.toCharArray();
	          for(int i = 0, k = s.length()-1; i <= k;){
	              char left = result[i];
	              char right = result[k];
	              if(isVowelOp(left) && isVowelOp(right)){
	                  result[i] = s.charAt(k);
	                  result[k] = s.charAt(i);
	                  i ++;
	                  k --;
	              }
	              if(!isVowelOp(left))
	                  i++;
	              if(!isVowelOp(right))
	                  k--;
	          }
	          return (new String(result));
	      }
	      public static boolean isVowelOp(char a){
	          char c = Character.toLowerCase(a);       
	          return(c == 'a' || c == 'i' || c == 'o' 
	            || c == 'u' || c == 'e');
	      }
	      /*Given a word, you need to judge whether the usage of capitals in it is right or not.
		We define the usage of capitals in a word to be right when one of the following cases holds:
		All letters in this word are capitals, like "USA".
		All letters in this word are not capitals, like "leetcode".
		Only the first letter in this word is capital, like "Google".
		Otherwise, we define that this word doesn't use capitals in a right way.*/
	      
	      public static boolean detectCapitalUse(String word) {
	          boolean hasCap = Character.isUpperCase(word.charAt(0)); 
	          int count = 0;
	          for(int i = 1; i < word.length(); i ++){
	              char c = word.charAt(i);
	              if(Character.isUpperCase(c))
	                  count ++;
	          }
	          return hasCap && (count == word.length()-1 || count == 0) || !hasCap && count == 0;
	      }
	      
	      public boolean detectCapitalUseOpt(String word) {
	          int count = 0;
	           for(char c : word.toCharArray()){
	               if(Character.isUpperCase(c)) count ++;
	           }
	           if(count == 1) return Character.isUpperCase(word.charAt(0));
	           return count == 0 || count == word.length();
	       }
	      /*Given a string, find the first non-repeating character in it and return it's index. 
	       * If it doesn't exist, return -1.
		Examples:
		s = "leetcode"
		return 0.
		s = "loveleetcode",
		return 2.*/
	      
	      public static int firstUniqChar(String s) {
	          if(s.isEmpty() || s.isBlank())
	              return -1;
	          var map = new HashMap<Character,Integer>();
	          if(s.length() == 1)
	              return 0;
	          for(int i = 0; i < s.length()-1; i ++){
	              if(!map.containsKey(s.charAt(i))){
	                  int index = s.indexOf(s.charAt(i)+"",i+1);
	                  if(index == -1)
	                      return i;
	                  map.put(s.charAt(i),0);
	              }
	          }
	          if(!map.containsKey(s.charAt(s.length()-1)))
	              return s.length()-1;
	          return -1;
	      }
	      /*Given two non-negative integers num1 and num2 represented as string, return 
	       * the sum of num1 and num2.
		Note:
		The length of both num1 and num2 is < 5100.
		Both num1 and num2 contains only digits 0-9.
		Both num1 and num2 does not contain any leading zero.
		You must not use any built-in BigInteger library or convert the inputs to integer directly.*/
	      public static String addStrings(String num1, String num2) {
	          int carry = 0;
	          int i = num1.length()-1;
	          int j = num2.length()-1;
	          String result = "";
	          while(i >= 0 && j >= 0){
	               int sum = Integer.parseInt(num1.charAt(i--)+"") + Integer.parseInt(num2.charAt(j--)+"") + carry;
	               carry = sum/10;
	               result = sum%10 + result;
	          }
	          while(i >= 0){
	              int sum = Integer.parseInt(num1.charAt(i--)+"") + carry;
	              result = sum%10 + result;
	              carry = sum/10;
	          }
	          while(j >= 0){
	              int sum = Integer.parseInt(num2.charAt(j--)+"") + carry;
	              result = sum%10 + result;
	              carry = sum/10;
	          }
	          if(carry == 1)
	              result = "1" + result;
	          return result;
	      }
	      
	      public static String addStringsOpt(String num1, String num2) {
	          StringBuilder result = new StringBuilder();
	          int carry = 0;
	          int i = num1.length()-1;
	          int j = num2.length()-1;
	          while(i >= 0 && j >= 0){
	              int sum = num1.charAt(i--) - '0' + num2.charAt(j--) - '0' + carry;
	              result.insert(0,sum%10);
	              carry = sum/10;
	          }
	          while(i >= 0){
	              int sum = num1.charAt(i--) - '0' + carry;
	              result.insert(0,sum%10);
	              carry = sum/10;
	          }
	          while(j >= 0){
	              int sum = num2.charAt(j--) - '0' + carry;
	              result.insert(0,sum%10);
	              carry = sum/10;
	          }
	          if(carry == 1)
	              result.insert(0,1);
	          return result.toString();
	      }
	      
	      public static String addStringsOpt1(String num1, String num2) {
	          StringBuilder result = new StringBuilder();
	          int i = num1.length()-1, j = num2.length()-1, carry = 0, digt1 = 0, digt2 = 0;
	          while(i >= 0 || j >= 0){
	              digt1 = i >= 0 ? num1.charAt(i--) - '0' : 0;
	              digt2 = j >= 0 ? num2.charAt(j--) - '0' : 0;
	              int sum = digt1 + digt2 + carry;
	              result.insert(0,sum%10);
	              carry = sum/10;
	          }
	          if(carry == 1)
	              result.insert(0,1);
	          return result.toString();
	      }
	      /*Count the number of segments in a string, where a segment is defined to 
	       * be a contiguous sequence of non-space characters.
		Please note that the string does not contain any non-printable characters.
		Example:
		Input: "Hello, my name is John"
		Output: 5*/
	      
	      public static int countSegments(String s) {
	    	  if(s.isBlank() || s.isEmpty())
	              return 0;
	          if(s.indexOf(" ") == -1)
	              return 1;
	          s = s.trim();
	          int count = 1;
	          for(int i = 1; i < s.length(); i ++)
	              if(s.charAt(i-1) == ' ' && s.charAt(i) != ' ') count ++;
	          return count;
	      }
}
