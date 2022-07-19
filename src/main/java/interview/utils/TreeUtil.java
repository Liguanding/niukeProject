package interview.utils;

import java.util.*;

public class TreeUtil {

    public static TreeNode createTree(Integer[] nums){
        if(nums.length == 0){
            return null;
        }
        TreeNode head = new TreeNode(nums[0]);
        LinkedList<TreeNode> subTree = new LinkedList<>();
        subTree.push(head);
        for(int i = 1;i < nums.length;++i){
            if(!subTree.isEmpty()){
                TreeNode cur = subTree.pop();
                if(nums[i] != null){
                    cur.left = new TreeNode(nums[i]);
                    subTree.add(cur.left);
                }
                i++;
                if(i >= nums.length){
                    break;
                }
                if(nums[i] != null){
                    cur.right = new TreeNode(nums[i]);
                    subTree.add(cur.right);
                }
            }else{
                break;
            }
        }
        return head;
    }

    public static void preOrederPrint(TreeNode root){
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        preOrederPrint(root.left);
        preOrederPrint(root.right);
    }

    public  static void levelOrder(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        List<List<TreeNode>> res = new ArrayList<>();
        if(root == null){
            return;
        }
        queue.offerLast(root);
        int layer = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            layer++;
            List<TreeNode> list = new ArrayList<>();
            for(int i = 0; i < size;++i){
                TreeNode cur = queue.pollFirst();
                list.add(cur);
                if(cur.left != null){
                    queue.offerLast(cur.left);
                }
                if(cur.right != null){
                    queue.offerLast(cur.right);
                }
            }
            res.add(list);
        }
        for (List<TreeNode> re : res) {
            for (TreeNode treeNode : re) {
                System.out.print(treeNode.val + " ");
            }
            System.out.println();
        }

    }
}