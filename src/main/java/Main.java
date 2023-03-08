import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean shouldCountinue=true;

        Books books = new Books();

        while (shouldCountinue){
            System.out.println("Select option:");
            System.out.println("1. Show books");
            System.out.println("2. add book");
            System.out.println("3. delete book");
            System.out.println("4. Exit");
            System.out.println(" ");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1 -> books.displayBooks();
                case 2 -> books.addBook();
                case 3 -> books.removeBook();
                case 4 -> shouldCountinue=false;
            }
        }
    }
}
