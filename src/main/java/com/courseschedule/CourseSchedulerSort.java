
package com.courseschedule;

import java.util.*;

public class CourseSchedulerSort {
    
	public int[] findOrder(int numCourses, int[][] prerequisites) {
	    @SuppressWarnings("unchecked")
	    List<Integer>[] graph = (List<Integer>[]) new ArrayList[numCourses];
	    int[] inDegree = new int[numCourses];
	    List<Integer> order = new ArrayList<>();

	    for (int i = 0; i < numCourses; i++) {
	        graph[i] = new ArrayList<>();
	    }

	    // Build adjacency list and in-degree count
	    for (int[] pre : prerequisites) {
	        graph[pre[1]].add(pre[0]);
	        inDegree[pre[0]]++;
	    }

	    // Queue for BFS
	    Queue<Integer> queue = new LinkedList<>();
	    for (int i = 0; i < numCourses; i++) {
	        if (inDegree[i] == 0) {
	            queue.offer(i);
	        }
	    }

	    while (!queue.isEmpty()) {
	        int course = queue.poll();
	        order.add(course);
	        for (int next : graph[course]) {
	            inDegree[next]--;
	            if (inDegree[next] == 0) {
	                queue.offer(next);
	            }
	        }
	    }

	    // If all courses are completed, return order; otherwise, return empty array
	    return order.size() == numCourses ? order.stream().mapToInt(i -> i).toArray() : new int[0];

	}
}

