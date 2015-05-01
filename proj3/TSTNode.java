public class TSTNode {
        char data;
        String word;
        boolean isEnd;
        TSTNode left, middle, right;
        double max, val;

        public TSTNode(char data) {
            this.data = data;
            this.isEnd = false;
            this.word = "";
            this.left = null;
            this.middle = null;
            this.right = null;
            this.max = 0;
            this.val = -1;
        }    
}
