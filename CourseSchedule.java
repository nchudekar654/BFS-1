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

//Backtrack + DP Solution (Memo Solution) - Maintaing path and visited arrays
//Tc: O(V+E)

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        boolean [] path = new boolean[numCourses];
        boolean [] visited = new boolean[numCourses];
        for(int[] edge: prerequisites){
            int in = edge[1];
            int dep = edge[0];
            if(!map.containsKey(in)){
                map.put(in, new ArrayList<>());
            }
            map.get(in).add(dep);
        }
        for(int i=0; i < numCourses; i++){
            if(!visited[i] && hasCycle(i, map, path, visited))
                return false;
        }
        return true;

    }

    public boolean hasCycle(int i, HashMap<Integer, List<Integer>> map, boolean [] path, boolean [] visited){
        //base
        if(visited[i])
            return false;
        if(path[i])
            return true;

        //logic
        List<Integer> edges = map.get(i);
        if(edges != null){
            //action
            path[i] = true;
            //recurse
            for(int edge : edges){
                if(hasCycle(edge, map, path, visited)){
                    return true;
                }
            }
        //backtrack
        path[i] = false;

        }
        
        //memo
        visited[i] = true;
        return false;
    }
}