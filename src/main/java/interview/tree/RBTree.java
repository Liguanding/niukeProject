package interview.tree;

public class RBTree {

    /**root of the tree**/
    private RBNode root;

    /**insert operations**/
    public void insert(String value) {
        RBNode node = new RBNode(value);
        RBNode temp = this.root;
        if(temp == null){
            root = node;
        }
        while(temp != null){
            if(node.compareTo(temp) > 0){
                if(temp.right != null) {
                    temp = temp.right;
                }else {
                    temp.right = node;
                    node.parent = temp;
                }
            }else if(node.compareTo(temp) < 0) { // node.value < temp.value
                if(temp.left != null) {
                    temp = temp.left;
                }else {
                    temp.left = node;
                    node.parent = temp;
                }
            }else {  // node.value = temp.value, cover the original node;
                /**It's strange, but the idea is as this**/
                node.left = temp.left;
                node.right = temp.right;
                node.color = temp.color;
                if(temp.parent != null) {
                    RBNode parent = temp.parent;
                    node.parent = parent;
                    if(temp.equals(parent.left)) {
                        parent.left = node;
                    }else if (temp.equals(parent.right)) {
                        parent.right = node;
                    }
                }
            }
        }
        renovate(node);
    }
    /**delete operations**/
    public void delete(String value) {
        RBNode temp = this.root;
        RBNode node = new RBNode(value);
        while(temp != null){
            if(temp.compareTo(node) == 1) {
                temp = temp.right;
            }else if(temp.compareTo(node) == -1) {
                temp = temp.left;
            }else {
                /**1. delete node without child**/
                if(temp.left == null && temp.right == null) {
                    temp = null;
                    break;
                }
                /**2. delete node only has left child**/
                if(temp.left != null && temp.right == null) {
                    if(temp.compareTo(this.root) == 0) {
                        this.root = temp.left;
                        renovateD(this.root);
                        break;
                    }
                    RBNode rbNode = temp.left;
                    temp.left.parent = temp.parent;
                    if(temp.parent.left.compareTo(temp) == 0) {
                        temp.parent.left = temp.left;
                    }else if(temp.parent.right.compareTo(temp) == 0) {
                        temp.parent.right = temp.left;
                    }
                    temp = null;
                    renovateD(rbNode);
                    break;
                }
                /**3. delete node only has right child**/
                if(temp.left == null && temp.right != null) {
                    if(temp.compareTo(this.root) == 0) {
                        this.root = temp.right;
                        renovateD(this.root);
                        break;
                    }
                    RBNode rbNode= temp.right;
                    temp.right.parent = temp.parent;
                    if(temp.parent.left.compareTo(temp) == 0) {
                        temp.parent.left = temp.right;
                    }else if(temp.parent.right.compareTo(temp) == 0) {
                        temp.parent.right = temp.right;
                    }
                    temp = null;
                    renovateD(rbNode);
                    break;
                }
                /**4. delete node has 2 children**/
                if(temp.left != null && temp.right != null) {
                    /**find the smallest node in the right children of the temp, swap temp and the node, delete temp**/
                    RBNode right = temp.right;
                    while(right.left !=null) {
                        right = right.left;
                    }
                    temp.value = right.value;
                    right = null;
                    renovateD(temp);
                    break;
                }
            }
        }

    }

    private void renovateD(RBNode root) {
    }

    /**other operations**/
    private void leftRotate(RBNode curNode) {
        RBNode right = curNode.right;
        RBNode parent = curNode.parent;

        if (parent != null && parent.left.compareTo(curNode) == 0) {
            parent.left = right;
            RBNode rl = right.left;
            right.parent = parent;
            right.left = curNode;
            curNode.parent = right;
            curNode.right = rl;
            if (rl != null)
                rl.parent = curNode;
        } else if (parent != null && parent.right.compareTo(curNode) == 0) {
            parent.right = right;
            RBNode rl = right.left;
            right.parent = parent;
            right.left = curNode;
            curNode.parent = right;
            curNode.right = rl;
            if (rl != null)
                rl.parent = curNode;
        } else if (parent == null) {
            this.root = right;
            right.parent = null;
            RBNode rl = right.left;
            right.left = curNode;
            curNode.parent = right;
            curNode.right = rl;
            if (rl != null)
                rl.parent = curNode;
        }
    }
    /**
     * @param curNode
     *
     *         |						|
     *       parent                   parent
     *         |						|
     *      curNode       --->    	   left
     *      /     \                    /  \
     *    left    right               ll   curNode
     *    /  \                              /    \
     *   ll  lr                            lr    right
     */
    private void rightRotate(RBNode curNode) {
        RBNode parent = curNode.parent;
        RBNode left = curNode.left;

        if(parent != null && parent.left.compareTo(curNode) == 0) {
            parent.left = left;
            left.parent = parent;
            RBNode lr = left.right;
            curNode.parent = left;
            left.right = curNode;
            curNode.left = lr;
            if(lr != null)
                lr.parent = curNode;
        }else if(parent != null && parent.right.compareTo(curNode) == 0) {
            parent.right = left;
            left.parent = parent;
            RBNode lr = left.right;
            curNode.parent = left;
            left.right = curNode;
            curNode.left = lr;
            if(lr != null)
                lr.parent = curNode;
        }else if(parent == null) {
            this.root = left;
            left.parent = null;
            RBNode lr = left.right;
            curNode.parent = left;
            left.right = curNode;
            curNode.left = lr;
            if(lr != null)
                lr.parent = curNode;
        }
    }
    private void renovate(RBNode curNode) {
        /**1. leaf, black**/
        if(curNode == null){
            return;
        }
        RBNode parent = curNode.parent;
        /**2. root is black**/
        if(parent == null){
            curNode.color = RBNode.BLACK;
        }
        /**3. if parent is red**/
        if(parent != null && parent.color == RBNode.RED){
            RBNode grandParent = parent.parent;
            if(grandParent != null){
                /**
                 * 3.1 Uncle is red, parent is red
                 *     Method: (1) print uncle and parent black
                 *             (2) grand parent is curNode and loop 3
                 */
                if(grandParent.left != null && grandParent.right != null){
                    //有叔叔节点
                    if(grandParent.left.color == RBNode.RED && grandParent.right.color == RBNode.RED){
                        //父亲和叔叔都是红色的
                        grandParent.left.color = RBNode.BLACK;
                        grandParent.right.color = RBNode.BLACK;
                        renovate(grandParent);
                    }
                }
                /**
                 * 3.2 uncle non-exists or black, parent is leftChild of grandParent
                 * 	   Method: (1) curNode is left child, print parent black, print grandparent red,
                 * 				   rightRotate(grandparent)
                 * 			   (2) curNode is right child, leftRotate(parent), we got 3.2.(1),
                 *                 parent is curNode and loop 3
                 */
                /**
                 * 3.3 uncle non-exists or black, parent is rightChild of grandParent
                 * 	   Method: (1) curNode is right child, print parent black, print grandparent red,
                 * 				   leftRotate(grandparent)
                 * 			   (2) curNode is left child, rightRotate(parent). we got 3.3.(1)
                 * 				   parent is curNode and loop 3
                 */
                if(grandParent.left == null || (grandParent.left != null && grandParent.left.color == RBNode.RED)
                    || grandParent.right == null || (grandParent.right != null && grandParent.right.color == RBNode.RED)){
                    //无叔叔节点或者叔叔节点是黑色的,
                    if(parent.compareTo(grandParent.left) == 0){
                        //并且 父节点是祖父节点的左孩子
                        if(curNode.compareTo(parent.left) == 0){
                            //如果插入节点是父节点的左孩子
                            parent.color = RBNode.BLACK;
                            grandParent.color = RBNode.RED;
                            rightRotate(grandParent);
                        }else if(curNode.compareTo(parent.right) == 0){
                            //如果插入节点是父节点的有孩子
                            leftRotate(parent);
                            renovate(parent);
                        }
                    }else if(parent.compareTo(grandParent.right) == 0){
                        if(curNode.compareTo(parent.right) == 0){
                            parent.color = RBNode.BLACK;
                            grandParent.color = RBNode.RED;
                            leftRotate(grandParent);
                        }else if(curNode.compareTo(parent.left) == 0){
                            rightRotate(parent);
                            renovate(parent);
                        }
                    }

                }

            }
        }

    }
    /**show operations**/
    public void inOrder() {
        inOrder(this.root);
    }

    private void inOrder(RBNode rootNode) {
        if(rootNode != null) {
            inOrder(rootNode);
            System.out.println(rootNode);
            inOrder(rootNode);
        }
    }
}
