import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class BazaDanych {
    private Connection connection = null;
    public BazaDanych(){

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Biblioteka","postgres","student");
            if(connection!=null){

            }else{
                System.out.println("Connection failed");
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
    public List queryWyswietlCzytelnik(){
        try{
            Statement statement = connection.createStatement();
            String query = "Select \"id_czyt\", \"imie\",\"nazwisko\",\"ilewypo\" from \"Czytelnicyy\";";
            ResultSet result = statement.executeQuery(query);
            List queryData = new LinkedList<>();
            while(result.next()){
                for (int i = 1; i <= 4; i++) {
                    queryData.add(result.getString(i));
                }

            }
            return queryData;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public List queryWyswietlWolumin(){
        try{
            Statement statement = connection.createStatement();
            String query = "Select tytul, nrew, slowo, stanwypo from \"Woluminyy\";";
            ResultSet result = statement.executeQuery(query);
            List queryData = new LinkedList<>();
            while(result.next()){
                for (int i = 1; i <= 4; i++) {
                    queryData.add(result.getString(i));
                }

            }
            return queryData;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public List queryWyswietlKsiazki(){
        try{
            Statement statement = connection.createStatement();
            String query = "Select tytul, nrew, slowo, autor, rok, stanwypo from \"Woluminyy\""+
            "where numer = \'null\' AND wykonawca = \'null\';";
            ResultSet result = statement.executeQuery(query);
            List queryData = new LinkedList<>();
            while(result.next()){
                for (int i = 1; i <= 6; i++) {
                    queryData.add(result.getString(i));
                }

            }

            return queryData;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public List queryWyswietlCzasopisma(){
        try{
            Statement statement = connection.createStatement();
            String query = "Select tytul, nrew, slowo, numer, rok, wydawnictwo, stanwypo from \"Woluminyy\""+
                    "where autor = \'null\' AND wykonawca = \'null\';";

            ResultSet result = statement.executeQuery(query);
            List queryData = new LinkedList<>();
            while(result.next()){
                for (int i = 1; i <= 7; i++) {
                    queryData.add(result.getString(i));
                }

            }

            return queryData;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public List queryWyswietlPlyty(){
        try{
            Statement statement = connection.createStatement();
            String query = "Select tytul, nrew, slowo, wykonawca, stanwypo from \"Woluminyy\""+
                    "where autor = \'null\' AND numer = \'null\' and rok = \'null\';";
            ResultSet result = statement.executeQuery(query);
            List queryData = new LinkedList<>();

            while(result.next()){
                for (int i = 1; i <= 5; i++) {
                    queryData.add(result.getString(i));
                }

            }

            return queryData;
        } catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public int getCzytId(String imie, String nazwisko){
        try{
            Statement statement = connection.createStatement();
            String query = "Select id_czyt from \"Czytelnicyy\"" +
                    "where imie = \'"+imie+"\' and nazwisko = \'"+nazwisko+"\';";
            ResultSet result = statement.executeQuery(query);
            String queryData = "";
            while(result.next()){
                for (int i = 1; i <= 1; i++) {
                    queryData += result.getString(i);
                }

            }
            int id = Integer.parseInt(queryData);

            return id;

        } catch(Exception e){
            System.out.println(e);
        }
        return -1;
    }
    public int getIdWolu(String nrew){
        try{
            Statement statement = connection.createStatement();
            String query = "Select id_wolu from \"Woluminyy\"" +
                    "where nrew = \'"+nrew+"\';";
            ResultSet result = statement.executeQuery(query);
            String queryData = "";
            while(result.next()){
                for (int i = 1; i <= 1; i++) {
                    queryData += result.getString(i);
                }

            }
            int id = Integer.parseInt(queryData);

            return id;

        } catch(Exception e){
            System.out.println(e);
        }
        return -1;
    }

    public void queryWyswietlWypozyczone(int idCzyt){
        try{
            Statement statement = connection.createStatement();
            String query = "Select \"Woluminy\".* from \"Wypozyczenia\"" +
                    "join \"Czytelnicy\" on \"Czytelnicy\".\"id_czyt\" = \"Wypozyczenia\".\"id_czyt\"" +
                    "join \"Woluminy\" on \"Woluminy\".\"id_wolu\" = \"Wypozyczenia\".\"id_wolu\"" +
                    "where \"Czytelnicy\".\"id_czyt\" = " + idCzyt +
                    ";";
            ResultSet result = statement.executeQuery(query);
            String queryData = "";
            while(result.next()){
                for (int i = 1; i <= 10; i++) {
                    queryData += result.getString(i)+ " ";
                }
                queryData += "\n";
            }

        } catch(Exception e){
            System.out.println(e);
        }
    }
    public void queryDodajWypo(int idCzyt, int idWolu){
        try{
            Statement statement = connection.createStatement();

            String query = "INSERT INTO \"Wypozyczenia\"" +
                    "VALUES(DEFAULT,"+idCzyt+","+idWolu+");";
            String query2 = "UPDATE \"Woluminyy\"" +
                    "set \"stanwypo\" = \'true\'" +
                    "where \"id_wolu\" = "+idWolu+";";
            statement.executeUpdate(query);
            statement.executeUpdate(query2);


        } catch(Exception e){
            System.out.println(e);
        }
    }
    public void queryDodajZwrot(int idCzyt, int idWolu){
        try{
            System.out.println(idWolu);
            Statement statement = connection.createStatement();

            String query = "DELETE FROM \"Wypozyczenia\"" +
                    "where id_wolu = "+idWolu+" and id_czyt = "+idCzyt;
            String query2 = "UPDATE \"Woluminyy\"" +
                    "set \"stanwypo\" = \'false\'" +
                    "where \"id_wolu\" = "+idWolu+";";
            statement.executeUpdate(query);
            statement.executeUpdate(query2);


        } catch(Exception e){
            System.out.println(e);
        }
    }
    public void queryDodajCzytelnik(String imie, String nazwisko){
        try{
            Statement statement = connection.createStatement();
            String query = "INSERT INTO \"Czytelnicyy\"" +
                    "VALUES(DEFAULT,\'"+imie+"\',\'"+nazwisko+"\',\'0\');";
            statement.executeUpdate(query);

        } catch(Exception e){
            System.out.println(e);
        }
    }
    public void dodajWolumin(String nrEw, String tytul, String autor, String numer
    , String wykonawca, String rok, String wydawnictwo, String slowo, String stanWypo){
        try{
            Statement statement = connection.createStatement();

            String query = "INSERT INTO \"Woluminyy\"" +
                    " VALUES(DEFAULT,\'"+nrEw+"\',\'"+tytul+"\',\'"+autor+"\',\'"+numer+"\',\'"+wykonawca+"\',\'"+rok+"\',\'"+wydawnictwo+"\',\'"+slowo+"\',\'"+stanWypo+"\');";

            statement.executeUpdate(query);
        } catch(Exception e){
            System.out.println(e);
        }

    }
    public void usunCzytelnik(String imie, String nazwisko){
        try{
            Statement statement = connection.createStatement();

            String query = "DELETE from \"Czytelnicyy\"" +
                    "where \"imie\" = \'"+imie +"\' and \"nazwisko\" = \'"+nazwisko+"\';";

            statement.executeUpdate(query);



        } catch(Exception e){
            System.out.println(e);
        }
    }
    public void usunWolumin(String idWolu){
        try{
            Statement statement = connection.createStatement();

            String query = "DELETE from \"Woluminyy\"" +
                    "where \"nrew\" = \'"+idWolu +"\';";

            statement.executeUpdate(query);



        } catch(Exception e){
            System.out.println(e);
        }
    }
//    public void ileWypo(int idCzyt){
//        try{
//            Statement statement = connection.createStatement();
//            Statement statement1 = connection.createStatement();
//            Statement statement2 = connection.createStatement();
//
//            String queryImie = "Select imie from \"Czytelnicyy\"" +
//                    "where \"id_czyt\" = "+idCzyt +";";
//            String queryNazwisko = "Select nazwisko from \"Czytelnicyy\"" +
//                    "where \"id_czyt\" = "+idCzyt +";";
//
//            String queryWypozyczone = "Select nrew, tytul, slowo, stanwypo from \"Woluminyy\"" +
//                    "join \"Wypozyczenia\" on \"Wypozyczenia\".\"id_wypo\" = \"Woluminyy\".\"id_wolu\"" +
//                    "where \"Wypozyczenia\".\"id_czyt\" = "+idCzyt+";";
//            ResultSet result = statement.executeQuery(queryImie);
//            ResultSet result2 = statement1.executeQuery(queryNazwisko);
//            ResultSet result3 = statement2.executeQuery(queryWypozyczone);
//
//
//            String queryData = "";
//            while(result.next()){
//                for (int i = 1; i <= 1; i++) {
//                    queryData += result.getString(i);
//                }
//            }
//            String queryData2 = "";
//            while(result2.next()){
//                for (int i = 1; i <= 1; i++) {
//                    queryData2 += result2.getString(i);
//                }
//            }
//
//            List wypozyczone = new LinkedList<Zbior>();
//            while(result3.next()){
//                for (int i = 1; i <= 8; i++) {
//
//                    Zbior zb = new Zbior(result3.getString(i),result3.getString(i+1),result3.getString(i+2));
//                    wypozyczone.add(zb);
//                    i=i+3;
//                }
//            }
//
//            System.out.println(wypozyczone);
//            int index = Czytelnik.szukajIndeksu(queryData, queryData2);
//            System.out.println(index);
//            if(index > -1)((Czytelnik)Czytelnik.getCzytelnicy().get(index)).setWypozyczone(wypozyczone);
//
//
//        } catch(Exception e){
//            System.out.println(e);
//        }
//    }
}
