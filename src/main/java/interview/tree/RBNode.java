package interview.tree;

public class RBNode implements Comparable<RBNode>{

    /**There is only 2 colors, so we can use boolean type to represent them**/
    public static final boolean RED = true;
    public static final boolean BLACK = false;
    /**Now that we define them as public, we don't have to add setters and getters**/
    public String value;
    public RBNode left;
    public RBNode right;
    public RBNode parent;
    public boolean color;

    @Override
    public int compareTo(RBNode o) {
        if(this.value == o.value) {
            return 0;
        }
        return this.value.hashCode()>o.value.hashCode()?1:-1;
    }
    public RBNode(String value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = RED;
    }
    @Override
    public String toString() {
        return "value = " + value + ", " + (this.color==true?"RED":"BLACK");
    }
}