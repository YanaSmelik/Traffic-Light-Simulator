package traffic.program;

import java.util.ArrayList;
import java.util.List;

public class RoadQueue {
    private final Road[] queue;
    private final int capacity;
    private int front;
    private int rare;

    public RoadQueue(int capacity) {
        front = 0;
        rare = capacity - 1;
        this.capacity = capacity;
        this.queue = new Road[capacity];
    }

    public boolean enqueue(Road road) {
        if(isFull()) {
            System.out.println("Queue is full");
            return false;
        }
        rare = (rare + 1) % capacity;
        queue[rare] = road;
        return true;
    }

    public Road dequeue() {
        if(isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        Road deletedRoad = queue[front];
        queue[front] = null;
        front = (front + 1) % capacity;
        return deletedRoad;
    }

    public List<Road> printElements(){
        List<Road> result = new ArrayList<>();
        if(front == rare) {
            if(queue[front] != null) {
                result.add(queue[front]);
            }
        } else if(front < rare) {
                for (int i = front; i <= rare; i++) {
                    if(queue[i] != null) {
                        result.add(queue[i]);
                    }
                }
        } else {
            int index = front;
            while(index < capacity) {
                if(queue[index] != null) {
                    result.add(queue[index]);
                }
                index++;
            }
            index = 0;
            while (index <= rare) {
                if(queue[index] != null) {
                    result.add(queue[index]);
                }
                index++;
            }
        }
        return result;
    }

    private boolean isFull() {
        int nextIndex = capacity == 1 ? 0 : (rare + 1) % capacity;

        return queue[nextIndex] != null;
    }

    private boolean isEmpty() {
        int index = capacity == 1 ? 0 : front;

        return queue[index] == null;
    }
}
