import java.util.LinkedList;
import java.util.List;

public class Czasopismo extends Zbior{
    private String numer, rok, wydawnictwo;
    private static List czasopisma = new LinkedList<Czasopismo>();
    private static BazaDanych startConnection = new BazaDanych();
    public Czasopismo(String tytul, String nrEw, String keyWord, String numer, String rok, String wydawnictwo) {
        super(tytul, nrEw, keyWord);
        this.numer = numer;
        this.rok = rok;
        this.wydawnictwo = wydawnictwo;
        czasopisma.add(this);
    }
    public static void updateBase(){
        List czyt = startConnection.queryWyswietlCzasopisma();
        Object[] czytA = czyt.toArray();
        List czytO = new LinkedList<Czasopismo>();
        //Zbior.setPrzedmioty(new LinkedList());
        int j = 0;
        for (int i = 0; i < czytA.length; i++) {
            Zbior cz = new Czasopismo(String.valueOf(czytA[0+j]),String.valueOf(czytA[1+j]),String.valueOf(czytA[2+j]),String.valueOf(czytA[3+j]),String.valueOf(czytA[4+j]),String.valueOf(czytA[5+j]));
            cz.setWypozyczona(Boolean.valueOf(String.valueOf(czytA[6+j])));
            czytO.add(cz);
            j += 7;
            if(j >= czytA.length) break;
        }

        czasopisma = czytO;

    }
    public static String[][] getPrzedmioty() {
        String[][] data = new String[czasopisma.size()][9];
        for (int i = 0; i < czasopisma.size(); i++) {
            Object item = czasopisma.get(i);
            data[i][0] = ((Zbior) item).getNrEw();
            data[i][1] = ((Zbior) item).getTytul();
            data[i][2] = "brak";
            data[i][3] = ((Czasopismo) item).getNumer();
            data[i][4] = "brak";
            data[i][5] = ((Czasopismo) item).getRok();
            data[i][6] = ((Czasopismo) item).getWydawnictwo();
            data[i][7] = ((Zbior) item).getKeyWord();
            data[i][8] = String.valueOf(((Czasopismo) item).isWypozyczona());
        }


        return data;

    }



    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public String getRok() {
        return rok;
    }

    public void setRok(String rok) {
        this.rok = rok;
    }

    public String getWydawnictwo() {
        return wydawnictwo;
    }

    public void setWydawnictwo(String wydawnictwo) {
        this.wydawnictwo = wydawnictwo;
    }

    @Override
    public String toString() {
        return super.toString()+numer+" "+rok+" "+wydawnictwo;

    }
}
