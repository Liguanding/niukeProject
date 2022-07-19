package interview.leetcode;

import interview.utils.TreeNode;
import interview.utils.TreeUtil;

public class Solution236 {

    public static void main(String[] args) {
        Integer[] nums = new Integer[]{3,5,1,6,2,0,8,null,null,7,4};
        TreeNode root = TreeUtil.createTree(nums);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root,TreeNode p,TreeNode q){
        if(root == null){
            return null;
        }
        if(root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left != null && right != null){
            return root;
        }
        return left == null ? right : left;
    }
}
