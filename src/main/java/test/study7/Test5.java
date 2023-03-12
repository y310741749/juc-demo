package test.study7;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * forkjoin
 */
public class Test5 {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool=new ForkJoinPool(4);
        MyTask myTask=new MyTask(500);
        System.out.println("结果:"+forkJoinPool.invoke(myTask));
    }


}

@Slf4j
@AllArgsConstructor
@ToString
class MyTask extends RecursiveTask<Integer>{

    private int n;
    @Override
    protected Integer compute() {
        log.debug("n={}",n);
        if(n==1){
            return n;
        }
        MyTask newMytask = new MyTask(n - 1);
        newMytask.fork();
        Integer join = newMytask.join();
        return n+join;
    }
}
