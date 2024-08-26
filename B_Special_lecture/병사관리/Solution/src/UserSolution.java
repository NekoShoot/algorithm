import java.util.Arrays;
import java.util.LinkedList;

class UserSolution
{
    // 고용된 병사들을 관리할 연결 리스트
    LinkedList<int[]> list;

    public void init()
    {
        list = new LinkedList<>();
    }

    // 고용
    public void hire(int mID, int mTeam, int mScore)
    {
        int[] soldier = { mID, mTeam, mScore };
        list.add(soldier);
    }

    // 해고
    public void fire(int mID)
    {
        // 리스트 순회로 탐색 or hashMap으로 mapping?
        for(int i = 0; i < list.size(); i++) {
            int[] hired = list.get(i);
            int hID = hired[0];
            
            if(hID == mID) {
                list.remove(i);
                return;
            }
        }
    }

    // 고유번호가 mID인 병사의 평판점수 mScore로 변경
    public void updateSoldier(int mID, int mScore)
    {
        for(int i = 0; i < list.size(); i++) {
            int[] hired = list.get(i);
            int hID = hired[0];

            if(hID == mID) {
                hired[2] = mScore;
                return;
            }

        }
    }

    public void updateTeam(int mTeam, int mChangeScore)
    {
        for(int i = 0; i < list.size(); i++) {
            int[] hired = list.get(i);
            int hTeam = hired[1];

            if(hTeam == mTeam) {
                int hScore= hired[2]; // mScore
                if(hScore + mChangeScore > 5) {
                    hired[2] = 5;
                } else if(hired[2] + mChangeScore < 1) {
                    hired[2] = 1;
                } else {
                    hired[2] = hScore + mChangeScore;
                }
            }
        }

    }

    public int bestSoldier(int mTeam)
    {
        int[] bestSoldier = new int[2]; // 0번이 평판 점수, 1번이 고유번호
        for(int i = 0; i < list.size(); i++) {
            int[] hired = list.get(i);
            int hTeam = hired[1];

            if(hTeam == mTeam && hired[2] >= bestSoldier[0]) {
                if(hired[2] == bestSoldier[0] && hired[0] < bestSoldier[1]) continue; // 평판 점수가 같은데 고유번호가 작으면 cut
                bestSoldier[0] = hired[2]; // mScore
                bestSoldier[1] = hired[0]; // mID
            }
        }
        return bestSoldier[1];
    }
}