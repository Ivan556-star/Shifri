public class Cezar implements shifr {
    private String text, KEY;
    private int choiceShifr;

    @Override
    public String[] crypt(int choiceShifr, String Text) {
        String unswer = "";
        if (choiceShifr == 1){
            System.out.println("Введите ключ: ");
            int indexTMP,keyShifr;
            while (true) {
                String keyShifrTmp = cin.nextLine();
                try {
                    keyShifr = Integer.parseInt(keyShifrTmp);
                    if (keyShifr < 1){
                        System.out.print("Ошибка, введите положительное целое число: ");
                        continue;
                    }
                    break;
                } catch (Exception ignored) {
                    System.out.print("Ошибка, введите положительное целое число: ");
                }
            }
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

        return null;
    }
}
