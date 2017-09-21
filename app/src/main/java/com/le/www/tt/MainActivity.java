package com.le.www.tt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.le.www.tt.interceptor.Chain;
import com.le.www.tt.interceptor.Request;
import com.le.www.tt.interceptor.Response;
import com.le.www.tt.fork.join.Master;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        int i = 0;
//        int j = 2;
//        long k = 2L;
//        if (k == 2) {
//
//        } else {
//
//        }
//        for (int i = 0; i < 5; i++) {
//
//        }
//
//        StringBuffer sb = new StringBuffer();
//超过120个字符的情况下，换行缩进4个空格，并且方法前的点符号一起换行
//        sb.append("zi")
//                .append("xin")
//                .append("huang")
//                .append("huang")
//                .append("huang");

        int i = -1;
//        Integer integer = null;
//        i = integer;
//        Log.i("wang", "i="+i);
    }


    public void testConveterListToArray(View v) {
        List<String> list = new ArrayList<>(2);
        list.add("aaa");
        list.add("bbb");

//        String[] arr = new String[2];
//        list.toArray(arr);
//        for (int i = 0; i < arr.length; i++) {
//            Log.d("DaoExample", arr[i]);
//        }

//        Object[] arr2 =  list.toArray();
//            String[] sArr = (String[]) arr2;//对象数组强转String数组,这里会抛出异常的
//        for (int i = 0; i < arr2.length; i++) {
//            Log.d("DaoExample", arr2[i].toString());
//        }

//        String[] arr = new String[]{"1", "2"};
//        Object[] arr2 = arr;
//        String[] arr3 = (String[])arr2;
        // Object[]数组是一定不能强制转化为具体的数组
        Object[] arr2 = new Object[]{"1", "2"};
        String[] arr = (String[]) arr2;

    }

    public void testConveterArrayToList(View v) {
        String[] arr = new String[]{"a", "b", "c"};
        // 实现类不是java.utils.ArrayList, 而是java.utils.arrays.ArrayList
        List<String> list = Arrays.asList(arr);

        for (int i = 0; i < list.size(); i++) {
            Log.d("DaoExample", list.get(i));
        }
//        list.add("d"); throws UnSupportOperationException
//        list.remove(0); throws UnSupportOperationException
//        str[0]= "gujin"; 那么list.get(0)也会随之修改。
    }


    public void testForeach(View v) {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        // 不能使用foreach 增加删除元素,有一个除外，如果删除恰好是最后一个元素，程序正常
        // 所以还是用迭代器的形式
//        for (String temp : a) {
//            if("2".equals(temp)){
//                a.remove(temp);
//            }
//        }

        for (int i = 0; i < a.size(); i++) {
            String temp = a.get(i);
            if ("2".equals(temp)) {
                a.remove(temp);
            }
        }
//        Iterator<String> iter = a.iterator();
//        while (iter.hasNext()) {
//            if (iter.next().equals("1")) {
//                iter.remove();
//            }
//        }
//        ConcurrentHashMap
        Log.d("DaoExample", a.size() + "");
    }

    public void testComparator(View v) {
        Student student1 = new Student();
        student1.setAge(2);

        Student student2 = new Student();
        student2.setAge(3);

        Student student3 = new Student();
        student3.setAge(1);

//        int result = student1.compareTo(student2);
//        Log.d("DaoExample", "result:"+result);

        Student[] arr = new Student[]{student1, student2, student3};
        for (Student s : arr) {
            Log.d("DaoExample", "age:" + s.getAge());
        }
        Log.d("DaoExample", "排序后");
        // Collections.sort();
        Arrays.sort(arr);

        for (Student s : arr) {
            Log.d("DaoExample", "age:" + s.getAge());
        }

        Map<String, String> map = new HashMap<>();
        // map 遍历是使用 entrySet
        Set<Map.Entry<String, String>> set = map.entrySet();
        Iterator<Map.Entry<String, String>> iter = set.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = iter.next();
            Log.d("DaoExample", entry.getKey() + entry.getValue());
        }

    }

    int count = 0;
    private static final Object sLock = new Object();

    public void testThread(View v) {
        int threadCount = 5;
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    increase();
                }
            }).start();
        }
    }

    public void testThreadResult(View v) {
        Log.d("DaoExample", "result count:" + count);
    }

    /**
     * 谁拿到锁，谁来执行，执行完毕，释放锁，其他随机某一个线程获取到锁，执行
     */
    private void increase() {
        synchronized (sLock) {
            try {
                Log.d("DaoExample", "tid:" + Thread.currentThread().getId() + " sleep ...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            Log.d("DaoExample", "tid:" + Thread.currentThread().getId() + ", count:" + count);
        }
    }

    public void testThreadPool(View v) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, 5, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        Log.d("DaoExample", "rejectedExecution r:" + r + ", executor:" + executor);
                    }
                });

        Log.d("DaoExample", "threadPoolExecutor:" + threadPoolExecutor);
        int taskCount = 30;
        for (int i = 0; i < taskCount; i++) {
            threadPoolExecutor.execute(new MyRunnable());
        }

    }

    public void testScheduledExecutorService(View v) {
        ScheduledThreadPoolExecutor service = new ScheduledThreadPoolExecutor(5);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("DaoExample", "tid:" + Thread.currentThread().getId() + " sleep ...");
                    Thread.sleep(1000);
                    Log.d("DaoExample", "tid:" + Thread.currentThread().getId() + " finised");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, 1L, 3L, TimeUnit.SECONDS);

    }

    /**
     * 程序的调用方式就只有两种，一种同步等待结果，一种是异步，回调，
     * 现在需要的是异步转为同步
     * 异步转化为同步
     *
     * @param v
     */
    public void testCountDownLatch(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                boolean result = false;
                try {
                    result = ApplicationStartupUtil.checkExternalServices();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("DaoExample", "External services validation completed !! Result was :: " + result);
            }
        }).start();

    }

    /**
     * CountDownLatch 一种汇总模型，也是一种异步转为同步的模型，比如说领导手上有一件任务，
     * 分成了很多的小任务，每一个worker拿到小任务后开始做，领导这是处于等待的一个状态，
     * 当worker做完小任务后把消息同步给领导，当且仅当全部的小任务做完，领导的任务才算是做完
     *
     * @param v
     */
    public void testCountDownLatch2(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int taskCount = 3;
                CountDownLatch countDownLatch = new CountDownLatch(taskCount);
                ExecutorService executorService = Executors.newFixedThreadPool(taskCount);
                for (int i = 0; i < taskCount; i++) {
                    executorService.execute(new Task(countDownLatch, "i:" + i, (i + 1) * 1000));
                }
                try {
                    Log.d("DaoExample", Thread.currentThread().getId() + " 领导开始等待");
                    long beginTime = System.currentTimeMillis();
                    countDownLatch.await();// 初始化的int减少到0时，方法返回
                    Log.d("DaoExample", Thread.currentThread().getId() + " 领导汇总花费time:" + (System.currentTimeMillis() - beginTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void testRandom(View v) {
        Random random = new Random();
        random.nextInt(10);
    }

    /**
     * AtomicInteger 线程安全的增减原子操作
     *
     * @param v
     */
    public void testAtomicInteger(View v) {
        // AtomicInteger 并发没问题, 提供了一系列的线程安全的增减操作接口
        final AtomicInteger atomicInteger = new AtomicInteger();
        for (int i = 0; i < 100000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int temp = atomicInteger.addAndGet(1);
                    Log.d("DaoExample", "temp:" + temp);
                }
            }).start();
        }
    }

    public void testLogByStringFormat(View v) {
        String msg = "current tid : %d , content : %s";
//        String content = String.format(msg, Thread.currentThread().getId(), "this is test");
//        Log.i(TAG, content);

        d(msg, Thread.currentThread().getId(), "this is test");

        String msg2 = "current tid";
        d(msg2);
    }

    private volatile boolean existed = false;

    public void testBooleanSync(View v) {
        synchronized (this) {
            if (existed) {
                existed = true;
            }
        }
    }

    int index;
    ConnectionPool pool;

    public void testThreadPoolAndNotify(View v) {
        pool = new ConnectionPool();
        pool.start();

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Log.i(TAG, "work sleep 1s tid:" + Thread.currentThread().getId());
                            Thread.sleep(5000);
                            Log.i(TAG, "work sleep finised tid:" + Thread.currentThread().getId());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Connection conn = new Connection(index);
                        index++;
                        pool.put(conn);
                    }
                }
            }).start();
        }
    }

    public void stopCleanThreadPool(View v) {
        Log.i(TAG, "click stopCleanThreadPool");
        pool.quit();
    }


    public void executeTwice(View v) {
        ConnectionPool.executeTwice();
    }


    public void testInterceptor(View v) {
        Request request = new Request();
        Chain chain = new Chain(request);
        Response response = chain.process();
        Log.i(TAG, "request content:" + request.content + ", response content:" + response.content);

    }


    static final boolean DEBUG = true;
    public static String TAG = "DaoExample";

    public static void d(String format, Object... args) {
        if (DEBUG) {
            String msg = (args == null ? format : String.format(format, args));
            Log.i(TAG, msg);
        }
    }


    public void testException(View v) {
        try {
            throwsException();
        } catch (IOException e) {
            e.printStackTrace();
        }

        throwsException2();
    }


    private void throwsException() throws IOException {

    }

    private void throwsException2() {

        List<Integer> list = new ArrayList<>(-1);
//        list.get(6);
//        if (5 / 0 == 2) {产生运行时
        throw new IndexOutOfBoundsException("xx");
//        }
    }


    class Task implements Runnable {
        private CountDownLatch countDownLatch;
        private String name;
        private long dealyTime;

        Task(CountDownLatch countDownLatch, String name, long dealyTime) {
            this.countDownLatch = countDownLatch;
            this.name = name;
            this.dealyTime = dealyTime;
        }

        @Override
        public void run() {

            try {
                Log.i("DaoExample", Thread.currentThread().getId() + " 开始做任务");
                Thread.sleep(dealyTime);
                Log.i("DaoExample", Thread.currentThread().getId() + " 完成任务 spend time:" + dealyTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }
    }


    class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Log.i("DaoExample", "tid:" + Thread.currentThread().getId() + " sleep ...");
                Thread.sleep(1000);
                Log.i("DaoExample", "tid:" + Thread.currentThread().getId() + " finised");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    int number;

    /**
     * 任务先分解，然后再合并
     * 汇总模式
     *
     * @param v
     */
    public void testForkAndJoin(View v) {
        Master master = new Master();
        Master.CallBack callBack = new Master.CallBack() {
            @Override
            public void itemfinished() {
                synchronized (MainActivity.this) {
                    number++;
                    Log.i(Master.TAG, "number:" + number + " thread id:" + Thread.currentThread().getId());
                }
            }
        };
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int value = random.nextInt(5);
            Master.Task task = new Master.Task(i, value * 1000, callBack);
            master.execute(task);
        }

    }

    public void testModifyVari(View v) {
//        Student student = new Student();
//        student.setAge(0);
//
//        List<Student> list = new ArrayList<>();
//        list.add(student);
//
//        modify(list.get(0));
//        // 测试机log.d是没问题，正式售卖的机器系统是不答应log的哦, log.i没问题
//        Log.i("DaoExample", "age:"+list.get(0).getAge());
//
//        Log.i("DaoExample", "age:"+student.getAge());
        String s =
                "packagenames=com.baidu.homework%2Ccom.vsco.cam%2Cso.ofo.labofo%2Ccom.hkairlines.apps%2Ccom.letv.whatslive%2Ccom.zcbl.bjjj_driving%2Cfm.qingting.qtradio%2Ccom.qiyi.video%2Ccom.rytong.airchina%2Ccom.sohu.inputmethod.sogou%2Ccom.mogujie%2Ccom.gtgj.view%2Ccom.android.bankabc%2Ccom.sankuai.meituan%2Ccom.airasia.mobile%2Ccom.youku.phone%2Ccom.cgbchina.xpt%2Cair.tv.douyu.android%2Ccom.tencent.qqmusic%2Ccom.sinovatech.unicom.ui%2Ccom.letv.android.letvlive%2Ccom.lesports.glivesports%2Ccom.csair.mbp%2Ccom.hexin.plat.android%2Ccom.smzdm.client.android%2Ccom.wandoujia.phoenix2.usbproxy%2Ccom.dianping.v1%2Ccom.mt.mtxx.mtxx%2Ccom.taou.maimai%2Ccmb.pb%2Ccom.cebbank.bankebb%2Ccom.jm.android.jumei%2Ccom.MobileTicket%2Ccom.letv.letvshop%2Ccom.tudou.android%2Ccom.xingin.xhs%2Ccom.bankcomm.Bankcomm%2Ccom.ifeng.news2%2Ccom.tencent.mm%2Ccom.rytong.app.bankhx%2Ccom.anjuke.android.app%2Ccom.bw30.zsch%2Ccom.sdu.didi.psnger%2Ccom.chinamworld.bocmbci%2Ccom.xunlei.downloadprovider%2Ccom.tencent.android.qqdownloader%2Ccn.amazon.mShop.android%2Cvz.com%2Ccom.xiangha%2Ccom.codoon.gps%2Ccom.lufax.android%2Ccom.tencent.qqpim%2Ccom.tencent.ttpic%2Ccom.yibasan.lizhifm%2Ccom.icbc%2Ccom.szzc%2Ccom.wuba%2Ccom.letv.android.client%2Ccom.mfw.roadbook%2Ccom.futurestar.mkmyle%2Ccom.le123.ysdq%2Ccom.shoujiduoduo.ringtone%2Ccom.beastbike.bluegogo%2Ccn.com.spdb.mobilebank.per%2Ccom.ichinait.gbpassenger%2Ccom.tencent.qqpimsecure%2Ccom.tencent.qqlive%2Ccom.meitu.meiyancamera%2Ccom.tencent.mtt%2Cfm.xiami.main%2Ccom.thestore.main%2Ccom.lingan.seeyou%2Ccom.cubic.autohome%2Ccom.kdlc.mcc%2Ccom.youdao.dict%2Ccom.kuaikan.comic%2Ccom.UCMobile%2Ccom.letv.kdweibo.client%2Ccom.sohu.sohuvideo%2Ccom.google.android.gms%2Ccom.google.android.gsf%2Ccom.baidu.video%2Ccom.tencent.mobileqq%2Ccom.ivvi.storeApp%2Ccom.pingan.pabank.activity%2Ccom.netease.newsreader.activity%2Ccom.suning.mobile.ebuy%2Ccom.kugou.android%2Ccom.gotokeep.keep%2Ccom.yoloho.dayima%2Ccom.wandoujia.phoenix2%2Ccom.snda.wifilocating%2Ccom.letv.games%2Ccom.jiuyan.infashion%2Ccom.immomo.momo%2Ccom.lphtsccft%2Ccom.air.sz%2Ccom.ximalaya.ting.android%2Ccom.letv.lesophoneclient%2Ccom.baidu.BaiduMap%2Ccom.soufun.app%2Ccom.meitu.meipaimv%2Ccom.tuniu.app.ui%2Ccom.google.android.gsf.login%2Ccom.jd.jrapp%2Ccom.flightmanager.view%2Ccom.pingan.carowner%2Ccom.chinamworld.main%2Ccom.baidu.lbs.waimai%2Ccn.eclicks.wzsearch%2Ccom.Qunar%2Ccom.tongcheng.android%2Ccom.qihoo.appstore%2Ccom.kaola%2Ccom.lashou.groupurchasing%2Ccom.nuomi%2Ccom.qzone%2Ccom.xueqiu.android%2Ccom.ecitic.bank.mobile%2Ccom.rytong.ceair%2Ccom.rytong.hnair%2Ccom.tencent.news%2CvStudio.Android.Camera360%2Ccom.netease.mail%2Ccom.jingdong.app.mall%2Ccom.tencent.karaoke%2Ccom.tmall.wireless%2Ctv.acfundanmaku.video%2Ccom.autonavi.minimap%2Ccom.citiccard.mobilebank%2Ccom.taobao.idlefish%2Cctrip.android.view%2Ccom.p1.mobile.putong%2Ccom.letv.jr%2Ccn.kuwo.player%2Ccom.shuqi.controller%2Ccom.ganji.android.haoche_c%2Cbubei.tingshu%2Ccom.smile.gifmaker%2Ccom.letv.ireader%2Ccom.android.dazhihui%2Ccom.meilishuo%2Ccom.taobao.taobao%2Ccom.china3s.android%2Ccom.cmbchina.ccd.pluto.cmbActivity%2Ccom.microfundrn%2Ccom.netease.cloudmusic%2Ccom.sina.news%2Ctv.danmaku.bili%2Ccom.dp.android.elong%2Ccom.changba%2Ccom.eg.android.AlipayGphone%2Ccom.taobao.ju.android%2Ccom.greenpoint.android.mc10086.activity%2Ccom.achievo.vipshop%2Ccom.qqreader.leshi&versioncodes=296%2C30170413%2C13660%2C201706090%2C68%2C17%2C632%2C80890%2C41600%2C660%2C944000%2C116%2C16%2C532%2C2045%2C129%2C20170623%2C10252200%2C665%2C52%2C130%2C147%2C20170704%2C4296%2C391%2C6215%2C9262%2C6770%2C1215%2C550%2C56%2C4605%2C137%2C10171%2C90%2C422004%2C3111%2C257%2C1080%2C4010%2C321811%2C43%2C250%2C69%2C10910%2C7072130%2C122451910%2C76%2C191%2C900%2C3070100%2C1480%2C496%2C113376%2C300025%2C77%2C71202%2C33000%2C375%2C24%2C67%2C6008330%2C201%2C96%2C35%2C1176%2C12708%2C6210%2C7703440%2C162%2C503%2C89%2C825%2C28%2C7040200%2C43100%2C699%2C606%2C6800%2C9879440%2C23%2C1073700540%2C708%2C100%2C300%2C697%2C173%2C8851%2C8253%2C160%2C16161%2C3130%2C33%2C96%2C1436%2C1700025531%2C56%2C133%2C1054%2C810%2C127%2C6210%2C114%2C23%2C62%2C127%2C3922%2C219%2C101%2C178%2C146%2C142%2C300070091%2C30070500%2C20170612%2C266%2C102%2C133%2C42%2C65%2C54547%2C5400%2C108611%2C120%2C49920%2C101%2C1780%2C440%2C6101%2C58%2C115%2C157%2C129%2C72%2C8503%2C138%2C3201%2C17611%2C4634%2C451%2C90747%2C143%2C159%2C630%2C61%2C40%2C97%2C149%2C510000%2C9302%2C824%2C112%2C474%2C41%2C708%2C10000305";
        String result = s.replaceAll("%2C", ",");
        Log.i("DaoExample", result);
        String arr[] = result.split("&");
        String pck = arr[0];
        String ps[] = pck.split("=");
        Log.i("DaoExample", ps[1]);

        String pckArr[] = ps[1].split(",");
        int index = -1;
        for (int i = 0; i < pckArr.length; i++) {
            if (pckArr[i].equals("com.icbc")) {
                index = i;
                break;
            }
        }
        Log.i("DaoExample", "index:" + index);

        String versionCo = arr[1];
        String versionCoA[] = versionCo.split("=");
        String aa[] = versionCoA[1].split(",");
        Log.i("DaoExample", aa[index]);

    }

    private void modify(Student student) {

        Student student2 = student;

        student2.setAge(student2.getAge() + 2);
    }

    Map<String, String> map = new ConcurrentHashMap<>();//

    int key = 0;
    public void testConcurrentHashMap(View v) {
        for (int i = 0; i < 1000; i++) {
            map.put(i + "", "i=" + i);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = iter.next();
                    Log.i("DaoExample", entry.getValue());
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    key++;
                    map.remove(key+"");
                    Log.i("DaoExample", "删除 key:"+key + ",map size:"+map.size());
                    if (map.size() == 0) {
                        break;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }
}
