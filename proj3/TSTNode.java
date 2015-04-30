
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
        private void setData(char data) {
            this.data = data;
        }  
        private void setEnd(boolean end) {
            this.isEnd = end;
        }   
        private void setLeft(TSTNode left) {
            this.left = left;
        }   
        private void setRight(TSTNode right) {
            this.right = right;
        }   
        private void setMiddle(char data) {
            this.middle = middle;
        }    
        private void setMax(double max) {
            this.max = max;
        }      
    }