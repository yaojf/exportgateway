package com.yaojiafeng.exportgateway.facade.ogw.protocol;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 调用id的生成工具类,选择性使用
 *
 * @author yaojiafeng
 * @since $Revision:1.0.0, $Date: 16/7/28 上午10:42 $
 */
public class InvokeIDGenerator {

    /**
     * 调用计数器
     */
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    static {
        //每秒重置调用计数器
        Timer timer = new Timer("Timer-EGProtocol-COUNTER-RESET");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                COUNTER.lazySet(0);
            }
        }, 1000, 1000);
    }


    /**
     * 生成调用id,方便查日志
     * 规则: |时间戳|机器IP|PID|计数器|
     *
     * @return
     */
    public static String generateInvokeID() {
        StringBuilder sb = new StringBuilder(100);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(new Date());

        String ip = "127.0.0.1";
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            ip = localHost.getHostAddress();
        } catch (Exception ex) {
            //pass
        }

        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];

        int count = COUNTER.incrementAndGet();

        sb.append(now).append(ip).append(pid).append(count);

        return sb.toString();
    }

}
