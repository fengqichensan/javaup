# 不同 GC 和堆内存的总结
### 1.ParallerGC
    新生代GC：Paraller Scavenge
        1.并行，多线程
            单位时间内效率高
        2.关注可控的吞吐量
            -XX：GCTimeRation=n
                设置吞吐量大小
                取值范围：1～99.
                默认值：99 （ 1/（1+99）） = 1% ←GC处理最大利用1%的CPU时间
    老年代GC：Parallel Old
        1.并行，多线程
            单位时间内效率高
        2.关注可控的吞吐量
        3.使用标记-清除-整理算法
### 2.CMSGC
    新生代：ParNew
        1.并行，多线程
            单位时间内效率高
        2.和CMS配合使用
    老年代：CMS
        1.并发，多线程
            STW时间短，但是会影响吞吐量
        2.使用标记-清除算法
            垃圾收集时会产生碎片。当碎片过多，无法找到足够大的连续的内存空间来分配对象时，会被迫提前出发FullGC。
            -XX：+UseCMSCompactAtFullCollection：
                FullGC时，开启内存整理。内存整理无法并发，会产生STW
        3.GC执行的步骤
            3.1.CMS initial mark ：会有STW
            3.2.CMS concurrent mark：并发执行，没有STW
            3.3.CMS concurrent preclean：并发执行，没有STW
                3.3.1CMS concurrent abortable preclean
            3.4.CMS final remark：会有STW
            3.5.CMS concurrent sweep：并发执行，没有STW
            3.6.CMS concurrent rest：并发执行，没有STW
        4.在一定条件下会退化成 Serial Old
            由于垃圾清除是并发的，所以在垃圾清理的过程中，老年代被并发运行的用户线程填满的话，会出现Concurrent Mode Failure。这是就会临时启用Serial Old，STW时间会变很长。
            （-XX:CMSInitiatingOccupancyFraction=99 ←可再现 Concurrent Mode Failure）
### 3.G1
    新生代，老年代：G1
        1.并发，多线程
        2.把内存分为多个大小相等的独立区域（Region），新生代和老年代不是物理隔离的。
        3.G1跟踪各个Region里的垃圾的多少，有限处理垃圾收集价值大的Region
        4.从整体上看是使用标记-清除-整理算法。从局部上看类似其他新生代的GC的From Survivor→To Survivor的复制算法。
        5.可预测SWT时间
### 4.SerialGC
    新生代：Serial
        1.单线程
        2.适合client模式下的应用
    老年代：Serial Old
        1.单线程
        2.适合client模式下的应用
        3.CMS的备案
        4.使用标记-清除-整理算法


