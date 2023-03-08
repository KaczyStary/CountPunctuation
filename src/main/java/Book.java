import java.util.Objects;

public class Book {
    private String title;
    private String language;
    private String path;
    private PunctuationMarks marks;

    public void setMarks(PunctuationMarks marks) {
        this.marks = marks;
    }

    public PunctuationMarks getMarks() {
        return marks;
    }

    public Book(String title, String language, String path) {
        this.title = title;
        this.language = language;
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public String getPath() {
        return path;
    }

    public void displayBookInformation()
    {
        System.out.println("Title: " + title);
        System.out.println("Language: "+ language);
        System.out.println("Path: "+ path);
        System.out.println(marks);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        if (language!="CHN")
        {
            System.out.println("");
        }else
        {
            System.out.println();
        }
    };



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(language, book.language) && Objects.equals(path, book.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, language, path);
    }
}
