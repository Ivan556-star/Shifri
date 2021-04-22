public class Vijenera implements shifr {

    @Override
    public String[] crypt(int choiceShifr, String text, String KEY) {
        String unswer = "";
        text = text.toUpperCase();
        KEY = KEY.toUpperCase();

        if (choiceShifr == 1){
            int k = 0;
            while (KEY.length() < text.length()){
                if (RU().contains(String.valueOf(text.charAt(k)))){
                    KEY += RU().charAt(r.nextInt(RU().length()));
                }
                if (EN().contains(String.valueOf(text.charAt(k)))){
                    KEY += EN().charAt(r.nextInt(EN().length()));
                }
                if (SIM().contains(String.valueOf(text.charAt(k)))){
                    KEY += SIM().charAt(r.nextInt(SIM().length()));
                }
                k++;
            }

            int indexTMP;
            for (int i = 0; i < text.length(); i++) {
                if (RU().contains(String.valueOf(text.charAt(i)))){
                    indexTMP = RU().indexOf(String.valueOf(text.charAt(i)));
                    unswer += RU().
                            charAt(Math.abs((indexTMP + RU().
                                    indexOf(String.valueOf(KEY.charAt(i)))) % RU().length()));
                }
                else if (EN().contains(String.valueOf(text.charAt(i)))){
                    indexTMP = EN().indexOf(String.valueOf(text.charAt(i)));
                    unswer += EN().
                            charAt(Math.abs((indexTMP + EN().
                                    indexOf(String.valueOf(KEY.charAt(i)))) % EN().length()));
                }
                else if (SIM().contains(String.valueOf(text.charAt(i)))){
                    indexTMP = SIM().indexOf(String.valueOf(text.charAt(i)));
                    unswer += SIM().
                            charAt(Math.abs((indexTMP + SIM().
                                    indexOf(String.valueOf(KEY.charAt(i)))) % SIM().length()));
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
                    text += RU().charAt(r.nextInt(RU().length()));
                else if (tmp == 1)
                    text += EN().charAt(r.nextInt(EN().length()));
                else
                    text += SIM().charAt(r.nextInt(SIM().length()));
            }

            try {
                int indexTMP;
                for (int i = 0; i <text.length(); i++) {
                    if (RU().contains(String.valueOf(text.charAt(i)))){
                        indexTMP = RU().indexOf(String.valueOf(text.charAt(i)));
                        unswer += RU().
                                charAt(Math.abs((indexTMP - RU().
                                        indexOf(String.valueOf(keyShifr.charAt(i)))) % RU().length()));
                    }
                    else if (EN().contains(String.valueOf(text.charAt(i)))){
                        indexTMP = EN().indexOf(String.valueOf(text.charAt(i)));
                        unswer += EN().
                                charAt(Math.abs((indexTMP - EN().
                                        indexOf(String.valueOf(keyShifr.charAt(i)))) % EN().length()));
                    }
                    else if (SIM().contains(String.valueOf(text.charAt(i)))){
                        indexTMP = SIM().indexOf(String.valueOf(text.charAt(i)));
                        unswer += SIM().
                                charAt(Math.abs((indexTMP - SIM().
                                        indexOf(String.valueOf(keyShifr.charAt(i)))) % SIM().length()));
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
}
