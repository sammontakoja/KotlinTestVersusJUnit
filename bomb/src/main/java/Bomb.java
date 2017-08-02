import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

public class Bomb {

    private String state = "ticktock";
    private final Stack<Wire> wiresLeft;

    public Bomb() {
        this.wiresLeft = wiresBlueYellowAndGreen();
        markStateAsKaboomAfterTwoSeconds();
    }

    private Stack<Wire> wiresBlueYellowAndGreen() {
        Stack<Wire> wires = new Stack<>();
        wires.push(Wire.blue);
        wires.push(Wire.yellow);
        wires.push(Wire.green);
        return wires;
    }

    private void markStateAsKaboomAfterTwoSeconds() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                state = "kaboom!";
            }
        };
        Timer t = new Timer();
        t.schedule(task, 2000);
    }

    public String state() {
        return state;
    }

    public void cutWires(Wire... wiresToBeCut) {

        if (state.equals("ticktock")) {

            for (Wire wireToBeCut : wiresToBeCut) {
                if (wiresLeft.peek() == wireToBeCut) {
                    wiresLeft.pop();
                    if (wiresLeft.empty())
                        state = "disarmed";
                } else {
                    state = "kaboom!";
                }
            }

        }
    }
}
