import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public abstract interface BookInterface
{
    public default void getLast6Books()
    {
        ArrayList<Book> list = Book.getBookList();
        if (list.size() < 6)
        {
            System.out.println("List is too small");
        }
        else
        {
            System.out.println("title,author,genre,cost,length,pages,booktype,");
            int x = list.size();
            for (int i = x-6; i < x; i++)
            {
                System.out.println(list.get(i).toString());
            }
        }
    }

    public abstract String numPerGenre();

    public abstract double getTotalCost();
}