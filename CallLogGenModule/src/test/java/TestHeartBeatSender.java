import com.longrise.loggen.udp.HeartBeatThread;
import org.junit.Test;

/**
 * @Author ：Tanger.
 * @Date ：Created in 22:19 2019/10/15
 */

public class TestHeartBeatSender {
    @Test
    public void test1() throws InterruptedException {
        new HeartBeatThread().start();
        while (true){
            Thread.sleep(1000);
        }
    }
}
