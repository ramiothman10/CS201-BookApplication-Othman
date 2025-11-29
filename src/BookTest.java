import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookTest
{
    public static void main(String[] args) throws FileNotFoundException {
        //User menu
        String textBlock = """
                =======================
                      BOOK APPLICATION
                =======================
                0 - import file
                1 - add book
                2 - display number of books per genre
                3 - average length of audiobooks
                4 - average pages of printed books
                5 - get total cost
                6 - display last 6 books
                7 - display last 3 printed books
                8 - display last 3 audio books
                9 - export to file
                10 - exit
                ------------------------
                Enter a number for which action you want to do: """;


        Scanner in = new Scanner(System.in);
        //dummy objects to call subclass methods
        PrintedBook printedDummy = new PrintedBook();
        AudioBook audioDummy = new AudioBook();
        //decimal formatter for formatting
        DecimalFormat fmt = new DecimalFormat("$###,###.00");
        boolean flag = true;
        boolean bool = true;
        //Looped user interface
        while (bool)
        {

            try{
                System.out.print(textBlock + " ");
                int input = Integer.parseInt(in.nextLine());
                switch (input)
                {
                    case (0) ->
                    {
                        //Takes file input and imports to super and subclasses to add books to their respective lists
                        try {
                            System.out.print("Import file name: ");
                            String file = in.nextLine();
                            Book.importFile(file);
                            printedDummy.importPrinted(file);
                            audioDummy.importAudio(file);
                            System.out.println("File imported successfully.");
                        } catch (FileNotFoundException e) {
                            System.out.println("Error: file not found.");
                        }
                    }
                    case (1) ->
                    {
                        //Manual feature to add books through user input, catching exceptions where applicable
                        try {
                            System.out.println("Would you like to add a printed (p) or audio (a) book?");
                            char add = in.nextLine().charAt(0);

                            if (add == 'p' || add == 'P')
                            {
                                int pages = -1;

                                System.out.print("Enter title: ");
                                String title = in.nextLine();
                                while(title.isEmpty()) {
                                    System.out.print("Please enter a valid title: ");
                                    title = in.nextLine();
                                }
                                title = "\"" + title + "\"";
                                System.out.print("Enter author: ");
                                String author = in.nextLine();
                                while(author.isEmpty()) {
                                    System.out.print("Please enter a valid author: ");
                                    author = in.nextLine();
                                }
                                System.out.print("Enter genre: ");
                                String genre = in.nextLine();
                                while(genre.isEmpty()) {
                                    System.out.print("Please enter a valid genre: ");
                                    genre = in.nextLine();
                                }
                                boolean mismatch = false;
                                try{
                                    System.out.print("Enter pages: ");
                                    pages = in.nextInt();
                                } catch(InputMismatchException e) {
                                    System.out.println("Invalid input for pages");
                                    mismatch = true;
                                }
                                while(pages <= 0 && !mismatch) {
                                    System.out.println("Invalid, please reenter a positive nonzero value for page number: ");
                                    try {
                                        pages = in.nextInt();
                                    }
                                    catch(InputMismatchException e) {
                                        System.out.println("Invalid input for pages");
                                        break;
                                    }
                                }
                                in.nextLine();
                                if (pages > 0) {
                                    PrintedBook printed = new PrintedBook(title, author, genre, pages * 10, pages);
                                    PrintedBook.addPrinted(printed);
                                    System.out.println("Printed book added successfully");
                                }


                            } else if (add == 'a' || add == 'A') {
                                    double length = -1;
                                    System.out.print("Enter title: ");
                                    String title = in.nextLine();
                                    while(title.isEmpty()) {
                                        System.out.print("Please enter a valid title: ");
                                        title = in.nextLine();
                                    }
                                    title = "\"" + title + "\"";
                                    System.out.print("Enter author: ");
                                    String author = in.nextLine();
                                    while(author.isEmpty()) {
                                        System.out.print("Please enter a valid author: ");
                                        author = in.nextLine();
                                    }
                                    System.out.print("Enter genre: ");
                                    String genre = in.nextLine();
                                    while(genre.isEmpty()) {
                                        System.out.print("Please enter a valid genre: ");
                                        genre = in.nextLine();
                                    }
                                boolean mismatch = false;
                                try{
                                    System.out.print("Enter length in minutes: ");
                                     length = in.nextDouble();
                                } catch(InputMismatchException e) {
                                    System.out.println("Invalid input for length");
                                    mismatch = true;
                                }
                                while(length <= 0 && !mismatch) {
                                    System.out.println("Invalid, please enter a positive nonzero value for length: ");
                                    try {
                                        length = in.nextDouble();
                                    }
                                    catch(InputMismatchException e) {
                                        System.out.println("Invalid input for length");
                                        break;
                                    }
                                }
                                    in.nextLine();
                                if(length > 0){
                                    AudioBook audio = new AudioBook(title, author, genre, length * 5, length);
                                    AudioBook.addAudio(audio);
                                    System.out.println("Audiobook added successfully");
                                }

                            } else {
                                System.out.println("Invalid input, please input 'p' or 'a'");
                            }
                        } catch (InputMismatchException e) {
                                System.out.println("Invalid input, please input 'p' or 'a'");
                        }
                        catch(RuntimeException | IOException e) {
                            System.out.println("Error: Invalid input");
                        }
                    }
                    case (2) -> {
                        //Calculates the amount of books per each genre, for printed or audio as specified by the user
                        System.out.println("Would you like the genres for printed (p) or audio (a)?");
                        try {
                            char genre = in.nextLine().charAt(0);
                            if (genre == 'p' || genre == 'P')
                                System.out.println(printedDummy.numPerGenre());
                            else if (genre == 'a' || genre == 'A')
                                System.out.println(audioDummy.numPerGenre());
                            else
                                System.out.println("Invalid input, please enter 'p' or 'a'");
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input, please enter 'p' or 'a'");
                        }
                        catch(RuntimeException e) {
                            System.out.println("Invalid input, please enter 'p' or 'a'");
                        }
                    }
                    case (3) -> {
                        //Calls subclass method to calculate the average length of audiobooks
                        double average = audioDummy.averageLength();
                        if (average == -1) {
                            System.out.println("There are no audiobooks in the list");
                        }
                        else {
                            System.out.printf("The average length of the audiobooks is: %.2f minutes \n", average);
                        }

                    }

                    case (4) -> {
                        //Calls subclass method to calculate average number of pages for printed books
                        int average = printedDummy.averagePages();
                        if (average == -1) {
                            System.out.println("There are no printed books in the list");
                        } else {
                            System.out.println("The average pages of the printed books is: " + average + " pages");
                        }

                    }

                    case (5) -> {
                        double total = audioDummy.getTotalCost() + printedDummy.getTotalCost();
                        if(total == 0) {
                            System.out.println("The list is empty");
                        }
                      else  {
                            System.out.println("The total cost of all books is " + fmt.format(total));
                        }
                    }
                    case (6) -> {
                        audioDummy.getLast6Books();
                    }
                    case (7) -> {
                        printedDummy.displayLast3Printed();
                    }
                    case (8) -> {
                        audioDummy.displayLast3Audio();
                    }
                    case (9) -> {
                        try {
                            System.out.println("Enter name of file to be created: ");
                            String fileName = in.nextLine();
                            try {
                                Book.exportToFile(fileName);
                            }
                            catch(IOException e) {
                                System.out.println("Error occurred: " + e.getMessage());
                            }
                        }
                        catch(InputMismatchException e) {
                            System.out.println("Please do not input integer or floating point values for the file name");
                        }
                    }
                    case(10) -> {
                        System.out.println("---thank you! program shutting---");
                        bool = false;
                        flag = false;
                        break;
                    }

                    default ->
                        System.out.println("Invalid input, please enter a digit 0-10");

                }
            }
            catch(NumberFormatException e) {
                System.out.println("Invalid input, please enter a digit 0-10");
        }
            if(flag){
                try {
                    System.out.print("do you want to continue? (Y/N): ");
                    char answer = in.nextLine().charAt(0);
                    if (answer == 'y' || answer == 'Y') {
                        continue;
                    } else {
                        System.out.println("---thank you! program shutting---");
                        bool = false;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("---thank you! program shutting---");
                    bool = false;
                }
                catch(StringIndexOutOfBoundsException e) {
                    System.out.println("---thank you! program shutting---");
                    bool = false;
                }
            }
        }
    }
}

