import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

public class soccerTeamDAO {
    private String url;
    private String user;
    private String password;
    private Connection conn;

    public soccerTeamDAO() throws IOException, SQLException {
        url = "jdbc:mariadb://localhost:3306/soccer";
        user = "root";
        password = "1234";
        conn = DriverManager.getConnection(url, user, password);
    }

    public void selectAll() {
        String query = "SELECT sp.soccerPlayerName, sp.soccerPlayerIncome, country.countryName, team.teamName, s_position.soccerPositionName, sp.squad " +
                "FROM soccerPlayer sp, country, soccerPosition s_position, team " +
                "WHERE sp.country_id = country.country_id " +
                "AND sp.team_id = team.team_id " +
                "AND sp.soccerPosition_id = s_position.soccerPosition_id";

        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            //ResultSet을 탐색해 id만 꺼내서 비교한다
            while (rs.next()) {
                if (rs.getBoolean("squad")) {
                    System.out.println(rs.getString(1) + " " + rs.getString(2) + " " +
                            rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

    }

    public void recruitPlayer() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        selectAll();
        System.out.println(" 영입할 선수 이름을 적으세요.");
        String playerName = br.readLine();
        String query = "update soccerPlayer set squad = ? where soccerPlayerName = ?";

        try (
                Statement stmt = conn.createStatement();
                PreparedStatement cursor = conn.prepareStatement(query)) {

            //sql 파라미터 설정
            cursor.setBoolean(1,true);
            cursor.setString(2,playerName);
            cursor.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

    }

    public void outPlayer() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        prtPlayer();
        System.out.println(" 방출 선수 이름을 적으세요.");
        String playerName = br.readLine();
        String query = "update soccerPlayer set squad = ? where soccerPlayerName = ?";

        try (
                Statement stmt = conn.createStatement();
                PreparedStatement cursor = conn.prepareStatement(query)) {

            //sql 파라미터 설정
            cursor.setBoolean(1,false);
            cursor.setString(2,playerName);
            cursor.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

    }



    public void prtPlayer() {
        String query = "SELECT sp.soccerPlayerName, sp.soccerPlayerIncome, country.countryName, team.teamName, s_position.soccerPositionName, sp.squad " +
                "FROM soccerPlayer sp, country, soccerPosition s_position, team " +
                "WHERE sp.country_id = country.country_id " +
                "AND sp.team_id = team.team_id " +
                "AND sp.soccerPosition_id = s_position.soccerPosition_id";

        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            //ResultSet을 탐색해 id만 꺼내서 비교한다
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " +
                        rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}
