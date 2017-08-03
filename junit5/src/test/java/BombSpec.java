import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BombSpec {

    @Test
    public void Bomb_tick_after_creating_it() {
        Bomb bomb = new Bomb();
        assertThat(bomb.state(), is("ticktock"));
    }

    @Test
    public void Bomb_disarmed_when_wires_are_cut_in_following_order_green_yellow_and_blue() {
        Bomb bomb = new Bomb();
        bomb.cutWires(Wire.green, Wire.yellow, Wire.blue);
        assertThat(bomb.state(), is("disarmed"));
    }

    @Nested
    public class Bomb_explode {

        @Test
        public void after_two_seconds() {
            Bomb bomb = new Bomb();
            waitOverTwoSeconds();
            assertThat(bomb.state(), is("kaboom!"));
        }

        @Test
        public void when_yellow_wire_is_cut_first() {
            Bomb bomb = new Bomb();
            bomb.cutWires(Wire.yellow);
            assertThat(bomb.state(), is("kaboom!"));
        }

        @Test
        public void when_blue_wire_is_cut_first() {
            Bomb bomb = new Bomb();
            bomb.cutWires(Wire.blue);
            assertThat(bomb.state(), is("kaboom!"));
        }

        @Test
        public void when_green_wire_is_cut_first_and_then_blue() {
            Bomb bomb = new Bomb();
            bomb.cutWires(Wire.blue, Wire.blue);
            assertThat(bomb.state(), is("kaboom!"));
        }
    }

    @Nested
    public class After_bomb_is_disarmed {

        @Test
        public void it_wont_explode_after_two_seconds() {
            Bomb bomb = new Bomb();
            bomb.cutWires(Wire.green, Wire.yellow, Wire.blue);
            assertThat(bomb.state(), is("disarmed"));
            waitOverTwoSeconds();
            assertThat(bomb.state(), is("disarmed"));
        }

        @Test
        public void it_wont_hurt_if_wires_are_cut_again() {
            Bomb bomb = new Bomb();
            bomb.cutWires(Wire.green, Wire.yellow, Wire.blue);
            assertThat(bomb.state(), is("disarmed"));
            bomb.cutWires(Wire.yellow);
            assertThat(bomb.state(), is("disarmed"));
        }

    }

    private void waitOverTwoSeconds() {
        try {
            Thread.sleep(2100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
