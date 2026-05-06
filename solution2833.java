public class solution2833 {
    public int furthestDistanceFromOrigin(String moves) {
        int left = 0, right = 0, blank = 0;

        for (char ch : moves.toCharArray()) {
            switch (ch) {
                case 'L' -> left++;
                case 'R' -> right++;
                case '_' -> blank++;
            }
        }

        int pos1 = right - (left + blank);

        int pos2 = (right + blank) - left;

        return Math.max(Math.abs(pos1), Math.abs(pos2));
    }
}