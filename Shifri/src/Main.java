import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    static private Scanner cin = new Scanner(System.in);
    static private Random r = new Random();
    public static void main(String[] args) {
        do {
            System.out.print("Зашифровать(1)/расшифровать(0): ");
            int[] arrShifry = shuffleArray(new int[]{2, 3});
            int choiceUser = cin.nextInt();
            String zaglushka = cin.nextLine();
            if (choiceUser == 1) {
                System.out.println("Введите текст");
                String KEY;
                String str = cin.nextLine().replace(" ", "_");
                // String[] text = Shifr_Vijenera(1, str, "");
                String[] text = Shifr_Cezarya(1, str, "");

                System.out.println("Зашифрованный текст:\n" + text[0] + "\nсекретный ключ: " + text[1]);

                // 1) КЁД9ПЧХ{VBEIX]
                // 2)
                // 33
            } else if (choiceUser == 0) {
                teleport:
                {
                    System.out.println("Введите ключ: ");
                    String KEY = cin.nextLine();
                    for (int i = 0; i < KEY.length(); i++)
                        if (!Character.isDigit(KEY.charAt(i))) {
                            System.out.println("Ошибка, ключ указан не верно, попробуйте снова\n");
                            break teleport;
                        }

                    System.out.println("Введите зашифрованный текст");
                    String[] CipherText = {cin.nextLine(), KEY};
                    CipherText = Shifr_Cezarya(2, CipherText[0], KEY);
                    //  CipherText = Shifr_Vijenera(2, CipherText[0], CipherText[1]);
                    System.out.println("Расшифрованный текст:\n" + CipherText[0]);
                }
            } else {
                System.out.println("Ошибка, введите 1 ли 0");
                String zaglushka2 = cin.nextLine();
            }
        } while (true);
    }

    static int[] shuffleArray(int[] ar)
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }

    static private String get_Russian_Alphabet(){
        return "АЙКШДЛТЗЁЮЭЖЯБСХУВМОЕНЬЫФЩПГИЧЪЦР";
    }

    static private String get_English_Alphabet(){
        return "QOUWIKCMRDBTVAGHJEXNFLYPZS";
    }

    static private String get_Symbols(){
        return "^№(3;${16<[=:9@?»/*),%'~.84\\\"!>+|&#50}]-«\\_27";
    }

    static private String MyCopy(int start, int finish, String str){
        String tmpS = "";
        for (int i = start; i < finish; i++)
            tmpS += str.charAt(i);
        return tmpS;
    }

    static private String What_Language(String symbol){
        if (get_Russian_Alphabet().contains(symbol))
            return "ru";

        if (get_English_Alphabet().contains(symbol))
            return "en";

        if (get_Symbols().contains(symbol))
            return "sim";

        if( "🔒".contains(symbol) || "🔑".contains(symbol) || "🔓".contains(symbol))
            return "true";
        return "false";
    }

    static private boolean prime(int number){
        int counter = 0;
        for (int i = 1; i < number + 1; i++)
            if (number % i == 0)
                counter++;
        return counter == 2;
    }

    static private String[] Shifr_Vijenera(int choiceSHIFR, String text, String KEY){
        String unswer = "";
        text = text.toUpperCase();

        if (choiceSHIFR == 1){
            int k = 0;
            while (KEY.length() < text.length()){
                if (get_Russian_Alphabet().contains(String.valueOf(text.charAt(k)))){
                    KEY += get_Russian_Alphabet().charAt(r.nextInt(get_Russian_Alphabet().length()));
                }
                if (get_English_Alphabet().contains(String.valueOf(text.charAt(k)))){
                    KEY += get_English_Alphabet().charAt(r.nextInt(get_English_Alphabet().length()));
                }
                if (get_Symbols().contains(String.valueOf(text.charAt(k)))){
                    KEY += get_Symbols().charAt(r.nextInt(get_Symbols().length()));
                }
                k++;
            }

            int indexTMP;
            for (int i = 0; i < text.length(); i++) {
                if (get_Russian_Alphabet().contains(String.valueOf(text.charAt(i)))){
                    indexTMP = get_Russian_Alphabet().indexOf(String.valueOf(text.charAt(i)));
                    unswer += get_Russian_Alphabet().
                            charAt(Math.abs((indexTMP + get_Russian_Alphabet().
                                    indexOf(String.valueOf(KEY.charAt(i)))) % get_Russian_Alphabet().length()));
                }
                else if (get_English_Alphabet().contains(String.valueOf(text.charAt(i)))){
                    indexTMP = get_English_Alphabet().indexOf(String.valueOf(text.charAt(i)));
                    unswer += get_English_Alphabet().
                            charAt(Math.abs((indexTMP + get_English_Alphabet().
                                    indexOf(String.valueOf(KEY.charAt(i)))) % get_English_Alphabet().length()));
                }
                else if (get_Symbols().contains(String.valueOf(text.charAt(i)))){
                    indexTMP = get_Symbols().indexOf(String.valueOf(text.charAt(i)));
                    unswer += get_Symbols().
                            charAt(Math.abs((indexTMP + get_Symbols().
                                    indexOf(String.valueOf(KEY.charAt(i)))) % get_Symbols().length()));
                }
                else
                    System.out.println("Error");
            }

            unswer = KEY + unswer;
            if (99 < KEY.length() && KEY.length() < 1000)
                KEY = String.valueOf(KEY.length());
            else if (9 < KEY.length() && KEY.length() < 100)
                KEY = "0" + KEY.length();
            else
                KEY = "00" + KEY.length();

            return new String[]{unswer, KEY};
        }

        else {

            String keyShifr = MyCopy(0, Integer.parseInt(KEY), text);
            text= MyCopy(Integer.parseInt(KEY), text.length(), text);
            if (keyShifr.length() < text.length()) {
                text = MyCopy(0, text.length() - 1, text);
            }

            while (keyShifr.length() > text.length()){
                int tmp = r.nextInt(3);
                if (tmp == 0)
                    text += get_Russian_Alphabet().charAt(r.nextInt(get_Russian_Alphabet().length()));
                else if (tmp == 1)
                    text += get_English_Alphabet().charAt(r.nextInt(get_English_Alphabet().length()));
                else
                    text += get_Symbols().charAt(r.nextInt(get_Symbols().length()));
            }

            try {
                int indexTMP;
                for (int i = 0; i <text.length(); i++) {
                    if (get_Russian_Alphabet().contains(String.valueOf(text.charAt(i)))){
                        indexTMP = get_Russian_Alphabet().indexOf(String.valueOf(text.charAt(i)));
                        unswer += get_Russian_Alphabet().
                                charAt(Math.abs((indexTMP - get_Russian_Alphabet().
                                        indexOf(String.valueOf(keyShifr.charAt(i)))) % get_Russian_Alphabet().length()));
                    }
                    else if (get_English_Alphabet().contains(String.valueOf(text.charAt(i)))){
                        indexTMP = get_English_Alphabet().indexOf(String.valueOf(text.charAt(i)));
                        unswer += get_English_Alphabet().
                                charAt(Math.abs((indexTMP - get_English_Alphabet().
                                        indexOf(String.valueOf(keyShifr.charAt(i)))) % get_English_Alphabet().length()));
                    }
                    else if (get_Symbols().contains(String.valueOf(text.charAt(i)))){
                        indexTMP = get_Symbols().indexOf(String.valueOf(text.charAt(i)));
                        unswer += get_Symbols().
                                charAt(Math.abs((indexTMP - get_Symbols().
                                        indexOf(String.valueOf(keyShifr.charAt(i)))) % get_Symbols().length()));
                    }
                    else
                        System.out.println("Ошибка, вы ввели символ, которого нет в стандартной клавиатуре");
                }
            }catch (IndexOutOfBoundsException e){
                return new String[]{"Ошибка, вы указали неверный ключ!", KEY};
            }

            KEY = MyCopy(0, KEY.length() - 2, KEY);
            return new String[]{unswer, KEY};
        }
    }

//    static private String[] Shifr_Transposition(int choiceSHIFR, String text, String KEY){
//        String unswer = "";
//        if (choiceSHIFR == 1){
//            int keyShifr = r.nextInt(((9 - 1) + 1) + 1) % text.length();
//            char[] arrCh = text.toCharArray();
//            for (int i = 0; i <= keyShifr; i++) {
//                char tmpCh = arrCh[i];
//                arrCh[i] = arrCh[(i + keyShifr) % text.length()];
//                arrCh[(i + keyShifr) % text.length()] = tmpCh;
//            }
//            for (char ch : arrCh) unswer += ch;
//
//            if (unswer.length() >= 9){
//                int indexKey = r.nextInt(10);
//                unswer = MyCopy(0, indexKey, unswer) + keyShifr + MyCopy(indexKey, unswer.length(), unswer);
//                KEY += indexKey + "2";
//            }
//            else {
//                int indexKey = r.nextInt(unswer.length());
//                unswer = MyCopy(0, indexKey, unswer) + keyShifr + MyCopy(indexKey, unswer.length(), unswer);
//                KEY += indexKey + "2";
//            }
//            return new String[]{unswer, KEY};
//        }
//
//        else {
//            int keyShifrInt;
//            char keyShifrChar;
//            if (text.length() < 1)
//                for (int i = 0; i < r.nextInt(((9 - 1) + 1)) + 1; i++)
//                    text += get_Symbols().charAt(r.nextInt(get_Symbols().length()));
//
//            boolean lol1= false, lol2 = false;
//            if (Character.isDigit(text.charAt(Integer.parseInt(String.valueOf(KEY.charAt(KEY.length() - 2))) % text.length()))) {
//                keyShifrInt = Integer.parseInt(
//                        String.valueOf(
//                                text.charAt(
//                                        Integer.parseInt(
//                                                String.valueOf(
//                                                        KEY.charAt(
//                                                                KEY.length() - 2))) % text.length()
//                                )
//                        )
//                );
//                lol1 = true;
//            }
//            else {
//                keyShifrChar = (
//                        text.charAt(
//                                Integer.parseInt(
//                                        String.valueOf(
//                                                KEY.charAt(
//                                                        KEY.length() - 2))) % text.length()
//                        )
//                );
//                lol2 = true;
//            }
//
//            text = MyCopy(0,
//                    Integer.parseInt(String.valueOf(KEY.charAt(KEY.length() - 2)))
//                            + Integer.parseInt(String.valueOf(KEY.charAt(KEY.length() - 2))) + 1 , text);
//
//            if (text.length() < 1)
//                for (int i = 0; i < r.nextInt(9 - 1 + 1) + 1; i++)
//                    text += get_Symbols().charAt(r.nextInt(get_Symbols().length()));
//
//            char[] arrCh = text.toCharArray();
//            int i = keyShifr;
//
//            if (i >= arrCh.length)
//                i = keyShifr % arrCh.length; // чтобы не выйти за границы списка
//
//            while (i >= 0) {
//                char tmpCh = arrCh[i];
//                arrCh[i] = arrCh[(i + keyShifr) % text.length()];
//                arrCh[(i + keyShifr) % text.length()] = tmpCh;
//                i--;
//            }
//
//            unswer = MyCopy(0 , arrCh.length, String.valueOf(arrCh));
//            KEY = MyCopy(0, KEY.length() - 2, KEY);
//            return new String[]{unswer, KEY};
//        }
//    }

    static private String[] Shifr_Cezarya(int choiceSHIFR, String text, String KEY){
        String unswer = "";
        text = text.toUpperCase();

        if (choiceSHIFR == 1){
            int keyShifr = r.nextInt(9 - 1 + 1) + 1, indexTMP;
            for (var i: text.toCharArray()) {
                if(get_Russian_Alphabet().contains(String.valueOf(i))){
                    indexTMP = get_Russian_Alphabet().indexOf(String.valueOf(i));
                    unswer += get_Russian_Alphabet().charAt(Math.abs(indexTMP + keyShifr) % get_Russian_Alphabet().length());
                }
                else if(get_English_Alphabet().contains(String.valueOf(i))){
                    indexTMP = get_English_Alphabet().indexOf(String.valueOf(i));
                    unswer += get_English_Alphabet().charAt(Math.abs(indexTMP + keyShifr) % get_English_Alphabet().length());
                }
                else if(get_Symbols().contains(String.valueOf(i))){
                    indexTMP = get_Symbols().indexOf(String.valueOf(i));
                    unswer += get_Symbols().charAt(Math.abs(indexTMP + keyShifr) % get_Symbols().length());
                }
                else
                    System.out.println("Error");
            }

            int indexKey;
            if (unswer.length() >= 9){
                indexKey = r.nextInt(10);
                unswer = MyCopy(0, indexKey, unswer) + keyShifr + MyCopy(indexKey, unswer.length(), unswer);
                KEY += indexKey + "3";
            }
            else{
                indexKey = r.nextInt(unswer.length());
                unswer = MyCopy(0, indexKey, unswer) + keyShifr + MyCopy(indexKey, unswer.length(), unswer);
                KEY += indexKey + "3";
            }
            return new String[]{unswer, KEY};
        }

        else {
            if (text.length() < 1)
                text = String.valueOf(get_Symbols().charAt(r.nextInt(get_Symbols().length())));

            int keyShifr;
            if (Character.isDigit(text.charAt(Integer.parseInt(String.valueOf(KEY.charAt(KEY.length() - 2))) % text.length()))) {
                keyShifr = Integer.parseInt(
                        String.valueOf(
                                text.charAt(
                                        Integer.parseInt(
                                                String.valueOf(
                                                        KEY.charAt(
                                                                KEY.length() - 2))) % text.length()
                                )
                        )
                );
            }
            else {
                keyShifr = (
                        text.charAt(
                                Integer.parseInt(
                                        String.valueOf(
                                                KEY.charAt(
                                                        KEY.length() - 2))) % text.length()
                        )
                );
            }

            String tmpText = MyCopy(0,
                    Integer.parseInt(String.valueOf(KEY.charAt(KEY.length() - 2)))
                            , text);
            tmpText += MyCopy(Integer.parseInt(String.valueOf(KEY.charAt(KEY.length() - 2))) + 1,
                    text.length()
                    , text);
            text = tmpText; // wadawd

            int indexTMP;
            for (var i: text.toCharArray()) {
                if(get_Russian_Alphabet().contains(String.valueOf(i))){
                    indexTMP = get_Russian_Alphabet().indexOf(String.valueOf(i));
                    unswer += get_Russian_Alphabet().charAt(Math.abs(indexTMP - keyShifr) % get_Russian_Alphabet().length());
                }
                else if(get_English_Alphabet().contains(String.valueOf(i))){
                    indexTMP = get_English_Alphabet().indexOf(String.valueOf(i));
                    unswer += get_English_Alphabet().charAt(Math.abs(indexTMP - keyShifr) % get_English_Alphabet().length());
                }
                else if(get_Symbols().contains(String.valueOf(i))){
                    indexTMP = get_Symbols().indexOf(String.valueOf(i));
                    unswer += get_Symbols().charAt(Math.abs(indexTMP - keyShifr) % get_Symbols().length());
                }
                else
                    System.out.println("Error");
            }

            KEY = MyCopy(0, KEY.length() - 2, KEY);
            return new String[]{unswer, KEY};
        }
    }

}