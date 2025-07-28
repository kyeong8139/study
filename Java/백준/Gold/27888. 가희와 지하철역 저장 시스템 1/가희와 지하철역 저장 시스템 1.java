import java.io.*;
import java.util.*;

public class Main {

    public static Map<String, Integer> featureOfStation = new HashMap<>();
    public static Map<String, Integer> featureIds = new HashMap<>();
    public static Map<Integer, Integer> featureCnts = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String station = br.readLine();
            featureOfStation.put(station, 0);   
        }
    
        initFeatureCnts(0, 0);
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
                        if (!featureIds.containsKey(feature)) {
                            key = -1;
                            break;
                        }

                        int featureId = featureIds.get(feature);
                        key |= 1 << featureId;
                    }
                    int answer = featureCnts.get(key);
                    bw.write(String.valueOf(answer));
                    bw.newLine();
                    break;
            }
        }
        bw.flush();
        bw.close();
    }

    public static void initFeatureCnts(int depth, int cur) {
        if (depth == 9) {
            featureCnts.put(cur, 0);
            return;
        }
        
        initFeatureCnts(depth + 1, cur);
        initFeatureCnts(depth + 1, cur | (1 << depth));
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
                cnt = featureCnts.get(cur) + 1; 
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