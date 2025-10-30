// Time Complexity : O(n).
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Approach : I have used BFS approach to solve this with the help of queue as I can achieve FIFO. We add nodes to the queue and maintain size to determine
// the level. And we remove node from queue, for each node we reduce the size and do this until complete level is removed. Along woth this we add the corresponding
// left and right values to the queue as well and continue the process.

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>(); //initialize queue
        queue.add(root); //add root to queue
        while(!queue.isEmpty()){
            int size = queue.size(); //maintain size for each level
            List<Integer> temp = new ArrayList<>(); //temp List to store each level
            while(size > 0){
                TreeNode curr = queue.poll(); //remove one root
                temp.add(curr.val);
                if(curr.left != null){ //add corresponding left
                    queue.add(curr.left);
                }
                if(curr.right != null){ //add corresponding right
                    queue.add(curr.right);
                }
                size--; //reduce size as we poll each node
            }
            result.add(temp); // add the temp list to final result List
        }
        return result;
    }
}