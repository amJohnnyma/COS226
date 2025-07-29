public class PattersonLock {
    private final TreeNode[] leafNodes; // leaf nodes correspond to threads
    private final TreeNode root;

    public PattersonLock(int n) {
        leafNodes = new TreeNode[n];
        root = buildTree(0, n - 1);
        assignLeaves(root);
    }

    private TreeNode buildTree(int start, int end) {
        if (start == end) {
            return new TreeNode(start);  // leaf node with threadId = start
        }
        TreeNode node = new TreeNode(-1); // internal node has no threadId
        int mid = (start + end) / 2;
        node.left = buildTree(start, mid);
        node.right = buildTree(mid + 1, end);
        return node;
    }
     private void assignLeaves(TreeNode node) {
        if (node.left == null && node.right == null) {
            leafNodes[node.nodeId] = node;
            return;
        }
        if (node.left != null) assignLeaves(node.left);
        if (node.right != null) assignLeaves(node.right);
    }

    public void lock(int threadId) {
        // path from leaf to root
        lockRec(root, threadId);
    }
    private boolean lockRec(TreeNode node, int threadId) {
        //TODO: ImplementFunction
	return true;
    }

    public void unlock(int threadId) {
        unlockRec(root, threadId);
    }
    private boolean unlockRec(TreeNode node, int threadId) {
        //TODO: ImplementFunction
	return true;
    }

    private boolean containsThread(TreeNode node, int threadId) {
        if (node == null) return false;
        if (node.left == null && node.right == null) {
            return node.nodeId == threadId;
        }
        return containsThread(node.left, threadId) || containsThread(node.right, threadId);
    }
}
