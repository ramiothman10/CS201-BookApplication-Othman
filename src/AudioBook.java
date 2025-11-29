import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AudioBook extends Book implements BookInterface
{
    //private fields
    private double length;
    private static ArrayList<AudioBook> audioList = new ArrayList<>();

    //nondefault constructor to create AudioBook object
    public AudioBook(String title, String author, String genre, double cost, double length)
    {
        super(title, author, genre, cost);
        this.length = length;
    }
    //dummy constructor for main class to use AudioBook methods
    public AudioBook(){}

    //user input to filter user info and collect only audioBooks
    public void importAudio(String file) throws FileNotFoundException {

        File inputFile = new File(file);
        Scanner in = new Scanner(inputFile);
        in.nextLine();
        while (in.hasNextLine())
        {
            String[] line = in.nextLine().split(",");
            // indexes: 0:title, 1:author, 2:genre, 3:cost, 4:length, 5:pages, 6:type
            if (line[6].equals("audioBook"))
            {
                audioList.add(new AudioBook(line[0], line[1], line[2], 5 * Double.parseDouble(line[4]), Double.parseDouble(line[4])));
            }
        }
    }
    //getter method for private field
    public double getLength()
    {
        return length;
    }

    //computes average length of aubioBooks
    public double averageLength()
    {
        if(audioList.isEmpty()) {
            return -1;
        }

        else{
            double x = 0, total = 0;
            for (AudioBook audioBook : audioList) {
                total += audioBook.getLength();
                x++;
            }
            return total / x;
        }
    }

    @Override
    //overrides abstract method to calculate the number of printed books for each genre
    public String numPerGenre()
    {
        if(audioList.isEmpty()) {
            return "There are no audiobooks in the list";
        }

        else{
            ArrayList<String> genres = new ArrayList<>();
            for (AudioBook audioBook : audioList) {
                String add = audioBook.getGenre().toLowerCase();
                if (!genres.contains(add)) {
                    genres.add(add);
                }
            }
            int[] amounts = new int[genres.size()];
            for (int i = 0; i < genres.size(); i++) {
                for (int j = 0; j < audioList.size(); j++) {
                    if (audioList.get(j).getGenre().toLowerCase().equals(genres.get(i))) {
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
    //overrides abstract method to calculate total cost of audioBooks
    public double getTotalCost()
    {
        double total = 0;
        for (AudioBook audioBook: audioList)
        {
            total += 5 * audioBook.getLength();
        }
        return total;
    }

    @Override
    public double getCost()
    {
        return cost;
    }
    //toString() method for displaying and file writing
    public String toString()
    {
        return super.toString() + (5 * getLength()) + "," + getLength() + "," + "N/A" + "," + "audioBook,";
    }
    //displays the information of last 3 printedBooks using toString()
    public void displayLast3Audio()
    {
        int x = audioList.size();
        if (audioList.isEmpty()) {
            System.out.println("There are no audiobooks in the list");
        }
        else if(x == 1) {
            System.out.println("There is only 1 audiobook in the list");
        }
        else if (x == 2) {
            System.out.println("There are only 2 audiobooks in the list");
        }
        else
        {
            System.out.println("title,author,genre,cost,length,pages,booktype,");
            for (int i = x-3; i < x; i++)
            {
                System.out.println(audioList.get(i).toString());
            }
        }
    }
    //static method to add to the printedList from the main class
    public static void addAudio(AudioBook audio) {
        audioList.add(audio);
        Book.add(audio);
    }
}



