import java.util.*;
import java.util.stream.*;

public class StringCoding {

	public static void main(String[] args) {
//		node head = new node(3);
//		node n1 = new node(0);
//		node n3 = new node(1);
//		node n4 = new node(2);
//		node n5 = new node(4);
//		node n6 = new node(4);
//		node n7 = new node(6);
//		node n8 = new node(6);
//		head.next = n1;
//		n1.next = n3;
//		n3.next = n4;
//		n4.next = n5;
//		n5.next = n6;
//		n6.next = n7;
//		n7.next = n8;
//		
//		System.out.println(dumpNodeValue(head));
//		var map = new HashMap<Integer,Integer>();
//		char c = 'c';
//		int myint = c;
//		System.out.println(myint);
//		map.put(1, 2);
//		map.put(1, 20);
//		map.put(11, 12);
//		map.put(10, 3);
//		map.put(3, 2);
//		map.put(3, 2);
//		System.out.println(map);
		
		//compare(a,b);
		int[][]a = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
		print(a);
		System.out.println("______________");
		System.out.println(maxAreaOfIsland(a));
		
	}
	
	public static int uniquePaths(int m, int n) {
        int[][] map = new int [n][m];
        return findPath(m,n,map);
    }
    public static int findPath(int m, int n, int[][]map){
         for(int i = 0; i < n; i ++){
             map[i][0] = 1;
         }
         for(int j = 0; j < m; j ++){
             map[0][j] = 1;
         }
        for(int i = 1; i < n; i ++){
            for(int j = 1; j < m; j ++){
                map[i][j] = map[i-1][j] + map[i][j-1];
            }
        }
        return map[n-1][m-1];
    }
	
	public static int maxAreaOfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int r = grid.length;
        int c = grid[0].length;
        int area = 0;
        for(int i = 0; i < r; i ++){
            for(int j = 0; j < c; j ++){
                if(!visited[i][j] && grid[i][j] == 1){
                    int now = findArea(grid,i,j,visited,r,c);
                    area = Math.max(area, now);
                }
            }
        }
        return area;
    }
    public static int findArea(int[][] grid, int i, int j, boolean[][] visited, int r, int c){    
         if(i < 0 || i >= r || j < 0 || j >= c || visited[i][j] || grid[i][j] == 0)
             return 0;
         visited[i][j] = true;
         return 1+findArea(grid,i-1,j,visited,r,c)+findArea(grid,i+1,j,visited,r,c)+ findArea(grid,i,j-1,visited,r,c)+findArea(grid,i,j+1,visited,r,c);
         
    }
	public static int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int l = 0;
        int r = nums.length-1;
        
        while(l <= r){
            int mid = (l+r)/2;
           
            System.out.println("mid: " + mid + " l: " + l + " r: " + r );
            if(nums[mid] == target)
                return mid;
            if(nums[mid] < nums[r]){
                if(target > nums[mid] && target < nums[r]) l = mid+1;
                else r = mid-1;
            }else{
                if(target > nums[l] && target < nums[mid]) r = mid-1;
                else    l = mid+1;
            }
            
        }
        return -1;
    }
	public static int subarraySum(int[] nums, int k) {
		int count = 0, sum = 0;
        HashMap < Integer, Integer > map = new HashMap < > ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            System.out.println(map);
        }
        return count;
    }
	
	 public static int subArraySum(int[]nums, int k) {
		 int c = 0;
		 for(int i = 0; i < nums.length; i ++) {
			 int total = nums[i];
			 if(total == k)
				 c ++;
			 for(int j = i+1; j < nums.length; j ++) {
				 total += nums[j];
				 if(total == k)
					 c++;
			 }
		 }
		 return c;
	 }
	 public static int firstUniqChar1(String s) {
	        if(s.length() == 1)
	            return 0;
	        for(int i = 0; i < s.length(); i ++)
	            if(i != s.length()-1 && s.indexOf(s.charAt(i),(i+1)) == -1){
	              return i;
	            }
	        return -1;
	}
	//assume array a is sorted in ascending order and might has been rotated 
	public static int findMinNum(int[] a) {
		//if has not been rotated
		if(a[0] <= a[a.length-1])
			return a[0];
		int left = 0;
		int right = a.length-1;
		while(left < right) {
			int mid = left + (right-left)/2;
			if(a[mid] > a[mid+1])
				return a[mid+1];
			if(a[mid-1] > a[mid])
				return a[mid];
			else {
				if(a[0] > a[mid])
					right = mid-1;
				else
					left = mid+1;
			}
		}
		return -1;
	}
	
	public static int[] intersectionOptimize(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int[] result = new int[Math.max(nums1.length,nums2.length)];
        int index = 0;
        int i = 0, j = 0;
        while(i < nums1.length && j < nums2.length){
            int l1 = nums1[i];
            int l2 = nums2[j];
            if(l1 == l2){
                result[index++] = l1;
                while(i < nums1.length && l1 == nums1[i]) i++;
                while(j < nums2.length && l2 == nums2[j]) j ++;
                continue;
            }
            if(l1 < l2) while( i < nums1.length && l1 == nums1[i]) i++;
            else  while(j < nums2.length && l2 == nums2[j]) j++;
        }
        return Arrays.copyOf(result, index);
    }
	  public static int[] intersection(int[] nums1, int[] nums2) {
	        var set = new HashSet<Integer>();
	        for(int i : nums1)
	            set.add(i);
	        var newSet = new HashSet<Integer>();
	        for(int i : nums2)
	            if(set.contains(i))
	                newSet.add(i);

	        int[] result = new int[newSet.size()];
	        int index = 0;
	        for(int i : newSet)
	            result[index++] = i;
	        return result;
	    }
	 public static List<List<String>> groupAnagrams(String[] strs) {
	        var map = new HashMap<String,List<String>>();
	        for(String s : strs){
	            char[] c = s.toCharArray();
	            Arrays.sort(c);
	            String key = String.valueOf(c);
	            if(!map.containsKey(key))
	                map.put(key, new ArrayList<String>());
	            map.get(key).add(s);
	        }
	        return new ArrayList(map.values());
	  }
	public static void compareMapwithKey(HashMap<Integer,Integer>m1) {
		var q = new PriorityQueue<Integer>();
		for(int key : m1.keySet()) {
			q.add(key);
		}
		while(!q.isEmpty()) {
			System.out.println(q.poll() + ", ");
		}

	}
	public static void compareMapwithValue(HashMap<Integer,Integer>m1) {
		Comparator<Integer> comparator = (a,b) -> Integer.compare(m1.get(a), m1.get(b));
		var q = new PriorityQueue<Integer>(comparator);
		for(int key : m1.keySet()) {
			q.add(key);
		}
		while(!q.isEmpty()) {
			System.out.println(q.poll() + ", ");
		}

	}
	public static void compare(int[]nums1, int[]nums2) {
	        Comparator<int[]> comparator = (a, b) -> Integer.compare(nums1[a[0]] + nums2[a[1]], nums1[b[0]] + nums2[b[1]]);
	        PriorityQueue<int[]> pq = new PriorityQueue<>(comparator);
	        for(int i = 0; i < nums1.length; i ++) {
	        	for(int j = 0; j < nums2.length; j ++) {
	        		pq.add(new int[] {i,j});
	        	}
	        }
	        while(!pq.isEmpty()) {
	        	var temp = pq.poll();
	        	System.out.println(nums1[temp[0]]+ "," + nums2[temp[1]]);
	        }
	}
	public static ArrayList<Integer> dumpNodeValue(node head) {
		var temp = head;
		var set = new HashSet<Integer>();
		var ls = new ArrayList<Integer>();
		while(temp != null) {
			if(!set.add(temp.val)) {
				ls.add(temp.val);
			}
			temp = temp.next;
		}
		return ls;
	}
	public static int largestSum(int[]a) {
		int sofar = Integer.MIN_VALUE;
		int i = 0;
		int now = 0;
		while(i < a.length) {
			now += a[i];
			sofar = Math.max(sofar, now);
			if(now < 0)
				now = 0;
			i ++;
		}
		return sofar;
	}
	public static int lengthOfLIS(int[] nums) {
        int memo[][] = new int[nums.length][nums.length];
        for (int[] l : memo) {
            Arrays.fill(l, -1);
        }
        return lengthofLIS(nums, -1, 0, memo);
    }
    public static int lengthofLIS(int[] nums, int previndex, int curpos, int[][] memo) {
        if (curpos == nums.length) {
            return 0;
        }
        if (memo[previndex + 1][curpos] >= 0) {
            return memo[previndex + 1][curpos];
        }
        int taken = 0;
        if (previndex < 0 || nums[curpos] > nums[previndex]) {
            taken = 1 + lengthofLIS(nums, curpos, curpos + 1, memo);
        }

        int nottaken = lengthofLIS(nums, previndex, curpos + 1, memo);
        System.out.println(previndex + " , " + curpos);
        memo[previndex + 1][curpos] = Math.max(taken, nottaken);
        for(int i = 0; i < memo.length; i ++) {
        	for(int j = 0; j < memo[i].length; j ++)
        		System.out.printf("%4d",memo[i][j]);
        	System.out.println("");
        }
        System.out.println("done");
        return memo[previndex + 1][curpos];
    }
    
	public static int[] longestFindArr(int[]a) {
		if(a.length == 0)
			return null;
		int[]arr = new int [a.length];
		int[]pos = new int [a.length];
		pos[0] = -1;
		Arrays.fill(arr, 1);
		for(int i = 1; i < a.length; i ++) 
			for(int j = 0; j < i; j ++) 
				if(a[j]<a[i]) 
					if(arr[i] <= arr[j]) { 
						arr[i] = arr[j]+1;
						pos[i] = j;
				    }
		
		int max = arr[0];
		int index = 0;
		for(int i = 1; i < arr.length; i ++) {
			if(arr[i] > max) {
				max = arr[i];
				index = i;
			}
		}
		var ls = new ArrayList<Integer>();
		
		while(index != -1) {
			ls.add(a[index]);
			index = pos[index];
		}
		Collections.reverse(ls);
		
		int []result = ls.stream().mapToInt(i->i).toArray();
		return result;
	}
	public static int longestLoop(int[]a) {
		if(a.length == 0)
			return 0;
		int[]arr = new int [a.length];
		Arrays.fill(arr, 1);
		for(int i = 1; i < a.length; i ++) 
			for(int j = 0; j < i; j ++) 
				if(a[j]<a[i]) 
					if(arr[i] <= arr[j]) 
						arr[i] = arr[j]+1;
		
		Arrays.sort(arr);
		return arr[arr.length-1];
	}
	public static int longestSubRec(int[]a) {
		return findLongest(a,Integer.MIN_VALUE,0);
	}
	public static int findLongest(int[]a, int pre, int curr) {
		if(curr == a.length)
			return 0;
		int take = 0;
		if(a[curr]>pre) {
			take = 1+findLongest(a,a[curr],curr+1);
		}
		int notake = findLongest(a,pre,curr+1);
		return Math.max(take, notake);
	}
	public static TreeNode merge(TreeNode t1, TreeNode t2) {
		if(t1 == null)
			return t2;
		if(t2 == null)
			return t1;
		t1.val += t2.val;
		t1.left = merge(t1.left,t2.left);
		t1.right = merge(t1.right,t2.right);
		return t1;
	}
	public static TreeNode mergeStack(TreeNode t1, TreeNode t2) {
		if(t1 == null) return t2;
		var stack = new Stack<TreeNode[]>();
		stack.push(new TreeNode[] {t1,t2});
		while(!stack.isEmpty()) {
			var temp = stack.pop();
			if(temp[1] == null)
				continue;
			temp[0].val += temp[1].val;
			if(temp[0].left == null)
				temp[0].left = temp[1].left;
			else
				stack.push(new TreeNode[] {temp[0].left, temp[1].left});
			if(temp[0].right == null)
				temp[0].left = temp[1].left;
			else
				stack.push(new TreeNode[] {temp[0].right, temp[1].right});
		}
		return t1;
	}
	public static int minDepth(TreeNode root) {
		if(root == null)
			return 0;
		return findDepth(root,1,Integer.MAX_VALUE);
	}
	public static int findDepth(TreeNode root, int level, int min) {
		if(root.left == null && root.right == null)
			return Math.min(level, min);
		int left = Integer.MAX_VALUE;
		int right = Integer.MAX_VALUE;
		if(root.left != null)
			left = findDepth(root.left,level+1,min);
		if(root.right != null)
			right = findDepth(root.right,level+1,min);
		return Math.min(left, right);
		
	}
	 public static int numIslands(char[][] grid) {
	        if(grid.length == 0)
	            return 0;
	        int island = 0;
	        boolean [][] visited = new boolean[grid.length][grid[0].length];
	       
	        for(int i = 0; i < grid.length; i ++){
	            for(int j = 0; j < grid[i].length; j ++){
	                if(grid[i][j] == '1' && !visited[i][j]){
	                    findIsland(i,j,grid,visited);
	                    island ++;
	                }
	            }
	        }
	        return island;
	    }
	    public static void findIsland(int i, int j, char[][] grid, boolean[][] visited){
	        int n = grid.length;
	        int l = grid[0].length;
	        var stack = new Stack<Pair>();
	        stack.push(new Pair(i,j));
        	visited[i][j] = true;

	        while(!stack.isEmpty()){
	            var temp = stack.pop();
	            int tempi = temp.first;
	            int tempj = temp.second;
	            
	            if(tempi+1 < n && !visited[tempi+1][tempj] && grid[tempi+1][tempj] == '1') {
	            	visited[tempi+1][tempj] = true;
	                stack.push(new Pair(tempi+1,tempj));
	            }
	            if(tempj+1 < l && !visited[tempi][tempj+1] && grid[tempi][tempj+1] == '1') {
	            	visited[tempi][tempj+1] = true;
	                stack.push(new Pair(tempi,tempj+1));
	            }
	            if(tempi-1 >= 0 && !visited[tempi-1][tempj] && grid[tempi-1][tempj] == '1') {
	            	visited[tempi-1][tempj] = true;
	                stack.push(new Pair(tempi-1,tempj));
	            }
	            if(tempj-1 >= 0 && !visited[tempi][tempj-1] && grid[tempi][tempj-1] == '1') {
	            	visited[tempi][tempj-1] = true;
	                stack.push(new Pair(tempi,tempj-1));
	            }
	        }
//	        for(int ii = 0; ii < visited.length; ii ++){
//	            for(int jj = 0; jj < visited[0].length; jj ++){
//	                System.out.print(ii + ", " + jj + ": " + visited[ii][jj] + ",");
//	            }
//	            System.out.println("");
//	        }
	    }
	public static node deleteDuplicates(node head) {
        if(head == null)
            return null;
        var write = head;
        var read = write.next;
        while(read != null && write != null){
            if(write.val == read.val){
                read = read.next;
                continue;
            }  
            write.next = read;
            write = read;
            read = read.next;
        }
        write.next = null;
        return head;
    }
	public static node deleteDuplicates2(node head) {
		var curr = head;
		while(curr != null && curr.next != null) {
			if(curr.val == curr.next.val)
				curr.next = curr.next.next;
			else
				curr = curr.next;
		}
		return head;
	}
	public static node reverseNode(node head) {
		if(head == null)
			return null;
		var stack = new Stack<node>();
		while(head != null) {
			stack.push(head);
			head = head.next;
		}
		node newHead = new node(stack.pop().val);
		node temp = newHead;
		while(!stack.isEmpty()) {
			temp.next = new node(stack.pop().val);
			temp = temp.next;
		}
		return newHead;
	}
	public static node detectCycle1(node head) {
        if(head == null)
            return null;
        node prev = head;
        node curr = head;
        while(curr.next != null){
            curr = curr.next.next;
            if(curr == null)
                return null;
            if(curr == prev)
                break;
            prev = prev.next;
        }
        if(curr == null || curr.next == null)
            return null;
        prev = head;
        while(prev != curr){
            prev = prev.next;
            curr = curr.next;
            System.out.println("prev-> " + prev.val + " curr-> " + curr.val);
        }
        return prev;
    }
    public static node detectCycle(node head) {
		  node slow = head, fast = head;
		  
		  while (fast != null && fast.next != null) {
		    slow = slow.next;
		    fast = fast.next.next;
		    
		    if (slow == fast)
		        // found the cycle
		        break;
		  }
		  
		  if (fast == null || fast.next == null)
		    // in case there is no cycle
		    return null;
		  
		  // let the slow pointer go from the head 
		  // and meet the fast pointer
		  // the meeting point is our answer
		  slow = head;
		  
		  while (slow != fast) {
		    slow = slow.next;
		    fast = fast.next;
		    System.out.println("slow ->" + slow.val + " fast ->" + fast.val );
		  }
		  
		  return slow;
		}
	public static void pNode(node n) {
		while(n != null) {
			System.out.print(n.val + " -> ");
			n = n.next;
		}
		System.out.println("");
	}
	//swap in-place, move all zeros to back
	public static void moveZeros(int[] nums) {
		int write = 0, read = 0;
		while(read < nums.length) {
			if(nums[read] != 0)
				nums[write++] = nums[read];
			read ++;
		}
		while(write < nums.length)
			nums[write++] = 0;
	}
	public static int binarySearch(int[] nums, int target) {
		int i = Arrays.binarySearch(nums, target);
		return i >= 0 ? i : -i-1;
	}
	public static void print(int []a) {
		System.out.print("[");
		for(int i = 0; i < a.length; i ++) {
			String k = i == a.length-1? a[i]+"" : a[i]+ ",";
			System.out.print(k);
		}
		System.out.println("]");

	}
	public static int numSpecialEquivGroups(String[] A) {
        Set<String> seen = new HashSet();
        for (String S: A) {
            int[] count = new int[52];
            for (int i = 0; i < S.length(); ++i) {
                count[S.charAt(i) - 'a' + 26 * (i % 2)]++;
            }
            seen.add(Arrays.toString(count));
            
            System.out.println(Arrays.toString(count));
        }
        return seen.size();
    }
	 public static boolean isPossible(int[] target) {
	        int max = 0;
	        int index = 0;
	        for(int i= 0 ;i < target.length ;i++){
	            if(max < target[i]){
	                max = target[i];
	                index = i;
	            }
	        }
	        if(max == 1)return true; // means we finally get an array full of 1, then we return true;
	        for(int i= 0;i<target.length;i++){
	            if(i == index)continue; // skip the max one's index
	            if(target[i] > max)return false; // max must be equal or bigger to any num in the array.
	            max-=target[i];// subtract the other num in the array.
	        }
	        target[index] = max; // change the current one to the new max.
	        return isPossible(target); //recursively call the function
	    }
	public static void print(int[][]a) {
		for(int i = 0; i < a.length; i++) {
			System.out.print("[");
			for(int j = 0; j < a[0].length; j ++) {
				System.out.print(a[i][j]+",");
			}
			System.out.println("],");
		}
	}
	public static void print(boolean[][]a) {
		for(int i = 0; i < a.length; i++) {
			System.out.print("[");
			for(int j = 0; j < a[0].length; j ++) {
				System.out.printf("%s", a[i][j] ? "1 " : "0 ");		
			}
			System.out.println("],");
		}
	}
	public static void sort(int[][]a) {
		Arrays.sort(a,(c,b) -> {
			return c[1] == b[1] ? c[0] - b[0] : c[1] - b[1];
		});
	}
	public static boolean circularArrayLoop(int[] nums) {
		 if(nums.length == 1)
	            return false;
			int j = 0, i = 0, step = 0,count = -1;
			while(j < nums.length) {
				count ++;
				int nextMove = nums[i];					
	            int temp;
	            if(nextMove > 0)
	                temp = (i+nextMove)%nums.length;  
	            else
	                temp = i+nextMove >= 0 ?  i+nextMove : nextMove%nums.length+nums.length + i;          
	            step += nextMove;
	              if(temp > nums.length)
	                 return false;
	            boolean sameSign = nums[temp] * nums[i] > 0 ? true : false;
	              if(temp == i && count != 0 || !sameSign  || sameSign && (temp > i && step >= nums.length && nextMove > 0) 
	            		  || (temp < i && Math.abs(step) >= nums.length && nextMove < 0)){
					j++;
					step = 0;
					i = j;
					count = -1;				
					continue;
				}  
	            if(temp == j && count != 0) 
						return true;	
	            i = temp;
			}
				return false;
	    }
	

	public static boolean isCir(int[] nums) {
		boolean isNeg = false, isPos = false;
		int j = 0, i = 0, step = 0,count = -1;
		while(j < nums.length) {
			count ++;
			int nextMove = nums[i];
			if(nextMove < 0) 
				isNeg = true;
			else
				isPos = true;
			if(isNeg && isPos) {
				j++;
				step = 0;
				i = j;
				count = -1;
				isNeg = false;
				isPos = false;
				continue;
			}
			if(isPos) {
			if((i + nextMove)%nums.length == i && count == 0) {
				j++;
				step = 0;
				i = j;
				count = -1;
				isNeg = false;
				isPos = false;
				continue;
			}
				step += nextMove;
				int temp  = (i + nextMove)%nums.length;
				if(i > j && step >= nums.length || i == temp){
					j ++;
					i = j;
					count = -1;
					step = 0;
					isNeg = false;
					isPos = false;
					continue;
				}
				i = temp;
				if(i == j && count != 0) 
					return true;
			}else {
				step += nextMove;
				int temp = i+nextMove >= 0 ?  i+nextMove : ((nums[i]%nums.length)+nums.length) + i;
				if(temp == i && count == 0) {
					j++;
					step = 0;
					i = j;
					count = -1;
					isNeg = false;
					isPos = false;
					continue;
				}
				i = temp;
				if(i > j && Math.abs(step) >= nums.length || i == temp){
					j ++;
					i = j;
					count = -1;
					step = 0;
					isNeg = false;
					isPos = false;
					continue;
				}
				if(i == j && count != 0) 
					return true;
			}
		}
			return false;
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
	     public static boolean isVowel(char c){
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
	      /*Given a string, you need to reverse the order of characters in each word within 
	       * a sentence while still preserving whitespace and initial word order.
		Example 1:
		Input: "Let's take LeetCode contest"
		Output: "s'teL ekat edoCteeL tsetnoc"*/
	      public static String reverseWords(String s) {
	          StringBuilder str = new StringBuilder();
	          char[] strArr = s.toCharArray();
	          int i = s.indexOf(" ");
	          int j = 0;  
	          while(j < s.length()){   
	              int last = i == -1 ? s.length()-1 : i-1;
	              while(j <= last){
	                  char temp = strArr[j];
	                  strArr[j++] = strArr[last];
	                  strArr[last--] = temp;
	              }
	              j = i == -1 ? s.length() : i+1;
	              i = s.indexOf(" ", i+1);
	          }
	          return new String(strArr);
	      }
	      public static String reverseWordsUsingBuildingClass(String s) {
	          String []arr = s.split(" ");
	          StringBuilder str = new StringBuilder();
	          
	          for(String temp : arr){
	              str.append(new StringBuilder(temp).reverse());
	              str.append(" ");
	          }
	          return str.toString().trim();
	      }
	      /*You need to construct a string consists of parenthesis and integers from a 
	       * binary tree with the preorder traversing way.
		The null node needs to be represented by empty parenthesis pair "()". And you need 
		to omit all 
		the empty parenthesis pairs that don't affect the one-to-one mapping relationship between 
		the string and the original binary tree.
		Example 1:
		Input: Binary tree: [1,2,3,4]
		       1
		     /   \
		    2     3
		   /    
		  4     
		Output: "1(2(4))(3)"
		
		Explanation: Originallay it needs to be "1(2(4)())(3()())", 
		but you need to omit all the unnecessary empty parenthesis pairs. 
		And it will be "1(2(4))(3)".
		Example 2:
		Input: Binary tree: [1,2,3,null,4]
		       1
		     /   \
		    2     3
		     \  
		      4 
		
		Output: "1(2()(4))(3)"
		
		Explanation: Almost the same as the first example, 
		except we can't omit the first parenthesis pair to break the one-to-one mapping relationship 
		between the input and the output.*/
	      public static String tree2str(TreeNode t) {
	          if(t == null)
	              return "";
	          return treeRec(t, t.left, t.right);
	      }
	      public static String treeRec(TreeNode t, TreeNode l, TreeNode r){
	          if(l == null && r == null)
	              return t.val+ "";
	          String left = "", right = "";
	          if(t.left == null && t.right != null){
	              left = "";
	              right = treeRec(t.right,t.right.left,t.right.right);
	          }

	          else if(t.left != null && t.right == null){
	              left = treeRec(t.left,t.left.left,t.left.right);
	              right = "";
	          }
	          else {
	              left = treeRec(t.left,t.left.left,t.left.right);
	              right = treeRec(t.right,t.right.left,t.right.right);
	          }
	          return right == "" ? t.val + "(" + left + ")" : t.val + "(" + left + ")" + "(" + right + ")";
	      }
	      public static String tree2strIterate(TreeNode t) {
	          if(t == null)
	              return "";
	          var stack = new Stack<TreeNode>();
	          StringBuilder result = new StringBuilder();
	          var visited = new HashSet<TreeNode>();
	          stack.push(t);
	          while(!stack.isEmpty()){
	              var temp = stack.peek();
	              if(visited.contains(temp)){
	                  result.append(")");
	                  stack.pop();
	                  continue;
	              }
	              result.append("("+temp.val);
	              visited.add(temp);
	              if(temp.left == null && temp.right != null)
	                  result.append("()");
	              if(temp.right != null)
	                   stack.push(temp.right);
	              if(temp.left != null)
	                   stack.push(temp.left);    
	          }
	          return result.toString().substring(1,result.toString().length()-1);
	      }
	      public static String tree2strRec(TreeNode t) {
	          if(t == null)
	              return "";
	          if(t.left == null && t.right == null)
	              return t.val+"";
	          else if(t.left == null && t.right != null){
	              return  t.val + "()" + "(" + tree2str(t.right)+")";
	          }
	          else if(t.right != null){
	              return t.val + "("+ tree2str(t.left) + ")" + "(" +  tree2str(t.right) + ")";
	          }
	          else{
	              return t.val + "("+ tree2str(t.left) + ")";
	          }
	      }
	      /*X is a good number if after rotating each digit individually by 180 degrees, 
	       * we get a valid number that is different from X.  Each digit must be rotated - 
	       * we cannot choose to leave it alone.
		A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to 
		themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of 
		the numbers do not rotate to any other number and become invalid.
		Now given a positive number N, how many numbers X from 1 to N are good?
		Example:
		Input: 10
		Output: 4
		Explanation: 
		There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
		Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.*/
	      public static int rotatedDigits(int N) {
	          int count = 0;
	           for(int i = 1; i <= N; i ++){
	               int j = i;
	               boolean contains2569 = false, contains374 = false;
	               while(j != 0){
	                   int mol = j%10;
	                   if(mol == 2 || mol == 5 || mol == 6 || mol == 9)
	                       contains2569 = true;
	                   if(mol == 3 || mol == 7 || mol == 4)
	                       contains374 = true;
	                   j = j/10;
	               }
	               if(contains2569 && !contains374)
	                       count ++;
	           }
	           return count;
	       }
	      /*A sentence S is given, composed of words separated by spaces. Each word consists of 
	       * lowercase and uppercase letters only.
		We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)
		The rules of Goat Latin are as follows:
		If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
		For example, the word 'apple' becomes 'applema'.
		If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it 
		to the end, then add "ma".
		For example, the word "goat" becomes "oatgma".
		Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
		For example, the first word gets "a" added to the end, the second word gets "aa" added to the 
		end and so on.
		Return the final sentence representing the conversion from S to Goat Latin. 
		
		Example 1:
		
		Input: "I speak Goat Latin"
		Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
		Example 2:
		
		Input: "The quick brown fox jumped over the lazy dog"
		Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa 
		azylmaaaaaaaaa ogdmaaaaaaaaaa"
		
		Notes:
		
		S contains only uppercase, lowercase and spaces. Exactly one space between each word.
		1 <= S.length <= 150.*/
	      public static String toGoatLatin(String S) {
	          String[] arry = S.split(" ");
	          String a = "";
	          for(int i = 0; i < arry.length; i ++){
	              a += "a";
	              if(!isVowel(arry[i].charAt(0))){
	                  arry[i] = arry[i].substring(1,arry[i].length()) + arry[i].charAt(0);
	              }
	              arry[i] += "ma" + a;
	          }
	          String result = "";
	          for(String s : arry){
	              result += s+ " ";
	          }
	          return result.trim();
	      }
	      /*You have an array of logs.  Each log is a space delimited 
	       * string of words.
		For each log, the first word in each log is an alphanumeric identifier.
		  Then, either:
		Each word after the identifier will consist only of lowercase letters, or;
		Each word after the identifier will consist only of digits.
		We will call these two varieties of logs letter-logs and digit-logs.  
		It is guaranteed that each log has at least one word after its 
		identifier.
		Reorder the logs so that all of the letter-logs come before any 
		digit-log.  The letter-logs are ordered lexicographically ignoring 
		identifier, with the identifier used in case of ties.  The digit-logs 
		should be put in their original order.
		
		Return the final order of the logs.
		Example 1:
		
		Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit
		 dig","let3 art zero"]
		Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 
		1 5 1","dig2 3 6"]
		 
		
		Constraints:
		
		0 <= logs.length <= 100
		3 <= logs[i].length <= 100
		logs[i] is guaranteed to have an identifier, and a word after the 
		identifier.*/
	     public static String[] reorderLogFiles(String[] logs) {
	          Arrays.sort(logs,(log1,log2) ->{
	              String[] sp1 = log1.split(" ",2);
	              String[] sp2 = log2.split(" ",2);
	              boolean isDigit1 = Character.isDigit(sp1[1].charAt(0));
	              boolean isDigit2 = Character.isDigit(sp2[1].charAt(0));
	              if(!isDigit1 && !isDigit2){
	                  int com = sp1[1].compareToIgnoreCase(sp2[1]);
	                  if(com != 0) return com;
	                  return sp1[0].compareToIgnoreCase(sp2[0]);
	              }
	              return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
	          });
	              return logs;
	       }
	     /*Given a paragraph and a list of banned words, return the most frequent 
	      * word that is not in the list of banned words.  It is guaranteed there 
	      * is at least one word that isn't banned, and that the answer is unique.
		Words in the list of banned words are given in lowercase, and free of 
		punctuation.  Words in the paragraph are not case sensitive.  
		The answer is in lowercase.
		Example:
		
		Input: 
		paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
		banned = ["hit"]
		Output: "ball"
		Explanation: 
		"hit" occurs 3 times, but it is a banned word.
		"ball" occurs twice (and no other word does), so it is the most frequent 
		non-banned word in the paragraph. 
		Note that words in the paragraph are not case sensitive,
		that punctuation is ignored (even if adjacent to words, such as "ball,"), 
		and that "hit" isn't the answer even though it occurs more because it is 
		banned.
		Note:
		
		1 <= paragraph.length <= 1000.
		0 <= banned.length <= 100.
		1 <= banned[i].length <= 10.
		The answer is unique, and written in lowercase (even if its occurrences 
		in paragraph may have uppercase symbols, and even if it is a proper noun.)
		paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
		There are no hyphens or hyphenated words.
		Words only consist of letters, never apostrophes or other punctuation symbols.*/
	     public static String mostCommonWord(String paragraph, String[] banned) {
	         paragraph = paragraph.toLowerCase();
	         paragraph += ".";
	         char[] str = paragraph.toCharArray();
	         var map = new HashMap<String,Integer>();
	         var set = new HashSet<String>(Arrays.asList(banned));
	         var temp = new StringBuilder();
	         var max = 0;
	         var ans = "";
	         for(int i = 0; i < str.length; i ++){
	             if(Character.isLetter(str[i])){
	                 temp.append(str[i]);
	             }
	             else{
	                 if(temp.length() != 0){
	                     String newstr = temp.toString();
	                     if(!set.contains(newstr)){
	                         map.put(newstr, map.getOrDefault(newstr,0)+1);
	                         if(max < map.get(newstr)){
	                             ans = newstr;
	                             max = map.get(newstr);
	                         }
	                     }
	                 }
	                 temp.setLength(0);
	             }
	         }
	         return ans;
	     }
	     /*Given a string S, return the "reversed" string where all 
	      * characters that are not a letter stay in the same place, and all 
	      * letters reverse their positions.
		Example 1:
		Input: "ab-cd"
		Output: "dc-ba"
		Example 2:
		
		Input: "a-bC-dEf-ghIj"
		Output: "j-Ih-gfE-dCba"
		Example 3:
		
		Input: "Test1ng-Leet=code-Q!"
		Output: "Qedo1ct-eeLg=ntse-T!"
		Note:
		
		S.length <= 100
		33 <= S[i].ASCIIcode <= 122 
		S doesn't contain \ or "*/
	     public static String reverseOnlyLetters(String S) {
	         var stack = new Stack<Character>();
	         for(char c : S.toCharArray()){
	             if(Character.isLetter(c))
	                 stack.push(c);
	         }
	         var st = new StringBuilder();
	         for(char c : S.toCharArray()){
	             if(Character.isLetter(c))
	                 st.append(stack.pop());
	             else
	                 st.append(c);
	         }
	         return st.toString();
	     }
	     public static String reverseOnlyLettersPointers(String S) {
	         char[] arr = S.toCharArray();
	         for(int i = 0, j = arr.length-1; i <= j && i < arr.length && j >= 0;){
	             boolean iIsLetter = Character.isLetter(arr[i]);
	             boolean jIsLetter = Character.isLetter(arr[j]);
	             if(!iIsLetter)
	                 i++;
	             if(!jIsLetter)
	                 j--;
	             if(iIsLetter && jIsLetter){
	                 char c = arr[i];
	                 arr[i++] = arr[j];
	                 arr[j--] = c;
	             }
	         }
	         return new String(arr);
	     }
	     /*Given a string text, you want to use the characters of text to form as 
	      * many instances of the word "balloon" as possible.

		You can use each character in text at most once. Return the maximum number 
		of instances that can be formed.
		Example 1:
		Input: text = "nlaebolko"
		Output: 1
		Example 2:
		Input: text = "loonbalxballpoon"
		Output: 2
		Example 3:
		Input: text = "leetcode"
		Output: 0*/
	     
	     public static int maxNumberOfBalloons(String text) {
	         int[] arr = new int[26];
	         for(char c : text.toCharArray()){
	             arr[c-97] += 1;
	         }
	        
	         int min = Integer.MAX_VALUE;
	         if(arr['b'-97] == 0 || arr['a'-97] == 0 || arr['l'-97] == 0 
	            || arr['o'-97] == 0 || arr['n'-97] == 0)
	             return 0;
	         min = Math.min(min, arr['b'-97]);
	         min = Math.min(min, arr['a'-97]);
	         min = Math.min(min, arr['n'-97]);
	         min = Math.min(min, arr['l'-97]/2);
	         min = Math.min(min, arr['o'-97]/2);
	         return min;
	     }
	     /*Given an array of characters, compress it in-place.
		The length after compression must always be smaller than or equal to the original array.
		Every element of the array should be a character (not int) of length 1.
		After you are done modifying the input array in-place, return the new length of the array.
		Follow up:
		Could you solve it using only O(1) extra space?
		Example 1:
		
		Input:
		["a","a","b","b","c","c","c"]
		
		Output:
		Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
		
		Explanation:
		"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
		 
		
		Example 2:
		
		Input:
		["a"]
		
		Output:
		Return 1, and the first 1 characters of the input array should be: ["a"]
		
		Explanation:
		Nothing is replaced.
		Example 3:
		
		Input:
		["a","b","b","b","b","b","b","b","b","b","b","b","b"]
		
		Output:
		Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
		
		Explanation:
		Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
		Notice each digit has it's own entry in the array.*/
	     public static int compress(char[] chars) {
	         int read = 1, write = 0, count = 1;
	         while(read < chars.length){
	             if(chars[read] == chars[read-1])
	                 count ++;
	             else{
	                 if(count > 1){
	                     String s = count + "";
	                     for(char c : s.toCharArray()){
	                         chars[++write] = c;   
	                     }
	                 }
	                 chars[++write] = chars[read];
	                 count = 1;
	             }
	             read ++;
	         }
	         if(count > 1){
	             String s = count + "";
	             for(char c : s.toCharArray()){
	                 chars[++write] = c;   
	             }
	         }
	         return ++write;
	     }
	     /*Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.

Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.

 

Example 1:

Input: queries = ["cbd"], words = ["zaaaz"]
Output: [1]
Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
Example 2:

Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
Output: [1,2]
Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
 

Constraints:

1 <= queries.length <= 2000
1 <= words.length <= 2000
1 <= queries[i].length, words[i].length <= 10
queries[i][j], words[i][j] are English lowercase letters.*/
	     public static int[] numSmallerByFrequency(String[] queries, String[] words) {
	         int[] q1 = new int[queries.length];
	         int[] w1 = new int[words.length];
	         var ans = new ArrayList<Integer>();
	         int index = 0;
	         for(String s : queries){
	             int[] arry = new int[26];
	             int min = Integer.MAX_VALUE;
	             for(char c : s.toCharArray()){
	                 arry[c-'a'] += 1;
	                 min = Math.min(min,c-'a');
	             }
	             q1[index++] = arry[min];
	         } 
	         index = 0;
	         for(String s : words){
	             int[] arry = new int[26];
	             int min = Integer.MAX_VALUE;
	             for(char c : s.toCharArray()){
	                 arry[c-'a'] += 1;
	                 min = Math.min(min,c-'a');
	             }
	             w1[index++] = arry[min];
	         }      
	         Arrays.sort(w1);       
	         for(int i : q1){
	             System.out.println(i);
	             int count = 0;
	             for(int j = w1.length-1; j >= 0; j --)
	                 if(w1[j] > i)
	                     count ++;
	             ans.add(count);
	         }
	         return ans.stream().mapToInt(i->i).toArray();
	     }
	     public static int numUniqueEmails(String[] emails) {
	         var set = new HashSet<String>();
	        for(String s : emails){
	            String[] arr = s.split("@");
	            int index = arr[0].indexOf('+');
	            if(index != -1)
	                arr[0] = arr[0].substring(0,index);
	            String[] dot = arr[0].split("\\.");
	            StringBuilder st = new StringBuilder();
	            for(String d : dot)
	                st.append(d);
	    
	            st.append("@"+arr[1]);
	            set.add(st.toString());
	        }
	        return set.size();	      
	    }
	     public static boolean buddyStrings(String A, String B) {
	         if(A.length() != B.length()) return false;
	           int first = -1, second = -1;
	          if(A.equals(B)){
	              int[] count = new int[26];
	              for(char c : A.toCharArray()){
	                  count[c-'a'] ++;
	                  if(count[c-'a'] >= 2)
	                      return true;
	              }
	              return false;
	          }else{
	              for(int i = 0; i < A.length(); i ++){
	                  if(A.charAt(i) != B.charAt(i)){
	                      if(first == -1)
	                          first = i;
	                      else if(second == -1)
	                          second = i;
	                      else
	                          return false;
	                  }
	              }
	          }
	          return (second != -1) && A.charAt(first) == B.charAt(second) &&
	                  A.charAt(second) == B.charAt(first);
	      }   
	     
}
class TreeNode {
	   int val;
	   TreeNode left;
	   TreeNode right;
	   TreeNode(int x) { val = x; }
 }
class Student implements Comparable{
	String name;
	int age;
	Student(String name, int age){
		this.name = name;
		this.age = age;
	}
	@Override
	public int compareTo(Object o) {
		Student s = (Student)o;
		if(age > s.age)
			return 0;
			
		return 0;
	}
	public String toString() {
		return "name: " + name + "  age:" + age + "\n";
	}
}
class kthLargest{
	PriorityQueue<Integer> p;
	int K;
	public kthLargest(int k, int[] nums) {
		p = new PriorityQueue<Integer>();
		for(int i : nums) {
			p.offer(i);
			if(p.size() > k) p.poll();
		}
		K = k;
	}
	public int add(int val) {
		p.offer(val);
		if(p.size() > K) p.poll();
		return p.peek();
	}
}
class node{
	int val;
	node next;
	node(int val){
		this.val = val;
		next = null;
	}
}
//class TreeNode{
//	int val;
//	TreeNode left;
//	TreeNode right;
//	TreeNode(int val){
//		this.val = val;
//		left = null;
//		right = null;
//	}
//}
class Pair{
    int first;
    int second;
    Pair(int first, int second){
        this.first = first;
        this.second = second;
    }
}
