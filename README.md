# trading-strategies

相关技术栈：jdk8，springboot，mybatis-plus，hutool，mysql，caffeine，线程池等。项目中所有技术点纯个人搭建，技术不复杂，复杂的是背后的计算逻辑。

当前仅提供了获取某日涨停板，某日强势股，二连板，连板等功能。

其他具体量化策略相关接口以及交易策略目前暂时不提供，如果有兴趣，可以自己实现IQuantitativeStrategiesService，写自己的策略。

由于token是付费获取，所以当前工程中没有提供token，如果想使用该工程，需要使用UrlConstant中没有token的url路径替换BaseServiceImpl里面的url路径。

新增接口文档支持~：http://127.0.0.1:8080/doc.html#/home

如有喜欢做交易志同道合的朋友可以加V：Jiangcy233

我的口号是：编程创造一切，拒绝人工复盘，直接代码导出~

### 其他复盘以及交易策略后续会根据需求慢慢展示，尽情期待~~~

![img.png](img.png)

### --致交易者

做交易的人一旦成功了，
一生都不用为钱发愁了。
十年痛苦无人问，
一招悟道世人问。
只有自己知道，
曾经的自己承受过什么，
你所付出的终将拿回来。

### --致码农

编程和金融都是一张王牌，
如果两者结合到一起就是一副王炸。
只要有想法，
没有什么是代码实现不了的。
只要有行动，
一切都将会取得成功。

## 守得云开见月明 ，静待花开终有时

![img_2.png](img_2.png)
![img_3.png](img_3.png)
![img_5.png](img_5.png)


### 回顾
#### 2023/12/11 
之前一直不理解市场的一个时间和空间的概念，昨天朋友来杭州逛了逛西湖，一下子开了窍。行情的空间上涨空间是要通过时间来换取的， 这也是我们常说的时间换空间。
怎么理解呢，行情一旦短时间连续涨停，他的上涨空间是有限的，往往在前期高点位置就进行反转。
而如果我们想要突破这个前期高点，形成趋势，必须要有通过长时间的盘整修复，才能有突破的可能。换句话说，如果行情加速冲到前期高点，90%的概率是反转。

### 【2023/12/11-2023/12/15】

整体市场处于弱势。周末复盘后可选操作股票不多，建议减少交易次数。目前满仓通化金马，本周关注历史高位以及突破后的走势。

603535 嘉诚国际—— 11/29日放量涨停后，一直处于横盘，12/08日是一个合适的入场点。止损点在18附近
600509 天富能源—— 同嘉诚国际，6.50附近强支撑，跌破离场
000829 天音控股—— 关注11/07日涨停板的高点。现在位置可以考虑低吸，止损位在10.50附近
002962 五方光电—— 关注二板之后的周一的走势。目前看结构比较不错，有持续的可能。如果周一能高开低走到17.40-17.90附近形成支撑，可以进场。最好在17.40
601138 工业富联—— 大级别C点，持续关注。等待结构
600895 张江高科—— 大级别C点，持续关注，等待结构。如果跌破20趋势结束，如果在当前位置横盘，C点有效。
002436 兴森科技—— 结构十分完美，14.70附近作为止损点。目前没有更好的入场位置。持续关注
002456 欧菲光—— 等待下跌到9块附近的结构变化
600971 恒源煤电—— 好股，慢牛。
600150 中国船舶—— 关注27.30支撑位变化














