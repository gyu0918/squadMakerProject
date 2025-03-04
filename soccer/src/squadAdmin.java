import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;

public class squadAdmin {
    private soccerTeamDAO soccerTeamDAO = new soccerTeamDAO();
    private soccerPlayerDAO soccerPlayerDAO = new soccerPlayerDAO();


    squadAdmin() throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean endFlag = true;
        while(endFlag){
            System.out.println("1. 나의 스쿼드 보기 2. 선수 검색 3. 종료" );
            int choice = Integer.parseInt(br.readLine());
            switch(choice){
                case 1:
                    //스쿼드 보여주기
                    soccerTeamDAO.selectAll();

                    System.out.println(" 1. 선수 방출 2. 선수 엽입 ");
                    int choiceNum = Integer.parseInt(br.readLine());
                    if (choiceNum == 1){
                        soccerTeamDAO.outPlayer();
                    }else if (choiceNum == 2){
                        soccerTeamDAO.recruitPlayer();
                    }
                    break ;
                case 2:
                    searchPlayer();
                    break ;
                case 3:
                    endFlag = false ;
                    break ;
            }
        }
    }
    public void searchPlayer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("1. 국가별 검색  2. 팀별 검색 3. 포지션별 검색");
        int choice = Integer.parseInt(br.readLine());
        switch(choice){
            case 1:
                soccerPlayerDAO.searchCountry();
                break;
            case 2:
                soccerPlayerDAO.searchTeam();
                break;
            case 3:
                soccerPlayerDAO.searchPoisition();
                break;
        }
    }
}
