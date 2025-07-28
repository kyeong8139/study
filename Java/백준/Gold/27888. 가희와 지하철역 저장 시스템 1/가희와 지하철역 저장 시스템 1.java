import java.io.*;
import java.util.*;

public class Main {

    public static Map<String, Integer> featureOfStation = new HashMap<>();
    public static Map<String, Integer> featureIds = new HashMap<>();
    public static Map<Integer, Integer> featureCnts = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String station = br.readLine();
            featureOfStation.put(station, 0);   
        }
        featureCnts.put(-1, 0);

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "U":
                    String station = st.nextToken();
                    String[] features = st.nextToken().split(",");
                    deleteFeature(station);
                    insertFeature(station, features);
                    break;
                case "G":
                    int key = 0;
                    features = st.nextToken().split(",");
                    for (String feature : features) {
                        int featureId = featureIds.getOrDefault(feature, -1);
                        if (featureId == -1) {
                            key = -1;
                            break;
                        } 

                        key |= 1 << featureId;
                    }
                    int answer = featureCnts.getOrDefault(key, 0);
                    sb.append(answer).append("\n");
                    break;
            }
        }
        System.out.print(sb);
    }

    public static void deleteFeature(String station) {
        int features = featureOfStation.get(station);
        if (features != 0) {
            updateFeature(features, 0, 0, false);
        }
    }

    public static void insertFeature(String station, String[] featureArr) {
        int features = 0;
        for (String featureStr : featureArr) {
            if (!featureIds.containsKey(featureStr)) {
                featureIds.put(featureStr, featureIds.size());
            }
            int id = featureIds.get(featureStr);
            features |= (1 << id);
        }
        
        featureOfStation.put(station, features);
        updateFeature(features, 0, 0, true);
    }

    private static void updateFeature(int features, int depth, int cur, boolean isInsert) {
        if (depth == featureIds.size()) {
            int cnt = 0;
            if (isInsert) {
                cnt = featureCnts.getOrDefault(cur, 0) + 1; 
            } else {
                cnt = featureCnts.get(cur) - 1;
            }
            featureCnts.put(cur, cnt);
            return;
        }

        updateFeature(features, depth + 1, cur, isInsert);
        if ((features & (1 << depth)) != 0) {
            int next = (cur | (1 << depth));
            updateFeature(features, depth + 1, next, isInsert);
        }
    }
}