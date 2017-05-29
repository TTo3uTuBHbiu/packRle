/**
 * Created by z on 08.05.2017.
 */

import java.io.*;

public class packRLE {



    private final File input;
    private final File output;



    public packRLE(String input1, String output1) {
        input = new File(input1);
        if (output1 == null) output = new File("output.txt");
         else output = new File(output1);
    }




    public static   String compression(String str) {

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < str.length() - 1; i++) {
            int charsLength = 1;
            if (str.charAt(i) != str.charAt(i + 1)) {
                out.append(str.charAt(i));
                if (i + 1 == str.length() - 1) out.append(str.charAt(i + 1));
            }

            else {
                while ((i < str.length() - 1) && (str.charAt(i) == str.charAt(i + 1))) {
                    i++;
                    charsLength++;
                }

                if (charsLength > 9) {
                    while (charsLength > 9) {
                        out.append("(");
                        out.append("*");
                        out.append("9");
                        out.append(str.charAt(i));
                        out.append(")");
                        charsLength = charsLength - 9;
                    }
                }

                if (charsLength < 9) {
                    out.append("(");
                    out.append("*");
                    out.append(charsLength);
                    out.append(str.charAt(i));
                    out.append(")");
                }

                if (i + 1 == str.length() - 1) out.append(str.charAt(i + 1));
            }
        }
        return out.toString();
    }



    public static   String decode(String str) {
        StringBuilder out = new StringBuilder();
        int last =0;
        for (int i = 0; i < str.length() - 2; i++) {
            if ((str.charAt(i) == '*') && (i < str.length()- 2)  && (str.charAt(i + 1) >= '0') && (str.charAt(i + 1) <= '9')) {
                last = i;
                int length = str.charAt(i + 1) - '0';
                while (length > 0) {
                    out.append(str.charAt(i + 2));
                    length--;
                }
                i = i + 2;
            } else if (i + 1 == str.length() - 1) out.append(str.charAt(i + 1));
                else if((str.charAt(i) != ')') && (str.charAt(i) != '(')) out.append(str.charAt(i));


        }
        if ((last == str.length()-4)&& (str.charAt(str.length()-1)!= ')')) out.append(str.charAt(str.length()-1));
        if ((last < str.length()-4)&&(str.charAt(str.length()-1)!= ')')){
            out.append(str.charAt(str.length()-1));
        }

        return out.toString();
    }





    public void compressing() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
                String str;
                while ((str = reader.readLine()) != null) {
                    writer.write(compression(str));
                }
            }
        }
    }

    public void decoding() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
                String str;
                while ((str = reader.readLine()) != null) {
                    writer.write(decode(str));
                }
            }
        }
    }


}
