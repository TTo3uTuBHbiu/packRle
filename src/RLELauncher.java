/**
 * Created by z on 17.05.2017.
 */



import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.CmdLineException;


import java.io.IOException;


    public class RLELauncher {
        @Option(name = "-z", usage = "Сжимает файл")
        private boolean compressing;
        @Option(name = "-u", usage = "Распаковывает файл")
        private boolean decoding;
        @Option(name = "-out", usage = "Имя выходного файла")
        private String output;
        @Argument(required = true, usage = "Имя входного файла")
        private String input;

        public static void main(String[] args) {
            new RLELauncher().launch(args);
        }

        private void launch(String[] args) {
            CmdLineParser pars = new CmdLineParser(this);
            try {
                pars.parseArgument(args);
            } catch (CmdLineException e) {
                System.err.println(e.getMessage());
                System.err.println("java -jar Packrle.jar -z -u -out ");
                pars.printUsage(System.err);
                return;
            }

            packRLE rle = new packRLE(input, output);
            if (compressing) {
                try {
                    rle.compressing();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (decoding) {
                try {
                    rle.decoding();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

