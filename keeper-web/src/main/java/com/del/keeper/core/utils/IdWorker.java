package com.del.keeper.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.del.keeper.core.exception.ServiceException;

/**
 * 流水号生成器
 * 
 * @author xie
 *
 */
public class IdWorker {

    private static final Logger log = LoggerFactory.getLogger(IdWorker.class);

    private final long workerId;
    private final long snsEpoch = 1330328109047L;// 起始标记点，作为基准
    private long sequence = 0L;// 0，并发控制
    private final long workerIdBits = 10L;// 只允许workid的范围为：0-1023
    private final long maxWorkerId = -1L ^ -1L << this.workerIdBits;// 1023,1111111111,10位
    private final long sequenceBits = 12L;// sequence值控制在0-4095

    private final long workerIdShift = this.sequenceBits;// 12
    private final long timestampLeftShift = this.sequenceBits + this.workerIdBits;// 22
    private final long sequenceMask = -1L ^ -1L << this.sequenceBits;// 4095,111111111111,12位

    private long lastTimestamp = -1L;

    public IdWorker(long workerId) {
        super();
        if (workerId > this.maxWorkerId || workerId < 0) {// workid <
                                                          // 1024[10位：2的10次方]
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() throws ServiceException {
        long timestamp = this.timeGen();
        // 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环)，下次再使用时sequence是新值
        if (this.lastTimestamp == timestamp) {
            this.sequence = this.sequence + 1 & this.sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);// 重新生成timestamp
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            log.warn(String.format("Clock moved backwards.Refusing to generate id for %d milliseconds",
                    (this.lastTimestamp - timestamp)));
            throw new ServiceException(
                    String.format("Clock moved backwards.Refusing to generate id for %d milliseconds",
                            (this.lastTimestamp - timestamp)));
        }
        this.lastTimestamp = timestamp;
        // 生成的timestamp
        return timestamp - this.snsEpoch << this.timestampLeftShift | this.workerId << this.workerIdShift
                | this.sequence;
    }

    /**
     * 保证返回的毫秒数在参数之后
     *
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    /**
     * 获得系统当前毫秒数
     *
     * @return
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    // public static void main(String[] args) throws Exception {
    // IdWorker iw1 = new IdWorker(1);
    // IdWorker iw2 = new IdWorker(2);
    // IdWorker iw3 = new IdWorker(3);
    //
    // System.out.println("---------------------------");
    //
    // long workerId = 1L;
    // long twepoch = 1330328109047L;
    // long sequence = 0L;// 0
    // long workerIdBits = 10L;
    // long maxWorkerId = -1L ^ -1L << workerIdBits;// 1023,1111111111,10位
    // long sequenceBits = 12L;
    //
    // long workerIdShift = sequenceBits;// 12
    // long timestampLeftShift = sequenceBits + workerIdBits;// 22
    // long sequenceMask = -1L ^ -1L << sequenceBits;// 4095,111111111111,12位
    //
    // long ct = System.currentTimeMillis();// 1330328109047L;//
    // System.out.println(ct);
    //
    // System.out.println(ct - twepoch);
    // System.out.println(ct - twepoch << timestampLeftShift);// 左移22位：*2的22次方
    // System.out.println(workerId << workerIdShift);// 左移12位：*2的12次方
    // System.out.println("哈哈");
    // System.out.println(ct - twepoch << timestampLeftShift | workerId <<
    // workerIdShift);
    // long result = ct - twepoch << timestampLeftShift | workerId <<
    // workerIdShift | sequence;// 取时间的低40位 | （小于1024:只有12位）的低12位 | 计算的sequence
    // System.out.println(result);
    //
    // System.out.println("---------------");
    // for (int i = 0; i < 10; i++) {
    // System.out.println(iw1.nextId());
    // }
    //
    // Long t1 = 66708908575965184l;
    // Long t2 = 66712718304231424l;
    // Long t3 = 66715908575739904l;
    // Long t4 = 66717361423925248l;
    // System.out.println(Long.toBinaryString(t1));
    // System.out.println(Long.toBinaryString(t2));
    // System.out.println(Long.toBinaryString(t3));
    // System.out.println(Long.toBinaryString(t4));
    // System.out.println(Long.toBinaryString(66706920114753536l));
    // }
}
