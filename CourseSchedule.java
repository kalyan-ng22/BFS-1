// Time Complexity : O(V+E).
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : Yes
// Approach : We first build a hashmap to represent which courses depend on which prerequisites, and an indegrees array to count how many prerequisites each course has.
//Next, we enqueue all courses with zero indegree (no prerequisites) — these can be taken first.
//Using BFS, we repeatedly dequeue a course, reduce the indegree of its dependent courses, and enqueue them once their indegree becomes zero.
//If we process all numCourses this way, it means all courses can be completed and no cycle can be formed.

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        int[] indegrees = new int[numCourses];
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for(int[] pr : prerequisites){  // pr[0] is dependent courses, pr[1] is independent courses

            indegrees[pr[0]]++; //construct indegrees array
            map.putIfAbsent(pr[1], new ArrayList<>()); //construct hashmap
            map.get(pr[1]).add(pr[0]);
        }

        int count = 0;
        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < numCourses; i++){
            if(indegrees[i] == 0){ //add indegrees elements to queue
                q.add(i);
                count++;
            }
        }

        if(q.isEmpty()) return false;
        if(count == numCourses) return true;

        //BFS technique with queue
        while(!q.isEmpty()){
            int curr = q.poll();
            List<Integer> dependencies = map.get(curr);
            if(dependencies != null){
                for(int dependent : dependencies){
                    indegrees[dependent]--;
                    if(indegrees[dependent] == 0){
                        q.add(dependent);
                        count++;
                        if(count == numCourses) return true;
                    }
                }
            }
        }

        return false;
    }
}