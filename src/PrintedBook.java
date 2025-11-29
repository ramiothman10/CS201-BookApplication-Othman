import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class PrintedBook extends Book implements BookInterface
{
    //private fields
    private int numPages;
    private static ArrayList<PrintedBook> printedList = new ArrayList<>();

    //nondefault constructor to create PrintedBook object
    public PrintedBook(String title, String author, String genre, double cost, int pages) {
        //uses inheritance to call Book constructor
        super(title, author, genre, (pages * 10));
        numPages = pages;
    }
    //dummy constructor for main class to use PrintedBook methods
    public PrintedBook()
    {}
    //user input to filter user info and collect only printedBooks
    public void importPrinted(String file) throws FileNotFoundException {
        try{
                File inputFile = new File(file);
                Scanner in = new Scanner(inputFile);
                in.nextLine();
                while (in.hasNextLine()) {
                    String[] line = in.nextLine().split(",");
                    // indexes: 0:title, 1:author, 2:genre, 3:cost, 4:length, 5:pages, 6:type
                    if (line[6].equals("printedBook")) {
                        printedList.add(new PrintedBook(line[0], line[1], line[2], 10 * Integer.parseInt(line[5]), Integer.parseInt(line[5])));
                    }
                }
        }
        catch(FileNotFoundException e) {
            System.out.println("Error: File was not found");
        }

        }
    //getter method for private field
        public int getNumPages() {
        return numPages;
    }
    //computes average number of pages per book
    public int averagePages() {
        if(printedList.isEmpty()) {
            return -1;
        }
       else {
            int x = 0, total = 0;
            for (PrintedBook printedBook : printedList) {
                total += printedBook.getNumPages();
                x++;
            }
            return (total / x);
        }

    }

    @Override
    //overrides abstract method to calculate the number of printed books for each genre
    public String numPerGenre()
    {
        if(printedList.isEmpty()) {
            return "There are no printed books in the list";
        }
        else{
            ArrayList<String> genres = new ArrayList<>();
            for (PrintedBook printedBook : printedList) {
                String add = printedBook.getGenre().toLowerCase();
                if (!genres.contains(add)) {
                    genres.add(add);
                }
            }
            int[] amounts = new int[genres.size()];
            for (int i = 0; i < genres.size(); i++) {
                for (int j = 0; j < printedList.size(); j++) {
                    if (printedList.get(j).getGenre().toLowerCase().equals(genres.get(i))) {
                        amounts[i]++;
                    }
                }
            }
            String textBlock = "";
            for (int i = 0; i < genres.size(); i++) {
                textBlock += genres.get(i) + ": " + amounts[i] + "\n";
            }
            return textBlock;
        }
    }

    @Override
    //overrides abstract method to calculate total cost of all printedBooks
    public double getTotalCost()
    {
        double total = 0;
        for (PrintedBook printedBook: printedList)
        {
            total += 10 * printedBook.getNumPages();
        }
    return total;
    }

    @Override
    public double getCost()
    {
        return cost;
    }

    @Override
    //toString() method for displaying and file writing
    public String toString()
    {
        return super.toString() + (getNumPages() * 10) + "," + "N/A" +"," + getNumPages()  +"," + "printedBook,";
    }

    //displays the information of last 3 printedBooks using toString()
    public void displayLast3Printed()
    {
        int x = printedList.size();
        if (printedList.isEmpty()) {
            System.out.println("There are no printed books in the list");
        }
        else if (x == 1) {
            System.out.println("There is only 1 printed book in the list");
        }
        else if(x == 2) {
            System.out.println("There are only 2 printed books in the list");
        }
        else
        {
            System.out.println("title,author,genre,cost,length,pages,booktype,");
            for (int i = x-3; i < x; i++)
            {
                System.out.println(printedList.get(i).toString());
            }
        }
    }
    //static method to add to the printedList from the main class
    public static void addPrinted(PrintedBook printed) throws IOException {
        printedList.add(printed);
        Book.add(printed);
    }
}
