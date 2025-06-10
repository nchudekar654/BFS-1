//Time Complexity: O(n)
//Space Complexity: O(n)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

//Level order traversal using BFS
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null)
            return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> lis = new ArrayList<>();
            for(int i=0; i< size; i++){
                TreeNode curr = q.poll();
                lis.add(curr.val);
                if(curr.left != null){
                    q.add(curr.left);

                }
                if(curr.right != null){
                    q.add(curr.right);
                    
                }
            }
            result.add(lis);
        }

        return result;
    }
}

// Level order traversal using DFS

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(root, 0, result);
        return result;
    }

    private void helper(TreeNode root, int level, List<List<Integer>> result){
        //base
        if(root == null)
            return;
        //logic
        if(level == result.size()){
            result.add(new ArrayList<>());
        }
        List<Integer> lis = result.get(level);
        lis.add(root.val);
        result.set(level, lis);
        level++;

        helper(root.left, level, result);
        helper(root.right, level, result);
    }
}

//Pass by reference

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        helper(root, 0, result);
        return result;
    }

    private void helper(TreeNode root, int level, List<List<Integer>> result){
        //base
        if(root == null)
            return;
        //logic
        if(level == result.size()){
            result.add(new ArrayList<>());
        }
        //List<Integer> lis = result.get(level);
        //lis.add(root.val);
        //result.set(level, lis);
        //level++;

        result.get(level).add(root.val);
        helper(root.left, level+1, result);
        helper(root.right, level+1, result);
    }
}