package game;

import java.io.IOException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

import static game.Game.H;
import static game.Game.HEIGHT;
import static game.Game.K;
import static game.Game.L;
import static game.Game.P;
import static game.Game.TIME;
import static game.Game.WIDTH;
import static game.Game.WIN_SCORE;
import static game.Game.X;
import static game.Game.Y;
import static game.Game.Z;


public class UtilityTest {

    private Utility utility;
    private final String folderName = "/data/test_data/";

    @Before
    public void setUp() {
        utility = new Utility();
    }

    @Test
    public void readFile_test_default() throws IOException {
        String path = "/data/default";
        utility.readFile(path);
        data_is_not_zero();
    }

    private void data_is_not_zero() {
        test_is_not_zero(WIDTH);
        test_is_not_zero(HEIGHT);
        test_is_not_zero(P);
        test_is_not_zero(H);
        test_is_not_zero(L);
        test_is_not_zero(K);
        test_is_not_zero(Z);
        test_is_not_zero(X);
        test_is_not_zero(Y);
        test_is_not_zero(WIN_SCORE);
        test_is_not_zero(TIME);
    }

    private void test_is_not_zero(int x) {
        assertFalse((x == 0));
    }

    @Test(expected = NullPointerException.class)
    public void readFile_path_null() throws IOException {
        String path = null;

        utility.readFile(path);

        assert false;
    }

    @Test(expected = NullPointerException.class)
    public void readFile_2_NullPointerException() throws IOException {
        String path = folderName;
        path += "defaul";

        utility.readFile(path);

        assert false;
    }

    @Test
    public void readFile_test_correct_data_different_sequence() throws IOException {
        String path = folderName;
        path += "testCorectDataDifferentSequence";

        utility.readFile(path);

        Assertions.assertThat(HEIGHT).isEqualTo(500);
        Assertions.assertThat(WIDTH).isEqualTo(500);
        Assertions.assertThat(P).isEqualTo(10);
        Assertions.assertThat(H).isEqualTo(10);
        Assertions.assertThat(X).isEqualTo(1);
        Assertions.assertThat(Y).isEqualTo(6);
        Assertions.assertThat(Z).isEqualTo(10);
        Assertions.assertThat(K).isEqualTo(20);
        Assertions.assertThat(L).isEqualTo(5);
        Assertions.assertThat(TIME).isEqualTo(300);
        Assertions.assertThat(WIN_SCORE).isEqualTo(100);
    }

    @Test
    public void readFile_test_correct_data_different_line() throws IOException {
        String path = folderName;
        path += "testCorrectDataDifferentLine";

        utility.readFile(path);

        Assertions.assertThat(HEIGHT).isEqualTo(500);
        Assertions.assertThat(WIDTH).isEqualTo(500);
        Assertions.assertThat(P).isEqualTo(10);
        Assertions.assertThat(H).isEqualTo(10);
        Assertions.assertThat(X).isEqualTo(1);
        Assertions.assertThat(Y).isEqualTo(6);
        Assertions.assertThat(Z).isEqualTo(10);
        Assertions.assertThat(K).isEqualTo(20);
        Assertions.assertThat(L).isEqualTo(5);
        Assertions.assertThat(TIME).isEqualTo(300);
        Assertions.assertThat(WIN_SCORE).isEqualTo(100);
    }

    @Test
    public void readFile_test_correct_data_not_all() throws IOException {
        String path = folderName;
        path += "test_correct_data_not_all";

        utility.readFile(path);

        Assertions.assertThat(HEIGHT).isEqualTo(500);
        Assertions.assertThat(P).isEqualTo(10);
        Assertions.assertThat(H).isEqualTo(10);
        Assertions.assertThat(X).isEqualTo(1);
        Assertions.assertThat(Y).isEqualTo(6);
        Assertions.assertThat(Z).isEqualTo(10);
        Assertions.assertThat(K).isEqualTo(20);
        Assertions.assertThat(L).isEqualTo(5);
        Assertions.assertThat(TIME).isEqualTo(300);
        data_is_not_zero();
    }

    @Test
    public void readFile_test_wrong_data_not_int() throws IOException {
        String path = folderName;
        path += "test_wrong_data_not_int";

        utility.readFile(path);

        Assertions.assertThat(H).isEqualTo(10);
        Assertions.assertThat(Y).isEqualTo(6);
        Assertions.assertThat(Z).isEqualTo(10);
        Assertions.assertThat(TIME).isEqualTo(300);
        data_is_not_zero();
    }

    @Test
    public void readFile_test_wrong_data_second_times_the_same_value() throws IOException {
        String path = folderName;
        path += "test_wrong_data_second_times_the_same_value";

        utility.readFile(path);

        Assertions.assertThat(WIDTH).isEqualTo(640);
        Assertions.assertThat(L).isEqualTo(7);
        Assertions.assertThat(K).isEqualTo(20);
        data_is_not_zero();
    }
}