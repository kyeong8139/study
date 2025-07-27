import java.io.*;
import java.util.*;

class Main {

	static class Word {
		String data;
		int cnt;

		public Word(String data, int cnt) {
			this.data = data;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Map<String, Word> map = new HashMap<>();
		Map<String, Integer> cnts = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			String data = br.readLine();
			int cnt = cnts.getOrDefault(data, 0) + 1;
			cnts.put(data, cnt);
		}

		int m = Integer.parseInt(br.readLine());
		String[] targets = new String[m];
		for (int i = 0; i < m; i++) {
			String target = br.readLine();
			map.put(target, null);
			targets[i] = target;
		}

		for (String data : cnts.keySet()) {
			int cnt = cnts.get(data);
			for (int end = 1; end <= data.length(); end++) {
				String key = data.substring(0, end);
				if (!map.containsKey(key)) continue;
				Word prev = map.get(key);
					
				if (prev == null || prev.cnt < cnt || (prev.cnt == cnt && prev.data.compareTo(data) >= 0 )) {
					map.put(key, new Word(data, cnt));		
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (String target : targets) {
			Word answer = map.get(target);
			
			if (answer != null) {
				sb.append(answer.data).append(" ").append(answer.cnt).append("\n");
			} else {
				sb.append("0\n");
			}
		}
		System.out.print(sb);
	}

}