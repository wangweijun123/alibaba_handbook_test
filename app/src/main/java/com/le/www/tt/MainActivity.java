package com.le.www.tt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
                    int i = 5/0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, 1L, 3L, TimeUnit.SECONDS);

    }

    /**
     * 异步转化为同步
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
                Log.d("DaoExample","External services validation completed !! Result was :: "+ result);
            }
        }).start();

    }


    public void testCountDownLatch2(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int taskCount = 3;
                CountDownLatch countDownLatch = new CountDownLatch(taskCount);
                ExecutorService executorService = Executors.newFixedThreadPool(taskCount);
                for (int i = 0; i < taskCount; i++) {
                    executorService.execute(new Task(countDownLatch, "i:"+i, (i+1)*1000));
                }
                try {
                    Log.d("DaoExample",Thread.currentThread().getId() + " 领导开始等待");
                    long beginTime = System.currentTimeMillis();
                    countDownLatch.await();
                    Log.d("DaoExample",Thread.currentThread().getId() + " 领导汇总花费time:"+(System.currentTimeMillis()-beginTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    class Task implements Runnable {
        private CountDownLatch countDownLatch;
        private String name;
        private long dealyTime;
        Task(CountDownLatch countDownLatch, String name, long dealyTime){
            this.countDownLatch = countDownLatch;
            this.name = name;
            this.dealyTime = dealyTime;
        }
        @Override
        public void run() {

            try {
                Log.d("DaoExample",Thread.currentThread().getId() + " 开始做任务");
                Thread.sleep(dealyTime);
                Log.d("DaoExample",Thread.currentThread().getId() + " 完成任务 spend time:"+dealyTime);
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
                Log.d("DaoExample", "tid:" + Thread.currentThread().getId() + " sleep ...");
                Thread.sleep(1000);
                Log.d("DaoExample", "tid:" + Thread.currentThread().getId() + " finised");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
