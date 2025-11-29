import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Book implements BookInterface
{
    //private fields
    private String title;
    private String author;
    private String genre;
    protected double cost;
    private static ArrayList<Book> bookList = new ArrayList<>();

    //nondefault constructor to create Book objects
    public Book(String title, String author, String genre, double cost)
    {
        this.title = title;
        this.author = author;
        this.genre = genre;

    }
    //dummy method for main class to use methods
    public Book(){}

    //user input to import a file containing book information
    public static void importFile(String input) throws FileNotFoundException
    {
        File inputFile = new File(input);
        Scanner in = new Scanner(inputFile);
        in.nextLine();
        while (in.hasNextLine())
        {
            String[] line = in.nextLine().split(",");
            // indexes: 0:title, 1:author, 2:genre, 3:cost, 4:length, 5:pages, 6:type
            if (line[6].equals("printedBook"))
            {
                bookList.add(new PrintedBook(line[0], line[1], line[2], Double.parseDouble(line[3]), Integer.parseInt(line[5])));

            }
            else if (line[6].equals("audioBook"))
            {
                bookList.add(new AudioBook(line[0], line[1], line[2], Double.parseDouble(line[3]), Double.parseDouble(line[4])));

            }
        }
    }

    //abstract methods to be implemented
    public abstract double getTotalCost();
    public abstract double getCost();

    //getter methods for private fields
    public String getGenre() {
        return genre;
    }
    public String getAuthor()
    {
        return author;
    }
    public String getTitle()
    {
        return title;
    }

    //Provides the BookInterface access to the bookList
    public static ArrayList<Book> getBookList()
    {
        return bookList;
    }


    @Override
    //toString() method for displaying and file writing
    public String toString() {
        return  getTitle() + "," + getAuthor() + "," + getGenre() + ",";
    }

    //Exports bookList to a file using toString() method
    public static void exportToFile(String fileName) throws IOException {
        if (bookList.isEmpty()) {
            System.out.println("Invalid, the book list is empty");
        }
        else if(fileName.isEmpty()) {
            System.out.println("Please enter a valid file name");
        }
       else {
            PrintWriter writer = new PrintWriter(fileName);
            writer.println("title,author,genre,cost,length,pages,booktype,");
            for (Book book : bookList) {
                writer.println(book.toString());
            }
            System.out.println("File successfully exported as " + fileName);
            writer.close();
        }
    }
    //static method to add to the bookList from subclasses
    public static void add(Book book) {
        bookList.add(book);
    }
}
