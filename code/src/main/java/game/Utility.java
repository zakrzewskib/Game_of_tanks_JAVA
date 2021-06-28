package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utility {
    private final String[] configurationVariables;
    private final String defaultData = "/data/default";

    public Utility() {
        configurationVariables = new String[]{"WIDTH", "HEIGHT", "P", "H", "X", "Y", "Z", "K", "L", "TIME", "WIN_SCORE"};
        try {
            readFile(defaultData);
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void readFile(String path) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(Utility.class.getResourceAsStream(path)));
        String line;
        StringBuilder allLine = new StringBuilder();

        while ((line = r.readLine()) != null) {
            allLine.append(" ");
            allLine.append(line);
        }

        readString(allLine.toString());
    }

    private void readString(String allLine) {
        String[] w = allLine.split("\\s+");
        for (int i = 0; i < w.length; i++) {
            if (equalsConfigurationVariable(w[i])) {
                i++;
                try {
                    if (isPositiveInteger(w[i])) {
                        int value = Integer.parseInt(w[i]);
                        loadConfigurationVariable(w[i - 1], value);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
                i--;
            }
        }
    }

    private void loadConfigurationVariable(String s, int value) {
        if (!correctSizeConfiguration(s, value)) {
            if (s.equals(configurationVariables[2])) {
                Game.P = value;
            } else if (s.equals(configurationVariables[3])) {
                Game.H = value;
            } else if (s.equals(configurationVariables[4])) {
                Game.X = value;
            } else if (s.equals(configurationVariables[5])) {
                Game.Y = value;
            } else if (s.equals(configurationVariables[6])) {
                Game.Z = value;
            } else if (s.equals(configurationVariables[7])) {
                Game.K = value;
            } else if (s.equals(configurationVariables[8])) {
                Game.L = value;
            } else if (s.equals(configurationVariables[9])) {
                Game.TIME = value;
            } else if (s.equals(configurationVariables[10])) {
                Game.WIN_SCORE = value;
            }
        }
    }

    private boolean correctSizeConfiguration(String s, int value) {
        if (s.equals(configurationVariables[0])) {
            if (value >= 480 && value <= 1440) {
                Game.WIDTH = value;
                return true;
            }
        } else if (s.equals(configurationVariables[1])) {
            if (value >= 360 && value <= 1080) {
                Game.HEIGHT = value;
                return true;
            }
        }
        return false;
    }

    private boolean equalsConfigurationVariable(String string) {
        for (String configurationVariable : configurationVariables) {
            if (string.equals(configurationVariable)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPositiveInteger(String s) {
        try {
            int tmp = Integer.parseInt(s);
            if (tmp <= 0) {
                return false;
            }
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}