import java.io.*;
import java.util.*;

public class Main {

    static class Task implements Comparable<Task>{
        int id;
        int spentTime;
        int waitTime;
        int prevTaskCnt;

        public Task(int id, int spentTime, int prevTaskCnt) {
            this.id = id;
            this.spentTime = spentTime;
            this.prevTaskCnt = prevTaskCnt;
        }

        @Override
        public int compareTo(Task o) {
            return this.prevTaskCnt - o.prevTaskCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Integer>[] nextTasks = new List[n+1];
        for (int i = 1; i <= n; i++) {
            nextTasks[i] = new ArrayList<>();
        }
        
        Task[] tasks = new Task[n+1];
        Queue<Task> taskQueue = new ArrayDeque<>(); 
        for (int id = 1; id <= n; id++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int prevTaskCnt = Integer.parseInt(st.nextToken());
            
            Task task = new Task(id, time, prevTaskCnt);
            tasks[id] = task;

            if (prevTaskCnt == 0) taskQueue.add(task);
            for (int i = 0; i < prevTaskCnt; i++) {
                int prevId = Integer.parseInt(st.nextToken());
                nextTasks[prevId].add(id);
            }
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            Task cur = tasks[i];
            int endTime = cur.spentTime + cur.waitTime;
            result = Math.max(endTime, result);

            for (int nextTaskId : nextTasks[cur.id]) {
                Task next = tasks[nextTaskId];
                next.waitTime = Math.max(next.waitTime, endTime);
            }
        }

        System.out.println(result);
    }

}