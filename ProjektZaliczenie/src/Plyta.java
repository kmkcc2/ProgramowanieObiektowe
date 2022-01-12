import java.util.LinkedList;
import java.util.List;

public class Plyta extends Zbior{
    private String wykonawca;
    private static List plyty = new LinkedList<Plyta>();
    private static BazaDanych startConnection = new BazaDanych();
    public Plyta(String tytul, String nrEw, String keyWord, String wykonawca) {
        super(tytul, nrEw, keyWord);
        this.wykonawca = wykonawca;
        plyty.add(this);
    }
    public static String[][] getPrzedmioty() {
        String[][] data = new String[plyty.size()][9];
        for (int i = 0; i < plyty.size(); i++) {
            Object item = plyty.get(i);
            data[i][0] = ((Zbior) item).getNrEw();
            data[i][1] = ((Zbior) item).getTytul();
            data[i][2] = "brak";
            data[i][3] = "brak";
            data[i][4] = ((Plyta) item).getWykonawca();
            data[i][5] = "brak";
            data[i][6] = "brak";
            data[i][7] = ((Zbior) item).getKeyWord();
            data[i][8] = String.valueOf(((Plyta) item).isWypozyczona());
        }


        return data;

    }
    public static void updateBase(){
        List czyt = startConnection.queryWyswietlPlyty();
        Object[] czytA = czyt.toArray();
        List czytO = new LinkedList<Plyta>();
        //Zbior.setPrzedmioty(new LinkedList());
        int j = 0;
        for (int i = 0; i < czytA.length; i++) {

            Zbior pl = new Plyta(String.valueOf(czytA[0+j]),String.valueOf(czytA[1+j]),String.valueOf(czytA[2+j]),String.valueOf(czytA[3+j]));
            pl.setWypozyczona(Boolean.valueOf(String.valueOf(czytA[4+j])));

            czytO.add(pl);

            j += 5;
            if(j >= czytA.length) break;



        }
        plyty = czytO;

    }
    public String getWykonawca() {
        return wykonawca;
    }

    public void setWykonawca(String wykonawca) {
        this.wykonawca = wykonawca;
    }

    @Override
    public String toString() {
        return super.toString()+wykonawca;
    }
}
