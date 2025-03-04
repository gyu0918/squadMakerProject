import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class soccerPlayerDAO {
    private String url;
    private String user;
    private String password;
    private Connection conn;

    public soccerPlayerDAO() throws IOException, SQLException {
        url = "jdbc:mariadb://localhost:3306/soccer";
        user = "root";
        password = "1234";
        conn = DriverManager.getConnection(url, user, password);
    }

    public void searchCountry() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String query = "select  sp.soccerPlayerName, sp.soccerPlayerIncome, country.countryName " +
                "from soccerPlayer sp, country" +
                " where sp.country_id = country.country_id";

        System.out.println("검색하고 싶은 국가를 적으세요. ");
        String countryName = br.readLine();
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            //ResultSet을 탐색해 id만 꺼내서 비교한다
            while (rs.next()) {
                if (rs.getString("countryName").equals(countryName)) {
                    System.out.println(rs.getString(1) + " " + rs.getString(2) + " " +
                            rs.getString(3));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public void searchPoisition() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String query = "select  sp.soccerPlayerName, sp.soccerPlayerIncome, soccerPosition.soccerPositionName " +
                "from soccerPlayer sp, soccerPosition " +
                " where sp.soccerPosition_id = soccerPosition.soccerPosition_id";

        System.out.println("검색하고 싶은 포지션를 적으세요. ");
        String soccerPositionName = br.readLine();
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            //ResultSet을 탐색해 id만 꺼내서 비교한다
            while (rs.next()) {
                if (rs.getString("soccerPositionName").equals(soccerPositionName)) {
                    System.out.println(rs.getString(1) + " " + rs.getString(2) + " " +
                            rs.getString(3));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public void searchTeam() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String query = "select  sp.soccerPlayerName, sp.soccerPlayerIncome, team.teamName " +
                "from soccerPlayer sp, team" +
                " where sp.team_id = team.team_id";

        System.out.println("검색하고 싶은 팀을적으세요. ");
        String teamName = br.readLine();
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            //ResultSet을 탐색해 id만 꺼내서 비교한다
            while (rs.next()) {
                if (rs.getString("teamName").equals(teamName)) {
                    System.out.println(rs.getString(1) + " " + rs.getString(2) + " " +
                            rs.getString(3));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

}
