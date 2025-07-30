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
	
	if(node==null || (node.left == null && node.right == null))
	{
		return false;
	}

	if(containsThread(node.left, threadId))
	{
		lockRec(node.left, threadId);
		node.lock.lock(0);
		return true;
	}else if (containsThread(node.right, threadId))
	{
		lockRec(node.right, threadId);
		node.lock.lock(1);
		return true;
	}

	return false;
    }


    public void unlock(int threadId) {
        unlockRec(root, threadId);
    }
    private boolean unlockRec(TreeNode node, int threadId) {
        //TODO: ImplementFunction
	if(node==null || (node.left == null && node.right == null))
	{
		return false;
	}

	if(containsThread(node.left, threadId))
	{
		unlockRec(node.left, threadId);
		node.lock.unlock(0);
		return true;
	}else if (containsThread(node.right, threadId))
	{
		unlockRec(node.right, threadId);
		node.lock.unlock(1);
		return true;
	}

	return false;
    }

    private boolean containsThread(TreeNode node, int threadId) {
        if (node == null) return false;
        if (node.left == null && node.right == null) {
            return node.nodeId == threadId;
        }
        return containsThread(node.left, threadId) || containsThread(node.right, threadId);
    }
    private TreeNode getParent(TreeNode node, TreeNode child)
    {
	    if(node == null || node.left == null && node.right == null) {return null;}
	    if (node.left == child || node.right == child) {return node;}

	    TreeNode left = getParent(node.left, child);
	    if(left != null) return left;
	    return getParent(node.right,child);
    }
}
