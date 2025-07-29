

public class TreeNode {
    public PetersonLock lock = new PetersonLock();
    public TreeNode left, right;
    public int nodeId; 

    public TreeNode(int nodeId) {
        this.nodeId = nodeId;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
