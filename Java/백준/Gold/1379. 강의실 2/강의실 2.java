import java.io.*;
import java.util.*;

public class Main {

    static class Lesson implements Comparable<Lesson>{
        int id;
        int start;
        int end;

        public Lesson (int id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Lesson o) {
            if (this.start == o.start) {
                return this.end - o.end;
            }
            return this.start - o.start;
        }
    }

    static class Room implements Comparable<Room> {
        int id;
        int endOfLastLesson = 0;

        public Room(int id) {
            this.id = id;
        }

        @Override
        public int compareTo(Room o) {
            if (this.endOfLastLesson == o.endOfLastLesson) {
                return this.id - o.id;
            }
            return this.endOfLastLesson - o.endOfLastLesson;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        PriorityQueue<Lesson> lessons = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int lessonId = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            Lesson lesson = new Lesson(lessonId, start, end);
            lessons.add(lesson);
        }

        int[] roomIds = new int[n+1]; // idx : Lesson.id 
        PriorityQueue<Room> rooms = new PriorityQueue<>();
        int roomId = 1;
        while (!lessons.isEmpty()) {
            Lesson cur = lessons.poll();

            if (rooms.isEmpty() || rooms.peek().endOfLastLesson > cur.start) {
                Room newRoom = new Room(roomId++);
                newRoom.endOfLastLesson = cur.end;
                roomIds[cur.id] = newRoom.id;
                rooms.add(newRoom);
            } else {
                Room target = rooms.poll();
                target.endOfLastLesson = cur.end;
                roomIds[cur.id] = target.id;
                rooms.add(target);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(rooms.size()).append("\n");
        for (int lessonId = 1; lessonId <= n; lessonId++) {
            sb.append(roomIds[lessonId]).append("\n");
        }
        System.out.print(sb);
    }
}