import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Books {
    private List<Book> books= new ArrayList<>();
    private List<PunctuationMarks> marksList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Book addBook() throws IOException {

        System.out.println("Enter title of the pdf book:");
        String title = scanner.nextLine();

        System.out.println("Enter language of the book: (PL, CHN, ANG");
        String language = scanner.nextLine();

        System.out.println("Enter path of the book:");
        String path = scanner.nextLine();

        Book book = new Book(title, language, path);

        books.add(book);
        loadPDF(book);
        count(book);
        return book;
    }

    public void displayBooks(){
        for(Book book : books) {
            book.displayBookInformation();
        }
    }

    public void removeBook(){
        System.out.println("Enter title of the book:");
        String title = scanner.nextLine();

        System.out.println("Enter language of the book: (PL, CHN, ANG");
        String language = scanner.nextLine();

        System.out.println("Enter path of the book:");
        String path = scanner.nextLine();

        Book book = new Book(title, language, path);

        books.remove(book);
    }

    public void loadPDF(Book book) throws IOException {
        if (book.getLanguage() != "CHN") {
            File file = new File(book.getPath()+ "/" + book.getTitle() + ".pdf");
            PDDocument document = Loader.loadPDF(file);

            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            FileWriter textfile = new FileWriter(book.getPath() + book.getTitle() + "_temp.txt");
            textfile.write(text);
            textfile.close();
        } else {
            PdfReader reader;

            reader = new PdfReader("src/temp.pdf");
            String textFromPage = null;
            String text = null;
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                textFromPage = PdfTextExtractor.getTextFromPage(reader, i);
                text += textFromPage;
            }

            FileWriter textfile = new FileWriter(book.getPath() + book.getTitle() + "_temp.txt");
            textfile.write(text);
            textfile.close();

        }
    }

    public void count(Book book) throws IOException {
        loadPDF(book);
        String data = new String(Files.readAllBytes(Paths.get(book.getPath()+book.getTitle()+"_temp.txt")));
        PunctuationMarks marks = new PunctuationMarks();

        int angdot=0, angqum=0, angexm=0, angcom=0, angsmc=0, angcol=0, angpse=0, angdsh=0, angsqb=0, angcyb=0 , angcrb=0, angapt=0, angqtm=0,angssp=0;
        int pldot=0, plqum=0, plexm=0, plcom=0, plsmc=0, plcol=0, plpse=0,plcrb=0, plqtm=0,plssp=0;
        int chndot=0, chncom=0, chnenc=0, chnexm=0, chnqum=0, chnsmc=0, chncol=0, chnqtm=0, chnbrd=0, chnpse=0, chnhps=0, chndsh=0, chnssp=0, chndis=0, chnfqm=0, chnmid=0, chnund=0;

        char sred = 73;

        for (int i = 0; i < data.length(); i++) {

           //PL
            if (data.charAt(i)=='.'){
                pldot++;
            }if (data.charAt(i)=='?'){
                plqum++;
            }if (data.charAt(i)=='!'){
                plexm++;
            }if (data.charAt(i)==','){
                plcom++;
            }if (data.charAt(i)==';'){
                plsmc++;
            }if (data.charAt(i)==':'){
                plcol++;
            }if (data.charAt(i)=='–'){
                plpse++;
            }if (data.charAt(i)=='(' || data.charAt(i)==')'){
                plcrb++;
            }if (data.charAt(i)=='„' || data.charAt(i)=='”'){
                plqtm++;
            }if (data.charAt(i)=='.' && data.charAt(i+1)=='.' && data.charAt(i+2)=='.'){
                plssp++;
            }

            //ANG
            if (data.charAt(i)=='.'){
                angdot++;
            }if (data.charAt(i)=='?'){
                angqum++;
            }if (data.charAt(i)=='!'){
                angexm++;
            }if (data.charAt(i)==','){
                angcom++;
            }if (data.charAt(i)==';'){
                angsmc++;
            }if (data.charAt(i)==':'){
                angcol++;
            }if (data.charAt(i)=='—'){
                angpse++;
            }if (data.charAt(i)=='-'){
                angdsh++;
            }if (data.charAt(i)=='[' || data.charAt(i)==']'){
                angsqb++;
            }if (data.charAt(i)=='{' || data.charAt(i)=='}'){
                angcyb++;
            }if (data.charAt(i)=='(' || data.charAt(i)==')'){
                angcrb++;
            }if (data.charAt(i)=='’' || data.charAt(i)=='‘'){
                angapt++;
            }if (data.charAt(i)=='“' || data.charAt(i)=='”'){
                angqtm++;
            }if (data.charAt(i)=='.' && data.charAt(i+1)=='.' && data.charAt(i+2)=='.'){
                angssp++;
            }


            //CHN
                if (data.charAt(i)=='。'){
                chndot++;
                }if (data.charAt(i)=='?' || data.charAt(i)=='？'){
                chnqum++;
                }if (data.charAt(i)=='!' || data.charAt(i)=='！'){
                chnexm++;
                }if (data.charAt(i)=='，'){
                chncom++;
                }if (data.charAt(i)=='、'){
                chnenc++;
                }if (data.charAt(i)==';' || data.charAt(i)=='；'){
                chnsmc++;
                }if (data.charAt(i)==':' || data.charAt(i)=='：'){
                chncol++;
                }if (data.charAt(i)=='—'&&data.charAt(i+1)!='—'&&data.charAt(i-1)!='—'){
                chnhps++;
                }if (data.charAt(i)=='—'){
                chndsh++;
                }if (data.charAt(i)=='(' || data.charAt(i)==')' || data.charAt(i)=='（' || data.charAt(i)=='）' || data.charAt(i)=='［' || data.charAt(i)=='］' || data.charAt(i)=='〕' || data.charAt(i)=='〔' || data.charAt(i)=='【' || data.charAt(i)=='】'){
                chnbrd++;
                }if (data.charAt(i)=='„' || data.charAt(i)=='“' || data.charAt(i)=='”' || data.charAt(i)=='「' || data.charAt(i)=='」' || data.charAt(i)=='‘' || data.charAt(i)=='’'){
                chnqtm++;
                }if (data.charAt(i)=='…' && data.charAt(i+1)=='…' /*&& data.charAt(i+2)=='.' && data.charAt(i+3)=='.' && data.charAt(i+4)=='.'&& data.charAt(i+5)=='.'*/){
                chnssp++;
                }if (data.charAt(i) == '《' || data.charAt(i) == '》') {
                chnfqm++;
                }if ((data.charAt(i) == '—' && data.charAt(i+1) == '—')){
                chnpse++;
                }if (data.charAt(i) == '_') {
                chnund++;
                }if (data.charAt(i) == '•') {
                chnmid++;
                }if (data.charAt(i) == '.') {
                chndis++;
                }

        }

        marks.setAngdot(angdot);marks.setAngqum(angqum);marks.setAngexm(angexm);marks.setAngcom(angcom);marks.setAngsmc(angsmc);marks.setAngcol(angcol);marks.setAngpse(angpse);marks.setAngdsh(angdsh);marks.setAngsqb(angsqb);marks.setAngcyb(angcyb);marks.setAngcrb(angcrb);marks.setAngapt(angapt);marks.setAngqtm(angqtm);marks.setAngssp(angssp);
        marks.setPldot(pldot);marks.setPlqum(plqum);marks.setPlexm(plexm);marks.setPlcom(plcom);marks.setPlsmc(plsmc);marks.setPlcol(plcol);marks.setPlpse(plpse);marks.setPlcrb(plcrb);marks.setPlqtm(plqtm);marks.setPlssp(plssp);
        marks.setChndot(chndot); marks.setChncom(chncom); marks.setChnenc(chnenc); marks.setChnexm(chnexm); marks.setChnqum(chnqum); marks.setChnsmc(chnsmc); marks.setChncol(chncol); marks.setChnqtm(chnqtm); marks.setChnbrd(chnbrd); marks.setChnpse(chnpse); marks.setChnhps(chnhps); marks.setChndsh(chndsh); marks.setChnssp(chnssp); marks.setChndis(chndis); marks.setChnfqm(chnfqm); marks.setChnmid(chnmid); marks.setChnund(chnund);

        book.setMarks(marks);
    }
}
