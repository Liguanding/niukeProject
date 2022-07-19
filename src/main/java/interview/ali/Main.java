package interview.ali;
import java.util.*;


public class Main {
	
	public static Map<Character,Integer> map = new HashMap<>();

//	class Word implements Comparable


	public static void main(String[] args) {
		map.put('1',1);
		map.put('2',2);
		map.put('3',3);
		map.put('4',4);
		map.put('5',5);
		map.put('6',6);
		map.put('7',7);
		map.put('8',8);
		map.put('9',9);
		map.put('A',10);
		map.put('B',11);
		map.put('C',12);
		map.put('D',13);
		map.put('E',14);
		map.put('F',15);
		
		Scanner sc = new Scanner(System.in);
//		while(sc.hasNext()) {
//			String str = sc.nextLine();
//			solution(str);
//		}
		solution("11");
//		addForString("")
	}
	
	public static void solution(String str) {

		//Set<String> set = new HashSet<>();\
		Set<Long> set = new HashSet<>();
		List<Long> list =new ArrayList<>();
		for(int i = 2;i <= 16;++i) {
			//String res = transTo(str,i);
			Long res1 = transTo(str,i);
			if(res1 != null && !set.contains(res1)) {
				set.add(res1);
				list.add(res1);
				//System.out.println(res1);
			}
		}
		Collections.sort(list);
		for(int i = 0;i < list.size();++i){
			if(i != list.size() - 1){
				System.out.println(list.get(i));
			}else{
				System.out.print(list.get(i));
			}
		}

	}
	
	public static Long transTo(String str,int tmp) {
		StringBuilder sb = new StringBuilder();
		for(char c : str.toCharArray()) {
			if(map.get(c) >= tmp) {
				return null;
			}
		}
		int len = str.length();
		String res = "0";
		long res1 = 0;
		for(int i = 0;i < len;++i) {
			int idxT = (int)Math.pow(tmp,i);
			res1 += (map.get(str.charAt(len - i - 1)) * idxT);
			if(res1 > 1000000007) {
				res1 %= 1000000007;
				//res1 = (long) ((long)res1 % (1000000007));
			}
		}
			
		return res1;
	}
	
	public static String addForString(String str1,String str2) {
		StringBuilder sb = new StringBuilder();
		int i = str1.length()-1,j = str2.length()-1;
		int k = 0;
		while(i >= 0 || j >= 0) {
			int a = i >= 0 ? str1.charAt(i) - '0' : 0;
			int b = j >= 0 ? str2.charAt(j) - '0' : 0;
			int c = (a + b + k) % 10;
			sb.append(c);
			k = (a + b + k) / 10;
			i--;
			j--;
		}
		if(k != 0) {
			sb.append(k);
		}
		return sb.reverse().toString();
	}
}
