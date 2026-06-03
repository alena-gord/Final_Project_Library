import javax.swing.*;
import java.io.IOException;

public class GameLauncher {

    public static void launchGame() {

        try {

            ProcessBuilder pb =
                    new ProcessBuilder(
                            "java",
                            "-jar",
                            "GavinCookie.jar"
                    );

            pb.start();

        }
        catch (IOException e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Could not find LibraryGame.jar"
            );
        }
    }
}