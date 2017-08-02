import io.kotlintest.eventually
import io.kotlintest.matchers.fail
import io.kotlintest.matchers.should
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.FreeSpec
import java.util.concurrent.TimeUnit

class BombSpec : FreeSpec() {

    init {

        """
Bomb will be ticking after it is initialized.
Bomb explode after two seconds.
Bomb is disposed if all wires are cut.
Bomb explode if wires are NOT cut in following order: green, yellow and blue
"""

        val waitOverTwoSeconds = fun() { Thread.sleep(2100) }

        "Bomb tick after creating it" {
            val bomb = Bomb()
            bomb.state() shouldBe "ticktock"
        }

        "Bomb explode" - {

            "after two seconds" {
                val bomb = Bomb()
                waitOverTwoSeconds()
                bomb.state() shouldBe "kaboom!"
            }

            "when yellow wire is cut first" {
                val bomb = Bomb()
                bomb.cutWires(Wire.yellow)
                bomb.state() shouldBe "kaboom!"
            }

            "when blue wire is cut first" {
                val bomb = Bomb()
                bomb.cutWires(Wire.blue)
                bomb.state() shouldBe "kaboom!"
            }

            "when green wire is cut first and then blue" {
                val bomb = Bomb()
                bomb.cutWires(Wire.green, Wire.blue)
                bomb.state() shouldBe "kaboom!"
            }

        }

        "Bomb disarmed when wires are cut in following order: green, yellow and blue" {
            val bomb = Bomb()
            bomb.cutWires(Wire.green, Wire.yellow, Wire.blue)
            bomb.state() shouldBe "disarmed"
        }

        "After bomb is disarmed" - {

            "it won't hurt if wires are cut again" {
                val bomb = Bomb()
                bomb.cutWires(Wire.green, Wire.yellow, Wire.blue)
                bomb.state() shouldBe "disarmed"
                bomb.cutWires(Wire.green)
            }

            "it wont explode after two seconds" {
                val bomb = Bomb()
                bomb.cutWires(Wire.green, Wire.yellow, Wire.blue)
                bomb.state() shouldBe "disarmed"
                waitOverTwoSeconds
                bomb.state() shouldBe "disarmed"
            }
        }

    }
}