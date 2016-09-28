### NIO网络基础示例

#### 都只是示例，不健壮，不完善

+ cn.ryanwu.nio2.exp.bio: Blocking IO示例
+ cn.ryanwu.nio2.exp.bio.pool: 线程池模式
+ cn.ryanwu.nio2.exp.nio: NIO基础
+ cn.ryanwu.nio2.exp.bio.withblockclient: NIO with BIO client
+ cn.ryanwu.nio2.exp.nio.dynamic:NioServer实现了读buffer的自动扩容，NIOClient实现了this.socketChannel.configureBlocking(true);使用阻塞channel
+ cn.ryanwu.nio2.exp.nio.packet: 演示粘包
+ cn.ryanwu.nio2.exp.nio.resolve: 粘包和拆包的解决方案，定长：FixLengthReader; 分隔符：LineBasedReader
