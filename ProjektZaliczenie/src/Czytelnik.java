import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class Czytelnik {
    private static List czytelnicy = new LinkedList<Czytelnik>();
    private String imie;
    private String nazwisko;

    private List wypozyczone;
    private static BazaDanych startConnection = new BazaDanych();
    public Czytelnik(String imie, String nazwisko){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wypozyczone = new LinkedList();
        czytelnicy.add(this);
    }
    public Czytelnik(String imie, String nazwisko, List wypo){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wypozyczone = wypo;
        czytelnicy.add(this);
    }

    public void setWypozyczone(List wypozyczone) {
        this.wypozyczone = wypozyczone;
    }

    public static int szukajIndeksu(String imie, String nazwisko){
        for(Object o : czytelnicy){
            if(((Czytelnik)o).getImie().equals(imie) && ((Czytelnik)o).getNazwisko().equals(nazwisko)){
                return czytelnicy.indexOf(o);
            }
        }
        return -1;
    }
    public static void updateBase(){
        List czyt = startConnection.queryWyswietlCzytelnik();
        Object[] czytA = czyt.toArray();
        List czytO = new LinkedList<Czytelnik>();
        for (int i = 0; i < czytA.length; i++) {
            Czytelnik cz = new Czytelnik(String.valueOf(czytA[i+1]),String.valueOf(czytA[i+2]));
            int id = startConnection.getCzytId(String.valueOf(czytA[i+1]),String.valueOf(czytA[i+2]));
            i = i+3;
            czytO.add(cz);
        }
        czytelnicy = czytO;
    }
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public List getWypozyczone() {
        return wypozyczone;
    }

    public void addWypozyczone(Zbior obiekt) {
        obiekt.setWypozyczona(true);
        this.wypozyczone.add(obiekt);

    }
    public void delWypozyczona(int i){
        this.wypozyczone.remove(i);
    }

    public static List getCzytelnicy() {
        return czytelnicy;
    }
    public static void delCzytelnik(int id){

        czytelnicy.remove(id);
    }
    public static String[][] getCzytelnicyString(){
        String[][] data = new String[czytelnicy.size()][3];
        for (int i = 0; i < czytelnicy.size(); i++) {
            data[i][0] = ((Czytelnik)czytelnicy.get(i)).getImie();
            data[i][1] = ((Czytelnik)czytelnicy.get(i)).getNazwisko();
            data[i][2] = String.valueOf(((Czytelnik)czytelnicy.get(i)).getWypozyczone().size());
        }
        return data;
    }

    @Override
    public String toString() {
        return imie+"   "+nazwisko+"        "+wypozyczone.size()+"\n";
    }
}
