package interview.Tencent;

import interview.utils.TreeNode;
import interview.utils.TreeUtil;

import java.util.*;

public class Main {

    public static Map<TreeNode,TreeNode> parent = new HashMap<>();

    public static void main(String[] args) {

        TreeNode tree = TreeUtil.createTree(new Integer[]{1,2,3, 4, 5, null, 6,7,8,null,9});
        TreeNode tree1 = TreeUtil.createTree(new Integer[]{1,2,3, 4, 5});
        TreeUtil.levelOrder(tree);
        System.out.println();
        System.out.println("_____________________");
        deleteNodes(tree,new int[]{3,4});
    }
    public static void dfs(TreeNode root) {
        //初始化parent
        if (root.left != null) {
            parent.put(root.left, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right, root);
            dfs(root.right);
        }
    }


    public static ArrayList<TreeNode> deleteNodes(TreeNode root,int[] layers){
        dfs(root);
        List<TreeNode> deleted = delete(root, layers); //deleted
        // 这个就是删除后剩余树的根节点
        return null;
    }

    public static List<TreeNode> delete(TreeNode root,int[] layers){
        //删除某一层，返回删除这一层后所剩下的所有子树的根节点
        List<TreeNode> res = new ArrayList<>();
        int idx = 0,curDeleteLayer = layers[idx];
        if(curDeleteLayer != 1){
            //如果不是删除root，第一层，把root加进去
            res.add(root);
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        int curLayer = 0;
        while(!deque.isEmpty()){
            int size = deque.size();
            curLayer++;
            if(curLayer == curDeleteLayer){
                for(int i = 0;i < size;++i){
                    //要把这一层给删除了
                    TreeNode curNode = deque.poll();
//                    TreeNode father = parent.get(curNode); // 找到其父亲节点，等待删除
////                    parent.remove(curNode);
//                    if(father != null){
//                        //有可能删除第一层，root没有father
//                        father.left = null;
//                        father.right = null;
//                    }
                    if(curNode.left != null){
                        //如果连着两层被删，不应该添加进结果
                        if(idx + 1 < layers.length && curLayer + 1 == layers[idx + 1]){
                            //下层也要被删
                            deque.offerLast(curNode.left);
                        }else {
                            res.add(curNode.left);
                            deque.offerLast(curNode.left);
                        }
                    }
                    if(curNode.right != null){
                        //如果连着两层被删，不应该添加进结果
                        if(idx + 1 < layers.length && curLayer + 1 == layers[idx + 1]){
                            //下层也要被删
                            deque.offerLast(curNode.right);
                        }else {
                            res.add(curNode.right);
                            deque.offerLast(curNode.right);
                        }
                    }
                    curNode = null;
                }
                if(idx == layers.length-1)
                    return res;
                else
                    curDeleteLayer = layers[++idx];
            }else{
                for(int i = 0; i < size;++i){
                    TreeNode cur = deque.pollFirst();
                    if(cur.left != null){
                        deque.offerLast(cur.left);
                    }
                    if(cur.right != null){
                        deque.offerLast(cur.right);
                    }
                }
            }
        }
        return res;

    }


    public static List<TreeNode> delete(TreeNode root,int layers){
        //删除某一层，返回删除这一层后所剩下的所有子树的根节点
        List<TreeNode> res = new ArrayList<>();
        if(layers == 1){
            if(root.left != null){
                res.add(root.left);
            }
            if(root.right != null){
                res.add(root.right);
            }
            return res;
        }
        res.add(root);
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        int curLayer = 0;
        while(!deque.isEmpty()){
            int size = deque.size();
            curLayer++;
            if(curLayer == layers){
                for(int i = 0;i < size;++i){
                    //要把这一层给删除了
                    //root肯定是有的，已经添加了，只用添加这层的孩子节点
                    TreeNode curNode = deque.poll();
                    TreeNode father = parent.get(curNode); // 找到其父亲节点，等待删除
                    parent.remove(curNode);
                    father.left = null;
                    father.right = null;
                    if(curNode.left != null){
                        res.add(curNode.left);
                    }
                    if(curNode.right != null){
                        res.add(curNode.right);
                    }
                }
                return res;
            }
            for(int i = 0; i < size;++i){
                TreeNode cur = deque.pollFirst();
                if(cur.left != null){
                    deque.offerLast(cur.left);
                }
                if(cur.right != null){
                    deque.offerLast(cur.right);
                }
            }
        }
        return res;

    }



    public static List<TreeNode> levelOrder(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        List<TreeNode> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        queue.offerLast(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size;++i){
                TreeNode cur = queue.pollFirst();
                res.add(cur);
                if(cur.left != null){
                    queue.offerLast(cur.left);
                }
                if(cur.right != null){
                    queue.offerLast(cur.right);
                }
            }
        }
        return res;
    }

}
