import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class Zbior {
    private String tytul, nrEw, keyWord;
    private boolean wypozyczona;
    private static List przedmioty = new LinkedList<Zbior>();
    private static BazaDanych startConnection = new BazaDanych();


    public Zbior(String tytul, String nrEw, String keyWord) {
        this.tytul = tytul;
        this.nrEw = nrEw;
        this.keyWord = keyWord;
        this.wypozyczona = false;
        przedmioty.add(this);
    }
    public Zbior(String tytul, String nrEw, String keyWord, boolean stanwypo) {
        this.tytul = tytul;
        this.nrEw = nrEw;
        this.keyWord = keyWord;
        this.wypozyczona = stanwypo;
        przedmioty.add(this);
    }
    public static void updateBase(){
        List czyt = startConnection.queryWyswietlWolumin();
        Object[] czytA = czyt.toArray();
        List czytO = new LinkedList<Zbior>();
        Zbior.setPrzedmioty(new LinkedList());
        int j = 0;
        for (int i = 0; i < czytA.length; i++) {
            Zbior ks = new Zbior(String.valueOf(czytA[0+j]),String.valueOf(czytA[1+j]),String.valueOf(czytA[2+j]));
            ks.setWypozyczona(Boolean.valueOf(String.valueOf(czytA[3+j])));
            czytO.add(ks);
            j += 4;
            if(j >= czytA.length) break;
        }


        System.out.println(przedmioty);

    }
    public static String[][] getPrzedmioty() {
        String[][] data = new String[przedmioty.size()][9];
        for (int i = 0; i < przedmioty.size(); i++) {

                Object item = przedmioty.get(i);
                item = (Zbior)item;
                if(item.getClass() == Ksiazka.class){
                    data[i][0] = ((Zbior) item).getNrEw();
                    data[i][1] = ((Zbior) item).getTytul();
                    data[i][2] = ((Ksiazka) item).getAutor();
                    data[i][3] = "brak";
                    data[i][4] = "brak";
                    data[i][5] = ((Ksiazka) item).getRok();
                    data[i][6] = "brak";
                    data[i][7] = ((Zbior) item).getKeyWord();
                    data[i][8] = String.valueOf(((Ksiazka) item).isWypozyczona());
                }else if(item.getClass() == Czasopismo.class){
                    data[i][0] = ((Zbior) item).getNrEw();
                    data[i][1] = ((Zbior) item).getTytul();
                    data[i][2] = "brak";
                    data[i][3] = ((Czasopismo) item).getNumer();
                    data[i][4] = "brak";
                    data[i][5] = ((Czasopismo) item).getRok();
                    data[i][6] = ((Czasopismo) item).getWydawnictwo();
                    data[i][7] = ((Zbior) item).getKeyWord();
                    data[i][8] = String.valueOf(((Czasopismo) item).isWypozyczona());
                }else if(item.getClass() == Plyta.class){
                    data[i][0] = ((Zbior) item).getNrEw();
                    data[i][1] = ((Zbior) item).getTytul();
                    data[i][2] = "brak";
                    data[i][3] = "brak";
                    data[i][4] = ((Plyta) item).getWykonawca();
                    data[i][5] = "brak";
                    data[i][6] = "brak";
                    data[i][7] = ((Zbior) item).getKeyWord();
                    data[i][8] = String.valueOf(((Plyta) item).isWypozyczona());
                }else System.out.println("error - wrong class");




        }

        return data;
    }

    public static void setPrzedmioty(List przedmioty) {
        Zbior.przedmioty = przedmioty;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getNrEw() {
        return nrEw;
    }

    public void setNrEw(String nrEw) {
        this.nrEw = nrEw;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public boolean isWypozyczona() {
        return wypozyczona;
    }
    public static List getPrzedmiotyList(){
        return przedmioty;
    }
    public void setWypozyczona(boolean wypozyczona) {
        this.wypozyczona = wypozyczona;
    }
    public static void delWolumin(int id){

        przedmioty.remove(id);
    }
    @Override
    public String toString() {
        return nrEw+" "+tytul+" "+keyWord+" "+wypozyczona+" ";

    }
}
