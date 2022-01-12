import java.util.LinkedList;
import java.util.List;

public class Ksiazka extends Zbior{
    private String autor, rok;
    private static List ksiazki = new LinkedList<Ksiazka>();
    private static BazaDanych startConnection = new BazaDanych();
    public Ksiazka(String tytul, String nrEw, String keyWord, String autor, String rok) {
        super(tytul, nrEw, keyWord);
        this.autor = autor;
        this.rok = rok;
        ksiazki.add(this);
    }
    public static void updateBase(){
        List czyt = startConnection.queryWyswietlKsiazki();
        Object[] czytA = czyt.toArray();
        List czytO = new LinkedList<Ksiazka>();
        //Zbior.setPrzedmioty(new LinkedList());
        int j = 0;
        for (int i = 0; i < czytA.length; i++) {
                Zbior ks = new Ksiazka(String.valueOf(czytA[0+j]),String.valueOf(czytA[1+j]),String.valueOf(czytA[2+j]),String.valueOf(czytA[3+j]),String.valueOf(czytA[4+j]));
                ks.setWypozyczona(Boolean.valueOf(String.valueOf(czytA[5+j])));
                czytO.add(ks);
                j += 6;
                if(j >= czytA.length) break;
        }
        ksiazki = czytO;

    }
    public static String[][] getPrzedmioty() {
        String[][] data = new String[ksiazki.size()][9];
        for (int i = 0; i < ksiazki.size(); i++) {
            Object item = ksiazki.get(i);
            data[i][0] = ((Zbior) item).getNrEw();
            data[i][1] = ((Zbior) item).getTytul();
            data[i][2] = ((Ksiazka) item).getAutor();
            data[i][3] = "brak";
            data[i][4] = "brak";
            data[i][5] = ((Ksiazka) item).getRok();
            data[i][6] = "brak";
            data[i][7] = ((Zbior) item).getKeyWord();
            data[i][8] = String.valueOf(((Ksiazka) item).isWypozyczona());
        }


        return data;

    }
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getRok() {
        return rok;
    }

    public void setRok(String rok) {
        this.rok = rok;
    }

    @Override
    public String toString() {
        return super.toString()+autor+" "+rok;
    }
}
