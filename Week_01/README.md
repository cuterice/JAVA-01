学习笔记

1.JVM的内存区域分为：堆内存和栈内存

2.堆内存分为两部分：堆和非堆

3.堆主要由GC管理，分为老年代+年轻代； 
                            年轻代 = 新生代 + 存活区；



-Xmx , 指最大堆内存， 这只是限制了heap部分的最大值， 这个内存不包括栈内存， 也不包括堆外使用的内存

-Xms, 指定堆内存空间的初始大小。指定的内存大小，并不是操作系统实际分配的初始值， 而是GC先规划好， 用到才分配。专用服务器上需要保持-Xms和-Xmx一致， 否则应用刚启动可能就有好几个FullGC.  当两者配置不一致的时， 堆内存扩容可能会导致性能抖动。

-Xmn， 等价于-XX: NewSize, 使用G1垃圾收集器不应该设置该选项， 在其他的某些业务场景下可以设置。官方建议设置为-Xmx的1/2 ~1/4.   -XX: MaxPermSize=size, 这是JDK1.7之前使用的。java8默认允许的Meta空间无限大，此参数无效。

-XX： MaxMetaspaceSize= size， java8默认不限制metaspace大小，一般不允许设置该选项。

-XX： MaxDirectMemorySize= size,系统可以使用的最大堆内存， 这个参数跟-Dsun.nio.MaxDirectMemorySize效果相同。

-Xss， 设置每个线程栈的字节数， 影响栈的深度。例如-Xss 1M指定线程栈为1MB， 与-XX： ThreadStackSize=1M等价。


