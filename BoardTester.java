public class BoardTester {

    public static void main(String[] args) {
        Board board1 = new Board();
        board1.set(0, 0, 1);
        board1.set(0, 1, 2);
        System.out.println("This should be true: " + board1.validValue(0, 2, 3));
        System.out.println("This should be false: " + board1.validValue(0, 2, 1));
        System.out.println("This should be false: " + board1.validSolution());

        Board board2 = new Board();
        boolean result = board2.read("test3.txt");
        if (!result) {
            System.out.println("Failure to load test3.txt");
        } else {
            System.out.println("This should be true: " + board2.validSolution());
        }
    }
}