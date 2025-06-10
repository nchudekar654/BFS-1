//Time Complexity: O(V + E)
//Space Complexity: O(V + E)

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for(int[] edge: prerequisites){         //O(E)
            int out = edge[1]; //independent 
            int in = edge[0]; //dependent
            indegrees[in]++;
            if(!map.containsKey(out)){
                map.put(out, new ArrayList<>());
            }
            map.get(out).add(in);
        }
        int count=0;
        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i< numCourses; i++){       //O(V)
            if(indegrees[i] == 0){
                q.add(i);
                count++;
            }
        }
        
        while(!q.isEmpty()){
            int curr = q.poll();
            List<Integer> children = map.get(curr);
            if(children != null){
                for(int child: children){
                indegrees[child]--;
                if(indegrees[child] == 0){
                    q.add(child);
                    count++;
                }
            }
        }

        }
            
        if(count == numCourses){
            return true;
        }
        return false;
    }
}