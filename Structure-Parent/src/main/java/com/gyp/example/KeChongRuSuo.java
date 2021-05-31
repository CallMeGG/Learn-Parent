package com.gyp.example;

/**
 * Title: 可重入锁和不可重入锁：区别同一个线程是否能进入被上锁的代码段。
 * date 2021/5/29 15:40
 *
 * @author GYP
 * @version 1.0
 */
public class KeChongRuSuo {

    MyLock myLock = new MyLock();

    MyReentrantLock myReentrantLock = new MyReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        //new MyCount().doSomeThingBuKeChongRu();
        new KeChongRuSuo().doSomeThingKeChongRu();
    }

    public void doSomeThingKeChongRu() throws InterruptedException {
        myReentrantLock.lock();
        System.out.println("doSomeThing");
        kezhongrudoJob();
        myReentrantLock.unlock();

    }

    public void doSomeThingBuKeChongRu() throws InterruptedException {
        myLock.lock();
        System.out.println("doSomeThing");
        doJob();
        myLock.unLock();

    }

    public void doJob() throws InterruptedException {
        myLock.lock();
        System.out.println("doJob");
        myLock.unLock();

    }

    public void kezhongrudoJob() throws InterruptedException {
        myReentrantLock.lock();
        System.out.println("kezhongrudoJob");
        myReentrantLock.unlock();

    }


    class MyLock {
        private boolean isLocked = false;

        public synchronized void lock() throws InterruptedException {
            while (isLocked) {
                wait();
            }
            isLocked = true;
        }

        public void unLock() {
            isLocked = false;
            notify();
        }
    }

    class MyReentrantLock {
        boolean isLocked = false;   // 默认没有上锁
        Thread lockedBy = null; // 记录阻塞线程
        int lockedCount = 0;    // 上锁次数计数

        /**
         * 上锁逻辑
         */
        public synchronized void lock() throws InterruptedException {
            Thread thread = Thread.currentThread();
            // 上锁了 并且 如果是同一个线程则放行，否则其它线程需要进入where循环进行等待
            while (isLocked && lockedBy != thread) {
                wait();
            }
            isLocked = true; // 第一次进入就进行上锁
            lockedCount++; // 上锁次数计数
            lockedBy = thread; // 当前阻塞的线程
        }

        /**
         * 释放锁逻辑
         */
        public synchronized void unlock() {
            if (Thread.currentThread() == this.lockedBy) {
                lockedCount--; // 将上锁次数减一
                if (lockedCount == 0) {// 当计数为0，说明所有线程都释放了锁
                    isLocked = false; // 真正的将释放了所有锁
                    notify();
                }
            }
        }
    }
}
