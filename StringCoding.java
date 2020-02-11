import java.util.*;
public class StringCoding {

	public static void main(String[] args) {
		String a = "asb kss df";
		System.out.println(a.indexOf(" ",20));
	}
	public static int gcd(int x, int y) {
		if(x%y == 0)
			return y;
		return gcd(y,x%y);
	}
	public static void count(int n) {
		if(n == 1)
			return;
		System.out.println(n);
		count(--n);
		System.out.println(n);
	}
	public static void print(Object[] s) {
		for(int i = 0; i < s.length; i ++)
			System.out.println(s[i]);
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
	      /*There is a robot starting at position (0, 0), the origin, on a 2D plane. Given a 
	       * sequence of its moves, judge if this robot ends up at (0, 0) after it completes its moves.
			The move sequence is represented by a string, and the character moves[i] represents its ith move. 
			Valid moves are R (right), L (left), U (up), and D (down). If the robot returns to the origin after it 
			finishes all of its moves, return true. Otherwise, return false.
			Note: The way that the robot is "facing" is irrelevant. "R" will always make the robot move to the right 
			once, "L" will always make it move left, etc. Also, assume that the magnitude of the robot's movement 
			is the same for each move.*/
	      public static boolean judgeCircle(String moves) {
	          int i = 0;
	          int j = 0;
	          for(char c : moves.toCharArray()){
	              if(c == 'U')
	                  j --;
	              else if(c == 'D')
	                  j ++;
	              else if(c == 'L')
	                  i --;
	              else 
	                  i ++;
	          }
	          return i == 0 && j == 0;
	      }
	      /*Given a string, find the length of the longest substring without repeating characters.
			Example 1:
			Input: "abcabcbb"
			Output: 3 
			Explanation: The answer is "abc", with the length of 3. 
			Example 2:
			Input: "bbbbb"
			Output: 1
			Explanation: The answer is "b", with the length of 1.
			Example 3:
			
			Input: "pwwkew"
			Output: 3
			Explanation: The answer is "wke", with the length of 3. 
			Note that the answer must be a substring, "pwke" is a subsequence and not a substring.*/
	      
	      //reached to limited exceeded
	      public static int lengthOfLongestSubstring(String s) {
	    	  if(s.isEmpty()) return 0;
	          int sofar = 1;
	          for(int i = 0; i < s.length()-1; i ++){
	              int now = 1;
	              var set = new HashSet<Character>();
	              set.add(s.charAt(i));
	              for(int j = i+1; j < s.length(); j ++){
	                  if(!set.add(s.charAt(j))){
	                      now = 0;
	                      set.clear();
	                  }else{
	                      now ++;
	                      sofar = Math.max(sofar,now);
	                  }
	              }
	          }
	          return sofar;
	      }
	     
	      public static int lengthOfLongestSubstringSlideWindow(String s) {
	          int i = 0, j = 0, ans = 0;
	          int n = s.length();
	          var set = new HashSet<Character>();
	          while(i < n && j < n){
	              if(!set.add(s.charAt(j))){
	                  set.remove(s.charAt(i++));
	                  continue;
	              }
	              j ++;
	              ans = Math.max(ans,j-i);
	          }
	          return ans;
	      }
	      public int lengthOfLongestSubstringOpt(String s) {
	          int sofar = 0;
	          var map = new HashMap<Character,Integer>();
	          for(int i = 0, j = 0; j < s.length(); j ++){
	              if(map.containsKey(s.charAt(j))){
	                  i = Math.max(map.get(s.charAt(j)), i);
	              }
	                  map.put(s.charAt(j),j+1);
	                  sofar = Math.max(sofar, j-i+1);
	          }
	          return sofar;
	      }
	      /*Implement atoi which converts a string to an integer.
		The function first discards as many whitespace characters as necessary until the first
		non-whitespace character is found. Then, starting from this character, takes an optional 
		initial plus or minus sign followed by as many numerical digits as possible, and interprets 
		them as a numerical value.
		The string can contain additional characters after those that form the integral number, which
		 are ignored and have no effect on the behavior of this function.
		If the first sequence of non-whitespace characters in str is not a valid integral number, or
		 if no such sequence exists because either str is empty or it contains only whitespace 
		 characters, no conversion is performed.
		If no valid conversion could be performed, a zero value is returned.
		Note:
		Only the space character ' ' is considered as whitespace character.
		Assume we are dealing with an environment which could only store integers within the 32-bit 
		signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of 
		representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.
		Example 1:
		
		Input: "42"
		Output: 42
		Example 2:
		Input: "   -42"
		Output: -42
		Explanation: The first non-whitespace character is '-', which is the minus sign.
		             Then take as many numerical digits as possible, which gets 42.
		Example 3:
		Input: "4193 with words"
		Output: 4193
		Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
		Example 4:
		Input: "words and 987"
		Output: 0
		Explanation: The first non-whitespace character is 'w', which is not a numerical 
		             digit or a +/- sign. Therefore no valid conversion could be performed.
		Example 5:
		
		Input: "-91283472332"
		Output: -2147483648
		Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
		Therefore INT_MIN (−231) is returned.*/
	      
	      public static int myAtoi(String str) {
	          if(str.isBlank() || str.isEmpty())
	              return 0;
	          str = str.trim();
	          char first = str.charAt(0);
	          if(!(first >= 48 && first <= 57 || first == 43 || first == 45))
	              return 0;
	          long total = 0;
	          var ls = new ArrayList<Integer>();
	          for(int i = 1; i < str.length(); i ++){
	              char c = str.charAt(i);
	              if(!Character.isDigit(c)) break;
	              ls.add(c-'0');
	          }
	          int pow = 0;
	          for(int i = ls.size()-1; i >= 0; i --){
	              total += ls.get(i)*Math.pow(10,pow++);   
	          }  
	          
	          if(first == 45){
	              if(total > 2147483647l) return Integer.MIN_VALUE;
	              return -1*(int)total;
	          }
	          int head = 0;
	          if(Character.isDigit(first)) head = (first-'0');     
	          total += head*Math.pow(10,pow);
	          if(total >= 2147483647l) return Integer.MAX_VALUE;
	          return (int)total;
	      }
	      public static int myAtoiOpt(String str) {
	          if(str.isBlank() || str.isEmpty())
	              return 0;
	          str = str.trim();
	          char first = str.charAt(0);
	          if(!(Character.isDigit(first) || first == '-' || first == '+'))
	              return 0;
	          int prev = 0, result = 0;
	          boolean isPos = first == '+';
	          boolean isNeg = first == '-';
	          for(int i = (Character.isDigit(first) ? 0 : 1); i < str.length(); i ++){
	              if(!Character.isDigit(str.charAt(i))) break;
	              result = result*10 + (str.charAt(i) - '0');
	              if(result/10 != prev)
	                  return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
	              prev = result;
	          }
	          return isNeg ? -1 * result : result;
	      }
	      /*Given a string and an integer k, you need to reverse the first k characters 
	       * for every 2k characters counting from the start of the string. If there are less than 
	       * k characters left, reverse all of them. If there are less than 2k but greater than or 
	       * equal to k characters, then reverse the first k characters and left the other as original.
			Example:
			Input: s = "abcdefg", k = 2
			Output: "bacdfeg"
			Restrictions:
			The string consists of lower English letters only.
			Length of the given string and k will in the range [1, 10000]*/
	      public static String reverseStr(String s, int k) {
	          StringBuilder st = new StringBuilder();
	          int i = 0;
	          while(i < s.length()){
	              String re = "";
	              while(i < s.length() && i%(2*k) < k)
	                  re += s.charAt(i++);
	              int reI = re.length()-1;
	              while(reI >= 0)
	                  st.append(re.charAt(reI--));
	              if(i >= s.length())
	                  break;
	              st.append(s.charAt(i++));
	          } 
	          return st.toString();
	      } 
	      public static String reverseStrOpt(String s, int k) {
	          char[] c = s.toCharArray();
	          for(int i = 0; i < s.length(); i += 2*k){
	              int j = Math.min(i+k-1,s.length()-1);
	              int start = i;
	              while(start <= j){
	                  char temp = c[start];
	                  c[start++] = c[j];
	                  c[j--] = temp;
	              }           
	          }
	          return new String(c);
	      } 
	      /*Given two strings A and B, find the minimum number of times A has to 
	       * be repeated such that B is a substring of it. If no such solution, return -1.
		For example, with A = "abcd" and B = "cdabcdab".
		Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; 
		and B is not a substring of A repeated two times ("abcdabcd").
		Note:
		The length of A and B will be between 1 and 10000.*/
	      public static int repeatedStringMatch(String A, String B) {
	          StringBuilder st = new StringBuilder();
	          int repeat = 0;
	          while(st.length() < B.length()){
	              st.append(A);
	              repeat ++;
	          }
	          if(st.indexOf(B) != -1)
	              return repeat;
	          st.append(A);
	          return st.indexOf(B) == -1 ? -1 : ++repeat;
	      }
	      
	      /*For strings S and T, we say "T divides S" if and only if S = T + ... + T  
	       * (T concatenated with itself 1 or more times)
			Return the largest string X such that X divides str1 and X divides str2.
			Example 1:
			Input: str1 = "ABCABC", str2 = "ABC"
			Output: "ABC"
			Example 2:
			Input: str1 = "ABABAB", str2 = "ABAB"
			Output: "AB"
			Example 3:
			
			Input: str1 = "LEET", str2 = "CODE"
			Output: ""
			Note:
			
			1 <= str1.length <= 1000
			1 <= str2.length <= 1000
			str1[i] and str2[i] are English uppercase letters.*/
	      public String gcdOfStrings(String str1, String str2) {
	          String big = str1.length() > str2.length() ? str1 : str2;
	          String small = str1.length() > str2.length() ? str2 : str1;
	         
	          return sp(big,small);
	      }
	      public String sp(String str1, String str2){
	          if(str1.split(str2).length == 0)
	              return str2;
	          if(str1.indexOf(str2) == -1)
	              return "";
	          StringBuilder st = new StringBuilder();
	          String [] result = str1.split(str2);
	          for(String s : result) st.append(s);
	          return sp(str2, st.toString());
	      }
}
