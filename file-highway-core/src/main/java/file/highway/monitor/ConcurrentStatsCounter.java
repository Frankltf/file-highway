package file.highway.monitor;

import com.codahale.metrics.*;
import file.highway.enums.Metrics;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.TimeUnit;

/**
 * @Intro
 * @Author liutengfei
 */
public class ConcurrentStatsCounter implements StatsCounter, InitializingBean {
    private final Gauge currentSocketSuccessCount;
    private final Counter socketSuccessCount;
    private final Counter socketFailCount;
    //连接持续时间
    private final Histogram socketDuration;
    private final Counter uploadFileSuccessCount;
    private final Counter uploadFileFailCount;
    private final Counter uploadFileSize;

    private static MetricRegistry registry;

    public static void init(){
        registry = new MetricRegistry();;
    }

    public static MetricRegistry getRegistry() {
        return registry;
    }

    public ConcurrentStatsCounter() {
        init();
        currentSocketSuccessCount = registry.register(Metrics.CURRENT_SOCKET_CONNECT_COUNT.getTag(), new Gauge<Integer>() {
            @Override
            public Integer getValue() {
                return 2;
            }
        });
        socketSuccessCount = registry.counter(Metrics.SOCKET_SUCCESS_CONNECT_COUNT.getTag());
        socketFailCount = registry.counter(Metrics.SOCKET_FAIL_CONNECT_COUNT.getTag());
        socketDuration = new Histogram(new ExponentiallyDecayingReservoir());
        uploadFileSuccessCount = registry.counter(Metrics.UPLOAD_FILE_SUCCESS_COUNT.getTag());
        uploadFileFailCount = registry.counter(Metrics.UPLOAD_FILE_FAIL_COUNT.getTag());
        uploadFileSize = registry.counter(Metrics.UPLOAD_FILE_SIZE_COUNT.getTag());
    }

    @Override
    public void recordConnectSuccess() {
        socketSuccessCount.inc();
    }

    @Override
    public void recordConnectFailure() {
        socketFailCount.inc();
    }

    @Override
    public void recordUploadSuccess() {
        uploadFileSuccessCount.inc();
    }

    @Override
    public void recordUploadFailure() {
        uploadFileFailCount.inc();
    }

    @Override
    public void recordUploadFileSize(long inc) {
        uploadFileSize.inc(inc);
    }

    @Override
    public HighwayStats snapshot() {
        return new HighwayStats(
                socketSuccessCount.getCount(),
                socketFailCount.getCount(),
                uploadFileSuccessCount.getCount(),
                uploadFileFailCount.getCount(),
                uploadFileSize.getCount()
        );
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
//        reporter.start(5, TimeUnit.SECONDS);
    }
}
