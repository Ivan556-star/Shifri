import java.util.Random;
import java.util.Scanner;

public interface shifr {
    Scanner cin = new Scanner(System.in);
    Random r = new Random();
    String[] crypt(int choiceShifr, String Text);

    default String get_Russian_Alphabet(){
        return "АЙКШДЛТЗЁЮЭЖЯБСХУВМОЕНЬЫФЩПГИЧЪЦР";
    }

    default String get_English_Alphabet(){
        return "QOUWIKCMRDBTVAGHJEXNFLYPZS";
    }

    default String get_Symbols(){
        return "^№(3;${16<[=:9@?»/*),%'~.84\\\"!>+|&#50}]-«\\_27";
    }

    default String MyCopy(int start, int finish, String str){
        String tmpS = "";
        for (int i = start; i < finish; i++)
            tmpS += str.charAt(i);
        return tmpS;
    }

}
