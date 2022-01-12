import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

public class GUI extends JFrame implements ActionListener, KeyListener {
    /*
   Temat 4: System biblioteczny 2.
    W bibliotece znajdują się woluminy -książki, płyty i poszczególne numery czasopism.
    Każdy z nich posiada tytuł i nr ewidencyjny oraz informację, czy jest wypożyczony.
    Każda książka ma tytuł , autora, rok wydania, słowo kluczowe oraz czy jest dostępna .
    Płyta ma tytuł, wykonawcę, słowo kluczowe, oraz czy jest dostępna.
    Czasopismo ma tytuł, numer i rok, wydawnictwo, słowo kluczowe oraz informację czy jest dostępna.
    System powinien umożliwiać :
    •Wyszukanie książki, płyty lub czasopisma (po tytule, słowie kluczowym, autorze),
        czasopismo –tylko podawany jest jednokrotnie tytuł.
    •Zamówienie  woluminu  (egzemplarza  książki,  płyty  lub  numeru  czasopisma) – jeśli  jest dostępny.
    •Informację o stanie konta użytkownika (wykaz posiadanych książek).Bibliotekarz ma konto administracyjne i może:
        •Dodać/usunąć wolumin z biblioteki.
        •Wypożyczyć danemu użytkownikowi wolumin.
        •Przyjąć zwrot woluminu (konto użytkownika jest aktualizowane).
    Do tego celu wykorzystaj stworzony przez siebie klasy np.
        Książka, Czytelnik, Czasopismo, Płyta itp. z odpowiednimi składowymi i metodami.
    Postaraj się wykorzystać w projekcie strukturę dziedziczenia.
    Aplikacja graficzna powinna zawierać (odpowiednio obsługiwane) następujące komponenty SWING:
    pola tekstowe, pola wyboru, listy rozwijane, listy wyboru, przyciski, przyciski radiowe i menu.
    */

    private JPanel panelContainer;
    private JPanel panelListaWolum;
    private JTextField tfSzukajWolum;
    private JPanel panelListaCzytelnik;
    private JTextField tfSzukajCzyt;
    private JPanel panelDodajCzytelnika;
    private JTextField tfImie;
    private JTextField tfNazwisko;
    private JButton buttonDodajCzyt;
    private JButton buttonUsunCzyt;
    private JPanel panelUsunCzytelnika;
    private JList<Object> jlistaCzytelnikUsun;
    private JPanel panelZwrot;
    private JComboBox<String> comboZwrotCzytelnik;
    private JComboBox<String> comboZwrotWolumin;
    private JButton buttonZwrot;
    private JPanel panelWypo;
    private JComboBox<String> comboCzytWypo;
    private JComboBox<String> comboWoluWypo;
    private JButton buttonWypo;
    private JPanel panelDodajWolu;
    private JComboBox comboRodzajWoluminu;
    private JButton buttonDodajWolumin;
    private JLabel labelKs1;
    private JLabel labelKsCz;
    private JLabel labelCz1;
    private JLabel labelPl1;
    private JLabel labelCz2;
    private JTextField tfAutorKs1;
    private JTextField tfRokKsCz;
    private JTextField tfNumerCz;
    private JTextField tfWykonawcaPl;
    private JTextField tfWydawnictwoCz;
    private JTextField tfSlowoKlucz;
    private JTextField tfNrEw;
    private JTextField tfTytul;
    private JPanel panelUsunWolu;
    private JList<Object> jlistaWoluminUsun;
    private JButton buttonUsunWolu;
    private JPanel panelStart;
    private JTable tabelaWolumin;
    private JTable tabelaCzytelnicy;
    private JComboBox comboTypWoluSzukaj;
    private JTable tabelaUsunWolu;
    private JTable tabelaCzytelnikUsun;
    private BazaDanych bd = new BazaDanych();
    private static final String[] columnNames = {
            "Numer Ewidencyjny",
            "Tytuł",
            "Autor",
            "Numer",
            "Wykonawca",
            "Rok wydania",
            "Wydawnictwo",
            "Słowo klucz",
            "Wypożyczona"
    };
    private static final String[] columnNamesCzyt = {
            "Imię",
            "Nazwisko",
            "Wypożyczone woluminy"
    };
    JMenuItem listaWolumin,listaCzytelnik, dodajWolumin, dodajCzytelnik, usunWolumin, usunCzytelnik, zwrot, wyp;
    JMenuBar menuBar;
    JMenu jMenuCzytelnik, jMenuWolumin;
    CardLayout cl = new CardLayout();


    public GUI(){
        super("Bibilioteka");
        this.setContentPane(panelContainer);
        this.setLayout(cl);
        panelContainer.add(panelStart,"startPanel");
        panelContainer.add(panelListaWolum,"listaWolumin");
        panelContainer.add(panelListaCzytelnik,"listaCzyt");
        panelContainer.add(panelDodajCzytelnika,"dodajCzyt");
        panelContainer.add(panelUsunCzytelnika,"usunCzyt");
        panelContainer.add(panelZwrot,"zwrot");
        panelContainer.add(panelWypo,"wypo");
        panelContainer.add(panelDodajWolu,"dodajWol");
        panelContainer.add(panelUsunWolu,"usunWol");
        int width = 600;
        int height = 600;

        setSize(width, height);

        menuBar = new JMenuBar();
        jMenuCzytelnik = new JMenu("Czytelnik");
        jMenuWolumin = new JMenu("Wolumin");

        listaWolumin = new JMenuItem("Lista egzemplarzy");
        listaCzytelnik = new JMenuItem("Lista czytelników");
        dodajWolumin = new JMenuItem("Dodaj");
        dodajCzytelnik = new JMenuItem("Dodaj");
        usunWolumin = new JMenuItem("Usuń");
        usunCzytelnik = new JMenuItem("Usuń");
        zwrot = new JMenuItem("Zwrot");
        wyp = new JMenuItem("Wypożyczenie");



        listaWolumin.addActionListener(this);
        listaCzytelnik.addActionListener(this);
        dodajWolumin.addActionListener(this);
        dodajCzytelnik.addActionListener(this);
        usunWolumin.addActionListener(this);
        usunCzytelnik.addActionListener(this);
        zwrot.addActionListener(this);
        wyp.addActionListener(this);
        comboRodzajWoluminu.addActionListener(this);
        buttonDodajCzyt.addActionListener(this);
        buttonUsunCzyt.addActionListener(this);
        buttonDodajWolumin.addActionListener(this);
        buttonUsunWolu.addActionListener(this);
        buttonWypo.addActionListener(this);
        comboZwrotCzytelnik.addActionListener(this);
        buttonZwrot.addActionListener(this);
        tfSzukajCzyt.addKeyListener(this);
        tfSzukajWolum.addKeyListener(this);
        comboTypWoluSzukaj.addActionListener(this);



        jMenuCzytelnik.add(listaCzytelnik);
        jMenuCzytelnik.addSeparator();
        jMenuCzytelnik.add(dodajCzytelnik);
        jMenuCzytelnik.add(usunCzytelnik);
        jMenuCzytelnik.addSeparator();
        jMenuCzytelnik.add(zwrot);
        jMenuCzytelnik.add(wyp);
        jMenuWolumin.add(listaWolumin);
        jMenuWolumin.addSeparator();
        jMenuWolumin.add(dodajWolumin);
        jMenuWolumin.add(usunWolumin);

        setJMenuBar(menuBar);

        menuBar.add(jMenuCzytelnik);
        menuBar.add(jMenuWolumin);


        Czytelnik.updateBase();
        Ksiazka.updateBase();
        Czasopismo.updateBase();
        Plyta.updateBase();








        String[][] dataWolu = Zbior.getPrzedmioty();
        String[][] dataCzyt = Czytelnik.getCzytelnicyString();
        rysujTabelaWolum(dataWolu);
        rysujTabelaCzyt(dataCzyt);



    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            GUI frame = new GUI();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);





        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object zrodlo = e.getSource();
        //obluga menu
        if(zrodlo == listaWolumin){
            cl.show(panelContainer,"listaWolumin");
        } else if(zrodlo == listaCzytelnik){
            cl.show(panelContainer,"listaCzyt");
        } else if (zrodlo == dodajCzytelnik){
            cl.show(panelContainer,"dodajCzyt");
        } else if(zrodlo == usunCzytelnik){
            cl.show(panelContainer,"usunCzyt");
            pokazCzytelnik();
        } else if(zrodlo == zwrot){
            cl.show(panelContainer, "zwrot");
            ustawComboZwrot();
        } else if (zrodlo == wyp){
            cl.show(panelContainer,"wypo");
            ustawComboWypo();
        } else if (zrodlo == dodajWolumin){
            cl.show(panelContainer, "dodajWol");
        } else if(zrodlo == usunWolumin){
            cl.show(panelContainer,"usunWol");
            pokazWolumin();
        }

        //obsluga combobox podczas dodawania nowego woluminu
        if(zrodlo == comboRodzajWoluminu){


            if (comboRodzajWoluminu != null) {
                Object text;
                text = comboRodzajWoluminu.getSelectedItem();
                if (text != null) {
                    text = text.toString();
                    if(text.equals("Ksiazka")){
                        labelKs1.setVisible(true);
                        labelKsCz.setVisible(true);
                        labelCz1.setVisible(false);
                        labelCz2.setVisible(false);
                        labelPl1.setVisible(false);

                        tfAutorKs1.setVisible(true);
                        tfNumerCz.setVisible(false);
                        tfWydawnictwoCz.setVisible(false);
                        tfWykonawcaPl.setVisible(false);
                        tfRokKsCz.setVisible(true);
                    }else if(text.equals("Czasopismo")){
                        labelKs1.setVisible(false);
                        labelKsCz.setVisible(true);
                        labelCz1.setVisible(true);
                        labelCz2.setVisible(true);
                        labelPl1.setVisible(false);

                        tfAutorKs1.setVisible(false);
                        tfNumerCz.setVisible(true);
                        tfWydawnictwoCz.setVisible(true);
                        tfWykonawcaPl.setVisible(false);
                        tfRokKsCz.setVisible(true);
                    }else if(text.equals("Plyta")){
                        labelKs1.setVisible(false);
                        labelKsCz.setVisible(false);
                        labelCz1.setVisible(false);
                        labelCz2.setVisible(false);
                        labelPl1.setVisible(true);

                        tfAutorKs1.setVisible(false);
                        tfNumerCz.setVisible(false);
                        tfWydawnictwoCz.setVisible(false);
                        tfWykonawcaPl.setVisible(true);
                        tfRokKsCz.setVisible(false);
                    } else{
                        labelKs1.setVisible(false);
                        labelKsCz.setVisible(false);
                        labelCz1.setVisible(false);
                        labelCz2.setVisible(false);
                        labelPl1.setVisible(false);

                        tfAutorKs1.setVisible(false);
                        tfNumerCz.setVisible(false);
                        tfWydawnictwoCz.setVisible(false);
                        tfWykonawcaPl.setVisible(false);
                        tfRokKsCz.setVisible(false);
                    }
                }

            }

        }
        //obluga combobox podczas zmiany wyswietlania tabeli woluminów
        if(zrodlo == comboTypWoluSzukaj){
            String item = comboTypWoluSzukaj.getSelectedItem().toString();
            if(item.equals("<wszystkie>")){
                String[][] data = Zbior.getPrzedmioty();
                rysujTabelaWolum(data);
            }else if(item.equals("Ksiazka")){
                String[][] data = Ksiazka.getPrzedmioty();
                rysujTabelaWolum(data);
            }else if(item.equals("Czasopismo")){
                String[][] data = Czasopismo.getPrzedmioty();
                rysujTabelaWolum(data);
            }else if(item.equals("Plyta")){
                String[][] data = Plyta.getPrzedmioty();
                rysujTabelaWolum(data);
            }
        }
        //obluga przyciskow
        if(zrodlo == buttonDodajCzyt){
            dodajCzytelnik();
        }else if(zrodlo == buttonUsunCzyt){
            usunCzytelnik();
        }else if(zrodlo == buttonDodajWolumin){
            dodajWolumin();
        }else if (zrodlo == buttonUsunWolu){
            usunWolumin();
        }else if(zrodlo == buttonWypo){
            dodajWypo();
        }else if(zrodlo == buttonZwrot){
            dodajZwrot();
        }

        //wyswietl wypozyczone woluminy uzytkownika
        if(zrodlo == comboZwrotCzytelnik){
           ustawZwrotDane();
        }

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object zrodlo = e.getSource();
        //wyszukiwanie czytelnikow
        if(zrodlo == tfSzukajCzyt){
            szukajCzyt();
        }else if(zrodlo == tfSzukajWolum){
            szukajWolum();
        }
    }
    public void dodajCzytelnik(){
        if (JOptionPane.showConfirmDialog(null, "Dodać nowego użytkownika?", "Uwaga",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            String imie = tfImie.getText();
            String nazwisko = tfNazwisko.getText();

            if(imie.equals("") || nazwisko.equals("")){
                JOptionPane.showMessageDialog(null,"Pola imię oraz nazwisko muszą zostać wypełnione!");

            }else{
                new Czytelnik(imie, nazwisko);
                bd.queryDodajCzytelnik(imie,nazwisko);
                String[] newData = {imie,nazwisko,"0"};
                DefaultTableModel model = (DefaultTableModel) tabelaCzytelnicy.getModel();
                model.addRow(newData);

                tfImie.setText("");
                tfNazwisko.setText("");

            }
        }


    }
    public void pokazCzytelnik(){
        List czytelnicy = Czytelnik.getCzytelnicy();
        String data[][] = new String[czytelnicy.size()][3];
        for (int i = 0; i < czytelnicy.size(); i++) {
            data[i][0] = ((Czytelnik)czytelnicy.get(i)).getImie();
            data[i][1] = ((Czytelnik)czytelnicy.get(i)).getNazwisko();
            data[i][2] = String.valueOf(((Czytelnik)czytelnicy.get(i)).getWypozyczone().size());
        }

        TableModel model = new DefaultTableModel(data, new String[] {"Imię","Nazwisko","Wypożyczone woluminy"}) {
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tabelaCzytelnikUsun.setModel(model);



    }
    public void usunCzytelnik(){

        if (JOptionPane.showConfirmDialog(null, "Czy na pewno?", "Uwaga",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int id = tabelaCzytelnikUsun.getSelectedRow();
            if(id > -1){
                if(((Czytelnik)Czytelnik.getCzytelnicy().get(id)).getWypozyczone().size() == 0){
                    String imie = String.valueOf(tabelaCzytelnikUsun.getModel().getValueAt(id,0));
                    String nazwisko = String.valueOf(tabelaCzytelnikUsun.getModel().getValueAt(id,1));
                    Czytelnik.delCzytelnik(id);
                    pokazCzytelnik();
                    String[][] data = Czytelnik.getCzytelnicyString();
                    rysujTabelaCzyt(data);
                    bd.usunCzytelnik(imie,nazwisko);
                    Czytelnik.updateBase();
                    JOptionPane.showMessageDialog(null,"Pomyślnie usunięto czytelnika");
                }else
                    JOptionPane.showMessageDialog(null,"Czytelnik ma wypożyczony co najmniej jeden wolumin. Usunięcie jest niemożliwe");

            }else
                JOptionPane.showMessageDialog(null,"Proszę wybrać czytelnika");

        }


    }
    public void dodajWolumin(){
        Object typ = comboRodzajWoluminu.getSelectedItem();
        if (typ != null) {
            typ = typ.toString();if(typ.equals("Ksiazka")){

                String tytul, autor, rok, slowo;
                String nrEw;
                tytul = tfTytul.getText();
                autor = tfAutorKs1.getText();
                rok = tfRokKsCz.getText();
                slowo = tfSlowoKlucz.getText();
                nrEw = tfNrEw.getText();

                if(tytul.equals("") || autor.equals("") || rok.equals("") || slowo.equals("") || nrEw.equals("")){
                    JOptionPane.showMessageDialog(null,"Wszystkie pola tekstowe muszą zostać wypełnione");
                }else{
                    new Ksiazka(tytul, nrEw, slowo, autor, rok);
                    String[] newData = {nrEw, tytul, autor, "brak", "brak",rok, "brak",slowo, "false"};
                    DefaultTableModel model = (DefaultTableModel) tabelaWolumin.getModel();
                    model.addRow(newData);
                    bd.dodajWolumin(nrEw, tytul, autor, "null", "null",rok, "null",slowo, "false");
                    JOptionPane.showMessageDialog(null,"Pomyślnie dodano wolumin");
                    tfTytul.setText("");
                    tfAutorKs1.setText("");
                    tfWykonawcaPl.setText("");
                    tfWydawnictwoCz.setText("");
                    tfRokKsCz.setText("");
                    tfNrEw.setText("");
                    tfSlowoKlucz.setText("");
                }

            }else if(typ.equals("Czasopismo")){
                String tytul, rok, slowo, nrEw, numer, wydawnictwo;
                numer = tfNumerCz.getText();
                wydawnictwo = tfWydawnictwoCz.getText();
                tytul = tfTytul.getText();
                rok = tfRokKsCz.getText();
                slowo = tfSlowoKlucz.getText();
                nrEw = tfNrEw.getText();
                if(tytul.equals("") || numer.equals("") || rok.equals("") || slowo.equals("") || nrEw.equals("") || wydawnictwo.equals("")){
                    JOptionPane.showMessageDialog(null,"Wszystkie pola tekstowe muszą zostać wypełnione");
                }else{
                    new Czasopismo(tytul, nrEw, slowo, numer, rok, wydawnictwo);
                    String[] newData = {nrEw, tytul, "brak", numer, "brak",rok, wydawnictwo,slowo,"false"};
                    DefaultTableModel model = (DefaultTableModel) tabelaWolumin.getModel();
                    model.addRow(newData);
                    bd.dodajWolumin(nrEw, tytul, null, numer, null,rok, wydawnictwo,slowo,"false");
                    JOptionPane.showMessageDialog(null,"Pomyślnie dodano wolumin");
                    tfTytul.setText("");
                    tfAutorKs1.setText("");
                    tfWykonawcaPl.setText("");
                    tfWydawnictwoCz.setText("");
                    tfRokKsCz.setText("");
                    tfNrEw.setText("");
                    tfNumerCz.setText("");
                    tfSlowoKlucz.setText("");
                }

            }else if(typ.equals("Plyta")){
                String tytul, wykonawca, slowo, nrEw;
                tytul = tfTytul.getText();
                wykonawca = tfWykonawcaPl.getText();
                slowo = tfSlowoKlucz.getText();
                nrEw = tfNrEw.getText();
                if(tytul.equals("") || wykonawca.equals("") || slowo.equals("") || nrEw.equals("")){
                    JOptionPane.showMessageDialog(null,"Wszystkie pola tekstowe muszą zostać wypełnione");
                }else{
                    new Plyta(tytul, nrEw, slowo, wykonawca);
                    DefaultTableModel model = (DefaultTableModel) tabelaWolumin.getModel();
                    String[] newData = {nrEw, tytul, "brak", "brak", wykonawca,"brak", "brak",slowo, "false"};
                    model.addRow(newData);
                    bd.dodajWolumin(nrEw, tytul, null, null, wykonawca,null, null,slowo, "false");
                    JOptionPane.showMessageDialog(null,"Pomyślnie dodano wolumin");
                    tfTytul.setText("");
                    tfAutorKs1.setText("");
                    tfWykonawcaPl.setText("");
                    tfWydawnictwoCz.setText("");
                    tfRokKsCz.setText("");
                    tfNrEw.setText("");
                    tfSlowoKlucz.setText("");
                }

            }else JOptionPane.showMessageDialog(null,"Proszę określić typ dodawanego woluminu");

        }



    }
    public void pokazWolumin(){


        List czyt = Zbior.getPrzedmiotyList();
        String[][] data = new String[czyt.size()][4];
        for (int i = 0; i < Zbior.getPrzedmiotyList().size(); i++) {
            data[i][0] = ((Zbior)czyt.get(i)).getNrEw();
            data[i][1] = ((Zbior)czyt.get(i)).getTytul();
            data[i][2] = ((Zbior)czyt.get(i)).getKeyWord();
            data[i][3] = String.valueOf(((Zbior)czyt.get(i)).isWypozyczona());


        }
        TableModel model = new DefaultTableModel(data, new String[] {"Nr. ew","tytuł","słowo klucz","wypozyczona"}) {
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };

        tabelaUsunWolu.setModel(model);

    }
    public void usunWolumin(){
        if (JOptionPane.showConfirmDialog(null, "Czy na pewno?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            int id = tabelaUsunWolu.getSelectedRow();
            if(id > -1){
                List woluminy = Zbior.getPrzedmiotyList();
                if(!((Zbior)woluminy.get(id)).isWypozyczona()){
                    Zbior.delWolumin(id);
                    DefaultTableModel model = (DefaultTableModel) tabelaWolumin.getModel();
                    String nrew = String.valueOf(tabelaUsunWolu.getModel().getValueAt(id, 0));
                    model.removeRow(id);
                    bd.usunWolumin(nrew);

                    JOptionPane.showMessageDialog(null, "Zaznaczony rekord został pomyślnie usunięty");
                }else{
                    JOptionPane.showMessageDialog(null,"Wolumin jest obecnie wypożyczony i nie może zostać usunięty.");
                }
            }else
                JOptionPane.showMessageDialog(null,"Proszę wybrać wolumin");

            pokazWolumin();
        }


    }
    public void ustawComboWypo(){
        comboCzytWypo.removeAllItems();
        comboWoluWypo.removeAllItems();
        List czyt = Czytelnik.getCzytelnicy();
        List wol = Zbior.getPrzedmiotyList();
        for (Object o : czyt) {
            String item = ((Czytelnik) o).getImie() + " " + ((Czytelnik) o).getNazwisko();
            comboCzytWypo.addItem(item);
        }
        for (Object o : wol) {
            String item = ((Zbior) o).getNrEw() + " " + ((Zbior) o).getTytul() + " " + ((Zbior) o).getKeyWord() + " " + ((Zbior) o).isWypozyczona();
            //if( !((Zbior)wol.get(i)).isWypozyczona() )
            comboWoluWypo.addItem(item);


        }

    }
    public void dodajWypo(){
        if (JOptionPane.showConfirmDialog(null, "Czy na pewno?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            List wol = Zbior.getPrzedmiotyList();
            List czyt = Czytelnik.getCzytelnicy();
            int osoba = comboCzytWypo.getSelectedIndex();
            int wolumin = comboWoluWypo.getSelectedIndex();
                if(osoba > -1 && wolumin > -1){
                    if (!((Zbior)wol.get(wolumin)).isWypozyczona()) {
                        ((Zbior) wol.get(wolumin)).setWypozyczona(true);
                        ((Czytelnik) czyt.get(osoba)).addWypozyczone((Zbior) wol.get(wolumin));
                        tabelaWolumin.setValueAt("true", wolumin, 8);
                        String[][] data = Czytelnik.getCzytelnicyString();
                        rysujTabelaCzyt(data);
                        ustawComboWypo();
                        int idczyt = bd.getCzytId(String.valueOf(tabelaCzytelnicy.getModel().getValueAt(osoba,0)),String.valueOf(tabelaCzytelnicy.getModel().getValueAt(osoba,1)));
                        int idwolu = bd.getIdWolu(String.valueOf(tabelaWolumin.getModel().getValueAt(wolumin,0)));
                        bd.queryDodajWypo(idczyt,idwolu);
                        JOptionPane.showMessageDialog(null, "Wypożyczenie zostało zatwierdzone");
                    }else{
                        JOptionPane.showMessageDialog(null, "Wolumin jest już obecnie wypożyczony i nie może zostać wypożyczony ponownie.");
                    }
                }else
                    JOptionPane.showMessageDialog(null,"Prosze wybrać czytelnika i wolumin");

            }


    }
    public void ustawComboZwrot(){
        comboZwrotCzytelnik.removeAllItems();
        comboZwrotWolumin.removeAllItems();
        List czyt = Czytelnik.getCzytelnicy();

        for (Object o : czyt) {
            String item = ((Czytelnik) o).getImie() + " " + ((Czytelnik) o).getNazwisko();
            comboZwrotCzytelnik.addItem(item);
        }

    }
    public void ustawZwrotDane(){
        comboZwrotWolumin.removeAllItems();
        int id = comboZwrotCzytelnik.getSelectedIndex();
        if(id>-1){
            List czyt = Czytelnik.getCzytelnicy();
            Object czytelnik = czyt.get(id);
            List wypo = ((Czytelnik)czytelnik).getWypozyczone();


            for (Object o : wypo) {
                String item = ((Zbior) o).getNrEw() + " " + ((Zbior) o).getTytul() + " " + ((Zbior) o).getKeyWord() + " " + ((Zbior) o).isWypozyczona();
                comboZwrotWolumin.addItem(item);
            }
        }
    }
    public void dodajZwrot(){
        int idCzyt = comboZwrotCzytelnik.getSelectedIndex();
        int idWol = comboZwrotWolumin.getSelectedIndex();
        if(idCzyt < 0 || idWol < 0){
            JOptionPane.showMessageDialog(null,"Prosze wybrać czytelnika oraz wolumin do zwrotu");

        }else{
            List czyt = Czytelnik.getCzytelnicy();
            Object czytelnik = czyt.get(idCzyt);
            List wypo = ((Czytelnik)czytelnik).getWypozyczone();
            Object wypozyczone = wypo.get(idWol);

            String nrew = ((Zbior)wypozyczone).getNrEw();
            int rowId = getRowByValue(tabelaWolumin.getModel(),nrew);
            ((Czytelnik)czytelnik).delWypozyczona(idWol);
            tabelaWolumin.setValueAt("false",rowId,8);
            String[][] data = Czytelnik.getCzytelnicyString();
            rysujTabelaCzyt(data);
            int bdId = bd.getCzytId(((Czytelnik) czytelnik).getImie(),((Czytelnik) czytelnik).getNazwisko());
            int bdIdWypo = bd.getIdWolu(((Zbior)wypozyczone).getNrEw());
            bd.queryDodajZwrot(bdId,bdIdWypo);
            comboZwrotWolumin.removeAllItems();
            Zbior.setPrzedmioty(new LinkedList());
            Ksiazka.updateBase();
            Czasopismo.updateBase();
            Plyta.updateBase();
            ustawZwrotDane();
            JOptionPane.showMessageDialog(null,"Zatwierdzono zwrot woluminu");
        }
    }
    public void szukajCzyt(){

        String szukany = tfSzukajCzyt.getText();
        List<Object> wyniki = new LinkedList<>();
        List czytelnicy = Czytelnik.getCzytelnicy();

        for (Object czyt : czytelnicy) {
            String imieNaziwsko = ((Czytelnik) czyt).getImie() + " " + ((Czytelnik) czyt).getNazwisko();
            if (((Czytelnik) czyt).getImie().contains(szukany) || ((Czytelnik) czyt).getNazwisko().contains(szukany) || imieNaziwsko.contains(szukany)) {
                wyniki.add(czyt);
            }

        }
        String[][] data = new String[wyniki.size()][3];
        for (int i = 0; i < wyniki.size(); i++) {
            Object item = wyniki.get(i);
            data[i][0] = ((Czytelnik)item).getImie();
            data[i][1] = ((Czytelnik)item).getNazwisko();
            data[i][2] = String.valueOf(((Czytelnik)item).getWypozyczone().size());
        }
        rysujTabelaCzyt(data);


    }
    public void szukajWolum(){
        comboTypWoluSzukaj.setSelectedIndex(0);
        String szukany = tfSzukajWolum.getText();
        List<Object> wyniki = new LinkedList<>();
        List woluminy = Zbior.getPrzedmiotyList();

        for (Object wolu : woluminy) {
            if (((Zbior) wolu).getTytul().contains(szukany) || ((Zbior) wolu).getKeyWord().contains(szukany) || ((Zbior) wolu).getNrEw().contains(szukany)) {
                wyniki.add(wolu);
            }

        }
        String[][] data = new String[wyniki.size()][9];
        for (int i = 0; i < wyniki.size(); i++) {
            Object item = wyniki.get(i);
            if(item.getClass() == Ksiazka.class){
                data[i][0] = ((Zbior) item).getNrEw();
                data[i][1] = ((Zbior) item).getTytul();
                data[i][2] = ((Ksiazka) item).getAutor();
                data[i][3] = "brak";
                data[i][4] = "brak";
                data[i][5] = ((Ksiazka) item).getRok();
                data[i][6] = "brak";
                data[i][7] = ((Zbior) item).getKeyWord();
                data[i][8] = String.valueOf(((Zbior) item).isWypozyczona());

            }else if(item.getClass() == Czasopismo.class){
                data[i][0] = ((Zbior) item).getNrEw();
                data[i][1] = ((Zbior) item).getTytul();
                data[i][2] = "brak";
                data[i][3] = ((Czasopismo) item).getNumer();
                data[i][4] = "brak";
                data[i][5] = ((Czasopismo) item).getRok();
                data[i][6] = ((Czasopismo) item).getWydawnictwo();
                data[i][7] = ((Zbior) item).getKeyWord();
                data[i][8] = String.valueOf(((Zbior) item).isWypozyczona());

            }else if(item.getClass() == Plyta.class){
                data[i][0] = ((Zbior) item).getNrEw();
                data[i][1] = ((Zbior) item).getTytul();
                data[i][2] = "brak";
                data[i][3] = "brak";
                data[i][4] = ((Plyta) item).getWykonawca();
                data[i][5] = "brak";
                data[i][6] = "brak";
                data[i][7] = ((Zbior) item).getKeyWord();
                data[i][8] = String.valueOf(((Zbior) item).isWypozyczona());

            }else System.out.println("error - wrong class");


        }
        rysujTabelaWolum(data);
    }
    public void rysujTabelaWolum(String[][] data){

        TableModel model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tabelaWolumin.setModel(model);
    }
    public void rysujTabelaCzyt(String[][] data){
        TableModel model = new DefaultTableModel(data, columnNamesCzyt) {
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
        tabelaCzytelnicy.setModel(model);
    }
    public int getRowByValue(TableModel model, String nrew) {
        for (int i = model.getRowCount() - 1; i >= 0; --i) {
            for (int j = model.getColumnCount() - 1; j >= 0; --j) {
                if (model.getValueAt(i, j).equals(nrew)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
